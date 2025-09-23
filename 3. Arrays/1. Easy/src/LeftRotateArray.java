public class LeftRotateArray {

    // ----------------------------------------------------------
    // Approach 1: Rotate by ONE place
    // Description:
    // Shift all elements left by 1 and move the first element to the end.
    // Time Complexity: O(n) – single pass to shift elements
    // Space Complexity: O(1) – constant extra space
    // ----------------------------------------------------------
    public static void rotateByOne(int[] arr) {
        int n = arr.length;
        if (n == 0) return; // Handle empty array

        int temp = arr[0]; // Store first element

        // Shift all elements left by 1
        for (int i = 1; i < n; i++) {
            arr[i - 1] = arr[i];
        }

        arr[n - 1] = temp; // Place first element at the end
    }

    // ----------------------------------------------------------
    // Approach 2: Rotate by d using TEMP ARRAY
    // Description:
    // Store first d elements in a temporary array, shift remaining left,
    // then copy temp elements to the end.
    // Time Complexity: O(n) – linear pass for shifting and copying
    // Space Complexity: O(d) – temp array of size d
    // ----------------------------------------------------------
    public static void rotateByDTemp(int[] arr, int d) {
        int n = arr.length;
        if (n == 0 || d == 0) return;

        d = d % n; // Handle d > n

        // Store first d elements
        int[] temp = new int[d];
        for (int i = 0; i < d; i++) {
            temp[i] = arr[i];
        }

        // Shift remaining elements left by d
        for (int i = d; i < n; i++) {
            arr[i - d] = arr[i];
        }

        // Copy temp elements to the end
        for (int i = 0; i < d; i++) {
            arr[n - d + i] = temp[i];
        }
    }

    // ----------------------------------------------------------
    // Helper Method: Reverse a portion of the array
    // Time Complexity: O(k) – where k = end - start + 1
    // Space Complexity: O(1) – in-place swapping
    // ----------------------------------------------------------
    public static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // ----------------------------------------------------------
    // Approach 3: Rotate by d using REVERSAL ALGORITHM
    // Description:
    // 1. Reverse first d elements
    // 2. Reverse remaining elements
    // 3. Reverse the whole array
    // Time Complexity: O(n) – three linear passes
    // Space Complexity: O(1) – in-place reversals
    // ----------------------------------------------------------
    public static void rotateByDReversal(int[] arr, int d) {
        int n = arr.length;
        if (n == 0 || d == 0) return;

        d = d % n;

        reverse(arr, 0, d - 1);  // Step 1
        reverse(arr, d, n - 1);  // Step 2
        reverse(arr, 0, n - 1);  // Step 3
    }

    // ----------------------------------------------------------
    // Main Method: Test All Rotations
    // ----------------------------------------------------------
    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5, 6, 7};
        int[] onePlace = original.clone();
        int[] dTemp = original.clone();
        int[] dRev = original.clone();
        int d = 3;

        // Rotate by One
        rotateByOne(onePlace);
        System.out.print("Left Rotate by 1 Place: ");
        for (int num : onePlace) System.out.print(num + " ");
        System.out.println();

        // Rotate by d using Temp Array
        rotateByDTemp(dTemp, d);
        System.out.print("Left Rotate by " + d + " Places (Temp): ");
        for (int num : dTemp) System.out.print(num + " ");
        System.out.println();

        // Rotate by d using Reversal Algorithm
        rotateByDReversal(dRev, d);
        System.out.print("Left Rotate by " + d + " Places (Reversal): ");
        for (int num : dRev) System.out.print(num + " ");
        System.out.println();
    }
}
