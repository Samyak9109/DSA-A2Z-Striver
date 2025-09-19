import java.util.*;

public class MergeOverlappingIntervals {

    /* ---------------------------------------------------
       Brute Force Approach
       ---------------------------------------------------
       Idea:
       - Sort intervals by start time.
       - For each interval, check other intervals for overlap.
       - Merge overlapping intervals and mark them visited.
       ---------------------------------------------------
       Time Complexity: O(n^2)
         - Each interval may compare with all others.
       Space Complexity: O(n)
         - To store merged intervals.
    */
    public int[][] mergeBrute(int[][] intervals) {
        int n = intervals.length;

        // Step 1: Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        boolean[] visited = new boolean[n]; // To mark intervals already merged
        List<int[]> result = new ArrayList<>(); // Stores final merged intervals

        // Step 2: Traverse all intervals
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue; // Skip if already merged

            // Take current interval as base
            int start = intervals[i][0];
            int end = intervals[i][1];

            // Step 3: Check for overlaps with future intervals
            for (int j = i + 1; j < n; j++) {
                if (intervals[j][0] <= end) { // Overlap found
                    end = Math.max(end, intervals[j][1]); // Extend the end
                    visited[j] = true; // Mark merged interval
                }
            }

            // Step 4: Store merged interval
            result.add(new int[]{start, end});
        }

        // Convert list to 2D array
        return result.toArray(new int[result.size()][]);
    }

    /* ---------------------------------------------------
       Better Approach (Using Stack)
       ---------------------------------------------------
       Idea:
       - Sort intervals by start time.
       - Use stack to merge:
         * If new interval overlaps with top → merge.
         * Else push new interval.
       ---------------------------------------------------
       Time Complexity: O(n log n)
         - Sorting O(n log n), merging O(n).
       Space Complexity: O(n)
         - Stack stores merged intervals.
    */
    public int[][] mergeBetter(int[][] intervals) {
        // Step 1: Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]); // Push first interval

        // Step 2: Traverse remaining intervals
        for (int i = 1; i < intervals.length; i++) {
            int[] top = stack.peek(); // Get last merged interval

            if (intervals[i][0] <= top[1]) {
                // Overlap → merge by updating end
                top[1] = Math.max(top[1], intervals[i][1]);
            } else {
                // No overlap → push new interval
                stack.push(intervals[i]);
            }
        }

        // Convert stack to 2D array
        return stack.toArray(new int[stack.size()][]);
    }

    /* ---------------------------------------------------
       Optimal Approach (Using List, No Stack)
       ---------------------------------------------------
       Idea:
       - Sort intervals by start time.
       - Maintain a "current" interval and keep merging.
       - If no overlap, start a new interval.
       ---------------------------------------------------
       Time Complexity: O(n log n)
         - Sorting O(n log n), merging O(n).
       Space Complexity: O(n)
         - List stores merged intervals.
    */
    public int[][] mergeOptimal(int[][] intervals) {
        // Step 1: Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();

        // Step 2: Take first interval as "current"
        int[] current = intervals[0];
        result.add(current);

        // Step 3: Traverse intervals
        for (int[] interval : intervals) {
            if (interval[0] <= current[1]) {
                // Overlap → merge by extending end
                current[1] = Math.max(current[1], interval[1]);
            } else {
                // No overlap → start new interval
                current = interval;
                result.add(current);
            }
        }

        // Convert list to 2D array
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        MergeOverlappingIntervals obj = new MergeOverlappingIntervals();

        // Input intervals
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};

        // Run Brute
        int[][] res1 = obj.mergeBrute(intervals);
        System.out.println("Brute: " + Arrays.deepToString(res1));

        // Run Better
        int[][] res2 = obj.mergeBetter(intervals);
        System.out.println("Better: " + Arrays.deepToString(res2));

        // Run Optimal
        int[][] res3 = obj.mergeOptimal(intervals);
        System.out.println("Optimal: " + Arrays.deepToString(res3));
    }
}
