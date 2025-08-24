import java.util.*;

public class QuickSort {

    /**
     * Partition the list around a pivot (chosen as the first element)
     * so that elements <= pivot are on the left, and > pivot on the right.
     *
     * Time Complexity: O(high - low + 1)
     * - Each element is compared at most once during partitioning.
     *
     * Space Complexity: O(1)
     * - In-place partitioning with no extra arrays, only constant extra space.
     */
    static int partition(List<Integer> arr, int low, int high) {
        int pivot = arr.get(low); // Pivot is first element
        int i = low + 1;          // Pointer starting just after pivot
        int j = high;             // Pointer starting at the end

        while (i <= j) {
            // Move i right while elements are <= pivot
            while (i <= high && arr.get(i) <= pivot) {
                i++;
            }
            // Move j left while elements are > pivot
            while (j >= low && arr.get(j) > pivot) {
                j--;
            }
            // Swap elements at i and j if i < j
            if (i < j) {
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }

        // Place pivot in the correct sorted position by swapping with element at j
        int temp = arr.get(low);
        arr.set(low, arr.get(j));
        arr.set(j, temp);

        // Return pivot index after partitioning
        return j;
    }

    /**
     * Recursive QuickSort function to sort subarrays between low and high.
     *
     * Time Complexity:
     * - Best/Average Case: O(n log n)
     *   * Each partition splits array roughly in half.
     *   * log n levels of recursion with n work per level.
     * - Worst Case: O(n^2)
     *   * Occurs when pivot choices are poor (e.g., sorted array, pivot always smallest or largest).
     *
     * Space Complexity:
     * - O(log n) average due to recursion stack.
     * - O(n) worst case if recursion tree is skewed.
     */
    static void quickSort(List<Integer> arr, int low, int high) {
        if (low < high) {
            int pIndex = partition(arr, low, high);

            quickSort(arr, low, pIndex - 1);
            quickSort(arr, pIndex + 1, high);
        }
    }

    /**
     * Entry function to start QuickSort on the entire list.
     * Returns the sorted list for convenience.
     */
    public static List<Integer> quickSort(List<Integer> arr) {
        quickSort(arr, 0, arr.size() - 1);
        return arr;
    }

    // Main method for testing the QuickSort
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(Arrays.asList(4, 6, 2, 5, 7, 9, 1, 3));
        int n = arr.size();

        System.out.println("Before Using QuickSort:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr.get(i) + " ");
        }

        quickSort(arr);

        System.out.println("\nAfter Using QuickSort:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }
}
