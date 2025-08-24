import java.util.ArrayList;
import java.util.Scanner;

// Helper class containing merge sort methods
class Merge {

    /**
     * Merge two sorted halves of the array [low..mid] and [mid+1..high]
     *
     * Time Complexity: O(high - low + 1)
     * - We merge each element exactly once.
     *
     * Space Complexity: O(high - low + 1)
     * - Temporary ArrayList used to store merged elements.
     */
    public static void merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>();

        int left = low;
        int right = mid + 1;

        // Merge elements from both halves in sorted order
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
            }
        }

        // Copy remaining elements from left half (if any)
        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }

        // Copy remaining elements from right half (if any)
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }

        // Copy merged elements back into original array
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    /**
     * Recursive merge sort function to sort arr[low..high]
     *
     * Time Complexity: O(n log n)
     * - Array is divided into halves log n times.
     * - Merging takes O(n) time at each level.
     *
     * Space Complexity: O(n)
     * - Due to temporary ArrayList used during merging.
     */
    public static void mergeSort(int[] arr, int low, int high) {
        if (low >= high) return;

        int mid = (low + high) / 2;

        // Sort left half
        mergeSort(arr, low, mid);

        // Sort right half
        mergeSort(arr, mid + 1, high);

        // Merge sorted halves
        merge(arr, low, mid, high);
    }
}

// Main class with driver code
public class MergeSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 7;
        int[] arr = {9, 4, 7, 6, 3, 1, 5};

        System.out.println("Before sorting array:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        Merge.mergeSort(arr, 0, n - 1);

        System.out.println("After sorting array:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
