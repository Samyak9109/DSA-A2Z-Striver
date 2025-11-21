public class ReverseNodesInKGroup {

    // Assuming the ListNode class is in a separate file as per your preference.
    // public static class ListNode {
    //     int val;
    //     ListNode next;
    //     ListNode(int val) { this.val = val; }
    //     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    // }


    /**
     * Time Complexity: O(N)
     * Reasoning: We traverse the entire linked list (N nodes) exactly once. The
     * 'getKthNode' takes O(k) for each group, and 'reverseLinkedList' also takes
     * O(k) for each group. Since there are N/k groups, the total time is
     * (N/k) * O(k) = O(N).
     * Space Complexity: O(1)
     * Reasoning: We only use a few extra pointers (temp, prevLast, kThNode, nextNode)
     * regardless of the input size, so the space used is constant.
     * Forward-Thinking Solution: Reverses the nodes of the list in groups of size k.
     * It leaves the last group untouched if it has less than k nodes.
     * @param head The head of the linked list.
     * @param k The size of the group to reverse.
     * @return The head of the modified linked list.
     */
    static ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head; // Start of the current group we are processing.
        ListNode prevLast = null; // Pointer to the LAST node of the PREVIOUS reversed group. This is key for linking!

        // Loop through the list, group by group.
        while (temp != null){
            // 1. Find the k-th node from 'temp' (the start of the current group).
            ListNode kThNode = getKthNode(temp, k);

            // 2. Check the edge case: Not enough nodes left for a full group.
            if (kThNode == null) {
                // If this is the case, we chill and don't reverse the remaining nodes.
                if (prevLast != null) {
                    // If we reversed a previous group, we just link its last node
                    // (which is 'prevLast') to the start of the remaining list ('temp').
                    prevLast.next = temp;
                }
                // Break out of the loop since we're done processing.
                break;
            }

            // 3. Prep for reversal: Sever the current group from the rest of the list.
            ListNode nextNode = kThNode.next; // Save the head of the NEXT group.
            kThNode.next = null; // Cut the link to isolate the current group for reversal.

            // 4. Reverse the current group. 'temp' is the head, and it becomes the new tail.
            // 'reverseLinkedList' returns the NEW head, which is 'kThNode' was!
            reverseLinkedList(temp);

            // 5. Connect the reversed group to the rest of the list. This is where we
            // "optimize the pipeline" and update the overall structure.
            if (prevLast == null) {
                // Case A: This is the VERY FIRST group we've reversed.
                // The new head of the entire list is the head of the current reversed group (which was 'kThNode').
                head = kThNode;
            } else {
                // Case B: We've reversed previous groups.
                // The last node of the PREVIOUS reversed group ('prevLast') must point to
                // the NEW head of the CURRENT reversed group ('kThNode').
                prevLast.next = kThNode;
            }

            // 6. Set up for the next iteration (move pointers forward).
            // The new LAST node of the current reversed group is 'temp' (the original head).
            prevLast = temp;
            // The starting node of the NEXT group is 'nextNode' (what we saved in step 3).
            temp = nextNode;
        }
        // Return the potentially new head of the whole linked list.
        return head;
    }

    /**
     * Time Complexity: O(k)
     * Reasoning: We iterate through k nodes to find the k-th node.
     *
     * Space Complexity: O(1)
     * Reasoning: We only use a few pointers.
     *
     * Helper function to find the k-th node starting from 'temp'.
     * @param temp The starting node.
     * @param k The step count (k-1 steps needed).
     * @return The k-th node, or null if there are fewer than k nodes.
     */
    static ListNode getKthNode(ListNode temp, int k) {
        // We need to move k-1 steps to land on the k-th node.
        k -= 1;
        // Loop until we hit the end of the list OR we've taken k-1 steps.
        while (temp != null && k > 0) {
            k--;
            temp = temp.next;
        }
        return temp; // Returns the k-th node or null.
    }

    /**
     * Time Complexity: O(k)
     * Reasoning: We traverse exactly k nodes in the group.
     *
     * Space Complexity: O(1)
     * Reasoning: Standard iterative reversal uses only constant extra pointers.
     *
     * Classic iterative linked list reversal for a single group.
     * @param head The head of the group to be reversed.
     * @return The new head (original tail) of the reversed group.
     */
    static ListNode reverseLinkedList(ListNode head) {
        ListNode temp = head; // The current node being processed.
        ListNode prev = null; // The node before 'temp' in the NEW (reversed) list.

        // Standard iterative reversal logic.
        while(temp != null){
            ListNode front = temp.next; // Save the NEXT node to avoid losing the list.
            temp.next = prev; // REVERSE the link: point current node to the PREVIOUS node.
            prev = temp; // Move 'prev' one step forward (it becomes the new reversed list's head).
            temp = front; // Move 'temp' one step forward (to the original next node).
        }
        // 'prev' is the new head of the reversed sublist.
        return prev;
    }

    /**
     * Helper function to print the linked list for demonstration.
     * @param head The head of the list.
     */
    static void printLinkedList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + (temp.next != null ? " -> " : ""));
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * Main method for execution and demonstration. Let's see this forward-thinking
     * solution in action!
     */
    public static void main(String[] args) {
        System.out.println("🚀 Initiating Reverse Nodes in K-Group Demo...");
        // Setup initial list: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        int k = 3;
        System.out.print("Original List: ");
        printLinkedList(head);
        System.out.println("Group size (k): " + k);

        // Execute the core logic
        ListNode newHead = reverseKGroup(head, k);

        System.out.print("Post-Synergy List (k=3): ");
        // Expected: 3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 7 -> 8 (Since the last group of 2 is < k, it's not reversed)
        printLinkedList(newHead);

        System.out.println("\n--- Testing Edge Case (k=5) ---");
        // Setup a new list: 10 -> 20 -> 30 -> 40 -> 50
        ListNode head2 = new ListNode(10);
        head2.next = new ListNode(20);
        head2.next.next = new ListNode(30);
        head2.next.next.next = new ListNode(40);
        head2.next.next.next.next = new ListNode(50);
        int k2 = 5;
        System.out.print("Original List: ");
        printLinkedList(head2);
        System.out.println("Group size (k): " + k2);

        // Execute: Should reverse the whole list since N % k == 0
        ListNode newHead2 = reverseKGroup(head2, k2);

        System.out.print("Post-Synergy List (k=5): ");
        // Expected: 50 -> 40 -> 30 -> 20 -> 10
        printLinkedList(newHead2);

        System.out.println("\n✅ Pipeline Optimized. Mission Accomplished.");
    }
}