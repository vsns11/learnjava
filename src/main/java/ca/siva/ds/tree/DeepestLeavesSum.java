package ca.siva.ds.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 *Leet: 1302
 */
public class DeepestLeavesSum {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Pair<U, V> {
        U item;
        V lvl;

        public Pair(U item, V lvl) {
            this.item = item;
            this.lvl = lvl;
        }

        public U getKey() {
            return this.item;
        }

        public V getValue() {
            return this.lvl;
        }
    }

    /**
     * Time: O(n + h) => O(n), space: O(n)
     */
    class Solution1 {


        public int getHeight(TreeNode root) {
            if (root == null || (root.left == null && root.right == null)) return 0;
            return Math.max(1 + getHeight(root.left), 1 + getHeight(root.right));
        }


        public int deepestLeavesSum(TreeNode root) {
            if (root == null) return 0;
            int h = getHeight(root);
            Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
            int result = 0;
            queue.offer(new Pair(root, 0));

            while (!queue.isEmpty()) {
                Pair<TreeNode, Integer> node = queue.poll();
                if (node.lvl == h) result += node.item.val;
                if (node.item.left != null) {
                    queue.offer(new Pair(node.item.left, node.lvl + 1));
                }
                if (node.item.right != null) {
                    queue.offer(new Pair(node.item.right, node.lvl + 1));
                }
            }

            return result;
        }


    }

    /**
     * Time: O(n), space: O(h)
     */
    class Solution2 {
        public int deepestLeavesSum(TreeNode root) {
            int deepestSum = 0, depth = 0, currDepth;
            Deque<Pair<TreeNode, Integer>> queue = new ArrayDeque();
            queue.offer(new Pair(root, 0));

            while (!queue.isEmpty()) {
                Pair<TreeNode, Integer> p = queue.poll();
                root = p.getKey();
                currDepth = p.getValue();

                if (root.left == null && root.right == null) {
                    // if this leaf is the deepest one seen so far
                    if (depth < currDepth) {
                        deepestSum = root.val;      // start new sum
                        depth = currDepth;          // note new depth
                    } else if (depth == currDepth) {
                        // if there were already leaves at this depth
                        deepestSum += root.val;     // update existing sum
                    }
                } else {
                    if (root.left != null) {
                        queue.offer(new Pair(root.left, currDepth + 1));
                    }
                    if (root.right != null) {
                        queue.offer(new Pair(root.right, currDepth + 1));
                    }
                }
            }
            return deepestSum;
        }
    }
}
