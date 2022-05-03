package ca.siva.ds.array;

import java.util.Arrays;

// LeetCode: 581

/**
 * e.g.
 * Input: nums = [2,6,4,8,10,9,15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 */
public class ShortestUnsortedSubArray {

    // approach: 1
    public int findUnsortedSubarray(int[] nums) {
        int[] x = Arrays.copyOf(nums, nums.length);

        Arrays.sort(x);
        int start = nums.length - 1, end = 0;

        for (int i = 0; i < x.length; i++) {
            if (x[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }


        return start >= end ? 0 : end - start + 1;
    }

    public int findUnsortedSubarrayOptimal(int[] nums) {
        int min = Integer.MAX_VALUE;
        boolean flag = false;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                flag = true;
            }
            if (flag) {
                min = Math.min(nums[i], min);
            }
        }

        int max = Integer.MIN_VALUE;
        boolean flag1 = false;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                flag1 = true;
            }
            if (flag1) {
                max = Math.max(nums[i], max);
            }
        }

        int l, r;
        for (l = 0; l < nums.length; l++) {
            if (nums[l] > min) {
                break;
            }
        }

        for (r = nums.length - 1; r >= 0; r--) {
            if (nums[r] < max) {
                break;
            }
        }

        return r - l < 0 ? 0 : r - l + 1;
    }

}

