package ca.siva.ds.bitmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * # DCP: 523
 * This problem was asked by Jane Street.
 * <p>
 * Given integers M and N, write a program that counts how many positive integer pairs (a, b) satisfy the following conditions:
 * <p>
 * a + b = M
 * a XOR b = N
 */
public class CountPositiveIntegerPairs {

    public ArrayList<ArrayList<Integer>> getCountOfPositiveIntegerPairsApproachOne(Integer m, Integer n) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= Math.floor(m / 2); i++) {
            if ((i ^ (m - i)) == n) {
                result.add((ArrayList<Integer>) Arrays.stream(new int[]{i, m - i}).boxed().collect(Collectors.toList()));
            }
        }
        return result;
    }

    public Integer getCountOfPositiveIntegerPairs(Integer m, Integer n) {
        String xorBinStr = Integer.toBinaryString(m);
        String andBinStr = Integer.toBinaryString(n);

        return null;
    }

    public static void main(String[] args) {
        CountPositiveIntegerPairs obj = new CountPositiveIntegerPairs();
        System.out.println(obj.getCountOfPositiveIntegerPairsApproachOne(17, 13));
    }

}
