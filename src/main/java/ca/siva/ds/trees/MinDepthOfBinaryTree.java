package ca.siva.ds.trees;

import java.util.LinkedList;
import java.util.Queue;

public class MinDepthOfBinaryTree {
    public int calculateMinDepthOfBinaryTree(BinaryTreeNode root) {
        Queue<BinaryTreeNode> queue1 = new LinkedList<>();
        Queue<BinaryTreeNode> queue2 = new LinkedList<>();
        int depth = 0;
        if (root == null) return depth;
        queue1.add(root);


        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            depth++;
            while (!queue1.isEmpty()) {
                BinaryTreeNode node = queue1.poll();
                if (node.left == null && node.right == null) return depth;
                if (node.left != null) queue2.add(node.left);
                if (node.right != null) queue2.add(node.right);
            }
            depth++;
            while (!queue2.isEmpty()) {
                BinaryTreeNode node = queue2.poll();
                if (node.left == null && node.right == null) return depth;
                if (node.left != null) queue1.add(node.left);
                if (node.right != null) queue1.add(node.right);
            }
        }
        return depth;

    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(12);
        root.left = new BinaryTreeNode(7);
        root.right = new BinaryTreeNode(1);
        root.right.left = new BinaryTreeNode(10);
        root.right.right = new BinaryTreeNode(5);
        MinDepthOfBinaryTree obj = new MinDepthOfBinaryTree();

        System.out.println("Tree Minimum Depth: " + obj.calculateMinDepthOfBinaryTree(root));
        root.left.left = new BinaryTreeNode(9);
        root.right.left.left = new BinaryTreeNode(11);
        System.out.println("Tree Minimum Depth: " + obj.calculateMinDepthOfBinaryTree(root));

    }

}
