public class BinarySearchFindX {

    /**
     * ITERATIVE BINARY SEARCH
     * ------------------------
     * Time Complexity: O(log n)
     *   - Each step halves the search space.
     * Space Complexity: O(1)
     *   - Only uses a few variables.
     */
    static int searchIterative(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // overflow-safe

            if (nums[mid] == target) return mid; // found
            else if (target > nums[mid]) low = mid + 1; // search right
            else high = mid - 1; // search left
        }
        return -1; // not found
    }

    /**
     * RECURSIVE BINARY SEARCH
     * ------------------------
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) due to recursion stack.
     */
    static int searchRecursive(int[] nums, int low, int high, int target) {
        if (low > high) return -1; // base case

        int mid = low + (high - low) / 2;

        if (nums[mid] == target) return mid; // found
        else if (target > nums[mid])
            return searchRecursive(nums, mid + 1, high, target); // right
        else
            return searchRecursive(nums, low, mid - 1, target); // left
    }

    /**
     * First Occurrence (Iterative)
     */
    static int firstOccurrence(int[] nums, int target) {
        int low = 0, high = nums.length - 1, result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                result = mid;
                high = mid - 1; // move left
            } else if (target > nums[mid]) low = mid + 1;
            else high = mid - 1;
        }
        return result;
    }

    /**
     * Last Occurrence (Iterative)
     */
    static int lastOccurrence(int[] nums, int target) {
        int low = 0, high = nums.length - 1, result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                result = mid;
                low = mid + 1; // move right
            } else if (target > nums[mid]) low = mid + 1;
            else high = mid - 1;
        }
        return result;
    }

    /**
     * Count Occurrences
     */
    static int countOccurrences(int[] nums, int target) {
        int first = firstOccurrence(nums, target);
        if (first == -1) return 0; // not found
        int last = lastOccurrence(nums, target);
        return last - first + 1;
    }

    // Driver
    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,3,3,3,3,3,3,4,4,4,5,5,5};
        int target = 3;

        // Iterative
        System.out.println("Iterative search: " + searchIterative(arr, target));

        // Recursive
        System.out.println("Recursive search: " + searchRecursive(arr, 0, arr.length - 1, target));

        // First + Last + Count
        System.out.println("First occurrence: " + firstOccurrence(arr, target));
        System.out.println("Last occurrence: " + lastOccurrence(arr, target));
        System.out.println("Count occurrences: " + countOccurrences(arr, target));
    }
}
