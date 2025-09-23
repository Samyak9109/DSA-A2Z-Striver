public class MaxConsecutiveOnes {

    // -------------------------------------------------------------------
    // Approach: Single Pass Counter
    // Description:
    // Traverse the array while counting consecutive 1's.
    // Reset counter to 0 whenever a 0 is encountered.
    // Keep track of maximum consecutive 1's in a variable.
    //
    // Time Complexity: O(n)
    // - Single pass through the array; each element visited once.
    //
    // Space Complexity: O(1)
    // - Only a few integer variables used, independent of input size.
    // -------------------------------------------------------------------
    static int maxConsecutiveOnes(int[] nums) {
        int maxi = 0;  // Maximum consecutive 1's found so far
        int count = 0; // Current consecutive count of 1's

        for (int num : nums) {
            if (num == 1) {
                count++;                   // Increment count when 1 is found
                maxi = Math.max(maxi, count); // Update maximum if needed
            } else {
                count = 0; // Reset count when 0 is found
            }
        }
        return maxi;
    }

    // -------------------------------------------------------------------
    // Main method to test the function
    // -------------------------------------------------------------------
    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 1, 1, 1}; // Example input
        int ans = maxConsecutiveOnes(nums);
        System.out.println("The maximum consecutive 1's are " + ans);
        // Expected output: 3 (last three 1's)
    }
}
