package ca.siva.ds.tree;

import java.util.function.Consumer;

public class StepByStepInstructionsToAnother {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
//    class Solution {
//
//        public TreeNode getCommonPoint(TreeNode root, int startValue, int destValue) {
//            if (root == null || root.val == startValue || root.val == destValue)
//                return null;
//            TreeNode l = getCommonPoint(root.left, startValue, destValue);
//            TreeNode r = getCommonPoint(root.right, startValue, destValue);
//            if (l != null && r != null) {
//                return root;
//            }
//            return l == null ? r : l;
//        }
//
//        public boolean getDirectionsLeft(TreeNode root, int startValue, StringBuilder sb) {
//            if (root == null) return false;
//
//            boolean foundLeft = getDirectionsLeft(root.left);
//            if (!foundLeft)
//        else
//            sb.append('U');
//
//        }
//
//        public String getDirections(TreeNode root, int startValue, int destValue) {
//            //1. common point
//            //2. find left point
//            //3. find right point
//            //4. Join 2 + 3
//
//
//        }
//    }
}
