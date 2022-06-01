package ca.siva.ds.array;

/**
 * Leetcode: 1480 Time: O(N), Space: O(1)
 */
public class RunningSumOf1DArray {
    class Solution {
        public int[] runningSum(int[] nums) {
            for (int i = 1; i < nums.length; ++i) {
                nums[i] = nums[i - 1] + nums[i];
            }
            return nums;
        }
    }
}
