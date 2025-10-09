import java.util.*;

public class MedianOfTwoSortedArray {

    /*
     🎯 Problem: Given two sorted arrays nums1 and nums2, find the median of the combined sorted array.

     📍 LeetCode: #4 — Median of Two Sorted Arrays
     📍 GFG: Median of Two Sorted Arrays of Different Sizes
     📍 HackerRank: Median of Two Sorted Arrays (under Sorting/Array sections)
    */

    // ============================================================
    // 🥱 BRUTE FORCE APPROACH — Merge completely, then find median
    // ============================================================
    /*
     🔧 Idea:
     - Literally merge both arrays into one sorted array (like merge sort).
     - Find the median of the merged array.

     ⏱️ Time Complexity: O(n1 + n2)
         → We’re traversing both arrays once.
     💾 Space Complexity: O(n1 + n2)
         → We’re storing a new merged array.
    */
    static double bruteForceMedian(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[] merged = new int[n1 + n2];
        int i = 0, j = 0, k = 0;

        // Merge both arrays fully
        while (i < n1 && j < n2) {
            if (nums1[i] < nums2[j]) merged[k++] = nums1[i++];
            else merged[k++] = nums2[j++];
        }

        // Add remaining elements
        while (i < n1) merged[k++] = nums1[i++];
        while (j < n2) merged[k++] = nums2[j++];

        // Median calculation
        int n = merged.length;
        if (n % 2 == 1) return merged[n / 2];
        else return ((double) (merged[(n / 2) - 1] + merged[n / 2])) / 2.0;
    }

    // ============================================================
    // 🧩 BETTER APPROACH — Merge only till the median point
    // ============================================================
    /*
     🔧 Idea:
     - Instead of merging the entire array (which wastes effort),
       we only merge until we reach the median position.
     - We don’t actually need all elements to find the median.

     ⏱️ Time Complexity: O((n1 + n2) / 2)
         → We stop merging halfway.
     💾 Space Complexity: O(1)
         → Constant space, since we track only needed values.
    */
    static double betterMedian(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int total = n1 + n2;

        int i = 0, j = 0;
        int count = 0;
        int prev = 0, curr = 0;

        // Merge till halfway
        while (count <= total / 2) {
            prev = curr;
            if (i < n1 && (j >= n2 || nums1[i] < nums2[j])) {
                curr = nums1[i++];
            } else {
                curr = nums2[j++];
            }
            count++;
        }

        // Odd → current element, Even → avg of prev + curr
        if (total % 2 == 1) return curr;
        else return ((double) (prev + curr)) / 2.0;
    }

    // ============================================================
    // ⚡ OPTIMAL APPROACH — Binary Search Partition Method
    // ============================================================
    /*
     🔧 Idea:
     - Use binary search on the smaller array.
     - Partition both arrays so that:
         left side = right side in size (or 1 extra if odd)
         and every element on left ≤ every element on right.
     - Median is then derived from max(left) and min(right).

     ⏱️ Time Complexity: O(log(min(n1, n2)))
         → We binary search only on smaller array.
     💾 Space Complexity: O(1)
         → Just a few variables.
    */
    static double optimalMedian(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;

        // Always binary search the smaller array
        if (n1 > n2) return optimalMedian(nums2, nums1);

        int total = n1 + n2;
        int leftHalf = (total + 1) / 2; // number of elements in left half
        int low = 0, high = n1;

        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = leftHalf - mid1;

            int l1 = (mid1 > 0) ? nums1[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? nums2[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < n1) ? nums1[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n2) ? nums2[mid2] : Integer.MAX_VALUE;

            if (l1 <= r2 && l2 <= r1) {
                // Perfect partition found
                if (total % 2 == 1) return Math.max(l1, l2);
                else return ((double) (Math.max(l1, l2) + Math.min(r1, r2))) / 2.0;
            } else if (l1 > r2) {
                // Too far right on nums1, go left
                high = mid1 - 1;
            } else {
                // Too far left, go right
                low = mid1 + 1;
            }
        }

        // Should not reach here if input valid
        return 0.0;
    }

    // ============================================================
    // ⚙️ MAIN METHOD — Let's vibe-test our algorithms 😎
    // ============================================================
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 8};
        int[] nums2 = {7, 9, 10, 11};

        System.out.println("🥱 Brute Force Median: " + bruteForceMedian(nums1, nums2));
        System.out.println("🧩 Better Median: " + betterMedian(nums1, nums2));
        System.out.println("⚡ Optimal Median: " + optimalMedian(nums1, nums2));
    }
}
