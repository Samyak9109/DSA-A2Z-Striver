public class MinDaysForMBouquets {

    /*
      Time Complexity:
        - O(n log(maxDay - minDay)):
          Each binary search step takes O(n) for feasibility check.
        - n = number of flowers.

      Space Complexity:
        - O(1): only a few integer/long vars, no extra DS.
    */

    // Function to check if it's possible to make 'm' bouquets on a given 'day'
    public static boolean possible(int[] bloomDay, int day, int m, int k) {
        // bloomDay[i] → the day flower i blooms
        // day → the candidate day we’re testing feasibility for
        // m → number of bouquets required
        // k → number of consecutive flowers per bouquet

        int n = bloomDay.length;  // total flowers
        int flowers = 0;          // counter for consecutive bloomed flowers
        int bouquets = 0;         // number of bouquets formed so far

        for (int i = 0; i < n; i++) {
            if (bloomDay[i] <= day) {
                // flower bloomed by this day
                flowers++;
                if (flowers == k) {
                    bouquets++;      // we formed a bouquet
                    flowers = 0;     // reset streak
                }
            } else {
                // flower not bloomed yet, streak breaks
                flowers = 0;
            }
        }
        return bouquets >= m; // true if we can make at least m bouquets
    }

    // Main function to find the minimum number of days
    public static int minDays(int[] bloomDay, int m, int k) {
        // bloomDay → array of days each flower blooms
        // m → bouquets required
        // k → flowers needed per bouquet

        long totalFlowers = (long) m * k; // total flowers needed
        int n = bloomDay.length;          // total available flowers

        if (totalFlowers > n) return -1;  // not enough flowers → impossible

        // Find minimum and maximum bloom day
        int mini = Integer.MAX_VALUE; // earliest possible bloom day
        int maxi = Integer.MIN_VALUE; // latest possible bloom day
        for (int day : bloomDay) {
            mini = Math.min(mini, day);
            maxi = Math.max(maxi, day);
        }

        // Binary search between earliest and latest bloom day
        int low = mini;     // search start (earliest possible day)
        int high = maxi;    // search end (latest possible day)
        int ans = -1;       // variable to store the earliest feasible day

        while (low <= high) {
            int mid = low + (high - low) / 2; // candidate day

            if (possible(bloomDay, mid, m, k)) {
                ans = mid;       // store current feasible day
                high = mid - 1;  // try earlier days
            } else {
                low = mid + 1;   // need more days, go later
            }
        }
        return ans; // earliest day we can form m bouquets
    }

    // 🌹 Test driver
    public static void main(String[] args) {
        // Testcase 1
        int[] bloomDay1 = {7, 7, 7, 7, 13, 11, 12, 7};
        int m1 = 2, k1 = 3;
        System.out.println("Case 1 → " + minDays(bloomDay1, m1, k1));
        // Expected: 12

        // Testcase 2
        int[] bloomDay2 = {1, 10, 3, 10, 2};
        int m2 = 3, k2 = 1;
        System.out.println("Case 2 → " + minDays(bloomDay2, m2, k2));
        // Expected: 3

        // Testcase 3
        int[] bloomDay3 = {1, 10, 3, 10, 2};
        int m3 = 3, k3 = 2;
        System.out.println("Case 3 → " + minDays(bloomDay3, m3, k3));
        // Expected: -1
    }
}
