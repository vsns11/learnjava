package ca.siva.ds.array;

/**
 * Leetcode 287. O(n), O(1)
 */
public class FindTheDuplicateNumber {
    class Solution {
        public int findDuplicate(int[] nums) {
            int t = nums[0], h = nums[0];

            do {
                t = nums[t];
                h = nums[nums[h]];
            } while (t != h);


            t = nums[0];

            while (t != h) {
                t = nums[t];
                h = nums[h];
            }
            return t;
        }
    }
}
