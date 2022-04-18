package ca.siva.ds.trees;

import java.util.LinkedList;
import java.util.ListIterator;

public class CountAllPaths {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int calculateCountAllPaths(TreeNode root, int sum) {
        return calculateCountAllPathsHelper(root, new LinkedList<Integer>(), sum);
    }

    public static int calculateCountAllPathsHelper(TreeNode root, LinkedList<Integer> currPath, int s) {
        if (root == null) return 0;
        currPath.add(root.val);
        int count = 0, currSum = 0;
        ListIterator<Integer> listIterator = currPath.listIterator(currPath.size());
        while (listIterator.hasPrevious()) {
            currSum += listIterator.previous();
            if (currSum == s) {
                count = count + 1;
            }
        }
        int leftCount = calculateCountAllPathsHelper(root.left, currPath, s);
        int rightCount = calculateCountAllPathsHelper(root.right, currPath, s);

        return count +  leftCount + rightCount;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + CountAllPaths.calculateCountAllPaths(root, 11));

    }

}
