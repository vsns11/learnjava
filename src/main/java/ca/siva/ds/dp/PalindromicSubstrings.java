package ca.siva.ds.dp;

/**
 * Leetcode: 647
 */
public class PalindromicSubstrings {
    /**
     * Time: O(N^3), Space: O(1)
     */
    class Solution1 {
        private boolean isPalindrome(String s, int start, int end) {
            while (start < end) {
                if (s.charAt(start) != s.charAt(end))
                    return false;

                ++start;
                --end;
            }

            return true;
        }

        public int countSubstrings(String s) {
            int ans = 0;

            for (int start = 0; start < s.length(); ++start)
                for (int end = start; end < s.length(); ++end)
                    ans += isPalindrome(s, start, end) ? 1 : 0;

            return ans;
        }
    }

    /**
     * Time: O(N^2), Space: O(N^2)
     */
    class Solution2 {
        public int countSubstrings(String s) {
            int n = s.length(), result = 0;
            boolean dp[][] = new boolean[n][n];
            // length 1 is always a palindrome
            for (int i = 0; i < n; ++i, ++result) {
                dp[i][i] = true;
            }

            // length 2 check for palindrome
            for (int i = 0; i < n - 1; ++i) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = true;
                    result++;
                }
            }

            // length >= 3
            for (int l = 3; l <= n; ++l) {
                for (int i = 0, j = i + l - 1; j < n ; ++i, ++j) {
                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                        result++;
                        dp[i][j] = true;
                    }
                }
            }


            return result;
        }


    }

    /**
     * Time: O(N^2), Space: O(1)
     */
    class Solution3 {
        public int getLengthPalindromes(int idx, boolean isOddType, String s, int n) {
            if (!isOddType && (!(idx + 1 < n) ||(idx + 1 < n && s.charAt(idx) != s.charAt(idx + 1))) ) return 0;

            int result = 1, l = idx - 1, r = (isOddType  == true ? idx + 1: idx + 2);

            while ( l >= 0 && r <= n - 1 && s.charAt(l) == s.charAt(r)) {
                result = result + 1;
                l--;
                r++;
            }

            return result;

        }

        public int countSubstrings(String s) {
            int result = 0, n = s.length();

            for (int i = 0; i < n; i++) {
                int odd = getLengthPalindromes(i, true, s, n); // odd
                int even = getLengthPalindromes(i, false, s, n); //even
                result += odd + even;
            }

            return result;
        }
    }
}
