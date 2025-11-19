public class DeleteOccurrenceOfKey {
    /**
     * * Time Complexity: O(N)
     * - We iterate through the list exactly once, where N is the number of nodes.
     * - Each deletion operation (re-linking pointers) is an O(1) operation.
     * * Space Complexity: O(1)
     * - We only use a few extra pointer variables (temp) regardless of the list size.
     */
    static Node deleteOccurrence(Node head, int key) {
        // Edge case: If the list is null, just return null. Stay ahead of the curve!
        if (head == null) {
            return null;
        }

        Node temp = head; // Our main pointer to traverse the list.

        // Step 1: Traverse the list and handle deletions on-the-fly.
        while (temp != null) {
            Node nextNode = temp.next; // Cache the next node before potential deletion.

            // Step 2: Check if the current node holds the key we need to delete.
            if (temp.data == key) {

                // Case A: Deleting the Head Node (and potentially other nodes)
                if (temp == head) {
                    head = nextNode; // Promote the next node to the new head.
                    // If the new head exists, its 'back' pointer must be null.
                    if (head != null) {
                        head.back = null;
                    }
                    // The old head (temp) will be garbage collected.
                }
                // Case B: Deleting a Middle or Tail Node
                else {
                    Node prevNode = temp.back; // The node right before 'temp'.
                    Node forwardNode = temp.next; // The node right after 'temp'.

                    // Re-link the previous node's 'next' pointer.
                    // This bypasses 'temp' and connects 'prevNode' to 'forwardNode'.
                    prevNode.next = forwardNode;

                    // Re-link the forward node's 'back' pointer.
                    // This is for the case where 'temp' is NOT the tail.
                    if (forwardNode != null) {
                        forwardNode.back = prevNode;
                    }

                    // The 'temp' node is now isolated and ready for garbage collection.
                    // We don't explicitly need to do temp.next = null and temp.back = null,
                    // but it's a clean practice if the garbage collector wasn't so smart.
                }
            }

            // Step 3: Move our main pointer to the cached next node and continue the traversal.
            temp = nextNode;
        }

        return head; // Return the (potentially new) head of the optimized list.
    }

    // Helper method to print the list (forward traversal)
    public static void printList(Node head) {
        if (head == null) {
            System.out.println("The list is empty. Empty pipeline.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " <-> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    /**
     * Main method for execution and demonstration. Let's see this forward-thinking solution in action!
     */
    public static void main(String[] args) {
        // --- Setup: Building a sample doubly linked list ---
        Node head = new Node(10);
        Node node2 = new Node(20);
        Node node3 = new Node(10); // Duplicate key
        Node node4 = new Node(40);
        Node node5 = new Node(20); // Duplicate key
        Node node6 = new Node(10); // Duplicate key and tail

        // Link them up (forward pointers)
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        // Link them up (backward pointers)
        node2.back = head;
        node3.back = node2;
        node4.back = node3;
        node5.back = node4;
        node6.back = node5;

        System.out.println("--- 📊 Initial List State ---");
        System.out.print("Original List: ");
        printList(head); // Output: 10 <-> 20 <-> 10 <-> 40 <-> 20 <-> 10
        System.out.println("-----------------------------");

        // --- Demo 1: Deleting a key that is the head, middle, and tail (Key: 10) ---
        int keyToDelete1 = 10;
        System.out.println("\n🚀 Optimizing pipeline: Deleting all occurrences of key " + keyToDelete1);
        head = deleteOccurrence(head, keyToDelete1);

        System.out.print("Resulting List: ");
        printList(head); // Expected Output: 20 <-> 40 <-> 20
        System.out.println("-----------------------------");


        // --- Demo 2: Deleting a key in the new list (Key: 20) ---
        int keyToDelete2 = 20;
        System.out.println("\n🚀 Optimizing pipeline: Deleting all occurrences of key " + keyToDelete2);
        head = deleteOccurrence(head, keyToDelete2);

        System.out.print("Resulting List: ");
        printList(head); // Expected Output: 40
        System.out.println("-----------------------------");

        // --- Demo 3: Deleting the last remaining node (Key: 40) ---
        int keyToDelete3 = 40;
        System.out.println("\n🚀 Optimizing pipeline: Deleting all occurrences of key " + keyToDelete3);
        head = deleteOccurrence(head, keyToDelete3);

        System.out.print("Resulting List: ");
        printList(head); // Expected Output: The list is empty. Empty pipeline.
        System.out.println("-----------------------------");
    }
}