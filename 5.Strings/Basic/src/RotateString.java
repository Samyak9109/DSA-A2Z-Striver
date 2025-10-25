/*
 * 🎯 Problem: Rotate String
 *
 * LeetCode: 796. Rotate String
 * GFG: "Check if strings are rotations of each other or not"
 * HackerRank: "String Rotation" problem variant
 *
 * 🧩 Problem Statement:
 * Given two strings s and goal, return true if and only if s can become goal
 * after some number of rotations on s.
 *
 * Example:
 * s = "abcde", goal = "cdeab" → true
 * s = "abcde", goal = "abced" → false
 */

public class RotateString {

    // ========================================================
    // 🧠 Brute Force Approach
    // ========================================================
    /*
     * 💡 Idea:
     * Try every possible rotation of s and compare with goal.
     * Each rotation means moving first i chars to the end.
     *
     * ⏱️ Time Complexity: O(n^2)
     * Because for each of n rotations, we build a new string (O(n)).
     *
     * 💾 Space Complexity: O(n)
     * Since we create a new rotated string in each iteration.
     */
    public boolean rotateStringBrute(String s, String goal) {
        if (s.length() != goal.length()) return false;

        // Try every rotation
        for (int i = 0; i < s.length(); i++) {
            // Rotate by moving first i chars to the end
            String rotated = s.substring(i) + s.substring(0, i);

            // Check if rotation matches goal
            if (rotated.equals(goal)) {
                return true; // 🔥 Found a match
            }
        }

        return false; // 😢 No rotation worked
    }


    // ========================================================
    // ⚙️ Better Approach (using StringBuilder)
    // ========================================================
    /*
     * 💡 Idea:
     * Instead of creating new strings in every loop, use a StringBuilder
     * to efficiently rotate characters.
     *
     * ⏱️ Time Complexity: O(n^2)
     * Still O(n^2) because rotation + comparison for each shift.
     *
     * 💾 Space Complexity: O(n)
     * Because StringBuilder holds the string.
     */
    public boolean rotateStringBetter(String s, String goal) {
        if (s.length() != goal.length()) return false;

        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < s.length(); i++) {
            // Rotate by moving first char to the end
            char ch = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(ch);

            if (sb.toString().equals(goal)) {
                return true; // Found match
            }
        }

        return false;
    }


    // ========================================================
    // 🚀 Optimal Approach (String Concatenation Trick)
    // ========================================================
    /*
     * 💡 Idea:
     * - If goal is a rotation of s, then it must be a substring of s+s.
     * - Example: s = "abcde" → s+s = "abcdeabcde"
     *   goal = "cdeab" ✅ appears in "abcdeabcde".
     *
     * ⏱️ Time Complexity: O(n)
     * contains() internally does substring search → O(n) average case.
     *
     * 💾 Space Complexity: O(1)
     * Just using concatenation — no extra data structures.
     */
    public boolean rotateStringOptimal(String s, String goal) {
        if (s.length() != goal.length()) return false;

        String doubled = s + s; // combine string with itself

        // If goal appears in doubled string → valid rotation
        return doubled.contains(goal);
    }


    // ========================================================
    // 🧪 Main method to demo all approaches
    // ========================================================
    public static void main(String[] args) {
        RotateString obj = new RotateString();

        String s = "abcde";
        String goal = "cdeab";

        System.out.println("Brute Force: " + obj.rotateStringBrute(s, goal));
        System.out.println("Better Approach: " + obj.rotateStringBetter(s, goal));
        System.out.println("Optimal Approach: " + obj.rotateStringOptimal(s, goal));
    }
}
