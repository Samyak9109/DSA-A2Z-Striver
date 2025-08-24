public class MaxProfit {

    /**
     * Brute Force approach to find the maximum profit from stock prices.
     * Time Complexity: O(n^2) - Nested loops over the array.
     * Space Complexity: O(1) - No extra space used.
     */
    static int maxProfitBrute(int[] arr) {
        int n = arr.length;
        int maxPro = 0;

        // Iterate through each price and compare with each future price to find max profit
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    maxPro = Math.max(arr[j] - arr[i], maxPro);
                }
            }
        }
        return maxPro;
    }

    /**
     * Dynamic Programming (Optimal) approach to find max profit in a single pass.
     * Time Complexity: O(n) - Single traversal of the array.
     * Space Complexity: O(1) - Only variables minPrice and maxPro used.
     *
     * Keeps track of minimum price so far and calculates profit if sold today.
     */
    static int maxProfitOptimal(int[] arr) {
        int n = arr.length;
        int maxPro = 0;
        int minPrice = Integer.MAX_VALUE;

        // Traverse prices while updating minPrice and maxPro
        for (int i = 0; i < n; i++) {
            minPrice = Math.min(minPrice, arr[i]);
            maxPro = Math.max(maxPro, arr[i] - minPrice);
        }
        return maxPro;
    }

    public static void main(String[] args) {
        int[] arr = {7, 1, 5, 3, 6, 4};
        int maxPro = maxProfitBrute(arr);
        System.out.println("Max profit is: " + maxPro);
    }
}