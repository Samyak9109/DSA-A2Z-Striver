import java.util.HashMap;
import java.util.Map;

public class NumOfSubstring {

    /**
     * Leetcode 992 uses same approach, but we get array of elements  instead of string
     * -----------------------------------------------------------
     * 🔹 Helper Function: mostDistinctK
     * Purpose: Count substrings with **at most K distinct characters**
     * -----------------------------------------------------------
     * 🧠 Logic:
     * Use the sliding window technique. Expand the right pointer
     * to include more characters, and shrink from the left
     * whenever distinct characters exceed 'k'.

     * For each valid window [left...right], the number of
     * substrings ending at 'right' = (right - left + 1)

     * Time Complexity: O(n)
     *   -> Each character is visited at most twice (once by right, once by left)
     * Space Complexity: O(k)
     *   -> Storing frequency of at most k distinct characters
     */
    static int mostDistinctK(String s, int k) {
        int left = 0, ans = 0;
        Map<Character, Integer> freq = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            // Expand the window by including s[right]
            freq.put(s.charAt(right), freq.getOrDefault(s.charAt(right), 0) + 1);

            // If we exceed K distinct characters, shrink from left
            while (freq.size() > k) {
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar) - 1);
                if (freq.get(leftChar) == 0) freq.remove(leftChar);
                left++;
            }

            // Add number of substrings ending at 'right'
            ans += (right - left + 1);
        }

        return ans;
    }

    /**
     * -----------------------------------------------------------
     * 🔹 Function: countExactlyK
     * Purpose: Count substrings with **exactly K distinct characters**
     * -----------------------------------------------------------
     * 🧠 Logic:
     * Number of substrings with exactly K distinct characters =
     * (Number with at most K) - (Number with at most K-1)

     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */
    static int countExactlyK(String s, int k) {
        if (k == 0) return 0;
        return mostDistinctK(s, k) - mostDistinctK(s, k - 1);
    }

    // -----------------------------------------------------------
    // 🚀 main() Method — Let’s test this bad boy in action
    // -----------------------------------------------------------
    public static void main(String[] args) {
        String s = "pqpqs";
        int k = 2;

        int result = countExactlyK(s, k);

        System.out.println("String: " + s);
        System.out.println("K = " + k);
        System.out.println("Number of substrings with exactly " + k + " distinct characters = " + result);
    }
}
