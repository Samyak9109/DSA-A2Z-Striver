import java.util.ArrayList;
import java.util.Arrays;

public class MoveZeroToEnd {

    // ----------------------------------------------------------
    // Brute Force Method using ArrayList
    // Time Complexity: O(n)
    // - One pass to collect non-zero elements,
    // - One pass to copy them back,
    // - One pass to fill zeros.
    // Space Complexity: O(n)
    // - Extra ArrayList holds non-zero elements.
    // ----------------------------------------------------------
    static void moveZero(int[] arr) {
        ArrayList<Integer> temp = new ArrayList<>();

        // Collect all non-zero elements
        for (int num : arr) {
            if (num != 0) temp.add(num);
        }

        int nz = temp.size();

        // Copy non-zero elements back to array
        for (int i = 0; i < nz; i++) {
            arr[i] = temp.get(i);
        }

        // Fill the rest with zeros
        for (int i = nz; i < arr.length; i++) {
            arr[i] = 0;
        }
    }


    // ----------------------------------------------------------
    // Optimal Method using in-place two-pointer technique
    // Time Complexity: O(n)
    // - Single traversal with constant-time swaps.
    // Space Complexity: O(1)
    // - No extra memory used.
    // ----------------------------------------------------------
    static void moveZeroOptimal(int[] arr) {
        int index = 0; // position to place next non-zero element

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                // Swap current element with element at 'index'
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
                index++;
            }
        }
    }

    // ----------------------------------------------------------
    // Main method to test both approaches
    // ----------------------------------------------------------
    public static void main(String[] args) {
        int[] arr1 = {0, 1, 0, 3, 12};
        int[] arr2 = {0, 1, 0, 3, 12};

        System.out.println("Before moving zeros (Brute):  " + Arrays.toString(arr1));
        moveZero(arr1);
        System.out.println("After moving zeros (Brute):   " + Arrays.toString(arr1));

        System.out.println("Before moving zeros (Optimal): " + Arrays.toString(arr2));
        moveZeroOptimal(arr2);
        System.out.println("After moving zeros (Optimal):  " + Arrays.toString(arr2));
    }
}
