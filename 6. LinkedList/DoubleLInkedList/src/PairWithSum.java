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
        // List: 1 <-> 4 <-> 6 <-> 8 <-> 10 <-> 13. Note: The list is NOT required to be sorted for these methods.
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
        printList(head);
        System.out.println("Target Sum: " + targetSum);
        System.out.println("-----------------------------");

        // --- Demo 1: Brute Force Execution (O(N^2)) ---
        System.out.println("\n🚀 Brute Force: Testing all combinations...");
        List<List<Integer>> brutePairs = pairWithSumBrute(head, targetSum);
        System.out.println("✅ Brute Force Found Pairs (O(N^2)):");
        if (brutePairs.isEmpty()) {
            System.out.println("   No synergy achieved.");
        } else {
            // Expected Pairs for Sum 14: (1, 13), (4, 10), (6, 8)
            brutePairs.forEach(pair -> System.out.println("   " + pair));
        }

        System.out.println("\n-----------------------------");

        // --- Demo 2: Optimal Hash Map Execution (O(N)) ---
        System.out.println("\n🚀 Optimal Hash Map: Optimizing the pipeline (O(N))...");
        List<List<Integer>> optimalPairs = pairWithSumOptimal(head, targetSum);
        System.out.println("✅ Optimal Found Pairs (O(N)):");
        if (optimalPairs.isEmpty()) {
            System.out.println("   No synergy achieved.");
        } else {
            // Expected Pairs for Sum 14: (13, 1), (10, 4), (8, 6) -- (Order depends on traversal, but values are the same)
            optimalPairs.forEach(pair -> System.out.println("   " + pair));
        }
    }
}