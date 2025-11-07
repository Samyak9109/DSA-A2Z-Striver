public class Node {
    int data;
    Node next;
    Node back;

    Node(int data1, Node next1, Node prev1) {
        data = data1;
        next = next1;
        back = prev1;
    }

    Node(int data1) {
        data = data1;
        next = null;
        back = null;
    }
}
