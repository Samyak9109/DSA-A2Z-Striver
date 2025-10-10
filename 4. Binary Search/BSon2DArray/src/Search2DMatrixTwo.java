// LeetCode 240 - Search a 2D Matrix II
// GFG Equivalent: https://practice.geeksforgeeks.org/problems/search-in-a-matrix-1587115621/

public class Search2DMatrixTwo {

    // 🚀 BRUTE FORCE APPROACH
    // Time Complexity: O(n*m) → check every element
    // Space Complexity: O(1) → no extra space
    static boolean searchMatrixBrute(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        // iterate every row and column
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == target) return true; // found target, boom!
            }
        }
        return false; // target not found
    }

    // 💡 BETTER APPROACH (Binary Search in each row)
    // Time Complexity: O(n * log m) → binary search in each row
    // Space Complexity: O(1)
    static boolean searchMatrixBetter(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < n; i++) {
            int low = 0, high = m - 1;
            while (low <= high) {
                int mid = low + (high - low)/2; // safer than (low + high)/2
                if (matrix[i][mid] == target) return true;
                if (matrix[i][mid] > target) high = mid - 1;
                else low = mid + 1;
            }
        }
        return false;
    }

    // ⚡ OPTIMAL APPROACH (Staircase Search)
    // Time Complexity: O(n + m) → start top-right, move smartly
    // Space Complexity: O(1)
    static boolean searchMatrixOptimal(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int row = 0, col = m - 1;

        // move left/down smartly
        while (row < n && col >= 0) {
            if (matrix[row][col] == target) return true; // found it
            if (matrix[row][col] < target) row++;       // move down
            else col--;                                  // move left
        }
        return false; // not found
    }

    // main to test all approaches
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11},
                {2, 5, 8, 12},
                {3, 6, 9, 16},
                {10, 13, 14, 17}
        };
        int target = 5;

        System.out.println("Brute Force: " + searchMatrixBrute(matrix, target));
        System.out.println("Better (Binary Search Rows): " + searchMatrixBetter(matrix, target));
        System.out.println("Optimal (Staircase Search): " + searchMatrixOptimal(matrix, target));
    }
}
