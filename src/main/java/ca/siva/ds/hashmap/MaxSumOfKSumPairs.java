package ca.siva.ds.hashmap;

import java.util.Map;
import java.util.HashMap;

// LeetCode: #1679

/**
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5, hence a total of 2 operations.
 */
public class MaxSumOfKSumPairs {
    public int maxOperations(int[] nums, int k) {

        Map<Integer, Integer> cache = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i], num2 = k - nums[i];
            if (cache.containsKey(num2) && cache.get(num2) > 0) {
                count++;
                cache.put(num2, cache.get(num2) - 1);
            } else {
                cache.put(nums[i], cache.getOrDefault(nums[i], 0) + 1);
            }
        }

        return count;
    }
}
