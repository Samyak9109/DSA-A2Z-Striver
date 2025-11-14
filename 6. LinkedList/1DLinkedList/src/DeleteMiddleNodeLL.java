/**
 * Class containing methods to delete the middle node of a Linked List.
 * Assumes a separate Node class exists.
 */
public class DeleteMiddleNodeLL {

    // --- Brute-Force Approach: Two Pass Strategy ---
    /**
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     *
     * Reasoning: Two full list traversals (O(N) + O(N/2)) means O(N) time. Constant extra space.
     *
     * @param head Head of the list.
     * @return Head after deleting the middle node.
     */
    static Node deleteMiddleBrute(Node head) {
        // Edge Case: 0 or 1 node, return null.
        if (head == null || head.next == null) {
            return null;
        }

        // 1. Pass 1: Count total nodes (N).
        Node temp = head;
        int n = 0;
        while (temp != null) {
            n++;
            temp = temp.next;
        }

        // Calculate steps needed to reach the node *before* the middle (index (N/2) - 1).
        int stepsToPrevious = (n / 2) - 1;

        // 2. Pass 2: Traverse to the node *before* the middle.
        temp = head;
        for (int i = 0; i < stepsToPrevious; i++) {
            temp = temp.next;
        }

        // 3. Deletion: Bypass the middle node (temp.next).
        temp.next = temp.next.next;

        return head;
    }

    // --- Optimal Approach: Slow and Fast Pointers (Single Pass) ---
    /**
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     *
     * Reasoning: Single list traversal using two pointers. Max N/2 iterations = O(N). Constant extra space.
     *
     * @param head Head of the list.
     * @return Head after deleting the middle node.
     */
    static Node deleteMiddleOptimal(Node head) {
        // Edge Case: 0 or 1 node, return null.
        if (head == null || head.next == null) {
            return null;
        }

        // Initialize three pointers: `fast` (2x speed), `slow` (1x speed), and `previous` (tracks node before slow).
        Node previous = head;
        Node slow = head;
        Node fast = head;

        // Loop until `fast` reaches the end. When loop ends, `slow` is at the middle node,
        // and `previous` is at the node *before* the middle. We gotta catch the node before deletion!
        while (fast != null && fast.next != null) {
            previous = slow;      // previous lags one step behind slow.
            slow = slow.next;     // slow moves one step.
            fast = fast.next.next; // fast moves two steps.
        }

        // Deletion: Link `previous` to the node *after* `slow` (the middle node).
        previous.next = slow.next;

        return head;
    }


    /**
     * Utility method to print the list (for demonstration).
     * @param head The head of the linked list.
     */
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + (temp.next != null ? " -> " : ""));
            temp = temp.next;
        }
        System.out.println();
    }

    // --- Execution and Demonstration ---
    public static void main(String[] args) {
        // --- Test Case 1: Odd Length (N=5) ---
        System.out.println("--- Test Case 1: Odd Length (N=5) ---");
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3); // Middle node
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        System.out.print("Original List: ");
        printList(head1);

        // Brute Force Test
        Node head1Brute = deleteMiddleBrute(head1);
        System.out.print("Brute-Force Result: ");
        printList(head1Brute);
        // Reset list for Optimal Test
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);

        // Optimal Test
        Node head1Optimal = deleteMiddleOptimal(head1);
        System.out.print("Optimal Result: ");
        printList(head1Optimal);

        System.out.println("-------------------------------------");

        // --- Test Case 2: Even Length (N=6) ---
        System.out.println("--- Test Case 2: Even Length (N=6) ---");
        Node head2 = new Node(10);
        head2.next = new Node(20);
        head2.next.next = new Node(30); // Middle node
        head2.next.next.next = new Node(40);
        head2.next.next.next.next = new Node(50);
        head2.next.next.next.next.next = new Node(60);
        System.out.print("Original List: ");
        printList(head2);

        // Brute Force Test
        Node head2Brute = deleteMiddleBrute(head2);
        System.out.print("Brute-Force Result: ");
        printList(head2Brute);
        // Reset list for Optimal Test
        head2 = new Node(10);
        head2.next = new Node(20);
        head2.next.next = new Node(30);
        head2.next.next.next = new Node(40);
        head2.next.next.next.next = new Node(50);
        head2.next.next.next.next.next = new Node(60);

        // Optimal Test
        Node head2Optimal = deleteMiddleOptimal(head2);
        System.out.print("Optimal Result: ");
        printList(head2Optimal);

        System.out.println("-------------------------------------");

        // --- Test Case 3: Edge Case (N=2) ---
        System.out.println("--- Test Case 3: Edge Case (N=2) ---");
        Node head3 = new Node(100);
        head3.next = new Node(200); // Middle node
        System.out.print("Original List: ");
        printList(head3);

        // Optimal Test
        Node head3Optimal = deleteMiddleOptimal(head3);
        System.out.print("Optimal Result: ");
        printList(head3Optimal); // Expected: 200

        System.out.println("-------------------------------------");

        // --- Test Case 4: Edge Case (N=1) ---
        System.out.println("--- Test Case 4: Edge Case (N=1) ---");
        Node head4 = new Node(999);
        System.out.print("Original List: ");
        printList(head4);

        // Optimal Test
        Node head4Optimal = deleteMiddleOptimal(head4);
        System.out.print("Optimal Result: ");
        if (head4Optimal == null) System.out.println("null (List is empty)");
        else printList(head4Optimal);
    }
}