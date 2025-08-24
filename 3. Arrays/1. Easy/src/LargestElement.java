public class LargestElement {

    // ----------------------------------------------------------
    // Method: Find the largest element in the array
    // Time Complexity: O(n)
    // - One pass through the array to find the largest element.
    //
    // Space Complexity: O(1)
    // - Only a few variables used, constant space.
    // ----------------------------------------------------------
    static int getLargest(int[] arr) {
        int largest = arr[0];  // Initialize largest as first element

        for (int var : arr) {
            if (var > largest) largest = var;  // Update largest if current is bigger
        }

        return largest;
    }

    // ----------------------------------------------------------
    // Main method to test getLargest
    // ----------------------------------------------------------
    public static void main(String[] args) {
        int[] numbers = {12, 45, 2, 67, 34};

        System.out.println("Largest element: " + getLargest(numbers));
        System.out.println("Time Complexity of getLargest(): O(n)");
    }
}
