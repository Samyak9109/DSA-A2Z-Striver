public class LongestPalindromicSubstring {

    // -------------------------------
    // OPTIMAL APPROACH: Expand Around Center
    // -------------------------------
    // Idea: Every palindrome has a center. It can be a single character (odd length)
    // or two characters (even length). We expand from every possible center.
    static String expandCenter(String s) {
        int start = 0; // Start index of the longest palindrome found
        int end = 0;   // End index of the longest palindrome found

        // Loop over every character in the string as a potential center
        for (int center = 0; center < s.length(); center++) {
            // Odd-length palindrome: center is one character
            int lenOdd = expandFromCenter(s, center, center);

            // Even-length palindrome: center is two characters
            int lenEven = expandFromCenter(s, center, center + 1);

            // Take the longer of the two lengths
            int maxLen = Math.max(lenOdd, lenEven);

            // Update start and end indices if we found a longer palindrome
            if (maxLen > end - start) {
                // start formula: move left from center
                start = center - (maxLen - 1) / 2;

                // end formula: move right from center
                end = center + maxLen / 2;
            }
        }

        // Return the substring from start to end (inclusive)
        return s.substring(start, end + 1);
    }

    // -------------------------------
    // Helper function to expand around a center
    // -------------------------------
    static int expandFromCenter(String str, int left, int right) {
        // Keep expanding as long as we don't go out of bounds
        // and the characters on both sides are equal
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;   // move left pointer to the left
            right++;  // move right pointer to the right
        }

        // Length of palindrome = right - left - 1
        // (we subtract 1 because we expanded one step too far on both sides)
        return right - left - 1;
    }

    // -------------------------------
    // Main method to demo the function
    // -------------------------------
    public static void main(String[] args) {
        String s = "babad";  // Sample input

        // Print input string
        System.out.println("Input: " + s);

        // Call optimal function and print result
        System.out.println("Longest Palindromic Substring → " + expandCenter(s));
        // Expected output: "bab" or "aba" (both are valid)
    }
}
