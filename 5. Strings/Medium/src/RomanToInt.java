import java.util.*;

/**
 * 🧩 Problem: Roman to Integer
 * LeetCode #13
 * GeeksforGeeks: "Convert Roman Numeral to Integer"
 * HackerRank Equivalent: "Roman Numbers Conversion" (similar concept)
 */

public class RomanToInt {

    /* --------------------------------------------------------------
     * 💀 BRUTE FORCE APPROACH
     * --------------------------------------------------------------
     * Idea:
     *  - Replace all subtraction pairs manually (like IV → 4, IX → 9)
     *  - Then just sum up the values of all remaining characters.
     *
     * ⏱️ Time Complexity: O(n)
     * 💾 Space Complexity: O(1)
     *  - Linear traversal but involves multiple string replacements.
     */
    static int romanToIntBrute(String s) {
        // Replace subtractive notations with their expanded equivalent
        s = s.replace("IV", "IIII");
        s = s.replace("IX", "VIIII");
        s = s.replace("XL", "XXXX");
        s = s.replace("XC", "LXXXX");
        s = s.replace("CD", "CCCC");
        s = s.replace("CM", "DCCCC");

        Map<Character, Integer> roman = Map.of(
                'I', 1, 'V', 5, 'X', 10, 'L', 50,
                'C', 100, 'D', 500, 'M', 1000
        );

        int ans = 0;
        for (char c : s.toCharArray()) {
            ans += roman.get(c);
        }
        return ans;
    }

    /* --------------------------------------------------------------
     * ⚙️ BETTER APPROACH (Forward with HashMap)
     * --------------------------------------------------------------
     * Idea:
     *  - Compare current Roman symbol with the next one.
     *  - If current < next → subtract it.
     *  - Otherwise → add it.
     *
     * ⏱️ Time Complexity: O(n)
     * 💾 Space Complexity: O(1)
     */
    static int romanToIntBetter(String s) {
        Map<Character, Integer> roman = new HashMap<>();
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);

        int ans = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            // If current symbol is smaller than next → subtract it
            if (roman.get(s.charAt(i)) < roman.get(s.charAt(i + 1))) {
                ans -= roman.get(s.charAt(i));
            } else {
                ans += roman.get(s.charAt(i));
            }
        }

        // Add value of last character (since loop stops at n-2)
        ans += roman.get(s.charAt(s.length() - 1));
        return ans;
    }

    /* --------------------------------------------------------------
     * 🚀 OPTIMAL APPROACH (Forward + Switch + Ternary)
     * --------------------------------------------------------------
     * 💡 Ternary refresher:
     *   condition ? valueIfTrue : valueIfFalse
     *   Example:
     *   int x = (5 > 3) ? 10 : 20;  // sets x = 10
     *
     * In our code:
     *   int next = (i + 1 < s.length()) ? valueOf(s.charAt(i + 1)) : 0;
     *   ✅ If there *is* a next char, get its value
     *   ❌ Else, assign 0 to avoid index out of bounds
     *
     * ⏱️ Time Complexity: O(n)
     * 💾 Space Complexity: O(1)
     */
    static int romanToIntOptimal(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            int curr = valueOf(s.charAt(i));

            // 💡 Ternary check for next char existence
            int next = (i + 1 < s.length())
                    ? valueOf(s.charAt(i + 1))  // ✅ next char exists
                    : 0;                         // ❌ no next char → use 0

            // Subtraction rule: smaller before larger → subtract
            if (curr < next) {
                ans -= curr;
            } else {
                ans += curr;
            }
        }

        return ans;
    }

    /* --------------------------------------------------------------
     * 🧠 REVERSE TRAVERSAL APPROACH (Mathematical Trick)
     * --------------------------------------------------------------
     * 💡 Idea:
     *  - Start scanning from the rightmost Roman symbol.
     *  - Normally, add each value.
     *  - But if the current value *times 4* is less than accumulated total,
     *    it means this is a "subtractive" case → subtract instead of add.
     *
     * 🔢 Trick:
     *  - The “4 * n < a” condition cleverly detects subtractive pairs
     *    without needing to look ahead.
     *
     * 🧩 Example: "MCMXCIV"
     *   Traverse backwards:
     *   → V(+5), I(-1), C(+100), X(-10), M(+1000), C(-100), M(+1000)
     *   = 1994 ✅
     *
     * ⏱️ Time Complexity: O(n)
     * 💾 Space Complexity: O(1)
     */
    static int romanToIntReverse(String s) {
        int a = 0; // final accumulated value
        int n = 0; // current numeral value

        // Traverse backwards
        for (int i = s.length() - 1; i >= 0; i--) {
            switch (s.charAt(i)) {
                case 'I': n = 1; break;
                case 'V': n = 5; break;
                case 'X': n = 10; break;
                case 'L': n = 50; break;
                case 'C': n = 100; break;
                case 'D': n = 500; break;
                case 'M': n = 1000; break;
            }

            // 🧮 Smart subtraction rule:
            // If 4 * current < accumulated → subtract
            // else → add normally
            if (4 * n < a) {
                a -= n;
            } else {
                a += n;
            }
        }

        return a;
    }

    // ⚡ Helper method: value mapping using switch
    static int valueOf(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default:  return 0;
        }
    }

    // 🧪 Demo main() — testing all 4 approaches
    public static void main(String[] args) {
        String s = "MCMXCIV";  // Expected → 1994

        System.out.println("Brute   → " + romanToIntBrute(s));
        System.out.println("Better  → " + romanToIntBetter(s));
        System.out.println("Optimal → " + romanToIntOptimal(s));
        System.out.println("Reverse → " + romanToIntReverse(s));
    }
}
