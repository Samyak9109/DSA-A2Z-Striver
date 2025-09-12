import java.util.HashMap;

public class LargestSubarraySumZero {

    /**
     * Brute Force Approach
     * -------------------
     * Time Complexity: O(n^2)
     *    - Uses two nested loops to check all subarrays and compute their sums.
     * Space Complexity: O(1)
     *    - No extra space used, only variables for sum and max length.
     */
    public int maxLenghtBrute(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; ++i) {
            int sum = 0;
            for (int j = i; j < arr.length; ++j) {
                sum += arr[j];
                if (sum == 0) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    /**
     * Optimal Approach (Using HashMap)
     * --------------------------------
     * Time Complexity: O(n)
     *    - Single pass through array. Each element is inserted into or looked up in the HashMap in O(1) average time.
     * Space Complexity: O(n)
     *    - Stores prefix sums in HashMap which can be up to the size of the array.
     *
     * Approach:
     *    - Maintain a running sum. If the sum repeats, it means the subarray between the previous index (where
     *      the sum was seen) and the current index sums to zero.
     *    - If sum is zero at any index, longest subarray seen so far is from index 0 to current.
     */
    public int maxLenghtOptimal(int[] arr) {
        HashMap<Integer, Integer> mpp = new HashMap<>();
        int n = arr.length;
        int maxi = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (sum == 0) {
                maxi = i + 1;
            } else {
                if (mpp.get(sum) != null) {
                    maxi = Math.max(maxi, i - mpp.get(sum));
                } else {
                    mpp.put(sum, i);
                }
            }
        }
        return maxi;
    }

    // Main method for demonstration
    public static void main(String[] args) {
        LargestSubarraySumZero solver = new LargestSubarraySumZero();
        int[] arr = {15, -2, 2, -8, 1, 7, 10, 23};

        System.out.println("Brute Force Output: " + solver.maxLenghtBrute(arr));
        System.out.println("Optimal Output: " + solver.maxLenghtOptimal(arr));
    }
}
