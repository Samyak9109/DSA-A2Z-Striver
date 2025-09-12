import java.util.*;

public class FourSumProblem {
    /**
     * Better Approach: Hashing with three loops.
     * Time Complexity: O(n^3 * log k)
     *      - Three nested loops: O(n^3)
     *      - Each quadruplet is sorted and added to a Set: O(log k), where k = number of unique quadruplets.
     * Space Complexity: O(k) for storing unique quadruplets in the result Set.
     */
    public List<List<Integer>> fourSumBetter(int[] nums, int target) {
        int n = nums.length;
        Set<List<Integer>> st = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Set<Long> hashset = new HashSet<>();
                for (int k = j + 1; k < n; k++) {
                    // Use long to avoid integer overflow
                    long sum = nums[i] + nums[j] + nums[k];
                    long fourth = target - sum;
                    if (hashset.contains(fourth)) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        temp.add((int) fourth);
                        temp.sort(Integer::compareTo);
                        st.add(temp); // O(log k): Set insertion
                    }
                    hashset.add((long) nums[k]);
                }
            }
        }
        // O(k): result collection
        return new ArrayList<>(st);
    }

    /**
     * Optimal Approach: Sort + Two pointers
     * Time Complexity: O(n^3)
     *      - Two outer loops for first two numbers: O(n^2)
     *      - Two pointers inside for third and fourth: O(n)
     *      - Overall: O(n^3)
     * Space Complexity: O(k) for storing quadruplets.
     */
    public static List<List<Integer>> fourSumOptimal(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); // O(n log n)

        for (int i = 0; i < n; i++) {
            // Skip duplicates for i
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n; j++) {
                // Skip duplicates for j
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // Two pointers for third and fourth elements
                int k = j + 1, l = n - 1;
                while (k < l) {
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        k++; l--;
                        while (k < l && nums[k] == nums[k - 1]) k++; // Skip duplicates for k
                        while (k < l && nums[l] == nums[l + 1]) l--; // Skip duplicates for l
                    } else if (sum < target) k++;
                    else l--;
                }
            }
        }
        return ans;
    }

    // Main method for demonstration
    public static void main(String[] args) {
        FourSumProblem solver = new FourSumProblem();
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;

        System.out.println("FourSum Better Approach:");
        List<List<Integer>> betterResult = solver.fourSumBetter(nums, target);
        for (List<Integer> quad : betterResult) {
            System.out.println(quad);
        }

        System.out.println("\nFourSum Optimal Approach:");
        List<List<Integer>> optimalResult = solver.fourSumOptimal(nums, target);
        for (List<Integer> quad : optimalResult) {
            System.out.println(quad);
        }
    }
}
