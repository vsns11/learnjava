package ca.siva.ds.tree;

import java.util.ArrayList;
import java.util.List;


public class AllPathsForASum {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    };

    public static List<List<Integer>> calculateAllPathsForASum(TreeNode root, int s) {
        List<List<Integer>> result = new ArrayList<>();
        calculateAllPathsForASumHelper(root, 0, s, new ArrayList<>(), result);
        return result;
    }

    public static void calculateAllPathsForASumHelper(TreeNode root, int currSum, int s, List<Integer> currPath, List<List<Integer>>  result) {
        if (root == null) return;

        currPath.add(root.val);
        if (currSum + root.val == s) {
            result.add(new ArrayList<Integer>(currPath));
        } else {
            calculateAllPathsForASumHelper(root.left, currSum + root.val, s, currPath, result);
            calculateAllPathsForASumHelper(root.right, currSum + root.val, s, currPath, result);
        }
        currPath.remove(currPath.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        List<List<Integer>> result = calculateAllPathsForASum(root, sum);
        System.out.println(result);

    }
}
