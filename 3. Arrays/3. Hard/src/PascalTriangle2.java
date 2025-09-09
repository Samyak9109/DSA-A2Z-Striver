import java.util.*;

public class PascalTriangle2 {

    // Brute force approach using factorial to compute each binomial coefficient independently
    // Time Complexity: O(N^3) because factorial calculation is O(N), done for each element (~N^2)
    // Space Complexity: O(N^2) for storing the output triangle
    // Approach: Uses formula C(n, r) = n! / (r! * (n-r)!) to build every value, repeated factorial calls cause inefficiency.
    private long factorial(int n) {
        long res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    public List<List<Integer>> generateBrute(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int n = 0; n < numRows; n++) {
            List<Integer> row = new ArrayList<>();
            for (int r = 0; r <= n; r++) {
                long val = factorial(n) / (factorial(r) * factorial(n - r));
                row.add((int) val);
            }
            triangle.add(row);
        }
        return triangle;
    }

    // Optimal approach: Builds each row iteratively from previous value in O(1) time per element
    // Time Complexity: O(N^2) because each element (~N^2 total) is computed once with constant time math
    // Space Complexity: O(N^2) for storing the output triangle
    // Approach: Uses relation C(n, r+1) = C(n, r) * (n-r) / (r+1), avoiding factorial computations entirely.
    public List<List<Integer>> generateOptimal(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int n = 0; n < numRows; n++) {
            List<Integer> row = new ArrayList<>();
            long val = 1;
            for (int r = 0; r <= n; r++) {
                row.add((int) val);
                val = val * (n - r) / (r + 1);
            }
            triangle.add(row);
        }
        return triangle;
    }

    // Main method demonstrating both methods
    public static void main(String[] args) {
        PascalTriangle2 pt = new PascalTriangle2();
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
