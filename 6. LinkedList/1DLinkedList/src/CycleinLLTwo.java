import java.util.HashMap;

// Assuming Node class is available from a separate file:
// class Node {
//     int data;
//     Node next;
//     Node(int data) { this.data = data; this.next = null; }
// }

public class CycleinLLTwo {

    /**
     * 🔥 Brute Force Approach: Using a HashMap (or HashSet) to Find the Cycle Start
     * * Time Complexity: O(N) 🕰️
     * - We traverse the list once, visiting N nodes in the worst case.
     * - HashMap operations (containsKey and put) take O(1) time on average.
     * - Reasoning: The traversal is linear, and the check/storage at each node is constant time.
     * Space Complexity: O(N) 💾
     * - We store a reference to every node in the HashMap/HashSet in the worst case (no cycle).
     * - Reasoning: The space used by the HashMap is directly proportional to the number of nodes (N).
     * * @param head The head of the linked list.
     * @return The starting node of the loop, or null if no loop exists.
     */
    static Node cycleLLTwoBrute(Node head) {
        // Step 1: Initialize the HashMap. Key is the Node reference, Value is arbitrary (we just need the presence of the key).
        HashMap<Node, Integer> nodeMap = new HashMap<>();
        Node temp = head;

        // Step 2: Traverse the linked list until we reach the end (null).
        while (temp != null) {

            // Check: Have we seen this specific Node object before?
            // This is the moment of truth! If it's in the map, it means 'temp' is the node
            // where the cycle begins for the *second time* we've encountered it,
            // making it the actual start of the loop.
            if (nodeMap.containsKey(temp)) {
                // We've found the cycle's starting point! Return it immediately.
                return temp;
            }

            // Log it: If it's a new node, store its reference in the map.
            // This marks it as visited.
            nodeMap.put(temp, 1);

            // Move to the next node.
            temp = temp.next;
        }

        // Step 3: If the entire list is traversed and we hit null, there's no loop.
        return null;
    }

    // ---

    /**
     * ⚡ Optimal Approach: Floyd's Algorithm (Tortoise and Hare) Extension
     * * Time Complexity: O(N) 🕰️
     * - Phase 1 (Detection): Takes O(N) time. The pointers cover distance proportional to N.
     * - Phase 2 (Finding Start): Takes O(N) time. The remaining distance for the two pointers to meet is at most the length of the list.
     * - Reasoning: Total time complexity remains linear, O(N) + O(N) = O(N).
     * Space Complexity: O(1) 💾
     * - We only use two extra pointers (slow and fast), regardless of the list size N.
     * - Reasoning: This is the optimal space solution, avoiding the O(N) space cost of the HashMap.
     * * @param head The head of the linked list.
     * @return The starting node of the loop, or null if no loop exists.
     */
    static Node cycleLLTwoOptimal(Node head) {
        Node slow = head;
        Node fast = head;

        // Walkthrough Analogy:
        // Let D be the distance from Head to Cycle Start.
        // Let K be the distance from Cycle Start to Meeting Point.
        // Let C be the length of the cycle.
        // It can be mathematically proven that: D = (C - K).
        // If we move a pointer from Head (D distance) and the 'fast' pointer (which is K distance from the start)
        // simultaneously at 1 step/time, they will meet exactly at the Cycle Start.


        // Phase 1: Detect the loop (same as Linked List Cycle I).
        while (fast != null && fast.next != null) {
            slow = slow.next; // Tortoise moves 1 step
            fast = fast.next.next; // Hare moves 2 steps

            // Check for meeting point:
            if (slow == fast) {
                // Loop detected! Time to optimize the pipeline to find the start.

                // Phase 2: Find the first node of the loop.
                // Reset one pointer (slow) back to the head.
                slow = head;

                // Now, move both 'slow' (from head) and 'fast' (from meeting point)
                // one step at a time until they meet again.
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                    // They *must* meet at the start of the cycle due to the mathematical relationship (D = C - K).
                }

                // When they meet, 'slow' (or 'fast') is the starting node of the loop!
                return slow;
            }
        }

        // If the 'fast' pointer hits null, it means there is no cycle,
        // and we successfully traversed the entire list.
        return null;
    }

    /**
     * Main method for execution and demonstration.
     */
    public static void main(String[] args) {
        // --- Test Case 1: List with a Cycle starting at Node 3 ---
        // 1 -> 2 -> 3(START) -> 4 -> 5 -> (points back to 3)
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3); // <-- Expected Start Node
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n3; // CREATE THE CYCLE

        System.out.println("--- Test Case: Cyclic List (Start at 3) ---");
        System.out.println("Brute Force (HashMap) Start Node data: " + (cycleLLTwoBrute(n1) != null ? cycleLLTwoBrute(n1).data : "null")); // Expected: 3
        System.out.println("Optimal (Fast/Slow Pointer) Start Node data: " + (cycleLLTwoOptimal(n1) != null ? cycleLLTwoOptimal(n1).data : "null")); // Expected: 3

        System.out.println("\n-------------------------------------------------");

        // --- Test Case 2: List without a Cycle ---
        // 10 -> 20 -> 30 -> 40 -> null
        Node a1 = new Node(10);
        Node a2 = new Node(20);
        Node a3 = new Node(30);
        Node a4 = new Node(40);

        a1.next = a2;
        a2.next = a3;
        a3.next = a4;

        System.out.println("--- Test Case: Acyclic List ---");
        System.out.println("Brute Force (HashMap) Start Node: " + cycleLLTwoBrute(a1)); // Expected: null
        System.out.println("Optimal (Fast/Slow Pointer) Start Node: " + cycleLLTwoOptimal(a1)); // Expected: null
    }
}
