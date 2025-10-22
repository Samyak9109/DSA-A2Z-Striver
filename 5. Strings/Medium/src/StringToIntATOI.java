/**
 * 🧩 Problem: String to Integer (atoi)
 * LeetCode #8
 * GeeksforGeeks Equivalent: "Implement atoi()"
 * HackerRank Equivalent: "String to Integer Conversion"
 */

public class StringToIntATOI {

    // ==========================================================
    // 🥉 Brute Force Approach — Using Try-Catch (for learning sake)
    // ==========================================================
    /**
     * Time Complexity: O(1) in best case (library call)
     * Space Complexity: O(1)
     * ⚠️ Not allowed on most interview platforms because it’s too easy.
     */
    static int myAtoiBrute(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            // Return 0 for invalid strings — matching problem statement
            return 0;
        }
    }

    // ==========================================================
    // 🥈 Better Approach — Manual Parsing using Conditions
    // ==========================================================
    /**
     * Time Complexity: O(n), because we traverse the string once.
     * Space Complexity: O(1), because we use only a few integer variables.
     * ---------------------------------------------------------------
     * Idea:
     * 1️⃣ Skip leading spaces.
     * 2️⃣ Handle optional + or - sign.
     * 3️⃣ Process digits until a non-digit appears.
     * 4️⃣ Handle overflow/underflow manually.
     */
    static int myAtoiBetter(String s) {
        int i = 0, sign = 1;
        long res = 0; // using long to safely detect overflow

        // 1️⃣ Skip leading whitespaces
        while (i < s.length() && s.charAt(i) == ' ') i++;

        // 2️⃣ Handle empty string (only spaces)
        if (i == s.length()) return 0;

        // 3️⃣ Handle optional sign
        if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (s.charAt(i) == '+') {
            i++;
        }

        // 4️⃣ Parse digits
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            // Multiply previous result by 10, add current digit
            res = res * 10 + (s.charAt(i) - '0');

            // Overflow Check: if sign * res exceeds range
            if (sign * res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign * res < Integer.MIN_VALUE) return Integer.MIN_VALUE;

            i++;
        }

        return (int) (sign * res);
    }

    // ==========================================================
    // 🥇 Optimal Approach — Compact, Readable, and Robust
    // ==========================================================
    /**
     * Time Complexity: O(n) — linear scan through string
     * Space Complexity: O(1)
     * ---------------------------------------------------------------
     * We follow the same logic as above, but with cleaner flow and
     * ternary operator usage for more readable, condensed expressions.
     * ---------------------------------------------------------------
     * Example:
     * sign = (s.charAt(i) == '-') ? -1 : 1;
     * means: if char is '-', set sign = -1; else sign = 1.
     * Ternary is basically shorthand for an if-else expression.
     */
    static int myAtoiOptimal(String s) {
        int i = 0, sign = 1;
        long result = 0;

        // Step 1: Skip leading spaces
        while (i < s.length() && s.charAt(i) == ' ') i++;

        // Step 2: Return 0 if empty or only spaces
        if (i == s.length()) return 0;

        // Step 3: Handle sign using ternary for elegance ✨
        // Equivalent to:
        // if (s.charAt(i) == '-') sign = -1;
        // else if (s.charAt(i) == '+') sign = 1;
        // else sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1; // ternary usage
            i++;
        }

        // Step 4: Parse digits
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            result = result * 10 + digit;

            // Step 5: Clamp values within integer bounds
            if (sign * result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign * result < Integer.MIN_VALUE) return Integer.MIN_VALUE;

            i++;
        }

        // Step 6: Apply sign and return
        return (int) (sign * result);
    }

    // ==========================================================
    // 🧪 Main Method — Demo Testing
    // ==========================================================
    public static void main(String[] args) {
        String[] testCases = {
                "42",
                "   -42",
                "4193 with words",
                "words and 987",
                "-91283472332",
                "+123",
                "00000123"
        };

        System.out.println("🚀 Testing String to Integer (atoi) Implementations:\n");

        for (String s : testCases) {
            System.out.println("Input: \"" + s + "\"");
            System.out.println("Brute: " + myAtoiBrute(s));
            System.out.println("Better: " + myAtoiBetter(s));
            System.out.println("Optimal: " + myAtoiOptimal(s));
            System.out.println("--------------------------------------");
        }
    }
}
