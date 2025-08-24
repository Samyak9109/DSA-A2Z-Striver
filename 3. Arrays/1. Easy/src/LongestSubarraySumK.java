import java.util.HashMap;
import java.util.Map;

public class LongestSubarraySumK {

    // -------------------------------------------------------------------
    // Brute Force Approach:
    // Time Complexity: O(N^3)
    // - Three nested loops: i, j for subarray boundaries and l for summing.
    // Space Complexity: O(1)
    // - Only a few variables, no extra data structures.
    // -------------------------------------------------------------------
    static int getLongestSubarrayBrute(int[] arr, int k) {
        int n = arr.length;
        int len = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int l = i; l <= j; l++) {
                    sum += arr[l]; // sum all elements in subarray [i..j]
                }
                if (sum == k) {
                    len = Math.max(len, j - i + 1);
                }
            }
        }
        return len;
    }

    // -------------------------------------------------------------------
    // Better Approach:
    // Time Complexity: O(N^2)
    // - Two nested loops: i and j. sum is accumulated incrementally.
    // Space Complexity: O(1)
    // - No extra space used.
    // -------------------------------------------------------------------
    static int getLongestSubarrayBetter(int[] arr, int k) {
        int n = arr.length;
        int len = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j]; // add current element to sum
                if (sum == k) {
                    len = Math.max(len, j - i + 1);
                }
            }
        }
        return len;
    }

    // -------------------------------------------------------------------
    // Optimal Approach using Prefix Sum and HashMap:
    // Time Complexity: O(N)
    // - Single pass through array; hashmap operations O(1) on average.
    // Space Complexity: O(N)
    // - HashMap stores prefix sums and their earliest indices.
    // -------------------------------------------------------------------
    static int getLongestSubarrayOptimal(int[] arr, int k) {
        int n = arr.length;
        Map<Integer, Integer> preSumMap = new HashMap<>();
        int sum = 0;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i]; // running prefix sum

            if (sum == k) {
                maxLen = i + 1; // entire prefix matches sum k
            }

            int rem = sum - k;
            if (preSumMap.containsKey(rem)) {
                int len = i - preSumMap.get(rem); // length of subarray summing to k
                maxLen = Math.max(maxLen, len);
            }

            // store earliest occurrence of prefix sum
            preSumMap.putIfAbsent(sum, i);
        }
        return maxLen;
    }

    // -------------------------------------------------------------------
    // Optimal Approach for Non-negative elements using Sliding Window (2 pointers):
    // Time Complexity: O(N)
    // - Each element visited at most twice.
    // Space Complexity: O(1)
    // - No extra space used.
    // Note: Valid only when array elements are non-negative.
    // -------------------------------------------------------------------
    public static int getLongestSubarrayOptimal2(int[] a, long k) {
        int n = a.length;
        int left = 0, right = 0;
        long sum = a.length > 0 ? a[0] : 0; // handle empty array edge case
        int maxLen = 0;

        while (right < n) {
            // shrink window if sum exceeds k
            while (left <= right && sum > k) {
                sum -= a[left];
                left++;
            }

            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            right++;
            if (right < n) {
                sum += a[right];
            }
        }

        return maxLen;
    }

    // -------------------------------------------------------------------
    // Main method to test all approaches
    // -------------------------------------------------------------------
    public static void main(String[] args) {
        int[] a = {-1, 1, 1};
        int k = 1;

        System.out.println("Input Array: {-1, 1, 1}, Target Sum = 1");

        int bruteLen = getLongestSubarrayBrute(a, k);
        System.out.println("Brute Force -> Longest subarray length: " + bruteLen);

        int betterLen = getLongestSubarrayBetter(a, k);
        System.out.println("Better Approach -> Longest subarray length: " + betterLen);

        int optimalLen = getLongestSubarrayOptimal(a, k);
        System.out.println("Optimal Approach (HashMap) -> Longest subarray length: " + optimalLen);

        int optimal2Len = getLongestSubarrayOptimal2(a, k); // Only valid if all elements are non-negative
        System.out.println("Optimal Approach 2 (2 pointers) -> Longest subarray length: " + optimal2Len);
    }
}
