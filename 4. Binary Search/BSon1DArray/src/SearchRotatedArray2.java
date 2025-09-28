/*
 * Problem: Search in Rotated Sorted Array II (with duplicates allowed)
 *
 * Brute Force:
 *   - Just linearly scan through the array.
 *   - Time Complexity: O(n) → check every element.
 *   - Space Complexity: O(1) → no extra space.
 *
 * Better:
 *   - Not really much better than brute force because duplicates mess up binary search partitions.
 *   - Can only optimize slightly by checking mid directly and trimming edges.
 *
 * Optimal (your code):
 *   - Modified Binary Search that skips duplicates when nums[low] == nums[mid] == nums[high].
 *   - If left half is sorted, check if target lies in it.
 *   - Else check right half.
 *   - Time Complexity: Worst Case → O(n) (when array has lots of duplicates like [2,2,2,2,2]).
 *                       Average Case → O(log n).
 *   - Space Complexity: O(1).
 */

public class SearchRotatedArray2 {

    // 🚩 Brute Force Approach
    static boolean bruteForceSearch(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) return true; // found target
        }
        return false; // not found
    }

    // 🚀 Optimal Approach (Modified Binary Search with duplicates handling)
    static boolean search(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Found the element
            if (nums[mid] == target) return true;

            // If we can't decide because of duplicates, shrink the window
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
                continue;
            }

            // Left half is sorted
            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1; // target lies in left half
                } else {
                    low = mid + 1; // target lies in right half
                }
            }
            // Right half is sorted
            else {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1; // target lies in right half
                } else {
                    high = mid - 1; // target lies in left half
                }
            }
        }
        return false; // not found
    }

    // 🎯 Main method for testing
    public static void main(String[] args) {
        int[] nums = {2,5,6,0,0,1,2};
        int target1 = 0;
        int target2 = 3;

        // Brute force check
        System.out.println("Brute Force: Target " + target1 + " found? " + bruteForceSearch(nums, target1));
        System.out.println("Brute Force: Target " + target2 + " found? " + bruteForceSearch(nums, target2));

        // Optimal binary search check
        System.out.println("Optimal: Target " + target1 + " found? " + search(nums, target1));
        System.out.println("Optimal: Target " + target2 + " found? " + search(nums, target2));
    }
}
