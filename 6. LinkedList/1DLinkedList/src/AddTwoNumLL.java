// Assuming the Node class is in a separate file (as per your request):
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

public class AddTwoNumLL {

    /**
     * Optimal Iterative Approach: Adding Two Numbers (Reversed Order)
     * This method simulates manual column addition, moving from the least significant digit (LSD)
     * at the head, towards the most significant digit (MSD) at the tail, and managing the carry.
     *
     * @param l1 The head of the first linked list (number 1).
     * @param l2 The head of the second linked list (number 2).
     * @return The head of the new linked list representing the sum (l1 + l2).
     *
     * Time Complexity: O(max(N, M))
     * Reasoning: We iterate through both lists exactly once. N and M are the lengths of l1 and l2.
     * The loop runs for the length of the longer list.
     * Space Complexity: O(max(N, M))
     * Reasoning: We create a new linked list to store the result, and its length will be at most
     * max(N, M) + 1 (in case of a final carry).
     */
    static Node addTwoNumbers(Node l1, Node l2) {
        // Step 1: Initialize a dummy head and a current pointer.
        // The 'dummyHead' is a phantom node that simplifies code by allowing us to treat
        // the first new node the same as all subsequent nodes.
        Node dummyHead = new Node(0);
        Node current = dummyHead;

        // Start with a carry of 0.
        int carry = 0;

        // Step 2: Traverse both lists simultaneously.
        // We continue the loop as long as EITHER list has nodes OR we still have a carry from the previous sum.
        while (l1 != null || l2 != null || carry != 0) {
            // Get the value of the current digit. If a list is exhausted (null), treat its value as 0.
            int x = (l1 != null) ? l1.data : 0;
            int y = (l2 != null) ? l2.data : 0;

            // Step 3: Calculate the sum, current digit, and new carry.
            int sum = x + y + carry;

            // The carry for the NEXT digit is the quotient (10/10=1, 9/10=0)
            carry = sum / 10;

            // The current digit value is the remainder (10%10=0, 15%10=5)
            int digit = sum % 10;

            // Step 4: Create the new node and advance the pointers.
            // Create a new node with the calculated digit and append it to the result list.
            current.next = new Node(digit);
            current = current.next; // Move the result pointer forward.

            // Move the input pointers forward if they are not null.
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        // Step 5: Return the result.
        // We return dummyHead.next because the 'dummyHead' itself was just a placeholder with value 0.
        return dummyHead.next;
    }

    /**
     * Utility method to print the linked list (Number is printed in reverse for clarity).
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

    // Main method for execution and demonstration.
    public static void main(String[] args) {
        // Example: (3 -> 4 -> 2) + (4 -> 6 -> 5)
        // Represents: 243 + 564 = 807
        Node l1 = new Node(3);
        l1.next = new Node(4);
        l1.next.next = new Node(2); // Number 243

        Node l2 = new Node(4);
        l2.next = new Node(6);
        l2.next.next = new Node(5); // Number 564

        System.out.println("--- Add Two Numbers Demo ---");
        System.out.print("Number 1 (243): ");
        printList(l1);
        System.out.print("Number 2 (564): ");
        printList(l2);

        Node result = addTwoNumbers(l1, l2);

        System.out.print("Result (807): ");
        printList(result); // Expected: 7 -> 0 -> 8 (which is 807)

        System.out.println("\n--- Handling Different Lengths and Carry ---");
        // Example: (9 -> 9) + (1)
        // Represents: 99 + 1 = 100
        Node l3 = new Node(9);
        l3.next = new Node(9); // Number 99

        Node l4 = new Node(1); // Number 1

        System.out.print("Number 1 (99): ");
        printList(l3);
        System.out.print("Number 2 (1): ");
        printList(l4);

        Node result2 = addTwoNumbers(l3, l4);
        System.out.print("Result (100): ");
        printList(result2); // Expected: 0 -> 0 -> 1 (which is 100)
    }
}
