import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    // --- 1. OPTIMAL: BACKTRACKING (Loop-Based DFS) ---

    /**
     * Time Complexity: O(2^N * N)
     * Space Complexity: O(2^N * N) - For results storage + O(N) for recursion stack.
     *
     * Reasoning: This is generally considered the most standard and flexible way to solve
     * combinatorial problems like Subsets, Permutations, and Combinations.
     * The complexity comes from generating 2^N subsets and spending O(N) time copying each one.
     */
    public List<List<Integer>> subsets_Backtracking(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // Kick-off the recursive process starting at index 0 with an empty current list.
        backtrack(nums, res, new ArrayList<>(), 0);
        return res;
    }

    /**
     * Recursive helper function for the loop-based backtracking approach.
     * @param nums The input array.
     * @param res The list to store all final subsets.
     * @param curr The current subset being built in the DFS path.
     * @param index The starting index for the current level's loop.
     */
    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> curr, int index) {
        // Core Step: ADD the current state of 'curr' as a valid subset.
        // WHY? Every node in the DFS tree represents a valid, unique subset.
        res.add(new ArrayList<>(curr));

        // The For Loop: Iteratively choose the next element.
        for (int i = index; i < nums.length; i++) {
            // 1. Choose: Include nums[i].
            curr.add(nums[i]);

            // 2. Explore: Recurse on the remaining elements (starting at i + 1 to avoid duplicates).
            backtrack(nums, res, curr, i + 1);

            // 3. Unchoose (Backtrack): Undo the choice to explore the next option in the loop.
            // This resets the state for the next iteration where nums[i] is NOT included.
            curr.remove(curr.size() - 1);
        }
    }

    // --- 2. BETTER: ITERATIVE (Layer-by-Layer Growth) ---

    /**
     * Time Complexity: O(2^N * N)
     * Space Complexity: O(2^N * N) - For results storage.
     *
     * Reasoning: This approach builds the solution iteratively. Starting with [[]], for each
     * element in 'nums', it doubles the current list of subsets by adding the new element
     * to every existing subset. The time complexity is dominated by the nested loops (O(N) outer loop,
     * O(2^N) inner loop iterating over existing subsets, and O(N) for copying lists).
     */
    public List<List<Integer>> subsets_Iterative(int[] nums) {
        // Start with the result list containing only the empty set.
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>()); // The foundation: [[]]

        // Iterate through each number in the input array.
        for (int num : nums) {
            // Get the current number of subsets we have so far (e.g., 1, 2, 4, 8...).
            int currentSize = res.size();

            // Iterate through all existing subsets in the 'res' list.
            for (int i = 0; i < currentSize; i++) {
                // Get a copy of an existing subset.
                List<Integer> subset = new ArrayList<>(res.get(i));

                // Add the current number to this copy.
                subset.add(num);

                // Add the new subset to the result list. This effectively doubles 'res'.
                // This is a forward-thinking way to generate new combinations from existing ones.
                res.add(subset);
            }
        }
        return res;
    }

    // --- 3. ALTERNATIVE: RECURSIVE (Take/No-Take Decision) ---

    /**
     * Time Complexity: O(2^N * N)
     * Space Complexity: O(2^N * N) - For results storage + O(N) for recursion stack.
     *
     * Reasoning: This frames the problem as a binary decision tree. At each element 'nums[i]',
     * we branch into two paths: one where we 'Take' the element, and one where we 'Do Not Take' it.
     * This is a conceptual twin to the loop-based backtracking, yielding the same complexity.
     */
    public List<List<Integer>> subsets_TakeNoTake(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // Kick-off the recursive process.
        takeNoTake(nums, res, new ArrayList<>(), 0);
        return res;
    }

    /**
     * Recursive helper function for the Take/No-Take approach.
     * @param nums The input array.
     * @param res The list to store all final subsets.
     * @param curr The current subset being built in the DFS path.
     * @param index The index of the element currently under consideration.
     */
    private void takeNoTake(int[] nums, List<List<Integer>> res, List<Integer> curr, int index) {
        // Base Case: If we've considered all elements, the current 'curr' is a complete subset.
        if (index == nums.length) {
            // We finalize the subset and add a copy to the results.
            res.add(new ArrayList<>(curr));
            return;
        }

        // --- Decision 1: LEAVE IT (NO-TAKE) ---
        // We move to the next element (index + 1) without adding nums[index] to 'curr'.
        takeNoTake(nums, res, curr, index + 1);


        // --- Decision 2: TAKE IT ---
        // 1. Choose: Include nums[index].
        curr.add(nums[index]);

        // 2. Explore: Move to the next element (index + 1).
        takeNoTake(nums, res, curr, index + 1);

        // 3. Unchoose (Backtrack): Remove the element to reset the state
        // for the parent call's next decision/return.
        curr.remove(curr.size() - 1);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 2, 3};
        System.out.println("Input Array: " + Arrays.toString(nums));
        System.out.println("-------------------------------------------------------");

        // Test Method 1: Backtracking (Loop-Based)
        List<List<Integer>> result1 = sol.subsets_Backtracking(nums);
        System.out.println("✅ Approach 1 (Backtracking): " + result1);
        System.out.println("Time Complexity: O(2^N * N)");
        System.out.println("---");

        // Test Method 2: Iterative (Layer-by-Layer Growth)
        List<List<Integer>> result2 = sol.subsets_Iterative(nums);
        System.out.println("✅ Approach 2 (Iterative): " + result2);
        System.out.println("Time Complexity: O(2^N * N)");
        System.out.println("---");

        // Test Method 3: Recursive (Take/No-Take)
        List<List<Integer>> result3 = sol.subsets_TakeNoTake(nums);
        System.out.println("✅ Approach 3 (Take/No-Take): " + result3);
        System.out.println("Time Complexity: O(2^N * N)");
        System.out.println("-------------------------------------------------------");
        //
    }
}