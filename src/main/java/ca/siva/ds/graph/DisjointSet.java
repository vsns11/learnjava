package ca.siva.ds.graph;

import java.util.HashSet;
import java.util.Set;

public class DisjointSet {
    public int[] rank;
    public int[] root;

    public DisjointSet(int l) {
        rank = new int[l];
        root = new int[l];
        for (int i = 0; i < l; ++i) {
            rank[i] = 1;
            root[i] = i;
        }

    }

    //Time: O(1) or O(log n) in worst case scenario
    public int find(int node) {
        int currRoot  = root[node];
        if (currRoot == node) return currRoot;
        return root[node] = find(currRoot);
    }

    //Time: O(1) or O(log n) in worst case scenario
  public void union(int v1, int v2) {
      int r1 = find(v1);
      int r2 = find(v2);

      if (r1 != r2) {
          int rank1 = rank[r1];
          int rank2 = rank[r2];
          if (rank1 > rank2) {
              root[v2] = v1;
          } else if (rank2 > rank1) {
              root[v1] = v2;
          } else {
              root[v1] = v2;
              rank[v2] += 1;
          }
      }
  }

  public static int findNumberOfProvinces(int[][] edges) {
        int numVertices = edges.length;
      DisjointSet djs = new DisjointSet(numVertices);

        for (int i = 0; i < edges.length; ++i) {
            for (int j = i + 1; j < edges.length; ++j) {
                if (edges[i][j] == 1) {
                    djs.union(i, j);
                }
            }
         }

      Set<Integer> visited = new HashSet<>();

      int result = 0;
      for (int i = 0; i < numVertices; ++i) {
          if (!visited.contains(djs.root[i])) result++;
          visited.add(djs.root[i]);
      }

      return result;

  }

}
