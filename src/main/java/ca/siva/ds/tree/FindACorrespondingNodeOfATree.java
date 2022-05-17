package ca.siva.ds.tree;

import java.util.Deque;
import java.util.LinkedList;

public class FindACorrespondingNodeOfATree {
    /**
     * LeetCode: 1379, Time: O(n), Space: O(n)
     */
    class Solution {
        class Pair<U, V> {
            U u;
            V v;

            public Pair(U u, V v) {
                this.u = u;
                this.v = v;
            }

        }

        public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {

            Deque<TreeNode> queue = new LinkedList<>();
            queue.add(cloned);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.val == target.val) return node;

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            return null;
        }

    }
}
