package ca.siva.ds.tree;

import java.util.*;

public class BinaryTreeRightSideView {
    //Leetcode:199, Time: O(N), Space: O(H)
    class Solution1 {
        public Set<Integer> visitedLvl = new HashSet<>();

        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            rightSideViewHelper(root, result, 0);
            return result;
        }

        public void rightSideViewHelper(TreeNode root, List<Integer> result, int idx) {
            if (root == null) return;
            if (!visitedLvl.contains(idx))
                result.add(root.val);
            visitedLvl.add(idx);
            rightSideViewHelper(root.right, result, idx + 1);
            rightSideViewHelper(root.left, result, idx + 1);

        }
    }

    //Time: O(N), Space: O(H)
    class Solution2 {
        public List<Integer> rightSideView(TreeNode root) {
            if (root == null) return new ArrayList<Integer>();


            ArrayDeque<TreeNode> nextLevel = new ArrayDeque() {{
                offer(root);
            }};
            ArrayDeque<TreeNode> currLevel = new ArrayDeque();
            List<Integer> rightside = new ArrayList();

            TreeNode node = null;
            while (!nextLevel.isEmpty()) {
                // prepare for the next level
                currLevel = nextLevel.clone();
                nextLevel.clear();

                while (!currLevel.isEmpty()) {
                    node = currLevel.poll();

                    // add child nodes of the current level
                    // in the queue for the next level
                    if (node.left != null)
                        nextLevel.offer(node.left);
                    if (node.right != null)
                        nextLevel.offer(node.right);
                }

                // The current level is finished.
                // Its last element is the rightmost one.
                // if (currLevel.isEmpty())
                rightside.add(node.val);
            }
            return rightside;
        }
    }
}
