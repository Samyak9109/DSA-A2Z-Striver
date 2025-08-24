public class BubbleSort {

    /**
     * Iterative Bubble Sort
     *
     * Time Complexity:
     * - Best Case: O(n) when array is already sorted (due to early termination).
     * - Worst Case: O(n^2) when array is reverse sorted.
     * - Average Case: O(n^2) for random order.
     *
     * Space Complexity: O(1) - in-place sorting.
     */
    static void bubbleSort(int[] arr, int n) {
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }
            }

            if (!swapped) {
                // Early termination - no swaps means sorted
                System.out.println("Early termination after " + (i + 1) + " passes");
                break;
            }
        }
    }

    /**
     * Recursive Bubble Sort
     *
     * Time Complexity:
     * - Best, Worst, Average Case: O(n^2)
     *   (No early termination optimization)
     *
     * Space Complexity: O(n) due to recursion call stack.
     */
    static void bubbleSortRecursive(int[] arr, int n) {
        if (n == 1) return;

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }

        bubbleSortRecursive(arr, n - 1);
    }

    // Utility method to print array elements
    static void printArray(int[] arr) {
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        // Best case (Iterative)
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println("Iterative Bubble Sort - Already Sorted Array:");
        bubbleSort(sortedArray, sortedArray.length);
        printArray(sortedArray);

        // Worst case (Recursive)
        int[] reverseArray = {8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println("\nRecursive Bubble Sort - Reverse Sorted Array:");
        bubbleSortRecursive(reverseArray, reverseArray.length);
        printArray(reverseArray);

        // Average case (Iterative)
        int[] randomArray = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("\nIterative Bubble Sort - Random Array:");
        bubbleSort(randomArray, randomArray.length);
        printArray(randomArray);
    }
}
