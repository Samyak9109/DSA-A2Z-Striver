public class MaxSubArraySum {

    // Brute Force: Check all subarrays and track the max sum
    static int maxSubarraySumBrute(int[] nums) {
        int n = nums.length;
        int maxi = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) { // Include j to form [i...j]
                    sum += nums[k]; // Compute sum of subarray [i...j]
                }
                maxi = Math.max(sum, maxi); // Update max sum if current is higher
            }
        }

        return maxi;

        // Time Complexity: O(N^3)
        // - Three nested loops, each up to N
        // Space Complexity: O(1)
        // - No extra space used
    }

    // Better Approach: Use a running sum to avoid recomputing sums
    static int maxSubarrayBetter(int[] nums) {
        int n = nums.length;
        int maxi = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j]; // Running sum of subarray [i...j]
                maxi = Math.max(sum, maxi); // Update max sum
            }
        }

        return maxi;

        // Time Complexity: O(N^2)
        // - Two nested loops, each up to N
        // - Inner loop uses O(1) addition (no third loop)
        // Space Complexity: O(1)
        // - No extra space used
    }

    // Optimal Approach: Kadane's Algorithm
    static int maxSubarrayOptimal(int[] nums) {
        int n = nums.length;
        int maxi = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i]; // Add current element to running sum
            if (sum > maxi) maxi = sum; // Update max sum if needed
            if (sum < 0) sum = 0; // Reset sum if it becomes negative
        }

        return maxi;

        // Time Complexity: O(N)
        // - Single loop through array
        // Space Complexity: O(1)
        // - Constant space used
    }

    // Optimal Approach to return and print the actual subarray
    static int[] printSubarray(int[] nums) {
        int n = nums.length;
        int maxi = Integer.MIN_VALUE;
        int sum = 0;

        int start = 0;
        int ansStart = -1, ansEnd = -1;

        for (int i = 0; i < n; i++) {
            if (sum == 0) start = i; // Potential new subarray start

            sum += nums[i]; // Add current element to running sum

            if (sum > maxi) { // Update max sum and indices
                maxi = sum;
                ansStart = start;
                ansEnd = i;
            }

            if (sum < 0) sum = 0; // Reset if running sum goes negative
        }

        // Print the subarray
        System.out.print("The subarray is: [");
        for (int i = ansStart; i <= ansEnd; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println("]");

        // Return the subarray as int[]
        int[] result = new int[ansEnd - ansStart + 1];
        for (int i = ansStart; i <= ansEnd; i++) {
            result[i - ansStart] = nums[i];
        }
        return result;

        // Time Complexity: O(N)
        // - Single loop through array
        // Space Complexity: O(K)
        // - K is the length of the result subarray (can be at most N)
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int maxSumBrute = maxSubarraySumBrute(arr);
        System.out.println("Maximum Subarray Sum (Brute): " + maxSumBrute);

        int maxSumBetter = maxSubarrayBetter(arr);
        System.out.println("Maximum Subarray Sum (Better): " + maxSumBetter);

        int maxSumOptimal = maxSubarrayOptimal(arr);
        System.out.println("Maximum Subarray Sum (Optimal): " + maxSumOptimal);

        printSubarray(arr); // Print the actual subarray with maximum sum
    }
}
