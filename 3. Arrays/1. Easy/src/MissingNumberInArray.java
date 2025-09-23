public class MissingNumberInArray {

    // -------------------------------------------------------------------
    // Brute Force Approach
    // Approach: Check for each number from 0 to n whether it exists in the array.
    // Time Complexity: O(n^2) – For each number, scan the entire array.
    // Space Complexity: O(1) – No extra space used.
    // -------------------------------------------------------------------
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
        return -1; // Valid input always has a missing number
    }

    // -------------------------------------------------------------------
    // Better Approach 1: Using Hashing
    // Approach: Use a boolean array to mark present numbers.
    // Time Complexity: O(n) – Two passes: one to mark, one to find missing.
    // Space Complexity: O(n) – Extra array of size n+1.
    // -------------------------------------------------------------------
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

    // -------------------------------------------------------------------
    // Better Approach 2: Using Sum Formula
    // Approach: Sum of 0..n minus sum of array elements = missing number.
    // Time Complexity: O(n) – Single pass to sum array elements.
    // Space Complexity: O(1) – Only a few variables used.
    // -------------------------------------------------------------------
    static int missingNumberSum(int[] arr) {
        int n = arr.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;

        for (int num : arr) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }

    // -------------------------------------------------------------------
    // Optimal Approach: Using XOR
    // Approach: XOR all array elements with all numbers from 0..n.
    // XOR cancels out duplicates, leaving the missing number.
    // Time Complexity: O(n) – Single pass through array and range 0..n.
    // Space Complexity: O(1) – Only a few variables used.
    // -------------------------------------------------------------------
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

    // -------------------------------------------------------------------
    // Main method to test all approaches
    // -------------------------------------------------------------------
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 6, 7, 8, 9}; // Missing number is 5

        System.out.println("Brute Force method: Missing Number = " + missingNumberBrute(arr));
        System.out.println("Hashing method:     Missing Number = " + missingNumberHashing(arr));
        System.out.println("Sum Formula method: Missing Number = " + missingNumberSum(arr));
        System.out.println("XOR method:         Missing Number = " + missingNumberXOR(arr));
    }
}
