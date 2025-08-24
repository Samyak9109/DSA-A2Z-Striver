import java.util.*;

public class FindTheUnion {

    // 1. Brute Force (Sorted arrays) - Using ArrayList contains check + sort
    // Time Complexity: O((n + m) * k + (n + m) log (n + m)) where k is union size (due to contains())
    // Space Complexity: O(n + m) for union list
    static ArrayList<Integer> unionSortedBrute(int[] arr1, int[] arr2, int n, int m) {
        ArrayList<Integer> union = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!union.contains(arr1[i]))  // O(k) contains check
                union.add(arr1[i]);
        }
        for (int i = 0; i < m; i++) {
            if (!union.contains(arr2[i]))  // O(k) contains check
                union.add(arr2[i]);
        }
        Collections.sort(union);  // O((n+m) log (n+m))
        return union;
    }

    // 2. Better Approach (Sorted or Unsorted) - Using HashSet + sorting result
    // Time Complexity: O(n + m + k log k), k = unique elements count
    // Space Complexity: O(n + m)
    static ArrayList<Integer> unionUsingHashSet(int[] arr1, int[] arr2, int n, int m) {
        HashSet<Integer> set = new HashSet<>();
        for (int val : arr1) set.add(val);  // O(n)
        for (int val : arr2) set.add(val);  // O(m)
        ArrayList<Integer> union = new ArrayList<>(set);
        Collections.sort(union);  // O(k log k)
        return union;
    }

    // 3. Optimal for Sorted Arrays - Two pointers without extra sorting
    // Time Complexity: O(n + m) single pass through both arrays
    // Space Complexity: O(n + m) worst case all unique elements
    static ArrayList<Integer> unionSortedOptimal(int[] arr1, int[] arr2, int n, int m) {
        ArrayList<Integer> union = new ArrayList<>();
        int i = 0, j = 0;

        while (i < n && j < m) {
            // Skip duplicates in arr1
            if (i > 0 && arr1[i] == arr1[i - 1]) {
                i++;
                continue;
            }
            // Skip duplicates in arr2
            if (j > 0 && arr2[j] == arr2[j - 1]) {
                j++;
                continue;
            }

            if (arr1[i] < arr2[j]) {
                union.add(arr1[i++]);
            } else if (arr2[j] < arr1[i]) {
                union.add(arr2[j++]);
            } else {
                union.add(arr1[i]);  // equal elements, add once
                i++;
                j++;
            }
        }
        // Add remaining unique elements from arr1
        while (i < n) {
            if (i == 0 || arr1[i] != arr1[i - 1])
                union.add(arr1[i]);
            i++;
        }
        // Add remaining unique elements from arr2
        while (j < m) {
            if (j == 0 || arr2[j] != arr2[j - 1])
                union.add(arr2[j]);
            j++;
        }
        return union;
    }

    // 4. Brute Force for Unsorted arrays - Using ArrayList contains check + sorting
    // Time Complexity: O((n + m) * k + (n + m) log (n + m)) (similar to sorted brute)
    // Space Complexity: O(n + m)
    static ArrayList<Integer> unionUnsortedBrute(int[] arr1, int[] arr2, int n, int m) {
        ArrayList<Integer> union = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!union.contains(arr1[i]))
                union.add(arr1[i]);
        }
        for (int i = 0; i < m; i++) {
            if (!union.contains(arr2[i]))
                union.add(arr2[i]);
        }
        Collections.sort(union);
        return union;
    }

    // 5. Optimal for Unsorted arrays - Using HashSet (same as better above, no sorting if order irrelevant)
    // Time Complexity: O(n + m)
    // Space Complexity: O(n + m)
    static ArrayList<Integer> unionUnsortedOptimal(int[] arr1, int[] arr2, int n, int m) {
        HashSet<Integer> set = new HashSet<>();
        for (int val : arr1) set.add(val);
        for (int val : arr2) set.add(val);
        return new ArrayList<>(set);
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] sorted1 = {1, 2, 2, 3, 4, 5};
        int[] sorted2 = {2, 3, 5, 6, 7};
        int[] unsorted1 = {5, 3, 1, 2, 4, 2};
        int[] unsorted2 = {7, 2, 3, 5, 6};

        System.out.println("Union Sorted Brute: " + unionSortedBrute(sorted1, sorted2, sorted1.length, sorted2.length));
        System.out.println("Union Using HashSet: " + unionUsingHashSet(sorted1, sorted2, sorted1.length, sorted2.length));
        System.out.println("Union Sorted Optimal: " + unionSortedOptimal(sorted1, sorted2, sorted1.length, sorted2.length));
        System.out.println("Union Unsorted Brute: " + unionUnsortedBrute(unsorted1, unsorted2, unsorted1.length, unsorted2.length));
        System.out.println("Union Unsorted Optimal: " + unionUnsortedOptimal(unsorted1, unsorted2, unsorted1.length, unsorted2.length));
    }
}
