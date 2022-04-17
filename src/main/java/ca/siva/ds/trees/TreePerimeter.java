package ca.siva.ds.trees;

import java.util.*;

// Time: O(n), Space: O(h) h => height of the tree
public class TreePerimeter {
    Map<Integer, Boolean> cache = new HashMap<>();

    public void calcTreePerimeter(BinaryTreeNode node, boolean left, ArrayList<Integer> result, int currIdx) {
        if (node == null) {
            return;
        }
        if (!cache.containsKey(currIdx) || (cache.containsKey(currIdx) && node.left == null && node.right == null) ) {
            result.add(node.data);
            cache.put(currIdx, true);
        }
        if (left) {
            calcTreePerimeter(node.left, left,  result, currIdx + 1);
            calcTreePerimeter(node.right, left, result, currIdx + 1);
        } else {
            calcTreePerimeter(node.right, left,  result, currIdx + 1);
            calcTreePerimeter(node.left, left, result, currIdx + 1);
        }
    }

    public ArrayList<Integer> executeCalcPerimeter(BinaryTreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        result.add(root.data);
        this.calcTreePerimeter(root.left, true, result, 1);
        this.cache.clear();
        this.calcTreePerimeter(root.right, false, result, 1);
        return result;
    }

    public static void main(String[] args) {
        TreePerimeter obj = new TreePerimeter();
        ArrayList<Integer> result = new ArrayList<>();
        List<Integer> arr = new ArrayList<Integer>();
        arr.add(100);
        arr.add(50);
        arr.add(200);
        arr.add(25);
        arr.add(60);
        arr.add(350);
        arr.add(10);
        arr.add(70);
        arr.add(400);


        BinaryTreeNode root = BinaryTree.createBST(arr);

        System.out.println(obj.executeCalcPerimeter(root));
    }
}
