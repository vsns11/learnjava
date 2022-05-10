package ca.siva.ds.tree;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderIterativeTraversal {

    Stack<BinaryTreeNode> stack = new Stack<>();

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public void doInsertion(BinaryTreeNode node) {
        while (node != null) {
            stack.add(node);
            node = node.left;
        }
    }

    public Integer next() {
        BinaryTreeNode node = stack.pop();
        doInsertion(node.right);
        return node.data;
    }


    public List<Integer> executeInorderIteratorTraversal(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        doInsertion(root);
        BinaryTreeNode node = root;
        while (this.hasNext()) {
            result.add(this.next());
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
        InorderIterativeTraversal obj = new InorderIterativeTraversal();
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
        System.out.println(obj.executeInorderIteratorTraversal(r));
        System.out.println(obj.executeInorderIteratorTraversal(r1));
    }
}
