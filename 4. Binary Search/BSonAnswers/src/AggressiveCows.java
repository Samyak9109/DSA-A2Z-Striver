import java.util.*;
//leetCode 1552 have same solution
// 🐮 A G G R E S S I V E   C O W S 🐮
// Problem: Place 'k' cows in 'n' stalls such that the minimum distance between any two cows is maximized.

/*
===========================================================
🧠 Time & Space Complexities Overview:
-----------------------------------------------------------
1️⃣ Brute Force:
   ⏱️ Time: O((maxDist - minDist + 1) * n)  → Check all distances
   💾 Space: O(1)
   ⚙️ Reason: For each possible distance, check feasibility (O(n)).

2️⃣ Better Approach:
   ⏱️ Time: O(n log n + n * log(maxDist))
   💾 Space: O(1)
   ⚙️ Reason: Binary Search on distance + feasibility check.

3️⃣ Optimal (same as Better actually):
   🚀 This is the final optimized version used in competitive coding.
===========================================================
*/

public class AggressiveCows {

    // ======================================================
    // 🧩 BRUTE FORCE APPROACH
    // ======================================================
    public int bruteForce(int[] stalls, int k) {
        Arrays.sort(stalls); // Sort the stalls for distance calculations
        int n = stalls.length;

        int minDist = 1;
        int maxDist = stalls[n - 1] - stalls[0];
        int ans = 0;

        // Try every possible distance between min and max
        for (int dist = minDist; dist <= maxDist; dist++) {
            if (canWePlace(stalls, dist, k)) {
                ans = dist; // possible → try next
            } else {
                break; // once we can't place, stop checking
            }
        }
        return ans;
    }

    // ======================================================
    // ⚙️ HELPER FUNCTION FOR ALL APPROACHES
    // ======================================================
    public static boolean canWePlace(int[] stalls, int dist, int cows) {
        int countCows = 1; // first cow in first stall
        int last = stalls[0]; // track last cow's stall position

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - last >= dist) {
                countCows++;
                last = stalls[i];
            }
            if (countCows >= cows) return true; // all cows placed
        }
        return false;
    }

    // ======================================================
    // 💪 BETTER / OPTIMAL APPROACH → Binary Search on Distance
    // ======================================================
    public int aggressiveCows(int[] stalls, int k) {
        int n = stalls.length;
        Arrays.sort(stalls);

        int low = 1; // min possible distance
        int high = stalls[n - 1] - stalls[0]; // max possible distance

        // Binary Search on the answer (distance)
        while (low <= high) {
            int mid = (low + high) / 2;

            if (canWePlace(stalls, mid, k)) {
                low = mid + 1; // mid works → try for larger distance
            } else {
                high = mid - 1; // mid doesn’t work → reduce distance
            }
        }

        return high; // max distance that worked
    }

    // ======================================================
    // 🧪 MAIN METHOD FOR TESTING ALL APPROACHES
    // ======================================================
    static void main(String[] args) {
        AggressiveCows obj = new AggressiveCows();

        int[] stalls = {1, 2, 8, 4, 9};
        int k = 3;

        System.out.println("🔥 STALLS: " + Arrays.toString(stalls));
        System.out.println("🐄 Number of cows: " + k);

        int bruteAns = obj.bruteForce(stalls.clone(), k);
        int optimalAns = obj.aggressiveCows(stalls.clone(), k);

        System.out.println("\n====================== RESULTS ======================");
        System.out.println("🧱 Brute Force → Max Min Distance: " + bruteAns);
        System.out.println("⚡ Optimal (Binary Search) → Max Min Distance: " + optimalAns);
        System.out.println("=====================================================");
    }
}
