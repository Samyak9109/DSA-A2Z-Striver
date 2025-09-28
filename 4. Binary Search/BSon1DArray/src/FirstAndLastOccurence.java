public class FirstAndLastOccurence {
    static int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int lb = lowerBound(nums, target);
        if (lb == n || nums[lb] != target) return new int[]{-1, -1};
        return new int[]{lb, (upperBound(nums, target)-1)};
    }
    static int lowerBound(int[] nums, int target) {
        int n = nums.length;
        int ans = n; // default answer if no element >= target
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // avoids overflow

            if (nums[mid] >= target) {
                ans = mid;        // potential lower bound found
                high = mid - 1;   // look left to find an earlier occurrence
            } else {
                low = mid + 1;    // target must be on the right
            }
        }

        return ans;
    }

    static int upperBound(int[] nums, int target) {
        int n = nums.length;
        int ans = n; // default if no element > target
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // prevents overflow

            if (nums[mid] > target) {
                ans = mid;        // potential answer
                high = mid - 1;   // search left
            } else {
                low = mid + 1;    // search right
            }
        }

        return ans;
    }

    //******************** USING PLAIN BINARY SEARCH *********************\\

    public int[] searchRange2(int[] nums, int target) {
        int first = findFirst(nums, target);
        int last = findLast(nums, target);

        return new int[]{first, last};
    }

    // Binary Search for first occurrence
    private int findFirst(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int ans = -1; // default if not found

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                ans = mid;       // record answer
                high = mid - 1;  // look left for earlier occurrence
            } else if (nums[mid] < target) {
                low = mid + 1;   // go right
            } else {
                high = mid - 1;  // go left
            }
        }
        return ans;
    }

    // Binary Search for last occurrence
    private int findLast(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                ans = mid;      // record answer
                low = mid + 1;  // look right for later occurrence
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}
