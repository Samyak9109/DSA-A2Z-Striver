public class LeaderInAnArray {

    /**
     * Brute Force Approach:
     * For each element, check all elements to its right.
     * If no element on the right is greater, it's a leader.
     *
     * Time Complexity: O(n^2) due to nested loops
     * Space Complexity: O(n) for storing leaders
     */
    static int[] leaderBrute(int[] arr) {
        int n = arr.length;
        int[] tempLeaders = new int[n];
        int leaderCount = 0;

        for (int i = 0; i < n; i++) {
            boolean isLeader = true;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    isLeader = false;
                    break;
                }
            }
            if (isLeader) {
                tempLeaders[leaderCount++] = arr[i];
            }
        }

        // Copy to array of correct size
        int[] leaders = new int[leaderCount];
        for (int i = 0; i < leaderCount; i++) {
            leaders[i] = tempLeaders[i];
        }

        return leaders;
    }

    /**
     * Optimal Approach:
     * Traverse from right to left, keep track of max seen so far.
     * If current element >= maxFromRight, it's a leader.
     * Time Complexity: O(n)
     * Space Complexity: O(n) for storing leaders
     */
    static int[] leaderOptimal(int[] arr) {
        int n = arr.length;
        int[] tempLeaders = new int[n];
        int leaderCount = 0;

        int maxFromRight = arr[n - 1];
        tempLeaders[leaderCount++] = maxFromRight;

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= maxFromRight) {
                maxFromRight = arr[i];
                tempLeaders[leaderCount++] = maxFromRight;
            }
        }

        // Reverse to maintain original order
        int[] leaders = new int[leaderCount];
        for (int i = 0; i < leaderCount; i++) {
            leaders[i] = tempLeaders[leaderCount - 1 - i];
        }

        return leaders;
    }

    public static void main(String[] args) {
        int[] arr = {16, 17, 4, 3, 5, 2};

        System.out.print("Array: ");
        for (int val : arr) System.out.print(val + " ");
        System.out.println();

        int[] bruteLeaders = leaderBrute(arr);
        System.out.print("Leaders (Brute Force): ");
        for (int leader : bruteLeaders) System.out.print(leader + " ");
        System.out.println();

        int[] optimalLeaders = leaderOptimal(arr);
        System.out.print("Leaders (Optimal): ");
        for (int leader : optimalLeaders) System.out.print(leader + " ");
        System.out.println();
    }
}
