public class RemoveDuplicatesSortedArray {

    // ----------------------------------------------------------
    // Method 1: Brute Force Approach
    // - Nested loops to find and shift duplicates
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    // ----------------------------------------------------------
    public static int removeDuplicatesBrute(int[] nums) {
        int size = nums.length;
        if (size == 0) return 0;

        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; ) {
                if (nums[i] == nums[j]) {
                    // Shift elements left
                    for (int k = j; k < size - 1; k++) {
                        nums[k] = nums[k + 1];
                    }
                    size--;
                } else {
                    j++;
                }
            }
        }
        return size;
    }

    // ----------------------------------------------------------
    // Method 2: Better Approach using Extra Space
    // - Use a temporary array to store unique elements
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    // ----------------------------------------------------------
    public static int removeDuplicatesBetter(int[] nums) {
        if (nums.length == 0) return 0;

        int[] temp = new int[nums.length];
        int j = 0;
        temp[j++] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                temp[j++] = nums[i];
            }
        }

        // Copy back
        for (int i = 0; i < j; i++) {
            nums[i] = temp[i];
        }

        return j;
    }

    // ----------------------------------------------------------
    // Method 3: Optimal Two-Pointer Approach (In-place)
    // - Single traversal using slow & fast pointers
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // ----------------------------------------------------------
    public static int removeDuplicatesOptimal(int[] nums) {
        if (nums.length == 0) return 0;

        int i = 0; // slow pointer
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1; // new length
    }

    // ----------------------------------------------------------
    // Helper: Print array up to given length
    // ----------------------------------------------------------
    public static void printArray(int[] arr, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // ----------------------------------------------------------
    // Main method: Test all three approaches
    // ----------------------------------------------------------
    public static void main(String[] args) {
        int[] original = {1, 1, 2, 2, 3, 4, 4, 5};

        int[] arr1 = original.clone();
        int len1 = removeDuplicatesBrute(arr1);
        System.out.print("Brute Force Output (O(n^2)): ");
        printArray(arr1, len1);

        int[] arr2 = original.clone();
        int len2 = removeDuplicatesBetter(arr2);
        System.out.print("Better Approach Output (O(n), Extra Space): ");
        printArray(arr2, len2);

        int[] arr3 = original.clone();
        int len3 = removeDuplicatesOptimal(arr3);
        System.out.print("Optimal Approach Output (O(n), In-place): ");
        printArray(arr3, len3);
    }
}
