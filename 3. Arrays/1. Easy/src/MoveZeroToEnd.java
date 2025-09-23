import java.util.ArrayList;
import java.util.Arrays;

public class MoveZeroToEnd {

    // ----------------------------------------------------------
    // Brute Force Method using ArrayList
    // Approach: Collect all non-zero elements in a list, then fill
    // the array first with non-zero elements and remaining with zeros.
    // Time Complexity: O(n)
    // Space Complexity: O(n) – Extra ArrayList holds non-zero elements
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

        // Fill remaining positions with zeros
        for (int i = nz; i < arr.length; i++) {
            arr[i] = 0;
        }
    }

    // ----------------------------------------------------------
    // Optimal Method using in-place two-pointer technique
    // Approach: Maintain a pointer for next non-zero placement.
    // Traverse the array, swap current non-zero with pointer position.
    // Time Complexity: O(n) – Single pass
    // Space Complexity: O(1) – In-place, no extra memory
    // ----------------------------------------------------------
    static void moveZeroOptimal(int[] arr) {
        int index = 0; // Next position for non-zero element

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                // Swap only if needed
                if (i != index) {
                    int temp = arr[i];
                    arr[i] = arr[index];
                    arr[index] = temp;
                }
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
