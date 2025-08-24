public class MaxConseCutiveOnes {

    /*
    Time Complexity: O(n)
    - Single pass through the array, each element visited once.

    Space Complexity: O(1)
    - Only a few integer variables used, independent of input size.
    */
    static int maxConsecutiveOnes(int[] nums) {
        int maxi = 0;  // Maximum consecutive 1's found so far
        int count = 0; // Current consecutive count of 1's

        for (int num : nums) {
            if (num == 1) {
                count++; // Increment count when 1 is found
                maxi = Math.max(maxi, count); // Update maximum
            } else {
                count = 0; // Reset count on 0
            }
        }
        return maxi;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 1, 1, 1};
        int ans = maxConsecutiveOnes(nums);
        System.out.println("The maximum consecutive 1's are " + ans);
    }
}
