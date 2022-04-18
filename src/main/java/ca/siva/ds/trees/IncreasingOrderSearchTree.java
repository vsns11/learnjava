package ca.siva.ds.trees;




// Leetcode 897: Increasing Order Search Tree

class Solution {
    class TreeNode {
        int val;
       TreeNode left;
       TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    TreeNode start = null, end = null;

    public TreeNode increasingBST(TreeNode root) {
        increasingBSThelper(root);
        return start;
    }

    public void increasingBSThelper(TreeNode root) {
        if (root == null) return;
        increasingBSThelper(root.left);
        if (start == null) {
            start = root;
        } else {
            end.right = root;
        }
        root.left = null;
        end = root;
        increasingBSThelper(root.right);
    }
}