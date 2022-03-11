package ca.siva.ds.arrays;

/**
 * Given an array of integers arr, return true if and only if it is a valid mountain array.
 *
 * Recall that arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 *
 * e.g:
 * Input: arr = [2,1]
 * Output: false
 *
 * Input: arr = [3,5,5]
 * Output: false
 *
 * Input: arr = [0,3,2,1]
 * Output: true
 */
public class ValidMountainArray {
    public boolean validMountainArray(int[] arr) {

        if(arr.length <= 2) return false;

        int biggestIdx = 0;

        int biggestValue = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > biggestValue){
                biggestValue = arr[i];
                biggestIdx = i;
            }
        }

        if(biggestIdx == 0 || biggestIdx == arr.length-1) return false;


        for(int i = 0; i < biggestIdx; i++){
            if(arr[i] >= arr[i+1]){
                return false;
            }
        }

        for(int i = biggestIdx; i < arr.length-1; i++){
            if(arr[i] <= arr[i+1]){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

    }
}