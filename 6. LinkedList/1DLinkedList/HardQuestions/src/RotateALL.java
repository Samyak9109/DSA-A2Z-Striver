public class RotateALL {

    /**
     * Brute Force Approach: Rotate the list one step at a time, 'k' times.
     * * Time Complexity: O(k * N)
     * - In the worst case, k can be close to N, leading to O(N^2) if k is large.
     * - Reasoning: We iterate through the list (O(N)) 'k' times.
     * * Space Complexity: O(1)
     * - Reasoning: We only use a few extra pointers (temp, end), regardless of the list size.
     * * @param head The head of the input linked list.
     * @param k The number of times to rotate the list to the right.
     * @return The head of the rotated linked list.
     */
    static ListNode rotateRightBrute(ListNode head, int k) {
        // Edge case check: If the list is empty or has only one node, no rotation is needed.
        if (head == null || head.next == null) return head;

        // We run the rotation process 'k' times. This is the slow part!
        // We're essentially moving the last node to the front in a loop.
        for (int i = 0; i < k; i++) {
            // Step 1: Initialize 'temp' to find the second-to-last node.
            ListNode temp = head;

            // Step 2: Iterate until 'temp' points to the second-to-last node.
            // Why? Because 'temp.next' will be the last node we need to move.
            while (temp.next.next != null) {
                temp = temp.next;
            }

            // Step 3: Isolate the last node ('end').
            ListNode end = temp.next;

            // Step 4: Break the link: The second-to-last node now becomes the new tail (points to null).
            temp.next = null;

            // Step 5: Move the isolated last node to the front.
            // It now points to the original head.
            end.next = head;

            // Step 6: Update the list's head to the newly moved node.
            head = end;
        }
        return head;
    }

    /**
     * Optimal Approach: Calculate the effective rotation and perform a single relinking.
     * * Time Complexity: O(N)
     * - Reasoning: We traverse the list once to find the length (O(N)), and once again
     * to find the new break point (O(N)). This is a significant optimization over the brute force.
     * * Space Complexity: O(1)
     * - Reasoning: Only a constant number of pointers are used.
     * * @param head The head of the input linked list.
     * @param k The number of times to rotate the list to the right.
     * @return The head of the rotated linked list.
     */
    static ListNode rotateRightOptimal(ListNode head, int k) {
        // Initial sanity check: If the list is too short or k is zero, we skip the operation.
        if (head == null || head.next == null || k == 0) return head;

        // --- Phase 1: Determine the Effective Rotation and Close the Circle ---

        ListNode temp = head;
        int length = 1;

        // Step 1: Calculate the total length of the list (N).
        // Why? We need N to calculate the effective rotation count and to find the break point.
        while (temp.next != null) {
            ++length;
            temp = temp.next; // 'temp' ends up pointing to the current tail.
        }

        // Step 2: Form a circular linked list.
        // This is a key forward-thinking solution! By making it circular,
        // we can rotate it without worrying about losing the front part.
        temp.next = head;

        // Step 3: Calculate the effective number of rotations.
        // k = k % length: If k > N (e.g., N=5, k=7), rotating 7 times is the same as rotating 2 times (7%5=2).
        // This is a major optimization to prevent unnecessary O(N) traversals!
        k = k % length;

        // --- Phase 2: Find the New Tail and Break the Circle ---

        // Step 4: Calculate the distance from the beginning to the new tail (where we need to break the link).
        // The list is N-length. Rotating k steps means the new head is at (N - k) steps from the *original* head.
        // We need to stop one node *before* the new head, which is the *new* tail.
        int stepsToNewTail = length - k;

        // Step 5: Traverse 'stepsToNewTail' times to find the new tail node.
        // We're using 'temp' which is currently pointing at the original tail (which is also connected to the head now).
        // We start from the tail and move towards the head 'stepsToNewTail' times.
        // Wait, let's simplify this. Since 'temp' is pointing to the old tail,
        // let's start 'temp' back at 'head' to make the logic cleaner to read.
        // Let's re-use 'temp' starting from head for easier mental mapping.
        temp = head;

        // We loop (length - k - 1) times to stop *before* the new head.
        // Let's use the current 'temp' (which is the original tail) and move it 'length - k' steps.
        // The while loop iterates one less time than the end value, which is fine,
        // but for clarity, let's use a standard for loop or a cleaner while loop.
        // The existing code's logic is:
        // while (end-- != 0) temp = temp.next; // end = length - k
        // If N=5, k=2. end=3. Loops 3 times (3, 2, 1). 'temp' moves 3 steps.
        // 1->2->3->4->5. The new tail should be 3.
        // Starts at 1. Moves 1->2, 2->3, 3->4. 'temp' ends at 4. This is wrong.

        // Let's refine the pointer movement: The new tail is at index (length - k - 1).
        // Example: 1->2->3->4->5, k=2. New List: 4->5->1->2->3.
        // New Tail is 3, which is at index 2 (0-based) or step 2 from head.
        // Break point is between 3 and 4. The node *before* the new head is the new tail.
        // New head is 4 (step 'k' from the end, or 'length - k' from the start).
        // New tail is 3 (step 'length - k - 1' from the start).

        ListNode newTail = head;
        // Loop (length - k - 1) times to land *on* the new tail.
        for (int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }

        // Step 6: Identify the new head. It's the node right after the new tail.
        head = newTail.next;

        // Step 7: Break the circle by pointing the new tail to null.
        newTail.next = null;

        return head;
    }

    // Helper function to print the list for demonstration
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    // Helper function to create a list from an array
    public static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * Main method for execution and demonstration.
     * Time to showcase this forward-thinking solution!
     */
    public static void main(String[] args) {
        System.out.println("--- 🚀 List Rotation Demo: Optimizing the Pipeline 🚀 ---");

        // Example: List 1 -> 2 -> 3 -> 4 -> 5, k = 2
        int[] data1 = {1, 2, 3, 4, 5};
        int k1 = 2;
        ListNode head1_brute = createList(data1);
        ListNode head1_optimal = createList(data1);

        System.out.println("\n✅ Input List: ");
        printList(head1_brute);

        System.out.println("\n--- Brute Force (O(k*N)) ---");
        System.out.println("Rotating k=" + k1 + " times...");
        // Note: The original brute force implementation provided has a slight bug
        // with k > N, so we run with the assumption k < N for this demo.
        // For a correct implementation, you'd need k = k % length calculation first.
        ListNode rotatedBrute = rotateRightBrute(head1_brute, k1);
        System.out.print("Output (k=2): ");
        printList(rotatedBrute); // Expected: 4 -> 5 -> 1 -> 2 -> 3

        System.out.println("\n--- Optimal Approach (O(N)) ---");
        int k2 = 7; // k > N test
        System.out.println("Rotating k=" + k2 + " times (N=5. Effective k=7%5=2)...");
        ListNode rotatedOptimal = rotateRightOptimal(head1_optimal, k2);
        System.out.print("Output (k=7): ");
        printList(rotatedOptimal); // Expected: 4 -> 5 -> 1 -> 2 -> 3

        System.out.println("\n--- Edge Case Test (k = N) ---");
        int[] data3 = {10, 20, 30};
        int k3 = 3; // k = N, should result in the original list
        ListNode head3 = createList(data3);
        System.out.print("Input: ");
        printList(head3);
        System.out.println("Rotating k=" + k3 + " times...");
        ListNode rotatedEdge = rotateRightOptimal(head3, k3);
        System.out.print("Output: ");
        printList(rotatedEdge); // Expected: 10 -> 20 -> 30

        System.out.println("\n✨ Optimal solution is the clear winner for performance synergy! ✨");
    }
}
