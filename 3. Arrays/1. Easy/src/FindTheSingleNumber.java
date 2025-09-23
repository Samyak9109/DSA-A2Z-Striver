import java.util.HashMap;
import java.util.Map;

public class FindTheSingleNumber {

    // ===========================
    // Approach 1: Brute Force
    // ===========================
    // Description:
    // Check each element by counting its occurrences in the array.
    // Time Complexity: O(n^2) – For each element, scan the entire array
    // Space Complexity: O(1) – Only uses variables
    static int getSingleElementBrute(int[] arr) {
        // Loop through each element
        for (int num : arr) {
            int cnt = 0; // Counter for frequency
            // Count occurrences of current element
            for (int i : arr) {
                if (i == num) cnt++;
            }
            // If element occurs exactly once, return it
            if (cnt == 1) return num;
        }
        return -1; // No single element found
    }

    // ===========================
    // Approach 2: Better (HashMap)
    // ===========================
    // Description:
    // Use a HashMap to count frequencies of elements.
    // Time Complexity: O(n) – One pass to count, another pass to find single element
    // Space Complexity: O(n) – Stores counts of all elements
    static int getSingleElementBetter(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer> freqMap = new HashMap<>(); // Frequency map

        // Count frequencies of each element
        for (int num : arr) {
            int value = freqMap.getOrDefault(num, 0);
            freqMap.put(num, value + 1);
        }

        // Find the element with frequency 1
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1; // No single element found
    }

    // ===========================
    // Approach 3: Optimal (XOR)
    // ===========================
    // Description:
    // XOR of a number with itself is 0, XOR with 0 is the number itself.
    // XORing all elements leaves the unique single element.
    // Time Complexity: O(n) – Single pass through array
    // Space Complexity: O(1) – Only one variable used
    static int getSingleElementOptimal(int[] arr) {
        int result = 0; // Initialize XOR result
        for (int num : arr) {
            result ^= num; // XOR with current element
        }
        return result; // Unique element
    }

    // ===========================
    // Main Method: Test All Approaches
    // ===========================
    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 1, 2}; // Input array

        // Test Brute Force
        int ansBrute = getSingleElementBrute(arr);
        System.out.println("Brute Force: The single element is: " + ansBrute);

        // Test Better approach (HashMap)
        int ansBetter = getSingleElementBetter(arr);
        System.out.println("Better (HashMap): The single element is: " + ansBetter);

        // Test Optimal approach (XOR)
        int ansOptimal = getSingleElementOptimal(arr);
        System.out.println("Optimal (XOR): The single element is: " + ansOptimal);
    }
}
