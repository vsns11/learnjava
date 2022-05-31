package ca.siva.ds.bitmanipulation;

import java.util.HashSet;
import java.util.Set;

public class StringContainsBinaryCodesOfSizeK {
    class Solution1 {
        /**
         * Leetcode: 146, Time: O(N * K), Space: O(N * K)
         */
        public boolean hasAllCodes(String s, int k) {
            int possibleSeqCount = 1 << k;
            Set<String> cache = new HashSet<String>();

            for (int i = k; i <= s.length(); ++i) {
                String subStr = s.substring(i - k, i);
                if (!cache.contains(subStr)) {
                    cache.add(subStr);
                    possibleSeqCount--;
                    if (possibleSeqCount == 0) return true;
                }

            }
            return false;
        }


    }
}
