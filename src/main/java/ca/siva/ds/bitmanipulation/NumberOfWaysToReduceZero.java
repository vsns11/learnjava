package ca.siva.ds.bitmanipulation;

/**
 * LeetCode: 1342, Time: O(log n), Space: O(logn)
 */
public class NumberOfWaysToReduceZero {
    class Solution {
        public int numberOfSteps(int num) {
            return numberOfSteps(num, 0);
        }

        public int numberOfSteps(int num, int cnt) {
            if (num == 0) {
                return cnt;
            }
            if (num%2 == 0) {
                num = num / 2;
            } else {
                num = num - 1;
            }
            return numberOfSteps(num, cnt + 1);

        }
    }
}
