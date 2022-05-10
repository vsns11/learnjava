package ca.siva.ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Time: O(n), Space: O(n)
public class LevelOrderTraversal {

    public List<Integer> executeLevelOrderTraversal(BinaryTreeNode node) {
        List<Integer> result = new ArrayList<>();
        Queue<BinaryTreeNode> curr = new LinkedList<>(), next = new LinkedList<>();
        if (node == null) return result;
        curr.add(node);
        while (!curr.isEmpty() || !next.isEmpty()) {
            while (!curr.isEmpty()) {
                BinaryTreeNode currNode = curr.poll();
                result.add(currNode.data);
                if (currNode.left != null)
                    next.add(currNode.left);
                if (currNode.right != null)
                    next.add(currNode.right);
            }

            while (!next.isEmpty()) {
                BinaryTreeNode nextNode = next.poll();
                result.add(nextNode.data);
                if (nextNode.left != null)
                    curr.add(nextNode.left);
                if (nextNode.right != null)
                    curr.add(nextNode.right);
            }
        }


        return result;
    }

    public static void main(String[] args) {
        /**
         *          10
         *      6       12
         *    3  7      11  16
         *         8
         *           9
         * o/p: [10, 6, 12, 3, 7, 11, 16, 8, 9]
         */
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
        LevelOrderTraversal obj = new LevelOrderTraversal();
        System.out.println(obj.executeLevelOrderTraversal(root));

    }
}
