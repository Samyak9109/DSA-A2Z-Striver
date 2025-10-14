/*
💡 LeetCode Equivalent: 151. Reverse Words in a String
🔗 https://leetcode.com/problems/reverse-words-in-a-string/

🎯 Problem Statement:
Given a string `s`, reverse the order of words. A word is defined as a sequence
of non-space characters. Return the reversed sentence with single spaces only.

Example:
Input:  "  the sky  is  blue  "
Output: "blue is sky the"
*/

import java.util.*;

public class ReverseWordsInString {

    /* ───────────────────────────────
       🧱 Brute Force Approach (Manual Stack)
       ───────────────────────────────
       🧠 Core Idea:
       - Walk through the string character by character.
       - Whenever we find a word (non-space sequence), push it onto a Stack.
       - Once all words are pushed, pop them one by one → reversed order!
    */
    // 🕒 Time Complexity: O(n) — traverse input once, build output once.
    // 💾 Space Complexity: O(n) — Stack stores all words.
    public static String reverseWordsBrute(String s) {
        Stack<String> stack = new Stack<>(); // Stack to reverse word order
        int n = s.length();
        StringBuilder word = new StringBuilder(); // temp buffer to build current word

        // Step 1️⃣: Traverse the string character by character
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            // If it's not a space → part of a word
            if (ch != ' ') {
                word.append(ch); // keep adding characters to build the word
            }
            // If it's a space, and we already have a word built, push it
            else if (!word.isEmpty()) {
                stack.push(word.toString()); // push the completed word to stack
                word.setLength(0); // clear the builder for the next word
            }
        }

        // Step 2️⃣: Push the last word if the string didn’t end with a space
        if (!word.isEmpty()) {
            stack.push(word.toString());
        }

        // Step 3️⃣: Pop all words to build reversed sentence
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop()); // pop gives reversed order
            if (!stack.isEmpty()) result.append(" "); // add space between words
        }

        // Step 4️⃣: Return the final reversed sentence
        return result.toString();
    }


    /* ───────────────────────────────
       ⚡ Better Approach (Split + Stack)
       ───────────────────────────────
       🧠 Core Idea:
       - Use Java's built-in split() to separate words easily.
       - Ignore empty words caused by multiple spaces.
       - Push all words to a Stack, then pop them out.
    */
    // 🕒 O(n) time | 💾 O(n) space
    public static String reverseWordsBetter(String s) {
        // Step 1️⃣: Split string by spaces (trim() removes leading/trailing)
        String[] words = s.trim().split(" ");
        Stack<String> stack = new Stack<>();

        // Step 2️⃣: Push only non-empty words (ignore extra spaces)
        for (String word : words) {
            if (!word.isEmpty()) {
                stack.push(word);
            }
        }

        // Step 3️⃣: Pop to reverse order
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()); // append top word
            if (!stack.isEmpty()) sb.append(" "); // avoid trailing space
        }

        return sb.toString();
    }


    /* ───────────────────────────────
       🚀 Optimal Approach (Two-pointer Magic)
       ───────────────────────────────
       🧠 Core Idea:
       - Instead of using extra structures, we reverse on-the-fly.
       - Traverse the string **backward**, find word boundaries manually.
       - Directly append each found word to the result.
    */
    // 🕒 Time Complexity: O(n)
    // 💾 Space Complexity: O(1) extra (just output builder)
    public static String reverseWordsOptimal(String s) {
        StringBuilder result = new StringBuilder(); // stores final reversed sentence
        int end = s.length() - 1; // start from the end of the string

        // Step 1️⃣: Loop backward through the string
        while (end >= 0) {
            // Skip trailing or multiple spaces
            while (end >= 0 && s.charAt(end) == ' ') {
                end--;
            }

            // If we reached before start, break
            if (end < 0) break;

            // Step 2️⃣: Find the start of the current word
            int start = end;
            while (start >= 0 && s.charAt(start) != ' ') {
                start--;
            }

            // Step 3️⃣: Append the current word to the result
            // (substring picks word between start+1 and end inclusive)
            result.append(s, start + 1, end + 1).append(" ");

            // Step 4️⃣: Move 'end' pointer to before this word
            end = start - 1;
        }

        // Step 5️⃣: Remove trailing space and return final result
        return result.toString().trim();
    }


    /* 💻 Driver code for testing all approaches */
    public static void main(String[] args) {
        String s = "  the sky  is  blue  ";

        System.out.println("Input: \"" + s + "\"\n");
        System.out.println("🧱 Brute Force (Manual Stack) → " + reverseWordsBrute(s));
        System.out.println("⚡ Better (Split + Stack)     → " + reverseWordsBetter(s));
        System.out.println("🚀 Optimal (Two-pointer)      → " + reverseWordsOptimal(s));
    }
}

/*
✨ OUTPUT:
Input: "  the sky  is  blue  "
🧱 Brute Force (Manual Stack) → blue is sky the
⚡ Better (Split + Stack)     → blue is sky the
🚀 Optimal (Two-pointer)      → blue is sky the

🌈 Intuitive Analogy:
Imagine your brain as a stack 🧠.
You read a sentence word by word — each goes on top of the stack.
When you’re done, you start recalling in reverse order — boom, words reversed!
*/
