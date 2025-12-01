import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode Equivalent: 22. Generate Parentheses
 * GeeksforGeeks Equivalent: Print all combinations of balanced parentheses
 * HackerRank Equivalent: Recursion and Backtracking challenges.
 */
public class GenerateParanthesis {

    // --- Brute Force Approach ---

    /**
     * Time Complexity (Brute Force): O(2^(2n) * n)
     * Rationale: We generate 2^(2n) possible strings of length 2n. For each string,
     * we perform an O(n) check in the isValid function. Total time is the product.
     * * Space Complexity (Brute Force): O(2^(2n) * n)
     * Rationale: We store 2^(2n) strings, each of length n. The recursion depth is 2n.
     */
    public List<String> genParanthesisBrute(int n) {
        // We're initializing the list to capture all the potential solutions.
        List<String> result = new ArrayList<>();
        // Start generating all possible binary-like strings (using '(' and ')')
        generateAll("", n, result);
        return result;
    }

    /**
     * Recursively generates all binary strings of length 2*n, then validates them.
     * * @param curr The current string being built.
     * @param n The number of pairs needed.
     * @param res The list to store valid strings.
     */
    private void generateAll(String curr, int n, List<String> res) {
        // 1. BASE CASE: If the length hits 2*n, we've built a full candidate string.
        if (curr.length() == 2 * n) {
            // Now, we validate it. This is the post-processing step that's slow.
            if (isValid(curr)) {
                res.add(curr);
            }
            return;
        }

        // Always try adding '('
        generateAll(curr + "(", n, res);
        // Always try adding ')'
        generateAll(curr + ")", n, res);
    }

    /**
     * Checks if a fully generated string is a valid, balanced parenthesis sequence.
     * * @param s The candidate string.
     */
    private boolean isValid(String s) {
        int balance = 0;
        // Iterate through the string, maintaining the balance counter.
        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            // If the balance ever drops below 0, it means we closed a parenthesis
            // that wasn't open yet. This is an invalid sequence, so we can immediately stop.
            if (balance < 0) {
                return false;
            }
        }
        // For a valid sequence, the final balance must be exactly 0 (all opened ones were closed).
        return balance == 0;
    }

    // --- Optimal Backtracking Approach (Forward-Thinking Solution) ---

    /**
     * Time Complexity (Optimal): O(4^n / sqrt(n)) - The number of valid strings (Catalan Number C_n).
     * Rationale: The algorithm only explores paths that lead to a valid solution. We avoid the
     * O(n) validation check on every leaf node, making the work proportional only to the output size.
     * * Space Complexity (Optimal): O(n * C_n)
     * Rationale: We store C_n strings of length n. O(n) for the recursion stack depth.
     */
    public List<String> generateParenthesis(int n) {
        // Let's synergize our results into a list!
        List<String> result = new ArrayList<>();
        // We kick off the smart, forward-thinking recursive process.
        backtrack(result, "", 0, 0, n);
        return result;
    }

    /**
     * The core recursive function to build the balanced parentheses strings by pruning invalid branches early.
     * * @param result The list to synergize our valid strings into.
     * @param current The current string being built.
     * @param openUsed The count of '(' used so far.
     * @param closeUsed The count of ')' used so far.
     * @param maxN The maximum number of pairs (n).
     */
    private void backtrack(List<String> result, String current, int openUsed, int closeUsed, int maxN) {
        // 1. BASE CASE: We've achieved the target length (2*n). Since we already enforced
        // the balance rules in the recursive calls, this string MUST be valid.
        if (current.length() == 2 * maxN) {
            result.add(current);
            return;
        }

        // --- OPTION 1: ADD OPEN PARENTHESIS '(' ---
        // We can add '(' as long as we haven't used all 'n' of them.
        if (openUsed < maxN) {
            // Recursive call: The new state has one more open parenthesis used.
            backtrack(result, current + "(", openUsed + 1, closeUsed, maxN);
        }

        // --- OPTION 2: ADD CLOSE PARENTHESIS ')' ---
        // This is the **key optimization**: We ONLY add ')' if we have a matching,
        // currently open '(' available. i.e., closeUsed must be LESS than openUsed.
        if (closeUsed < openUsed) {
            // Recursive call: The new state has one more close parenthesis used.
            backtrack(result, current + ")", openUsed, closeUsed + 1, maxN);
        }
        // By using these constraints, we avoid generating 99% of the invalid strings!
    }

    /**
     * Main method for execution and demonstration of both approaches.
     */
    public static void main(String[] args) {
        GenerateParanthesis solution = new GenerateParanthesis();
        int n = 3;

        System.out.println("--- 📊 Comparing Approaches for n = " + n + " ---");

        // 1. Brute Force Demonstration
        System.out.println("\n🐌 BRUTE FORCE: Generating all and validating (O(2^(2n) * n))");
        List<String> bruteResult = solution.genParanthesisBrute(n);
        System.out.println("Total Strings Generated (and checked): " + (int)Math.pow(2, 2*n)); // 2^(2*3) = 64
        System.out.println("Valid Output: " + bruteResult);

        System.out.println("\n--------------------------------------------------");

        // 2. Optimal Backtracking Demonstration
        System.out.println("🚀 OPTIMAL Backtracking: Pruning the search tree (O(C_n))");
        List<String> optimalResult = solution.generateParenthesis(n);
        System.out.println("Total Valid Strings Found: " + optimalResult.size()); // Catalan(3) = 5
        System.out.println("Valid Output: " + optimalResult);
        // Expected: ["((()))", "(()())", "(())()", "()(())", "()()()"]
    }
}