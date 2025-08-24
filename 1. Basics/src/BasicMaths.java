import java.util.ArrayList;
import java.util.Collections;

public class BasicMaths {

    // Count the number of digits in a number
    // Time Complexity: O(log n) because dividing by 10 reduces digits count
    // Space Complexity: O(1) constant space
    public static int countDigits(int n) {
        int count = 0;
        while (n > 0) {
            n = n / 10; // Remove last digit
            count++;    // Increase count
        }
        return count;
    }

    // Reverse the digits of a number
    // Time Complexity: O(log n) where n is the number of digits (since we process each digit once)
    // Space Complexity: O(1)
    public static int reverseDigits(int x) {
        int num = 0;
        while (x != 0) {
            int rem = x % 10;        // Get last digit
            num = (num * 10) + rem;  // Append it to reversed number
            x = x / 10;              // Remove last digit
        }
        return num;
    }

    // Check if a number is a palindrome (Method 1 - complete reverse)
    // Time Complexity: O(log n) - reversing digits once
    // Space Complexity: O(1)
    public static boolean isPalindrome(int n) {
        int num = 0;
        int oriNum = n; // Store original number
        int rem;

        while (n > 0) {
            rem = n % 10;         // Get last digit
            num = (num * 10) + rem; // Append digit
            n = n / 10;             // Remove last digit
        }

        // Compare reversed number with original
        if (oriNum == num) {
            System.out.println("Number is a Palindrome");
            return true;
        } else {
            System.out.println("Number is not a Palindrome");
            return false;
        }
    }

    // Check if a number is a palindrome (Method 2 - optimized, reverse only half)
    // Time Complexity: O(log n) - only half of the digits are processed
    // Space Complexity: O(1)
    public static boolean isPalindromeMeth2(int n) {
        // Special case check
        if (n < 0 || (n % 10 == 0 && n != 0)) {
            return false;
        }

        int reversedHalf = 0;
        while (n > reversedHalf) {
            int rem = n % 10;
            reversedHalf = reversedHalf * 10 + rem;
            n = n / 10;
        }

        // Works for both even and odd digit numbers
        return (n == reversedHalf || n == reversedHalf / 10);
    }

    // Check if a 3-digit number is an Armstrong number (sum of cubes of digits = number)
    // Time Complexity: O(log n) - iterates through each digit
    // Space Complexity: O(1)
    public static void Armstrong(int n) {
        int num = 0;
        int oriNum = n;
        while (n > 0) {
            int digit = n % 10;
            num += digit * digit * digit; // Add cube of digit
            n = n / 10;
        }

        // Compare result with original number
        if (oriNum == num) {
            System.out.println("Number is an Armstrong number");
        } else {
            System.out.println("Number is not an Armstrong number");
        }
    }

    // Brute-force method to print all divisors of a number
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static void printDivisors(int n) {
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) { // If divisible
                System.out.println(i);
            }
        }
    }

    // Optimized method to print all divisors in sorted order using square root
    // Time Complexity: O(√n log √n)
    // Explanation: O(√n) to find divisors, and O(k log k) for sorting k divisors (k ≤ 2√n)
    // Space Complexity: O(√n) to store divisors in the ArrayList
    public static void printDivisors2(int n) {
        ArrayList<Integer> Divisor = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                Divisor.add(i); // Add divisor
                if (n / i != i) {
                    Divisor.add(n / i); // Add paired divisor
                }
            }
        }

        Collections.sort(Divisor); // Sort divisors
        for (int pr : Divisor) {
            System.out.println(pr);
        }
    }

    // Check if a number is prime using sqrt(n) optimization
    // Time Complexity: O(√n)
    // Space Complexity: O(1)
    public static void checkForPrime(int n) {
        if (n <= 1) {
            System.out.println("Number is NOT a Prime Number");
            return;
        }

        int counter = 0;
        // Check from 1 to sqrt(n)
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                if (n / i == i) {
                    counter++; // Count perfect square root only once
                } else {
                    counter += 2; // Count both divisors
                }
            }
        }

        // Prime number has exactly 2 divisors: 1 and itself
        if (counter == 2) {
            System.out.println("Number is a Prime Number");
        } else {
            System.out.println("Number is NOT a Prime Number");
        }
    }

    // Find GCD (Greatest Common Divisor) using Euclidean algorithm
    // Time Complexity: O(log(min(a, b))) - worst case complexity of Euclidean algorithm
    // Space Complexity: O(log(min(a, b))) due to recursion stack depth
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a; // Base case
        }
        return gcd(b, a % b); // Recursive step
    }

    // Main method to test the functions
    public static void main(String[] args) {

        // Count digits in number
        System.out.println("Digits Count: " + countDigits(98654));

        // Reverse digits
        System.out.println("Reversed: " + reverseDigits(89563));

        // Check palindrome using full reverse method
        isPalindrome(121);

        // Check Armstrong number
        Armstrong(153); // Try with 370, 371, 407 for more Armstrong numbers

        // Brute-force approach for printing divisors
        System.out.println("Brute-force Divisors:");
        printDivisors(36);

        // Optimized approach for printing divisors
        System.out.println("Optimized Divisors:");
        printDivisors2(36);

        // Prime check
        checkForPrime(5); // You can test with 2, 4, 9, 17, 19

        // GCD of two numbers
        int a = 36, b = 60;
        System.out.println("GCD of " + a + " and " + b + " is: " + gcd(a, b));
    }
}
