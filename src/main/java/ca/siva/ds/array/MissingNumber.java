package ca.siva.ds.array;

/**
 * LeetCode: 268, Time: O(n), Space: O(1)
 */
public class MissingNumber {
    class Solution {
        public int missingNumber(int[] nums) {

            int x = 0, maxVal = nums.length;
            for (int i : nums) {
                maxVal = Math.max(maxVal, i);
                x += i;
            }

            return ((maxVal * (maxVal + 1) / 2) - x);
        }
    }
}
