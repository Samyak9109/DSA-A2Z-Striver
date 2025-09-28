public class FindPeakElement {
    /*
      Time Complexity: O(log n) --> Binary search halves the search space each iteration
      Space Complexity: O(1) --> Constant extra space
    */
    static int findPeakIndex(int[] nums) {
        int n = nums.length;

        // Edge cases
        if (n == 1) return 0; // only one element
        if (nums[0] > nums[1]) return 0; // peak at start
        if (nums[n - 1] > nums[n - 2]) return n - 1; // peak at end

        int low = 1, high = n - 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if mid is peak
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid; // return index of peak
            }

            // Move toward the larger neighbor
            if (nums[mid] < nums[mid + 1]) {
                low = mid + 1; // peak is on the right
            } else {
                high = mid - 1; // peak is on the left
            }
        }

        return -1; // fallback, should never happen
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 20, 4, 1, 0};
        int[] nums2 = {5, 10, 20, 15};

        System.out.println("Peak index in nums1: " + findPeakIndex(nums1)); // Output: 2
        System.out.println("Peak index in nums2: " + findPeakIndex(nums2)); // Output: 2
    }
}
