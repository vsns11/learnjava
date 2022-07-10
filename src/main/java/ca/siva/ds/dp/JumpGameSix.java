package ca.siva.ds.dp;

import java.util.*;

// Leetcode: 1696
public class JumpGameSix {

    // Time: O(N^2), Space: O(N)
    class Solution1 {
        public int maxResult(int[] nums, int k) {
            Map<Integer, Integer> cache = new HashMap<>();
            maxResultHelper(nums, k, 0, cache, nums.length - 1);
            return cache.get(0);
        }

        public int maxResultHelper(int[] nums, int k, int idx, Map<Integer, Integer> cache, int target) {
            if (cache.containsKey(idx)) return cache.get(idx);
            if (idx == target) {
                cache.put(target, nums[target]);
                return cache.get(target);
            }
            int result = Integer.MIN_VALUE;

            for (int i = idx + 1; i <= Math.min(target, idx + k); ++i) {
                int calcResult = nums[idx] + maxResultHelper(nums, k, i, cache, target);
                result = Math.max(result, calcResult);
            }
            cache.put(idx, result);
            return result;
        }

    }

    // Time: O(N), Space: O(N)
    class Solution2 {
        public int maxResult(int[] nums, int k) {
            int[] score = new int[nums.length];

            score[0] = nums[0];
            Deque<Integer> deque = new LinkedList<>();

            deque.offerFirst(0);

            for (int i = 1; i < nums.length; ++i) {
                while (deque.peekFirst() != null &&
                        deque.peekFirst() < i - k) {
                    deque.pollFirst();
                }
                score[i] = score[deque.peek()] + nums[i];
                while (deque.peekLast() != null
                        && score[i] >= score[deque.peekLast()]) {
                    deque.pollLast();
                }

                deque.offerLast(i);
            }
            return score[nums.length - 1];
        }
    }

    // Time: O(NlogN), Space: O(N)
    class Solution3 {
        public int maxResult(int[] nums, int k) {
            int n = nums.length;
            int[] score = new int[n];
            score[0] = nums[0];
            PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            priorityQueue.add(new int[] { nums[0], 0 });
            for (int i = 1; i < n; i++) {
                // pop the old index
                while (priorityQueue.peek()[1] < i - k) {
                    priorityQueue.remove();
                }
                score[i] = nums[i] + score[priorityQueue.peek()[1]];
                priorityQueue.add(new int[] { score[i], i });
            }
            return score[n - 1];
        }
    }


}
