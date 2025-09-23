public class CheckArrayForSorted {

    // ===========================
    // Approach 1: Brute Force
    // ===========================
    // Description:
    // Compare every pair (i, j) where i < j. If any arr[i] > arr[j], array is not sorted.
    // Time Complexity: O(n^2) – Two nested loops
    // Space Complexity: O(1) – No extra space used
    static boolean isSortedBruteForce(int[] arr) {
        // Loop through each element
        for (int i = 0; i < arr.length - 1; i++) {
            // Compare with every element after i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    // Found a pair violating order
                    return false;
                }
            }
        }
        return true; // All pairs are in order
    }

    // ===========================
    // Approach 2: Iterative (Better)
    // ===========================
    // Description:
    // Compare each element with the next one. If any arr[i] > arr[i+1], array is not sorted.
    // Time Complexity: O(n) – Single loop
    // Space Complexity: O(1) – Constant extra space
    static boolean isSortedIterative(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false; // Found unsorted adjacent elements
            }
        }
        return true; // Array is sorted
    }

    // ===========================
    // Approach 3: Recursive (Optimal)
    // ===========================
    // Description:
    // Recursively check adjacent elements. Base case is last element.
    // Time Complexity: O(n) – One recursive call per element
    // Space Complexity: O(n) – Stack space due to recursion
    static boolean isSortedRecursive(int[] arr, int index) {
        if (index == arr.length - 1) return true; // Base case: last element
        if (arr[index] > arr[index + 1]) return false; // Not sorted
        return isSortedRecursive(arr, index + 1); // Check next pair
    }

    // ===========================
    // Approach 4: Stream (Concise)
    // ===========================
    // Description:
    // Java 8 stream checks that all adjacent pairs are sorted.
    // Time Complexity: O(n) – Stream loops once internally
    // Space Complexity: O(1) – Constant overhead
    static boolean isSortedStream(int[] arr) {
        return java.util.stream.IntStream.range(0, arr.length - 1)
                .allMatch(i -> arr[i] <= arr[i + 1]);
    }

    // ===========================
    // Helper Method
    // ===========================
    // Prints result for each method
    static void checkSorted(String methodName, boolean result) {
        System.out.println(methodName + ": " +
                (result ? "Array is sorted." : "Array is NOT sorted."));
    }

    // ===========================
    // Main Method: Test All Approaches
    // ===========================
    public static void main(String[] args) {
        int[] sortedArray = {1, 2, 3, 4, 5};
        int[] unsortedArray = {1, 3, 2, 5};

        System.out.println("Testing with sortedArray:");
        checkSorted("BruteForce", isSortedBruteForce(sortedArray));
        checkSorted("Iterative", isSortedIterative(sortedArray));
        checkSorted("Recursive", isSortedRecursive(sortedArray, 0));
        checkSorted("Stream", isSortedStream(sortedArray));

        System.out.println("\nTesting with unsortedArray:");
        checkSorted("BruteForce", isSortedBruteForce(unsortedArray));
        checkSorted("Iterative", isSortedIterative(unsortedArray));
        checkSorted("Recursive", isSortedRecursive(unsortedArray, 0));
        checkSorted("Stream", isSortedStream(unsortedArray));
    }
}
