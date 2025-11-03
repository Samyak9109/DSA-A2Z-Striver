// File: InsertAndDeleteLL.java
// ✅ Works with shared Node class (from another package)

/*
 * 🚀 Solution class (package-private)
 * Handles Linked List insertions and deletions
 */
class Solution {

    /**
     * 🧩 Insert at Head
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public Node insertAtHead(Node head, int val) {
        return new Node(val, head); // just plug the new node at the front
    }

    /**
     * 🧩 Insert at Tail
     * Time Complexity: O(n) → must traverse till end
     * Space Complexity: O(1)
     */
    public Node insertAtTail(Node head, int val) {
        if (head == null) return new Node(val);
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(val);
        return head;
    }

    /**
     * 🧩 Insert at Kth Position (1-indexed)
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Edge Cases:
     * - Insert in empty list at pos > 1 → ❌ invalid
     * - Insert at head → O(1)
     * - Insert beyond list size → graceful warning
     */
    public Node insertAtKth(Node head, int val, int k) {
        // 🧠 Case 1: Empty list
        if (head == null) {
            if (k == 1) return new Node(val);
            System.out.println("⚠️ Invalid position! Can't insert beyond size in empty list.");
            return null;
        }

        // 🧠 Case 2: Insert at head (position 1)
        if (k == 1) {
            return new Node(val, head);
        }

        // 🧠 Case 3: Traverse till (k-1)th node
        Node temp = head;
        int cnt = 1;
        while (temp != null && cnt < k - 1) {
            temp = temp.next;
            cnt++;
        }

        // 🧠 Case 4: If k is larger than list size
        if (temp == null) {
            System.out.println("⚠️ Position " + k + " out of bounds! Insertion failed.");
            return head;
        }

        // ✅ Case 5: Normal insertion in middle or end
        Node newNode = new Node(val, temp.next);
        temp.next = newNode;

        return head;
    }

    /**
     * 🧩 Delete Head
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public Node deleteHead(Node head) {
        if (head == null) {
            System.out.println("⚠️ List is already empty!");
            return null;
        }
        return head.next;
    }

    /**
     * 🧩 Delete Tail
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public Node deleteTail(Node head) {
        if (head == null || head.next == null) return null;
        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
        return head;
    }

    /**
     * 🧩 Delete Kth Node (1-indexed)
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public Node deleteKth(Node head, int k) {
        if (head == null) return null;

        // Deleting the head
        if (k == 1) return head.next;

        Node temp = head;
        int count = 1;

        // Reach (k-1)th node
        while (temp != null && count < k - 1) {
            temp = temp.next;
            count++;
        }

        // Out of range
        if (temp == null || temp.next == null) {
            System.out.println("⚠️ Position " + k + " out of bounds! Deletion failed.");
            return head;
        }

        // Skip the kth node
        temp.next = temp.next.next;
        return head;
    }

    /**
     * 🧩 Print List
     * Time Complexity: O(n)
     */
    public void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}

/*
 * 🧩 Driver Class (public - matches filename)
 * Runs insert/delete demos
 */
public class InsertAndDeleteLL {
    public static void main(String[] args) {
        Solution sol = new Solution();

        Node head = new Node(2);
        head.next = new Node(3);

        System.out.print("Original List: ");
        sol.printList(head);

        head = sol.insertAtHead(head, 1);
        System.out.print("After Insertion at Head: ");
        sol.printList(head);

        head = sol.insertAtTail(head, 5);
        System.out.print("After Insertion at Tail: ");
        sol.printList(head);

        head = sol.insertAtKth(head, 10, 3);
        System.out.print("After Insertion at 3rd Position: ");
        sol.printList(head);

        head = sol.deleteHead(head);
        System.out.print("After Deletion of Head: ");
        sol.printList(head);

        head = sol.deleteTail(head);
        System.out.print("After Deletion of Tail: ");
        sol.printList(head);

        head = sol.deleteKth(head, 2);
        System.out.print("After Deleting 2nd Node: ");
        sol.printList(head);
    }
}
