package ca.siva.ds.stack;

import java.util.Stack;

/**
 * LeetCode: 1209
 */
public class RemoveAllAdjacentDuplicatesInString {

    class Solution {

        class Node {
            Character item;
            int count;

            public Node(Character item, int count) {
                this.item = item;
                this.count = count;
            }
        }

        public String removeDuplicates(String s, int k) {

            Stack<Node> stack = new Stack<>();


            for (int i = 0; i < s.length(); ++i) {
                Character x = s.charAt(i);

                if (stack.isEmpty() || stack.peek().item != x) {
                    stack.add(new Node(x, 1));
                } else {
                    stack.peek().count++;

                    if (stack.peek().count == k) {
                        int j = k;
                        while (j-- > 1)
                            stack.pop();
                    } else {
                        stack.add(new Node(x, stack.peek().count));
                    }
                }


            }


            StringBuffer sb = new StringBuffer();

            while (!stack.isEmpty()) {
                sb.insert(0, stack.pop().item);
            }
            return sb.toString();

        }
    }
}
