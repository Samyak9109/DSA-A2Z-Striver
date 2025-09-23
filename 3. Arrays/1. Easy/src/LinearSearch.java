public class LinearSearch {

    // ----------------------------------------------------------
    // Approach: Basic Linear Search
    // ----------------------------------------------------------
    // Description:
    // Sequentially checks each element in the array to find the target.
    // Returns the index of the first occurrence of the target.
    //
    // Time Complexity: O(n)
    // - Worst case: target is at the end or not present → scan all n elements.
    // - Best case: target is at the first element → O(1).
    //
    // Space Complexity: O(1)
    // - Uses constant extra space; no additional data structures.
    // ----------------------------------------------------------
    static int linearSearch(int target, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i; // Return index if target found
        }
        return -1; // Target not found
    }

    // ----------------------------------------------------------
    // Main Method: Test the linearSearch function
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
