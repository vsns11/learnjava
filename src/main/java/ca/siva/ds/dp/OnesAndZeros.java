package ca.siva.ds.dp;

public class OnesAndZeros {
    /**
     * Leetcode: 474
     * Time complexity: O(l*m*n), space: O(l*m*n)
     */
    class Solution1 {
        public int findMaxForm(String[] strs, int m, int n) {
            int[][][] cache = new int[strs.length][m + 1][n + 1];
            return calculateMaxForm(strs, 0, m, n, cache);
        }

        public int calculateMaxForm(String[] strs, int idx, int m, int n, int[][][] cache) {
            if (idx == strs.length) return 0;
            if (cache[idx][m][n] != 0) {
                return cache[idx][m][n];
            }
            int inclResult = 0;
            int[] cnt = countZerosAndOnes(strs[idx]);

            int zeros = cnt[0], ones = cnt[1];
            if (m - zeros >= 0 &&  n - ones >= 0)
                inclResult = calculateMaxForm(strs, idx + 1, m - zeros, n - ones, cache) + 1;

            int exclResult = calculateMaxForm(strs, idx + 1, m, n, cache);

            cache[idx][m][n] = Math.max(inclResult, exclResult);
            return cache[idx][m][n];
        }

        public int[] countZerosAndOnes(String s) {
            int cnt[] = new int[2];
            for (char c: s.toCharArray()) {
                cnt[c - '0']++;
            }
            return cnt;
        }

    }

    /**
     * Time complexity: O(l*m*n), space: O(m*n)
     */
    public class Solution2 {
        public int findMaxForm(String[] strs, int m, int n) {
            int[][] dp = new int[m + 1][n + 1];
            for (String s: strs) {
                int[] count = countzeroesones(s);
                for (int zeroes = m; zeroes >= count[0]; zeroes--)
                    for (int ones = n; ones >= count[1]; ones--)
                        dp[zeroes][ones] = Math.max(1 + dp[zeroes - count[0]][ones - count[1]], dp[zeroes][ones]);
            }
            return dp[m][n];
        }
        public int[] countzeroesones(String s) {
            int[] c = new int[2];
            for (int i = 0; i < s.length(); i++) {
                c[s.charAt(i)-'0']++;
            }
            return c;
        }
    }


}
