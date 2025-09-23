public class LargestElement {

    // ----------------------------------------------------------
    // Approach: Single Traversal
    // ----------------------------------------------------------
    // Description:
    // Finds the largest element in the array.
    //
    // Time Complexity: O(n)
    // - Single pass through the array.
    //
    // Space Complexity: O(1)
    // - Uses only one variable to track the largest element.
    // ----------------------------------------------------------
    static int getLargest(int[] arr) {
        int largest = arr[0];  // Initialize largest with the first element

        for (int var : arr) {
            // Update largest if current element is greater
            if (var > largest) largest = var;
        }

        return largest;
    }

    // ----------------------------------------------------------
    // Main Method: Test the function
    // ----------------------------------------------------------
    public static void main(String[] args) {
        int[] numbers = {12, 45, 2, 67, 34};

        System.out.println("Largest element: " + getLargest(numbers));
        // Expected output: 67
        System.out.println("Time Complexity of getLargest(): O(n)");
        System.out.println("Space Complexity of getLargest(): O(1)");
    }
}
