import java.util.*;

public class MinMaxGasStation {

    // ---------------------------------------------------------------------------
    // 🧠 BRUTE FORCE APPROACH — Simulate adding one station at a time
    // ---------------------------------------------------------------------------
    /*
     * 🧩 Idea:
     * - For each of the K new stations:
     *   → Find the segment (gap between two existing stations) that currently has the largest distance.
     *   → Add one gas station inside that segment (thus splitting it further).
     *   → Recompute the largest gap.
     * - After placing all K stations, find the largest remaining distance — that’s our minimized max distance.
     *
     * 🕒 Time Complexity: O(N * K)
     *    - For each of the K stations, we scan all N-1 gaps.
     * 💾 Space Complexity: O(N)
     *    - Uses an array to track how many extra stations we’ve added in each segment.
     */
    public static double minimiseMaxDistanceBrute(int[] arr, int k) {
        int n = arr.length;
        int[] howMany = new int[n - 1]; // howMany[i] → number of gas stations added in the i-th segment

        // Repeat process for K gas stations
        for (int gasStations = 1; gasStations <= k; gasStations++) {
            double maxSection = -1; // Track largest segment distance
            int maxInd = -1;        // Index of that largest segment

            // Step 1️⃣: Find the segment with the maximum section length
            for (int i = 0; i < n - 1; i++) {
                double diff = arr[i + 1] - arr[i];           // Distance between current pair
                double sectionLength = diff / (howMany[i] + 1); // Split by (stations already added + 1)
                if (sectionLength > maxSection) {
                    maxSection = sectionLength;
                    maxInd = i;
                }
            }

            // Step 2️⃣: Add one station to that max segment
            howMany[maxInd]++;
        }

        // Step 3️⃣: After placing all K stations, compute final max segment
        double maxAns = -1;
        for (int i = 0; i < n - 1; i++) {
            double diff = arr[i + 1] - arr[i];
            double sectionLength = diff / (howMany[i] + 1);
            maxAns = Math.max(maxAns, sectionLength);
        }

        return maxAns;
    }

    // ---------------------------------------------------------------------------
    // ⚙️ BETTER APPROACH — Using Max Heap (Priority Queue)
    // ---------------------------------------------------------------------------
    /*
     * 🧩 Idea:
     * - Instead of scanning all segments each time (which is O(N*K)),
     *   we can use a **max-heap** (priority queue) that always gives us the largest segment instantly.
     * - For each added station:
     *   → Pop the largest segment from PQ.
     *   → Add one more station in that segment.
     *   → Compute its new divided length.
     *   → Push it back to PQ.
     *
     * 🕒 Time Complexity: O(K * log N)
     * 💾 Space Complexity: O(N)
     *
     * This is way more scalable for large K.
     */
    static class Pair {
        double first;  // The current max distance of the segment
        int second;    // Index of the segment
        Pair(double f, int s) {
            first = f;
            second = s;
        }
    }

    public static double minimiseMaxDistanceBetter(int[] arr, int k) {
        int n = arr.length;
        int[] howMany = new int[n - 1];

        // Create a max-heap (PQ) based on distance (larger first)
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Double.compare(b.first, a.first));

        // Step 1️⃣: Initialize PQ with all initial segment distances
        for (int i = 0; i < n - 1; i++) {
            pq.add(new Pair(arr[i + 1] - arr[i], i));
        }

        // Step 2️⃣: Add each gas station optimally
        for (int gasStations = 1; gasStations <= k; gasStations++) {
            // Take the largest current segment
            Pair top = pq.poll();
            int secInd = top.second;

            // Add a station there
            howMany[secInd]++;

            // Recalculate the new segment length after insertion
            double originalDist = arr[secInd + 1] - arr[secInd];
            double newLen = originalDist / (howMany[secInd] + 1);

            // Push back the updated segment into PQ
            pq.add(new Pair(newLen, secInd));
        }

        // Step 3️⃣: The top of PQ gives us the minimized max distance
        return pq.peek().first;
    }

    // ---------------------------------------------------------------------------
    // 🚀 OPTIMAL APPROACH — Binary Search on Answer
    // ---------------------------------------------------------------------------
    /*
     * 🧩 Intuition:
     * - The answer (minimum max distance) lies between [0, max_gap].
     * - We can check for a given distance "mid" if we can place ≤ K stations
     *   so that no two adjacent stations are more than "mid" apart.
     *
     * - If we need MORE than K → mid is too small → increase it (low = mid)
     * - If we can do it with ≤ K → mid might be reduced → decrease it (high = mid)
     *
     * 🔥 This is “Binary Search on Real Values”.
     *
     * 🕒 Time Complexity: O(N * log(1e6)) ≈ O(N * 60)
     * 💾 Space Complexity: O(1)
     */

    // Helper to calculate how many stations are required for a given max allowed distance
    public static int numberOfGasStationsRequired(double dist, int[] arr) {
        int n = arr.length;
        int count = 0;
        for (int i = 1; i < n; i++) {
            double gap = arr[i] - arr[i - 1];   // Gap between stations
            int stations = (int)(gap / dist);   // Number of stations to fill this gap

            // If gap divides perfectly, remove one redundant station
            if (gap == stations * dist) stations--;
            count += stations;
        }
        return count;
    }

    public static double minimiseMaxDistanceOptimal(int[] arr, int k) {
        int n = arr.length;
        double low = 0, high = 0;

        // Step 1️⃣: Determine the search space (0 → largest existing gap)
        for (int i = 0; i < n - 1; i++) {
            high = Math.max(high, arr[i + 1] - arr[i]);
        }

        double diff = 1e-6; // precision (to 6 decimal places)

        // Step 2️⃣: Binary search on the real number space
        while (high - low > diff) {
            double mid = (low + high) / 2.0;
            int required = numberOfGasStationsRequired(mid, arr);

            if (required > k)
                low = mid;   // too many stations needed → mid too small
            else
                high = mid;  // feasible → try smaller max distance
        }

        // Step 3️⃣: Return minimized max distance
        return high;
    }

    // ---------------------------------------------------------------------------
    // 🧩 MAIN METHOD — Testing all three approaches
    // ---------------------------------------------------------------------------
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4;

        System.out.println("🚧 Brute Force Answer: " + minimiseMaxDistanceBrute(arr, k));
        System.out.println("⚙️ Better (PQ) Answer: " + minimiseMaxDistanceBetter(arr, k));
        System.out.println("🚀 Optimal (Binary Search) Answer: " + minimiseMaxDistanceOptimal(arr, k));
    }
}
