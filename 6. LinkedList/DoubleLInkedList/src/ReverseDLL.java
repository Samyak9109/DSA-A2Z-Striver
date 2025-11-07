public class ReverseDLL {

    /**
     * Optimal DLL reverse (pointer reversal)
     * Time Complexity: O(n) -> one pass through the list
     * Space Complexity: O(1) -> only pointers used
     *
     * Logic:
     * - For each node, swap next and back pointers
     * - After the pass, the last processed node's back pointer will point to the new head
     */
    static Node reverse(Node head) {
        if (head == null || head.next == null) return head;

        Node current = head;
        Node temp = null;

        while (current != null) {
            // swap next and back
            temp = current.back;
            current.back = current.next;
            current.next = temp;

            // move to the next node in original sequence
            current = current.back;
        }

        // temp now points to the last processed node's original back
        // new head is temp.back after swapping
        return temp != null ? temp.back : null;
    }

    // Helper to create DLL from array
    static Node createFromArray(int[] arr) {
        if (arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node prev = head;
        for (int i = 1; i < arr.length; i++) {
            Node curr = new Node(arr[i]);
            prev.next = curr;
            curr.back = prev;
            prev = curr;
        }
        return head;
    }

    // Print DLL forward
    static void printForward(Node head) {
        Node t = head;
        while (t != null) {
            System.out.print(t.data + (t.next != null ? " <-> " : ""));
            t = t.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        Node head = createFromArray(arr);

        System.out.println("Original DLL:");
        printForward(head);

        head = reverse(head);
        System.out.println("Reversed DLL:");
        printForward(head);
    }
}
