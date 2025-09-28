/*
 🔍 Problem: Search in a Rotated Sorted Array
    We need to find the index of a target in a rotated sorted array.
    If it’s not there, return -1. Array has no duplicates.

-------------------------------------------------
📊 COMPLEXITY ANALYSIS:
Brute Force: O(n) time, O(1) space → check each element.
Better: O(log n + log n) = O(log n) time, O(1) space → find pivot + binary search.
Optimal: O(log n) time, O(1) space → single modified binary search.

-------------------------------------------------
*/

public class SearchRotatedArray {

    // ---------------- BRUTE FORCE ----------------
    // Just iterate and check
    // Time: O(n) because we scan entire array
    // Space: O(1) because no extra storage
    static int searchBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i; // found target
        }
        return -1; // not found
    }

    // ---------------- BETTER APPROACH ----------------
    // 1. Find the pivot (smallest element index).
    // 2. Binary search in correct half.
    // Time: O(log n) + O(log n) = O(log n)
    // Space: O(1)
    static int searchBetter(int[] nums, int target) {
        int n = nums.length;
        int pivot = findPivot(nums);

        // If array not rotated
        if (pivot == 0) return binarySearch(nums, 0, n - 1, target);

        // Decide which half to search
        if (target >= nums[pivot] && target <= nums[n - 1]) {
            return binarySearch(nums, pivot, n - 1, target); // right half
        } else {
            return binarySearch(nums, 0, pivot - 1, target); // left half
        }
    }

    // Helper: Find pivot index
    static int findPivot(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1; // pivot lies right
            } else {
                high = mid; // pivot lies left or is mid
            }
        }
        return low; // smallest element index
    }

    // Helper: Classic binary search
    static int binarySearch(int[] nums, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    // ---------------- OPTIMAL APPROACH ----------------
    // Do everything in one modified binary search
    // Time: O(log n)
    // Space: O(1)
    static int searchOptimal(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) return mid;

            // Check if left half is sorted
            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1; // search left
                } else {
                    low = mid + 1;  // search right
                }
            }
            // Otherwise right half is sorted
            else {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;  // search right
                } else {
                    high = mid - 1; // search left
                }
            }
        }
        return -1; // not found
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;

        System.out.println("Brute Force → " + searchBrute(nums, target));
        System.out.println("Better → " + searchBetter(nums, target));
        System.out.println("Optimal → " + searchOptimal(nums, target));
    }
}
