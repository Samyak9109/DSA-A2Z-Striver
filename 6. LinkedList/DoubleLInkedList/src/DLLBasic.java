
public class DLLBasic {

    // 🔹 Convert array to Doubly Linked List (DLL)
    static Node arrayToDLL(int[] arr) {
        // Time Complexity: O(n) → each element is visited once to create nodes
        // Space Complexity: O(1) extra → only pointers used, nodes count as input storage

        if (arr.length == 0) return null; // empty array → return null

        Node head = new Node(arr[0]); // first element becomes head
        Node prev = head;             // prev keeps track of last node added

        // iterate over array from 2nd element
        for (int i = 1; i < arr.length; i++) {
            Node temp = new Node(arr[i], null, prev); // new node points back to prev
            prev.next = temp;                         // previous node points forward to temp
            prev = temp;                              // move prev forward
        }

        return head; // return head of DLL
    }

    // 🔹 Delete head node
    static Node deleteHeadDLL(Node head) {
        // Time Complexity: O(1) → direct access to head
        // Space Complexity: O(1) → no extra memory used

        if (head == null) return null; // empty DLL
        if (head.next == null) return null; // single-node DLL → deletion results in empty DLL

        Node newHead = head.next; // new head is next node
        newHead.back = null;      // detach old head by removing backward link
        head.next = null;         // clean old head forward link
        return newHead;           // return updated head
    }

    // 🔹 Delete tail node
    static Node deleteTailDLL(Node head) {
        // Time Complexity: O(n) → traverse to last node
        // Space Complexity: O(1) → no extra memory

        if (head == null || head.next == null) return null; // empty or single-node DLL

        Node tail = head;
        while (tail.next != null) tail = tail.next; // traverse to last node

        Node prev = tail.back; // node before tail
        prev.next = null;      // detach tail from DLL
        tail.back = null;      // clean tail back pointer
        return head;           // head remains same
    }

    // 🔹 Delete K-th node (1-based index)
    static Node deleteKthDLL(Node head, int k) {
        // Time Complexity: O(k) → traverse up to K-th node
        // Space Complexity: O(1) → in-place deletion

        if (head == null || k <= 0) return head; // invalid input

        Node temp = head;
        int cnt = 1;

        // traverse to K-th node
        while (temp != null && cnt < k) {
            temp = temp.next;
            cnt++;
        }

        if (temp == null) return head; // k > length → no deletion

        Node prev = temp.back;  // node before K-th
        Node front = temp.next; // node after K-th

        // adjust pointers to remove temp
        if (prev != null) prev.next = front;
        if (front != null) front.back = prev;

        if (prev == null) head = front; // if head was deleted

        temp.next = null;
        temp.back = null; // detach fully
        return head;
    }

    // 🔹 Delete first node with given value
    static Node deleteNode(Node head, int k) {
        // Time Complexity: O(n) → traverse until value is found
        // Space Complexity: O(1) → in-place deletion

        if (head == null) return null; // empty DLL

        Node temp = head;
        while (temp != null) {
            if (temp.data == k) { // value found
                Node prev = temp.back;
                Node front = temp.next;

                if (prev != null) prev.next = front;
                else head = front; // deleting head

                if (front != null) front.back = prev;

                temp.next = null;
                temp.back = null; // clean removed node
                return head;      // return updated head
            }
            temp = temp.next;
        }

        return head; // value not found → no deletion
    }

    // 🔹 Print DLL forward
    static void printDLL(Node head) {
        System.out.print("DLL: ");
        while (head != null) {
            System.out.print(head.data + " <-> ");
            head = head.next;
        }
        System.out.println("null");
    }

    // 🔹 Demo main method for full DLL operations
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};

        // create DLL from array
        Node head = arrayToDLL(arr);
        System.out.println("✅ Initial DLL:");
        printDLL(head);

        // delete head
        System.out.println("\n🔹 Delete head:");
        head = deleteHeadDLL(head);
        printDLL(head);

        // delete tail
        System.out.println("\n🔹 Delete tail:");
        head = deleteTailDLL(head);
        printDLL(head);

        // delete K-th node
        System.out.println("\n🔹 Delete 2nd node (K-th):");
        head = deleteKthDLL(head, 2);
        printDLL(head);

        // delete node by value
        System.out.println("\n🔹 Delete node with value 20:");
        head = deleteNode(head, 20);
        printDLL(head);
    }
}
