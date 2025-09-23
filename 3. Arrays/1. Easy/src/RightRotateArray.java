public class RightRotateArray {

    // ----------------------------------------------------------
    // Method 1: Right Rotate by ONE Place
    // Time Complexity: O(n) - shift all elements once
    // Space Complexity: O(1) - only a temp variable
    // ----------------------------------------------------------
    public static void rotateByOne(int[] arr) {
        int n = arr.length;
        if (n == 0) return;

        int temp = arr[n - 1]; // store last element
        for (int i = n - 2; i >= 0; i--) {
            arr[i + 1] = arr[i]; // shift elements right
        }
        arr[0] = temp; // move last element to front
    }

    // ----------------------------------------------------------
    // Method 2: Right Rotate by d using TEMP ARRAY
    // Time Complexity: O(n) - linear shifts + copy
    // Space Complexity: O(d) - temp array of size d
    // ----------------------------------------------------------
    public static void rotateByDTemp(int[] arr, int d) {
        int n = arr.length;
        if (n == 0 || d == 0) return;

        d = d % n; // handle d > n

        int[] temp = new int[d];
        for (int i = 0; i < d; i++) {
            temp[i] = arr[n - d + i]; // store last d elements
        }

        // shift remaining elements right by d
        for (int i = n - d - 1; i >= 0; i--) {
            arr[i + d] = arr[i];
        }

        // copy temp to start
        for (int i = 0; i < d; i++) {
            arr[i] = temp[i];
        }
    }

    // ----------------------------------------------------------
    // Helper: Reverse a sub-array in-place
    // Time Complexity: O(end - start + 1)
    // Space Complexity: O(1)
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
    // Method 3: Right Rotate by d using REVERSAL ALGORITHM
    // Time Complexity: O(n) - three linear reversals
    // Space Complexity: O(1) - in-place
    // ----------------------------------------------------------
    public static void rotateByDReversal(int[] arr, int d) {
        int n = arr.length;
        if (n == 0 || d == 0) return;

        d = d % n;

        // Step 1: reverse last d elements
        reverse(arr, n - d, n - 1);
        // Step 2: reverse first n-d elements
        reverse(arr, 0, n - d - 1);
        // Step 3: reverse entire array
        reverse(arr, 0, n - 1);
    }

    // ----------------------------------------------------------
    // Main method: Test all rotations
    // ----------------------------------------------------------
    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5, 6, 7};
        int[] onePlace = original.clone();
        int[] dTemp = original.clone();
        int[] dRev = original.clone();
        int d = 3;

        rotateByOne(onePlace);
        System.out.print("Right Rotate by 1 Place: ");
        for (int num : onePlace) System.out.print(num + " ");
        System.out.println();

        rotateByDTemp(dTemp, d);
        System.out.print("Right Rotate by " + d + " Places (Temp): ");
        for (int num : dTemp) System.out.print(num + " ");
        System.out.println();

        rotateByDReversal(dRev, d);
        System.out.print("Right Rotate by " + d + " Places (Reversal): ");
        for (int num : dRev) System.out.print(num + " ");
        System.out.println();
    }
}
