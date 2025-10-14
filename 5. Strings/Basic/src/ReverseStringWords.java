/*
💡 LeetCode Equivalent: 151. Reverse Words in a String
🔗 https://leetcode.com/problems/reverse-words-in-a-string/

🎯 Problem Summary:
Given an input string `s`, reverse the order of the words.
Words are separated by spaces, and multiple spaces should be reduced to one in the result.

Example:
Input:  "  the sky  is  blue  "
Output: "blue is sky the"
*/

public class ReverseStringWords {

    /* ───────────────────────────────
       🪜 Brute Force Approach
       ───────────────────────────────
       1️⃣ Split the string by spaces.
       2️⃣ Store non-empty words in an array/list.
       3️⃣ Reverse the list and join them with a single space.
    */
    // 🕒 O(n) — traversing the string once for splitting and once for joining
    // 💾 O(n) — for storing words in an array/list
    public String reverseWordsBrute(String s) {
        // Split by spaces (some will be empty strings)
        String[] words = s.trim().split(" ");

        StringBuilder sb = new StringBuilder();
        // Iterate backward and append non-empty words
        for (int i = words.length - 1; i >= 0; i--) {
            if (!words[i].isEmpty()) { // ignore multiple spaces
                sb.append(words[i]).append(" ");
            }
        }
        // Remove the trailing space at the end
        return sb.toString().trim();
    }


    /* ───────────────────────────────
       ⚡ Better Approach
       ───────────────────────────────
       Use a `StringTokenizer` to handle splitting automatically.
       It ignores multiple spaces and tokenizes cleanly.
    */
    // 🕒 O(n)
    // 💾 O(n) — tokens stored in stack-like order
    public String reverseWordsBetter(String s) {
        java.util.StringTokenizer st = new java.util.StringTokenizer(s);
        java.util.Stack<String> stack = new java.util.Stack<>();

        // Push each word into the stack
        while (st.hasMoreTokens()) {
            stack.push(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        // Pop from stack to reverse order
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        return sb.toString().trim();
    }


    /* ───────────────────────────────
       🚀 Optimal Approach (Two-pointer)
       ───────────────────────────────
       Instead of splitting or using extra structures,
       we walk from the end of the string and extract each word directly.
       This avoids storing all words at once.
    */
    // 🕒 Time Complexity: O(n)
    // 💾 Space Complexity: O(1) (excluding output)
    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        int end = s.length() - 1;

        while (end >= 0) {
            // Skip trailing spaces
            while (end >= 0 && s.charAt(end) == ' ') end--;

            if (end < 0) break; // No more words

            int start = end;
            // Move `start` to the beginning of the word
            while (start >= 0 && s.charAt(start) != ' ') start--;

            // Append the current word
            result.append(s.substring(start + 1, end + 1)).append(" ");

            // Move `end` to before the previous word
            end = start - 1;
        }

        // Trim extra space at the end
        return result.toString().trim();
    }


    // 💻 Main method for demonstration
    public static void main(String[] args) {
        ReverseStringWords sol = new ReverseStringWords();

        String s1 = "  the sky  is  blue  ";
        String s2 = "  hello world  ";
        String s3 = "a good   example";

        System.out.println("Brute Force → " + sol.reverseWordsBrute(s1));
        System.out.println("Better      → " + sol.reverseWordsBetter(s1));
        System.out.println("Optimal     → " + sol.reverseWords(s1));

        System.out.println("\nExtra Tests:");
        System.out.println(sol.reverseWords(s2)); // "world hello"
        System.out.println(sol.reverseWords(s3)); // "example good a"
    }
}

/*
🌈 Real-world Analogy:
Imagine you’re reading a sentence from left to right — now flip your perspective
like reversing the order of tweets in your timeline.
You don’t change the words, just the order. That’s exactly what we’re doing here 🧠
*/
