import java.util.ArrayList;

public class ReversePairs {

    // -------------------------------------------------
    // BRUTE FORCE APPROACH
    // -------------------------------------------------
    /**
     * Idea:
     * - For each element arr[i], check every arr[j] where j > i.
     * - Count pairs where arr[i] > 2 * arr[j].
     * - Cast to long to prevent integer overflow.
     *
     * Time Complexity: O(n^2)
     *   -> Two nested loops, each up to n.
     * Space Complexity: O(1)
     *   -> Only variables are used.
     */
    static int reversePairsBrute(int[] arr) {
        int n = arr.length;
        int count = 0;

        // Check all pairs (i, j) with i < j
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Use long to avoid integer overflow
                if ((long) arr[i] > 2L * arr[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    // -------------------------------------------------
    // OPTIMAL APPROACH (Merge Sort Based)
    // -------------------------------------------------
    /**
     * Merge function:
     * - Merges two sorted halves of the array.
     * - Maintains sorted order while combining.
     * - Uses a temporary ArrayList to store merged result.
     *
     * Time Complexity: O(high - low + 1)
     *   -> Each element merged once.
     * Space Complexity: O(high - low + 1)
     *   -> Temporary ArrayList for merged elements.
     */
    static void merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>();
        int left = low;      // pointer for left half
        int right = mid + 1; // pointer for right half

        // Merge elements from both halves in sorted order
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left++]);
            } else {
                temp.add(arr[right++]);
            }
        }

        // Copy remaining elements from left half
        while (left <= mid) temp.add(arr[left++]);

        // Copy remaining elements from right half
        while (right <= high) temp.add(arr[right++]);

        // Transfer merged result back to original array
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    /**
     * Count pairs across two sorted halves.
     * - Left half: arr[low..mid]
     * - Right half: arr[mid+1..high]
     * - For each element in left half:
     *   -> Find how many elements in right half satisfy arr[left] > 2*arr[right].
     */
    static int countPairs(int[] arr, int low, int mid, int high) {
        int right = mid + 1; // start pointer for right half
        int count = 0;

        // Traverse left half
        for (int left = low; left <= mid; left++) {
            // Move right pointer until condition fails
            while (right <= high && (long) arr[left] > 2L * arr[right]) {
                right++;
            }
            // Add number of valid elements from right half
            count += (right - (mid + 1));
        }
        return count;
    }

    /**
     * Modified Merge Sort function:
     * - Recursively sort left and right halves.
     * - Count inversions (reverse pairs) while merging.
     *
     * Time Complexity: O(n log n)
     *   -> Array split log(n) times, each merge O(n).
     * Space Complexity: O(n)
     *   -> Temporary arrays for merging.
     */
    static int mergeSort(int[] arr, int low, int high) {
        int count = 0;

        // Base condition: single element has no pairs
        if (low >= high) return count;

        // Find mid index
        int mid = (low + high) / 2;

        // Count in left half
        count += mergeSort(arr, low, mid);

        // Count in right half
        count += mergeSort(arr, mid + 1, high);

        // Count across left and right halves
        count += countPairs(arr, low, mid, high);

        // Finally merge the two sorted halves
        merge(arr, low, mid, high);

        return count;
    }

    // Wrapper function for optimal approach
    static int reversePairsOptimal(int[] arr) {
        return mergeSort(arr, 0, arr.length - 1);
    }

    // -------------------------------------------------
    // MAIN METHOD FOR TESTING
    // -------------------------------------------------
    public static void main(String[] args) {
        // Example 1: Large numbers (check overflow handling)
        int[] nums1 = {2147483647, 2147483647, 2147483647,
                2147483647, 2147483647, 2147483647};
        System.out.println("Brute Force Output (nums1): " + reversePairsBrute(nums1.clone()));
        System.out.println("Optimal Output (nums1): " + reversePairsOptimal(nums1.clone()));

        // Example 2: Small array (easy to verify manually)
        int[] nums2 = {1, 3, 2, 3, 1}; // Expected reverse pairs = 2 -> (3,1), (3,1)
        System.out.println("Brute Force Output (nums2): " + reversePairsBrute(nums2.clone()));
        System.out.println("Optimal Output (nums2): " + reversePairsOptimal(nums2.clone()));

        // Example 3: Strictly descending array (maximum reverse pairs)
        int[] nums3 = {5, 4, 3, 2, 1}; // Expected reverse pairs = 4 (5,2), (5,1), (4,1), (3,1)
        System.out.println("Brute Force Output (nums3): " + reversePairsBrute(nums3.clone()));
        System.out.println("Optimal Output (nums3): " + reversePairsOptimal(nums3.clone()));
    }
}
