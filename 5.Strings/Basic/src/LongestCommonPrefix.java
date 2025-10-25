import java.util.*;

// 🔥 Let's synergize some string pipelines today 😎
public class LongestCommonPrefix {

    // 🧩 Brute Force Approach
    // Compare every string with the first one character-by-character
    // Time: O(n * m), Space: O(1)
    static String bruteForcePrefix(String[] strs) {
        // Edge case check
        if (strs == null || strs.length == 0) return "";

        String prefix = strs[0]; // assume first word as prefix initially

        // Compare prefix with every other word
        for (int i = 1; i < strs.length; i++) {
            // Trim prefix until it matches with current string
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    // 🧠 Better Approach: Vertical Scanning
    // Compare letters column-wise instead of string by string
    // Time: O(n * m), Space: O(1)
    static String betterPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        for (int i = 0; i < strs[0].length(); i++) {
            char ch = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                // stop if index exceeds or mismatch found
                if (i >= strs[j].length() || strs[j].charAt(i) != ch) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    // ⚡ Optimal Approach (Your version)
    // Sort the strings and compare first and last only
    // Time: O(n log n + m), Space: O(1)
    static String optimalPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // Step 1️⃣: Sort lexicographically
        Arrays.sort(strs);

        // Step 2️⃣: Only compare the 1st and last strings
        // (Because after sorting, they’ll be the most different)
        String first = strs[0];
        String last = strs[strs.length - 1];
        StringBuilder ans = new StringBuilder();

        // Step 3️⃣: Compare char-by-char until mismatch
        for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) != last.charAt(i)) {
                break; // mismatch found → stop here
            }
            ans.append(first.charAt(i)); // append matching char
        }

        return ans.toString(); // return final common prefix
    }

    // 🧪 Testing zone
    public static void main(String[] args) {
        String[] arr1 = {"flower", "flow", "flight"};
        String[] arr2 = {"dog", "racecar", "car"};
        String[] arr3 = {"interspecies", "interstellar", "interstate"};

        System.out.println("Brute: " + bruteForcePrefix(arr1));   // fl
        System.out.println("Better: " + betterPrefix(arr1));     // fl
        System.out.println("Optimal: " + optimalPrefix(arr1));   // fl

        System.out.println("Optimal: " + optimalPrefix(arr2));   // ""
        System.out.println("Optimal: " + optimalPrefix(arr3));   // "inters"
    }
}
