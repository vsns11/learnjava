package ca.siva.ds.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativeInorderTraversal {

    public List<Integer> executeIterativeInorderTraversal(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        BinaryTreeNode ref = root;
        while (ref != null) {
            stack.add(ref);
            ref = ref.left;
        }

        while(!stack.isEmpty()) {
            BinaryTreeNode node = stack.pop();
            result.add(node.data);
            BinaryTreeNode right_ref = node.right;
            while( right_ref != null) {
                stack.add(right_ref);
                right_ref = right_ref.left;
            }

        }
        return result;
    }


    public List<Integer> executeIterativeInorderTraversalOptimized(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
//        if (root == null) return result;

        while(!stack.isEmpty() || root != null) {
            if( root != null) {
                stack.add(root);
                root = root.left;
                continue;
            }
            BinaryTreeNode node = stack.pop();
            result.add(node.data);
            root = node.right;

        }
        return result;
    }

    public static void main(String[] args) {
        /**
         *         0
         *      1    2
         *    3  4  5  6
         *
         * o/p: 0, 1, 2, 3, 4, 5, 6
         */
        BinaryTreeNode r = new BinaryTreeNode(0);
        BinaryTreeNode r1 = new BinaryTreeNode(1);
        BinaryTreeNode r2 = new BinaryTreeNode(2);
        BinaryTreeNode r3 = new BinaryTreeNode(3);
        BinaryTreeNode r4 = new BinaryTreeNode(4);
        BinaryTreeNode r5 = new BinaryTreeNode(5);
        BinaryTreeNode r6 = new BinaryTreeNode(6);
        r1.left = r3;
        r1.right = r4;
        r2.left = r5;
        r2.right = r6;
        r.left = r1;
        r.right = r2;
        IterativeInorderTraversal obj = new IterativeInorderTraversal();
        System.out.println(obj.executeIterativeInorderTraversalOptimized(r));
    }
}
