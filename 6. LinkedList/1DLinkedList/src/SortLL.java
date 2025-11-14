import java.util.ArrayList;
import java.util.Collections;

// Assuming the Node class is defined elsewhere, like this:
// class Node {
//     int data;
//     Node next;
//     Node(int data) {
//         this.data = data;
//         this.next = null;
//     }
//     Node(int data, Node next) {
//         this.data = data;
//         this.next = next;
//     }
// }

public class SortLL {

    /**
     * Time Complexity: O(N log N)
     * - O(N) to traverse the list and copy all N elements into the ArrayList.
     * - O(N log N) for the Collections.sort() method (typically Timsort or MergeSort).
     * - O(N) to traverse the list again and update the N nodes with sorted values.
     * - Overall: O(N) + O(N log N) + O(N) = O(N log N).
     * * Space Complexity: O(N)
     * - We use an auxiliary ArrayList to store N elements from the linked list,
     * which requires O(N) space.
     */
    static Node sortLLBrute(Node head) {
        // Step 1: Initialize a dynamic array (ArrayList) to store all node values.
        // This is where we temporarily pull the data out of the linked list structure.
        ArrayList<Integer> arr = new ArrayList<>();

        // Step 2: Traverse the entire linked list and populate the ArrayList.
        Node temp = head;
        while (temp != null) {
            arr.add(temp.data); // Copy the data of the current node.
            temp = temp.next;   // Move to the next node.
        }

        // Step 3: Use Java's built-in sorting algorithm (like Timsort) to sort the array.
        // This is the O(N log N) heavy lifting part.
        Collections.sort(arr);

        // Step 4: Traverse the linked list again and update node data with sorted values.
        // We are "patching" the original list with the new, sorted data.
        temp = head;
        for (int i = 0; i < arr.size(); i++) {
            temp.data = arr.get(i); // Update the node's data field.
            temp = temp.next;       // Move to the next node.
        }

        // Step 5: Return the head of the now-sorted list.
        return head;
    }

    // --- Helper Function 1: Merging Two Sorted Lists ---

    /**
     * Time Complexity: O(L1 + L2) -> O(N)
     * - We traverse each list only once, visiting each node at most one time.
     * * Space Complexity: O(1)
     * - We only use a few extra pointers (dummyNode, temp, list1, list2)
     * to manage the merging process. No auxiliary data structures are created
     * that depend on the input size.
     */
    public Node mergeTwoSortedLinkedLists(Node list1, Node list2) {
        // Create a dummy node to act as the starting point of the new merged list.
        // This makes the code cleaner, avoiding edge cases for inserting the first element.
        Node dummyNode = new Node(-1, null);

        // 'temp' pointer is the one we use to build the merged list, always pointing
        // to the last node added.
        Node temp = dummyNode;

        // Core merging logic: while both lists have nodes, compare and attach the smaller one.
        while (list1 != null && list2 != null) {
            if (list1.data <= list2.data) {
                temp.next = list1; // Attach node from list1
                list1 = list1.next;  // Move list1's pointer forward
            } else {
                temp.next = list2; // Attach node from list2
                list2 = list2.next;  // Move list2's pointer forward
            }
            temp = temp.next; // Always move the 'temp' pointer to the newly attached node
        }

        // After one list is exhausted, attach the remainder of the other list directly.
        // Since both halves are already sorted, this is a clean O(1) step.
        if (list1 != null) {
            temp.next = list1;
        } else {
            temp.next = list2;
        }

        // The actual head of the merged list is the node after the dummy node.
        return dummyNode.next;
    }

    // --- Helper Function 2: Finding the Middle Node ---

    /**
     * Time Complexity: O(N)
     * - We traverse the list with two pointers, where the fast pointer reaches the end
     * after N/2 iterations. This is linear time.
     * * Space Complexity: O(1)
     * - Only a few extra pointers (slow, fast) are used.
     */
    public Node findMiddle(Node head) {
        // Edge case check: A list with 0 or 1 node is its own middle.
        if (head == null || head.next == null) {
            return head;
        }

        // Initialize slow and fast pointers.
        // Crucially, fast starts at head.next (instead of head) so that when fast hits
        // the end, slow is at the *first* middle node in an even-length list (e.g., 1->2->3->4,
        // slow stops at 2), which is necessary for correct splitting in Merge Sort.
        Node slow = head;
        Node fast = head.next;

        // The fast pointer moves twice as fast as slow.
        while (fast != null && fast.next != null) {
            slow = slow.next;      // Slow moves 1 step
            fast = fast.next.next; // Fast moves 2 steps
        }

        // Slow is now pointing to the end of the first half (the middle node).
        // This is the splitting point.
        return slow;
    }

    // --- Optimal Solution: Merge Sort for Linked List ---

    /**
     * Time Complexity: O(N log N)
     * - The process is: Split (O(log N) depth) and Merge (O(N) per level).
     * - The recurrence relation is T(N) = 2*T(N/2) + O(N).
     * - By Master Theorem or Recurrence Tree method, the complexity is O(N log N).
     * * Space Complexity: O(log N)
     * - This space is consumed by the recursion stack due to the recursive calls
     * to sortLLOptimal, which goes O(log N) levels deep.
     * - We don't use any auxiliary data structures proportional to N, making
     * this approach *in-place* concerning data storage, but not recursion space.
     */
    public Node sortLLOptimal(Node head) {
        // Base Case (The 'Exit Strategy'): If the list is empty or has only one node,
        // it's already sorted. This stops the recursion.
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Divide the list into two halves.
        // We use the slow/fast pointer technique to find the middle point.
        Node middle = findMiddle(head);

        // Step 2: Set up the two separate sub-lists (Left and Right).
        Node right = middle.next; // 'right' starts just after the middle.

        // This is the crucial step: breaking the link to split the list!
        // We terminate the first half at 'middle'.
        middle.next = null;

        Node left = head; // 'left' is the start of the first half.

        // Step 3: Conquer (Recursively sort both halves).
        // We recursively call sort on the smaller halves. This is the O(log N) depth.
        left = sortLLOptimal(left);
        right = sortLLOptimal(right);

        // Step 4: Combine (Merge the sorted halves).
        // The O(N) work per level happens here, where we "zip" the two sorted lists.
        return mergeTwoSortedLinkedLists(left, right);
    }

    // --- Utility Function to Print the List ---
    private static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + (temp.next != null ? " -> " : ""));
            temp = temp.next;
        }
        System.out.println();
    }

    // --- Main Method for Execution and Demonstration ---
    public static void main(String[] args) {
        System.out.println("🤖 Initiating linked list sorting pipeline...");
        SortLL sorter = new SortLL();

        // Let's create an unsorted list for testing: 4 -> 2 -> 1 -> 3 -> 5
        Node headOptimal = new Node(4, new Node(2, new Node(1, new Node(3, new Node(5, null)))));
        System.out.print("\nOriginal List (for Optimal Merge Sort): ");
        printList(headOptimal);

        // ** Test Optimal Solution (Merge Sort) **
        // This is the forward-thinking solution we want to optimize the pipeline with!
        Node sortedOptimal = sorter.sortLLOptimal(headOptimal);
        System.out.print("Optimal Sorted List (O(N log N)): ");
        printList(sortedOptimal);

        // Let's create a new unsorted list for testing the Brute Force: 9 -> 6 -> 7 -> 8
        Node headBrute = new Node(9, new Node(6, new Node(7, new Node(8, null))));
        System.out.print("\nOriginal List (for Brute Force): ");
        printList(headBrute);

        // ** Test Brute Force Solution **
        Node sortedBrute = sortLLBrute(headBrute);
        System.out.print("Brute Force Sorted List (O(N log N) but O(N) space): ");
        printList(sortedBrute);

        System.out.println("\n✅ Pipeline execution complete. Data fully harmonized.");
    }
}

