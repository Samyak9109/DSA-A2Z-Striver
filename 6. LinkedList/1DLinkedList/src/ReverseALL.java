public class ReverseALL {

    // -----------------------------------------------------------
    // 🥱 Brute Force Approach (Using a Stack, so we just vibe here...)
    // Time Complexity: O(n) → we traverse list twice (push + pop).
    // Space Complexity: O(n) → we store all data in stack.
    // -----------------------------------------------------------
    static Node reverseLLBrute(Node head) {
        // Just a pointer to walk across the OG list
        Node temp = head;

        java.util.Stack<Integer> stack = new java.util.Stack<>();

        // Step 1: Push everything into stack (LIFO gang 😎)
        while (temp != null) {
            stack.push(temp.data); // storing data values
            temp = temp.next;
        }

        // Step 2: Pop from stack and overwrite linked list values
        temp = head;
        while (temp != null) {
            temp.data = stack.pop(); // reassign values in reverse order
            temp = temp.next;
        }

        return head; // list reversed (at data level only)
    }


    // -----------------------------------------------------------
    // 🚀 Better Approach (Iterative Pointer Reversal)
    // Time Complexity: O(n) → One clean pass.
    // Space Complexity: O(1) → No extra space; just pointers.
    // -----------------------------------------------------------
    static Node reverseLLOptimal1(Node head) {
        Node temp = head;
        Node prev = null; // will become new head eventually

        // The REAL reverse operation happens here — pointer rewrite game strong.
        while (temp != null) {
            Node front = temp.next; // preserve next node before breaking links
            temp.next = prev;       // reverse pointer direction
            prev = temp;            // slide prev forward
            temp = front;           // slide temp forward
        }

        return prev; // prev is now the CEO (new head)
    }


    // -----------------------------------------------------------
    // 🧙 Recursive Approach (Elegant + Brain-flex + O(n) stack usage)
    // Time Complexity: O(n) → Each node visited once.
    // Space Complexity: O(n) → Call stack grows per recursive call.
    // -----------------------------------------------------------
    static Node reverseLLRecursive(Node head) {
        // Base case: if empty list OR just one node → already reversed boss
        if (head == null || head.next == null) {
            return head;
        }

        // Recursively reverse the rest of the list
        Node newHead = reverseLLRecursive(head.next);

        // head.next is now the last node in reversed part → point it back to head
        head.next.next = head;

        // detach original link
        head.next = null;

        return newHead; // propagating the new head upwards
    }


    // -----------------------------------------------------------
    // 🧪 main() to synergize and demo the pipeline
    // -----------------------------------------------------------
    public static void main(String[] args) {

        // assuming your Node class + helper to create list exists 💀
        Node head = new Node(1, new Node(2, new Node(3, new Node(4, null))));

        System.out.println("Original:");
        printList(head);

        head = reverseLLBrute(head);
        System.out.println("\nAfter Brute Reverse:");
        printList(head);

        head = reverseLLOptimal1(head);
        System.out.println("\nAfter Optimal Iterative Reverse:");
        printList(head);

        head = reverseLLRecursive(head);
        System.out.println("\nAfter Recursive Reverse:");
        printList(head);
    }

    // Quick print function just to flex the output visually
    static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}
