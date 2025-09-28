public class SingleElementInArray {
    /*
      Time Complexity: O(log n) --> Binary search
      Space Complexity: O(1) --> Constant space
    */
    static int singleNonDuplicate(int[] nums) {
        int n = nums.length;

        // Edge case: single element array
        if (n == 1) return nums[0];

        // Check first and last elements
        if (nums[0] != nums[1]) return nums[0];
        if (nums[n - 1] != nums[n - 2]) return nums[n - 1];

        int low = 1, high = n - 2; // skip first and last elements

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if mid is the single element
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }

            // Decide which side to search based on pair pattern
            if ((mid % 2 == 0 && nums[mid] == nums[mid + 1]) ||
                    (mid % 2 == 1 && nums[mid] == nums[mid - 1])) {
                low = mid + 1; // single element is on the right
            } else {
                high = mid - 1; // single element is on the left
            }
        }

        return -1; // fallback, should never happen
    }

    public static void main(String[] args) {
        int[] nums1 = {1}; // single-element array
        int[] nums2 = {1,1,2,2,3}; // normal case

        System.out.println(singleNonDuplicate(nums1)); // ✅ Output: 1
        System.out.println(singleNonDuplicate(nums2)); // ✅ Output: 3
    }
}
