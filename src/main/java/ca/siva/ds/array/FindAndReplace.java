package ca.siva.ds.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAndReplace {
    //Leetcode: 890, Time: O(N*K), Space: O(N*K)
    class Solution1 {
        public List<String> findAndReplacePattern(String[] words, String pattern) {
            List<String> result = new ArrayList<>();
            for (String word: words) {
                if (isPatternMatches(word, pattern)) {
                    result.add(word);
                }
            }
            return result;
        }

        public boolean isPatternMatches(String word, String pattern) {
            int l = word.length(), p = pattern.length();
            if (l != p) return false;
            Map<Character, Character> m1 = new HashMap<>();
            Map<Character, Character> m2 = new HashMap<>();

            for (int i = 0; i < l; ++i) {
                char x = word.charAt(i), y = pattern.charAt(i);
                m1.putIfAbsent(x, y);
                m2.putIfAbsent(y, x);
                if (m1.get(x) != y || m2.get(y) != x) return false;

            }
            return true;
        }

    }

    class Solution2 {
        public List<String> findAndReplacePattern(String[] words, String pattern) {
            List<String> ans = new ArrayList();
            for (String word: words)
                if (match(word, pattern))
                    ans.add(word);
            return ans;
        }

        public boolean match(String word, String pattern) {
            Map<Character, Character> M = new HashMap();
            for (int i = 0; i < word.length(); ++i) {
                char w = word.charAt(i);
                char p = pattern.charAt(i);
                if (!M.containsKey(w)) M.put(w, p);
                if (M.get(w) != p) return false;
            }

            boolean[] seen = new boolean[26];
            for (char p: M.values()) {
                if (seen[p - 'a']) return false;
                seen[p - 'a'] = true;
            }
            return true;
        }
    }
}
