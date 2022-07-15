package ca.siva.ds.graph;

//Leet: 696 Time:O(R*C), Space: O(R * C)
public class MaxAreaOfGraph {
    class Solution {
        public int maxAreaOfIsland(int[][] grid) {
            boolean visited[][] = new boolean[grid.length][grid[0].length];
            int result = 0;
            for (int i = 0; i < visited.length; ++i) {
                for (int j = 0; j < visited[0].length; ++j) {
                    if (!visited[i][j] && grid[i][j] == 1) {
                        int calcArea = maxAreaOfIslandHelper(grid, visited, i, j);
                        result = Math.max(calcArea, result);
                    }
                }
            }
            return result;
        }

        public int maxAreaOfIslandHelper(int[][] grid, boolean[][] visited, int row, int col) {
            int currArea = 1;

            int[][] nextPoints = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

            visited[row][col] = true;
            int l = grid.length, c = grid[0].length;

            for (int[] point : nextPoints) {
                int nxtRow = row + point[0], nxtCol = col + point[1];

                if (nxtRow < l && nxtCol < c && nxtRow >= 0 && nxtCol >= 0 && !visited[nxtRow][nxtCol] && grid[nxtRow][nxtCol] == 1) {
                    int calcArea = maxAreaOfIslandHelper(grid, visited, nxtRow, nxtCol);
                    currArea += calcArea;
                }
            }

            return currArea;
        }


    }
}
