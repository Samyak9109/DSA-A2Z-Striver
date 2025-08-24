public class InsertionSort {

    /**
     * First implementation of insertion sort using swaps
     *
     * Time Complexity:
     * - Best Case: O(n) when the array is already sorted (minimal swaps).
     * - Worst Case: O(n^2) when the array is reverse sorted (max swaps).
     * - Average Case: O(n^2).
     *
     * Space Complexity: O(1) — in-place sorting.
     */
    static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i <= n - 1; i++) {
            int j = i;
            while (j > 0 && arr[j - 1] > arr[j]) {
                // Swap arr[j] and arr[j - 1]
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }

    /**
     * Alternative insertion sort implementation using a key
     *
     * Time Complexity:
     * - Best Case: O(n) when already sorted.
     * - Worst Case: O(n^2).
     * - Average Case: O(n^2).
     *
     * Space Complexity: O(1).
     */
    static void insertionSortAlternative(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Shift elements greater than key to the right
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Insert key at correct position
            arr[j + 1] = key;
        }
    }

    // Utility to print array elements
    static void printArray(int[] arr) {
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {12, 11, 13, 5, 6};
        System.out.println("Original array:");
        printArray(arr1);

        insertionSort(arr1);
        System.out.println("Sorted array (original implementation):");
        printArray(arr1);

        int[] arr2 = {12, 11, 13, 5, 6};
        insertionSortAlternative(arr2);
        System.out.println("Sorted array (alternative implementation):");
        printArray(arr2);

        int[] sortedArr = {1, 2, 3, 4, 5};
        long startTime = System.nanoTime();
        insertionSort(sortedArr);
        long endTime = System.nanoTime();
        System.out.println("Time taken for sorted array (best case): " + (endTime - startTime) + " ns");

        int[] reverseArr = {5, 4, 3, 2, 1};
        startTime = System.nanoTime();
        insertionSort(reverseArr);
        endTime = System.nanoTime();
        System.out.println("Time taken for reverse sorted array (worst case): " + (endTime - startTime) + " ns");
    }
}
