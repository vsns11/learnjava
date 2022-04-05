package ca.siva.ds.trees;

import java.util.ArrayList;

// Time Complexity: O(logn), space: O(1)
public class NextInorderSuccessor {
    public Integer getMinInRightTree(BinaryTreeNode node) {
        if (node == null) return null;
        if (node.left == null) {
            return node.data;
        }
        return getMinInRightTree(node.left);
    }

    public Integer getSuccessor(Integer ref, BinaryTreeNode root) {
        BinaryTreeNode parent = null, node = root;
        while (node != null) {
            if (node.data == ref) {
                if (node.right != null) {
                    return getMinInRightTree(node.right);
                }
                else {

                    if (parent == null) {
                        return null;
                    }
                    else if (parent.left == node) {
                        return parent.left.data;
                    } else {
                        BinaryTreeNode parentNode = parent, curr = node;

                        while (parentNode != null && parentNode.left != curr) {
                            curr = curr.parent;
                            parentNode = parentNode.parent;
                        }

                        return parentNode == null? null: parentNode.data ;
                    }
                }
            } else if (node.data < ref) {
                parent = node;
                node = node.right;
            } else {
                parent = node;
                node = node.left;
            }
        }
        return null;
    }


    public static void main(String[] args) {

        /**
         *          10
         *      6       12
         *    3  7      15  16
         *         8
         *           9
         * o/p: 0, 1, 2, 3, 4, 5, 6
         */
        ArrayList<Integer> origData = new ArrayList<Integer>();
        origData.add(10);origData.add(6);origData.add(12);origData.add(3);origData.add(11);
        origData.add(16);origData.add(7);origData.add(8);origData.add(9);

        BinaryTreeNode root = BinaryTree.createBST(origData);
        ArrayList<Integer> result = new ArrayList<>();
        BinaryTree.getInorder(root, result);
        BinaryTree.anotherDisplayTree(root);
        NextInorderSuccessor obj = new NextInorderSuccessor();
        System.out.println(obj.getSuccessor(9, root));

    }

}
