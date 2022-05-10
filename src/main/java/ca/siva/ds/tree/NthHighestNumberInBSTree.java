package ca.siva.ds.tree;

import java.util.ArrayList;

// Best_case Time: O(n), Worst_case Space: O(n), Best_case space: O(h)
public class NthHighestNumberInBSTree {
//    int currCount = 0;
    static class CustomReturnType {
        BinaryTreeNode node;
        int count;

        public static CustomReturnType initializeCustomReturnType(BinaryTreeNode n, int currCount) {
            CustomReturnType x = new CustomReturnType();
            x.node = n;
            x.count = currCount;
            return x;
        }
    }
    public CustomReturnType findTheNode(BinaryTreeNode node, int count, int currCount) {
        if (node == null) return CustomReturnType.initializeCustomReturnType(null, currCount);
        CustomReturnType n = findTheNode(node.right, count, currCount);
        if (n.node != null) return n;
        n.count++;
        if ( n.count == count) return CustomReturnType.initializeCustomReturnType(node,  n.count);
        return findTheNode(node.left, count, n.count);
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
        NthHighestNumberInBSTree obj = new NthHighestNumberInBSTree();
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
        int currCount = 0;
        BinaryTreeNode root = BinaryTree.createBST(origData);

        CustomReturnType node = obj.findTheNode(root, 4, currCount);

        System.out.println( node != null && node.node != null? node.node.data : node);
    }
}
