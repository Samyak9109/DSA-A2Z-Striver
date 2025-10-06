import java.util.*;
//Leetcode 410

public class BookAllocation {

    /*
     * 🚀 Problem Summary:
     * We are given 'n' books and 'k' students. Each book has some number of pages.
     * The goal is to allocate books to students such that:
     *  - Each student gets a contiguous sequence of books.
     *  - The maximum number of pages assigned to a student is minimized.
     *
     * 📚 Real-World Analogy:
     * Imagine you're a team lead assigning work to interns. You want to make sure
     * no intern gets overloaded (max pages = workload). Balance is key 🔥
     */

    // ----------------------------------------------------------------------
    // ⚙️ BETTER APPROACH (LINEAR SEARCH)
    // ----------------------------------------------------------------------
    // Time Complexity: O((sum(arr) - max(arr)) * N)
    // Space Complexity: O(1)
    // Just loop from max(arr) → sum(arr), and check for feasible allocations.
    // ----------------------------------------------------------------------
    static int betterApproach(int[] arr, int k) {
        int n = arr.length;
        if (k > n) return -1; // can't allocate fewer books to more students

        int arrSum = 0, arrMax = Integer.MIN_VALUE;
        for (int j : arr) {
            arrSum += j;
            arrMax = Math.max(j, arrMax);
        }

        // Trying every possible max pages from max(arr) to sum(arr)
        for (int pages = arrMax; pages <= arrSum; pages++) {
            if (numOfStudents(arr, pages) <= k) {
                return pages; // first valid max pages found
            }
        }
        return arrSum;
    }

    // ----------------------------------------------------------------------
    // ⚡ OPTIMAL APPROACH (BINARY SEARCH)
    // ----------------------------------------------------------------------
    // Time Complexity: O(N * log(sum(arr) - max(arr)))
    //    - Binary search range = [max(arr), sum(arr)]
    //    - numOfStudents() runs in O(N)
    // Space Complexity: O(1)
    // Reasoning: Binary search drastically reduces search space 🔥
    // ----------------------------------------------------------------------
    static int findPages(int[] arr, int k) {
        int n = arr.length;
        if (k > n) return -1;

        int arrSum = 0, arrMax = Integer.MIN_VALUE;
        for (int j : arr) {
            arrSum += j;
            arrMax = Math.max(j, arrMax);
        }

        int low = arrMax, high = arrSum;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int students = numOfStudents(arr, mid);

            if (students > k) {
                // Too many students needed → increase page limit
                low = mid + 1;
            } else {
                // Can we reduce further?
                high = mid - 1;
            }
        }
        return low;
    }

    // ----------------------------------------------------------------------
    // 🧩 Helper Function: Calculates how many students needed
    // if each student can read up to 'pages' pages max.
    // ----------------------------------------------------------------------
    static int numOfStudents(int[] arr, int pages) {
        int students = 1;
        long pagesStudent = 0;

        for (int j : arr) {
            if (pagesStudent + j <= pages) {
                // Assign current book to same student
                pagesStudent += j;
            } else {
                // Assign book to next student
                students++;
                pagesStudent = j;
            }
        }
        return students;
    }

    // ----------------------------------------------------------------------
    // 🧪 MAIN METHOD — let's test it out like pros 🧠
    // ----------------------------------------------------------------------
    public static void main(String[] args) {
        int[] books = {12, 34, 67, 90}; // pages in each book
        int students = 2;

        System.out.println("🔥 Brute Force (conceptual only): " + bruteForce(books, books.length, students));
        System.out.println("⚙️  Better Approach: " + betterApproach(books, students));
        System.out.println("⚡ Optimal Approach (Binary Search): " + findPages(books, students));
    }
}
