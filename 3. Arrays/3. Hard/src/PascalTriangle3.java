public class PascalTriangle3 {

    // Brute force method: calculates factorial and then uses it to compute nCr
    // Time Complexity: O(n) for each factorial, called 3 times per element = O(n) per element
    // Space Complexity: O(1) aside from output (single element)
    // Approach: Uses factorial formula nCr = n! / (r! * (n-r)!)
    private static long factorial(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    public static int pascalTriangleBrute(int r, int c) {
        // Compute binomial coefficient using factorials
        long val = factorial(r - 1) / (factorial(c - 1) * factorial(r - c));
        return (int) val;
    }


    // Optimal method: computes nCr iteratively in O(r) time without factorials
    // Time Complexity: O(r)
    // Space Complexity: O(1)
    // Approach: Uses the relation nCr = (n * (n-1) * ... ) / (r * (r-1)... )
    public static long nCrOptimal(int n, int r) {
        long res = 1;
        if (r > n - r) r = n - r; // Take advantage of symmetry
        for (int i = 0; i < r; i++) {
            res = res * (n - i);
            res = res / (i + 1);
        }
        return res;
    }

    public static int pascalTriangleOptimal(int r, int c) {
        // Calculate element at (r, c) using optimized nCr
        long val = nCrOptimal(r - 1, c - 1);
        return (int) val;
    }


    // Main method demonstrating both approaches
    public static void main(String[] args) {
        int r = 5; // 1-based row number
        int c = 3; // 1-based column number

        int elemBrute = pascalTriangleBrute(r, c);
        System.out.println("Brute force element at (" + r + "," + c + "): " + elemBrute);

        int elemOpt = pascalTriangleOptimal(r, c);
        System.out.println("Optimal element at (" + r + "," + c + "): " + elemOpt);
    }
}
