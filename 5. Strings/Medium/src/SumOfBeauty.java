import java.util.HashMap;
import java.util.Map;

public class SumOfBeauty {

    /**
     * ------------------------------------------------------------
     * 🧠 APPROACH 1: Brute Force
     * ------------------------------------------------------------
     * For every substring, calculate frequency of each character,
     * then sum (max frequency - min frequency) ignoring zeros.
     *
     * Time Complexity: O(n^3)
     *  - O(n^2) substrings * O(26) for counting frequency (or O(n) if we iterate map)
     * Space Complexity: O(26) ~ O(1)
     *  - HashMap for character frequencies
     * ------------------------------------------------------------
     */
    static int beautySumBrute(String s) {
        int n = s.length();
        int sum = 0;

        // Loop through all possible starting points
        for (int i = 0; i < n; i++) {
            Map<Character, Integer> freq = new HashMap<>(); // reset for each starting point

            // Loop through all possible end points for substring starting at i
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);

                // Update frequency of current character
                freq.put(c, freq.getOrDefault(c, 0) + 1);

                int maxi = Integer.MIN_VALUE;
                int mini = Integer.MAX_VALUE;

                // Find max and min frequency in current substring
                for (int val : freq.values()) {
                    maxi = Math.max(maxi, val);
                    mini = Math.min(mini, val);
                }

                // Add difference to the total sum
                sum += (maxi - mini);
            }
        }

        return sum;
    }

    /**
     * ------------------------------------------------------------
     * ⚡ OPTIMAL APPROACH (Slight Improvement)
     * ------------------------------------------------------------
     * Since the problem is for lowercase letters only,
     * we can replace HashMap with an int[26] array to reduce overhead.
     *
     * Time Complexity: O(n^3) in worst case (still checking all substrings)
     * Space Complexity: O(26) ~ O(1)
     * ------------------------------------------------------------
     */
    static int beautySumOptimal(String s) {
        int n = s.length();
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26]; // frequency array for 'a' to 'z'

            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'a']++; // increment frequency

                int maxi = 0;
                int mini = Integer.MAX_VALUE;

                // Find max and min (ignoring 0)
                for (int f : freq) {
                    if (f > 0) {
                        maxi = Math.max(maxi, f);
                        mini = Math.min(mini, f);
                    }
                }

                sum += (maxi - mini);
            }
        }

        return sum;
    }

    // -------------------------------
    // Main method to test
    // -------------------------------
    public static void main(String[] args) {
        String s = "aabcb";

        System.out.println("Input: " + s);
        System.out.println("Beauty Sum (Brute Force) → " + beautySumBrute(s));
        System.out.println("Beauty Sum (Optimal) → " + beautySumOptimal(s));
        // Expected output: 5
    }
}
