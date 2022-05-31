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

        /**
         * Time: O(N), Space: O(2^k)
         */
        class Solution2 {
            // Rolling hash approach
            public boolean hasAllCodes(String s, int k) {
                int totalCount = 1 << k;
                int allOnes = totalCount - 1;
                int hash = 0;
                boolean[] cache = new boolean[totalCount];

                for (int i = 0; i < s.length(); ++i) {
                    char ch = s.charAt(i);
                    hash = ((hash << 1) & allOnes) | ch - '0';
                    if ( i + 1 >= k && !cache[hash]) {
                        cache[hash] = true;
                        totalCount--;
                        if (totalCount == 0) return true;
                    }
                }

                return false;

            }


        }

    }
}
