import java.util.*;

public class KthElementOfArray {

    /*
     🎯 PROBLEM STATEMENT:
     You are given two sorted arrays `a[]` and `b[]` of sizes `m` and `n`.
     You need to find the k-th smallest element in the final sorted array
     formed after merging both arrays.

     Example:
     a = [2, 3, 6, 7, 9]
     b = [1, 4, 8, 10]
     k = 5 → Output = 6

     Basically, if we merged both arrays → [1, 2, 3, 4, 6, 7, 8, 9, 10]
     The 5th element = 6 🎯

     🔗 LeetCode: #4 (Median of Two Sorted Arrays)
     🔗 GFG: "K-th element of two sorted arrays"
     🔗 HackerRank: Custom problems under "Searching / Arrays"
    */


    // ===================================================================
    // 🥱 BRUTE FORCE APPROACH — Merge everything and pick the k-th element
    // ===================================================================
    /*
     🧠 IDEA:
     - Straightforward approach.
     - Merge both sorted arrays into a new one.
     - The merged array will also be sorted.
     - Then directly return merged[k-1].

     ⏱️ Time Complexity: O(m + n)
         → We traverse both arrays fully once.
     💾 Space Complexity: O(m + n)
         → We store all elements in a new array.
    */
    static int bruteForceKth(int[] a, int[] b, int k) {
        int m = a.length, n = b.length;
        int[] merged = new int[m + n]; // Final merged array
        int i = 0, j = 0, idx = 0;     // Pointers for arrays and merged array

        // Merge process (like in merge sort)
        while (i < m && j < n) {
            // Compare current elements of both arrays
            if (a[i] < b[j]) {
                merged[idx++] = a[i++]; // Add smaller one and move its pointer
            } else {
                merged[idx++] = b[j++];
            }
        }

        // If any elements are left in array a
        while (i < m) merged[idx++] = a[i++];

        // If any elements are left in array b
        while (j < n) merged[idx++] = b[j++];

        // Arrays are 0-indexed → k-th element means index (k-1)
        return merged[k - 1];
    }



    // ===================================================================
    // 🧩 BETTER APPROACH — Merge partially until reaching the k-th element
    // ===================================================================
    /*
     🧠 IDEA:
     - We don’t need the entire merged array to find the k-th element.
     - We can just merge up to the point where we reach that position.
     - Once we’ve picked k elements, the last picked element is our answer!

     ⏱️ Time Complexity: O(k)
         → We stop merging once we reach k elements.
     💾 Space Complexity: O(1)
         → No extra storage, just pointers.
    */
    static int betterKth(int[] a, int[] b, int k) {
        int m = a.length, n = b.length;
        int i = 0, j = 0, count = 0, ans = -1;

        // Merge until we've chosen k elements
        while (i < m && j < n) {
            // Pick the smaller of the two current elements
            if (a[i] < b[j]) {
                ans = a[i++]; // Pick from array a
            } else {
                ans = b[j++]; // Pick from array b
            }
            count++; // Increment number of elements taken
            if (count == k) return ans; // Stop once we’ve taken k elements
        }

        // If array a still has elements left
        while (i < m) {
            ans = a[i++];
            count++;
            if (count == k) return ans;
        }

        // If array b still has elements left
        while (j < n) {
            ans = b[j++];
            count++;
            if (count == k) return ans;
        }

        // Just in case (shouldn't hit)
        return ans;
    }



    // ===================================================================
    // ⚡ OPTIMAL APPROACH — Binary Search Partition (next-level stuff)
    // ===================================================================
    /*
     🧠 IDEA (same as Median of Two Sorted Arrays):
     - Instead of merging, we’ll do a binary search on the smaller array.
     - Think of "cutting" both arrays into two halves such that:
          Left half → contains exactly k elements combined
          Right half → contains the rest
     - Then we check if our partition is valid:
          l1 ≤ r2 && l2 ≤ r1 (ensures correct positioning)
     - If not valid, adjust our cut using binary search 🔍

     ✅ Once valid partition found:
         → The k-th element = max(l1, l2)

     ⏱️ Time Complexity: O(log(min(m, n)))
         → Binary search only on smaller array.
     💾 Space Complexity: O(1)
         → Constant extra variables.
    */
    static int optimalKth(int[] a, int[] b, int k) {
        int m = a.length, n = b.length;

        // Always binary search on the smaller array for efficiency
        if (m > n) return optimalKth(b, a, k);

        // Define search boundaries
        int low = Math.max(0, k - n);  // Minimum we can take from a
        int high = Math.min(k, m);     // Maximum we can take from a

        // Binary search begins 💻
        while (low <= high) {
            int mid1 = (low + high) / 2;   // #elements we take from a
            int mid2 = k - mid1;           // #elements we must take from b

            // Left and right boundary elements
            int l1 = (mid1 > 0) ? a[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? b[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < m) ? a[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n) ? b[mid2] : Integer.MAX_VALUE;

            // 💡 Check if partition is valid
            if (l1 <= r2 && l2 <= r1) {
                // Perfect partition found
                // The k-th element is the maximum element on the left side
                return Math.max(l1, l2);
            }
            else if (l1 > r2) {
                // Too many elements taken from a → move cut to the left
                high = mid1 - 1;
            }
            else {
                // Too few elements taken from a → move cut to the right
                low = mid1 + 1;
            }
        }

        // Should never happen if input arrays are valid
        return -1;
    }



    // ===================================================================
    // ⚙️ MAIN METHOD — Testing all three versions like a boss 😎
    // ===================================================================
    public static void main(String[] args) {
        int[] a = {2, 3, 6, 7, 9};
        int[] b = {1, 4, 8, 10};
        int k = 5;

        System.out.println("🥱 Brute Force Kth Element: " + bruteForceKth(a, b, k));
        System.out.println("🧩 Better Kth Element: " + betterKth(a, b, k));
        System.out.println("⚡ Optimal Kth Element: " + optimalKth(a, b, k));
    }
}
