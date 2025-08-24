public class BasicRecursion {

    /*
     * Time Complexity: O(n)
     * Space Complexity: O(n) - due to recursion call stack of depth n
     * Reason: The function prints the message n times, making one recursive call per print.
     */
    public static void printN(int n, String message) {
        if (n <= 0) return;
        System.out.println(message);
        printN(n - 1, message);
    }

    /*
     * Time Complexity: O(n)
     * Space Complexity: O(n) - recursion depth n
     * Reason: Prints numbers from i to i + n - 1, total n prints and recursive calls.
     */
    public static void print1toN(int n, int i) {
        if (n <= 0) return;
        System.out.println(i);
        print1toN(n - 1, i + 1);
    }

    /*
     * Time Complexity: O(n)
     * Space Complexity: O(n) - recursion depth n
     * Reason: Prints numbers from n down to 1, one recursive call per print.
     */
    public static void printNto1(int n) {
        if (n <= 0) return;
        System.out.println(n);
        printNto1(n - 1);
    }

    /*
     * Time Complexity: O(n)
     * Space Complexity: O(n) - recursion call stack depth n
     * Reason: Goes down to base case n=0, then prints on backtracking, total n prints.
     */
    public static void printBacktrack(int n) {
        if (n == 0) return;
        printBacktrack(n - 1);
        System.out.println(n);
    }

    /*
     * Time Complexity: O(n)
     * Space Complexity: O(n) - recursion depth n
     * Reason: Similar to printBacktrack, but uses two parameters to print while backtracking.
     */
    public static void printBacktrackNto1(int n, int current) {
        if (current > n) return;
        printBacktrackNto1(n, current + 1);
        System.out.println(current);
    }

    /*
     * Time Complexity: O(n)
     * Space Complexity: O(n) - recursion depth n
     * Reason: Sums numbers from n down to 0 recursively.
     */
    public static int sumOfN(int n) {
        if (n == 0) return 0;
        return n + sumOfN(n - 1);
    }

    /*
     * Time Complexity: O(n)
     * Space Complexity: O(n) - recursion depth n
     * Reason: Calculates factorial by recursive multiplication from n down to 1.
     */
    public static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }

    /*
     * Time Complexity: O(n)
     * Space Complexity: O(n) - recursion depth approx n/2 (still O(n))
     * Reason: Swaps elements from start to end, each call moves indices inward by 1.
     */
    public static void reverseAnArrayMeth1(int[] arr, int start, int end) {
        if (start >= end) return;
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        reverseAnArrayMeth1(arr, start + 1, end - 1);
    }

    /*
     * Time Complexity: O(n)
     * Space Complexity: O(n) - recursion depth approx n/2 (still O(n))
     * Reason: Swaps symmetric elements in the array using backtracking with index i.
     */
    public static void reveseAnArrayBacktraking(int[] arr, int i) {
        int n = arr.length;
        if (i >= n / 2) return;
        int temp = arr[i];
        arr[i] = arr[n - i - 1];
        arr[n - i - 1] = temp;
        reveseAnArrayBacktraking(arr, i + 1);
    }

    /*
     * Time Complexity: O(n)
     * Space Complexity: O(n) - recursion depth approx n/2
     * Reason: Checks characters at start and end indices, moving inward until middle.
     */
    public static boolean checkPalindrome(String str, int start, int end) {
        if (start >= end) return true;
        if (str.charAt(start) != str.charAt(end)) return false;
        return checkPalindrome(str, start + 1, end - 1);
    }

    /*
     * Time Complexity: O(n)
     * Space Complexity: O(n) - recursion depth approx n/2
     * Reason: Similar to checkPalindrome but uses single index i.
     */
    public static boolean isPalindromeMeth2(String str, int i) {
        if (i >= str.length() / 2) return true;
        if (str.charAt(i) != str.charAt(str.length() - i - 1)) return false;
        return isPalindromeMeth2(str, i + 1);
    }

    /*
     * Time Complexity: O(2^n) - exponential
     * Space Complexity: O(n) - recursion depth n
     * Reason: Naive Fibonacci calculation calls fib(n-1) and fib(n-2) recursively without memoization, leading to exponential calls.
     */
    public static int fibonacchi(int n) {
        if (n <= 1) return n;
        return fibonacchi(n - 1) + fibonacchi(n - 2);
    }

    /*
     * Time Complexity: O(n * 2^n)
     * Space Complexity: O(n)
     * Reason: Calls fibonacchi(i) for i=0 to n-1. Each fibonacchi call is exponential O(2^i). Summed up, this is O(n*2^n).
     */
    public static void printFibonacchi(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacchi(i) + " ");
        }
        System.out.println();
    }

    // Main execution method demonstrating each recursive method with example inputs
    public static void main(String[] args) {

        System.out.println("printN(3, \"Hello\"):");
        printN(3, "Hello");

        System.out.println("\nprint1toN(5, 1):");
        print1toN(5, 1);

        System.out.println("\nprintNto1(5):");
        printNto1(5);

        System.out.println("\nprintBacktrack(5):");
        printBacktrack(5);

        System.out.println("\nprintBacktrackNto1(5, 1):");
        printBacktrackNto1(5, 1);

        System.out.println("\nsumOfN(5):");
        System.out.println(sumOfN(5));  // Output: 15

        System.out.println("\nfactorial(5):");
        System.out.println(factorial(5));  // Output: 120

        int[] arr1 = {1, 2, 3, 4, 5};
        System.out.println("\nreverseAnArrayMeth1 before reverse:");
        for (int v : arr1) System.out.print(v + " ");
        System.out.println();
        reverseAnArrayMeth1(arr1, 0, arr1.length - 1);
        System.out.println("reverseAnArrayMeth1 after reverse:");
        for (int v : arr1) System.out.print(v + " ");
        System.out.println();

        int[] arr2 = {10, 20, 30, 40, 50};
        System.out.println("\nreveseAnArrayBacktraking before reverse:");
        for (int v : arr2) System.out.print(v + " ");
        System.out.println();
        reveseAnArrayBacktraking(arr2, 0);
        System.out.println("reveseAnArrayBacktraking after reverse:");
        for (int v : arr2) System.out.print(v + " ");
        System.out.println();

        String palindromeTest1 = "racecar";
        System.out.println("\ncheckPalindrome(\"" + palindromeTest1 + "\"):");
        System.out.println(checkPalindrome(palindromeTest1, 0, palindromeTest1.length() - 1)); // true

        String palindromeTest2 = "hello";
        System.out.println("\nisPalindromeMeth2(\"" + palindromeTest2 + "\"):");
        System.out.println(isPalindromeMeth2(palindromeTest2, 0)); // false

        System.out.println("\nprintFibonacchi(10):");
        printFibonacchi(10);
    }
}
