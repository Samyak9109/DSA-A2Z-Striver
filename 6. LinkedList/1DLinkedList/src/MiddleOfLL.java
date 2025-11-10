class Node {
    int data;
    Node next;
    Node(int data) { this.data = data; }
}

public class MiddleOfLL {

    // 🧪 BRUTE FORCE APPROACH
    // Step 1: Count nodes (O(n))
    // Step 2: Walk again to middle (O(n))
    // Time Complexity: O(n) + O(n) = O(n)
    // Space Complexity: O(1)
    static Node middleBrute(Node head) {
        if (head == null || head.next == null) return head;

        // Count nodes
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        // Middle position (0-based index = count/2)
        int steps = count / 2;

        // Walk again to reach the middle
        temp = head;
        while (steps > 0) {
            temp = temp.next;
            steps--;
        }
        return temp;
    }

    // 🚀 OPTIMAL APPROACH (Tortoise & Hare)
    // Slow moves 1 step, Fast moves 2 steps
    // When fast hits end, slow is at the middle
    // Time: O(n)
    // Space: O(1)
    static Node middleOptimal(Node head) {
        if (head == null || head.next == null) return head;

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;        // move slow 1 step
            fast = fast.next.next;   // move fast 2 steps
        }
        return slow; // Middle node
    }

    // Utility: Create LL from array
    static Node arrayToLL(int[] arr) {
        if (arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new Node(arr[i]);
            temp = temp.next;
        }
        return head;
    }

    // Utility: Print LL
    static void printLL(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // 🧵 main() - Demo time
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        Node head = arrayToLL(arr);

        System.out.print("Linked List: ");
        printLL(head);

        System.out.println("Middle (Brute): " + middleBrute(head).data);
        System.out.println("Middle (Optimal): " + middleOptimal(head).data);
    }
}
