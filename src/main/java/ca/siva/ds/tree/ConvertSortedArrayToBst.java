package ca.siva.ds.tree;

//Leetcode: 108, Time: O(n), Space: O(log n)
public class ConvertSortedArrayToBst {
    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            if (nums.length == 0) return null;

            return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
        }

        public TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
            if (left > right) return null;

            int mid = (left + right) / 2;

            TreeNode root = new TreeNode(nums[mid]);
            root.left = sortedArrayToBSTHelper(nums, left, mid - 1);
            root.right = sortedArrayToBSTHelper(nums, mid + 1, right);
            return root;
        }
    }
}
