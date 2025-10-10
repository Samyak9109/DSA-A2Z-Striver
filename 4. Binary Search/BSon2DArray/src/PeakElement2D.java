// LeetCode 1901 - Find a Peak Element II
// GFG Equivalent: https://practice.geeksforgeeks.org/problems/find-a-peak-element-1587115621/

public class PeakElement2D {

    // ===============================
    // 1️⃣ BRUTE FORCE APPROACH
    // ===============================
    // Idea: Check every element, see if it is strictly greater than all 4 neighbors
    // Time Complexity: O(m * n) → check every cell
    // Space Complexity: O(1) → no extra space
    static int[] peakElementBrute(int[][] mat) {
        int m = mat.length;    // number of rows
        int n = mat[0].length; // number of columns

        // Loop over every row and column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Grab neighbors; edges are treated as -1 (perimeter)
                int up = (i > 0) ? mat[i-1][j] : -1;
                int down = (i < m-1) ? mat[i+1][j] : -1;
                int left = (j > 0) ? mat[i][j-1] : -1;
                int right = (j < n-1) ? mat[i][j+1] : -1;

                // Check if current element is greater than all neighbors
                if (mat[i][j] > up && mat[i][j] > down && mat[i][j] > left && mat[i][j] > right) {
                    return new int[]{i, j}; // Found a peak, return coordinates
                }
            }
        }

        // Should never reach here because a peak always exists
        return new int[]{-1, -1};
    }

    // ===============================
    // 2️⃣ OPTIMAL APPROACH
    // ===============================
    // Idea: Binary search along columns
    // - Pick middle column
    // - Find global max in this column
    // - Check left/right neighbors:
    //   → If left > max → move left
    //   → If right > max → move right
    //   → Else → peak found
    // Time Complexity: O(m * log n) → binary search on n columns, scan m rows each step
    // Space Complexity: O(1)
    static int[] peakElementOptimal(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int left = 0, right = n - 1; // search boundaries for columns

        while (left <= right) {
            int midCol = left + (right - left) / 2;

            // 1️⃣ Find the row index of maximum element in this column
            int maxRow = 0;
            for (int i = 0; i < m; i++) {
                if (mat[i][midCol] > mat[maxRow][midCol]) {
                    maxRow = i;
                }
            }

            // 2️⃣ Check neighbors in left and right columns
            int leftNeighbor = (midCol - 1 >= 0) ? mat[maxRow][midCol - 1] : -1;
            int rightNeighbor = (midCol + 1 < n) ? mat[maxRow][midCol + 1] : -1;

            // 3️⃣ Decide the direction
            if (mat[maxRow][midCol] > leftNeighbor && mat[maxRow][midCol] > rightNeighbor) {
                return new int[]{maxRow, midCol}; // Found a peak!
            } else if (leftNeighbor > mat[maxRow][midCol]) {
                right = midCol - 1; // move search to left half
            } else {
                left = midCol + 1;  // move search to right half
            }
        }

        return new int[]{-1, -1}; // theoretically unreachable
    }

    // ===============================
    // 3️⃣ MAIN METHOD TO TEST
    // ===============================
    public static void main(String[] args) {
        int[][] mat = {
                {10, 20, 15},
                {21, 30, 14},
                {7, 16, 32}
        };

        // Test Brute Force
        int[] peak1 = peakElementBrute(mat);
        System.out.println("Brute Force Peak: [" + peak1[0] + ", " + peak1[1] + "]");

        // Test Optimal
        int[] peak2 = peakElementOptimal(mat);
        System.out.println("Optimal Peak: [" + peak2[0] + ", " + peak2[1] + "]");
    }
}
