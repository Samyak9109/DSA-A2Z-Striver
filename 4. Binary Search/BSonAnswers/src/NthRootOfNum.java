public class NthRootOfNum {

    /*
       Time Complexity Analysis:
       -------------------------
       - Binary search runs on range [0, m] → O(log m)
       - For each mid, we compute mid^n (loop runs n times) → O(n)
       - So total = O(n * log m)

       Space Complexity Analysis:
       --------------------------
       - O(1) → just using a few variables (low, high, mid, ans)

       TLDR: Time ~ O(n log m), Space ~ O(1)
    */

    // Helper function to check mid^n vs m
    public static int func(int n, int m, int mid) {
        long ans = 1;
        for (int i = 1; i <= n; i++) {  // multiply mid n times
            ans *= mid;

            // Early stopping → saves time if ans grows too big
            if (ans > m) return 2; // mid^n is greater than m
        }
        if (ans == m) return 1;     // perfect root
        return 0;                   // mid^n is smaller than m
    }

    // Main function to find n-th root of m
    static int nthRoot(int n, int m) {
        int low = 1;          // root can't be 0 unless m==0
        int high = m;         // upper bound for search

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // call helper → check mid^n compared to m
            int midN = func(n, m, mid);

            if (midN == 1) {
                return mid;   // found exact root
            } else if (midN == 0) {
                low = mid + 1;  // move right → need bigger root
            } else {
                high = mid - 1; // move left → root too big
            }
        }

        return -1; // no integer root exists
    }

    // Main method for testing
    public static void main(String[] args) {
        System.out.println(nthRoot(3, 27)); // 3
        System.out.println(nthRoot(2, 16)); // 4
        System.out.println(nthRoot(4, 81)); // -1 (no exact 4th root)
    }
}
