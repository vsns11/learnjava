package ca.siva.ds.dp;

import java.util.HashMap;
import java.util.Map;

public class CombinationSumIv {
    class Solution {
        public int combinationSum4(int[] nums, int target) {
            Map<Integer, Integer> ref = new HashMap<>();
            return combinationSum4Helper(nums, target, ref);
        }


        public int combinationSum4Helper(int[] nums, int target, Map<Integer, Integer> ref) {
            if (ref.containsKey(target)) return ref.get(target);
            if (target == 0) return 1;

            int count = 0;
            for (int num: nums) {
                if (target - num >= 0)
                    count += combinationSum4Helper(nums, target - num, ref);
            }
            ref.put(target, count);
            return count;
        }

    }
}
