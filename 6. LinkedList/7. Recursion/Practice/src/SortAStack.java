import java.util.Stack;
import java.util.Arrays;
import java.util.List;

public class SortAStack {

    // -------------------------------------------------------------
    // 🧨 BRUTE FORCE APPROACH
    // -------------------------------------------------------------
    // Idea: Copy stack → put elements in an array → sort array → rebuild stack
    // Time Complexity: O(n log n)
    // Reasoning: The built-in Arrays.sort() method is typically implemented using a
    // variation of MergeSort or QuickSort, leading to O(n log n) complexity.
    // Space Complexity: O(n)
    // Reasoning: We need an auxiliary array of size n to hold all stack elements.
    // Note: Not allowed per typical assignment constraints (avoiding loops/sort),
    // but included for a complete "optimization pipeline" analysis.
    public static Stack<Integer> bruteForce(Stack<Integer> st) {
        // Yoink all elements into an ArrayList first (using a list to easily convert to array)
        List<Integer> list = Arrays.asList(st.toArray(new Integer[0]));
        int n = list.size();

        // Convert List to array for efficient sorting
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = list.get(i);

        // **Optimization Point**: Use built-in sort for O(n log n) efficiency
        java.util.Arrays.sort(arr); // Sorts in ascending order (smallest at index 0)

        // **Rebuilding the Stack**: Push back in DESCENDING order to get the largest
        // element at the TOP (as required by the recursive solution's goal)
        Stack<Integer> res = new Stack<>();
        for (int i = 0; i < n; i++) {
            res.push(arr[i]);
        }

        // The result stack is now sorted Descending (Largest at Top)
        return res;
    }

    // -------------------------------------------------------------
    // ⚡ BETTER APPROACH (Auxiliary Stack - Simulating Iterative Insertion Sort)
    // -------------------------------------------------------------
    // Idea: Use an auxiliary stack and simulate insertion sort.
    // Time Complexity: O(n²)
    // Reasoning: In the worst-case (reverse sorted stack), for each of the n
    // elements we pop from 'st', we may have to pop/push up to n elements
    // between 'aux' and 'st' to find the correct sorted position. This is
    // an O(n) operation nested inside an O(n) loop: n * n = O(n²).
    // Space Complexity: O(n)
    // Reasoning: We use an auxiliary stack of size n to hold the sorted elements.
    public static Stack<Integer> better(Stack<Integer> st) {
        // 'aux' will be the sorted stack (descending: largest at top)
        Stack<Integer> aux = new Stack<>();

        // Loop through every element in the original stack 'st'
        while (!st.isEmpty()) {
            // 1. Pop the current element to be inserted
            int temp = st.pop();

            // 2. Move elements from 'aux' back to 'st' that are *smaller* than 'temp'
            // We want 'aux' to be sorted descending (largest at top).
            // So, if aux.peek() < temp, it means 'temp' is bigger and must go deeper
            // in 'aux' than aux.peek(), so we temporarily move aux.peek() back to 'st'.
            while (!aux.isEmpty() && aux.peek() < temp) {
                st.push(aux.pop());
            }

            // 3. Insert 'temp' into its correct sorted position in 'aux'
            aux.push(temp);
        }

        // 'aux' is the fully sorted stack (Largest at Top)
        return aux;
    }

    // -------------------------------------------------------------
    // 🚀 OPTIMAL RECURSIVE APPROACH (The assignment’s requirement)
    // -------------------------------------------------------------
    // Goal: Sort stack DESCENDING → largest at top
    // Time Complexity: O(n²)
    // Reasoning: This is a recursive implementation of Insertion Sort.
    // The `sortStack` function runs n times (once for each element).
    // The `insertInSortedForm` function, in the worst case (reverse sorted),
    // performs an O(n) operation by popping and pushing elements down/up the
    // recursion stack. O(n) work inside an O(n) call structure gives O(n²).
    //
    // Space Complexity: O(n)
    // Reasoning: This is due to the depth of the recursive call stack. For a stack
    // of size n, the recursion depth is n.
    // -------------------------------------------------------------

    /**
     * This is the entry point that recursively sorts the entire stack.
     * It's the "Divide and Conquer" step (Isolation/Recursion).
     * @param st The stack to be sorted.
     */
    public static void sortStack(Stack<Integer> st) {
        // BASE CASE: If the stack is empty, there's nothing left to sort, so we bounce.
        if (st.isEmpty()) return;

        // **ISOLATION**: Synergistically pop the top value and keep it safe.
        // This is the element we'll insert later.
        int top = st.pop();

        // **CONQUER**: Recursively sort the remaining N-1 elements in the stack.
        // When this call returns, the stack will be sorted except for 'top'.
        sortStack(st);

        // **COMBINE**: Insert this value back into the sorted stack at its
        // correct sorted position. This ensures the sorted property is maintained.
        insertInSortedForm(st, top);
    }

    /**
     * Inserts a value into an already sorted stack, maintaining the sorted (DESCENDING) property.
     * This is the "Combine" step (Insertion).
     * @param st The stack, which is already sorted with N-1 elements.
     * @param val The value to insert.
     */
    private static void insertInSortedForm(Stack<Integer> st, int val) {
        // BASE CASE: If the stack is empty OR 'val' is smaller than or equal to
        // the current top, 'val' should sit right on top (since we sort DESCENDING).
        // This is the correct spot! Push it and start the backtracking.
        if (st.isEmpty() || st.peek() <= val) {
            st.push(val);
            return;
        }

        // **DIVE DEEPER**: The top element is greater than 'val', meaning 'val'
        // must go deeper down. We pop the top element, keeping it safe.
        int temp = st.pop();

        // **RECURSE**: Forward-thinking solution to find the spot deeper down the stack.
        insertInSortedForm(st, val);

        // **BACKTRACKING**: After the recursive call returns (meaning 'val' has
        // been inserted), we push 'temp' back onto the stack. This rebuilds the
        // stack structure layer-by-layer, ensuring it remains sorted!
        st.push(temp);
    }


    // -------------------------------------------------------------
    // 👾 MAIN METHOD FOR DEMO
    // -------------------------------------------------------------
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(1);
        stack.push(3);
        stack.push(2);

        System.out.println("Original Stack (Top -> Bottom): " + stack); // [4, 1, 3, 2]

        // Kickstarting the optimal recursive pipeline!
        sortStack(stack);

        // Expected output: Largest at Top. Top to Bottom should be [4, 3, 2, 1]
        System.out.println("Sorted Stack (DESC, Top -> Bottom): " + stack);
        //
    }
}