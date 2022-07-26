package ca.siva.ds.tree;

public class LowestCommonAncestor {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    //Leetcode: 236, Time: O(N), Space: O(N)
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return null;
            if (root.val == p.val || root.val == q.val) return root;

            TreeNode l = lowestCommonAncestor(root.left, p, q);
            TreeNode r = lowestCommonAncestor(root.right, p, q);
            if (l != null && r != null) {
                return root;
            } else if (l != null) {
                return l;
            }
            return r;
        }
    }
}
