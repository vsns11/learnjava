package ca.siva.ds.linkedlist;

//Leetcode: 234
//Time: O(N), Space: O(1)
public class PalindromeLinkedList {
    class ListNode {
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

    /**
     * Definition for singly-linked list.
     */
    class Solution {
        public boolean isPalindrome(ListNode head) {
            /**
             1. Find the first half's end
             2. Reverse the second half list
             3. Check for a paliandrome match
             4. After that adjust back the reversed 2nd list to normal.
             **/
            if (head == null) return true;

            //step: 1
            ListNode firstEnd = findFirstHalfEnd(head);

            //step: 2
            ListNode revList = reverseList(firstEnd.next);

            ListNode curr1 = head, curr2 = revList;

            boolean isPal = true;

            while (curr1 != null && curr2 != null) {
                if (curr1.val != curr2.val) return false;
                curr1 = curr1.next;
                curr2 = curr2.next;
            }

            firstEnd.next = reverseList(revList);
            return isPal;

        }


        public ListNode reverseList(ListNode head) {
            ListNode prev = null, next = null;
            ListNode curr = head;

            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;

            }

            return prev;
        }

        public ListNode findFirstHalfEnd(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;

            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow;
        }

    }
}
