import java.util.ArrayList;
import java.util.Collections;

public class FlattenLL {

    /**
     * Brute Force Approach: Collect all elements, sort, and reconstruct.
     * This is the "get it done now, optimize later" approach!
     * * Time Complexity: O(N*M + N*M log(N*M))
     * - Let N be the number of main lists (nodes connected by 'next') and M be the max length of a child list.
     * - O(N*M): Traversing all nodes to collect them into the ArrayList.
     * - O(N*M log(N*M)): Sorting the ArrayList, where N*M is the total number of elements.
     * * Space Complexity: O(N*M)
     * - Reasoning: We use an auxiliary ArrayList to store all N*M elements, and then build a new list of the same size.
     * * @param head The head of the main linked list.
     * @return The head of the flattened, sorted linked list.
     */
    static ListNode flattenLLBrute(ListNode head) {
        // Step 1: Initialize the data structure to hold all values.
        // We're using an ArrayList because it makes sorting super easy.
        ArrayList<Integer> arr = new ArrayList<>();

        // Step 2: Traverse the main list (using 'next' pointers).
        while (head != null) {
            // Step 3: Traverse the child list (using 'child' pointers) for the current node.
            ListNode t2 = head;
            while (t2 != null) {
                // Collect the data!
                arr.add(t2.val);
                t2 = t2.child;
            }
            // Move to the next main list.
            head = head.next;
        }

        // Step 4: The heavy lifting—sorting everything!
        // This is the step that makes the time complexity high.
        Collections.sort(arr);

        // Step 5: Reconstruct the final flattened list.
        return convertArrToLinkedList(arr);
    }

    /**
     * Helper Method: Converts a sorted ArrayList back into a linked list
     * using the 'child' pointer for the main sequence.
     * * Time Complexity: O(P), where P is the total number of elements.
     * * Space Complexity: O(P), for creating the new list nodes.
     */
    static ListNode convertArrToLinkedList(ArrayList<Integer> arr) {
        // Dummy node helps avoid edge cases with head creation. A true developer move!
        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;

        // Iterate through the sorted array and create the new list, connecting with 'child'.
        for (Integer integer : arr) {
            temp.child = new ListNode(integer);
            temp = temp.child;
        }
        return dummyNode.child;
    }

    /**
     * Helper Method: Merges two sorted linked lists (connected by 'child' pointers) into one.
     * This is the core engine for the optimal, divide-and-conquer solution!
     * * Time Complexity: O(L1 + L2), where L1 and L2 are the lengths of the two lists.
     * - Reasoning: We traverse each list once completely to merge them.
     * * Space Complexity: O(1) (excluding the output list structure).
     * - Reasoning: We only use a constant number of extra pointers.
     * * @param list1 The head of the first sorted list (down pointers).
     * * @param list2 The head of the second sorted list (down pointers).
     * @return The head of the merged and sorted list.
     */
    public static ListNode merge(ListNode list1, ListNode list2) {
        // Create a dummy node as a placeholder. We're pointing to the results using 'res'.
        ListNode dummyNode = new ListNode(-1);
        ListNode res = dummyNode;

        // Step 1: Standard merge loop. While both lists have elements, compare and link the smaller one.
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                res.child = list1; // Link the smaller node from list1.
                res = list1;
                list1 = list1.child; // Move list1's pointer down.
            } else {
                res.child = list2; // Link the smaller node from list2.
                res = list2;
                list2 = list2.child; // Move list2's pointer down.
            }
            // CRITICAL: We ensure the 'next' pointer of the node we just added is null.
            // Why? Because we are ONLY flattening vertically (down/child), so the horizontal/next links must be purged.
            res.next = null;
        }

        // Step 2: After the loop, one list might still have remaining nodes (since they're sorted).
        // Connect the non-null remainder directly.
        if (list1 != null) {
            res.child = list1;
        } else {
            res.child = list2;
        }

        // Final sanity check: Ensure the head of the merged list has no horizontal link.
        // The rest of the nodes' 'next' links were handled inside the loop.
        if (dummyNode.child != null) {
            dummyNode.child.next = null;
        }

        return dummyNode.child;
    }

    /**
     * Optimal Approach: Recursive Merge (Divide and Conquer).
     * This is the true forward-thinking, performance-synergy solution!
     * * Time Complexity: O(P)
     * - Reasoning: P is the total number of nodes. Every node is visited and merged exactly once.
     * The complexity is similar to merging k sorted lists (O(P log k), where k is the number of head lists).
     * * Space Complexity: O(N)
     * - Reasoning: O(N) is the recursive stack space, where N is the number of main lists (horizontal nodes).
     * * @param head The head of the main linked list.
     * @return The head of the flattened, sorted linked list.
     */
    public static ListNode flattenLLOptimal(ListNode head) {
        // Base case: If we hit the end of the list, or if the list has only one column, return it.
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: DIVIDE & CONQUER (Recursive Call).
        // Recursively flatten the rest of the list (everything to the right of 'head').
        // This is a post-order traversal pattern.
        ListNode mergedHead = flattenLLOptimal(head.next);

        // Step 2: PROCESS (Merge/Conquer).
        // The list starting at 'head' (the current vertical list) is sorted.
        // 'mergedHead' is the result of flattening the rest of the list, which is also sorted.
        // We merge these two sorted lists using the O(L1+L2) merge helper.
        head = merge(head, mergedHead);

        // Step 3: Return the new head of the now-merged and sorted structure.
        return head;
    }

    // --- Helper for Main Method ---

    // Helper function to print the list (will only print the 'child' sequence)
    public static void printFlattenedList(ListNode head) {
        ListNode current = head;
        System.out.print("Flattened List: ");
        while (current != null) {
            System.out.print(current.val + (current.child != null ? " -> " : ""));
            current = current.child;
        }
        System.out.println();
    }

    // Helper function to create the complex structure for testing
    // Example Structure:
    // 5 -> 10 -> 19 -> 28
    // |    |     |     |
    // 7    20    22    35
    // |    |     |     |
    // 8    22    50    40
    // |    |           |
    // 30   35          45
    public static ListNode createTestList() {
        // Column 1 (5 -> 7 -> 8 -> 30)
        ListNode L1_4 = new ListNode(30);
        ListNode L1_3 = new ListNode(8); L1_3.child = L1_4;
        ListNode L1_2 = new ListNode(7); L1_2.child = L1_3;
        ListNode L1_1 = new ListNode(5); L1_1.child = L1_2;

        // Column 2 (10 -> 20 -> 22 -> 35)
        ListNode L2_4 = new ListNode(35);
        ListNode L2_3 = new ListNode(22); L2_3.child = L2_4;
        ListNode L2_2 = new ListNode(20); L2_2.child = L2_3;
        ListNode L2_1 = new ListNode(10); L2_1.child = L2_2;

        // Column 3 (19 -> 22 -> 50)
        ListNode L3_3 = new ListNode(50);
        ListNode L3_2 = new ListNode(22); L3_2.child = L3_3;
        ListNode L3_1 = new ListNode(19); L3_1.child = L3_2;

        // Column 4 (28 -> 35 -> 40 -> 45)
        ListNode L4_4 = new ListNode(45);
        ListNode L4_3 = new ListNode(40); L4_3.child = L4_4;
        ListNode L4_2 = new ListNode(35); L4_2.child = L4_3;
        ListNode L4_1 = new ListNode(28); L4_1.child = L4_2;

        // Connect the main list horizontally ('next' pointers)
        L1_1.next = L2_1;
        L2_1.next = L3_1;
        L3_1.next = L4_1;

        return L1_1;
    }

    /**
     * Main method for execution and demonstration.
     * Time to showcase this forward-thinking solution!
     */
    public static void main(String[] args) {
        System.out.println("--- 🚀 Flattening LL Demo: Optimal Pipeline Synergy 🚀 ---");

        // Create the test list
        ListNode testHead_Brute = createTestList();
        ListNode testHead_Optimal = createTestList();

        System.out.println("\n--- 💥 Brute Force (O(N*M log(N*M))) ---");
        // Traverses, sorts, and reconstructs. Slow for large datasets.
        ListNode flattenedBrute = flattenLLBrute(testHead_Brute);
        printFlattenedList(flattenedBrute);
        // Expected: 5 -> 7 -> 8 -> 10 -> 19 -> 20 -> 22 -> 22 -> 28 -> 30 -> 35 -> 35 -> 40 -> 45 -> 50

        System.out.println("\n--- ✨ Optimal Recursive Merge (O(P)) ---");
        // Uses Divide & Conquer (Recursion) and a highly efficient O(L1+L2) merge.
        ListNode flattenedOptimal = flattenLLOptimal(testHead_Optimal);
        printFlattenedList(flattenedOptimal);
        // Expected: 5 -> 7 -> 8 -> 10 -> 19 -> 20 -> 22 -> 22 -> 28 -> 30 -> 35 -> 35 -> 40 -> 45 -> 50

        System.out.println("\n✅ The Optimal solution avoids the costly sort operation, leveraging the fact that columns are already sorted for maximum performance! A true **forward-thinking** design!");
    }
}