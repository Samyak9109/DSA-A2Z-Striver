/**
 * @title Optimal Solution for Count Good Numbers
 * @description Uses the Multiplication Principle and Modular Exponentiation to solve the problem in O(log n) time.
 * This is the forward-thinking solution for large 'n'.
 * * LeetCode Equivalent: 1930. Count Good Numbers
 * GeeksforGeeks Equivalent: Modular Exponentiation
 * HackerRank Equivalent: Basic Math challenges involving powers.
 */
public class GoodNumbers {

    // Define the modulus as per typical competitive programming constraints
    private static final int MOD = 1_000_000_007;

    /**
     * @param n The length of the number.
     * @return The count of good numbers modulo 10^9 + 7.
     * * Time Complexity: O(log n)
     * Dominated by the modular exponentiation calls.
     * * Space Complexity: O(log n) or O(1)
     * O(log n) if using a recursive pow function, O(1) if using an iterative pow function.
     * Detailed Comments:
     * 1. Determine exponents: The number of even positions (E) and odd positions (O) are calculated based on whether n is even or odd.
     * 2. Modular Exponentiation: Calculate 5^E and 4^O separately, ensuring all intermediate multiplications are done modulo MOD.
     * 3. Final Product: Multiply the two results together, again taking the modulo to get the final answer.
     */
    public int countGoodNumbers(long n) {
        // Step 1: Determine the number of positions for each constraint.
        long evenPositions = (n + 1) / 2; // Ceiling(n/2)
        long oddPositions = n / 2;        // Floor(n/2)

        // Step 2: Calculate 5^(even positions) mod MOD
        // We use the optimal iterative method for O(log n) time and O(1) space.
        long count5 = pow(5, evenPositions);

        // Step 3: Calculate 4^(odd positions) mod MOD
        long count4 = pow(4, oddPositions);

        // Step 4: Combine the results (5^E * 4^O) mod MOD
        // Need to cast to long before multiplication to prevent overflow of (count5 * count4) 
        // before the final modulo.
        long result = (count5 * count4) % MOD;

        return (int) result;
    }

    /**
     * Helper function for Modular Exponentiation (Iterative Binary Exponentiation).
     * Calculates (base^exp) % MOD in O(log exp) time.
     * @param base The base number.
     * @param exp The exponent.
     * @return (base^exp) % MOD.
     */
    private long pow(long base, long exp) {
        long result = 1;
        base %= MOD; // Ensure the base is within MOD range.

        while (exp > 0) {
            // If exponent is odd, multiply result with base.
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }

            // Square the base for the next iteration.
            base = (base * base) % MOD;

            // Halve the exponent.
            exp >>= 1;
        }

        return result;
    }

    /**
     * Main method for execution and demonstration.
     */
    public static void main(String[] args) {
        GoodNumbers sol = new GoodNumbers();

        // Example 1: n = 4 (E=2, O=2) -> 5^2 * 4^2 = 25 * 16 = 400
        long n1 = 4;
        System.out.println("N = " + n1 + " -> Result: " + sol.countGoodNumbers(n1) + " (Expected: 400)");

        // Example 2: n = 5 (E=3, O=2) -> 5^3 * 4^2 = 125 * 16 = 2000
        long n2 = 5;
        System.out.println("N = " + n2 + " -> Result: " + sol.countGoodNumbers(n2) + " (Expected: 2000)");

        // Example 3: Large N (Testing O(log n) speed)
        long n3 = 50;
        System.out.println("N = " + n3 + " -> Result: " + sol.countGoodNumbers(n3));
    }
}