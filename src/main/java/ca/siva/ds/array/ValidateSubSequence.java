package ca.siva.ds.array;

import java.util.*;

//AE Time: O(N), Space: O(N)
class Program {
    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        return isValidSubsequenceHelper(array, sequence, 0, 0, array.size(), sequence.size());
    }

    public static boolean isValidSubsequenceHelper(List<Integer> array, List<Integer> sequence, int i, int j, int maxLen1, int maxLen2) {
        if (j == maxLen2) return true;

        if (i < maxLen1) {
            if (array.get(i) == sequence.get(j))
                return isValidSubsequenceHelper(array, sequence, i + 1, j + 1, maxLen1, maxLen2);
            else
                return isValidSubsequenceHelper(array, sequence, i + 1, j, maxLen1, maxLen2);
        }
        return false;
    }
}
