package ca.siva.ds.array;

/**
 * Given an array of integers arr, return true if and only if it is a valid mountain array.
 * <p>
 * Recall that arr is a mountain array if and only if:
 * <p>
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * <p>
 * e.g:
 * Input: arr = [2,1]
 * Output: false
 * <p>
 * Input: arr = [3,5,5]
 * Output: false
 * <p>
 * Input: arr = [0,3,2,1]
 * Output: true
 */
public class ValidMountainArray {
    public boolean validMountainArray(int[] arr) {

        if (arr.length <= 2) return false;

        int biggestIdx = 0;

        int biggestValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > biggestValue) {
                biggestValue = arr[i];
                biggestIdx = i;
            }
        }
        // using the above max digit, return strictly increasing(last) && decreasing(0) ones as false.
        if (biggestIdx == 0 || biggestIdx == arr.length - 1) return false;

        // return false for the zigzag ones.
        for (int i = 0; i < biggestIdx; i++) {
            if (arr[i] >= arr[i + 1]) {
                return false;
            }
        }
        // In decreasing ones, if greater return false
        for (int i = biggestIdx; i < arr.length - 1; i++) {
            if (arr[i] <= arr[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ValidMountainArray obj = new ValidMountainArray();
        System.out.println(obj.validMountainArray(new int[]{2, 1}));
    }
}