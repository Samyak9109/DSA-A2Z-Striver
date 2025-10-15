import java.util.*;

public class IsomorphicStrings {

    /*
     * 💡 Problem: Check if two strings s and t are isomorphic.
     * Two strings are isomorphic if each character in s can be replaced
     * to get t, maintaining the order of characters.
     *
     * 🎯 Example:
     * s = "egg", t = "add" → true  ✅
     * s = "foo", t = "bar" → false ❌
     *
     * 🔗 LeetCode: https://leetcode.com/problems/isomorphic-strings/
     * 🔗 GeeksforGeeks: https://www.geeksforgeeks.org/check-if-two-given-strings-are-isomorphic-to-each-other/
     * 🔗 HackerRank Equivalent: “String Transformations” or “Bijective String Mapping” problems
     */

    // ------------------------------------------------------------
    // 🧠 Brute Force Approach
    // ------------------------------------------------------------
    /*
     * Idea: For each character in s, check what it should map to in t.
     * Use a nested loop to verify consistency — extremely inefficient.
     *
     * Time Complexity: O(n^2)
     * Because for each char, we might scan entire previous chars.
     * Space Complexity: O(1)
     * Just using a few variables.
     */
    static boolean bruteIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                // If two characters in s are same, their corresponding in t must match
                if (s.charAt(i) == s.charAt(j) && t.charAt(i) != t.charAt(j)) return false;
                // If two characters in s are different, their corresponding in t must differ
                if (s.charAt(i) != s.charAt(j) && t.charAt(i) == t.charAt(j)) return false;
            }
        }
        return true;
    }

    // ------------------------------------------------------------
    // ⚙️ Better Approach
    // ------------------------------------------------------------
    /*
     * Idea: Use a HashMap to keep a mapping from s → t
     * and a HashSet to make sure no two characters from s map to the same t.
     *
     * Time Complexity: O(n)
     * One pass through the strings, with O(1) map/set ops.
     * Space Complexity: O(1)
     * At most 256 mappings for ASCII.
     */
    static boolean betterIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> map = new HashMap<>();
        Set<Character> used = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);

            if (map.containsKey(sc)) {
                // If already mapped, it must be consistent
                if (map.get(sc) != tc) return false;
            } else {
                // If not mapped yet, check if target char already used
                if (used.contains(tc)) return false;
                map.put(sc, tc);
                used.add(tc);
            }
        }
        return true;
    }

    // ------------------------------------------------------------
    // 🚀 Optimal Approach (O(n), constant space, using arrays)
    // ------------------------------------------------------------
    /*
     * Idea: Maintain two integer arrays representing mapping consistency.
     * Each array holds the "last seen index + 1" for characters from both strings.
     *
     * Trick:
     *  - If characters have been seen before, they must have been seen at the same index.
     *  - If not, mark their index now.
     *
     * Time Complexity: O(n)
     * Single traversal of both strings.
     * Space Complexity: O(1)
     * Only 2 fixed-size arrays (256 for ASCII) — constant space.
     */
    static boolean optimalIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] m1 = new int[256];
        int[] m2 = new int[256];
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // If the previous index of both chars don't match, it's inconsistent mapping
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) return false;

            // Record the current position (+1 to differentiate from default 0)
            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }
        return true;
    }

    // ------------------------------------------------------------
    // 🧪 Main Method - Let's test our synergy in action
    // ------------------------------------------------------------
    public static void main(String[] args) {
        String s1 = "egg", t1 = "add";
        String s2 = "foo", t2 = "bar";
        String s3 = "paper", t3 = "title";

        System.out.println("🧩 Brute Force:");
        System.out.println(s1 + ", " + t1 + " → " + bruteIsomorphic(s1, t1));
        System.out.println(s2 + ", " + t2 + " → " + bruteIsomorphic(s2, t2));

        System.out.println("\n⚙️ Better:");
        System.out.println(s1 + ", " + t1 + " → " + betterIsomorphic(s1, t1));
        System.out.println(s2 + ", " + t2 + " → " + betterIsomorphic(s2, t2));

        System.out.println("\n🚀 Optimal:");
        System.out.println(s1 + ", " + t1 + " → " + optimalIsomorphic(s1, t1));
        System.out.println(s3 + ", " + t3 + " → " + optimalIsomorphic(s3, t3));
    }
}
