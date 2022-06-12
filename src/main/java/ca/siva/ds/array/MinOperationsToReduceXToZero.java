package ca.siva.ds.array;

/**
 * Leetcode: 1658, Time: O(N), Space: O(N)
 */
public class MinOperationsToReduceXToZero {
    class Solution {
        public int minOperations(int[] nums, int x) {
            int sum = 0;
            for (int i : nums) {
                sum += i;
            }
            int matchVal = sum - x;

            int maxLen = -1, current = 0, left = 0;
            for (int right = 0; right < nums.length; ++right) {
                current += nums[right];
                while (current > matchVal && left <= right) {
                    current -= nums[left];
                    left++;
                }
                if (current == matchVal) {
                    maxLen = Math.max(maxLen, right - left + 1);
                }
            }

            return maxLen == -1 ? -1 : nums.length - maxLen;

        }
    }
}
