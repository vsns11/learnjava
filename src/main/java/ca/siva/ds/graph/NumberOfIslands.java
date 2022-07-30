package ca.siva.ds.graph;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    //Leetcode: Time: O(M*N), Space: O(min(M, N))
    class Solution {

        public int numIslands(char[][] grid) {
            int count = 0;
            boolean[][] visited = new boolean[grid.length][grid[0].length];

            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
                    if (!visited[i][j] && grid[i][j] == '1') {
                        count += numIslandsHelper(grid, i, j, visited);
                    }
                }
            }

            return count;
        }


        public int numIslandsHelper(char[][] grid, int sr, int sc, boolean[][] visited) {

            int l = grid.length, col = grid[0].length;

            Queue<int[]> queue = new LinkedList<>();

            queue.offer(new int[]{sr, sc});

            while(!queue.isEmpty()) {

                int[] val = queue.remove();
                int currRow = val[0], currCol = val[1];
                if (visited[currRow][currCol]) continue;

                visited[currRow][currCol] = true;

                if (currRow + 1 < l && grid[currRow + 1][currCol] == '1' && !visited[currRow + 1][currCol]) {
                    queue.offer(new int[]{currRow + 1, currCol});
                }
                if (currRow - 1 >= 0 && grid[currRow - 1][currCol] == '1' && !visited[currRow - 1][currCol]) {
                    queue.offer(new int[]{currRow - 1, currCol});
                }
                if (currCol + 1 < col && grid[currRow][currCol + 1] == '1' &&  !visited[currRow][currCol + 1]) {
                    queue.offer(new int[]{currRow, currCol + 1});
                }
                if (currCol - 1 >= 0 && grid[currRow][currCol - 1] == '1' &&  !visited[currRow][currCol - 1]) {
                    queue.offer(new int[]{currRow, currCol - 1});
                }

            }
            return 1;
        }

    }
}
