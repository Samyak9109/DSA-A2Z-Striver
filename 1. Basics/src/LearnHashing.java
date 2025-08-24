import java.util.HashMap;
import java.util.Map;

public class LearnHashing {

    /*
     * countFreq function:
     * This function counts the frequency of each element in the array using a HashMap,
     * then finds the element with the maximum frequency and the element with the minimum frequency.
     *
     * Time Complexity: O(n)
     * - Traversing the array once to build the frequency map: O(n)
     * - Traversing the frequency map entries to find max/min frequency: O(k) where k is number of unique elements (k <= n)
     * Overall: O(n)
     *
     * Space Complexity: O(k)
     * - Additional space for the HashMap storing frequencies of unique elements.
     */
    static void countFreq(int arr[], int n) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Count frequencies of elements
        for (int i = 0; i < n; i++) {
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
        }

        int maxFreq = 0, minFreq = n;
        int maxEle = -1, minEle = -1;

        // Find max and min frequency elements
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int key = entry.getKey();
            int freq = entry.getValue();

            System.out.println(key + " -> " + freq);

            if (freq > maxFreq) {
                maxFreq = freq;
                maxEle = key;
            }

            if (freq < minFreq) {
                minFreq = freq;
                minEle = key;
            }
        }

        System.out.println("The highest frequency element is: " + maxEle + " (Freq: " + maxFreq + ")");
        System.out.println("The lowest frequency element is: " + minEle + " (Freq: " + minFreq + ")");
    }

    /*
     * Frequency function:
     * Counts frequencies of elements using a HashMap but only prints frequencies.
     *
     * Time Complexity: O(n) — same as countFreq.
     * Space Complexity: O(k)
     */
    static void Frequency(int arr[], int n) {
        Map<Integer, Integer> map = new HashMap<>();

        // Count frequency of each element
        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }

        // Print frequencies
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + " using Map");
        }
    }

    public static void main(String args[]) {
        int arr[] = {10, 5, 10, 15, 10, 5};
        int n = arr.length;

        Frequency(arr, n);
        countFreq(arr, n);
    }
}
