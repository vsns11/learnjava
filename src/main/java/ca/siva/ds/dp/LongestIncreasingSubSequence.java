package ca.siva.ds.dp;

/**
 * Leetcode: 300, Time: O(n^2), Space: O(m)
 */
public class LongestIncreasingSubSequence {
    class Solution {
        public int lengthOfLIS(int[] nums) {
            int[] maxIncSeq = new int[nums.length];

            maxIncSeq[0] = 1;
            int result = 1;

            for (int i = 1; i < nums.length; ++i) {
                int calcLen = 1;
                for (int j = i - 1; j >= 0; --j) {
                    if (nums[i] > nums[j]) {
                        calcLen = Math.max(maxIncSeq[j] + 1, calcLen);
                    }
                }

                result = Math.max(calcLen, result);
                maxIncSeq[i] = calcLen;
            }


            return result;

        }
    }
}
