import java.util.HashMap;
import java.util.Map;

public class LongestSubarraySumK {

    // -------------------------------------------------------------------
    // Approach 1: Brute Force
    // Description:
    // Check all subarrays by three nested loops and calculate sum.
    // Time Complexity: O(N^3) – three nested loops
    // Space Complexity: O(1) – only uses variables
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
    // Approach 2: Better (Incremental Sum)
    // Description:
    // Compute sum incrementally in two nested loops, avoiding third loop.
    // Time Complexity: O(N^2)
    // Space Complexity: O(1)
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
    // Approach 3: Optimal using Prefix Sum + HashMap
    // Description:
    // Use hashmap to store first occurrence of prefix sums.
    // If (prefixSum - k) exists, a subarray with sum k is found.
    // Works with negative numbers as well.
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    // -------------------------------------------------------------------
    static int getLongestSubarrayOptimal(int[] arr, int k) {
        int n = arr.length;
        Map<Integer, Integer> preSumMap = new HashMap<>();
        int sum = 0;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i]; // running prefix sum

            if (sum == k) {
                maxLen = i + 1; // subarray from start
            }

            int rem = sum - k;
            if (preSumMap.containsKey(rem)) {
                int len = i - preSumMap.get(rem); // length of subarray
                maxLen = Math.max(maxLen, len);
            }

            preSumMap.putIfAbsent(sum, i); // store earliest occurrence
        }
        return maxLen;
    }

    // -------------------------------------------------------------------
    // Approach 4: Optimal for Non-negative Numbers using Sliding Window
    // Description:
    // Expand window to the right; shrink from left if sum exceeds k.
    // Only valid for non-negative numbers.
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    // -------------------------------------------------------------------
    public static int getLongestSubarrayOptimal2(int[] a, long k) {
        int n = a.length;
        int left = 0, right = 0;
        long sum = n > 0 ? a[0] : 0;
        int maxLen = 0;

        while (right < n) {
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

        System.out.println("Brute Force -> Longest subarray length: " + getLongestSubarrayBrute(a, k));
        System.out.println("Better Approach -> Longest subarray length: " + getLongestSubarrayBetter(a, k));
        System.out.println("Optimal (HashMap) -> Longest subarray length: " + getLongestSubarrayOptimal(a, k));
        System.out.println("Optimal 2 (Sliding Window, non-negative) -> Longest subarray length: "
                + getLongestSubarrayOptimal2(a, k));
    }
}
