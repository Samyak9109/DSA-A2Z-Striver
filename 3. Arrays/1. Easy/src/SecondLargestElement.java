public class SecondLargestElement {

    /**
     * Returns the second-largest non-negative element in the array.
     * Ignores negative values.
     *
     * Time Complexity: O(n)
     * - Single pass through the array.
     *
     * Space Complexity: O(1)
     * - Uses fixed number of variables.
     *
     * @param arr Input integer array
     * @return second largest non-negative element or -1 if none exists
     */
    static int getSecondLargest(int[] arr) {
        int largest = -1;        // Track largest non-negative number found
        int secondLargest = -1;  // Track second largest non-negative number found

        for (int val : arr) {
            if (val < 0) continue; // Skip negative numbers

            if (val > largest) {
                // Update both largest and second largest
                secondLargest = largest;
                largest = val;
            } else if (val > secondLargest && val < largest) {
                // Update second largest only
                secondLargest = val;
            }
        }

        return secondLargest;
    }

    // Main method to test getSecondLargest
    public static void main(String[] args) {
        int[] numbers = {12, 45, 2, 67, 34};
        System.out.println("Second Largest element: " + getSecondLargest(numbers));
    }
}
