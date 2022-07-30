package ca.siva.ds.graph;

// Leetcode: 733, Time: O(N), Space: O(N)
public class FloodFill {
    class Solution {
        public int[][] floodFill(int[][] image, int sr, int sc, int color) {

            if (image[sr][sc] != color) {

                floodFillHelper(image, sr, sc, color, image.length, image[0].length);
            }

            return image;
        }

        public void floodFillHelper(int[][] image, int sr, int sc, int color, int rows, int cols) {

            int tmp = image[sr][sc];
            image[sr][sc] = color;

            if (sr + 1 < rows && image[sr + 1][sc] == tmp) {
                floodFillHelper(image, sr + 1, sc, color, rows, cols);
            }
            if (sr - 1 >= 0 && image[sr - 1][sc] == tmp) {
                floodFillHelper(image, sr - 1, sc, color, rows, cols);
            }
            if (sc + 1 < cols && image[sr][sc + 1] == tmp) {
                floodFillHelper(image, sr, sc + 1, color, rows, cols);
            }
            if (sc - 1 >= 0 && image[sr][sc - 1] == tmp) {
                floodFillHelper(image, sr, sc - 1, color, rows, cols);
            }

        }


    }
}
