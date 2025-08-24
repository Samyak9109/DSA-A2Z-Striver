public class GetSecondSmallest {

    // -----------------------------------------------------------------------
    // Method: getSecondSmallest
    // Description: Finds the second smallest non-negative element in an array.
    //
    // Time Complexity: O(n)
    // - Single traversal of the array.
    //
    // Space Complexity: O(1)
    // - Uses constant extra space.
    // -----------------------------------------------------------------------
    static int getSecondSmallest(int[] arr) {
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int var : arr) {
            if (var < 0) continue; // Ignore negative values

            if (var < smallest) {
                secondSmallest = smallest;  // update second smallest
                smallest = var;             // update smallest
            } else if (var > smallest && var < secondSmallest) {
                secondSmallest = var;       // update second smallest only if var is between smallest and secondSmallest
            }
        }

        // If no second smallest found, return -1
        return (secondSmallest == Integer.MAX_VALUE) ? -1 : secondSmallest;
    }

    // -----------------------------------------------------------------------
    // Main method for testing
    // -----------------------------------------------------------------------
    public static void main(String[] args) {
        int[] numbers = {12, -5, 45, 2, 67, -9, 0};
        System.out.println("Second Smallest non-negative element: " + getSecondSmallest(numbers));
        // Expected output: 2 (smallest non-negative is 0, second smallest is 2)
    }
}
