package ca.siva.ds.graph;

public class CountSubIslands {
    //Leetcode: 1905, Time: O(M*N), Space: O(M*N)
    class Solution {
        public boolean countSubIslandsHelper(int[][] grid1, int[][] grid2, int sr, int sc, int l, int c) {
            if (sr < 0 || sc < 0 || sr >=l || sc >= c || grid2[sr][sc] == 0) return true;
            if (grid2[sr][sc] != grid1[sr][sc]) return false;
            grid2[sr][sc] = 0;
            boolean left = countSubIslandsHelper(grid1,  grid2, sr - 1, sc, l, c);
            boolean right = countSubIslandsHelper(grid1, grid2, sr + 1, sc, l, c);
            boolean down = countSubIslandsHelper(grid1,  grid2, sr,    sc + 1, l, c);
            boolean up = countSubIslandsHelper(grid1, grid2, sr, sc - 1, l, c);
            return left && right && up && down;
        }

        public int countSubIslands(int[][] grid1, int[][] grid2) {
            int result = 0;
            int l = grid1.length, c = grid1[0].length;
            for (int i = 0; i < l; ++i) {
                for (int j = 0; j < c; ++j) {
                    if (grid2[i][j] == 1) {
                        result += (countSubIslandsHelper(grid1,  grid2, i, j, l, c) == true ? 1 : 0);
                    }
                }
            }
            return result;
        }


    }
}
