//package ca.siva.ds.trees;
//import j
//
//import java.io.Serializable;
//import java.util.ArrayList;
//
//
//public class BinaryTreeToDllConversion {
//    Serializable
//    public BinaryTreeNode performConversion(BinaryTreeNode node) {
//        if (node == null) return  null;
//        node.left = performConversion(node.left);
//        node.right = performConversion(node.right);
//        return node.right == null ? node : node.right;
//    }
//
//    public static void main(String[] args) {
//        ArrayList<Integer> origData = new ArrayList<Integer>();
//        origData.add(10);
//        origData.add(6);
//        origData.add(12);
//        origData.add(3);
//        origData.add(11);
//        origData.add(16);
//        origData.add(7);
//        origData.add(8);
//        origData.add(9);
//
//        BinaryTreeNode root = BinaryTree.createBST(origData);
//        BinaryTreeToDllConversion obj = new BinaryTreeToDllConversion();
//        obj.performConversion(root);
//    }
//}
