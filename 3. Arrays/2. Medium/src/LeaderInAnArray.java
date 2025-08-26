public class LeaderInAnArray {

    /**
     * Brute force approach:
     * For each element, check all elements to its right to see if any is greater.
     * If none are greater, the element is a leader.
     * Time Complexity: O(n^2) because of nested loops.
     * Space Complexity: O(n) for the temporary leaders array and final output array.
     */
    static int[] leaderBrute(int[] arr) {
        int n = arr.length;
        int[] tempLeaders = new int[n];
        int leaderCount = 0;

        for (int i = 0; i < n; i++) {
            boolean leader = true;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    leader = false;
                    break;
                }
            }
            if (leader) {
                tempLeaders[leaderCount] = arr[i];
                leaderCount++;
            }
        }

        int[] leaders = new int[leaderCount];
        for (int i = 0; i < leaderCount; i++) {
            leaders[i] = tempLeaders[i];
        }
        return leaders;
    }

    /**
     * Optimal approach:
     * Traverse the array from right to left.
     * Keep track of the maximum element seen so far on the right.
     * If the current element is greater than or equal to that maximum, it is a leader.
     * This method collects leaders in reverse order, so they are reversed before returning.
     * Time Complexity: O(n) because it traverses the array once.
     * Space Complexity: O(n) for the temporary leaders array and final output array.
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

        int[] leaders = new int[leaderCount];
        for (int i = 0; i < leaderCount; i++) {
            leaders[i] = tempLeaders[leaderCount - 1 - i];
        }

        return leaders;
    }

    public static void main(String[] args) {
        int[] arr = {16, 17, 4, 3, 5, 2};

        System.out.print("Array: ");
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();

        int[] bruteLeaders = leaderBrute(arr);
        System.out.print("Leaders by Brute Force: ");
        for (int leader : bruteLeaders) {
            System.out.print(leader + " ");
        }
        System.out.println();

        int[] optimalLeaders = leaderOptimal(arr);
        System.out.print("Leaders by Optimal Method: ");
        for (int leader : optimalLeaders) {
            System.out.print(leader + " ");
        }
        System.out.println();
    }
}
