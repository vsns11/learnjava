package ca.siva.ds.dp;

//Time: O(N), Space: O(1)
public class BestTimeToBuyAndSellStock {
    class Solution {
        public int maxProfit(int[] prices) {
            int profit = 0, purchase = prices[0];

            for (int sell = 1; sell < prices.length; sell++) {
                profit = Math.max(profit, prices[sell] - purchase);
                if (purchase > prices[sell])
                    purchase = prices[sell];
            }
            return profit;
        }
    }
}
