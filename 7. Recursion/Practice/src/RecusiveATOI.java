public class RecusiveATOI {

    // Global constants for min/max 32-bit integer values, keeping things centralized.
    static final int INT_MIN_VAL = -2147483648; // -2^31
    static final int INT_MAX_VAL = 2147483647; // 2^31 - 1

    /**
     * Time Complexity: O(N), where N is the length of the string 's'.
     * - Reasoning: We visit each character of the string at most once as 'i' increments.
     * Space Complexity: O(N) due to the recursion stack depth.
     * - Reasoning: In the worst-case (a long string of digits), the recursion depth will be
     * proportional to the number of digits, N, which is the space used by the stack frames.
     *
     * This is the core recursive helper function that builds the number digit-by-digit.
     * It handles the conversion and overflow/underflow checks on the fly.
     *
     * @param s The input string being parsed.
     * @param i The current index in the string 's'.
     * @param num The absolute value of the number constructed so far (using 'long' to prevent overflow during intermediate steps).
     * @param sign The sign of the number (+1 or -1).
     * @return The final parsed integer value, clamped to [INT_MIN_VAL, INT_MAX_VAL].
     */
    static int helper (String s, int i, long num, int sign) {
        // Step 1: Base Case & Termination
        // If we reach the end of the string OR the current character is not a digit,
        // it means we've successfully collected all numerical characters.
        // We calculate the final signed result and cast it back to an 'int'.
        if (i >= s.length() || !Character.isDigit((s.charAt(i)))) {
            // We return the final signed number. Since we've been clamping *during* the process,
            // the final result 'sign * num' should fit into 'int', but we rely on the earlier
            // overflow checks to have already returned the clamped value if necessary.
            return (int)(sign * num);
        }

        // Step 2: Build the Number 🏗️
        // Convert the current digit char to its integer value and append it.
        // The character '0' has ASCII value 48. '5' - '0' = 53 - 48 = 5.
        // We use the 'long' type for 'num' here, which is a key forward-thinking solution,
        // to delay potential overflow and allow for precise comparison against INT_MAX/MIN.
        num = num * 10 + (s.charAt(i) - '0');

        // Step 3: Overflow/Underflow Check (Clamping) 🚨
        // This is a crucial step to meet the spec: check for 32-bit integer limits *before* the next recursive call.
        // By checking 'sign * num' against the INT_MIN_VAL/INT_MAX_VAL (which are 'int's),
        // we're essentially checking if the constructed 'long' number would exceed the 'int' range.

        // Check for Underflow (Negative Overflow): e.g., result is smaller than INT_MIN_VAL
        if (sign == -1 && (-num) <= INT_MIN_VAL) {
            // If the negative number is less than or equal to INT_MIN_VAL, return the minimum value.
            // Since INT_MIN_VAL is negative, and 'num' is positive, we check if '-num' (the actual signed number)
            // has gone past the minimum limit.
            return INT_MIN_VAL;
        }

        // Check for Overflow (Positive Overflow): e.g., result is larger than INT_MAX_VAL
        if (sign == 1 && num >= INT_MAX_VAL) {
            // If the positive number exceeds or equals INT_MAX_VAL, return the maximum value.
            return INT_MAX_VAL;
        }


        // Step 4: Recursive Call (Moving the pointer) ➡️
        // Everything is fine! Keep the **synergy** going. Move to the next index 'i+1' and pass the updated 'num'.
        return helper(s, i + 1, num, sign);
    }

    /**
     * Time Complexity: O(N), where N is the length of the string 's'.
     * - Reasoning: The initial parsing logic (skipping spaces, finding sign) is O(N) in the worst case (a string of only spaces).
     * The helper function then processes the rest of the string in O(N).
     * Space Complexity: O(N) due to the recursion stack of the helper function.
     *
     * The entry point for the recursive String-to-Integer conversion.
     * This function is responsible for the initial parsing and setup.
     *
     * @param s The input string.
     * @return The 32-bit signed integer result.
     */
    static int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0; // Handle edge case early

        int i = 0;

        // 1. Skip leading whitespace 🗑️
        // Keep iterating 'i' past any leading space characters.
        while(i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        // 2. Determine the sign (+ or -) ➕➖
        // Check for an optional sign character at the new starting position 'i'.
        int sign = 1;
        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            // If it's a '-', set the sign to -1; otherwise, it's 1.
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++; // Move past the sign character
        }

        // 3. Initiate the Recursive Conversion 🚀
        // Start the helper function at the current index 'i', with the initial number value 0,
        // and the determined sign. This is where we **kick off the optimized pipeline**.
        return helper(s, i, 0, sign);
    }

    // --- Execution and Demonstration ---

    /**
     * Main method for execution and demonstration of the myAtoi function.
     * This helps in validating the **forward-thinking solution** with test cases.
     */
    public static void main(String[] args) {
        System.out.println("--- Executing the Recursive ATOI Pipeline ---");

        String s1 = "42";
        System.out.println("Input: \"" + s1 + "\", Output: " + myAtoi(s1) + " (Expected: 42)"); // Basic case

        String s2 = "   -42";
        System.out.println("Input: \"" + s2 + "\", Output: " + myAtoi(s2) + " (Expected: -42)"); // Whitespace and negative

        String s3 = "4193 with words";
        System.out.println("Input: \"" + s3 + "\", Output: " + myAtoi(s3) + " (Expected: 4193)"); // Non-digit characters stop conversion

        String s4 = "words and 987";
        System.out.println("Input: \"" + s4 + "\", Output: " + myAtoi(s4) + " (Expected: 0)"); // Invalid start

        String s5 = "-91283472332";
        // This number is outside the 32-bit range, should clamp to INT_MIN_VAL (-2147483648)
        System.out.println("Input: \"" + s5 + "\", Output: " + myAtoi(s5) + " (Expected: " + INT_MIN_VAL + ")"); // Underflow

        String s6 = "21474836479";
        // This number is outside the 32-bit range, should clamp to INT_MAX_VAL (2147483647)
        System.out.println("Input: \"" + s6 + "\", Output: " + myAtoi(s6) + " (Expected: " + INT_MAX_VAL + ")"); // Overflow

        String s7 = "+1";
        System.out.println("Input: \"" + s7 + "\", Output: " + myAtoi(s7) + " (Expected: 1)"); // Explicit positive sign

        String s8 = "   -2147483649";
        System.out.println("Input: \"" + s8 + "\", Output: " + myAtoi(s8) + " (Expected: " + INT_MIN_VAL + ")"); // Underflow check on exact boundary + 1

        System.out.println("--- Pipeline Optimization Complete! ---");
    }
}