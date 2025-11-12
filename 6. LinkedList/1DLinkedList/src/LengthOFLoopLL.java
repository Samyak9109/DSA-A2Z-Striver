// Assuming Node class is available from a separate file:
// class Node {
//     int data;
//     Node next;
//     Node(int data) { this.data = data; this.next = null; }
// }

import java.util.HashMap;

public class LengthOFLoopLL {

    /**
     * 🔥 Brute Force Approach: Using a HashMap to Store Index (Timer) Values
     * * Time Complexity: O(N) 🕰️
     * - We traverse the list once.
     * - HashMap operations (containsKey and get/put) take O(1) time on average.
     * - Reasoning: The total work is proportional to the number of nodes (N).
     * Space Complexity: O(N) 💾
     * - We store every node reference and its index (timer value) in the HashMap.
     * - Reasoning: In the worst case (no loop), the map stores N entries.
     * * @param head The head of the linked list.
     * @return The length of the loop, or 0 if no loop exists.
     */
    public int lengthOfLoopBrute(Node head) {
        // Step 1: Initialize a HashMap to store (Node -> Index/Timer).
        // The index helps us calculate the length: Length = (Current Index) - (Stored Index)
        HashMap<Node, Integer> visitedNodes = new HashMap<>();
        Node temp = head;
        int timer = 0; // Acts as the index/timer, starting at 0 for the head.

        // Step 2: Traverse the list, checking for visited nodes.
        while (temp != null) {

            // Check: Have we seen this specific node object before?
            if (visitedNodes.containsKey(temp)) {

                // Found the loop! Now calculate the length.
                // The length is the difference between the *current* index (timer) and the 
                // index where we *first* saw the node (stored value).
                int loopLength = timer - visitedNodes.get(temp);

                // Mission accomplished! Return the length.
                return loopLength;
            }

            // Log it: Store the current node and its unique index.
            visitedNodes.put(temp, timer);

            // Move the pointer and increment the index/timer.
            temp = temp.next;
            timer++;
        }

        // Step 3: If we hit 'null', there is no cycle.
        return 0;
    }

    // ---

    /**
     * ⚡ Helper Function: Counts the length of the loop from a known point inside the cycle.
     * * Time Complexity: O(L) 🕰️ where L is the length of the loop.
     * - We traverse the loop once, starting from the meeting point.
     * Space Complexity: O(1) 💾
     * - We only use one extra temporary pointer and an integer counter.
     * * @param meetingPoint Any node inside the cycle (usually the meeting point of slow and fast pointers).
     * @return The length of the loop.
     */
    private int countLoopLength(Node meetingPoint) {
        // Start one pointer (temp) from the meeting point.
        Node temp = meetingPoint;
        int length = 1; // Start count at 1, since we're already at the meetingPoint.

        // Traverse the loop until 'temp' comes back to the 'meetingPoint'.
        // This measures the circumference of the cycle.
        while (temp.next != meetingPoint) {
            temp = temp.next;
            length++;
        }

        // This is the full length (circumference) of the loop!
        return length;
    }

    /**
     * ⚡ Optimal Approach: Floyd's Algorithm (Tortoise and Hare)
     * * Time Complexity: O(N) 🕰️
     * - Phase 1 (Detection): O(N).
     * - Phase 2 (Counting): O(L) where L < N.
     * - Reasoning: Total work is linear with the list size, making it O(N).
     * Space Complexity: O(1) 💾
     * - We only use two extra pointers (slow and fast) and a few local variables.
     * - Reasoning: This is the forward-thinking solution for space efficiency!
     * * @param head The head of the linked list.
     * @return The length of the loop, or 0 if no loop exists.
     */
    public int lengthOfLoopOptimal(Node head) {
        // Step 1: Initialize the 'slow' and 'fast' pointers.
        Node slow = head;
        Node fast = head;

        // Phase 1: Detect the loop.
        while (fast != null && fast.next != null) {
            slow = slow.next; // Tortoise: 1 step
            fast = fast.next.next; // Hare: 2 steps

            // Check for meeting point:
            if (slow == fast) {
                // Loop detected! Immediately call the helper function to calculate the length.
                // We pass the 'meetingPoint' (which is 'slow' or 'fast').
                return countLoopLength(slow);
            }
        }

        // Step 2: If the loop finishes, no cycle was found.
        return 0;
    }

    /**
     * Main method to test and demonstrate both approaches.
     */
    public static void main(String[] args) {
        LengthOFLoopLL solver = new LengthOFLoopLL();

        // --- Test Case 1: List with a Cycle of Length 3 ---
        // 1 -> 2 -> 3(START) -> 4 -> 5 -> (points back to 3)
        // Loop is 3 -> 4 -> 5 (Length = 3)
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n3; // CREATE THE CYCLE

        System.out.println("--- Test Case: Cyclic List (Length 3) ---");
        System.out.println("Brute Force (HashMap) Loop Length: " + solver.lengthOfLoopBrute(n1)); // Expected: 3
        System.out.println("Optimal (Fast/Slow Pointer) Loop Length: " + solver.lengthOfLoopOptimal(n1)); // Expected: 3

        System.out.println("\n-------------------------------------------------");

        // --- Test Case 2: List without a Cycle ---
        // 10 -> 20 -> 30 -> 40 -> null
        Node a1 = new Node(10);
        Node a2 = new Node(20);
        Node a3 = new Node(30);
        Node a4 = new Node(40);

        a1.next = a2;
        a2.next = a3;
        a3.next = a4;

        System.out.println("--- Test Case: Acyclic List ---");
        System.out.println("Brute Force (HashMap) Loop Length: " + solver.lengthOfLoopBrute(a1)); // Expected: 0
        System.out.println("Optimal (Fast/Slow Pointer) Loop Length: " + solver.lengthOfLoopOptimal(a1)); // Expected: 0
    }
}
