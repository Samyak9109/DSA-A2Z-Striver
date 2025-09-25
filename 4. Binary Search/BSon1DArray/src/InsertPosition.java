public class InsertPosition {

    /**
     * SEARCH INSERT POSITION
     * ----------------------
     * Finds the index of the target if found.
     * If not found, returns the index where it would be inserted to maintain sorted order.
     * Uses binary search for O(log n) efficiency.
     *
     * Time Complexity: O(log n) -> binary search halves the search space each iteration
     * Space Complexity: O(1) -> only uses variables, no extra data structures
     */
    static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int ans = n; // default insert position if target > all elements
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // safe mid calculation

            if (nums[mid] >= target) {
                ans = mid;      // potential insert position
                high = mid - 1; // search left for earlier occurrence
            } else {
                low = mid + 1;  // search right
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9}; // single sorted array
        int[] targets = {5, 6, 10, 0, 8}; // multiple targets to test

        System.out.println("Array: " + java.util.Arrays.toString(nums));
        for (int target : targets) {
            int pos = searchInsert(nums, target);
            System.out.println("Target " + target + " -> Insert position: " + pos);
        }
    }
}
