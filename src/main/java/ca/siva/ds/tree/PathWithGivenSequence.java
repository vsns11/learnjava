package ca.siva.ds.tree;

// Time: O(n), Space O(n)
public class PathWithGivenSequence {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static boolean pathWithGivenSequence(TreeNode root, int[] sequence) {

        return pathWithGivenSequenceHelper(root, sequence.length, 0, sequence);
    }

    public static boolean pathWithGivenSequenceHelper(TreeNode root, int size, int idx, int[] sequence) {
        if (idx == size) return true;
        if (root != null && root.val == sequence[idx]) {
            return pathWithGivenSequenceHelper(root.left, size, idx + 1, sequence) ||
                    pathWithGivenSequenceHelper(root.right, size, idx + 1, sequence);
        } else
            return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path sequence: " + PathWithGivenSequence.pathWithGivenSequence(root, new int[] { 1, 0, 7 }));
        System.out.println("Tree has path sequence: " + PathWithGivenSequence.pathWithGivenSequence(root, new int[] { 1, 1, 6 }));

    }
}
