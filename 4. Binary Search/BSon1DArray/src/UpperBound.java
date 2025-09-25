public class UpperBound {

    /**
     * UPPER BOUND FUNCTION
     * --------------------
     * Finds the first index where nums[index] > target
     * Binary search for efficiency.
     *
     * Time Complexity: O(log n) -> halves the search space each iteration
     * Space Complexity: O(1) -> uses only variables, no extra space
     */
    static int upperBound(int[] nums, int target) {
        int n = nums.length;
        int ans = n; // default if no element > target
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // prevents overflow

            if (nums[mid] > target) {
                ans = mid;        // potential answer
                high = mid - 1;   // search left
            } else {
                low = mid + 1;    // search right
            }
        }

        return ans;
    }

    // MAIN METHOD: testing multiple targets on a single array
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9}; // single sorted array

        int[] targets = {0, 1, 4, 5, 6, 9, 10}; // multiple targets to test

        System.out.println("Array: " + java.util.Arrays.toString(nums));
        for (int target : targets) {
            int ub = upperBound(nums, target);
            System.out.println("Target " + target + " -> Upper bound index: " + ub);
        }
    }
}
