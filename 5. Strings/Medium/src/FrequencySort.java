import java.util.*;

public class FrequencySort {

    /*-------------------------------------------------------------------
     🧠 APPROACH 1: HashMap + Sorting
     -------------------------------------------------------------------
     💡 IDEA:
     - Count the frequency of each character using a HashMap.
     - Then, sort the unique characters based on their frequency (descending).
     - Finally, rebuild the string with characters repeated by their frequency.

     ⚙️ ANALOGY:
     Imagine counting how many of each snack is in your pantry,
     then sorting snacks so the most common ones appear first.

     🕒 TIME COMPLEXITY: O(n log n)
        → O(n) to count + O(k log k) to sort (k = number of unique chars)
     🧮 SPACE COMPLEXITY: O(n)
        → O(k) for HashMap + StringBuilder space
    -------------------------------------------------------------------*/
    public String frequencySort(String s) {
        // Step 1️⃣: Count how many times each character appears
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Step 2️⃣: Create a list of all unique characters
        List<Character> chars = new ArrayList<>(freqMap.keySet());

        // Step 3️⃣: Sort characters based on frequency (descending order)
        // Comparator compares frequency of two characters using freqMap
        chars.sort((a, b) -> freqMap.get(b) - freqMap.get(a));

        // Step 4️⃣: Rebuild the result string by repeating characters based on their frequency
        StringBuilder result = new StringBuilder();
        for (char c : chars) {
            int freq = freqMap.get(c);
            for (int i = 0; i < freq; i++) {
                result.append(c);
            }
        }

        // Step 5️⃣: Return the final frequency-sorted string
        return result.toString();
    }

    /*-------------------------------------------------------------------
     🧠 APPROACH 2: PriorityQueue (Max Heap)
     -------------------------------------------------------------------
     💡 IDEA:
     - Still count frequencies using a HashMap.
     - Use a Max-Heap (PriorityQueue in Java) to always extract the most frequent character first.
     - Keep polling the heap and building the result.

     ⚙️ ANALOGY:
     Think of it like a ticketing system where the most frequent customers
     (characters) get served first from a VIP priority queue. 💼✨

     🕒 TIME COMPLEXITY: O(n log k)
        → O(n) to count + O(k log k) for heap operations
     🧮 SPACE COMPLEXITY: O(n)
        → HashMap + Heap + StringBuilder
    -------------------------------------------------------------------*/
    public String frequencySort_v2(String s) {
        // Step 1️⃣: Count frequencies
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Step 2️⃣: Create a max heap that sorts by frequency descending
        PriorityQueue<Character> maxHeap = new PriorityQueue<>(
                (a, b) -> freqMap.get(b) - freqMap.get(a)
        );

        // Step 3️⃣: Add all unique characters to the heap
        maxHeap.addAll(freqMap.keySet());

        // Step 4️⃣: Poll (remove) elements from heap one by one
        // Each poll gives us the next most frequent character
        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll(); // get highest frequency char
            int freq = freqMap.get(c);

            // Append that character freq times
            for (int i = 0; i < freq; i++) {
                result.append(c);
            }
        }

        // Step 5️⃣: Return final built string
        return result.toString();
    }

    /*-------------------------------------------------------------------
     🧠 APPROACH 3: Bucket Sort (Optimal & Fastest)
     -------------------------------------------------------------------
     💡 IDEA:
     - After counting frequencies, create an array of lists (buckets).
     - Each index in the array represents a frequency count.
     - Place each character into its respective bucket based on frequency.
     - Finally, build the result from the highest frequency bucket to the lowest.

     ⚙️ ANALOGY:
     Imagine having multiple bins labeled 1 to maxFreq.
     You throw each character into the bin that matches its frequency,
     then collect from the fullest bin first. 🔥

     🕒 TIME COMPLEXITY: O(n)
        → O(n) to count + O(n) to build output (no sorting needed)
     🧮 SPACE COMPLEXITY: O(n)
        → HashMap + buckets array
    -------------------------------------------------------------------*/
    public String frequencySort_v3(String s) {
        if (s == null || s.length() == 0) return s;

        // Step 1️⃣: Count frequencies and track the max frequency
        Map<Character, Integer> freqMap = new HashMap<>();
        int maxFreq = 0;
        for (char c : s.toCharArray()) {
            int freq = freqMap.getOrDefault(c, 0) + 1;
            freqMap.put(c, freq);
            maxFreq = Math.max(maxFreq, freq); // track highest frequency
        }

        // Step 2️⃣: Create buckets, where index = frequency
        // Example: bucket[3] = list of chars appearing 3 times
        List<Character>[] buckets = new ArrayList[maxFreq + 1];
        for (int i = 0; i <= maxFreq; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Step 3️⃣: Fill buckets based on frequency
        for (char c : freqMap.keySet()) {
            int freq = freqMap.get(c);
            buckets[freq].add(c);
        }

        // Step 4️⃣: Build result from highest frequency to lowest
        StringBuilder result = new StringBuilder();
        for (int freq = maxFreq; freq > 0; freq--) {
            for (char c : buckets[freq]) {
                for (int i = 0; i < freq; i++) {
                    result.append(c); // repeat char freq times
                }
            }
        }

        // Step 5️⃣: Return final sorted string
        return result.toString();
    }

    /*-------------------------------------------------------------------
     🧪 TESTING MAIN METHOD
     -------------------------------------------------------------------
     - Runs multiple test cases through all three approaches.
     - This helps you visually verify that all methods give same output.
     -------------------------------------------------------------------*/
    public static void main(String[] args) {
        FrequencySort solution = new FrequencySort();
        String[] testCases = {"tree", "cccaaa", "Aabb", "loveleetcode"};

        for (String s : testCases) {
            System.out.println("Input: '" + s + "'");
            System.out.println("Output v1 (Map+Sort): '" + solution.frequencySort(s) + "'");
            System.out.println("Output v2 (MaxHeap):  '" + solution.frequencySort_v2(s) + "'");
            System.out.println("Output v3 (Bucket):   '" + solution.frequencySort_v3(s) + "'");
            System.out.println("--------------------------------------------------");
        }
    }
}
