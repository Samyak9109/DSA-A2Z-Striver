import java.util.HashMap;

public class CycleInLL {

    /**
     * 🔥 Brute Force Approach: Using a HashMap (or HashSet) to Detect a Cycle
     * * Time Complexity: O(N) 🕰️
     * - We traverse the list once. In the worst case (no cycle), we visit N nodes.
     * - HashMap operations (containsKey and put) take O(1) time on average.
     * - Reasoning: We do a constant number of O(1) operations for each of the N nodes.
     * Space Complexity: O(N) 💾
     * - We store a reference to every single node in the HashMap/HashSet in the worst case (no cycle).
     * - Reasoning: The space used by the HashMap is directly proportional to the number of nodes (N).
     * * @param head The head of the linked list.
     * @return true if a cycle exists, false otherwise.
     */
    static boolean cycleLLBrute(Node head) {
        // Step 1: Initialize a HashMap to store encountered nodes.
        // We only care about the node reference, so a HashSet would be a cleaner alternative,
        // but a HashMap works too (using '1' as a placeholder value).
        HashMap<Node, Integer> nodeMap = new HashMap<>();
        Node temp = head;

        // Step 2: Traverse the linked list until we hit null (end of a non-cyclic list)
        while (temp != null) {
            // Check: Is this node already in our map?
            // If we've seen this specific Node object before, it means we've completed a cycle!
            // This is the core logic for detecting the loop.
            if (nodeMap.containsKey(temp)) {
                return true;
            }

            // Log it: If it's a new node, store its reference in the map.
            // This marks it as "visited" for future checks.
            nodeMap.put(temp, 1);

            // Move to the next node to continue the traversal.
            temp = temp.next;
        }

        // Step 3: If the loop finishes without returning true, we successfully hit 'null',
        // which confirms the list is linear and cycle-free.
        return false;
    }

    // ---

    /**
     * ⚡ Optimal Approach: Floyd's Tortoise and Hare Algorithm (Fast & Slow Pointers)
     * * Time Complexity: O(N) 🕰️
     * - In the worst case, the slow pointer travels at most N steps, and the fast pointer travels at most 2N steps.
     * - The total number of operations is proportional to N.
     * - Reasoning: The fast pointer (2x speed) will eventually catch the slow pointer, and the distance covered is linear with the list size.
     * Space Complexity: O(1) 💾
     * - We only use two extra pointers (slow and fast), regardless of the list size N.
     * - Reasoning: This is the forward-thinking solution because it avoids the space cost of the HashMap!
     * * @param head The head of the linked list.
     * @return true if a cycle exists, false otherwise.
     */
    static boolean cycleLLOptimal(Node head) {
        // Step 1: Initialize the 'slow' and 'fast' pointers.
        // Think of 'slow' as the Tortoise 🐢 and 'fast' as the Hare 🐇.
        Node slow = head;
        Node fast = head;

        // Walkthrough: Why does this work?
        // Imagine two runners on a circular track. If one is exactly twice as fast, they MUST meet eventually.
        // When they meet, it proves the track is a circle (a cycle exists).


        // Step 2: Traverse the list. The loop continues as long as 'fast' and 'fast.next' are valid (i.e., we haven't hit the end of a non-cyclic list).
        while (fast != null && fast.next != null) {

            // Move the Tortoise (slow) one step.
            slow = slow.next;

            // Move the Hare (fast) two steps.
            // This differential speed is the secret sauce for meeting up in a cycle!
            fast = fast.next.next;

            // Check: Did the pointers meet?
            // This is the convergence point. If slow and fast reference the exact same node, we've confirmed a loop!
            if (slow == fast) {
                // We've got a cycle! Mission accomplished.
                return true;
            }
        }

        // Step 3: If the loop finishes, it means the 'fast' pointer hit 'null',
        // indicating the end of the list was reached, and thus, no cycle exists.
        return false;
    }

    /**
     * Main method to test and demonstrate both approaches.
     */
    public static void main(String[] args) {
        // --- Test Case 1: List with a Cycle ---
        // 1 -> 2 -> 3 -> 4 -> 5 -> (points back to 3)
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        // CREATE THE CYCLE
        n5.next = n3;

        System.out.println("--- Test Case: Cyclic List (1->2->3->4->5->3) ---");
        System.out.println("Brute Force (HashMap) result: " + cycleLLBrute(n1)); // Expected: true
        System.out.println("Optimal (Fast/Slow Pointer) result: " + cycleLLOptimal(n1)); // Expected: true

        System.out.println("\n-------------------------------------------------");

        // --- Test Case 2: List without a Cycle ---
        // 10 -> 20 -> 30 -> 40 -> null
        Node a1 = new Node(10);
        Node a2 = new Node(20);
        Node a3 = new Node(30);
        Node a4 = new Node(40);

        a1.next = a2;
        a2.next = a3;
        a3.next = a4; // a4.next remains null

        System.out.println("--- Test Case: Acyclic List (10->20->30->40->null) ---");
        System.out.println("Brute Force (HashMap) result: " + cycleLLBrute(a1)); // Expected: false
        System.out.println("Optimal (Fast/Slow Pointer) result: " + cycleLLOptimal(a1)); // Expected: false
    }
}