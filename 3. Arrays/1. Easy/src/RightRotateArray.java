public class RightRotateArray {

    // ----------------------------------------------------------
    // Method 1: Right Rotate by ONE Place
    // Time Complexity: O(n)
    // - Shift all elements once.
    // Space Complexity: O(1)
    // - Only temporary variable used.
    // ----------------------------------------------------------
    public static void rotateByOne(int[] arr) {
        int n = arr.length;
        if (n == 0) return;

        int temp = arr[n - 1]; // Store last element

        // Shift elements right by one
        for (int i = n - 2; i >= 0; i--) {
            arr[i + 1] = arr[i];
        }

        arr[0] = temp; // Place last element at front
    }

    // ----------------------------------------------------------
    // Method 2: Right Rotate by d using TEMP ARRAY
    // Time Complexity: O(n)
    // - Copy d elements + shift rest + copy back d elements.
    // Space Complexity: O(d)
    // - Extra array of size d.
    // ----------------------------------------------------------
    public static void rotateByDTemp(int[] arr, int d) {
        int n = arr.length;
        if (n == 0 || d == 0) return;

        d = d % n; // Handle d > n

        // Store last d elements in temp
        int[] temp = new int[d];
        for (int i = 0; i < d; i++) {
            temp[i] = arr[n - d + i];
        }

        // Shift rest of the elements right by d
        for (int i = n - d - 1; i >= 0; i--) {
            arr[i + d] = arr[i];
        }

        // Copy temp elements to start
        for (int i = 0; i < d; i++) {
            arr[i] = temp[i];
        }
    }

    // ----------------------------------------------------------
    // Helper Method: Reverse a sub-array from start to end
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
    // Time Complexity: O(n)
    // - Three reversals, total linear time.
    // Space Complexity: O(1)
    // - In-place reversals, no extra space.
    // ----------------------------------------------------------
    public static void rotateByDReversal(int[] arr, int d) {
        int n = arr.length;
        if (n == 0 || d == 0) return;

        d = d % n;

        // Reverse last d elements
        reverse(arr, n - d, n - 1);
        // Reverse first n-d elements
        reverse(arr, 0, n - d - 1);
        // Reverse entire array
        reverse(arr, 0, n - 1);
    }

    // ----------------------------------------------------------
    // Main Method: Test all rotations
    // ----------------------------------------------------------
    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5, 6, 7};
        int[] onePlace = original.clone();
        int[] dTemp = original.clone();
        int[] dRev = original.clone();
        int d = 3;

        // Rotate by one place
        rotateByOne(onePlace);
        System.out.print("Right Rotate by 1 Place: ");
        for (int num : onePlace) System.out.print(num + " ");
        System.out.println();

        // Rotate by d places using temp array
        rotateByDTemp(dTemp, d);
        System.out.print("Right Rotate by " + d + " Places (Temp): ");
        for (int num : dTemp) System.out.print(num + " ");
        System.out.println();

        // Rotate by d places using reversal algorithm
        rotateByDReversal(dRev, d);
        System.out.print("Right Rotate by " + d + " Places (Reversal): ");
        for (int num : dRev) System.out.print(num + " ");
        System.out.println();
    }
}
