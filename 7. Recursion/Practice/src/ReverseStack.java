import java.util.Stack;
import java.util.Collections; // Used for brute-force analogy

public class ReverseStack {

    // -------------------------------------------------------------
    // 🧨 BRUTE FORCE APPROACH (Conceptual - Using built-in reverse)
    // -------------------------------------------------------------
    // Idea: Use a built-in reverse function (if available) or copy to an
    // intermediate list/array and use Collections.reverse().
    // Time Complexity: O(n)
    // Reasoning: Copying all n elements and then reversing them takes linear time.
    // Space Complexity: O(n)
    // Reasoning: Need an auxiliary data structure (List) to hold all n elements.
    public static void bruteForceReverse(Stack<Integer> st) {
        // Not the recursive solution we want, but hey, it's efficient!
        Collections.reverse(st);
    }

    // -------------------------------------------------------------
    // 🚀 OPTIMAL RECURSIVE APPROACH (The Assignment’s Requirement)
    // -------------------------------------------------------------
    // This is a two-part recursive solution:
    // 1. reverseStack: Isolates the top element and recursively reverses the rest.
    // 2. insertAtBottom: Inserts the isolated element at the bottom of the stack.
    //
    // Time Complexity: O(n²)
    // Reasoning: The `reverseStack` function is called n times. Inside it,
    // `insertAtBottom` is called once. The `insertAtBottom` function itself
    // recursively pops and pushes up to n elements. This nested structure
    // leads to an arithmetic series: n + (n-1) + ... + 1, which simplifies to O(n²).
    //
    // Space Complexity: O(n)
    // Reasoning: This is dictated by the maximum depth of the recursive call stack,
    // which goes down n levels for a stack of size n.
    // -------------------------------------------------------------

    /**
     * Helper function to insert a value at the bottom of the stack.
     * This ensures the stack remains sorted after the recursive call.
     * @param st The stack currently being manipulated.
     * @param val The value to be inserted at the very bottom.
     */
    private static void insertAtBottom(Stack<Integer> st, int val) {
        // BASE CASE: If the stack is empty, we have finally reached the bottom.
        // This is the optimal spot for 'val'. Push it and start the backtracking.
        if (st.isEmpty()) {
            st.push(val);
            // System.out.println("DEBUG: Inserted " + val + " at bottom. Stack: " + st);
            return;
        }

        // **ISOLATION**: Temporarily pop the current top element.
        // We're safeguarding it while we recursively dive deeper.
        int topVal = st.pop();
        // System.out.println("DEBUG: Popped " + topVal + " to go deeper for " + val);

        // **DIVE DEEPER**: Recursively call to find the next level down.
        insertAtBottom(st, val);

        // **BACKTRACKING**: After 'val' is successfully inserted at the bottom,
        // we push the safeguarded element ('topVal') back onto the stack.
        // This rebuilds the stack structure in reverse order of its popping.
        st.push(topVal);
        // System.out.println("DEBUG: Pushed back " + topVal + ". Stack: " + st);
    }

    /**
     * Main function to reverse the stack using recursion.
     * It uses 'insertAtBottom' to place elements in their new, reversed positions.
     * @param st The stack to be reversed in-place.
     */
    public static void reverseStack(Stack<Integer> st) {
        // BASE CASE: If the stack is empty, the reversal pipeline is complete for this branch.
        if (st.isEmpty()) return;

        // **ISOLATION**: Pop the top element. This element will be the new
        // BOTTOM of the fully reversed stack.
        int topVal = st.pop();

        // **CONQUER**: Recursively reverse the remaining N-1 elements.
        // When this returns, the rest of the stack will be reversed,
        // but 'topVal' is still outside.
        reverseStack(st);

        // **COMBINE**: Insert the isolated 'topVal' (which was the original top)
        // back into the stack, but specifically at the BOTTOM. This ensures it
        // ends up at its correct, final, reversed position.
        insertAtBottom(st, topVal);
    }


    // -------------------------------------------------------------
    // 👾 MAIN METHOD FOR DEMO
    // -------------------------------------------------------------
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4); // 4 is the top

        System.out.println("Original Stack (Top -> Bottom): " + stack); // [1, 2, 3, 4] -> 4 is top

        System.out.println("\n--- Initiating Recursive Reversal Pipeline ---\n");
        // Kickstarting the optimal recursive pipeline!
        reverseStack(stack);

        // Expected output: The original top (4) is now at the bottom, and original bottom (1) is at the top.
        System.out.println("\n--- Pipeline Complete! ---\n");
        System.out.println("Reversed Stack (Top -> Bottom): " + stack); // [4, 3, 2, 1] -> 1 is top
        //
    }
}