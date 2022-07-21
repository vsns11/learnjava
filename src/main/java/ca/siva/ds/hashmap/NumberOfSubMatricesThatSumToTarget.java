package ca.siva.ds.hashmap;

import java.util.HashMap;
import java.util.Map;

public class NumberOfSubMatricesThatSumToTarget {

     //Leetcode: 1074, Time: O(R^2 * C), Space: O(R * C)
    class Solution {
        public int numSubmatrixSumTarget(int[][] matrix, int target) {
            // Calculate Prefix Sum
            int[][] pref = new int[matrix.length + 1][matrix[0].length + 1];

            for (int i = 1; i < matrix.length + 1; ++i) {
                for (int j = 1; j < matrix[0].length + 1; ++j) {
                    pref[i][j] = pref[i - 1][j] + pref[i][j - 1] - pref[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }

            int totalCount = 0;
            Map<Integer, Integer> map = new HashMap<>();

            for (int r1 = 1; r1 < matrix.length + 1; ++r1) {
                for (int r2 = r1; r2 < matrix.length + 1; ++r2) {
                    map.clear();
                    map.put(0, 1);
                    for (int c1 = 1; c1 < matrix[0].length + 1; ++c1) {
                        int actualVal = pref[r2][c1] - pref[r1 - 1][c1];
                        totalCount += map.getOrDefault(actualVal - target, 0);
                        map.put(actualVal, map.getOrDefault(actualVal, 0) + 1);
                    }


                }

            }


            return totalCount;
        }
    }
}
