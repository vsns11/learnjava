package ca.siva.ds.array;

import java.util.Arrays;

public class MergeSortedArray {

    //Leetcode: 88 Time: O(m +n), Space: O(m), not optimal
    class Solution1 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {

            int[] nums1Copy = new int[m];

            for (int p = 0; p < m; ++p)
                nums1Copy[p] = nums1[p];

            int i = 0, j = 0, k = 0;

            while (i < m && j < n) {
                if (nums1Copy[i] < nums2[j]) {
                    nums1[k++] = nums1Copy[i++];
                } else {
                    nums1[k++] = nums2[j++];
                }
            }

            while (i < m) {
                nums1[k++] = nums1Copy[i++];
            }

            while (j < n) {
                nums1[k++] = nums2[j++];
            }

            System.out.println(Arrays.toString(nums1));
            // nums1 = array;
        }


    }

    // Time: O(m + n): Space: O(1), optimal approach
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            // Set p1 and p2 to point to the end of their respective arrays.
            int p1 = m - 1;
            int p2 = n - 1;

            // And move p backwards through the array, each time writing
            // the smallest value pointed at by p1 or p2.
            for (int p = m + n - 1; p >= 0; p--) {
                if (p2 < 0) {
                    break;
                }
                if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                    nums1[p] = nums1[p1--];
                } else {
                    nums1[p] = nums2[p2--];
                }
            }
        }
    }
}
