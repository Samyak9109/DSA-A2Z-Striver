import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NextPermutation {
    // Brute Force Solution:
    // Approach: Generate all permutations of the array, sort them lexicographically,
    // and find the next one after the current permutation.
    // Time Complexity: O(n! * n) due to generating all permutations and comparisons.
    // Space Complexity: O(n! * n) for storing all permutations.
    public void nextPermutationBruteForce(int[] nums) {
        List<int[]> allPermutations = new ArrayList<>();
        generatePermutations(nums, 0, allPermutations);
        allPermutations.sort((a, b) -> {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) return a[i] - b[i];
            }
            return 0;
        });

        int idx = -1;
        for (int i = 0; i < allPermutations.size(); i++) {
            if (Arrays.equals(nums, allPermutations.get(i))) {
                idx = i;
                break;
            }
        }

        if (idx == allPermutations.size() - 1) {
            System.arraycopy(allPermutations.get(0), 0, nums, 0, nums.length);
        } else {
            System.arraycopy(allPermutations.get(idx + 1), 0, nums, 0, nums.length);
        }
    }

    private void generatePermutations(int[] arr, int start, List<int[]> allPermutations) {
        if (start == arr.length) {
            allPermutations.add(arr.clone());
            return;
        }
        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            generatePermutations(arr, start + 1, allPermutations);
            swap(arr, start, i);
        }
    }

    // Optimal Solution:
    // Approach: Find the first decreasing element from the right,
    // swap it with the next larger element on the right, and reverse the suffix.
    // Time Complexity: O(n), since only a few linear scans are needed.
    // Space Complexity: O(1), operates in place without extra memory.
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // Step 1: Find the first element from the right which is smaller than its next
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            // Step 2: Find element just larger than nums[i] from the right
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j); // Step 3: Swap
        }

        // Step 4: Reverse the suffix starting i+1
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    // Main method to test both solutions
    public static void main(String[] args) {
        NextPermutation sol = new NextPermutation();

        // Test cases
        int[][] testCases = {
                {1, 2, 3},
                {3, 2, 1},
                {1, 1, 5}
        };

        System.out.println("Testing Brute Force Next Permutation:");
        for (int[] test : testCases) {
            int[] nums = test.clone();
            sol.nextPermutationBruteForce(nums);
            System.out.println(Arrays.toString(nums));
        }

        System.out.println("\nTesting Optimal Next Permutation:");
        for (int[] test : testCases) {
            int[] nums = test.clone();
            sol.nextPermutation(nums);
            System.out.println(Arrays.toString(nums));
        }
    }
}
