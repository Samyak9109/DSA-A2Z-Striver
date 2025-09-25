public class CountInversions {

    // ---------------------------------------------------
    // BRUTE FORCE APPROACH
    // ---------------------------------------------------
    // Idea:
    // - For every pair (low < high), check if arr[low] > arr[high].
    // - If yes, it's an inversion → increment counter.
    //
    // Time Complexity: O(n^2)
    //   -> Outer loop runs n times.
    //   -> Inner loop runs up to n times.
    //   -> Total comparisons ~ n*(n-1)/2 → O(n^2).
    // Space Complexity: O(1)
    //   -> Only counter variable is used.
    static int countInversionBrute(int[] arr) {
        int n = arr.length;
        int count = 0;

        // Check all possible pairs (low, high)
        for (int low = 0; low < n; low++) {
            for (int high = low + 1; high < n; high++) {
                // If arr[low] > arr[high] → inversion found
                if (arr[low] > arr[high]) count += 1;
            }
        }
        return count;
    }

    // ---------------------------------------------------
    // OPTIMAL APPROACH (Merge Sort based)
    // ---------------------------------------------------
    // Idea:
    // - Use modified merge sort.
    // - While merging two sorted halves:
    //   -> If arr[left] > arr[right], then arr[left..mid] are all > arr[right].
    //   -> This adds (mid - left + 1) inversions at once.
    // - Continue recursively.
    //
    // Time Complexity: O(n log n)
    //   -> Merge sort splits log(n) times.
    //   -> Each merge takes O(n).
    // Space Complexity: O(n)
    //   -> Temporary array used for merging.
    public static int countInversionOptimal(int[] arr) {
        return mergeSortAndCount(arr, 0, arr.length - 1);
    }

    // Recursive merge sort function with inversion counting
    private static int mergeSortAndCount(int[] arr, int low, int high) {
        int count = 0;
        if (low < high) {
            int mid = low + (high - low) / 2;

            // Count inversions in left half
            count += mergeSortAndCount(arr, low, mid);

            // Count inversions in right half
            count += mergeSortAndCount(arr, mid + 1, high);

            // Count inversions across left and right halves
            count += mergeAndCount(arr, low, mid, high);
        }
        return count;
    }

    // Merge function that counts inversions while merging
    private static int mergeAndCount(int[] arr, int low, int mid, int high) {
        // Temporary array to store merged result
        int[] temp = new int[high - low + 1];
        int left = low;     // Pointer for left half
        int right = mid + 1; // Pointer for right half
        int index = 0;      // Pointer for temp array
        int count = 0;      // Inversion counter

        // Merge two sorted halves
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                // If arr[left] <= arr[right], no inversion
                temp[index++] = arr[left++];
            } else {
                // If arr[left] > arr[right], all elements from left..mid are greater
                temp[index++] = arr[right++];
                count += (mid - left + 1); // Count inversions
            }
        }

        // Copy remaining elements from left half
        while (left <= mid) temp[index++] = arr[left++];

        // Copy remaining elements from right half
        while (right <= high) temp[index++] = arr[right++];

        // Copy sorted elements back to original array
        for (int i = low, k = 0; i <= high; i++, k++) {
            arr[i] = temp[k];
        }

        return count; // Return number of inversions in this merge
    }

    // ---------------------------------------------------
    // MAIN METHOD FOR TESTING
    // ---------------------------------------------------
    public static void main(String[] args) {
        int[] arr1 = {8, 4, 2, 1}; // Expected inversions = 6
        int[] arr2 = {3, 1, 2};    // Expected inversions = 2

        // Test brute force
        System.out.println("Brute Force Count (arr1): " + countInversionBrute(arr1.clone()));
        System.out.println("Brute Force Count (arr2): " + countInversionBrute(arr2.clone()));

        // Test optimal (merge sort)
        System.out.println("Optimal Count (arr1): " + countInversionOptimal(arr1.clone()));
        System.out.println("Optimal Count (arr2): " + countInversionOptimal(arr2.clone()));
    }
}
