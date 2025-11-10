public class MiddleOfLL {
    static Node middleOfLLBrute (Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        int cnt = 0;
        Node temp = head;
        while (temp != null) {
            cnt++;
            temp = temp.next;
        }
        int mid = cnt / 2 + 1;
        temp = head;

        while (temp != null) {
            mid = mid - 1;

            // Check if the middle
            // position is reached.
            if (mid == 0){
                // break out of the loop
                // to return temp
                break;
            }
            // Move temp ahead
            temp = temp.next;
        }

        // Return the middle node.
        return temp;
    }

    static Node middleOfLLOptimal (Node head) {
        //using Tortoise and hare approach
        if (head == null || head.next == null) {
            return head;
        }

        Node slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
