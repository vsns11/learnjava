package ca.siva.ds.array;

import java.util.HashMap;
import java.util.Map;

//Leetcode Q.128, Time: O(N), Space: O(N)
public class LargestConsecutiveSequence {
    class Solution {
        public int longestConsecutive(int[] nums) {


            Map<Integer, Boolean> visited = new HashMap<>();
            int maxResult = 0;

            for (int i : nums) {
                visited.put(i, false);
            }

            for (int i : nums) {
                if (visited.get(i) == true) continue;

                int l = 0, r = 0, p = i + 1, q = i - 1;

                while (visited.containsKey(p)) {
                    l++;
                    visited.put(p, true);
                    p++;

                }

                while (visited.containsKey(q)) {
                    r++;
                    visited.put(q, true);
                    q--;
                }

                maxResult = Math.max(maxResult, l + r + 1);
            }

            return maxResult;

        }
    }
}
