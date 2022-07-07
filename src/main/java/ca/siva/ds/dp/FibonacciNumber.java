package ca.siva.ds.dp;

// Leetcode: 500
public class FibonacciNumber {
    class Solution {
        public int fib(int n) {

            int one = 0, two = 1;
            if (n == 0) return one;

            for (int i = 2; i <= n; ++i) {
                int tmp = two;
                two = one + two;
                one = tmp;
            }
            return two;

        }
    }
}
