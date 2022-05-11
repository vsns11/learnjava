package ca.siva.ds.dp;

/**
 * LeetCode: 1641
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 */
public class CountVowelStrings {

    /**
     * Approach1: Backtracking
     */
    public int countVowelStrings(int n) {
        return helper(n, 1);
    }

    public int helper(int n, int startIdx) {
        if (n == 0)
            return 1;

        int result = 0;
        for (int i = startIdx; i <= 5; ++i) {
            result += helper(n - 1, i);
        }
        return result;
    }

    /**
     * Approach2:
     * Time: O(n^5)
     */
    public int countVowelStrings2(int n) {
        return countVowelStringUtil(n, 5);
    }

    int countVowelStringUtil(int n, int vowels) {
        if (n == 1)
            return vowels;
        if (vowels == 1)
            return 1;
        return countVowelStringUtil(n - 1, vowels) +
                countVowelStringUtil(n, vowels - 1);
    }

    /**
     * Approach3: Time: O(n), space: O(n)
     */
    class Solution {
        public int countVowelStrings(int n) {
            int[][] dp = new int[n + 1][6];

            for (int i = 1; i < 6; ++i) {
                dp[1][i] = i;
            }

            for (int j = 2; j <= n; ++j) {
                dp[j][1] = 1;
                for (int k = 2; k < 6; ++k) {
                    dp[j][k] = dp[j - 1][k] + dp[j][k - 1];
                }
            }
            return dp[n][5];

        }
    }
}
