package ca.siva.ds.linkedlist;

public class PartitionList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    //Leetcode: 86, Time: O(N), Space: O(1)
    class Solution {
        public ListNode partition(ListNode head, int x) {
            ListNode beforeJoint = new ListNode(-1);
            ListNode beforeRef = beforeJoint;

            ListNode afterJoint = new ListNode(-1);
            ListNode afterRef = afterJoint;

            while (head != null) {
                if (head.val < x) {
                    beforeJoint.next = head;
                    beforeJoint = beforeJoint.next;
                } else {
                    afterJoint.next = head;
                    afterJoint = afterJoint.next;
                }
                head = head.next;
            }
            afterJoint.next = null;
            beforeJoint.next = afterRef.next;


            return beforeRef.next;

        }
    }
}
