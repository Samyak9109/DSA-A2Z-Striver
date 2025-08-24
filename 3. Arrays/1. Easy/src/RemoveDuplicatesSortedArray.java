public class RemoveDuplicatesSortedArray {

    // ----------------------------------------------------------
    // Method 1: Brute Force Approach
    // Time Complexity: O(n^2)
    // - Nested loops to find and shift duplicates.
    // Space Complexity: O(1)
    // - No extra space used.
    // ----------------------------------------------------------
    public static int removeDuplicatesBrute(int[] nums) {
        int size = nums.length;
        if (size == 0) return 0;

        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; ) {
                if (nums[i] == nums[j]) {
                    // Shift elements left to remove duplicate
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
    // Time Complexity: O(n)
    // - Single pass to collect unique elements.
    // Space Complexity: O(n)
    // - Uses temporary array.
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

        // Copy unique elements back to original array
        for (int i = 0; i < j; i++) {
            nums[i] = temp[i];
        }

        return j;
    }

    // ----------------------------------------------------------
    // Method 3: Optimal Two-Pointer Approach (In-place)
    // Time Complexity: O(n)
    // - Single traversal using two pointers.
    // Space Complexity: O(1)
    // - No extra space.
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
    // Helper method: Print array up to given length
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // ----------------------------------------------------------
    public static void printArray(int[] arr, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // ----------------------------------------------------------
    // Main method: Tests all three approaches
    // ----------------------------------------------------------
    public static void main(String[] args) {
        int[] original = {1, 1, 2, 2, 3, 4, 4, 5};

        // Test Brute Force
        int[] arr1 = original.clone();
        int newLength1 = removeDuplicatesBrute(arr1);
        System.out.print("Brute Force Output (O(n^2)): ");
        printArray(arr1, newLength1);

        // Test Better
        int[] arr2 = original.clone();
        int newLength2 = removeDuplicatesBetter(arr2);
        System.out.print("Better Approach Output (O(n), Extra Space): ");
        printArray(arr2, newLength2);

        // Test Optimal
        int[] arr3 = original.clone();
        int newLength3 = removeDuplicatesOptimal(arr3);
        System.out.print("Optimal Approach Output (O(n), In-place): ");
        printArray(arr3, newLength3);
    }
}
