import java.util.HashMap;
import java.util.Map;

public class SubArraySumK {
    // Brute force approach:
    // Check sum of every subarray using 3 nested loops.
    // Time Complexity: O(n^3)
    // Space Complexity: O(1)
    static int subArraySumBrute(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int x = i; x <= j; x++) {
                    sum += nums[x];
                }
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // Better approach:
    // Use 2 nested loops, maintain running sum to avoid repeated summing.
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    static int subArraySumBetter(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // Optimal approach:
    // Use prefix sums with hashmap to store frequencies of prefix sums.
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    static int subArraySumOptimal(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1);

        int count = 0, preSum = 0;
        for (int i = 0; i < n; i++) {
            preSum += nums[i];
            int remove = preSum - k;
            count += prefixSumCount.getOrDefault(remove, 0);
            prefixSumCount.put(preSum, prefixSumCount.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    // Main method to test the three approaches
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, -2, 5};
        int k = 5;

        // Test brute force approach
        System.out.println("Brute force count: " + subArraySumBrute(nums, k));

        // Test better approach
        System.out.println("Better approach count: " + subArraySumBetter(nums, k));

        // Test optimal approach
        System.out.println("Optimal approach count: " + subArraySumOptimal(nums, k));
    }
}
