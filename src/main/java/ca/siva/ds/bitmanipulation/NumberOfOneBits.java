package ca.siva.ds.bitmanipulation;

/**
 * Leetcode: 191, Number Of 1 bits
 */
public class NumberOfOneBits {
    public class Solution {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int count = 0;

            while (n != 0) {
                count++;
                n = n & (n - 1);
            }
            return count;
        }
    }
}
