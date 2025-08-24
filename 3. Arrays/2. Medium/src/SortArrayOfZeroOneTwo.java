import java.util.*;

public class SortArrayOfZeroOneTwo {

    // ---------------------------------------------
    // Brute Force Method:
    // Time Complexity: O(n) -> Two passes: one to count, one to overwrite.
    // Space Complexity: O(1) -> Only counters used.
    // Logic: Count how many 0s, 1s, and 2s, then overwrite original list.
    // ---------------------------------------------
    public static void sortBrute(ArrayList<Integer> arr) {
        int count0 = 0, count1 = 0, count2 = 0;

        // Count occurrences of 0, 1, and 2
        for (int num : arr) {
            if (num == 0) count0++;
            else if (num == 1) count1++;
            else count2++;
        }

        // Overwrite array with counted values
        int index = 0;
        for (int i = 0; i < count0; i++) arr.set(index++, 0);
        for (int i = 0; i < count1; i++) arr.set(index++, 1);
        for (int i = 0; i < count2; i++) arr.set(index++, 2);
    }

    // ---------------------------------------------
    // Better Method:
    // Time Complexity: O(n) -> Three passes: one each for 0s, 1s, and 2s.
    // Space Complexity: O(n) -> Extra ArrayList to temporarily hold sorted elements.
    // Logic: Collect 0s, then 1s, then 2s into a temp list, then copy back.
    // ---------------------------------------------
    public static void sortBetter(ArrayList<Integer> arr) {
        ArrayList<Integer> temp = new ArrayList<>();

        // Add all 0s
        for (int num : arr) if (num == 0) temp.add(0);
        // Add all 1s
        for (int num : arr) if (num == 1) temp.add(1);
        // Add all 2s
        for (int num : arr) if (num == 2) temp.add(2);

        // Copy back to original list
        for (int i = 0; i < arr.size(); i++) {
            arr.set(i, temp.get(i));
        }
    }

    // ---------------------------------------------
    // Optimal Method (Dutch National Flag Algorithm):
    // Time Complexity: O(n) -> Single pass with three pointers.
    // Space Complexity: O(1) -> In-place sorting using swaps.
    // Logic: Use three pointers low, mid, high:
    // - 0s go to front (low)
    // - 2s go to end (high)
    // - 1s stay in the middle
    // ---------------------------------------------
    public static void sortOptimal(ArrayList<Integer> arr) {
        int low = 0, mid = 0, high = arr.size() - 1;

        while (mid <= high) {
            if (arr.get(mid) == 0) {
                // Swap arr[low] and arr[mid], then move low and mid forward
                int temp = arr.get(low);
                arr.set(low, arr.get(mid));
                arr.set(mid, temp);
                low++;
                mid++;
            } else if (arr.get(mid) == 1) {
                // Move mid forward
                mid++;
            } else {
                // Swap arr[mid] and arr[high], move high backward
                int temp = arr.get(mid);
                arr.set(mid, arr.get(high));
                arr.set(high, temp);
                high--;
            }
        }
    }

    // ---------------------------------------------
    // Main method to test all three approaches
    // ---------------------------------------------
    public static void main(String[] args) {
        ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(0, 2, 1, 2, 0, 1));
        ArrayList<Integer> arr2 = new ArrayList<>(arr1); // copy for better method
        ArrayList<Integer> arr3 = new ArrayList<>(arr1); // copy for optimal method

        System.out.println("Original array: " + arr1);

        // Apply Brute Force
        sortBrute(arr1);
        System.out.println("Sorted using Brute Force: " + arr1);

        // Apply Better Method
        sortBetter(arr2);
        System.out.println("Sorted using Better Method: " + arr2);

        // Apply Optimal Method
        sortOptimal(arr3);
        System.out.println("Sorted using Optimal Method: " + arr3);
    }
}
