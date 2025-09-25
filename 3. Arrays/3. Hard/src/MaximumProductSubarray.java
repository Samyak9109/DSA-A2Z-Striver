public class MaximumProductSubarray {

    /**
     * BRUTE FORCE APPROACH
     * ---------------------
     * Generate all possible subarrays and calculate their products.
     * Time Complexity: O(n^3) → triple nested loop
     * Space Complexity: O(1) → only variables used
     */
    public int maxProductBrute(int[] nums) {
        int n = nums.length;
        int result = Integer.MIN_VALUE;

        // Loop over all starting indices
        for (int start = 0; start < n; start++) {
            // Loop over all ending indices >= start
            for (int end = start; end < n; end++) {
                int product = 1;
                // Calculate product of subarray nums[start..end]
                for (int k = start; k <= end; k++) {
                    product *= nums[k];
                }
                result = Math.max(result, product);
            }
        }
        return result;
    }

    /**
     * BETTER APPROACH
     * ----------------
     * Expand subarrays from start index, keeping a running product.
     * Time Complexity: O(n^2) → double nested loop
     * Space Complexity: O(1)
     */
    public int maxProductBetter(int[] nums) {
        int n = nums.length;
        int result = Integer.MIN_VALUE;

        for (int start = 0; start < n; start++) {
            int product = 1;
            for (int end = start; end < n; end++) {
                product *= nums[end]; // accumulate product
                result = Math.max(result, product);
            }
        }
        return result;
    }

    /**
     * OPTIMAL APPROACH 1 (Kadane's-like)
     * -----------------------------------
     * Track both max and min products at each index to handle negative numbers.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int maxProductOptimal1(int[] nums) {
        int n = nums.length;
        int maxEndingHere = nums[0]; // max product ending at current index
        int minEndingHere = nums[0]; // min product ending at current index
        int result = nums[0];        // overall max product

        for (int i = 1; i < n; i++) {
            int current = nums[i];

            // Compute max product at current index
            int tempMax = Math.max(current, Math.max(maxEndingHere * current, minEndingHere * current));

            // Compute min product at current index
            minEndingHere = Math.min(current, Math.min(maxEndingHere * current, minEndingHere * current));

            // Update max product ending here
            maxEndingHere = tempMax;

            // Update overall result
            result = Math.max(result, maxEndingHere);
        }

        return result;
    }

    /**
     * OPTIMAL APPROACH 2 (Prefix & Suffix scan)
     * -----------------------------------------
     * Scan left-to-right and right-to-left, keeping running products.
     * Reset product to 1 on encountering 0.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int maxProductOptimal2(int[] nums) {
        int n = nums.length;
        int maxProduct = Integer.MIN_VALUE;
        int prefixProduct = 1;
        int suffixProduct = 1;

        for (int i = 0; i < n; i++) {
            prefixProduct = (prefixProduct == 0) ? nums[i] : prefixProduct * nums[i];
            suffixProduct = (suffixProduct == 0) ? nums[n - 1 - i] : suffixProduct * nums[n - 1 - i];

            maxProduct = Math.max(maxProduct, Math.max(prefixProduct, suffixProduct));
        }

        return maxProduct;
    }

    // Main method for testing all approaches
    public static void main(String[] args) {
        MaximumProductSubarray solver = new MaximumProductSubarray();
        int[] nums = {2, 3, -2, 4};

        System.out.println("Brute Force: " + solver.maxProductBrute(nums));
        System.out.println("Better: " + solver.maxProductBetter(nums));
        System.out.println("Optimal 1: " + maxProductOptimal1(nums));
        System.out.println("Optimal 2: " + maxProductOptimal2(nums));
    }
}
