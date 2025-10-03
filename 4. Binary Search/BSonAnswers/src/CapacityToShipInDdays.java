public class CapacityToShipInDdays {

    /*
       ================================
       🕒 Time & 🗂️ Space Complexities
       ================================

       Brute Force:
         - Time: O((sum(weights) - maxWeight) * n)
           -> Trying every possible capacity from maxWeight to totalSum.
           -> For each capacity, we simulate shipping -> O(n).
         - Space: O(1) -> just counters.

       Optimal (Binary Search):
         - Time: O(n * log(sum(weights)))
           -> log(sum(weights)) for binary search range.
           -> Each mid-check takes O(n).
         - Space: O(1) -> only variables, no extra arrays.

       Forward-Looking POV:
       - Brute force is 💀 for large input.
       - Binary search scaling is 🔥, feels like a production-ready pipeline.
    */

    // ================================
    // 🚨 Brute Force Approach
    // ================================
    public static int shipWithinDaysBrute(int[] weights, int days) {
        int n = weights.length;
        int maxWeight = 0, totalSum = 0;

        // find the max single weight and total sum
        for (int w : weights) {
            totalSum += w;
            maxWeight = Math.max(maxWeight, w);
        }

        // check each possible capacity from maxWeight to totalSum
        for (int cap = maxWeight; cap <= totalSum; cap++) {
            int numDays = findDays(weights, cap);
            if (numDays <= days) return cap;
        }
        return -1; // should never hit here
    }

    // ================================
    // ⚡ Optimal Approach: Binary Search
    // ================================
    public static int shipWithinDays(int[] weights, int days) {
        int n = weights.length;
        int low = Integer.MIN_VALUE, high = 0;

        // find search space: max weight .. sum of weights
        for (int w : weights) {
            high += w;
            low = Math.max(low, w);
        }

        // binary search on capacity
        while (low <= high) {
            int mid = low + (high - low) / 2;

            int numOfDays = findDays(weights, mid);
            if (numOfDays <= days) {
                high = mid - 1; // try smaller capacity
            } else {
                low = mid + 1; // increase capacity
            }
        }
        return low;
    }

    // ================================
    // 🛠️ Helper: simulate shipping process
    // ================================
    static int findDays(int[] weights, int cap) {
        int days = 1; // start with day 1
        int load = 0;

        for (int w : weights) {
            if (load + w > cap) {
                days++;         // need a new day
                load = w;       // reset load with current weight
            } else {
                load += w;      // keep adding to current day
            }
        }
        return days;
    }

    // ================================
    // 🚀 Main Method (entry point)
    // ================================
    public static void main(String[] args) {
        int[] weights = {1,2,3,4,5,6,7,8,9,10};
        int days = 5;

        System.out.println("Brute Force Capacity: " + shipWithinDaysBrute(weights, days));
        System.out.println("Optimal Capacity: " + shipWithinDays(weights, days));
    }
}
