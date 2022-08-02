package ca.siva.ds.graph;

public class NumberOfEnclaves {
    // Leetcode: 1020, Time: O(M*N), Space: O(M*N)
    class Solution {
        public int dfs(int[][]grid, int sr, int sc, int l, int c) {
            if (sr >= l  || sr < 0 || sc >= c || sc < 0 || grid[sr][sc] == 0) return 0;
            grid[sr][sc] = 0;
            int left   =  dfs(grid, sr,      sc - 1, l, c);
            int right  =  dfs(grid, sr,      sc + 1, l, c);
            int up     =  dfs(grid, sr - 1,  sc,     l, c);
            int down   =  dfs(grid, sr + 1,  sc,     l, c);

            return 1 + left + right + up + down;
        }


        public int numEnclaves(int[][] grid) {
            int l = grid.length, c = grid[0].length;
            for (int i = 0; i < l; ++i) {
                for (int j = 0; j < c; ++j) {
                    if ((i == 0 || i == l-1 || j == 0 || j == c-1) && grid[i][j] == 1) {
                        dfs(grid, i, j, l, c);
                    }
                }
            }

            int result = 0;
            for (int i = 0; i < l; ++i) {
                for (int j = 0; j < c; ++j) {
                    if (grid[i][j] == 1) {
                        result += dfs(grid, i, j, l, c);
                    }
                }
            }
            return result;

        }
    }

}
