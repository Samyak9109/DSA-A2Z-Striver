import java.util.*;

public class FindIntersection {

    // -------------------------------------------------------------------
    // Brute Force (Unsorted Arrays)
    // Check each element of arr1 against all elements of arr2,
    // and ensure uniqueness in result by checking previously added elements.
    static ArrayList<Integer> intersectionUnsortedBrute(int[] arr1, int[] arr2, int n, int m) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean alreadyExists = false;
            // Check if arr1[i] already in result to avoid duplicates
            for (int k : result) {
                if (k == arr1[i]) {
                    alreadyExists = true;
                    break;
                }
            }
            if (alreadyExists) continue;

            // Check if arr1[i] is in arr2
            for (int j = 0; j < m; j++) {
                if (arr1[i] == arr2[j]) {
                    result.add(arr1[i]);
                    break;
                }
            }
        }
        return result;
    }
    // Time Complexity:
    // O(n * m) – For each element in arr1, scan entire arr2
    // Plus O(k) per element for duplicate check where k is size of result (worst O(n))
    // Total worst-case: O(n * m + n^2) ~ O(n * m)
    // Space Complexity:
    // O(min(n, m)) – result stores intersection elements

    // -------------------------------------------------------------------
    // Better Approach (Unsorted Arrays) - Using HashSet
    // Use HashSet for quick lookup to find intersection uniquely
    static ArrayList<Integer> intersectionUnsortedBetter(int[] arr1, int[] arr2, int n, int m) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> resultSet = new HashSet<>();

        // Add all elements of arr1 to set1 for O(1) lookups
        for (int i = 0; i < n; i++)
            set1.add(arr1[i]);

        // For each element in arr2, check if present in set1
        // Add to resultSet to keep unique values only
        for (int i = 0; i < m; i++) {
            if (set1.contains(arr2[i]))
                resultSet.add(arr2[i]);
        }

        return new ArrayList<>(resultSet);
    }
    // Time Complexity:
    // O(n) – Insert all arr1 elements into HashSet
    // O(m) – Lookup each arr2 element in set1
    // Total Time Complexity: O(n + m)
    // Space Complexity:
    // O(n + m) – For set1 and resultSet in worst case

    // -------------------------------------------------------------------
    // Optimal Approach for Sorted Arrays - Two Pointers
    // Traverse both sorted arrays simultaneously to find intersection
    static ArrayList<Integer> intersectionSortedOptimal(int[] arr1, int[] arr2, int n, int m) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < n && j < m) {
            // Skip duplicates in arr1
            if (i > 0 && arr1[i] == arr1[i - 1]) {
                i++;
                continue;
            }
            if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr2[j] < arr1[i]) {
                j++;
            } else {
                // Both equal - add to result
                result.add(arr1[i]);
                i++;
                j++;
            }
        }
        return result;
    }
    // Time Complexity:
    // O(n + m) – Single pass through both arrays
    // Total Time Complexity: O(n + m)
    // Space Complexity:
    // O(min(n, m)) – for storing intersection elements

    // -------------------------------------------------------------------
    // Using HashMap for Frequency - Handles duplicates intersection
    // Count frequency of arr1 elements, then match with arr2 reducing counts
    static ArrayList<Integer> intersectionWithDuplicates(int[] arr1, int[] arr2, int n, int m) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();

        // Count frequencies of elements in arr1
        for (int i = 0; i < n; i++)
            freq.put(arr1[i], freq.getOrDefault(arr1[i], 0) + 1);

        // For each element in arr2, add to result if frequency > 0
        // Decrement frequency to avoid overcounting duplicates
        for (int i = 0; i < m; i++) {
            if (freq.getOrDefault(arr2[i], 0) > 0) {
                result.add(arr2[i]);
                freq.put(arr2[i], freq.get(arr2[i]) - 1);
            }
        }
        return result;
    }
    // Time Complexity:
    // O(n) – Count frequencies in arr1
    // O(m) – Process arr2 with frequency checks
    // Total Time Complexity: O(n + m)
    // Space Complexity:
    // O(n) – For frequency map

    // -------------------------------------------------------------------
    // Main method to test all implementations
    public static void main(String[] args) {
        int[] sorted1 = {1, 2, 2, 3, 4, 5};
        int[] sorted2 = {2, 2, 3, 5, 6};
        int[] unsorted1 = {4, 5, 9, 4, 8};
        int[] unsorted2 = {5, 4, 4, 10};

        System.out.println("Unsorted Brute: " +
                intersectionUnsortedBrute(unsorted1, unsorted2, unsorted1.length, unsorted2.length));
        System.out.println("Unsorted Better (HashSet): " +
                intersectionUnsortedBetter(unsorted1, unsorted2, unsorted1.length, unsorted2.length));
        System.out.println("Sorted Optimal (Two Pointer): " +
                intersectionSortedOptimal(sorted1, sorted2, sorted1.length, sorted2.length));
        System.out.println("Intersection with Duplicates: " +
                intersectionWithDuplicates(sorted1, sorted2, sorted1.length, sorted2.length));
    }
}
