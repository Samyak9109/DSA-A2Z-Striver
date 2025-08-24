public class SelectionSort {

    /**
     * Performs selection sort on the array.
     *
     * Time Complexity:
     * - Best Case: O(n^2) — selection sort always scans the entire unsorted part to find the min element.
     * - Worst Case: O(n^2) — same as best, no optimization for already sorted arrays.
     * - Average Case: O(n^2)
     *
     * Space Complexity:
     * - O(1) — sorts in-place, no additional data structures used.
     *
     * @param arr the array to sort
     * @param n length of the array
     */
    // Function to perform selection sort on the array
    static void selectionSort(int[] arr, int n) {
        // Loop to move boundary of unsorted subarray
        for (int i = 0; i <= n - 2; i++) {

            // Assume the current element is the minimum
            int min = i;

            // Find the index of the smallest element in the remaining unsorted array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min]) {
                    min = j;  // Update index of the minimum element
                }
            }

            // Swap the found minimum element with the first element of the unsorted part
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        // Declare and initialize the array to be sorted
        int[] arr = {64, 25, 12, 22, 11};
        int n = arr.length;  // Length of the array

        // Print the original (unsorted) array
        System.out.println("Original array:");
        for (int val : arr) {
            System.out.print(val + " ");
        }

        // Call the selectionSort function
        selectionSort(arr, n);

        // Print the sorted array
        System.out.println("\nSorted array:");
        for (int val : arr) {
            System.out.print(val + " ");
        }
    }
}
