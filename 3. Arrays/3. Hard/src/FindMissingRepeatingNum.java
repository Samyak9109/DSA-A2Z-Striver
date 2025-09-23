public class FindMissingRepeatingNum {

    // ---------------------------------------------------
    // BRUTE FORCE APPROACH
    // ---------------------------------------------------
    // Idea:
    // - For every number from 1..n, count its frequency in the array.
    // - If count == 2 → repeating number
    // - If count == 0 → missing number
    //
    // Time Complexity: O(n^2)
    //   -> For each of n numbers, scan entire array O(n).
    // Space Complexity: O(1)
    //   -> No extra data structure, only variables.
    public static int[] findMissingRepeatingNumbersBrute(int[] arr) {
        int n = arr.length;
        int repeating = -1, missing = -1;

        // Check frequency of each number from 1 to n
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (arr[j] == i) cnt++;
            }
            // If found twice -> repeating
            if (cnt == 2) repeating = i;
                // If not found -> missing
            else if (cnt == 0) missing = i;

            if (repeating != -1 && missing != -1) break;
        }
        return new int[]{repeating, missing};
    }


    // ---------------------------------------------------
    // BETTER APPROACH (HASHING)
    // ---------------------------------------------------
    // Idea:
    // - Create a frequency array (size n+1).
    // - Traverse array and increase count for each number.
    // - Missing = number with frequency 0
    // - Repeating = number with frequency 2
    //
    // Time Complexity: O(n)
    //   -> One pass to fill hash, one pass to check frequencies.
    // Space Complexity: O(n)
    //   -> Extra array 'hash' of size n+1.
    public static int[] findMissingRepeatingNumbersBetter(int[] arr) {
        int n = arr.length;
        int[] hash = new int[n + 1]; // frequency array

        // Count frequency of each number
        for (int i = 0; i < n; i++) {
            hash[arr[i]]++;
        }

        int repeating = -1, missing = -1;
        // Scan the hash array to find repeating & missing
        for (int i = 1; i <= n; i++) {
            if (hash[i] == 2) repeating = i;
            else if (hash[i] == 0) missing = i;

            if (repeating != -1 && missing != -1) break;
        }
        return new int[]{repeating, missing};
    }


    // ---------------------------------------------------
    // OPTIMAL APPROACH (MATH FORMULAS)
    // ---------------------------------------------------
    // Idea:
    // - Let X = repeating number, Y = missing number.
    // - Expected sum of 1..n = n(n+1)/2  (SN)
    // - Expected sum of squares of 1..n = n(n+1)(2n+1)/6  (S2N)
    // - Actual sum of array = S
    // - Actual sum of squares of array = S2
    // - From equations:
    //     (X - Y) = S - SN
    //     (X^2 - Y^2) = S2 - S2N
    //     => (X + Y) = (X^2 - Y^2) / (X - Y)
    // - Solve for X and Y.
    //
    // Time Complexity: O(n)
    //   -> Single traversal to compute sum & sum of squares.
    // Space Complexity: O(1)
    //   -> Only a few variables used.
    public static int[] findMissingRepeatingNumbersOptimal1(int[] arr) {
        long n = arr.length;

        // Expected sum of 1..n
        long SN = (n * (n + 1)) / 2;
        // Expected sum of squares of 1..n
        long S2N = (n * (n + 1) * (2 * n + 1)) / 6;

        // Actual sum and sum of squares from array
        long S = 0, S2 = 0;
        for (int i = 0; i < n; i++) {
            S += arr[i];
            S2 += (long) arr[i] * (long) arr[i];
        }

        // Equation results
        long val1 = S - SN;      // (X - Y)
        long val2 = S2 - S2N;    // (X^2 - Y^2)

        long val3 = val2 / val1; // (X + Y)

        // Solve equations:
        long x = (val1 + val3) / 2; // repeating number
        long y = x - val1;          // missing number

        return new int[]{(int) x, (int) y};
    }


    // ---------------------------------------------------
    // MAIN METHOD FOR TESTING
    // ---------------------------------------------------
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 5, 3}; // Example input

        int[] ans1 = findMissingRepeatingNumbersBrute(arr);
        System.out.println("Brute: Repeating = " + ans1[0] + ", Missing = " + ans1[1]);

        int[] ans2 = findMissingRepeatingNumbersBetter(arr);
        System.out.println("Better: Repeating = " + ans2[0] + ", Missing = " + ans2[1]);

        int[] ans3 = findMissingRepeatingNumbersOptimal1(arr);
        System.out.println("Optimal: Repeating = " + ans3[0] + ", Missing = " + ans3[1]);
    }
}
