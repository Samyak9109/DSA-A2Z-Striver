public class RotateMatrix {
    /**
     * Rotates the given square matrix 90 degrees clockwise using extra space (brute-force).
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     * Approach: Creates a new matrix for the result.
     */
    static int[][] rotateBrute(int[][] matrix) {
        int n = matrix.length;
        int[][] rotated = new int[n][n];
        // Copy elements to new positions in a new matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - i - 1] = matrix[i][j];
            }
        }
        return rotated;
    }

    /**
     * Rotates the given square matrix 90 degrees clockwise in-place (optimal).
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * Approach: Transpose, then reverse each row of the matrix.
     */
    static void rotateOptimal(int[][] matrix) {
        int n = matrix.length;
        // Transpose the matrix (swap elements across the diagonal)
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // Reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    // Helper function to print matrices for demonstration purposes
    static void printMatrix(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Main method showing both approaches in action
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        System.out.println("Original Matrix:");
        printMatrix(arr);

        // Brute-force approach (creates a new matrix)
        int[][] rotatedBrute = rotateBrute(arr);
        System.out.println("\nRotated Matrix (Brute-force, O(n^2) time, O(n^2) space):");
        printMatrix(rotatedBrute);

        // Optimal in-place approach
        // Clone matrix so original is usable for both approaches
        int[][] arrForOptimal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotateOptimal(arrForOptimal);
        System.out.println("\nRotated Matrix (Optimal/In-place, O(n^2) time, O(1) space):");
        printMatrix(arrForOptimal);
    }
}
