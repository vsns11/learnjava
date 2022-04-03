package ca.siva.ds.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//import java.util.Queue;
import java.util.Stack;

public class IterativeTraversal {

    public List<Integer> doIterativeTraversal(BinaryTreeNode node) {
        Stack<BinaryTreeNode> forward = new Stack<>();
        Stack<BinaryTreeNode> backward = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (node == null) return result;
        forward.add(node);

        while ( !forward.isEmpty() || !backward.isEmpty()) {
            while (!forward.isEmpty()) {
                BinaryTreeNode initalPoppedNode = forward.pop();
                result.add(initalPoppedNode.data);
                if (initalPoppedNode.left != null) {
                    backward.add(initalPoppedNode.left);
                }
                if (initalPoppedNode.right != null) {
                    backward.add(node.right);
                }

            }
            while (!backward.isEmpty()) {
                BinaryTreeNode poppedNode = backward.pop();
                result.add(poppedNode.data);
                if (poppedNode.right != null) {
                    forward.add(poppedNode.right);
                }

                if (poppedNode.left != null) {
                    forward.add(poppedNode.left);
                }

            }
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
        IterativeTraversal x = new IterativeTraversal();
        System.out.println(x.doIterativeTraversal(r));


    }
}
