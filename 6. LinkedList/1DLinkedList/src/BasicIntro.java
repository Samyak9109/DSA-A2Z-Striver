/*
 * 🧠 LINKED LIST BASICS — THE OG DYNAMIC DATA STRUCTURE
 * ------------------------------------------------------
 * - Unlike arrays, linked lists don't store data in contiguous memory.
 * - Each element (called a NODE) has:
 *     1️⃣ data → stores the value
 *     2️⃣ next → reference to the next node
 * - Dynamic size — can grow/shrink at runtime 🧩
 * - Insertions & deletions: O(1) (if we already have the reference)
 * - Traversal: O(n)
 * - Commonly used in Stack & Queue implementations.
 * - In Java, references act like pointers for us (no manual pointer magic needed 🪄).
 */

// 🧱 Node class — Self-referential structure that powers the linked list
class Node {
    int data;    // stores data
    Node next;   // reference to next node

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

/*
 * 🚀 Converting an Array → Linked List
 * -----------------------------------
 * Steps:
 * 1️⃣ Create the head node using the first array element.
 * 2️⃣ Maintain a tail pointer to track the end.
 * 3️⃣ For each element, create a new node and link it to the tail.
 */
class NodeArrayToLinkedList {
    /**
     * Time Complexity: O(n)
     *  - We traverse the array once → linear time.
     * Space Complexity: O(n)
     *  - Each array element gets converted to a node.
     */
    public static Node arrayToLinkedList(int[] arr) {
        if (arr.length == 0) {
            return null; // empty array → empty list
        }

        Node head = new Node(arr[0]); // Step 1: create head node
        Node tail = head;             // Step 2: initialize tail

        // Step 3: iterate through remaining elements
        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            tail.next = newNode; // link current tail → new node
            tail = newNode;      // move tail to new node
        }

        return head; // Return the head (entry point to list)
    }
}

/*
 * 🔁 Traversing a Linked List
 * ---------------------------
 * Start from head, move node → node until null.
 */
class LinkedListTraversal {
    /**
     * Time Complexity: O(n)
     *  - Each node is visited once.
     * Space Complexity: O(1)
     *  - Only uses a few pointers.
     */
    public static void traverseLinkedList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next; // hop to next node
        }
        System.out.println();
    }

    /**
     * 📏 Method to get the length (size) of the linked list
     *
     * Time Complexity: O(n)
     *  - We visit each node once to count.
     * Space Complexity: O(1)
     *  - Uses only one pointer and one counter variable.
     */
    public static int getLinkedListLength(Node head) {
        int count = 0;          // initialize counter
        Node current = head;    // start from head

        while (current != null) {
            count++;            // increment counter for each node
            current = current.next; // move ahead
        }

        return count; // total nodes in the list
    }
}

/*
 * 🧩 Main Driver Class
 * --------------------
 * Demonstrates:
 * - Array → Linked List conversion
 * - Linked List Traversal
 * - Linked List Length Calculation
 */
public class BasicIntro {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};

        // Creating a single node (for demo)
        Node singleNode = new Node(arr[0]);
        System.out.println("Single Node Data: " + singleNode.data);

        // Convert array → linked list
        Node head = NodeArrayToLinkedList.arrayToLinkedList(arr);
        System.out.println("\nTraversing Linked List (Method 1):");

        // Method 1: Inline traversal (classic)
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }

        // Method 2: Using helper method
        System.out.println("\n\nTraversal using helper method:");
        LinkedListTraversal.traverseLinkedList(head);

        // ⚡ Length of the linked list
        int length = LinkedListTraversal.getLinkedListLength(head);
        System.out.println("\nLength of Linked List: " + length);
    }
}
