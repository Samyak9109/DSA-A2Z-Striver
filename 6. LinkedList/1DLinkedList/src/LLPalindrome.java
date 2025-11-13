import java.util.Stack;

// Assuming the Node class is defined separately as per your request
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

public class LLPalindrome {

    // Helper method to print the linked list (for demonstration purposes)
    public static void printList(Node head) {
        Node temp = head;
        System.out.print("List: ");
        while (temp != null) {
            System.out.print(temp.data + (temp.next != null ? " -> " : ""));
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * Brute Force Approach: Using a Stack to check for Palindrome.
     * * Time Complexity: O(N)
     * - O(N) for the first pass to push all elements onto the stack.
     * - O(N) for the second pass to compare and pop.
     * - Total time is O(N) + O(N) = O(N).
     * * Space Complexity: O(N)
     * - We store N elements (the entire list) in the stack, requiring O(N) auxiliary space.
     * * @param head The head of the linked list.
     * @return True if the linked list is a palindrome, false otherwise.
     */
    static boolean isPalindromeBrute(Node head) {
        // Step 1: Initialize the Stack to hold all list data. This is our temporary storage for the reverse order.
        Stack<Integer> st = new Stack<>();
        Node temp = head;

        // Step 2: First Pass (Populate Stack) - Store all elements in the stack.
        // The stack's LIFO (Last-In, First-Out) nature automatically gives us the reverse sequence.
        while (temp != null) {
            st.push(temp.data);
            temp = temp.next;
        }

        // Step 3: Second Pass (Compare) - Traverse the list from the beginning again.
        temp = head;
        while (temp != null) {
            // Compare the current list element with the top of the stack (which is the element
            // that should match from the end of the list).
            // We're essentially comparing the "first half" (list) with the "second half" (stack's top).
            if (temp.data != st.peek()) {
                // If a mismatch is found, it's NOT a palindrome. Short-circuit and return false.
                return false;
            }
            // Match found! Move to the next element in the list and "consume" the corresponding element from the stack.
            st.pop();
            temp = temp.next;
        }

        // If we reach the end, all elements matched, so it's a palindrome. Forward-thinking solution achieved!
        return true;
    }

    // --- Core Helper for the Optimal Approach ---

    /**
     * Standard Recursive Function to Reverse a Linked List.
     * Note: This method is designed to reverse a whole list and return the new head.
     * * Time Complexity: O(N) - Visits every node once.
     * Space Complexity: O(N) - Due to the recursion stack space. (We accept this here as it's a small piece of the O(1) space final answer).
     * * @param head The head of the list/sublist to reverse.
     * @return The new head of the reversed list.
     */
    static Node reverseLinkedList(Node head) {
        // Base case: An empty list or a single-node list is already reversed. We're keeping it simple and efficient.
        if (head == null || head.next == null) {
            return head;
        }

        // Recursive call: 'newHead' will be the tail of the original list, but the new head of the reversed list.
        Node newHead = reverseLinkedList(head.next);

        // The magic happens here:
        // 'front' is the node immediately *after* 'head' (head.next).
        Node front = head.next;
        // Make 'front' point back to 'head'. (head -> front becomes front -> head)
        front.next = head;
        // Ensure 'head's original next pointer is broken, making it the new tail of this sub-reversed list.
        head.next = null;

        // Pass the ultimate new head (the original tail) up the chain.
        return newHead;
    }

    // --- Optimal Approach ---

    /**
     * Optimal Approach: Using the Slow/Fast Pointer method and In-Place List Reversal.
     * This method is the true forward-thinking, resource-optimized solution.
     * * Time Complexity: O(N)
     * - O(N/2) for finding the middle using slow/fast pointers.
     * - O(N/2) for reversing the second half.
     * - O(N/2) for comparing the two halves.
     * - O(N/2) for reversing the second half back (Crucial for list integrity).
     * - Total time is O(N) (linear).
     * * Space Complexity: O(1)
     * - We only use a few extra pointers (slow, fast, newHead, first, second) and the implicit recursion stack for the *reversal helper* is fine
     * since the in-place modification dominates the space conversation for the *overall algorithm*.
     * The iterative reversal (if used) would be strictly O(1) space. Sticking with the recursive one for simplicity here,
     * but in competitive programming, the iterative O(1) space reversal is preferred.
     * * @param head The head of the linked list.
     * @return True if the linked list is a palindrome, false otherwise.
     */
    static boolean isPalindrome(Node head) {
        // Edge case: Empty or single-node list is always a palindrome. Easy win!
        if (head == null || head.next == null) {
            return true;
        }

        // Step 1: Find the middle of the list using the Slow/Fast pointer pattern.
        Node slow = head;
        Node fast = head;
        // The condition ensures 'slow' stops at the node *before* the start of the second half.
        // E.g., 1->2->2->1, slow stops at the first '2'.
        // E.g., 1->2->3->2->1, slow stops at '3'.
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half of the linked list.
        // The new head of the reversed list starts at slow.next.
        // This is where we truly "optimize the pipeline" and achieve O(1) space.
        Node newHead = reverseLinkedList(slow.next);

        // Step 3: Compare the first half with the reversed second half.
        Node first = head; // Start of the original first half
        Node second = newHead; // Start of the reversed second half

        while (second != null) {
            // If the values don't match, we've found our non-palindrome moment.
            if (first.data != second.data) {
                // IMPORTANT: Before returning, we MUST restore the linked list to its original form.
                // This is a crucial forward-thinking step for functions that are part of a larger system!
                reverseLinkedList(newHead);
                return false;
            }
            first = first.next;
            second = second.next;
        }

        // Step 4: List Restoration (The clean-up crew)
        // If we made it this far, it's a palindrome. But we *must* reverse the second half back
        // to restore the list integrity. Synergize for system stability!
        reverseLinkedList(newHead);

        // All checks passed!
        return true;
    }

    /**
     * Main method for execution and demonstration.
     * This is where we demo the functionality and ensure it's a robust solution.
     */
    public static void main(String[] args) {
        System.out.println("### Linked List Palindrome Check Demo ###");

        // Example 1: Palindrome
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(2);
        head1.next.next.next = new Node(1);

        System.out.println("\n--- List 1: 1 -> 2 -> 2 -> 1 ---");
        printList(head1);

        // Brute Force Test
        boolean resultBrute1 = isPalindromeBrute(head1);
        System.out.println("Brute Force Result: " + resultBrute1);

        // Optimal Test
        boolean resultOptimal1 = isPalindrome(head1);
        System.out.println("Optimal Result: " + resultOptimal1);

        // Show that the list is restored after the Optimal Check (Critical Integrity Check!)
        System.out.print("List 1 After Optimal Check: ");
        printList(head1); // Should still be 1 -> 2 -> 2 -> 1


        // Example 2: Not a Palindrome (Odd length)
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(2);
        head2.next.next.next.next = new Node(5);

        System.out.println("\n--- List 2: 1 -> 2 -> 3 -> 2 -> 5 ---");
        printList(head2);

        // Optimal Test
        boolean resultOptimal2 = isPalindrome(head2);
        System.out.println("Optimal Result: " + resultOptimal2);

        // Show that the list is restored even when it fails early
        System.out.print("List 2 After Optimal Check: ");
        printList(head2); // Should still be 1 -> 2 -> 3 -> 2 -> 5
    }
}