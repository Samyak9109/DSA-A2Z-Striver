import java.util.HashMap;
import java.util.Map;

public class NumOfSubarrayXORK {

    /**
     * Brute Force Approach
     * -------------------
     * Time Complexity: O(n^3)
     *   - Three nested loops: the outer two generate all possible subarrays,
     *     and the innermost one recalculates the XOR for each subarray.
     * Space Complexity: O(1)
     *   - Only a fixed number of variables are used, regardless of input size.
     *
     * Approach: Try every possible subarray and explicitly calculate the XOR of its elements.
     * Increment count if the XOR matches k.
     */
    public static int subarraysWithXorKBrute(int[] a, int k) {
        int n = a.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int xorr = 0;
                for (int K = i; K <= j; K++) {
                    xorr = xorr ^ a[K];
                }
                if (xorr == k) cnt++;
            }
        }
        return cnt;
    }

    /**
     * Better Approach
     * --------------
     * Time Complexity: O(n^2)
     *   - Outer loop picks subarray start, inner loop expands the subarray.
     *   - Running XOR is maintained so no need to recalculate for every new subarray end.
     * Space Complexity: O(1)
     *   - Uses only a constant number of extra variables.
     *
     * Approach: For every start index, maintain a running XOR for the subarray as you expand it; increment count if running XOR equals k.
     */
    public static int subarraysWithXorKBetter(int[] a, int k) {
        int n = a.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int xorr = 0;
            for (int j = i; j < n; j++) {
                xorr = xorr ^ a[j];
                if (xorr == k) cnt++;
            }
        }
        return cnt;
    }

    /**
     * Optimal Approach (Prefix XOR + HashMap)
     * ---------------------------------------
     * Time Complexity: O(n)
     *   - Single traversal of the array.
     *   - Each prefix XOR insertion and lookup is O(1) on average.
     * Space Complexity: O(n)
     *   - In the worst case, all prefix XORs are unique and stored in the HashMap.
     *
     * Approach: Maintain a running prefix XOR and store its occurrence count in a map.
     * If (current prefix XOR ^ k) has been seen before, the corresponding number of subarrays end here.
     */
    public static int subarraysWithXorKOptimal(int[] a, int k) {
        int n = a.length;
        int xr = 0;
        Map<Integer, Integer> mpp = new HashMap<>();
        mpp.put(xr, 1);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            xr = xr ^ a[i];
            int x = xr ^ k;
            if (mpp.containsKey(x)) {
                cnt += mpp.get(x);
            }
            mpp.put(xr, mpp.getOrDefault(xr, 0) + 1);
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 6, 4};
        int k = 6;

        System.out.println("Brute Force Count: " + subarraysWithXorKBrute(arr, k));   // Expected: 4
        System.out.println("Better Count: " + subarraysWithXorKBetter(arr, k));       // Expected: 4
        System.out.println("Optimal Count: " + subarraysWithXorKOptimal(arr, k));     // Expected: 4
    }

}
