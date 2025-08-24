import java.util.HashMap;
import java.util.Map;

public class FindTheSingleNumber {

    // -------------------------------------------------------------------
    // Brute Force method: Check each element by counting occurrences
    // Time Complexity:
    // O(n^2) – For each element, scan entire array to count frequency
    // Space Complexity:
    // O(1) – Only uses variables, no extra data structures
    static int getSingleElementBrute(int[] arr) {
        // Check each number's frequency
        for (int num : arr) {
            int cnt = 0;       // Counter for frequency
            for (int i : arr) {
                if (i == num) cnt++;  // Increment count if match found
            }
            if (cnt == 1) return num;  // Return if count is exactly one
        }
        return -1;  // No single element found
    }

    //-------------------------------------------------------------------

    // Better method: Use a HashMap to count frequencies
    // Time Complexity:
    // O(n) – Single pass to count, another pass to find single element in map
    // Space Complexity:
    // O(n) – HashMap stores counts of elements
    static int getSingleElementBetter(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer> mpp = new HashMap<>();  // Frequency map

        // Count frequencies of each element
        for (int j : arr) {
            int value = mpp.getOrDefault(j, 0);
            mpp.put(j, value + 1);
        }

        // Find the element with frequency 1
        for (Map.Entry<Integer, Integer> it : mpp.entrySet()) {
            if (it.getValue() == 1) {
                return it.getKey();
            }
        }
        return -1;  // No single element found
    }

    //-------------------------------------------------------------------

    // Optimal method: Use XOR property
    // XOR of a number with itself is 0, and XOR with 0 is the number itself
    // XORing all elements leaves the unique single element
    // Time Complexity:
    // O(n) – Single pass through array
    // Space Complexity:
    // O(1) – Only uses one variable for XOR result
    static int getSingleElementOptimal(int[] arr) {
        int result = 0;  // Initialize XOR result
        for (int num : arr) {
            result ^= num;  // XOR all elements
        }
        return result;  // Result is the single element
    }

    //-------------------------------------------------------------------

    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 1, 2};  // Input array

        int ansBrute = getSingleElementBrute(arr);  // Call brute method
        System.out.println("Brute Force: The single element is: " + ansBrute);

        int ansBetter = getSingleElementBetter(arr);  // Call better method
        System.out.println("Better (HashMap): The single element is: " + ansBetter);

        int ansOptimal = getSingleElementOptimal(arr);  // Call optimal method
        System.out.println("Optimal (XOR): The single element is: " + ansOptimal);
    }
}
