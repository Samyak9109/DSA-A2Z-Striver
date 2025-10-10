public class RowWithMaxOne {

    /*
     * 📌 LeetCode Problem 2643 → "Row With Maximum Ones"
     * 📌 GFG Problem → "Row with max 1s"
     * 💡 Problem:
     * Given a binary matrix `arr[][]` where each row is sorted (0s first, then 1s),
     * find the index of the row that contains the **maximum number of 1s**.
     * If multiple rows tie, return the **first row index** (smallest index).
     * If no 1s exist, return -1.
     */

    // ======================================================
    // 🧱 BRUTE FORCE APPROACH
    // ======================================================
    // Idea: Just count all 1s in every row and track the max.
    // 🔹 Time Complexity: O(n * m) — we check every element in the matrix
    // 🔹 Space Complexity: O(1) — only a few integer variables
    static int rowWithMax1s_Brute(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int maxCount = 0;  // maximum number of 1s found so far
        int rowIndex = -1; // row index with maximum 1s

        // Traverse each row
        for (int i = 0; i < n; i++) {
            int count = 0; // count 1s in this row
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) count++; // increment for every 1
            }

            // If this row has more 1s than previous max, update
            if (count > maxCount) {
                maxCount = count;
                rowIndex = i;
            }
        }
        return rowIndex; // return row with max 1s (or -1 if none)
    }

    // ======================================================
    // ⚡ BETTER APPROACH — Binary Search
    // ======================================================
    // Idea: Each row is sorted, so all 0s come first. Find the first 1 using binary search.
    // 🔹 Time Complexity: O(n * log m) — binary search on each row
    // 🔹 Space Complexity: O(1)
    static int rowWithMax1s_BinarySearch(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int maxCount = 0;
        int rowIndex = -1;

        for (int i = 0; i < n; i++) {
            int firstOneIndex = lowerBound(arr[i], m, 1); // index of first 1
            int countOnes = m - firstOneIndex;            // total 1s in this row

            if (countOnes > maxCount) {
                maxCount = countOnes;
                rowIndex = i;
            }
        }
        return rowIndex;
    }

    // Helper function for binary search: find first index where arr[i] >= x
    static int lowerBound(int[] row, int m, int x) {
        int low = 0, high = m - 1;
        int ans = m; // default: x not found

        while (low <= high) {
            int mid = (low + high) / 2;
            if (row[mid] >= x) {
                ans = mid;       // possible answer
                high = mid - 1;  // look for earlier occurrence on left
            } else {
                low = mid + 1;   // look on right
            }
        }
        return ans;
    }

    // ======================================================
    // 🚀 OPTIMAL APPROACH — Staircase Trick
    // ======================================================
    // Idea: Start at top-right of matrix
    // - If current element is 1 → move left (maybe row has more 1s)
    // - If current element is 0 → move down (next row may have more 1s)
    // 🔹 Time Complexity: O(n + m)
    // 🔹 Space Complexity: O(1)
    static int rowWithMax1s_Optimal(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        int rowIndex = -1;  // track row with maximum 1s
        int j = m - 1;      // start at top-right corner (0, m-1)

        // Traverse rows
        for (int i = 0; i < n; i++) {
            // Move left while there are 1s
            while (j >= 0 && arr[i][j] == 1) {
                rowIndex = i; // this row has more 1s than previous
                j--;           // move left
            }
            // If we hit a 0, we stop inner while and move to next row
        }

        return rowIndex; // row with maximum 1s, -1 if none
    }

    // ======================================================
    // 🧠 MAIN METHOD — Demonstration
    // ======================================================
    public static void main(String[] args) {
        int[][] mat1 = {
                {0, 0, 0, 1},
                {0, 1, 1, 1},
                {0, 0, 1, 1}
        };

        int[][] mat2 = {
                {0, 1},
                {1, 0}
        };

        int[][] mat3 = {
                {0, 0},
                {0, 0}
        };

        System.out.println("🔹 Matrix 1 Results:");
        System.out.println("Brute Force → " + rowWithMax1s_Brute(mat1));
        System.out.println("Binary Search → " + rowWithMax1s_BinarySearch(mat1));
        System.out.println("Optimal → " + rowWithMax1s_Optimal(mat1));

        System.out.println("\n🔹 Matrix 2 Results:");
        System.out.println("Brute Force → " + rowWithMax1s_Brute(mat2));
        System.out.println("Binary Search → " + rowWithMax1s_BinarySearch(mat2));
        System.out.println("Optimal → " + rowWithMax1s_Optimal(mat2));

        System.out.println("\n🔹 Matrix 3 Results (all 0s):");
        System.out.println("Brute Force → " + rowWithMax1s_Brute(mat3));
        System.out.println("Binary Search → " + rowWithMax1s_BinarySearch(mat3));
        System.out.println("Optimal → " + rowWithMax1s_Optimal(mat3));
    }
}
