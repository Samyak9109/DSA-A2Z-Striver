/*
--------------------------------------------------------
🚀 Problem: Determine the rotation count of a rotated
           sorted array by finding the index of the
           minimum element.
--------------------------------------------------------

1. Brute Force (Linear Scan)
   - Scan the whole array to locate the minimum element index.
   - Rotation count = min element index.
   - Time Complexity: O(n) → check every element.
   - Space Complexity: O(1) → only a few variables used.

2. Better Approach (Sort + Lookup)
   - Copy array, sort it, find minimum, locate its original index.
   - Time Complexity: O(n log n) → due to sorting.
   - Space Complexity: O(n) → storing a copy of array.
   - Overkill, but still works.

3. Optimal (Binary Search / Power Move)
   - Use modified binary search to pinpoint minimum index efficiently.
   - Time Complexity: O(log n) → halves search space each iteration.
   - Space Complexity: O(1) → in-place, no extra storage.
   - Min index = rotation count, super clean and scalable.
--------------------------------------------------------
*/

public class NumArrayIsRotated {

    // 🔹 Brute Force
    static int bruteForceRotation(int[] nums) {
        int n = nums.length;
        int minVal = Integer.MAX_VALUE;
        int minIdx = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] < minVal) {
                minVal = nums[i];
                minIdx = i;
            }
        }
        return minIdx; // rotation count
    }

    // 🔹 Better Approach (Sort + Lookup)
    static int betterRotation(int[] nums) {
        int n = nums.length;
        int[] copy = nums.clone(); // duplicate array
        java.util.Arrays.sort(copy); // sort it
        int minVal = copy[0]; // smallest element
        // locate original index
        for (int i = 0; i < n; i++) {
            if (nums[i] == minVal) return i;
        }
        return -1; // safety check
    }

    // 🔹 Optimal Approach (Binary Search / Power Move)
    static int optimalRotation(int[] nums) {
        int n = nums.length;
        int low = 0, high = n - 1;
        int index = -1;
        int minVal = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Case 1: subarray already sorted
            if (nums[low] <= nums[high]) {
                if (nums[low] < minVal) {
                    minVal = nums[low];
                    index = low;
                }
                break; // done
            }

            // Case 2: left half sorted
            if (nums[low] <= nums[mid]) {
                if (nums[low] < minVal) {
                    minVal = nums[low];
                    index = low;
                }
                low = mid + 1;
            }
            // Case 3: right half sorted
            else {
                if (nums[mid] < minVal) {
                    minVal = nums[mid];
                    index = mid;
                }
                high = mid - 1;
            }
        }
        return index; // rotation count
    }

    // 🏁 Main driver
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        System.out.println("Brute Force Rotation Count → " + bruteForceRotation(nums)); // 4
        System.out.println("Better Approach Rotation Count → " + betterRotation(nums)); // 4
        System.out.println("Optimal Rotation Count → " + optimalRotation(nums)); // 4
    }
}
