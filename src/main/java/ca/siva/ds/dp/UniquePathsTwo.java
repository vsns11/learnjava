package ca.siva.ds.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode: 63,
 */
public class UniquePathsTwo {
    /**
     * Time: O(m*n), Space: O(m + n)
     */
    class Solution1 {

        public int[][] directions = new int[][]{{0, 1}, {1, 0}};


        public int doDfs(int row, int col, int[][] grid, Map<String, Integer> visited) {
            String idx = String.valueOf(row) + "_" + String.valueOf(col);
            if (visited.get(idx) != null) {
                return visited.get(idx);
            }
            if (row == 0 && col == 0 && grid[row][col] == 1) return 0;

            if (row == grid.length - 1 && col == grid[0].length - 1 && grid[row][col] == 0) {
                return 1;
            }

            int allPathCount = 0;

            for (int[] dist : directions) {
                int nxtRow = row + dist[0], nxtCol = col + dist[1];
                if (nxtRow < grid.length && nxtCol < grid[0].length && grid[nxtRow][nxtCol] == 0) {
                    allPathCount += this.doDfs(nxtRow, nxtCol, grid, visited);
                }
            }

            visited.put(idx, allPathCount);
            return allPathCount;
        }

        public int uniquePathsWithObstacles(int[][] obstacleGrid) {

            return doDfs(0, 0, obstacleGrid, new HashMap<String, Integer>());
        }


    }


}
