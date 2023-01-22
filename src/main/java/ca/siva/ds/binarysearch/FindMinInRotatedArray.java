package ca.siva.ds.binarysearch;

// Time: O(log n), Space: O(1)
public class FindMinInRotatedArray {
    public static int min(int num1, int num2) {
        if (num1 < num2) return num1;
        return num2;
    }

    class Solution {
        public int findMin(int[] nums) {
            int numsLen = nums.length;
            if (numsLen == 1) return nums[0];
            int l = 0, r = numsLen - 1, result = Integer.MAX_VALUE;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                result = min(result, nums[mid]);
                if (nums[l] <= nums[mid]) {
                    result = min(result, nums[l]);
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return result;
        }



    }
}
