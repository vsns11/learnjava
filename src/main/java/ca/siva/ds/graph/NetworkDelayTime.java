package ca.siva.ds.graph;

import java.util.*;

/**
 *LeetCode: 743
 */
public class NetworkDelayTime {

    /**
     * Approach DFS, time: O( (N-1)! + ElogE)
     *  Space: O(N + E)
     */
    class Solution {

        class Pair<U, V> {
            U t; // destination
            V d; // time

            public Pair(U t, V d) {
                this.t = t;
                this.d = d;
            }

        }

        Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();

        public void doDfs(int[] recvTimes, int src, int currDistFromSrc) {
            if (currDistFromSrc >= recvTimes[src]) return;

            recvTimes[src] = currDistFromSrc;
            for (Pair<Integer, Integer> item : adj.get(src)) {
                doDfs(
                        recvTimes,
                        item.t,
                        currDistFromSrc + item.d
                );
            }

        }

        public int networkDelayTime(int[][] times, int n, int k) {

            for (int[] t : times) {
                int source = t[0], destination = t[1], time = t[2];
                adj.putIfAbsent(source, new ArrayList<>());
                adj.putIfAbsent(destination, new ArrayList<>());
                adj.get(source).add(new Pair(destination, time));
            }

            int[] recvTimes = new int[n + 1];
            Arrays.fill(recvTimes, Integer.MAX_VALUE);
            for (Integer i : adj.keySet()) {
                // sort based on the times (w)
                Collections.sort(adj.get(i), (p1, p2) -> p1.d - p2.d);
            }
            doDfs(recvTimes, k, 0);

            int result = -1;
            // System.out.println(Arrays.toString(recvTimes));
            for (int i = 1; i < recvTimes.length; ++i) {
                result = Math.max(result, recvTimes[i]);
            }


            return result == Integer.MAX_VALUE ? -1 : result;

        }
    }
}
