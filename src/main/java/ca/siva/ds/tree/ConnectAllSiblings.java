package ca.siva.ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Time: O(n), Space: O(n)
public class ConnectAllSiblings {

    public void performConnections(BinaryTreeNode node) {
        Queue<BinaryTreeNode> queue1 = new LinkedList<>();
        Queue<BinaryTreeNode> queue2 = new LinkedList<>();
        BinaryTreeNode prev = null;
        queue1.add(node);
        while (!queue1.isEmpty() || !queue2.isEmpty()) {

            while (!queue1.isEmpty()) {
                BinaryTreeNode ref = queue1.poll();
                if (prev != null) {
                    prev.next = ref;
                }
                prev = ref;
                if (ref.left != null)
                    queue2.add(ref.left);
                if (ref.right != null)
                    queue2.add(ref.right);
            }

            while (!queue2.isEmpty()) {
                BinaryTreeNode ref = queue2.poll();
                if (prev != null) {
                    prev.next = ref;
                }
                prev = ref;
                if (ref.left != null)
                    queue1.add(ref.left);
                if (ref.right != null)
                    queue1.add(ref.right);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
        input.add(100);input.add(50);input.add(200);input.add(25);input.add(75);input.add(125);input.add(350);
        BinaryTreeNode root = BinaryTree.createBST(input);
        ConnectAllSiblings obj = new ConnectAllSiblings();
        obj.performConnections(root);
        BinaryTreeNode curr = root;
        while(curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

}
