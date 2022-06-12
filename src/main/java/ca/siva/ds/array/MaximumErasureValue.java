package ca.siva.ds.array;

import java.util.HashMap;
import java.util.Map;

public class MaximumErasureValue {
    /**
     * LeetCode: 1695, Time: O(N), space: O(N)
     */
    class Solution {
        public int maximumUniqueSubarray(int[] nums) {
            Map<Integer, Integer> count = new HashMap<>();
            int left = 0, right = 0, maxValue = Integer.MIN_VALUE;
            int current = 0;

            for (right = 0; right < nums.length; ++right) {
                current += nums[right];
                if (count.get(nums[right]) == null) {
                    count.put(nums[right], right);
                } else {
                    int maxIdx = count.get(nums[right]);
                    while (left <= maxIdx) {
                        current -= nums[left];
                        left++;
                    }
                    count.put(nums[right], right);
                }
                // int len = right - len + 1;
                if (current > maxValue) {
                    maxValue = current;

                }

            }

            return maxValue;
        }
    }
}
