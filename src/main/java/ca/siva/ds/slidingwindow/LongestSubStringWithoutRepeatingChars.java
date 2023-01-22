package ca.siva.ds.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubStringWithoutRepeatingChars {

    // Approach #1, Time: O(N^3), Space: O(N)
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        for (int start = 0; start < s.length(); ++start) {
            for (int end = start; end < s.length(); ++end) {
                if (isStringContainsUniqueChars(s, start, end)) {
                    result = Math.max(result, end - start + 1);
                }
            }
        }
        return result;
    }

    public boolean isStringContainsUniqueChars(String s, int start, int end) {
        Set<Character> visited = new HashSet<>();
        for ( ;start <= end; start++){
            if (visited.contains(s.charAt(start))) {
                return false;
            }
            visited.add(s.charAt(start));
        }
        return true;
    }

    class Solution {
        // Approach: 2, Time :O(N), Space: O(M), M is the size of the charset
        public int lengthOfLongestSubstringApproach2(String s) {
            Map<Character, Integer> cntMap = new HashMap<>();
            int left = 0, right = 0, result = 0;

            while (right < s.length()) {
                Character ch = s.charAt(right);
                cntMap.put(ch, cntMap.getOrDefault(ch, 0) + 1);

                while (cntMap.get(ch) > 1) {
                    cntMap.put(s.charAt(left), cntMap.get(s.charAt(left)) - 1);
                    left++;
                }
                result = Math.max(result, right - left + 1);
                right++;
            }
            return result;
        }

    }

    class Solution3 {
        //Approach: 3
        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> idxMap = new HashMap<>();

            int l = 0, result = 0;

            for (int r = 0; r < s.length(); ++r) {
                if (idxMap.containsKey(s.charAt(r))) {
                    l = Math.max(idxMap.get(s.charAt(r)), l);
                }
                result = Math.max(result, r - l + 1);
                idxMap.put(s.charAt(r), r + 1);
            }
            return result;
        }
    }

}
