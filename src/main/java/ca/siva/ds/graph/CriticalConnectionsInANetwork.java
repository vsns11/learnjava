package ca.siva.ds.graph;

import java.util.*;

public class CriticalConnectionsInANetwork {
    class Solution {

        public class Pair<U, V> {
            U u;
            V v;

            public Pair(U u, V v) {
                this.u = u;
                this.v = v;
            }

            @Override
            public int hashCode() {
                return ((Integer) u) ^ ((Integer) v);
            }

            @Override
            public boolean equals(Object o) {
                if (o == null || !(o instanceof Pair)) return false;
                Pair that = (Pair) o;
                return u == that.u && v == that.v;
            }
        }

        Map<Integer, List<Integer>> graph;
        Map<Integer, Integer> rank;
        Map<String, Boolean> conn;

        public int doDfs(int node, int currRank) {
            if (rank.get(node) != null) return rank.get(node);

            rank.put(node, currRank);

            int minExpectedRank = currRank + 1;
            for (Integer neighbour : graph.get(node)) {

                if (rank.get(neighbour) != null && rank.get(neighbour) == currRank - 1) {
                    continue;
                }

                int finalRank = doDfs(neighbour, currRank + 1);

                if (finalRank <= currRank) {
                    int max = Math.max(neighbour, node), min = Math.min(neighbour, node);
                    conn.remove(String.valueOf(min) + "_" + String.valueOf(max));
                    // conn.remove(new Pair<Integer, Integer>(min, max));
                }

                minExpectedRank = Math.min(minExpectedRank, finalRank);
            }
            return minExpectedRank;
        }


        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            buildGraph(n, connections);
            doDfs(0, 0);

            List<List<Integer>> result = new ArrayList<>();
            for (String x : conn.keySet()) {
                String[] parts = x.split("_");
                result.add(new ArrayList<>(Arrays.asList(Integer.valueOf(parts[0]),
                        Integer.valueOf(parts[1]))));
            }
            return result;
        }


        public void buildGraph(int n, List<List<Integer>> connections) {
            this.graph = new HashMap<Integer, List<Integer>>();
            this.rank = new HashMap<Integer, Integer>();
            this.conn = new HashMap<String, Boolean>();

            for (int i = 0; i < n; ++i) {
                rank.put(i, null);
                graph.put(i, new ArrayList<>());
            }

            for (List<Integer> edge : connections) {
                int v1 = edge.get(0), v2 = edge.get(1);
                graph.get(v1).add(v2);
                graph.get(v2).add(v1);

                int min = Math.min(v1, v2), max = Math.max(v1, v2);
                conn.put(String.valueOf(min) + "_" + String.valueOf(max), true);
                // conn.put(new Pair<Integer, Integer>(min, max), true);

            }
        }

    }
}
