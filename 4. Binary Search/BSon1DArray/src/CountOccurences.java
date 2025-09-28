public class CountOccurences {

    /**
     * Count how many times target appears in a sorted array.
     *
     * Time Complexity:
     * - findFirst(): O(log n)
     * - findLast(): O(log n)
     * - Overall: O(log n)
     *
     * Space Complexity:
     * - O(1), just variables
     */
    public int count(int[] nums, int target) {
        int firstOccurence = findFirst(nums, target);
        int lastOccurence = findLast(nums, target);

        // if element is not found
        if (firstOccurence == -1 || lastOccurence == -1) {
            return 0;
        }

        // count = difference + 1
        return lastOccurence - firstOccurence + 1;
    }

    // Binary Search for first occurrence
    private int findFirst(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                ans = mid;       // record answer
                high = mid - 1;  // search left
            } else if (nums[mid] < target) {
                low = mid + 1;   // go right
            } else {
                high = mid - 1;  // go left
            }
        }
        return ans;
    }

    // Binary Search for last occurrence
    private int findLast(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                ans = mid;      // record answer
                low = mid + 1;  // search right
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // Quick driver to test
    public static void main(String[] args) {
        CountOccurences obj = new CountOccurences();

        int[] nums = {2, 4, 4, 4, 6, 7, 7, 9};
        int target = 4;

        int result = obj.count(nums, target);
        System.out.println("Target " + target + " occurs " + result + " times.");
        // Expected: Target 4 occurs 3 times.
    }
}
