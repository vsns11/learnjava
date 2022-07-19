package ca.siva.ds.dp;

import java.util.ArrayList;
import java.util.List;

//Leetcode: 118, Time: O(NumOfRows^2), Space: O(1)
public class PascalTriangle {
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(List.of(1));

            for (int i = 1; i < numRows; ++i) {
                List<Integer> prevRow = result.get(i - 1);
                List<Integer> currRow = new ArrayList<>();
                currRow.add(1);
                for (int j = 1; j < i; ++j) {
                    currRow.add(prevRow.get(j - 1) + prevRow.get(j));
                }
                currRow.add(1);
                result.add(currRow);
            }

            return result;
        }
    }
}
