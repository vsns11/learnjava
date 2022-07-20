package ca.siva.ds.hashmap;

import java.util.ArrayList;
import java.util.List;

// Leetcode: 792
public class NumberOfMatchingSubSequences {
    //Brute force solution, Time: O(m*n), Space: O(Max(m,n))
    class Solution1 {
        public int numMatchingSubseq(String s, String[] words) {
            int count = 0;
            for (String w: words) {
                if (isSubSequence(s, w, 0, 0, s.length(), w.length()))
                    count++;
            }
            return count;
        }

        public boolean isSubSequence(String s1, String s2, int i, int j, int s1Length, int s2Length) {
            if (j == s2Length) return true;
            if (i == s1Length) return false;
            if (s1.charAt(i) == s2.charAt(j)) {
                return isSubSequence(s1, s2, i + 1, j + 1, s1Length, s2Length);
            }
            return isSubSequence(s1, s2, i + 1, j, s1Length, s2Length);
        }
    }

    //Optimal solution, Time: O(m + length of all words), Space O(length of all words):
    class Solution2 {
        public int numMatchingSubseq(String s, String[] words) {
            ArrayList<Node>[] heads = new ArrayList[26];
            for (int i = 0; i < 26; ++i) {
                heads[i] = new ArrayList<Node>();
            }

            for (String w: words) {
                heads[w.charAt(0) - 'a'].add(new Node(w, 0));
            }

            int count = 0;
            for (char x : s.toCharArray()) {
                List<Node> oldBucket = heads[x - 'a'];
                heads[x - 'a'] = new ArrayList();
                for (Node n: oldBucket) {
                    n.i = n.i + 1;
                    if (n.i == n.s.length()) count++;
                    else {
                        heads[n.s.charAt(n.i) - 'a'].add(n);
                    }
                }
            }

            return count;
        }


        class Node {
            String s;
            int i;

            Node(String s, int i) {
                this.s = s;
                this.i = i;
            }
        }

    }

}
