package ca.siva.ds.binarysearch;

public class SearchInaRotatedArray {
    //Time: O(log n), Space: O(1)
    class Solution {

        public int search(int[] nums, int target) {
            int numsLen = nums.length;
            if (nums.length == 0) return -1;

            int l = 0, r = numsLen - 1;

            while (l <= r) {
                int mid = l + (r - l)/2;
                System.out.println(l + "" + mid + "" + r);
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[mid] >= nums[l]) {
                    if (target < nums[mid] && target >= nums[l]) r = mid - 1;
                    else l = mid + 1;
                } else {
                    if (target > nums[mid] && target <= nums[r]) l = mid + 1;
                    else r = mid - 1;
                }

            }
            return -1;

        }
    }
}
