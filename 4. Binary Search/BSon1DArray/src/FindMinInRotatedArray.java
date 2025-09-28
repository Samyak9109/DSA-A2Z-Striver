/*
   Problem: Find the Minimum in a Rotated Sorted Array (No Duplicates)

   Approaches in this file:
   ---------------------------------------------------------
   1. findMin        → Binary Search, always explores till done
   2. findMinOptimal2 → Binary Search with early-exit if subarray sorted

   Time Complexity:
   - Both → O(log n) because we halve the search space every step
   - Worst case (no rotation): early-exit in Optimal2 is O(1)

   Space Complexity:
   - Both → O(1), just pointers and one answer variable
*/

public class FindMinInRotatedArray {

    // ---------------------------------------------------------
    // Binary Search (basic version, keeps checking both halves)
    // Always moves left/right until range collapses
    static int findMin(int[] nums) {
        int n = nums.length;
        int low = 0, high = n - 1;
        int ans = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Left part sorted
            if (nums[low] <= nums[mid]) {
                ans = Math.min(ans, nums[low]);
                low = mid + 1; // search right
            }
            // Right part sorted
            else {
                ans = Math.min(ans, nums[mid]);
                high = mid - 1; // search left
            }
        }
        return ans;
    }

    // ---------------------------------------------------------
    // Binary Search (optimized version with early exit)
    // If current subarray is sorted, no need to dig further
    static int findMinOptimal2(int[] nums) {
        int n = nums.length;
        int low = 0, high = n - 1;
        int ans = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Early exit: If range [low…high] already sorted
            if (nums[low] <= nums[high]) {
                ans = Math.min(ans, nums[low]);
                break;
            }

            // Left part sorted
            if (nums[low] <= nums[mid]) {
                ans = Math.min(ans, nums[low]);
                low = mid + 1;
            }
            // Right part sorted
            else {
                ans = Math.min(ans, nums[mid]);
                high = mid - 1;
            }
        }
        return ans;
    }

    // ---------------------------------------------------------
    // Test drive
    public static void main(String[] args) {
        int[] nums1 = {4,5,6,7,0,1,2};
        int[] nums2 = {11,13,15,17}; // already sorted
        int[] nums3 = {2,3,4,5,1};   // rotated

        System.out.println("findMin → " + findMin(nums1));
        System.out.println("findMinOptimal2 → " + findMinOptimal2(nums1));

        System.out.println("findMin → " + findMin(nums2));
        System.out.println("findMinOptimal2 → " + findMinOptimal2(nums2));

        System.out.println("findMin → " + findMin(nums3));
        System.out.println("findMinOptimal2 → " + findMinOptimal2(nums3));
    }
}
