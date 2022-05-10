package ca.siva.ds.tree;

public class SumOfPathNumbers {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static int ans = 0;

    public static int calculateSumOfPathNumbers(TreeNode root) {
        if (root == null) return 0;
        return calculateSumOfPathNumbersHelper(root, 0);
    }

    public static int calculateSumOfPathNumbersHelper(TreeNode currentNode, int pathSum) {
        if (currentNode == null)
            return 0;

        // calculate the path number of the current node
        pathSum = 10 * pathSum + currentNode.val;

        // if the current node is a leaf, return the current path sum.
        if (currentNode.left == null && currentNode.right == null) {
            return pathSum;
        }

        // traverse the left and the right sub-tree
        return calculateSumOfPathNumbersHelper(currentNode.left, pathSum) +
                calculateSumOfPathNumbersHelper(currentNode.right, pathSum);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        System.out.println("Total Sum of Path Numbers: " + calculateSumOfPathNumbers(root));
    }
}
