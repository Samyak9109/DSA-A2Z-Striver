public class SmallestDivisorInRange {

    /*
       Goal: Find the smallest divisor such that:
             sum( ceil(nums[i] / divisor) ) <= threshold

       Key Insight:
       - As divisor increases → sum decreases (monotonic relationship).
       - So we can binary search the divisor in range [1, max(nums)].

       Time Complexity: O(n * log(max(nums)))
         -> log(max(nums)) for binary search iterations
         -> each iteration needs O(n) to compute sum
       Space Complexity: O(1)
         -> only integers used, no extra space
    */

    // -------- Optimal Binary Search Approach --------
    public static int smallestDivisorOptimal(int[] nums, int threshold) {
        // Find the max element in nums (upper bound of divisor)
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        // Search space of divisors = [1, max]
        int low = 1, high = max;

        // Standard binary search
        while (low < high) {
            // Mid divisor candidate
            int mid = low + (high - low) / 2;

            // If sum with this divisor is within threshold
            if (sumByD(nums, mid) <= threshold) {
                // It's a valid divisor, but maybe smaller one works too
                high = mid;
            } else {
                // Sum too big, need larger divisor
                low = mid + 1;
            }
        }

        // Low == High → smallest divisor found
        return low;
    }

    // -------- Utility Function --------
    public static int sumByD(int[] arr, int div) {
        int sum = 0;
        for (int num : arr) {
            // Instead of using floating ceil,
            // use math trick: ceil(a/b) = (a + b - 1)/b
            sum += (num + div - 1) / div;
        }
        return sum;
    }

    // -------- Main Method for Testing --------
    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 9};
        int threshold = 6;

        // Expected answer = 5
        System.out.println("Smallest Divisor: " + smallestDivisorOptimal(nums, threshold));
    }
}
