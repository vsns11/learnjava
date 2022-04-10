package ca.siva.ds.trees;

public class NthHighestNumberInBSTree {
    int currCount = 0;

    public BinaryTreeNode findTheNode(BinaryTreeNode node, int count) {
        if (node == null) return null;
        BinaryTreeNode n = findTheNode(node.right, count);
        if (n != null) return n;
        count++;
        if (count == currCount) return node;
        return findTheNode(node.left, count);
    }

    public static void main(String[] args) {

    }
}
