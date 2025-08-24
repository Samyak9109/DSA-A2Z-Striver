public class CheckArrayForSorted {

    // -------------------------------------------------------------------
    // Brute Force: Compare every pair (i, j) where i < j
    static boolean isSortedBruteForce(int[] arr) {
        // Check every pair to ensure arr[i] <= arr[j] for all j > i
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    return false;
                }
            }
        }
        return true;
    }
    // Time Complexity:
    // O(n^2) – Two nested loops checking all pairs (i, j)
    // Total Time Complexity: O(n^2)
    // Space Complexity:
    // O(1) – No extra space used

    // -------------------------------------------------------------------
    // Better Approach: Iterative comparison of adjacent elements
    static boolean isSortedIterative(int[] arr) {
        // Compare each element with the one before it
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }
    // Time Complexity:
    // O(n) – Single loop through the array
    // Total Time Complexity: O(n)
    // Space Complexity:
    // O(1) – Constant space used

    // -------------------------------------------------------------------
    // Optimal Approach 1: Recursive check of adjacent elements
    static boolean isSortedRecursive(int[] arr, int index) {
        // Base case: last element reached
        if (index == arr.length - 1) return true;

        // If current is greater than next, not sorted
        if (arr[index] > arr[index + 1]) return false;

        // Recursive call for next index
        return isSortedRecursive(arr, index + 1);
    }
    // Time Complexity:
    // O(n) – One recursive call per element
    // Total Time Complexity: O(n)
    // Space Complexity:
    // O(n) – Recursive stack takes linear space

    // -------------------------------------------------------------------
    // Optimal Approach 2: Java 8 Streams for concise check
    static boolean isSortedStream(int[] arr) {
        // Stream checks that all adjacent pairs are sorted
        return java.util.stream.IntStream.range(0, arr.length - 1)
                .allMatch(i -> arr[i] <= arr[i + 1]);
    }
    // Time Complexity:
    // O(n) – Stream internally loops once
    // Total Time Complexity: O(n)
    // Space Complexity:
    // O(1) – Stream is internally optimized with constant overhead

    // -------------------------------------------------------------------
    // Wrapper method to print result of each check
    static void checkSorted(String methodName, boolean result) {
        System.out.println(methodName + ": " +
                (result ? "Array is sorted." : "Array is NOT sorted."));
    }

    // -------------------------------------------------------------------
    // Main method to test all implementations
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
