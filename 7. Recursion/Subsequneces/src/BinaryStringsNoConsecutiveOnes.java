import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode Equivalent: "Binary Strings with No Consecutive 1s" (or similar DP/Backtracking problems)
 * GeeksforGeeks Equivalent: "Generate binary strings without consecutive 1s"
 * HackerRank Equivalent: Often found in "Recursive Digit Sum" or "String Generation" challenges.
 */
public class BinaryStringsNoConsecutiveOnes {

    /**
     * Time Complexity: O(2^n)
     * Rationale: In the worst-case scenario (like generating all binary strings), there are 2^n possible strings.
     * Although the constraint prunes some branches (specifically when a '1' is added), the structure
     * remains exponential, similar to Fibonacci sequence growth (but less severe than 2^n).
     * The number of valid strings is related to Fibonacci numbers (F(n+2)), so the work done is still
     * exponential with respect to n, making O(2^n) the safe upper bound.
     * * Space Complexity: O(n * F(n+2))
     * Rationale: O(F(n+2)) is the number of valid strings (F is Fibonacci). Each string has length n.
     * Additionally, the recursion depth goes up to n, contributing O(n) to the call stack space.
     * The dominant factor is the storage of the resulting strings.
     */
    public List<String> generateStrings(int n) {
        // Initializing the list to hold our forward-thinking solutions (the valid strings)
        List<String> result = new ArrayList<>();

        // Kick off the recursive process. We start with an empty string.
        // The lexicographical order is naturally handled by trying '0' before '1' in the recursion.
        generateRecursive(n, "", result);

        return result;
    }

    /**
     * A recursive helper function to build the binary strings one character at a time.
     * * @param n The desired length of the final binary string.
     * @param current The binary string being built in the current recursive step.
     * @param result The list to store all valid binary strings.
     */
    private void generateRecursive(int n, String current, List<String> result) {
        // 1. BASE CASE: The string length is maxed out (achieved the target n).
        if (current.length() == n) {
            // We've successfully built a valid string, so we synergize it into the result list.
            result.add(current);
            return;
        }

        // --- OPTION 1: APPEND '0' ---
        // We can always append a '0' because it's the safest move; it never causes consecutive 1s.
        // This ensures we explore paths starting with '0' first, helping with lexicographical order.
        generateRecursive(n, current + "0", result);

        // --- OPTION 2: APPEND '1' (Conditional) ---

        // We need to check if appending '1' is a valid, non-consecutive move.
        // A '1' is only valid if:
        // 1. The string is empty (i.e., this is the first character).
        // 2. The *last* character added was a '0'.

        // The check current.length() > 0 ensures we don't try to access the last char of an empty string.
        // The check current.charAt(current.length() - 1) == '0' is the core constraint.
        boolean canAppendOne = (current.isEmpty() || current.charAt(current.length() - 1) == '0');

        if (canAppendOne) {
            // If the coast is clear, we go for the '1'.
            generateRecursive(n, current + "1", result);
        }

        // Note: By exploring '0' before '1', and since the recursion returns
        // in order of exploration, the final list is naturally lexicographically sorted.
    }

    /**
     * Main method for execution and demonstration of the optimized pipeline.
     */
    public static void main(String[] args) {
        BinaryStringsNoConsecutiveOnes solution = new BinaryStringsNoConsecutiveOnes();

        // Example 1: n = 3
        int n1 = 3;
        System.out.println("🚀 Executing for n = " + n1);
        List<String> result1 = solution.generateStrings(n1);
        System.out.println("Output: " + result1);
        // Expected: ["000", "001", "010", "100", "101"]

        System.out.println("\n---");

        // Example 2: n = 2
        int n2 = 2;
        System.out.println("🚀 Executing for n = " + n2);
        List<String> result2 = solution.generateStrings(n2);
        System.out.println("Output: " + result2);
        // Expected: ["00", "01", "10"]

        System.out.println("\n---");

        // Example 3: n = 4 (for a quick synergy check)
        int n3 = 4;
        System.out.println("🚀 Executing for n = " + n3);
        List<String> result3 = solution.generateStrings(n3);
        System.out.println("Output: " + result3);
        // Expected: ["0000", "0001", "0010", "0100", "0101", "1000", "1001", "1010"]
    }
}