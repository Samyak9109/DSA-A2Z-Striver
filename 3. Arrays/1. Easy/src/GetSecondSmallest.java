public class GetSecondSmallest {

    // -----------------------------------------------------------------------
    // Approach: Single Traversal
    // -----------------------------------------------------------------------
    // Description:
    // Find the second smallest non-negative element in the array.
    // Ignores negative numbers.
    //
    // Time Complexity: O(n)
    // - Only one pass through the array.
    //
    // Space Complexity: O(1)
    // - Uses only two variables for smallest and second smallest.
    // -----------------------------------------------------------------------
    static int getSecondSmallest(int[] arr) {
        int smallest = Integer.MAX_VALUE;       // Initialize smallest
        int secondSmallest = Integer.MAX_VALUE; // Initialize second smallest

        for (int var : arr) {
            if (var < 0) continue; // Ignore negative numbers

            if (var < smallest) {
                secondSmallest = smallest; // Previous smallest becomes second smallest
                smallest = var;            // Update smallest
            } else if (var > smallest && var < secondSmallest) {
                secondSmallest = var;      // Update second smallest if in between
            }
        }

        // If no second smallest found, return -1
        return (secondSmallest == Integer.MAX_VALUE) ? -1 : secondSmallest;
    }

    // -----------------------------------------------------------------------
    // Main Method: Test the function
    // -----------------------------------------------------------------------
    public static void main(String[] args) {
        int[] numbers = {12, -5, 45, 2, 67, -9, 0};
        System.out.println("Second Smallest non-negative element: " + getSecondSmallest(numbers));
        // Expected output: 2 (smallest non-negative is 0, second smallest is 2)
    }
}
