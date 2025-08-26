public class RearrangeArrayTwo {

    // Brute Force Solution
    // Method: Separate positives and negatives into separate arrays.
    // Then merge them by alternating elements and append leftovers at the end.
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int[] rearrangeArrayBruteForce(int[] nums) {
        int n = nums.length;
        int posCount = 0, negCount = 0;
        for (int num : nums) {
            if (num >= 0) posCount++;
            else negCount++;
        }
        int[] pos = new int[posCount];
        int[] neg = new int[negCount];
        int pi = 0, ni = 0;
        for (int num : nums) {
            if (num >= 0) pos[pi++] = num;
            else neg[ni++] = num;
        }
        int[] result = new int[n];
        pi = 0;
        ni = 0;
        int i = 0;
        while (pi < posCount && ni < negCount) {
            result[i++] = pos[pi++];
            result[i++] = neg[ni++];
        }
        while (pi < posCount) result[i++] = pos[pi++];
        while (ni < negCount) result[i++] = neg[ni++];
        return result;
    }

    // Optimal In-place Solution
    // Method:
    // 1. Traverse to find out-of-place elements (wrong sign at wrong index).
    // 2. Rotate the sub-array between out-of-place element and next opposite sign element.
    // Time Complexity: O(n^2) in worst case due to rotation.
    // Space Complexity: O(1)
    public void rearrangeArrayOptimal(int[] nums) {
        int n = nums.length;
        int outOfPlace = -1;

        for (int i = 0; i < n; i++) {
            if (outOfPlace >= 0) {
                if ((nums[i] >= 0 && nums[outOfPlace] < 0) ||
                        (nums[i] < 0 && nums[outOfPlace] >= 0)) {
                    rightRotate(nums, outOfPlace, i);
                    if (i - outOfPlace > 2)
                        outOfPlace += 2;
                    else
                        outOfPlace = -1;
                }
            } else {
                if (((nums[i] >= 0) && (i % 2 == 1)) || ((nums[i] < 0) && (i % 2 == 0))) {
                    outOfPlace = i;
                }
            }
        }
    }

    // Helper method to right rotate the subarray between start and end indices
    private void rightRotate(int[] nums, int start, int end) {
        int temp = nums[end];
        for (int i = end; i > start; i--) {
            nums[i] = nums[i - 1];
        }
        nums[start] = temp;
    }

    // Utility method to print arrays
    public void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Main method to test both solutions
    public static void main(String[] args) {
        RearrangeArrayTwo obj = new RearrangeArrayTwo();

        int[] nums = {1, -1, 2, 3, -2, -3, 4};

        System.out.println("Original Array:");
        obj.printArray(nums);

        // Test brute force
        int[] rearrangedBrute = obj.rearrangeArrayBruteForce(nums);
        System.out.println("Brute Force Rearranged Array:");
        obj.printArray(rearrangedBrute);

        // For optimal, copy the array to avoid modifying original
        int[] numsCopy = nums.clone();
        obj.rearrangeArrayOptimal(numsCopy);
        System.out.println("Optimal In-place Rearranged Array:");
        obj.printArray(numsCopy);
    }
}
