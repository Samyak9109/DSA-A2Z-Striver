import java.util.HashSet;

public class LongestConsecutiveSequence {

    /**
     * BRUTE FORCE APPROACH
     * ---------------------
     * For each element, check next consecutive numbers using a linear search.
     * - Time Complexity: O(n^2) (for each element, linear scan for next values)
     * - Space Complexity: O(1)
     *
     * @param arr Input array
     * @return Length of longest consecutive sequence
     */
    static int bruteForce(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int longest = 1;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int x = arr[i];
            int count = 1;
            int next = x + 1;

            while (contains(arr, next)) {
                count++;
                next++;
            }
            longest = Math.max(longest, count);
        }
        return longest;
    }

    // Helper for brute force
    static boolean contains(int[] arr, int val) {
        for (int num : arr) {
            if (num == val) return true;
        }
        return false;
    }

    /**
     * BETTER APPROACH
     * ----------------
     * Use a HashSet for O(1) average lookup instead of scanning the array.
     * Still starts counting from every element (not just sequence starts).
     * - Time Complexity: O(n^2) worst case (but faster in practice due to O(1) lookups)
     * - Space Complexity: O(n) (HashSet storage)
     *
     * @param arr Input array
     * @return Length of longest consecutive sequence
     */
    static int betterApproach(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }

        int longest = 1;

        for (int x : arr) {
            int count = 1;
            int next = x + 1;

            while (set.contains(next)) {
                count++;
                next++;
            }
            longest = Math.max(longest, count);
        }
        return longest;
    }

    /**
     * OPTIMAL APPROACH
     * -----------------
     * Use HashSet for constant time lookups.
     * Only start counting when the number is the start of a sequence (num-1 not in set).
     * - Time Complexity: O(n) (each element starts sequence once, lookups O(1) average)
     * - Space Complexity: O(n) (HashSet storage)
     *
     * @param arr Input array
     * @return Length of longest consecutive sequence
     */
    static int optimalApproach(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }

        int longest = 0;

        for (int num : set) {
            // Only start from sequence beginning
            if (!set.contains(num - 1)) {
                int current = num;
                int count = 1;
                while (set.contains(current + 1)) {
                    current++;
                    count++;
                }
                longest = Math.max(longest, count);
            }
        }
        return longest;
    }

    // MAIN METHOD TO TEST ALL APPROACHES
    public static void main(String[] args) {
        int[] arr = {100, 4, 200, 1, 3, 2};

        System.out.println("Array: {100, 4, 200, 1, 3, 2}\n");

        System.out.println("Brute Force Result: " + bruteForce(arr));
        System.out.println("Better Approach Result: " + betterApproach(arr));
        System.out.println("Optimal Approach Result: " + optimalApproach(arr));

        int[] arr2 = {9, 1, 8, 2, 7, 3, 6};

        System.out.println("\nArray: {9, 1, 8, 2, 7, 3, 6}\n");

        System.out.println("Brute Force Result: " + bruteForce(arr2));
        System.out.println("Better Approach Result: " + betterApproach(arr2));
        System.out.println("Optimal Approach Result: " + optimalApproach(arr2));
    }
}
