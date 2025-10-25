/*
💡 LeetCode Equivalent: 1021. Remove Outermost Parentheses
🔗 https://leetcode.com/problems/remove-outermost-parentheses/

🎯 Problem Summary:
Given a valid parentheses string s, split it into primitive substrings
and remove the outermost parentheses of each primitive.

Example:
Input: s = "(()())(())"
Output: "()()()"
Reason:
Primitive groups are "(()())" and "(())"
After removing outermost pairs → "()()" + "()" = "()()()"
*/

public class RemoveOuterParanthesis {

    /*
     🧠 Approach: Level Counter (a.k.a. stack simulation)
     -----------------------------------------------------
     We keep a `level` counter representing the depth of parentheses.
     - Every '(' increases the depth.
     - Every ')' decreases the depth.
     - We only append a parenthesis to result if we are **inside** (i.e. level > 0).

     This ensures that the first '(' and the last ')' of every primitive substring
     (the outermost pair) are *not included* in the final result.
    */

    /*
     🕒 Time Complexity: O(n)
        - We visit each character exactly once.
     🧠 Reasoning: Just one pass through the string.

     💾 Space Complexity: O(n)
        - StringBuilder stores the processed string.
        - O(1) extra variables aside from output.
    */
    static String removeOuterParenthesis(String s) {
        StringBuilder result = new StringBuilder(); // stores final output string
        int level = 0; // represents current nesting level

        // Iterate through every character in the string
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                // If level > 0, it means this '(' is not outermost, so keep it
                if (level > 0) result.append(ch);
                // Increase the depth level
                level++;
            } else if (ch == ')') {
                // Before decreasing, check if we’re not closing outermost '('
                level--;
                if (level > 0) result.append(ch);
            }
        }

        // Return the string after removing all outermost parentheses
        return result.toString();
    }

    // 💻 Demo time!
    public static void main(String[] args) {
        String s1 = "(()())(())";
        String s2 = "(()())(())(()(()))";
        String s3 = "()()";

        System.out.println("Input: " + s1 + " ➤ Output: " + removeOuterParenthesis(s1));
        System.out.println("Input: " + s2 + " ➤ Output: " + removeOuterParenthesis(s2));
        System.out.println("Input: " + s3 + " ➤ Output: " + removeOuterParenthesis(s3));
    }
}

/*
✨ Output:
Input: (()())(()) ➤ Output: ()()()
Input: (()())(())(()(())) ➤ Output: ()()()()(())
Input: ()() ➤ Output:

🌈 Intuitive Analogy:
Think of each '(' as walking deeper into a forest 🌲, and each ')' as walking out.
The first step into the forest and the final step out of it? You skip those —
you only record what happens *inside the forest*. 🧭
*/
