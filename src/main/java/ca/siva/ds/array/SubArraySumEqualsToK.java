package ca.siva.ds.array;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumEqualsToK {
    //Leetcode: 560, Time: O(N), Space: O(N)
    class Solution {
        public int subarraySum(int[] nums, int k) {
            int count = 0;
            Map<Integer, Integer> ref = new HashMap<>();
            ref.put(0, 1);
            int sumSoFar = 0;
            for (int num : nums) {
                sumSoFar += num;
                if (ref.containsKey(sumSoFar - k)) {
                    count += ref.get(sumSoFar - k);
                }
                ref.put(sumSoFar, ref.getOrDefault(sumSoFar, 0) + 1);
            }
            return count;
        }

    }
}
