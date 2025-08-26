//This is for the case when pos == negative
public class RearrangeArray {

    // Brute force approach
    // Time Complexity: O(n) - two passes over the array (one to separate, one to merge)
    // Space Complexity: O(n) - extra arrays to store positive and negative numbers separately
    public int[] rearrangeArrayBruteForce(int[] nums) {
        int n = nums.length;
        int[] pos = new int[n / 2];
        int[] neg = new int[n / 2];
        int posIndex = 0, negIndex = 0;

        // Separate positives and negatives
        for (int num : nums) {
            if (num > 0) pos[posIndex++] = num;
            else neg[negIndex++] = num;
        }

        int[] result = new int[n];
        posIndex = 0;
        negIndex = 0;

        // Place positives and negatives alternately starting with positive
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                result[i] = pos[posIndex++];
            } else {
                result[i] = neg[negIndex++];
            }
        }
        return result;
    }

    // Optimal approach
    // Time Complexity: O(n) - single pass over the array
    // Space Complexity: O(n) - one result array of the same size
    public int[] rearrangeArrayOptimal(int[] nums) {
        int[] result = new int[nums.length];
        int posIndex = 0, negIndex = 1;

        for (int num : nums) {
            if (num > 0) {
                result[posIndex] = num;
                posIndex += 2;
            } else {
                result[negIndex] = num;
                negIndex += 2;
            }
        }

        return result;
    }

    // Main method to test both implementations
    public static void main(String[] args) {
        RearrangeArray solution = new RearrangeArray();
        int[] nums1 = {3, 1, -2, -5, 2, -4};
        int[] nums2 = {-1, 1};

        int[] resultBruteForce1 = solution.rearrangeArrayBruteForce(nums1);
        int[] resultOptimal1 = solution.rearrangeArrayOptimal(nums1);
        int[] resultBruteForce2 = solution.rearrangeArrayBruteForce(nums2);
        int[] resultOptimal2 = solution.rearrangeArrayOptimal(nums2);

        System.out.println("Brute Force Result 1: ");
        printArray(resultBruteForce1);
        System.out.println("Optimal Result 1: ");
        printArray(resultOptimal1);

        System.out.println("Brute Force Result 2: ");
        printArray(resultBruteForce2);
        System.out.println("Optimal Result 2: ");
        printArray(resultOptimal2);
    }

    // Helper method to print arrays
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
