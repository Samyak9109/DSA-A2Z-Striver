import java.util.*;

public class FindTheUnion {

    // ===========================
    // Approach 1: Brute Force for Sorted Arrays
    // ===========================
    // Description:
    // Use ArrayList and check contains() before adding elements.
    // Sort at the end to get sorted union.
    // Time Complexity: O((n + m) * k + (n + m) log (n + m)), k = size of union (contains() check)
    // Space Complexity: O(n + m) – for union list
    static ArrayList<Integer> unionSortedBrute(int[] arr1, int[] arr2, int n, int m) {
        ArrayList<Integer> union = new ArrayList<>();
        // Add elements from arr1 if not already in union
        for (int i = 0; i < n; i++) {
            if (!union.contains(arr1[i]))
                union.add(arr1[i]);
        }
        // Add elements from arr2 if not already in union
        for (int i = 0; i < m; i++) {
            if (!union.contains(arr2[i]))
                union.add(arr2[i]);
        }
        Collections.sort(union); // Sort union
        return union;
    }

    // ===========================
    // Approach 2: Better Approach Using HashSet
    // ===========================
    // Description:
    // HashSet ensures uniqueness automatically. Sorting done after.
    // Time Complexity: O(n + m + k log k), k = unique elements count
    // Space Complexity: O(n + m)
    static ArrayList<Integer> unionUsingHashSet(int[] arr1, int[] arr2, int n, int m) {
        HashSet<Integer> set = new HashSet<>();
        for (int val : arr1) set.add(val);
        for (int val : arr2) set.add(val);
        ArrayList<Integer> union = new ArrayList<>(set);
        Collections.sort(union); // Sort union
        return union;
    }

    // ===========================
    // Approach 3: Optimal for Sorted Arrays (Two Pointers)
    // ===========================
    // Description:
    // Traverse both sorted arrays using two pointers, skip duplicates
    // Time Complexity: O(n + m) – Single pass
    // Space Complexity: O(n + m) – For union list
    static ArrayList<Integer> unionSortedOptimal(int[] arr1, int[] arr2, int n, int m) {
        ArrayList<Integer> union = new ArrayList<>();
        int i = 0, j = 0;

        while (i < n && j < m) {
            if (i > 0 && arr1[i] == arr1[i - 1]) { i++; continue; } // skip duplicates in arr1
            if (j > 0 && arr2[j] == arr2[j - 1]) { j++; continue; } // skip duplicates in arr2

            if (arr1[i] < arr2[j]) union.add(arr1[i++]);
            else if (arr2[j] < arr1[i]) union.add(arr2[j++]);
            else { union.add(arr1[i]); i++; j++; } // equal elements, add once
        }

        // Add remaining elements from arr1
        while (i < n) {
            if (i == 0 || arr1[i] != arr1[i - 1])
                union.add(arr1[i]);
            i++;
        }

        // Add remaining elements from arr2
        while (j < m) {
            if (j == 0 || arr2[j] != arr2[j - 1])
                union.add(arr2[j]);
            j++;
        }

        return union;
    }

    // ===========================
    // Approach 4: Brute Force for Unsorted Arrays
    // ===========================
    // Description:
    // ArrayList contains() + sort at the end
    // Time Complexity: O((n + m) * k + (n + m) log (n + m)), similar to sorted brute
    // Space Complexity: O(n + m)
    static ArrayList<Integer> unionUnsortedBrute(int[] arr1, int[] arr2, int n, int m) {
        ArrayList<Integer> union = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (!union.contains(arr1[i])) union.add(arr1[i]);
        for (int i = 0; i < m; i++)
            if (!union.contains(arr2[i])) union.add(arr2[i]);
        Collections.sort(union); // sort at end
        return union;
    }

    // ===========================
    // Approach 5: Optimal for Unsorted Arrays Using HashSet
    // ===========================
    // Description:
    // HashSet automatically handles duplicates
    // Time Complexity: O(n + m)
    // Space Complexity: O(n + m)
    static ArrayList<Integer> unionUnsortedOptimal(int[] arr1, int[] arr2, int n, int m) {
        HashSet<Integer> set = new HashSet<>();
        for (int val : arr1) set.add(val);
        for (int val : arr2) set.add(val);
        return new ArrayList<>(set); // convert set to list
    }

    // ===========================
    // Main Method: Test All Approaches
    // ===========================
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
