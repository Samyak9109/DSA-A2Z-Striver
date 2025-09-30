public class KokoEatingBanana {

    /*
      Time Complexity:
        - O(n log(maxPile)), where n = piles.length
        - Binary search takes log(maxPile) steps
        - Each step scans all piles → O(n)

      Space Complexity:
        - O(1), constant extra space
    */

    static int minEatingSpeed(int[] piles, int h) {
        int low = 1; // can't be 0, otherwise divide-by-zero
        int high = findMax(piles); // max pile = max possible speed

        // Binary search for the minimum feasible speed
        while (low <= high) {
            int mid = low + (high - low) / 2;
            long totalH = calculateTotalHours(piles, mid);

            if (totalH <= h) {
                // mid speed works, try slower
                high = mid - 1;
            } else {
                // mid speed too slow, need faster
                low = mid + 1;
            }
        }
        return low; // smallest valid speed
    }

    // Helper to find max pile
    public static int findMax(int[] v) {
        int maxi = Integer.MIN_VALUE;
        for (int j : v) {
            maxi = Math.max(maxi, j);
        }
        return maxi;
    }

    // Helper to calculate hours for a given speed
    public static long calculateTotalHours(int[] v, int hourly) {
        long totalH = 0;
        for (int bananas : v) {
            // integer ceil division → (a + b - 1) / b
            totalH += (bananas + hourly - 1) / hourly;
        }
        return totalH;
    }

    // 🚀 Main method to test multiple cases
    public static void main(String[] args) {
        // Testcase 1: big piles, big hours
        int[] piles1 = {805306368, 805306368, 805306368};
        int h1 = 1000000000;
        System.out.println("Test 1 → Expected: 3 | Got: " + minEatingSpeed(piles1, h1));

        // Testcase 2: classic small piles
        int[] piles2 = {3, 6, 7, 11};
        int h2 = 8;
        System.out.println("Test 2 → Expected: 4 | Got: " + minEatingSpeed(piles2, h2));

        // Testcase 3: generous hours
        int[] piles3 = {30, 11, 23, 4, 20};
        int h3 = 10;
        System.out.println("Test 3 → Expected: 15 | Got: " + minEatingSpeed(piles3, h3));
    }
}
