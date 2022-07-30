package ca.siva.ds.string;

import java.util.ArrayList;
import java.util.List;

// Leetcode: 916, Time: O(M + N), Space: O(M + N)
public class WordSubsets {
    class Solution {
        public List<String> wordSubsets(String[] words1, String[] words2) {
            int cnt[] = new int[26];
            for (int i = 0; i < words2.length; ++i) {
                int[] cal = count(words2[i]);
                for (int j = 0; j < 26; ++j) {
                    cnt[j] = Math.max(cal[j], cnt[j]);
                }
            }

            List<String> result = new ArrayList<>();

            label: for (int i = 0; i < words1.length; ++i) {
                int cnt2[] = count(words1[i]);
                for (int j = 0; j < 26; ++j) {
                    if (cnt2[j] < cnt[j]) continue label;
                }
                result.add(words1[i]);
            }

            return result;

        }

        public int[] count(String word) {
            int[] cnt = new int[26];
            for (char x : word.toCharArray()) {
                cnt[x - 'a']++;
            }
            return cnt;
        }
    }
}
