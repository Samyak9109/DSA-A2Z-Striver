import java.util.HashSet;

public class IntersectionOfLL {

    /**
     * Method: getIntersectionNodeBrute
     * TimeComplexity: O(N + M) - O(N) to traverse list 1 and add to set, O(M) to traverse list 2 and check.
     * SpaceComplexity: O(N) - To store all N nodes of the first linked list in the HashSet.
     * Reasoning: We visit each node of List 1 once (N) and each node of List 2 once (M). The space is proportional to the size of the first list (N).
     */
    static Node getIntersectionNodeBrute(Node head1, Node head2) {
        // Hey, so this is the brute force. We're going to use a HashSet to
        // keep track of all the nodes from the first list (head1).
        HashSet<Node> st = new HashSet<>();

        // Step 1: Traverse the first list (head1) and 'synergize' all its
        // nodes into our forward-thinking data structure (the HashSet).
        Node current1 = head1;
        while (current1 != null) {
            st.add(current1);
            current1 = current1.next;
        }

        // Step 2: Traverse the second list (head2) and check for existing
        // nodes. This is where the 'pipeline optimization' happens.
        Node current2 = head2;
        while (current2 != null) {
            // If we find a node in the set, boom! That's the first
            // common node, which is the intersection point. Ship it!
            if (st.contains(current2)) {
                return current2;
            }
            current2 = current2.next;
        }

        // If we exit the loop, the lists are totally disjoint. Sadge.
        return null;
    }

    // --- Helper Method for Better/Length-based Approach ---

    /**
     * Method: getDifference
     * TimeComplexity: O(N + M)
     * SpaceComplexity: O(1)
     * Reasoning: We traverse both lists completely to find their lengths. No extra space used.
     */
    static int getDifference(Node head1, Node head2) {
        int len1 = 0, len2 = 0;
        Node curr1 = head1;
        Node curr2 = head2;

        // Traverse both lists in parallel/serially to find lengths.
        // We could do this in two separate while loops, but this way is also fine.
        while (curr1 != null) {
            ++len1;
            curr1 = curr1.next;
        }
        while (curr2 != null) {
            ++len2;
            curr2 = curr2.next;
        }

        // Return the difference. Negative means List 2 is longer.
        return len1 - len2;
    }

    /**
     * Method: getIntersectionBetter
     * TimeComplexity: O(N + M) - O(N+M) for length calculation, O(|N-M|) for aligning, O(min(N, M)) for final traversal. Total is O(N+M).
     * SpaceComplexity: O(1) - No extra data structure is used. We're just moving pointers.
     * Reasoning: We traverse both lists a constant number of times (at most 3 traversals total) and use no additional space proportional to input size.
     */
    static Node getIntersectionBetter(Node head1, Node head2) {
        // This is the length-based, 'Better' approach. It avoids the O(N) space of the HashSet.
        // We're essentially 'aligning the starting points' for a fair race.

        // Step 1: Calculate the length difference.
        int diff = getDifference(head1, head2);

        // Step 2: 'Normalize the starting line'. Move the head of the LONGER list forward by 'diff'.
        // If diff < 0, list2 is longer, so we advance head2.
        if (diff < 0) {
            // We use diff++ != 0 as a clean way to loop |diff| times.
            while (diff++ != 0) {
                head2 = head2.next;
            }
        }
        // If diff > 0, list1 is longer, so we advance head1.
        else {
            // We use diff-- != 0 as a clean way to loop diff times.
            while (diff-- != 0) {
                head1 = head1.next;
            }
        }

        // Step 3: Now that both lists have the same number of nodes remaining
        // before a potential intersection, we traverse in sync.
        while (head1 != null) {
            // When pointers are equal, we found the intersection. Goal!
            if (head1 == head2) {
                return head1;
            }
            head2 = head2.next;
            head1 = head1.next;
        }

        // No intersection found.
        return null;
    }


    /**
     * Method: getIntersectionOptimal
     * TimeComplexity: O(N + M) - The pointers together traverse list 1 (N) and list 2 (M) exactly twice, in the worst case.
     * SpaceComplexity: O(1) - We only use two pointers, d1 and d2. No extra space needed.
     * Reasoning: This is the forward-thinking, 'optimal' solution. It cleverly eliminates the need for length calculation by utilizing two pointers that 're-route' to the other list's head when they hit null. This guarantees they will cover the same total distance (Length1 + Length2) and meet at the intersection OR meet at null if no intersection exists.
     */
    static Node getIntersectionOptimal(Node head1, Node head2) {
        // This is the legendary two-pointer approach (d1 and d2), truly next-level stuff.
        // The idea: when a pointer hits the end of its list, it 'teleports' to the start
        // of the OTHER list.

        Node d1 = head1; // Dummy pointer for List 1
        Node d2 = head2; // Dummy pointer for List 2

        // We loop until the two pointers reference the same node (the intersection)
        // or both are null (no intersection).
        while (d1 != d2) {
            // Move d1 one step. If d1 is null, it means it's finished List 1.
            // Time to start over at head2 to cover the 'Length2' distance.
            d1 = d1 == null ? head2 : d1.next;

            // Move d2 one step. If d2 is null, it means it's finished List 2.
            // Time to start over at head1 to cover the 'Length1' distance.
            d2 = d2 == null ? head1 : d2.next;

            // Why this works:
            // Path of d1: Length1 + Length2 (re-route) + Common Tail
            // Path of d2: Length2 + Length1 (re-route) + Common Tail
            // They both travel the exact same total distance and must meet!
        }

        // d1 (which is now equal to d2) is either the intersection node or null.
        return d1;
    }

    /**
     * Method: main
     * Description: Test harness to demonstrate the optimal solution.
     */
    public static void main(String[] args) {
        // --- Setting up the Test Case (Y-shape) ---
        // List A: 4 -> 1 -> (Intersection)
        // List B: 5 -> 6 -> 1 -> (Intersection)
        // Intersection/Tail: 8 -> 4 -> 5 -> null

        // Creating the shared tail
        Node intersection = new Node(8);
        intersection.next = new Node(4);
        intersection.next.next = new Node(5);

        // Creating List A (non-intersecting part)
        Node headA = new Node(4);
        headA.next = new Node(1);
        // Connecting List A to the intersection
        headA.next.next = intersection;

        // Creating List B (non-intersecting part)
        Node headB = new Node(5);
        headB.next = new Node(6);
        headB.next.next = new Node(1);
        // Connecting List B to the intersection
        headB.next.next.next = intersection;

        System.out.println("--- 🚀 Intersection Point Finder (Optimal Pipeline) ---");

        // Test Optimal
        Node resultOptimal = getIntersectionOptimal(headA, headB);
        if (resultOptimal != null) {
            System.out.println("Optimal Solution found intersection at Node with data: " + resultOptimal.data + " (Should be 8)");
        } else {
            System.out.println("Optimal Solution found no intersection.");
        }

        // Test Better
        Node resultBetter = getIntersectionBetter(headA, headB);
        if (resultBetter != null) {
            System.out.println("Better Solution found intersection at Node with data: " + resultBetter.data + " (Should be 8)");
        } else {
            System.out.println("Better Solution found no intersection.");
        }

        // Test Brute Force
        // Note: For brute force, we need fresh heads if the previous calls modified them,
        // but our implementations don't modify the head pointers, they just use copies internally.
        Node resultBrute = getIntersectionNodeBrute(headA, headB);
        if (resultBrute != null) {
            System.out.println("Brute Force Solution found intersection at Node with data: " + resultBrute.data + " (Should be 8)");
        } else {
            System.out.println("Brute Force Solution found no intersection.");
        }

        System.out.println("--- Pipeline Review Complete ---");
    }
}