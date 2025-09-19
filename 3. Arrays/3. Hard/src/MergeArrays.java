import java.util.*;

public class MergeArrays {

    // ---------------- Brute Force ----------------
    /*
        Approach:
        1. Copy all elements of nums2 into nums1's extra space.
        2. Sort the entire nums1 array.
        Why it works: Sorting arranges the combined elements in non-decreasing order.

        Time Complexity:
        - Copying nums2 into nums1 takes O(n).
        - Sorting nums1 of size (m+n) takes O((m+n) log(m+n)).
        - Hence total = O((m+n) log(m+n)).

        Space Complexity:
        - Sorting is in-place in Java (Timsort), so O(1) extra space.
    */
    public void mergeBrute(int[] nums1, int m, int[] nums2, int n) {
        int k = 0;
        // Step 1: Copy nums2 into nums1
        for (int i = m; i < m + n; i++) {
            nums1[i] = nums2[k++];
        }
        // Step 2: Sort nums1
        Arrays.sort(nums1);
    }

    // ---------------- Better ----------------
    /*
        Approach:
        1. Use two pointers to merge nums1[0..m-1] and nums2[0..n-1]
           into a temporary array (like merge step of merge-sort).
        2. Copy the result back into nums1.
        Why it works: Merge sort logic ensures sorted merge in O(m+n).

        Time Complexity:
        - Merging takes O(m+n) since both arrays are traversed once.
        - Copying back into nums1 takes another O(m+n).
        - Hence total = O(m+n).

        Space Complexity:
        - Extra array of size (m+n) is created.
        - Hence space = O(m+n).
    */
    public void mergeBetter(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m + n]; // temporary array
        int i = 0, j = 0, k = 0;

        // Step 1: Merge both arrays into temp
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) temp[k++] = nums1[i++];
            else temp[k++] = nums2[j++];
        }

        // Step 2: Copy remaining elements
        while (i < m) temp[k++] = nums1[i++];
        while (j < n) temp[k++] = nums2[j++];

        // Step 3: Copy merged array back into nums1
        for (int p = 0; p < m + n; p++) {
            nums1[p] = temp[p];
        }
    }

    // ---------------- Optimal ----------------
    /*
        Approach (In-place, from back):
        1. Maintain three pointers:
           - i at index m-1 (end of nums1's valid part),
           - j at index n-1 (end of nums2),
           - k at index m+n-1 (end of nums1's full capacity).
        2. Compare nums1[i] and nums2[j]:
           - Place the larger one at nums1[k].
           - Move the corresponding pointer backward.
        3. If nums2 still has elements, copy them into nums1.
        4. No need to copy leftover nums1 elements (already in place).

        Why it works:
        - We start from the back, so we don’t overwrite nums1’s valid elements.
        - The array remains sorted because we always pick the larger element first.

        Time Complexity:
        - Each element is checked/placed exactly once.
        - Hence O(m+n).

        Space Complexity:
        - No extra space used, merging is in-place.
        - Hence O(1).
    */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;       // pointer for nums1 valid part
        int j = n - 1;       // pointer for nums2
        int k = m + n - 1;   // pointer for final index

        // Step 1: Merge from the back
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // Step 2: If nums2 has leftover elements
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
        // Leftover nums1 elements are already in correct place
    }

    // Function to calculate next gap
    private static int nextGap(int gap) {
        if (gap <= 1) return 0;
        return (gap / 2) + (gap % 2); // ceil(gap/2)
    }

    /*
        Approach (Gap Method):
        1. Start with gap = ceil((m+n)/2).
        2. Compare elements at index i and i+gap, swap if out of order.
        3. Reduce gap until it becomes 0.

        Time Complexity:
        - O((m+n) log(m+n)) because each pass takes O(m+n) and we reduce gap ~log times.

        Space Complexity:
        - O(1) since merging happens in-place.
    */
    public static void mergeGap(int[] nums1, int m, int[] nums2, int n) {
        int total = m + n;
        int gap = nextGap(total);

        while (gap > 0) {
            int i = 0, j = gap;

            while (j < total) {
                // Case 1: both pointers in nums1
                if (i < m && j < m) {
                    if (nums1[i] > nums1[j]) {
                        int temp = nums1[i];
                        nums1[i] = nums1[j];
                        nums1[j] = temp;
                    }
                }
                // Case 2: i in nums1, j in nums2
                else if (i < m && j >= m) {
                    if (nums1[i] > nums2[j - m]) {
                        int temp = nums1[i];
                        nums1[i] = nums2[j - m];
                        nums2[j - m] = temp;
                    }
                }
                // Case 3: both pointers in nums2
                else {
                    if (nums2[i - m] > nums2[j - m]) {
                        int temp = nums2[i - m];
                        nums2[i - m] = nums2[j - m];
                        nums2[j - m] = temp;
                    }
                }
                i++;
                j++;
            }
            gap = nextGap(gap);
        }
    }

    // ---------------- Main Method ----------------
    public static void main(String[] args) {
        MergeArrays sol = new MergeArrays();

        // Example input
        int[] nums1a = {1,2,3,0,0,0};
        int[] nums2a = {2,5,6};
        int m1 = 3, n1 = 3;

        int[] nums1b = {1,2,3,0,0,0};
        int[] nums2b = {2,5,6};

        int[] nums1c = {1,2,3,0,0,0};
        int[] nums2c = {2,5,6};

        int[] nums1d = {1,2,3,0,0,0};
        int[] nums2d = {2,5,6};

        // Brute Force
        sol.mergeBrute(nums1a, m1, nums2a, n1);
        System.out.println("Brute:   " + Arrays.toString(nums1a));

        // Better
        sol.mergeBetter(nums1b, m1, nums2b, n1);
        System.out.println("Better:  " + Arrays.toString(nums1b));

        // Optimal
        sol.merge(nums1c, m1, nums2c, n1);
        System.out.println("Optimal: " + Arrays.toString(nums1c));

        // Gap Method
        sol.mergeGap(nums1d, m1, nums2d, n1);
        System.out.println("Gap:     " + Arrays.toString(nums1d));
    }
}
