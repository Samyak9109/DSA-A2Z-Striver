import java.util.*;

public class MajorityElement {

    // Brute-force approach: For each unique element, count its frequency by iterating through the array again.
    // If count > n/3, add to result. Stops searching if two such elements found (max 2 can appear > n/3 times).
    // Time Complexity: O(n^2)
    // Space Complexity: O(1) excluding output list
    public List<Integer> majorityElementBrute(int[] nums) {
        int n = nums.length;
        List<Integer> lst = new ArrayList<>();

        for (int num : nums) {
            if (lst.isEmpty() || lst.getFirst() != num) {
                int count = 0;
                for (int i : nums) {
                    if (i == num) count++;
                }
                if (count > (int) n / 3) lst.add(num);
            }
            if (lst.size() == 2) break;
        }
        return lst;
    }

    // Better approach: Count frequencies of each number using a HashMap,
    // then collect those with count > n/3.
    // Time Complexity: O(n)
    // Space Complexity: O(n) for HashMap and output list
    public List<Integer> majorityElementBetter(int[] nums) {
        int n = nums.length;
        List<Integer> lst = new ArrayList<>();
        HashMap<Integer, Integer> mpp = new HashMap<>();
        int threshold = n / 3;

        for (int num : nums) {
            mpp.put(num, mpp.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : mpp.entrySet()) {
            if (entry.getValue() > threshold) {
                lst.add(entry.getKey());
            }
        }
        return lst;
    }

    // Optimal approach: Boyer-Moore Voting variant for finding elements appearing more than n/3 times.
    // First pass finds up to two candidates.
    // Second pass verifies their counts.
    // Time Complexity: O(n)
    // Space Complexity: O(1) excluding output list
    public List<Integer> majorityElementOptimal(int[] nums) {
        int n = nums.length;
        int cnt1 = 0, cnt2 = 0;
        int ele1 = Integer.MIN_VALUE;
        int ele2 = Integer.MIN_VALUE;

        for (int j : nums) {
            if (cnt1 == 0 && ele2 != j) {
                cnt1 = 1;
                ele1 = j;
            } else if (cnt2 == 0 && ele1 != j) {
                cnt2 = 1;
                ele2 = j;
            } else if (ele1 == j) cnt1++;
            else if (ele2 == j) cnt2++;
            else {
                cnt1--;
                cnt2--;
            }
        }

        List<Integer> lst = new ArrayList<>();
        cnt1 = 0; cnt2 = 0;

        for (int num : nums) {
            if (num == ele1) cnt1++;
            if (num == ele2) cnt2++;
        }

        int threshold = (int)(n / 3) + 1;
        if (cnt1 >= threshold) lst.add(ele1);
        if (cnt2 >= threshold) lst.add(ele2);

        return lst;
    }

    // Main method to test all three implementations with sample input
    public static void main(String[] args) {
        MajorityElement solver = new MajorityElement();

        int[] nums = {1, 2, 3, 2, 2, 1, 1, 1};

        System.out.println("Input array: " + Arrays.toString(nums));

        List<Integer> bruteResult = solver.majorityElementBrute(nums);
        System.out.println("Brute-force result: " + bruteResult);

        List<Integer> betterResult = solver.majorityElementBetter(nums);
        System.out.println("Better (HashMap) result: " + betterResult);

        List<Integer> optimalResult = solver.majorityElementOptimal(nums);
        System.out.println("Optimal (Boyer-Moore) result: " + optimalResult);
    }
}
