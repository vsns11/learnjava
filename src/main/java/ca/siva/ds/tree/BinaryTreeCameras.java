package ca.siva.ds.tree;

/**
 * leetcode: 968, Time: O(N), Space: O(H), h is the height of a tree
 */

public class BinaryTreeCameras {
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

    public int minCameraCover(TreeNode root) {
        int[] result = minCameraCoverHelper(root);
        return Math.min(result[1], result[2]);
    }

    /**
     * 3 states:
     * 0 - Strict - Current node is not covered, but children do.
     * 1 - Normal - Current node is covered and its children too, but no camera at this node
     * 2 - Placed camera - Current node is covered and its children too, camera is placed.
     **/
    public int[] minCameraCoverHelper(TreeNode node) {
        if (null == node) {
            return new int[]{0, 0, 999};
        }
        int[] left = minCameraCoverHelper(node.left);
        int[] right = minCameraCoverHelper(node.right);

        int minLeftCamCount = Math.min(left[1], left[2]);
        int minRightCamCount = Math.min(right[1], right[2]);

        int currStrict = left[1] + right[1];
        int currNormal = Math.min(left[2] + minRightCamCount, right[2] + minLeftCamCount);
        int currPlaced = 1 + Math.min(left[0], minLeftCamCount) + Math.min(right[0], minRightCamCount);
        return new int[]{currStrict, currNormal, currPlaced};
    }
}