package ca.siva.ds.graph;

import java.util.HashMap;
import java.util.Map;

public class LongestIncreasingPathInaMatrix {

    // LeetCode: 329, Time: O(M * N), Space: O(M *N), Space can be improved by removing the visiting array
    class Solution {
        public int longestIncreasingPath(int[][] matrix) {
            Map<String, Integer> cache = new HashMap<>();
            boolean[][] visiting = new boolean[matrix.length][matrix[0].length];
            int result = 0, l = matrix.length, c = matrix[0].length;

            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 0; j < matrix[0].length; ++j) {
                    int calcPath = longestIncreasingPathHelper(matrix, i, j, l, c, cache, visiting, -1);
                    result = Math.max(result, calcPath);

                }
            }
            return result;
        }


        public int longestIncreasingPathHelper(int[][] matrix,
                                               int i, int j,
                                               int l, int c,
                                               Map<String, Integer> cache, boolean[][] visiting, int prev) {
            if (cache.containsKey(i +"-" +j) && (prev!= -1 && matrix[i][j] > prev) ) {
                return cache.get(i + "-" +j);
            }

            if ( i < 0 || i >= l || j < 0 || j >= c || (prev!= -1 && matrix[i][j] <= prev) || visiting[i][j]) return 0;

            visiting[i][j] = true;


            int left =  longestIncreasingPathHelper(matrix,  i,     j - 1, l, c, cache, visiting, matrix[i][j]);
            int right = longestIncreasingPathHelper(matrix,  i,     j + 1, l, c, cache, visiting, matrix[i][j]);
            int up =    longestIncreasingPathHelper(matrix,  i - 1,  j,    l, c, cache, visiting, matrix[i][j]);
            int down =  longestIncreasingPathHelper(matrix, i + 1,   j,    l, c, cache, visiting, matrix[i][j]);


            int maxPathSumCount = Math.max(Math.max(left, right), Math.max(up, down));
            // System.out.println(i+"-"+j+"="+maxPathSumCount);
            // System.out.println("left="+left+"right="+right+"up="+up+"down="+down);
            visiting[i][j] = false;
            cache.put(i + "-" +j, maxPathSumCount + 1);
            return maxPathSumCount + 1;
        }

    }

}
