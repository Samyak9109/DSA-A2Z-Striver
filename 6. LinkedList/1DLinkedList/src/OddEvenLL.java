import java.util.ArrayList;

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

public class OddEvenLL{

    // --- Brute Force Approach (Position-Based) ---

    /**
     * Brute Force Approach: Collecting all nodes into an ArrayList first, then reconnecting them.
     * This method separates based on the node's POSITION (1st, 3rd, 5th... vs 2nd, 4th, 6th...).
     * * Time Complexity: O(N)
     * - O(N) to traverse the list and add nodes to the array.
     * - O(N) to traverse the array and relink the nodes.
     * - Total time is O(N).
     * * Space Complexity: O(N)
     * - We store N node references in the ArrayList, requiring O(N) auxiliary space.
     * * @param head The head of the linked list.
     * @return The head of the reordered linked list.
     */
    static Node oddEvenListBrute(Node head) {
        // Edge case: Handle empty or single-node lists. No reordering needed.
        if (head == null || head.next == null) return head;

        // Step 1: Initialization. We need an array to hold the nodes in the new order.
        ArrayList<Node> arr = new ArrayList<>();

        // Step 2: Traverse and collect ODD-POSITIONED nodes (1st, 3rd, 5th...).
        Node temp = head;
        while (temp != null) {
            arr.add(temp);
            // Move two steps forward to get the next odd-positioned node.
            temp = temp.next != null ? temp.next.next : null;
        }

        // Step 3: Traverse and collect EVEN-POSITIONED nodes (2nd, 4th, 6th...).
        // Start from the second node (head.next).
        temp = head.next;
        while (temp != null) {
            arr.add(temp);
            // Move two steps forward to get the next even-positioned node.
            temp = temp.next != null ? temp.next.next : null;
        }

        // Step 4: Reconnect the nodes based on the order in the ArrayList.
        // We're iterating through the array and setting the 'next' pointer for each node.
        for (int i = 0; i < arr.size() - 1; i++) {
            arr.get(i).next = arr.get(i + 1);
        }

        // Step 5: Clean up the tail. The last node's 'next' pointer must be set to null.
        arr.get(arr.size() - 1).next = null;

        // The first node in the array is the head of our newly formed list.
        return arr.get(0);
    }

    // --- Optimal Approach (Position-Based) ---

    /**
     * Optimal Approach: In-place manipulation using separate pointers for odd and even lists.
     * This is the true forward-thinking solution, achieving O(1) space!
     * * Time Complexity: O(N)
     * - A single pass over the list to separate and relink the nodes.
     * * Space Complexity: O(1)
     * - We only use a few extra pointers (odd, even, evenHead), maintaining constant space.
     * * @param head The head of the linked list.
     * @return The head of the reordered linked list.
     */
    static Node oddEvenListOptimal(Node head) {
        // Edge case: List is empty, has one node, or has two nodes (already in odd-even order).
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        // Step 1: Initialize the pointers.
        // 'odd' is the current tail of the odd list (starts at head, 1st position).
        Node odd = head;
        // 'even' is the current tail of the even list (starts at head.next, 2nd position).
        Node even = head.next;
        // 'evenHead' is the head of the even list. We need this to connect the odd list's tail later.
        Node evenHead = even;

        // Step 2: Iterate and relink. The loop continues as long as there's at least one more even node.
        while (even != null && even.next != null) {
            // A. Relink the ODD list: The next odd node is even.next.
            // (e.g., in 1->2->3->4, odd=1, even=2. odd.next becomes 3).
            odd.next = even.next;
            // Advance the odd pointer to its new tail (the node we just linked).
            odd = odd.next;

            // B. Relink the EVEN list: The next even node is odd.next (which is the node after the one we just linked).
            // (e.g., odd=3. even.next becomes odd.next, which is 4).
            even.next = odd.next;
            // Advance the even pointer to its new tail.
            even = even.next;
        }

        // Step 3: Connect the two lists. Synergize the two segments!
        // The tail of the odd list (current 'odd' pointer) points to the head of the even list (evenHead).
        odd.next = evenHead;

        // The head of the entire list remains the original head.
        return head;
    }


    // --- Utility Methods for Demonstration ---

    static Node head = null, tail = null;

    static void InsertatLast(int value) {
        Node newnode = new Node(value);
        if (head == null) {
            head = newnode;
            tail = newnode;
        } else {
            tail.next = newnode;
            tail = newnode;
        }
    }

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
     * Main method for execution and demonstration.
     */
    public static void main(String[] args) {
        System.out.println("### Linked List Odd-Even Reorder Demo (Position-Based) ###");

        // Example: 1 -> 2 -> 3 -> 4 -> 5
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);

        System.out.println("\n--- Initial List: 1 -> 2 -> 3 -> 4 -> 5 ---");

        // Optimal Test (The main event!)
        // Expected Output: 1 -> 3 -> 5 -> 2 -> 4
        Node newHeadOptimal = oddEvenListOptimal(head1);
        System.out.print("Optimal Result (O(1) Space): ");
        printList(newHeadOptimal);

        // Example 2: Even length
        Node head2 = new Node(10);
        head2.next = new Node(20);
        head2.next.next = new Node(30);
        head2.next.next.next = new Node(40);

        System.out.println("\n--- Initial List: 10 -> 20 -> 30 -> 40 ---");

        // Expected Output: 10 -> 30 -> 20 -> 40
        Node newHeadOptimal2 = oddEvenListOptimal(head2);
        System.out.print("Optimal Result (Even Length): ");
        printList(newHeadOptimal2);
    }
}