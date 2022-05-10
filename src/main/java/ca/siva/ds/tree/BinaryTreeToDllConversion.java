package ca.siva.ds.tree;


import java.util.ArrayList;


public class BinaryTreeToDllConversion {
    BinaryTreeNode prev = null, head = null;

    public void performConversion(BinaryTreeNode root) {
        if (root ==  null) return;
        performConversion(root.left);
        if (prev == null) {
            head = root;
        } else {
            prev.right = root;
            root.left = prev;
        }
        prev = root;
        performConversion(root.right);
    }

    public static void main(String[] args) {
        ArrayList<Integer> origData = new ArrayList<Integer>();
        origData.add(10);
        origData.add(6);
        origData.add(12);
        origData.add(3);
        origData.add(11);
        origData.add(16);
        origData.add(7);
        origData.add(8);
        origData.add(9);

        BinaryTreeNode root = BinaryTree.createBST(origData);
        BinaryTreeToDllConversion obj = new BinaryTreeToDllConversion();
        obj.performConversion(root);
        BinaryTreeNode node = obj.head;
        while (node != null) {
            System.out.println(node.data);
            node = node.right;
        }
    }
}
