package ca.siva.ds.array;

/**
 * #524
 * <p>
 * This problem was asked by Amazon.
 * <p>
 * Given an array of numbers, find the maximum sum of any contiguous subarray of the array.
 * <p>
 * For example, given the array [34, -50, 42, 14, -5, 86], the maximum sum would be 137, since we would take elements 42, 14, -5, and 86.
 * <p>
 * Given the array [-5, -1, -8, -9], the maximum sum would be 0, since we would not take any elements.
 * <p>
 * Do this in O(N) time.
 */

public class MaxContinousArraySum {
    public static Integer getMaxContinousArraySum(Integer[] inputArray) {
        Integer maxSumAtPoint = 0, maxContSum = 0;
        for (int i = 0; i < inputArray.length; ++i) {
            maxContSum = Math.max(inputArray[i], maxContSum + inputArray[i]);
            maxSumAtPoint = Math.max(maxContSum, maxSumAtPoint);
        }
        return maxSumAtPoint;
    }

    public static void main(String[] args) {
        MaxContinousArraySum obj = new MaxContinousArraySum();
        System.out.println(obj.getMaxContinousArraySum(new Integer[]{34, -50, 42, 14, -5, 86}));
        System.out.println(obj.getMaxContinousArraySum(new Integer[]{-5, -1, -8, -9}));
    }
}
