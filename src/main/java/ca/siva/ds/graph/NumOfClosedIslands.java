package ca.siva.ds.graph;

public class NumOfClosedIslands {
    //Leetcode: 1254, Time: (M *N), Space: O( M *N )
    class Solution {
        public void dfs(int[][] grid, boolean[][]visited, int x, int y, int l, int c) {

            if (x < 0 || x == l || y < 0 || y == c || visited[x][y] || grid[x][y] == 1) return;

            visited[x][y] = true;
            dfs(grid, visited, x + 1, y, l, c);
            dfs(grid, visited, x, y + 1, l, c);
            dfs(grid, visited, x - 1, y, l, c);
            dfs(grid, visited, x, y - 1, l, c);
        }

        public int closedIsland(int[][] grid) {
            //1. Remove all the islands those are connected to the edges.
            // 2. Next, do a flood fill on the remaining islands and consider the count.

            //step 1:
            int l = grid.length, c = grid[0].length;

            boolean[][] visited = new boolean[l][c];

            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
                    if ((i == 0 || j == 0 || i == l - 1 || j == c - 1) && grid[i][j] == 0 && !visited[i][j]){
                        dfs(grid, visited, i, j, l, c);
                    }
                }
            }

            int closedIslandCnt = 0;
            for (int i = 1; i < l - 1; ++i) {
                for (int j = 1; j < c - 1; ++j) {
                    if (grid[i][j] == 0 && !visited[i][j]){
                        dfs(grid, visited, i, j, l, c);
                        closedIslandCnt++;
                    }
                }
            }


            return closedIslandCnt;
        }
    }
}
