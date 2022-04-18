package ca.siva.ds.trees;

import java.util.LinkedList;

public class NthSmallestNumberInBST {

    public int getNthSmallestNumber(BinaryTreeNode root, int n) {
        LinkedList<BinaryTreeNode> stack = new LinkedList<>();
        while(true) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            if (--n == 0) return root.data;
            root = root.right;
        }

    }
}
