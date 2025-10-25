import java.util.*;

public class StringAnagrams {

    /*---------------------------------------------------------------
     🧠 Brute Force Approach
     Idea: Sort both strings and compare them directly.
     ---------------------------------------------------------------
     Time Complexity: O(n log n) — due to sorting both strings.
     Space Complexity: O(n) — new char arrays created during sorting.
    ---------------------------------------------------------------*/
    public static String sortString(String str) {
        char[] c = str.toCharArray(); // convert string to char array
        Arrays.sort(c);               // sort alphabetically
        return new String(c);         // convert back to string
    }

    static boolean isAnagramBrute(String s, String t) {
        // Different lengths = not anagrams
        if (s.length() != t.length()) return false;

        // Sort both strings and check if they match entirely
        s = sortString(s);
        t = sortString(t);

        // Compare sorted strings
        return s.equals(t);
    }


    /*---------------------------------------------------------------
     ⚙️ Better Approach
     Idea: Use frequency counting (HashMap) for characters.
     ---------------------------------------------------------------
     Time Complexity: O(n) — single pass through both strings.
     Space Complexity: O(1) — only 26 lowercase letters stored.
    ---------------------------------------------------------------*/
    static boolean isAnagramBetter(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] freq = new int[26]; // frequency array for characters a–z

        // Count +1 for s, -1 for t
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        // If all frequencies are zero, it’s an anagram
        for (int count : freq) {
            if (count != 0) return false;
        }

        return true;
    }


    /*---------------------------------------------------------------
     🚀 Optimal Approach (if Unicode or larger charset)
     Idea: Use a HashMap to support all chars (not just a–z).
     ---------------------------------------------------------------
     Time Complexity: O(n)
     Space Complexity: O(k) — k = number of unique characters.
    ---------------------------------------------------------------*/
    static boolean isAnagramOptimal(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> map = new HashMap<>();

        // Count chars in s
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Subtract counts using t
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) return false; // char not found
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) map.remove(c);   // clean up zeros
        }

        return map.isEmpty(); // if empty → perfect anagram
    }


    // 🧪 Main method to demo all 3
    public static void main(String[] args) {
        String s = "listen";
        String t = "silent";

        System.out.println("Brute Force: " + isAnagramBrute(s, t));
        System.out.println("Better: " + isAnagramBetter(s, t));
        System.out.println("Optimal: " + isAnagramOptimal(s, t));
    }
}
