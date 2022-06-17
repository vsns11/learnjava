package ca.siva.ds.string;

/**
 * Leetcode: 5, Time O(N^2), Space: O(1)
 */
public class LongestPalindromicSubString {
    public String longestPalindrome(String s) {
        if (s.length() < 2) return s;

        int maxLen = 0, strLen = s.length();
        int[] resultIdx = new int[]{0, 0};
        for (int i = 0; i < s.length(); ++i) {
            // odd length;
            int idx1 = i - 1, idx2 = i + 1;
            while (idx1 >= 0 && idx2 < strLen && s.charAt(idx1) == s.charAt(idx2)) {
                idx1 -= 1;
                idx2 += 1;
            }
            int calcOddLen = idx2 - idx1 + 1;
            if (calcOddLen > maxLen) {
                maxLen = calcOddLen;
                resultIdx[0] = idx1 + 1;
                resultIdx[1] = idx2 - 1;
            }

            int idx3 = i, idx4 = i + 1;
            while (idx3 >= 0 && idx4 < strLen && s.charAt(idx3) == s.charAt(idx4)) {
                idx3 -= 1;
                idx4 += 1;
            }

            int calcEvenLen = idx4 - idx3 + 1;
            if (calcEvenLen > maxLen) {
                maxLen = calcEvenLen;
                resultIdx[0] = idx3 + 1;
                resultIdx[1] = idx4 - 1;
            }

        }
        return s.substring(resultIdx[0], resultIdx[1] + 1);

    }
}
