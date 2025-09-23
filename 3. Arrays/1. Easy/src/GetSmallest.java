public class GetSmallest {

    // ----------------------------------------------------------
    // Approach: Single Traversal
    // ----------------------------------------------------------
    // Description:
    // Finds the smallest non-negative element in an array.
    // Ignores negative numbers.
    //
    // Time Complexity: O(n)
    // - Only one pass through the array.
    //
    // Space Complexity: O(1)
    // - Uses a constant number of variables.
    // ----------------------------------------------------------
    static int getSmallest(int[] arr) {
        int smallest = Integer.MAX_VALUE; // Initialize smallest
        boolean found = false;             // Flag to check if a non-negative element exists

        for (int var : arr) {
            // Consider only non-negative numbers
            if (var >= 0 && var < smallest) {
                smallest = var; // Update smallest
                found = true;   // Mark that we found a non-negative number
            }
        }

        // Return smallest if found, otherwise -1
        return found ? smallest : -1;
    }

    // ----------------------------------------------------------
    // Main Method: Test the function
    // ----------------------------------------------------------
    public static void main(String[] args) {
        int[] numbers = {12, -5, 45, 2, 67, -9, 0};

        System.out.println("Smallest non-negative element: " + getSmallest(numbers));
        // Expected output: 0 (smallest non-negative element)
        System.out.println("Time Complexity of getSmallest(): O(n)");
        System.out.println("Space Complexity of getSmallest(): O(1)");
    }
}
