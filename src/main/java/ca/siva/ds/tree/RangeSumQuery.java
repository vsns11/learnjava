package ca.siva.ds.tree;

// Leetcode: 307
public class RangeSumQuery {
    class NumArray {
        int[] tree;
        int l;
        //Time: O(n), Space: O(n)
        public NumArray(int[] nums) {
            if (nums != null) {
                l = nums.length;
                tree = new int[2 * nums.length];
                for (int i = l, j = 0; i < 2*l; ++i, ++j) {
                    tree[i] = nums[j];
                }
                for (int i = l - 1; i > 0; --i) {
                    tree[i] = tree[2 * i] + tree [2 * i + 1];
                }
            }
        }
        // Time: O(log n), Space: O(1)
        public void update(int index, int val) {
            // Find the leaf node of that index and update it with the value, because leaf nodes contain the actual node values
            int idx = index + l;
            tree[idx] = val;

            while (idx > 0) {
                int left = idx, right = idx;
                if (idx % 2 == 0) {
                    right++;
                }
                if (idx % 2 == 1) {
                    left--;
                }
                tree[idx / 2] = tree[left] + tree[right];
                idx  /= 2;
            }
        }

        // Time: O(logn), Space: O(1)
        public int sumRange(int left, int right) {
            int l = left + this.l, r = right + this.l;
            int sum = 0;
            while (l <= r) {
                if ((l % 2) == 1) {
                    sum += tree[l];
                    l++;
                }
                if ((r % 2) == 0) {
                    sum += tree[r];
                    r--;
                }
                l /= 2;
                r /= 2;
            }
            return sum;
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */


}
