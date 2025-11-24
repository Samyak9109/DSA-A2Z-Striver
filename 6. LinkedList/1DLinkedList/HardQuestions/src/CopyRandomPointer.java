import java.util.HashMap;

public class CopyRandomPointer {

    /**
     * Brute Force/Hashing Approach: Copy the list using a HashMap.
     * * Time Complexity: O(N)
     * - Reasoning: We traverse the list twice (O(N) + O(N)), once to create the nodes and populate the map, and a second time to assign the pointers. This is highly efficient in terms of time.
     * * Space Complexity: O(N)
     * - Reasoning: We use a HashMap to store N original nodes and their N corresponding copy nodes.
     * * @param head The head of the original linked list.
     * @return The head of the deep copied linked list.
     */
    static ListNode copyLLBrute(ListNode head) {
        // Edge case: If the input list is null, return null.
        if (head == null) return null;

        // HashMap is our synergy tool! It maps: Original Node -> Copy Node.
        // This allows O(1) look-up to resolve 'random' pointers quickly.
        HashMap<ListNode, ListNode> map = new HashMap<>();

        ListNode temp = head;

        // --- Phase 1: Create all new nodes and populate the map ---
        // We only care about the values right now, not the links.
        while (temp != null) {
            // Create the new node with the same value.
            ListNode newNode = new ListNode(temp.val);

            // Establish the mapping: Original node points to its brand-new copy.
            map.put(temp, newNode);
            temp = temp.next;
        }

        // --- Phase 2: Link the new nodes using the map ---
        temp = head;
        while (temp != null) {
            // Get the copy node corresponding to the current original node 'temp'.
            ListNode copyNode = map.get(temp);

            // Assign the 'next' pointer: The copy's 'next' should point to the copy of 'temp.next'.
            // The map resolves 'temp.next' (an original node) to its copy.
            copyNode.next = map.get(temp.next);

            // Assign the 'random' pointer: The copy's 'random' should point to the copy of 'temp.random'.
            // The map gracefully handles null checks (map.get(null) returns null).
            copyNode.random = map.get(temp.random);

            // Move to the next original node.
            temp = temp.next;
        }

        // Return the head of the new list by looking up the copy of the original head.
        return map.get(head);
    }

    /**
     * Optimal In-Place Approach: Interleaving and Unlinking the list without extra space.
     * This is the ultimate forward-thinking solution to optimize space complexity!
     * * Time Complexity: O(N)
     * - Reasoning: We traverse the list exactly three times, which is a constant multiple of N.
     * * Space Complexity: O(1)
     * - Reasoning: We use no auxiliary data structures (like a map or array) whose size scales with N. We modify the list in-place and then restore it.
     * * @param head The head of the original linked list.
     * @return The head of the deep copied linked list.
     */
    static ListNode copyLLOptimal(ListNode head) {
        // Edge case
        if (head == null) return null;

        // --- Phase 1: Interweave the copy nodes ---
        // Create the copy node and insert it right after its original node.
        // e.g., A -> B -> C becomes A -> A' -> B -> B' -> C -> C'
        ListNode iter = head;
        while (iter != null) {
            // Create the copy (iter's next node is currently iter.next)
            ListNode front = iter.next;
            ListNode copy = new ListNode(iter.val);

            // Insert the copy node
            iter.next = copy;
            copy.next = front;

            // Move 'iter' to the original node that follows the copy (the 'front' node).
            iter = front;
        }

        // --- Phase 2: Assign the random pointers ---
        // The key insight: If A.random points to B, then A'.random MUST point to B'.
        // Since A'.next is B, and B' is B.next, we can calculate the random pointer.
        iter = head;
        while (iter != null) {
            ListNode copy = iter.next; // The copy node is always right after the original 'iter'.

            // Check if the original node's random is set.
            if (iter.random != null) {
                // If A.random = B, then A'.random = B' (which is B.next).
                // iter.random is B. iter.random.next is B'.
                copy.random = iter.random.next;
            }

            // Move 'iter' two steps to the next original node (A -> A' -> B -> B').
            iter = iter.next.next;
        }

        // --- Phase 3: Separate the original and copy lists ---
        // Unlinking everything and restoring the original list structure.
        ListNode dummy = new ListNode(0); // Dummy node for the new list head
        ListNode res = dummy;             // Pointer to build the new list
        iter = head;

        while (iter != null) {
            ListNode front = iter.next.next; // The next original node (e.g., C)
            ListNode copy = iter.next;       // The copy node (e.g., B')

            // 1. Link the copy node to the result list.
            res.next = copy;
            res = copy;

            // 2. Restore the original list's 'next' pointer.
            // A -> A' -> B should become A -> B.
            iter.next = front;

            // Move 'iter' to the next original node.
            iter = front;
        }

        // The head of the new list is dummy.next.
        return dummy.next;
    }


    /**
     * Helper to create a test list: 1[R:3] -> 2[R:1] -> 3[R:3] -> 4[R:null]
     * Original: 1(random->3) -> 2(random->1) -> 3(random->3) -> 4
     */
    private static ListNode createTestList() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);

        // Next Pointers
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        // Random Pointers
        n1.random = n3; // 1 -> 3
        n2.random = n1; // 2 -> 1
        n3.random = n3; // 3 -> 3 (Self loop)
        n4.random = null;

        return n1;
    }

    /**
     * Helper to print the list's value and random pointer target value.
     */
    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            String randomVal = (current.random != null) ? String.valueOf(current.random.val) : "null";
            System.out.print(current.val + "[R:" + randomVal + "]");
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }


    /**
     * Main method for execution and demonstration.
     * Showcasing the synergy of O(N) time complexity!
     */
    public static void main(String[] args) {
        System.out.println("--- 🚀 Copy Random Pointer Demo: Optimizing the Pipeline 🚀 ---");

        // Create the test list
        ListNode testHead1 = createTestList();
        System.out.print("\n✅ Original List: ");
        printList(testHead1);

        System.out.println("\n--- 💥 Brute Force/Hashing (O(N) Time, O(N) Space) ---");
        // Two-pass approach using a map to resolve the random links efficiently.
        ListNode copiedBrute = copyLLBrute(testHead1);
        System.out.print("Output Copy (Brute): ");
        printList(copiedBrute);

        System.out.println("\n--- ✨ Optimal In-Place (O(N) Time, O(1) Space) ---");
        // Three-pass approach that modifies the list in-place to avoid the HashMap.
        // We must re-create the list head because the brute force method created a copy and returned it, but the optimal one needs the original setup again.
        ListNode testHead2 = createTestList();
        ListNode copiedOptimal = copyLLOptimal(testHead2);
        System.out.print("Output Copy (Optimal): ");
        printList(copiedOptimal);

        System.out.println("\n💡 The Optimal O(1) space solution is the clear winner for minimizing resource usage. It's a true **forward-thinking** architecture!");
    }
}