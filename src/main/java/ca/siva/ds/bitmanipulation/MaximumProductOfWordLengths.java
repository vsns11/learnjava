package ca.siva.ds.bitmanipulation;

/**
 * Leetcode: 318, Time: O(L1 * L2), Space: O(1)
 */
public class MaximumProductOfWordLengths {
    class Solution {
        public boolean noCommonMatch(String w1, String w2) {
            int mask1 = 0, mask2 = 0;
            for (char c1 : w1.toCharArray()) {
                mask1 |= 1 << c1 - 'a';
            }
            for (char c2 : w2.toCharArray()) {
                mask2 |= 1 << c2 - 'a';
            }
            return (mask1 & mask2) == 0;
        }

        public int maxProduct(String[] words) {
            int maxResult = 0;
            for (int i = 0; i < words.length - 1; ++i) {
                for (int j = i + 1; j < words.length; ++j) {
                    if (noCommonMatch(words[i], words[j])) {
                        int l = words[i].length(), r = words[j].length();
                        maxResult = Math.max(l * r, maxResult);
                    }
                }
            }
            return maxResult;
        }

    }
}
