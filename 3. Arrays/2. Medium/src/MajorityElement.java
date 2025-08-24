import java.util.HashMap;
import java.util.Map;

// Find the element that appears more than n/2 times
public class MajorityElement {

    // Brute force approach:
    // For each element, count how many times it appears by checking all elements.
    // Time complexity: O(n^2) because for each element we count occurrences in O(n).
    // Space complexity: O(1) as no extra space is used.
    static int majorityElementBrute(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (arr[j] == arr[i]) count++;
            }
            if (count > n / 2) return arr[i];
        }
        return -1;
    }

    // Better approach using HashMap:
    // Count the frequency of each element in one pass and then check for majority.
    // Time complexity: O(n) because we traverse array twice (once for counting, once for checking).
    // Space complexity: O(n) in worst case when all elements are distinct.
    static int majorityElementBetter(int[] arr) {
        HashMap<Integer, Integer> mpp = new HashMap<>();
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int value = mpp.getOrDefault(arr[i], 0);
            mpp.put(arr[i], value + 1);
        }
        for (Map.Entry<Integer, Integer> it : mpp.entrySet()) {
            if (it.getValue() > (n / 2)) return it.getKey();
        }
        return -1;
    }

    // Optimal approach using Moore's Voting Algorithm:
    // First pass: find a candidate for majority element.
    // Second pass: verify if candidate appears more than n/2 times.
    // Time complexity: O(n) as we do two passes over the array.
    // Space complexity: O(1) since only a few variables are used.
    static int majorityElementOptimal(int[] nums) {
        int n = nums.length, count = 0, ele = 0;

        // Find candidate for majority element
        for (int i = 0; i < n; i++) {
            if (count == 0) {
                count = 1;
                ele = nums[i];
            } else if (ele == nums[i]) {
                count++;
            } else {
                count--;
            }
        }

        // Verify candidate
        int cnt1 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == ele) cnt1++;
        }
        if (cnt1 > (n / 2)) return ele;
        return -1;
    }

    public static void main(String args[]) {
        int[] arr = {2, 2, 1, 1, 1, 2, 2};

        int ans = majorityElementBrute(arr);
        int ansBetter = majorityElementBetter(arr);
        int ansOptimal = majorityElementOptimal(arr);

        System.out.println("The majority element (Brute) is: " + ans);
        System.out.println("The majority element (Better) is: " + ansBetter);
        System.out.println("The majority element (Optimal) is: " + ansOptimal);
    }
}
