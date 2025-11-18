import java.util.LinkedList; // Just for testing utility, keeping the Node class separate as requested

// Assuming the Node class is in a separate file like this (as per your request):
/*
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
*/

public class AddOneToLL {

    /**
     * Reverses a singly linked list.
     * This is a utility function used in the brute force approach.
     *
     * @param node The head of the linked list to be reversed.
     * @return The new head of the reversed list.
     */
    static Node reverseList(Node node) {
        // Time Complexity: O(N) - We traverse every node once.
        // Space Complexity: O(1) - We use a constant number of pointers (prev, current, nextNode).
        Node prev = null;
        Node current = node;

        // Iterate through the list, re-pointing each node's 'next' to the 'prev' node.
        while (current != null) {
            Node nextNode = current.next; // 1. Save the next node before modifying current.next
            current.next = prev;         // 2. Reverse the link: current now points to prev
            prev = current;              // 3. Move 'prev' to 'current' (prev is now the new head of the reversed portion)
            current = nextNode;          // 4. Move 'current' to the next node in the original list
        }
        return prev; // 'prev' will hold the reference to the new head (the last node of the original list)
    }

    /**
     * Bruteforce Approach: Reverse -> Add One with Carry -> Reverse Back.
     * This is super simple to think about because we handle the carry like standard addition.
     *
     * @param head The head of the linked list representing the number.
     * @return The head of the new linked list representing the number + 1.
     *
     * Time Complexity: O(N)
     * Reasoning: We traverse the list three times: O(N) for reverse, O(N) for adding 1, and O(N) for reversing back.
     * This simplifies to O(N).
     * Space Complexity: O(1)
     * Reasoning: We only use a few extra pointers and, potentially, one new node at the end (not recursive stack space).
     */
    static Node addOneLLBrute(Node head) {
        // Step 1: Reverse the list so we can start adding from the least significant digit (LSD).
        // This makes carry propagation straightforward, just like elementary school addition.
        head = reverseList(head);

        Node current = head;
        int carry = 1; // Starting carry is 1 because we are adding one to the number.

        // Step 2: Traverse the list and perform the addition with carry.
        while (current != null && carry > 0) {
            int sum = current.data + carry;
            current.data = sum % 10; // The new digit is the remainder (e.g., 10 % 10 = 0)
            carry = sum / 10;        // The new carry is the quotient (e.g., 10 / 10 = 1)

            // CRITICAL CHECK: If we hit the end of the list and still have a carry (e.g., 999 + 1 = 1000),
            // we need to dynamically allocate a new node for the final carry.
            if (current.next == null && carry > 0) {
                current.next = new Node(carry);
                carry = 0; // Stop the loop/carry now that it's placed.
            }

            current = current.next;
        }

        // Step 3: Reverse the list back to its original order to represent the correct number.
        head = reverseList(head);
        return head;
    }

    /**
     * Optimal Approach: Recursion with "Phantom Carry" (No Reversal)
     * This is the real synergy move! We use the recursion stack to simulate the reverse traversal
     * and propagate the carry *up* the stack as the function calls return.
     *
     * @param head The head of the linked list.
     * @return The head of the new linked list (or a new node if the head changes).
     *
     * Time Complexity: O(N)
     * Reasoning: We traverse the list once via the recursive calls.
     * Space Complexity: O(N)
     * Reasoning: The recursion stack depth goes up to N (the number of nodes).
     */
    static Node addOneLLOptimal(Node head) {
        // The core recursive function handles the addition and returns the final carry (0 or 1).
        int finalCarry = solveRecursive(head);

        // Outside the recursion, we check if the entire list resulted in a carry (e.g., 999 -> 1000).
        if (finalCarry == 1) {
            // If so, we gotta allocate a new head node and link the old list as its 'next'.
            Node newHead = new Node(1);
            newHead.next = head;
            return newHead; // New head, new number!
        }

        return head; // No carry, so the original head is still correct.
    }

    /**
     * Recursive helper function to perform addition and carry propagation.
     *
     * @param node The current node being processed.
     * @return The carry (0 or 1) that needs to be passed to the previous node (parent call).
     */
    private static int solveRecursive(Node node) {
        // Base Case: We hit the end of the list (null). No carry to generate, so return 0.
        if (node == null) {
            return 1; // TRICK: Instead of a base carry, we return the initial '1' to be added here.
            // When the first return unwinds to the last digit, the carry becomes '1'.
        }

        // RECURSIVE STEP: Go deep first! This traverses to the LSD.
        // When this call returns, 'carry' will hold the carry generated by the nodes *after* the current one.
        int carry = solveRecursive(node.next);

        // If 'carry' is 0, it means all the remaining additions (including the initial +1) have been done
        // and no further carry is propagating. We can stop the process now to save time (though O(N) remains).
        if (carry == 0) {
            return 0; // Mission accomplished for this branch.
        }

        // PERFORM ADDITION:
        int sum = node.data + carry;
        node.data = sum % 10;
        int newCarry = sum / 10;

        // Propagate the new carry up the stack.
        return newCarry;
    }

    /**
     * Utility method to print the linked list.
     * @param head The head of the linked list.
     */
    static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    // Main method to demonstrate and execute all approaches.
    public static void main(String[] args) {
        System.out.println("--- Brute Force Demo (Reverse -> Add -> Reverse) ---");
        // Case 1: Simple carry (456 + 1 = 457)
        Node head1 = new Node(4);
        head1.next = new Node(5);
        head1.next.next = new Node(6);
        System.out.print("Input: ");
        printList(head1);
        Node result1 = addOneLLBrute(head1);
        System.out.print("Output (457): ");
        printList(result1); // Expected: 4 -> 5 -> 7

        // Case 2: Full carry propagation and new node (999 + 1 = 1000)
        Node head2 = new Node(9);
        head2.next = new Node(9);
        head2.next.next = new Node(9);
        System.out.print("Input: ");
        printList(head2);
        Node result2 = addOneLLBrute(head2);
        System.out.print("Output (1000): ");
        printList(result2); // Expected: 1 -> 0 -> 0 -> 0

        System.out.println("\n--- Optimal Demo (Recursive Phantom Carry) ---");
        // Case 3: Simple carry (123 + 1 = 124)
        Node head3 = new Node(1);
        head3.next = new Node(2);
        head3.next.next = new Node(3);
        System.out.print("Input: ");
        printList(head3);
        Node result3 = addOneLLOptimal(head3);
        System.out.print("Output (124): ");
        printList(result3); // Expected: 1 -> 2 -> 4

        // Case 4: Full carry propagation and new head (9 + 1 = 10)
        Node head4 = new Node(9);
        System.out.print("Input: ");
        printList(head4);
        Node result4 = addOneLLOptimal(head4);
        System.out.print("Output (10): ");
        printList(result4); // Expected: 1 -> 0
    }
}
