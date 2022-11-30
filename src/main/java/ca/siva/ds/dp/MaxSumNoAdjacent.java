package ca.siva.ds.dp;
import java.util.*;


//AE, T: O(N), S: O(1)
public class MaxSumNoAdjacent {
    class Program {
        public int maxSubsetSumNoAdjacent(int[] array) {
            if (array.length == 0) return 0;
            if (array.length == 1) return array[0];
            int f1 = array[0], f2 = Math.max(array[0], array[1]);

            for (int i = 2; i < array.length; ++i) {
                int curr = Math.max(f1 + array[i], f2);
                f1 = f2;
                f2 = curr;
            }


            return f2;
        }
    }
}



