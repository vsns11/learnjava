package ca.siva.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode Q.117
 */
public class PopulatingNextPointers {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    class Solution {
        public Node connect(Node root) {
            if (root == null) return null;
            Queue<Node> queue1 = new LinkedList<>(), queue2 = new LinkedList<>();
            Node ans = root;
            queue1.add(root);

            while (!queue1.isEmpty() || !queue2.isEmpty()) {
                Node curr = null;
                while (!queue1.isEmpty()) {
                    Node e = queue1.poll();
                    if (e.left != null) queue2.add(e.left);
                    if (e.right != null) queue2.add(e.right);

                    if (curr == null) {
                        curr = e;
                    } else {
                        curr.next = e;
                        curr = e;
                    }
                }
                if (curr != null) {
                    curr.next = null;
                    curr = curr.next;
                }

                while (!queue2.isEmpty()) {
                    Node e = queue2.poll();
                    if (e.left != null) queue1.add(e.left);
                    if (e.right != null) queue1.add(e.right);

                    if (curr == null) {
                        curr = e;
                    } else {
                        curr.next = e;
                        curr = e;
                    }
                }

                if (curr != null) {
                    curr.next = null;
                    curr = curr.next;
                }
            }

            return ans;

        }
    }
}
