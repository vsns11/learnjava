package ca.siva.ds.graph;

import java.util.LinkedList;
import java.util.Queue;

public class MaxAreaOfIsland {
    //Leetcode: 695, Time: O(M * N), Space: O(min(m. n))
    class Solution {
        public int maxAreaOfIsland(int[][] grid) {
            int maxArea = 0;
            boolean[][] visited = new boolean[grid.length][grid[0].length];

            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
                    if (!visited[i][j] && grid[i][j] == 1) {
                        maxArea = Math.max(maxArea, numIslandsHelper(grid, i, j, visited));
                    }
                }
            }

            return maxArea;
        }

        public int numIslandsHelper(int[][] grid, int sr, int sc, boolean[][] visited) {

            int l = grid.length, col = grid[0].length;

            Queue<int[]> queue = new LinkedList<>();

            queue.offer(new int[]{sr, sc});
            int area = 0;
            while (!queue.isEmpty()) {

                int[] val = queue.remove();
                int currRow = val[0], currCol = val[1];
                if (visited[currRow][currCol]) continue;

                area++;

                visited[currRow][currCol] = true;

                if (currRow + 1 < l && grid[currRow + 1][currCol] == 1 && !visited[currRow + 1][currCol]) {
                    queue.offer(new int[]{currRow + 1, currCol});
                }
                if (currRow - 1 >= 0 && grid[currRow - 1][currCol] == 1 && !visited[currRow - 1][currCol]) {
                    queue.offer(new int[]{currRow - 1, currCol});
                }
                if (currCol + 1 < col && grid[currRow][currCol + 1] == 1 && !visited[currRow][currCol + 1]) {
                    queue.offer(new int[]{currRow, currCol + 1});
                }
                if (currCol - 1 >= 0 && grid[currRow][currCol - 1] == 1 && !visited[currRow][currCol - 1]) {
                    queue.offer(new int[]{currRow, currCol - 1});
                }

            }
            return area;
        }
    }
}
