package ca.siva.ds.dp;

// Leetcode: 746
public class MinCostClimbingStairs {
    // Time: O(N), Space: O(N)
    class Solution {
        public int minCostClimbingStairs(int[] cost) {
            // The array's length should be 1 longer than the length of cost
            // This is because we can treat the "top floor" as a step to reach
            int minimumCost[] = new int[cost.length + 1];

            // Start iteration from step 2, since the minimum cost of reaching
            // step 0 and step 1 is 0
            for (int i = 2; i < minimumCost.length; i++) {
                int takeOneStep = minimumCost[i - 1] + cost[i - 1];
                int takeTwoSteps = minimumCost[i - 2] + cost[i - 2];
                minimumCost[i] = Math.min(takeOneStep, takeTwoSteps);
            }

            // The final element in minimumCost refers to the top floor
            return minimumCost[minimumCost.length - 1];
        }

    }

    // Time: O(N), Space: O(1)
    class Solution2 {
        public int minCostClimbingStairs(int[] cost) {
            int oneStepBack = 0, twoStepBack = 0;

            for (int i = 2; i < cost.length + 1; ++i) {
                int tmp = oneStepBack;
                oneStepBack = Math.min(oneStepBack + cost[i - 1], twoStepBack + cost[i - 2]);
                twoStepBack = tmp;
            }

            return oneStepBack;
        }

    }

}
