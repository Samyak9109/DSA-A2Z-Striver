import java.util.*;

public class ThreeSumProblem {

    // Brute-force approach: Triple nested loops to check all triplets
    // Time Complexity: O(n^3) due to three nested loops
    // Space Complexity: O(m) for storing unique triplets in Set, where m is number of unique triplets
    public List<List<Integer>> threeSumBrute(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> res = new HashSet<>();

        for (int i = 0; i < n - 2; i++) {
            for (int j = i+1; j < n - 1; j++) {
                for (int k = j+1; k < n; k++) {
                    if ((nums[i] + nums[j] + nums[k]) == 0) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(triplet); // for consistent ordering
                        res.add(triplet);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    // Better approach: Use HashSet and hashing to find third element
    // Time Complexity: O(n^2) - two nested loops and O(1) average HashSet lookups
    // Space Complexity: O(m + n), m for results and n for inner HashSet
    public List<List<Integer>> threeSumBetter(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> st = new HashSet<>();

        for (int i = 0; i < n; i++) {
            Set<Integer> hashset = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                int third = -(nums[i] + nums[j]);
                if (hashset.contains(third)) {
                    List<Integer> temp = Arrays.asList(nums[i], nums[j], third);
                    temp.sort(null);
                    st.add(temp);
                }
                hashset.add(nums[j]);
            }
        }
        return new ArrayList<>(st);
    }

    // Optimal approach: Sort input and use two pointers
    // Time Complexity: O(n^2) due to sorting + two-pointer scan for each element
    // Space Complexity: O(m) for storing unique triplets
    public List<List<Integer>> threeSumOptimal(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue; // skip duplicates

            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < 0) {
                    j++;
                } else if (sum > 0) {
                    k--;
                } else {
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) j++; // skip duplicates
                    while (j < k && nums[k] == nums[k + 1]) k--; // skip duplicates
                }
            }
        }
        return ans;
    }

    // Main method to test all three approaches
    public static void main(String[] args) {
        ThreeSumProblem solver = new ThreeSumProblem();

        int[] nums = {-1, 0, 1, 2, -1, -4};

        System.out.println("Input array: " + Arrays.toString(nums));

        List<List<Integer>> bruteResult = solver.threeSumBrute(nums);
        System.out.println("Brute-force result: " + bruteResult);

        List<List<Integer>> betterResult = solver.threeSumBetter(nums);
        System.out.println("Better (HashSet) result: " + betterResult);

        List<List<Integer>> optimalResult = solver.threeSumOptimal(nums);
        System.out.println("Optimal (Two-Pointer) result: " + optimalResult);
    }
}
