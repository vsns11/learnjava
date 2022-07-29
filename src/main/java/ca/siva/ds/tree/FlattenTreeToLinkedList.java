package ca.siva.ds.tree;

public class FlattenTreeToLinkedList {
    //Leet: 114, time: O(N), Space: O(N)
    class Solution {
        public TreeNode flattenTreeHelper(TreeNode root) {
            if (root == null) return null;

            if (root.left == null && root.right == null) {
                return root;
            }
            TreeNode l = flattenTreeHelper(root.left);
            TreeNode r = flattenTreeHelper(root.right);
            if (l != null){
                l.right = root.right;
            }
            root.right = root.left != null ? root.left : root.right;
            root.left = null;
            return r != null? r : l;
        }

        public void flatten(TreeNode root) {
            flattenTreeHelper(root);
        }
}}
