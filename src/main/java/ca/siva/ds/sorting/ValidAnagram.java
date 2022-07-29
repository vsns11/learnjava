package ca.siva.ds.sorting;

/**
 * Leetcode: 242, Space: O(1), Time: O(N)
 */
public class ValidAnagram {
    class Solution {
        public boolean isAnagram(String s, String t) {
            int[] ref = new int[26];
            for (char x: s.toCharArray()) {
                ref[x - 'a']++;
            }

            for (char x: t.toCharArray()) {
                int val = ref[x - 'a'];
                if (val == 0) return false;
                ref[x - 'a']--;
            }

            for (int i = 0; i < 26; ++i) {
                if (ref[i] > 0) return false;
            }
            return true;
        }
    }
}
