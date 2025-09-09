import java.util.*;

public class PascalTriangle {
    // Brute force approach using factorial to compute binomial coefficients
    // Time Complexity: O(N^3) because factorial computation is O(N) and
    // is performed for each of the ~N^2 elements
    // Space Complexity: O(N^2) for storing the output triangle
    // Approach: Computes each element independently by factorial formula nCr = n!/(r!(n-r)!).
    // This method recalculates factorials repeatedly, making it inefficient for large N.
    private int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    public List<List<Integer>> generateBrute(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                int value = factorial(i) / (factorial(j) * factorial(i - j));
                row.add(value);
            }
            triangle.add(row);
        }
        return triangle;
    }

    // Optimal approach using iterative binomial coefficient calculation:
    // Time Complexity: O(N^2) because we compute each element in constant time,
    // iterating through all ~N^2 elements once.
    // Space Complexity: O(N^2) for storing the output triangle.
    // Approach: Each element in a row is derived from the previous element:
    // C(i, j+1) = C(i, j) * (i - j) / (j + 1)
    // This avoids expensive factorial computation and redundant recalculation.
    public List<List<Integer>> generateOptimal(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            int val = 1;  // First value is always 1 (C(i,0))
            for (int j = 0; j <= i; j++) {
                row.add(val);
                val = val * (i - j) / (j + 1);  // Compute next value using previous
            }
            triangle.add(row);
        }
        return triangle;
    }

    // Main method demonstrating both methods
    public static void main(String[] args) {
        PascalTriangle pt = new PascalTriangle();
        int numRows = 5;

        System.out.println("Brute Force Pascal's Triangle:");
        List<List<Integer>> brute = pt.generateBrute(numRows);
        for (List<Integer> row : brute) {
            System.out.println(row);
        }

        System.out.println("\nOptimal Pascal's Triangle:");
        List<List<Integer>> optimal = pt.generateOptimal(numRows);
        for (List<Integer> row : optimal) {
            System.out.println(row);
        }
    }
}
