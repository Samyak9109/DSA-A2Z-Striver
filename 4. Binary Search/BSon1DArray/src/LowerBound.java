public class LowerBound {

    /**
     * LOWER BOUND FUNCTION
     * --------------------
     * Finds the first index where nums[index] >= target
     * Uses binary search for efficiency.
     *
     * Time Complexity: O(log n) -> classic binary search
     * Space Complexity: O(1) -> just a few variables, no extra data structures
     */
    static int lowerBound(int[] nums, int target) {
        int n = nums.length;
        int ans = n; // default answer if no element >= target
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // avoids overflow

            if (nums[mid] >= target) {
                ans = mid;        // potential lower bound found
                high = mid - 1;   // look left to find an earlier occurrence
            } else {
                low = mid + 1;    // target must be on the right
            }
        }

        return ans;
    }

    // MAIN METHOD to test the lowerBound function
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int target1 = 5;
        int target2 = 6;
        int target3 = 10;

        System.out.println("Lower bound of " + target1 + " is at index: " + lowerBound(nums, target1)); // 2
        System.out.println("Lower bound of " + target2 + " is at index: " + lowerBound(nums, target2)); // 3
        System.out.println("Lower bound of " + target3 + " is at index: " + lowerBound(nums, target3)); // 5 (past end)
    }
}
