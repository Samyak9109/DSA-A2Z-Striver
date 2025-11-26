/**
 * @title Power(x, n) Implementations
 * @description Demonstrates the brute-force, the corrected recursive (better), and the iterative optimal (best) approaches 
 * to calculate x raised to the power of n, using the core concept of Binary Exponentiation for O(log n) efficiency.
 * * LeetCode Equivalent: 50. Pow(x, n)
 * GeeksforGeeks Equivalent: Modular Exponentiation
 * HackerRank Equivalent: Basic Math challenges involving powers.
 */
public class Power {

    // Helper method to safely handle the exponent magnitude
    // We use a final method signature for the optimal approach, as requested by the user's initial structure.
    public double myPow(double x, int n) {
        // We'll call the optimal solution here, which is the industry standard.
        return powerOptimal(x, n);
    }

    // --- Brute-Force Approach ---

    /**
     * @param x The base number.
     * @param n The integer exponent.
     * @return The result of x^n.
     * * Time Complexity: O(|n|)
     * The loop runs |n| times, performing a constant time multiplication in each iteration.
     * * Space Complexity: O(1)
     * Only a few variables are used.
     * Detailed Comments:
     * 1. Check edge cases: If n is 0 or x is 1, the result is 1.
     * 2. Handle negative n: If n is negative, we invert x (x = 1/x) and make n positive, storing it in 'temp' as a long to avoid overflow when n = Integer.MIN_VALUE.
     * 3. Loop and multiply: Iteratively multiply the result 'ans' by the base 'x' for 'temp' number of times. This is the simple, yet slow, definition of exponentiation.
     */
    static double powBrute(double x, int n) {
        if (n == 0) return 1.0;
        if (x == 1.0) return 1.0;

        long temp = n; // Use long to safely hold the exponent magnitude.
        if (n < 0) {
            x = 1.0 / x;
            temp = -1L * n; // Safely negate n, avoiding overflow for Integer.MIN_VALUE.
        }

        double ans = 1.0;

        for (long i = 0; i < temp; i++) {
            ans *= x;
        }
        return ans;
    }

    // --- Corrected Recursive Approach (Better) ---

    /**
     * @param x The base.
     * @param n The non-negative exponent (long, to handle min int edge case).
     * @return x raised to the power of n.
     * * Time Complexity: O(log n)
     * The exponent is effectively halved in every recursive step.
     * * Space Complexity: O(log n)
     * Due to the recursion stack depth.
     * Detailed Comments:
     * 1. Base Case: If n is 0, return 1.0. This stops the recursion.
     * 2. Recursive Step: Calculate the result for n/2 (power(x*x, n/2)). This is the core 'exponentiation by squaring' or Binary Exponentiation step. By squaring the base (x*x) and halving the exponent (n/2), we cut down the work logarithmically.
     * 3. Combine: If n is even, the result is just the squared half-power. If n is odd, we multiply by the original base 'x' once (since n = 2*(n/2) + 1). This is the key fix that prevents StackOverflow and makes the algorithm O(log n).
     */
    static double powerBetter(double x, long n) {
        if (n == 0) return 1.0;

        // This calculates (x^2)^(n/2) and reuses the result.
        double halfPower = powerBetter(x * x, n / 2);

        if (n % 2 == 0) {
            return halfPower; // Even case: (x^2)^(n/2)
        } else {
            return x * halfPower; // Odd case: x * (x^2)^(n/2)
        }
    }

    // --- Optimal Iterative Approach ---

    /**
     * @param x The base number.
     * @param n The integer exponent.
     * @return The result of x^n.
     * * Time Complexity: O(log |n|)
     * The exponent is halved in each loop iteration (O(log n) iterations).
     * * Space Complexity: O(1)
     * No recursion stack or auxiliary data structures are used.
     * Detailed Comments:
     * 1. Pre-Processing: Use a long 'N' for the exponent to safely handle Integer.MIN_VALUE. If n is negative, invert the base (x = 1/x) and make N positive.
     * 2. Initialization: Initialize 'result' to 1.0 (the accumulator).
     * 3. Iterative Binary Exponentiation (The Pipeline Optimization): Loop while N > 0. 
     * a. Check LSB (N & 1): If the current bit is 1 (N is odd), we must include the current power of x into the 'result'.
     * b. Update Base: Square the base x (x = x * x). This means x now represents x^2, then x^4, x^8, etc.
     * c. Shrink Exponent: Right-shift N (N >>= 1), effectively dividing N by 2. This moves to the next bit of the exponent.
     * 4. Return the Final Product: The 'result' variable holds the final value of x^n.
     */
    static double powerOptimal(double x, int n) {
        long N = n;

        if (N < 0) {
            N = -N;
            x = 1.0 / x;
        }

        double result = 1.0;

        while (N > 0) {
            // If the current bit is set (N is odd), multiply the current power of x into the result.
            if ((N & 1) == 1) {
                result *= x;
            }

            // Square the base for the next iteration.
            x *= x;

            // Halve the exponent (move to the next bit).
            N >>= 1;
        }

        return result;
    }

    /**
     * Main method for execution and demonstration.
     */
    public static void main(String[] args) {
        double x = 2.0;
        int n = 10;
        int bigN = Integer.MIN_VALUE;

        System.out.println("--- Demonstrating Power(x, n) Solutions ---");
        System.out.println("Input: x = " + x + ", n = " + n);

        double bruteResult = powBrute(x, n);
        System.out.println("1. Brute-Force (O(n)) Result: " + bruteResult);

        // Need to manually handle the negative call for the better recursive method
        double betterResult = (n < 0) ? 1.0 / powerBetter(x, (long)-n) : powerBetter(x, (long)n);
        System.out.println("2. Better Recursive (O(log n)) Result: " + betterResult);

        double optimalResult = powerOptimal(x, n);
        System.out.println("3. Optimal Iterative (O(log n), O(1) Space) Result: " + optimalResult);

        System.out.println("\n--- Testing Edge Case (Integer.MIN_VALUE) ---");
        double optimalEdge = powerOptimal(x, bigN);
        System.out.println("Input: x = " + x + ", n = " + bigN);
        System.out.println("Optimal Result: " + optimalEdge); // Should be a very small positive number
    }
}