package ca.siva.ds.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
    Leetcode: 47
 */
public class PermutationsTwo {
    class Solution {

        public void swap(int idx1, int idx2, int[] nums) {
            int temp = nums[idx1];
            nums[idx1] = nums[idx2];
            nums[idx2] = temp;
        }

        public List<List<Integer>> permuteUnique(int[] nums) {

            List<List<Integer>> result = new ArrayList<>();
            helper(
                    nums,
                    0,
                    nums.length,
                    result,
                    new ArrayList<>(),
                    new HashMap<>()
            );
            return result;
        }

        public void helper(int[] nums, int startIdx, int limit, List<List<Integer>> result, List<Integer> currResult,
                           HashMap<String, Boolean> cache) {

            if (currResult.size() == limit) {

                if (!cache.containsKey(currResult.toString())) {
                    result.add(new ArrayList<>(currResult));
                    cache.put(currResult.toString(), true);
                }
                return;
            }


            for (int i = startIdx; i < nums.length; ++i) {

                swap(startIdx, i, nums);
                currResult.add(nums[startIdx]);
                // System.out.println(startIdx + Arrays.toString(nums) + currResult);

                helper(
                        nums,
                        startIdx + 1,
                        limit,
                        result,
                        currResult,
                        cache
                );
                currResult.remove(currResult.size() - 1);
                swap(startIdx, i, nums);
            }

        }


    }
}
