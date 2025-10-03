public class KthMissingPosiNum {

    /*
       ================================
       🕒 Time & 🗂️ Space Complexities
       ================================

       Brute Force:
         - Time: O(n + k)
           -> Simulate from 1 upward until we find the k-th missing.
         - Space: O(1).

       Better (Linear Scan):
         - Time: O(n)
           -> Walk through arr[], track missing count till each index.
         - Space: O(1).

       Optimal (Binary Search):
         - Time: O(log n)
           -> Binary search on index, each step O(1).
         - Space: O(1).

       🔑 Key Idea:
       - Count how many numbers are "missing" at any index mid:
         missing(mid) = arr[mid] - (mid + 1).
       - Use binary search to find smallest index where missing(mid) >= k.
       - Final formula for answer = low + k.
    */

    // ================================
    // 🚨 Brute Force (simulate counting missing numbers)
    // ================================
    static int findKthPositiveBrute(int[] arr, int k) {
        int num = 1, i = 0;

        while (k > 0) {
            if (i < arr.length && arr[i] == num) {
                i++; // number exists in arr, skip
            } else {
                k--; // this number is missing
                if (k == 0) return num;
            }
            num++;
        }
        return -1; // shouldn't happen
    }

    // ================================
    // ⚡ Better Approach (linear scan through array)
    // ================================
    static int findKthPositiveBetter(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            int missing = arr[i] - (i + 1); // numbers missing up to arr[i]

            if (missing >= k) {
                // Found the spot → answer is just k + i
                return k + i;
            }
        }
        // If we never hit "missing >= k", the answer lies after arr[n-1]
        return k + arr.length;
    }

    // ================================
    // 🚀 Optimal Approach (Binary Search)
    // ================================
    static int findKthPositive(int[] arr, int k) {
        int n = arr.length;
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            int missing = arr[mid] - (mid + 1);
            // 🔑 how many numbers are missing before arr[mid]

            if (missing < k) {
                low = mid + 1; // not enough missing yet → go right
            } else {
                high = mid - 1; // too many missing → go left
            }
        }

        /*
           ================================
           💡 Formula Derivation
           ================================

           At the end of binary search:
           - high = last index where missing < k
           - low = high + 1

           Case 1: Answer lies after arr[high]
             ans = arr[high] + (k - missing)
             where missing = arr[high] - (high + 1)

             expand:
             ans = arr[high] + k - (arr[high] - (high + 1))
                 = arr[high] + k - arr[high] + high + 1
                 = high + 1 + k

           Since low = high + 1:
             ans = low + k ✅

           Case 2: Answer lies inside the array range
             Binary search has already placed low exactly
             where the k-th missing number would fall.
             Same formula ans = low + k works ✅
        */

        return low + k;
    }

    // ================================
    // 🚀 Main Method with Example
    // ================================
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 7, 11};
        int k = 5;

        System.out.println("Brute Force: " + findKthPositiveBrute(arr, k));
        System.out.println("Better: " + findKthPositiveBetter(arr, k));
        System.out.println("Optimal (Binary Search): " + findKthPositive(arr, k));

        /*
          Dry Run Example: arr = [2,3,4,7,11], k=5

          missing(0) = 2 - 1 = 1
          missing(1) = 3 - 2 = 1
          missing(2) = 4 - 3 = 1
          missing(3) = 7 - 4 = 3
          missing(4) = 11 - 5 = 6

          We want the 5th missing.
          Binary search ends with:
            low = 5, high = 4
          Answer = low + k = 5 + 5 = 10 ✅
        */
    }
}
