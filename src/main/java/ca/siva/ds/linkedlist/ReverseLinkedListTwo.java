package ca.siva.ds.linkedlist;

public class ReverseLinkedListTwo {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
     //Leetcode: 92, Time: O(N), Space: O(1)
    class Solution1 {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            if (head == null) return null;

            ListNode curr = head, prev1 = null;
            int count = 1;

            while (curr != null && count++ < left) {
                prev1 = curr;
                curr = curr.next;
            }


            ListNode prev2 = curr;

            ListNode curr2 = curr.next;
            ListNode next2 = curr2;

            // break the start point
            if (prev2 != null) {
                prev2.next = null;
            }

            left++ ;

            while (curr2 != null && left <= right) {
                next2 = curr2.next;
                curr2.next = prev2;
                prev2 = curr2;
                curr2 = next2;
                left++;
            }
            if (next2 != null) {
                curr.next = next2;
            }

            if (prev1 != null) {
                prev1.next = prev2;
            } else {
                head = prev2;
            }

            return head;
        }

    }
}
