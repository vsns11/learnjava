package ca.siva.ds.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class ConvertNaryTreeToBinaryTree {

    class Pair<U, V> {
        public U first;
        public V second;

        public Pair(U f, V s) {
            this.first = f;
            this.second = s;
        }

    }

    // Encodes an n-ary tree to a binary tree.
    // Time: O(n), Space: O(l), l:- Max level size.
    public TreeNode encode(Node root) {
        if (root == null) return null;
        Queue<Pair<TreeNode, Node>> queue = new LinkedList<>();
        TreeNode newRoot = new TreeNode(root.val);
        queue.add(new Pair<>(newRoot, root));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Node> pair = queue.poll();
            Node curr = pair.second;
            TreeNode newNode = pair.first, head = null, tail = null;
            for(Node child: curr.children) {
                TreeNode newChild = new TreeNode(child.val);
                if (tail == null) {
                    head = newChild;
                } else {
                    tail.right = newChild;
                }
                tail = newChild;
                queue.add(new Pair<>(newChild, child));
            }
            newNode.left = head;
        }

        return newRoot;
    }

    // Decodes your binary tree to an n-ary tree.
    // Time: O(n), Space: O(l), l:- Max level size.
    public Node decode(TreeNode root) {
        if (root == null) return null;
        Node oldRoot = new Node(root.val, new ArrayList<Node>());
        Queue<Pair<TreeNode, Node>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, oldRoot));

        while ( !queue.isEmpty() ) {
            Pair<TreeNode, Node> pair = queue.poll();
            TreeNode tnode = pair.first.left;
            Node curr = pair.second;
            while (tnode != null) {
                Node child = new Node(tnode.val, new ArrayList<Node>());
                curr.children.add(child);
                queue.add(new Pair<>(tnode, child));
                tnode = tnode.right;
            }

        }

        return oldRoot;

    }
}
