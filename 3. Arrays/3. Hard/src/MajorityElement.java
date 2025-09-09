import java.util.*;


public class MajorityElement {
    public List<Integer> majorityElementBrute(int[] nums) {
        int n = nums.length;
        List<Integer> lst = new ArrayList<>();

        for (int i = 0; i < n; i++) {
             if (lst.isEmpty() || lst.get(0) != nums[i]) {
                 int count = 0;
                 for (int j = 0; j < n; j++) {
                     if (nums[j] == nums[i]) count++;
                 }
                 if (count > (int)n/3)lst.add(nums[i]);
             }
             if (lst.size() == 2) break;
        }
        return lst;
    }

    public List<Integer> majorityElementBetter(int[] nums) {
        int n = nums.length;
        List<Integer> lst = new ArrayList<>();
        HashMap<Integer, Integer> mpp = new HashMap<>();
        int threshold = n / 3;

        // Count frequencies of each element
        for (int i = 0; i < n; i++) {
            mpp.put(nums[i], mpp.getOrDefault(nums[i], 0) + 1);
        }

        // Add all elements exceeding threshold to result list
        for (Map.Entry<Integer, Integer> entry : mpp.entrySet()) {
            if (entry.getValue() > threshold) {
                lst.add(entry.getKey());
            }
        }

        return lst;
    }

    public List<Integer> majorityElementOptimal(int[] nums) {
        int n = nums.length;

        int cnt1 = 0, cnt2 = 0;
        int ele1 = Integer.MIN_VALUE;
        int ele2 = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++){
           if (cnt1 == 0 && ele2 != nums[i]) {
               cnt1 = 1;
               ele1 = nums[i];
           } else if (cnt2 == 0 && ele1 != nums[i]) {
               cnt2 = 1;
               ele2 = nums[i];
           } else if (cnt1 == nums[i]) cnt1++;
           else if(cnt2 == nums[i]) cnt2++;
           else {
               cnt1--;
               cnt2--;
           }
        }

        List<Integer> lst = new ArrayList<>(); // list of answers

        // Manually check if the stored elements in
        // el1 and el2 are the majority elements:
        cnt1 = 0; cnt2 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == ele1) cnt1++;
            if (nums[i] == ele2) cnt2++;
        }

        int mini = (int)(n / 3) + 1;
        if (cnt1 >= mini) lst.add(ele1);
        if (cnt2 >= mini) lst.add(ele2);

        return lst;
    }
}
