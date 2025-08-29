public class SetMatrixZero {

    // Brute Force Method
    // Approach: Use a separate boolean matrix to mark positions of zeros, then zero rows and columns accordingly.
    // Time Complexity: O(m * n * (m + n)) due to repeated zeroing of rows and columns.
    // Space Complexity: O(m * n) for the auxiliary boolean matrix.
    public static void setZeroesBruteForce(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] marks = new boolean[m][n];

        // Mark cells that are initially zero
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    marks[i][j] = true;
                }
            }
        }

        // Zero rows and columns based on marks
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (marks[i][j]) {
                    // Set entire row i zero
                    for (int k = 0; k < n; k++) {
                        matrix[i][k] = 0;
                    }
                    // Set entire column j zero
                    for (int k = 0; k < m; k++) {
                        matrix[k][j] = 0;
                    }
                }
            }
        }
    }


    // Better Method
    // Approach: Use two auxiliary arrays to mark rows and columns, then update the matrix in one pass.
    // Time Complexity: O(m * n)
    // Space Complexity: O(m + n) for row and column marker arrays.
    public static void setZeroesBetter(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];

        // Mark rows and columns to be zeroed
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        // Zero marked rows
        for (int i = 0; i < m; i++) {
            if (rows[i]) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Zero marked columns
        for (int j = 0; j < n; j++) {
            if (cols[j]) {
                for (int i = 0; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }


    // Optimal Method
    // Approach: Use first row and first column of the matrix as markers to track rows and columns to zero.
    // Time Complexity: O(m * n)
    // Space Complexity: O(1), constant extra space.
    public static void setZeroesOptimal(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Check whether first row has zero
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Check whether first column has zero
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Use first row and column as markers for other rows and columns
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Zero rows based on first column markers
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Zero columns based on first row markers
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Zero first row if needed
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Zero first column if needed
        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }


    // Helper method to print the matrix
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };

        int[][] matrix2 = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };

        System.out.println("Original matrix1:");
        printMatrix(matrix1);

        setZeroesBruteForce(matrix1);
        System.out.println("After Brute Force:");
        printMatrix(matrix1);

        // Reset matrix2 for better approach test (fresh copy to keep original)
        matrix2 = new int[][] {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };

        System.out.println("Original matrix2:");
        printMatrix(matrix2);

        setZeroesBetter(matrix2);
        System.out.println("After Better Method:");
        printMatrix(matrix2);

        // Reset matrix2 for optimal approach test (fresh copy)
        matrix2 = new int[][] {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };

        System.out.println("Original matrix2 (for optimal):");
        printMatrix(matrix2);

        setZeroesOptimal(matrix2);
        System.out.println("After Optimal Method:");
        printMatrix(matrix2);
    }
}
