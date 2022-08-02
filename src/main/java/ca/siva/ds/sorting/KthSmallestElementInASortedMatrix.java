package ca.siva.ds.sorting;

import java.util.Collections;
import java.util.PriorityQueue;

//Leetcode: 378
public class KthSmallestElementInASortedMatrix {
    static class Node {
        int val;
        int sr;
        int sc;

        public Node(int val, int sr, int sc) {
            this.val = val;
            this.sr = sr;
            this.sc = sc;
        }
    }

    class Solution {
        // Time: O(Min(K, N) + K*log(Min(K, N))), Space: O(Min(K, N))
        // N: Number of rows in the matrix
        public int kthSmallest(int[][] matrix, int k) {
            int numOfRows = Math.min(matrix.length, k);
            PriorityQueue<Node> minHeap = new PriorityQueue<>(numOfRows, (a, b) -> a.val - b.val);

            for (int i = 0; i < numOfRows; ++i) {
                minHeap.offer(new Node(matrix[i][0], i, 0));
            }
            Node ans = null;
            while (k-- > 0) {
                ans = minHeap.poll();
                if (ans.sc + 1 < matrix[0].length) {
                    Node nxtNode = new Node(matrix[ans.sr][ans.sc + 1], ans.sr, ans.sc + 1);
                    minHeap.offer(nxtNode);
                }
            }
            return ans.val;
        }
    }
}
