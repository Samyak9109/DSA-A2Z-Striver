public class PowerOfTwo {

    // ✅ Version 1: Brute Force using Math.pow (with type casting)
    public boolean isPowerOfTwoBrute(int n) {
        // Loop from 0 to 30 because 2^31 exceeds int limit
        for (int i = 0; i <= 30; i++) {
            // Math.pow returns double, so cast to int
            int ans = (int) Math.pow(2, i);

            // If n matches any power of 2, return true
            if (ans == n) {
                return true;
            }
        }
        // If no power of 2 matches, return false
        return false;
    }

    // ✅ Version 2: Better Approach using simple multiplication
    public boolean isPowerOfTwoBetter(int n) {
        int value = 1; // Start with 2^0

        // Multiply value by 2 until it exceeds n
        for (int i = 0; i <= 30; i++) {
            if (value == n) {
                return true;
            }
            value = value * 2;
        }
        return false;
    }

    // ✅ Version 3: Optimal Bitwise Approach
    public boolean isPowerOfTwoOptimal(int n) {
        // Only powers of two have exactly one bit set in binary
        // Example: 8 = 1000 → 8 & (8-1) = 1000 & 0111 = 0000
        return n > 0 && (n & (n - 1)) == 0;
    }

    // ✅ Main method to test all versions
    public static void main(String[] args) {
        PowerOfTwo checker = new PowerOfTwo();

        int[] testNumbers = {1, 2, 3, 4, 16, 18, 0, -4, 1024, 2049};

        System.out.println("Testing isPowerOfTwo using all methods:\n");

        for (int n : testNumbers) {
            System.out.println("Number: " + n);
            System.out.println("Brute Force: " + checker.isPowerOfTwoBrute(n));
            System.out.println("Better (Multiplication): " + checker.isPowerOfTwoBetter(n));
            System.out.println("Optimal (Bitwise): " + checker.isPowerOfTwoOptimal(n));
            System.out.println("--------------------------------------");
        }
    }
}
