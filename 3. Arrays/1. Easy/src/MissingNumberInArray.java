public class MissingNumberInArray {

    // 🔴 Brute Force Method
    // Time Complexity: O(n^2)
    // - For each number from 0 to n, scan entire array to check presence.
    // Space Complexity: O(1)
    static int missingNumberBrute(int[] arr) {
        for (int i = 0; i <= arr.length; i++) {
            boolean found = false;
            for (int num : arr) {
                if (num == i) {
                    found = true;
                    break;
                }
            }
            if (!found) return i;
        }
        return -1; // Should not reach here if input is valid
    }

    // 🟡 Better Method 1: Using Hashing
    // Time Complexity: O(n)
    // - One pass to mark presence, one pass to find missing number.
    // Space Complexity: O(n)
    static int missingNumberHashing(int[] arr) {
        int n = arr.length;
        boolean[] hash = new boolean[n + 1];

        for (int num : arr) {
            hash[num] = true;
        }

        for (int i = 0; i <= n; i++) {
            if (!hash[i]) return i;
        }
        return -1;
    }

    // 🟡 Better Method 2: Using Sum Formula
    // Time Complexity: O(n)
    // - Sum of 0 to n minus sum of array elements gives missing number.
    // Space Complexity: O(1)
    static int missingNumberSum(int[] arr) {
        int n = arr.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;

        for (int num : arr) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }

    // 🟢 Optimal Method: Using XOR
    // Time Complexity: O(n)
    // - XOR of full range with XOR of array cancels out all except missing number.
    // Space Complexity: O(1)
    static int missingNumberXOR(int[] arr) {
        int n = arr.length;
        int xorArr = 0, xorFull = 0;

        for (int i = 0; i < n; i++) {
            xorArr ^= arr[i];
            xorFull ^= i;
        }
        xorFull ^= n; // Include n in XOR of full range

        return xorArr ^ xorFull;
    }

    // Main method to test all approaches
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 6, 7, 8, 9}; // Missing 5

        System.out.println("Brute Force method: Missing Number = " + missingNumberBrute(arr));
        System.out.println("Hashing method:     Missing Number = " + missingNumberHashing(arr));
        System.out.println("Sum Formula method: Missing Number = " + missingNumberSum(arr));
        System.out.println("XOR method:         Missing Number = " + missingNumberXOR(arr));
    }
}
