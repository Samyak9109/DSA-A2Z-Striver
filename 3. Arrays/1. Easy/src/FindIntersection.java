import java.util.*;

public class FindIntersection {

    // ===========================
    // Approach 1: Brute Force (Unsorted Arrays)
    // ===========================
    // Description:
    // Check each element of arr1 against all elements of arr2.
    // Ensure uniqueness in result by checking previously added elements.
    // Time Complexity: O(n * m + n^2) ~ O(n * m) – Nested loops + duplicate check
    // Space Complexity: O(min(n, m)) – Result array stores intersection
    static ArrayList<Integer> intersectionUnsortedBrute(int[] arr1, int[] arr2, int n, int m) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean alreadyExists = false;
            // Avoid duplicates in result
            for (int k : result) {
                if (k == arr1[i]) {
                    alreadyExists = true;
                    break;
                }
            }
            if (alreadyExists) continue;

            // Check if element exists in arr2
            for (int j = 0; j < m; j++) {
                if (arr1[i] == arr2[j]) {
                    result.add(arr1[i]);
                    break;
                }
            }
        }
        return result;
    }

    // ===========================
    // Approach 2: Better (Unsorted Arrays – HashSet)
    // ===========================
    // Description:
    // Use HashSet for O(1) lookup to find intersection uniquely.
    // Time Complexity: O(n + m) – Insert arr1 elements + lookup arr2 elements
    // Space Complexity: O(n + m) – set1 + resultSet
    static ArrayList<Integer> intersectionUnsortedBetter(int[] arr1, int[] arr2, int n, int m) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> resultSet = new HashSet<>();

        // Add arr1 elements to set1 for fast lookup
        for (int i = 0; i < n; i++)
            set1.add(arr1[i]);

        // Check arr2 elements in set1
        for (int i = 0; i < m; i++) {
            if (set1.contains(arr2[i]))
                resultSet.add(arr2[i]); // Only unique elements added
        }

        return new ArrayList<>(resultSet);
    }

    // ===========================
    // Approach 3: Optimal for Sorted Arrays – Two Pointers
    // ===========================
    // Description:
    // Traverse both sorted arrays with two pointers to find intersection.
    // Time Complexity: O(n + m) – Single pass through both arrays
    // Space Complexity: O(min(n, m)) – For result array
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
                // Match found
                result.add(arr1[i]);
                i++;
                j++;
            }
        }
        return result;
    }

    // ===========================
    // Approach 4: Intersection with Duplicates (HashMap Frequency)
    // ===========================
    // Description:
    // Count frequency of arr1 elements, match with arr2 reducing counts.
    // Time Complexity: O(n + m) – Count arr1 + process arr2
    // Space Complexity: O(n) – For frequency map
    static ArrayList<Integer> intersectionWithDuplicates(int[] arr1, int[] arr2, int n, int m) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();

        // Count frequencies of arr1
        for (int i = 0; i < n; i++)
            freq.put(arr1[i], freq.getOrDefault(arr1[i], 0) + 1);

        // Process arr2 and reduce frequency to handle duplicates
        for (int i = 0; i < m; i++) {
            if (freq.getOrDefault(arr2[i], 0) > 0) {
                result.add(arr2[i]);
                freq.put(arr2[i], freq.get(arr2[i]) - 1);
            }
        }
        return result;
    }

    // ===========================
    // Main Method: Test All Approaches
    // ===========================
    public static void main(String[] args) {
        int[] sorted1 = {1, 2, 2, 3, 4, 5};
        int[] sorted2 = {2, 2, 3, 5, 6};
        int[] unsorted1 = {4, 5, 9, 4, 8};
        int[] unsorted2 = {5, 4, 4, 10};

        System.out.println("Unsorted Brute Force: " +
                intersectionUnsortedBrute(unsorted1, unsorted2, unsorted1.length, unsorted2.length));
        System.out.println("Unsorted Better (HashSet): " +
                intersectionUnsortedBetter(unsorted1, unsorted2, unsorted1.length, unsorted2.length));
        System.out.println("Sorted Optimal (Two Pointer): " +
                intersectionSortedOptimal(sorted1, sorted2, sorted1.length, sorted2.length));
        System.out.println("Intersection with Duplicates: " +
                intersectionWithDuplicates(sorted1, sorted2, sorted1.length, sorted2.length));
    }
}
