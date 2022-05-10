package ca.siva.ds.tree;

public class DeleteZeroSumSubTrees {

    public int performDeletion(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = performDeletion(node.left);
        int right = performDeletion(node.right);

        if (left == 0) {
            node.left = null;
        } else if (right == 0) {
            node.right = null;
        }
        return (left + right + node.data);

    }

    public static void main(String[] args) {
        BinaryTreeNode head = new BinaryTreeNode(7);
        BinaryTreeNode currentHead = head;

        BinaryTreeNode left = new BinaryTreeNode(5);
        BinaryTreeNode right = new BinaryTreeNode(6);
        currentHead.left = left;
        currentHead.right = right;

        currentHead = head.left;
        left = new BinaryTreeNode(-3);
        right = new BinaryTreeNode(-2);
        currentHead.left = left;
        currentHead.right = right;
        BinaryTree tree = new BinaryTree(head);
        System.out.println("Before Level Order Traversal");
        BinaryTree.displayLevelOrder(tree.root);


        DeleteZeroSumSubTrees obj = new DeleteZeroSumSubTrees();
        obj.performDeletion(tree.root);
        System.out.println("After Level Order Traversal");
        BinaryTree.displayLevelOrder(tree.root);

    }
}
