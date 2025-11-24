import java.util.List;

public class ListNode {
    int val;
    ListNode next;
    ListNode child;

    ListNode(int data1, ListNode next1, ListNode childNode) {
        val = data1;
        next = next1;
        child = childNode;
    }

    ListNode(int data1) {
        val = data1;
        next = null;
        child = null;
    }
}
// class node common to all The Files in the package