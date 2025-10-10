import java.util.*;

public class MatrixMedian {

    /*
     * 🔹 BRUTE FORCE APPROACH
     * - Flatten the matrix into a 1D array, sort it, and pick the middle element.
     * Time Complexity: O(r * c * log(r * c)) → sorting all elements
     * Space Complexity: O(r * c) → storing flattened array
     */
    static int bruteForceMedian(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;

        int[] arr = new int[r * c];
        int idx = 0;

        // Flatten the matrix into a 1D array
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                arr[idx++] = matrix[i][j];
            }
        }

        // Sort the array
        Arrays.sort(arr);

        // Return the middle element (median)
        return arr[arr.length / 2];
    }

    /*
     * 1. Binary Search on the VALUE RANGE (not indices!)
     *    - Minimum possible value: smallest element in the matrix
     *    - Maximum possible value: largest element in the matrix
     * 2. For each mid value, count how many elements in the matrix are <= mid
     *    - Use binary search on each row (because rows are sorted)
     * 3. Adjust the search space based on this count:
     *    - If count < desiredCount → median is higher → move min = mid + 1
     *    - Else → median is mid or lower → move max = mid
     * 4. Loop until min == max → that's the median
     *
     * Time Complexity: O(r * log(max-min) * logc)
     *      - log(max-min) → binary search over value range
     *      - r * logc → counting elements <= mid in all rows via binary search
     * Space Complexity: O(1) → no extra space except variables
     */
    static int optimalMedian(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;

        // Find min and max in the matrix
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < r; i++) {
            min = Math.min(min, matrix[i][0]);
            max = Math.max(max, matrix[i][c - 1]);
        }

        int desiredCount = (r * c + 1) / 2; // median position

        // Binary search over value range
        while (min < max) {
            int mid = min + (max - min) / 2;
            int place = 0;

            // Count elements <= mid in each row
            for (int i = 0; i < r; i++) {
                place += upperBound(matrix[i], mid);
            }

            if (place < desiredCount)
                min = mid + 1; // median is higher
            else
                max = mid;     // median could be mid or lower
        }

        return min; // median found
    }

    // Helper: counts elements <= target in a sorted row
    static int upperBound(int[] row, int target) {
        int l = 0, h = row.length;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (row[m] <= target)
                l = m + 1;
            else
                h = m;
        }
        return l; // number of elements <= target
    }

    // 🚀 MAIN METHOD TO DEMO
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5},
                {2, 6, 9},
                {3, 6, 9}
        };

        System.out.println("Brute Force Median: " + bruteForceMedian(matrix));
        System.out.println("Optimal Median: " + optimalMedian(matrix));
    }
}
