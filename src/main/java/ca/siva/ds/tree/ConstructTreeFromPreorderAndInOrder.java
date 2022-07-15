package ca.siva.ds.tree;

import java.util.HashMap;
import java.util.Map;

// Leet: 105, Time: O(N), Space: O(N)
public class ConstructTreeFromPreorderAndInOrder {
    class Solution {
        Map<Integer, Integer> indexer = new HashMap<>();
        int preOrderIdx = 0;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            for (int idx = 0; idx < inorder.length; ++idx) {
                indexer.put(inorder[idx], idx);
            }
            return buildTreeHelper(0, inorder.length - 1, preorder);
        }


        public TreeNode buildTreeHelper(int left, int right, int[] preorder) {
            if (left > right) return null;
            TreeNode node = new TreeNode(preorder[preOrderIdx]);
            int r = indexer.get(preorder[preOrderIdx]) - 1, l = indexer.get(preorder[preOrderIdx]) + 1;
            preOrderIdx += 1;
            node.left = buildTreeHelper(left, r, preorder);
            node.right = buildTreeHelper(l, right, preorder);
            return node;
        }
    }
}
