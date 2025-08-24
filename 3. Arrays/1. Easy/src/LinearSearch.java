public class LinearSearch {

    // ----------------------------------------------------------
    // Method: Basic Linear Search
    // Description: Returns the index of the target element in the array.
    //
    // Time Complexity: O(n)
    // - Worst case: scan entire array if target not found or at end.
    // - Average case: also linear due to sequential search.
    //
    // Space Complexity: O(1)
    // - Uses only a few variables; no extra data structures.
    // ----------------------------------------------------------
    static int linearSearch(int target, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i; // Return index if found
        }
        return -1; // Target not found
    }

    // ----------------------------------------------------------
    // Main method to test the linearSearch function
    // ----------------------------------------------------------
    public static void main(String[] args) {
        int[] arr = {3, 7, 1, 9, 4, 10};
        int target = 9;

        int index = linearSearch(target, arr);
        if (index != -1) {
            System.out.println("Element " + target + " found at index: " + index);
        } else {
            System.out.println("Element " + target + " not found.");
        }
    }
}
