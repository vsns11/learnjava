package ca.siva.ds.tree;

import java.util.ArrayList;

// Time Complexity: O(n), Space Complexity: O(n)
public class IsValidBST {
    public Boolean isValidBst(BinaryTreeNode node, int MIN_VAL, int MAX_VAL) {
        if (node == null) return true;
        if (node.data < MIN_VAL || node.data > MAX_VAL) return false;
        return isValidBst(node.left, MIN_VAL, node.data) && isValidBst(node.right, node.data, MAX_VAL);
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
        IsValidBST obj = new IsValidBST();
        System.out.println(obj.isValidBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}
