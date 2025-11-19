import java.util.List;
import java.util.ArrayList;

public class RemoveDuplicates {

    /**
     * **Optimal Approach: Remove Duplicates in a Sorted DLL (Keeping First Occurrence)**
     * * Time Complexity: O(N)
     * - We traverse the list exactly once. Although there's a nested 'while' loop, the inner loop 
     * only iterates over nodes that the outer loop will skip. Every node is visited and processed 
     * at most once (either as 'current' or as a node being skipped by 'nextDistinct').
     * * Space Complexity: O(1)
     * - We only use a few extra pointer variables, regardless of the list size.
     */
    static Node removeDuplicates(Node head) {
        // Edge Case: If the list is empty, there's nothing to do.
        if (head == null) {
            return null;
        }

        Node current = head;

        // Detailed Comment: The outer loop iterates through every unique node in the list.
        while (current != null && current.next != null) {

            // Step 1: Initialize a pointer to start checking for duplicates right after 'current'.
            Node runner = current.next;

            // Step 2: Skip and unlink all nodes with the same value as current.
            // Detailed Comment: The inner loop (runner) quickly skips over all duplicate nodes.
            while (runner != null && runner.data == current.data) {
                // By advancing 'runner', we effectively mark these intermediate nodes for deletion 
                // (they will be garbage collected later).
                runner = runner.next;
            }

            // Step 3: Re-link 'current' to the next distinct node found by the runner.
            // Detailed Comment: We are bypassing the entire block of duplicates.
            current.next = runner;

            // Step 4: Update the 'back' pointer of the distinct node (if it exists).
            // This is crucial for maintaining the integrity of the Doubly Linked List!
            if (runner != null) {
                runner.back = current;
            }

            // Step 5: Move to the newly linked next distinct node and continue the process.
            current = current.next;
        }

        return head; // Return the head of the optimized, duplicate-free list.
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
        // --- Setup: Building a sample doubly linked list with duplicates ---
        // List: 1 <-> 1 <-> 4 <-> 6 <-> 6 <-> 6 <-> 8 <-> 10
        Node head = new Node(1);
        Node n2 = new Node(1);
        Node n3 = new Node(4);
        Node n4 = new Node(6);
        Node n5 = new Node(6);
        Node n6 = new Node(6);
        Node n7 = new Node(8);
        Node n8 = new Node(10);

        // Link them up (forward and backward pointers)
        head.next = n2;    n2.back = head;
        n2.next = n3;      n3.back = n2;
        n3.next = n4;      n4.back = n3;
        n4.next = n5;      n5.back = n4;
        n5.next = n6;      n6.back = n5;
        n6.next = n7;      n7.back = n6;
        n7.next = n8;      n8.back = n7;

        System.out.println("--- 📊 Initial List State ---");
        System.out.print("Original List (with Duplicates): ");
        printList(head); // Output: 1 <-> 1 <-> 4 <-> 6 <-> 6 <-> 6 <-> 8 <-> 10
        System.out.println("-----------------------------");

        // --- Demo: Removing Duplicates ---
        System.out.println("\n🚀 Optimizing pipeline: Removing duplicates...");
        head = removeDuplicates(head);

        System.out.print("Resulting List (Unique Values): ");
        printList(head); // Expected Output: 1 <-> 4 <-> 6 <-> 8 <-> 10
        System.out.println("-----------------------------");
    }
}