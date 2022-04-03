package ca.siva.ds.trees;

public class TwoTreesIdentical {
    public boolean areIdentical(BinaryTreeNode node1, BinaryTreeNode node2) {
        if (node1 != null && node2 == null) {
            return false;
        } else if (node2 != null && node1 == null) {
            return false;
        } else if (node1 == null && node2 == null) {
            return true;
        }

        return node1.data == node2.data && areIdentical(node1.left, node2.left) && areIdentical(node1.right, node2.right);
    }

    public static void main(String[] args) {
        BinaryTreeNode rootNode1 = new BinaryTreeNode(1);
        BinaryTreeNode m1 = new BinaryTreeNode(1);
        BinaryTreeNode m2 = new BinaryTreeNode(3);
        rootNode1.left = m1;
        rootNode1.right = m2;

        BinaryTreeNode rootNode2 = new BinaryTreeNode(2);
        rootNode2.left = m1;
        rootNode2.right = m2;

        TwoTreesIdentical obj = new TwoTreesIdentical();
        obj.areIdentical(rootNode1, rootNode2);

    }
}
