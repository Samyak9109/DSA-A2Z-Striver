import java.util.*;

public class MatrixSpiral {
    /*
     * Method to return all elements of the given matrix in spiral order.
     *
     * Approach:
     * Using four pointers (top, bottom, left, right) to mark boundaries of current matrix layer.
     * Traverse layers in order: left to right (top row), top to bottom (right column),
     * right to left (bottom row), and bottom to top (left column). Adjust boundaries inward after each.
     * Repeat until all elements are traversed.
     *
     * Time Complexity: O(m * n) - each element visited once.
     * Space Complexity: O(m * n) - output list storing all elements.
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int n = matrix.length;        // Number of rows
        int m = matrix[0].length;     // Number of columns

        int top = 0, left = 0, bottom = n - 1, right = m - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++)
                ans.add(matrix[top][i]);
            top++;

            for (int i = top; i <= bottom; i++)
                ans.add(matrix[i][right]);
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    ans.add(matrix[bottom][i]);
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    ans.add(matrix[i][left]);
                left++;
            }
        }
        return ans;
    }

    // Main method to test spiralOrder function
    public static void main(String[] args) {
        MatrixSpiral solution = new MatrixSpiral();

        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        List<Integer> spiral = solution.spiralOrder(matrix);
        System.out.println("Spiral order: " + spiral);
    }
}
