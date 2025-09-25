public class FloorAndCeil {

    /**
     * FIND FLOOR
     * ----------------
     * Returns the largest element <= x in the array.
     * If no such element exists, returns -1.
     *
     * Time Complexity: O(log n) -> binary search halves search space each step
     * Space Complexity: O(1) -> only uses variables
     */
    static int findFloor(int[] arr, int n, int x) {
        int low = 0, high = n - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // safe mid calculation

            if (arr[mid] <= x) {
                ans = arr[mid];   // potential floor found
                low = mid + 1;    // search right for closer value to x
            } else {
                high = mid - 1;   // search left
            }
        }

        return ans;
    }

    /**
     * FIND CEIL
     * ----------------
     * Returns the smallest element >= x in the array.
     * If no such element exists, returns -1.
     *
     * Time Complexity: O(log n) -> binary search
     * Space Complexity: O(1)
     */
    static int findCeil(int[] arr, int n, int x) {
        int low = 0, high = n - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= x) {
                ans = arr[mid];   // potential ceil found
                high = mid - 1;   // search left for closer value to x
            } else {
                low = mid + 1;    // search right
            }
        }

        return ans;
    }

    /**
     * GET FLOOR AND CEIL
     * ------------------
     * Returns an array containing [floor, ceil] of x in arr
     */
    public static int[] getFloorAndCeil(int[] arr, int n, int x) {
        int f = findFloor(arr, n, x);
        int c = findCeil(arr, n, x);
        return new int[] {f, c};
    }

    // MAIN METHOD: testing examples
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int[] targets = {0, 2, 5, 6, 10}; // multiple targets

        System.out.println("Array: " + java.util.Arrays.toString(nums));
        for (int x : targets) {
            int[] fc = getFloorAndCeil(nums, nums.length, x);
            System.out.println("Target " + x + " -> Floor: " + fc[0] + ", Ceil: " + fc[1]);
        }
    }
}
