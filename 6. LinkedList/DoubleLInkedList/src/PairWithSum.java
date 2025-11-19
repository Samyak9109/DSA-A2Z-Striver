import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class PairWithSum {

    /**
     * **Approach 1: Brute Force (Nested Loops)**
     * * Time Complexity: O(N^2)
     * - The nested 'while' loops check every single possible pair, resulting in N * (N-1)/2 checks. Not great for scaling!
     * * Space Complexity: O(1) + O(P)
     * - O(1) for pointers. O(P) for storing the 'P' resulting pairs (which can be up to O(N^2) in dense cases, but usually closer to O(N)).
     */
    static List<List<Integer>> pairWithSumBrute(Node head, int sum) {
        List<List<Integer>> allPairs = new ArrayList<>();
        // Detailed Comment: The outer pointer, temp1, sets the first element of the pair.
        Node temp1 = head;

        while (temp1 != null) {
            // Detailed Comment: The inner pointer, temp2, starts RIGHT AFTER temp1.
            // This is crucial! It prevents checking a pair twice (A, B and then B, A)
            // and avoids comparing a node with itself. This is our non-repetitive pipeline logic.
            Node temp2 = temp1.next;

            while (temp2 != null) {
                int currentSum = temp1.data + temp2.data;

                // Detailed Comment: If the sum matches the target, we've found a pair!
                if (currentSum == sum) {
                    List<Integer> pair = new ArrayList<>();
                    // We store them in order (temp1, temp2) for consistency.
                    pair.add(temp1.data);
                    pair.add(temp2.data);
                    allPairs.add(pair);
                }

                // Move the inner pointer forward.
                temp2 = temp2.next;
            }

            // Move the outer pointer forward to start the next cycle.
            temp1 = temp1.next;
        }

        return allPairs;
    }

    /**
     * **Approach 2: Optimal Hash Map (Single Pass)**
     * * Time Complexity: O(N)
     * - We traverse the list once. Look-ups and insertions in the HashMap are O(1) on average. This is how we optimize the pipeline!
     * * Space Complexity: O(N) + O(P)
     * - O(N) to store up to all N elements in the HashMap. O(P) to store the resulting pairs.
     */
    static List<List<Integer>> pairWithSumOptimal(Node head, int sum) {
        List<List<Integer>> allPairs = new ArrayList<>();
        // Detailed Comment: The HashMap acts as a historical database of all node values seen so far.
        Map<Integer, Boolean> seenData = new HashMap<>();
        Node temp = head;

        // Detailed Comment: Single pass through the list. This is the forward-thinking solution!
        while (temp != null) {
            int currentData = temp.data;
            int complement = sum - currentData;

            // Step 1: Check if the required complement is already in our history (HashMap).
            if (seenData.containsKey(complement)) {

                // Step 2: Found a pair! Record it.
                List<Integer> pair = new ArrayList<>();
                // Store the pair, typically (smaller, larger) or (complement, currentData)
                pair.add(complement);
                pair.add(currentData);
                allPairs.add(pair);
            }

            // Step 3: Add the current element to the map. It is now available as a complement
            // for any future nodes.
            seenData.put(currentData, true);

            // Step 4: Move to the next node.
            temp = temp.next;
        }

        return allPairs;
    }

    /**
     * **Approach 3: Two-Pointer Converging (Applicable ONLY for Sorted DLL)**
     * * Time Complexity: O(N)
     * - We traverse the list exactly once (the pointers cross, checking each element at most once).
     * * Space Complexity: O(1) + O(P)
     * - O(1) for the two pointer variables (left and right). O(P) for storing the 'P' resulting pairs.
     *
     * @param head The head of the SORTED doubly linked list.
     * @param sum The target sum.
     * @return A list of lists containing all pairs [left_data, right_data].
     */
    static List<List<Integer>> pairWithSumTwoPointers(Node head, int sum) {
        // Edge case: empty or single-node list can't form a pair.
        if (head == null || head.next == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> allPairs = new ArrayList<>();

        // Step 1: Find the 'tail' of the doubly linked list.
        Node right = head;
        // Detailed Comment: We traverse to the end to set up the 'right' pointer.
        while (right.next != null) {
            right = right.next;
        }

        Node left = head; // 'left' starts at the head.

        // Step 2: Converge the pointers. The process stops when they meet or cross.
        // Detailed Comment: Since we need to check distinct pairs, left should never cross or equal right.
        while (left != right && left.back != right) {
            int currentSum = left.data + right.data;

            if (currentSum == sum) {
                // Success! Found a pair. Record it and move both pointers inward.
                List<Integer> pair = new ArrayList<>();
                pair.add(left.data);
                pair.add(right.data);
                allPairs.add(pair);

                // Detailed Comment: Since we found the target, we move BOTH pointers inward
                // to look for the next potential pair. This is the core movement synergy.
                left = left.next;
                right = right.back;
            } else if (currentSum < sum) {
                // Detailed Comment: Sum is too small. Since the list is sorted, we need a larger number.
                // We move the 'left' pointer forward to increase the sum.
                left = left.next;
            } else { // currentSum > sum
                // Detailed Comment: Sum is too large. We need a smaller number.
                // We move the 'right' pointer backward to decrease the sum.
                right = right.back;
            }
        }

        return allPairs;
    }


    // Helper method to create and print the list
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " <-> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    /**
     * Main method for execution and demonstration. Let's see the side-by-side performance!
     */
    public static void main(String[] args) {
        // --- Setup: Building a sample doubly linked list ---
        // List: 1 <-> 4 <-> 6 <-> 8 <-> 10 <-> 13. NOTE: This setup is sorted, which is required for the Two-Pointer method.
        Node head = new Node(1);
        Node n2 = new Node(4);
        Node n3 = new Node(6);
        Node n4 = new Node(8);
        Node n5 = new Node(10);
        Node n6 = new Node(13);

        // Link them up (forward and backward pointers)
        head.next = n2;    n2.back = head;
        n2.next = n3;      n3.back = n2;
        n3.next = n4;      n4.back = n3;
        n4.next = n5;      n5.back = n4;
        n5.next = n6;      n6.back = n5;

        int targetSum = 14;
        System.out.println("--- 📊 Initial List State ---");
        System.out.print("List: ");
        // Assuming printList is defined elsewhere or in the class
        printList(head);
        System.out.println("Target Sum: " + targetSum);
        System.out.println("-----------------------------");

        // --- Demo 1: Brute Force Execution (O(N^2)) ---
        System.out.println("\n🚀 Brute Force: Testing all combinations...");
        // Assuming pairWithSumBrute is defined elsewhere or in the class
        List<List<Integer>> brutePairs = pairWithSumBrute(head, targetSum);
        System.out.println("✅ Brute Force Found Pairs (O(N^2)):");
        if (brutePairs.isEmpty()) {
            System.out.println("   No synergy achieved.");
        } else {
            // Expected Pairs for Sum 14: (1, 13), (4, 10), (6, 8)
            brutePairs.forEach(pair -> System.out.println("   " + pair));
        }

        System.out.println("\n-----------------------------");

        // --- Demo 2: Optimal Hash Map Execution (O(N) Time, O(N) Space) ---
        System.out.println("\n🚀 Optimal Hash Map: Optimizing the pipeline (O(N) Time, O(N) Space)...");
        // Assuming pairWithSumOptimal is defined elsewhere or in the class
        List<List<Integer>> optimalPairs = pairWithSumOptimal(head, targetSum);
        System.out.println("✅ Optimal Hash Map Found Pairs (O(N)):");
        if (optimalPairs.isEmpty()) {
            System.out.println("   No synergy achieved.");
        } else {
            // Expected Pairs for Sum 14: (13, 1), (10, 4), (8, 6) -- (Order depends on traversal, but values are the same)
            optimalPairs.forEach(pair -> System.out.println("   " + pair));
        }

        System.out.println("\n-----------------------------");

        // --- Demo 3: TRUE Optimal Two-Pointer Execution (O(N) Time, O(1) Space) ---
        System.out.println("\n⭐ TRUE Optimal Two-Pointer: Converging for O(1) space...");
        // Assuming pairWithSumTwoPointers is defined elsewhere or in the class
        List<List<Integer>> twoPointerPairs = pairWithSumTwoPointers(head, targetSum);
        System.out.println("✅ Two-Pointer Found Pairs (O(1) Space):");
        if (twoPointerPairs.isEmpty()) {
            System.out.println("   Ultimate synergy not achieved.");
        } else {
            // Expected Pairs for Sum 14: (1, 13), (4, 10), (6, 8)
            twoPointerPairs.forEach(pair -> System.out.println("   " + pair));
        }
    }
}