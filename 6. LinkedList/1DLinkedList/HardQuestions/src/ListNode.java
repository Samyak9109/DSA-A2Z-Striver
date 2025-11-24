import java.util.List;

public class ListNode {
    int val;
    ListNode next;
    ListNode child;
    ListNode random;

    ListNode(int data1, ListNode next1, ListNode childNode, ListNode randomNode) {
        this.val = data1;
        this.next = next1;
        this.child = childNode;
        this.random = randomNode;
    }

    ListNode(int data1) {
        this.val = data1;
        this.next = null;
        this.child = null;
        this.random = null;
    }
}
// class node common to all The Files in the package