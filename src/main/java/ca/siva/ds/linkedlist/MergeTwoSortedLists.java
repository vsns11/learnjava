package ca.siva.ds.linkedlist;

// Time: O(N), Space: O(1)
public class MergeTwoSortedLists {
     public class ListNode {
          int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    class Solution {
        ListNode dummy = new ListNode(-1), temp = dummy;
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

            while (list1 != null && list2 != null) {
                if (list1.val <= list2.val) {
                    ListNode next = list1.next;
                    attachListNode(list1);
                    list1 = next;
                } else {
                    ListNode next = list2.next;
                    attachListNode(list2);
                    list2 = next;
                }
            }
            attachRemaining(list1);
            attachRemaining(list2);
            return temp.next;
        }

        public void attachRemaining(ListNode node) {
            while (node != null) {
                ListNode next = node.next;
                attachListNode(node);
                node = next;
            }
        }

        public void attachListNode(ListNode nodeToAttach) {
            dummy.next = nodeToAttach;
            dummy = dummy.next;
            dummy.next = null;
        }

    }
}
