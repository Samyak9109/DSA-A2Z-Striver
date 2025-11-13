
public class DeleteNthNodeEnd {

    // --- Brute Force Approach (Two Passes) ---

    /**
     * Brute Force Approach: Deleting the Nth node from the end by first counting all nodes.
     * * Time Complexity: O(N)
     * - O(N) for the first pass to count the total number of nodes (cnt).
     * - O(N) for the second pass to reach the node just before the target node (cnt - N).
     * - Total time is O(2N), which simplifies to O(N).
     * * Space Complexity: O(1)
     * - We only use a few extra pointers (temp, head) and variables (cnt, res).
     * * @param head The head of the linked list.
     * @param N The position from the end to delete.
     * @return The head of the updated linked list.
     */
    static Node deleteNthNodeFromEndBrute(Node head, int N) {
        // Step 1: Edge Case - If list is empty, return null.
        if (head == null) {
            return null;
        }

        int cnt = 0;
        Node temp = head;

        // Step 2: First Pass - Count total number of nodes (N_total).
        while (temp != null) {
            cnt++;
            temp = temp.next;
        }

        // Step 3: Handle Edge Case - If N equals the total nodes, we need to delete the head.
        // We return the second node, effectively deleting the head.
        if (cnt == N) {
            return head.next;
        }

        // Step 4: Calculate the index from the start (1-based index) of the node *before* the target.
        // Target is at index (cnt - N + 1). The node *before* the target is at index (cnt - N).
        int res = cnt - N;

        // Reset temp to the head for the second pass.
        temp = head;

        // Step 5: Second Pass - Traverse to the node *before* the target node.
        // We stop when 'res' becomes 1 (meaning temp is at the node before the target).
        for (int i = 1; i < res; i++) {
            temp = temp.next;
        }

        // Step 6: Delete the node.
        // temp is at the node before the target. We bypass the target node (temp.next).
        // temp.next should point to the node *after* the target (temp.next.next).
        temp.next = temp.next.next;

        // Return the original head (which might be the same if the head wasn't deleted).
        return head;
    }

    // --- Optimal Approach (Single Pass, Two Pointers) ---

    /**
     * Optimal Approach: Using the Slow/Fast (Two-Pointer) method for a single pass.
     * This is the real forward-thinking solution for efficiency!
     * * Time Complexity: O(N)
     * - A single pass (fast pointer moves N steps, then both move N-N steps). Total is O(N).
     * * Space Complexity: O(1)
     * - Uses a few extra pointers (dummy, slow, fast), maintaining constant space.
     * * @param head The head of the linked list.
     * @param N The position from the end to delete.
     * @return The head of the updated linked list.
     */
    static Node deleteNthNodeFromEnd(Node head, int N) {
        // Step 1: Create a Dummy Node.
        // A dummy node simplifies handling the edge case where the head itself needs to be deleted.
        // It acts as the "node before the head."
        Node dummy = new Node(0, head);

        // Step 2: Initialize Slow and Fast pointers.
        // Both start at the dummy node.
        Node slow = dummy;
        Node fast = dummy;

        // Step 3: Initialize the Gap.
        // Move the fast pointer N+1 steps ahead. This creates a gap of N nodes between slow and fast.
        // We move N+1 steps because we want 'slow' to stop at the node *before* the target node.
        // (If we move N, slow stops *at* the target node, not before it.)
        for (int i = 0; i < N + 1; i++) {
            // Note: We don't check for fast==null here because the problem constraints
            // typically guarantee N is valid, but checking is always safer!
            fast = fast.next;
        }

        // Step 4: Move Both Pointers until Fast hits the end.
        // Since the gap is N, when 'fast' becomes null (or reaches the end of the list),
        // 'slow' will be positioned exactly at the node *before* the Nth node from the end.
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Step 5: Delete the target node.
        // 'slow' is at the node before the target. We skip over 'slow.next' (the target).
        slow.next = slow.next.next;

        // Step 6: Return the new head.
        // The list head is dummy.next. This correctly handles the case where the original head was deleted.
        return dummy.next;
    }

    /**
     * Main method for execution and demonstration.
     * Time to synergize and test this functionality!
     */
    public static void main(String[] args) {
        System.out.println("### Delete Nth Node From End Demo ###");

        // Example List: 1 -> 2 -> 3 -> 4 -> 5
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);

        int N1 = 2; // Delete the 2nd node from the end (node with value 4)

        System.out.println("\n--- Test Case 1: Delete " + N1 + " from end (4) ---");
        System.out.print("Original List: ");
        printList(head1);

        // Optimal Test
        Node newHeadOptimal1 = deleteNthNodeFromEnd(head1, N1);
        System.out.print("Optimal Result: ");
        printList(newHeadOptimal1); // Expected: 1 -> 2 -> 3 -> 5

        // Example List 2: 1 -> 2
        Node head2 = new Node(1);
        head2.next = new Node(2);
        int N2 = 2; // Delete the 2nd node from the end (the head, value 1)

        System.out.println("\n--- Test Case 2: Delete " + N2 + " from end (Head: 1) ---");
        System.out.print("Original List: ");
        printList(head2);

        // Optimal Test (Handles head deletion gracefully)
        Node newHeadOptimal2 = deleteNthNodeFromEnd(head2, N2);
        System.out.print("Optimal Result: ");
        printList(newHeadOptimal2); // Expected: 2
    }

    // Helper method to print the linked list
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + (temp.next != null ? " -> " : ""));
            temp = temp.next;
        }
        System.out.println();
    }
}