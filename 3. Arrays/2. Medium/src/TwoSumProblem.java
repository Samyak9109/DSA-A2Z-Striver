import java.util.Arrays;
import java.util.HashMap;

public class TwoSumProblem {

    // ----------------------------------------
    // Brute Force Method 1: Check all pairs and return "YES" or "NO"
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    // ----------------------------------------
    static String twoSumBruteMeth1(int[] nums, int target) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue; // skip same element
                if (nums[i] + nums[j] == target) return "YES"; // pair found
            }
        }
        return "NO"; // no pair found
    }

    // ----------------------------------------
    // Brute Force Method 2: Return indices of pair or [-1, -1]
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    // ----------------------------------------
    static int[] twoSumBruteMeth2(int[] nums, int target) {
        int n = nums.length;
        int[] ans = {-1, -1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && nums[i] + nums[j] == target) {
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
        }
        return ans;
    }

    // ----------------------------------------
    // Better Method 1: Using HashMap, return "Yes" or "No"
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    // ----------------------------------------
    static String twoSumBetterMeth1(int[] nums, int target) {
        HashMap<Integer, Integer> mpp = new HashMap<>();

        for (int num : nums) {
            int needed = target - num;
            if (mpp.containsKey(needed)) return "Yes";
            mpp.put(num, 1);
        }
        return "No";
    }

    // ----------------------------------------
    // Better Method 2: Using HashMap, return pair of elements or [-1, -1]
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    // ----------------------------------------
    static int[] twoSumBetterMeth2(int[] nums, int target) {
        HashMap<Integer, Integer> mpp = new HashMap<>();
        int[] ans = {-1, -1};

        for (int num : nums) {
            int needed = target - num;
            if (mpp.containsKey(needed)) {
                ans[0] = needed;
                ans[1] = num;
                return ans;
            }
            mpp.put(num, 1);
        }
        return ans;
    }

    // ----------------------------------------
    // Optimal Method: Sort + Two pointers, return "Yes" or "No"
    // Time Complexity: O(n log n)
    // Space Complexity: O(1)
    // ----------------------------------------
    static String twoSumOptimal(int[] nums, int target) {
        Arrays.sort(nums);  // sort the array

        int left = 0, right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) return "Yes";
            else if (sum < target) left++;
            else right--;
        }
        return "No";
    }

    // ----------------------------------------
    // Main method to test all implementations
    // ----------------------------------------
    public static void main(String[] args) {
        int[] arr = {2, 6, 5, 8, 11};
        int target = 14;

        System.out.println("Brute Method 1 (Yes/No): " + twoSumBruteMeth1(arr, target));

        int[] ans2 = twoSumBruteMeth2(arr, target);
        System.out.println("Brute Method 2 (Indices): [" + ans2[0] + ", " + ans2[1] + "]");

        System.out.println("Better Method 1 (Yes/No with HashMap): " + twoSumBetterMeth1(arr, target));

        int[] ans4 = twoSumBetterMeth2(arr, target);
        System.out.println("Better Method 2 (Values): [" + ans4[0] + ", " + ans4[1] + "]");

        System.out.println("Optimal Method (Two Pointers): " + twoSumOptimal(arr, target));
    }
}
