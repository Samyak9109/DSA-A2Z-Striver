public class SquareRootBinary {

    /*
      Time Complexity:
        - O(log n) --> because we keep halving the search space.
      Space Complexity:
        - O(1) --> we only use a few variables.
    */

    // Function to return the floor of sqrt(n)
    static int sqrt(int n) {
        if (n < 2) return n; // Edge case for 0 and 1

        int low = 0, high = n, ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Avoid overflow: use long for mid*mid
            long val = (long) mid * mid;

            if (val == n) {
                return mid; // Perfect square
            } else if (val < n) {
                ans = mid;   // store potential answer
                low = mid + 1; // search right
            } else {
                high = mid - 1; // search left
            }
        }
        return ans; // floor of sqrt(n)
    }

    // Main to test
    public static void main(String[] args) {
        System.out.println(sqrt(0));   // 0
        System.out.println(sqrt(1));   // 1
        System.out.println(sqrt(4));   // 2
        System.out.println(sqrt(8));   // 2 (floor of 2.82...)
        System.out.println(sqrt(16));  // 4
        System.out.println(sqrt(25));  // 5
        System.out.println(sqrt(26));  // 5
    }
}
