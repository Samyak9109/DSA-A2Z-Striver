public class CountInversions {

    // ---------------------------------------------------
    // BRUTE FORCE APPROACH
    // ---------------------------------------------------
    // Idea:
    // - For every pair (i, j) with i < j, check if arr[i] > arr[j].
    // - If yes, it's an inversion → increment counter.
    //
    // Time Complexity: O(n^2)
    //   -> Double nested loop for pairs.
    // Space Complexity: O(1)
    //   -> Only variables used.
    static int countInversionBrute(int[] arr) {
        int n = arr.length;
        int cnt = 0;

        // Check all pairs
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) cnt += 1; // inversion found
            }
        }
        return cnt;
    }


    // ---------------------------------------------------
    // BETTER APPROACH (Binary Indexed Tree / Fenwick Tree)
    // ---------------------------------------------------
    // Idea:
    // - Compress values (coordinate compression).
    // - Traverse array from right to left.
    // - Use BIT to count how many smaller elements are already seen.
    //
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)
    // Note: This is advanced, so we move directly to optimal below.


    // ---------------------------------------------------
    // OPTIMAL APPROACH (Merge Sort based)
    // ---------------------------------------------------
    // Idea:
    // - During merge step of merge sort, whenever an element from
    //   right subarray is smaller than element from left subarray,
    //   it contributes inversions = (mid - i + 1).
    // - Count these while sorting.
    //
    // Time Complexity: O(n log n)
    //   -> Merge sort complexity.
    // Space Complexity: O(n)
    //   -> Temporary array for merging.
    public static int countInversionOptimal(int[] arr) {
        return mergeSortAndCount(arr, 0, arr.length - 1);
    }

    private static int mergeSortAndCount(int[] arr, int left, int right) {
        int cnt = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Count inversions in left part
            cnt += mergeSortAndCount(arr, left, mid);

            // Count inversions in right part
            cnt += mergeSortAndCount(arr, mid + 1, right);

            // Count inversions during merge
            cnt += mergeAndCount(arr, left, mid, right);
        }
        return cnt;
    }

    private static int mergeAndCount(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        int cnt = 0;

        // Merge two sorted halves
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                // All remaining elements in left half (i..mid) are > arr[j-1]
                cnt += (mid - i + 1);
            }
        }

        // Copy remaining elements
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        // Copy back to original array
        for (i = left, k = 0; i <= right; i++, k++) {
            arr[i] = temp[k];
        }

        return cnt;
    }


    // ---------------------------------------------------
    // MAIN METHOD FOR TESTING
    // ---------------------------------------------------
    public static void main(String[] args) {
        int[] arr1 = {8, 4, 2, 1}; // Expected inversions = 6
        int[] arr2 = {3, 1, 2};    // Expected inversions = 2

        System.out.println("Brute Force Count (arr1): " + countInversionBrute(arr1.clone()));
        System.out.println("Optimal Count (arr1): " + countInversionOptimal(arr1.clone()));

        System.out.println("Brute Force Count (arr2): " + countInversionBrute(arr2.clone()));
        System.out.println("Optimal Count (arr2): " + countInversionOptimal(arr2.clone()));
    }
}
