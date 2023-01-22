package ca.siva.ds.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class MatchingBrackets {
    //Time: O(N), Space: O(N)
    class Solution {
        public boolean isValid(String s) {

            Deque<Character> stack = new ArrayDeque<>();
            int strLen = s.length(), i = 0;
            if (strLen == 1) return false;
            Map<Character, Character> brackets = Map.of('(', ')', '{', '}', '[', ']');

            while (i < strLen) {
                Character currChar = s.charAt(i);
                if (brackets.containsKey(currChar)) {
                    stack.addLast(currChar);
                } else if (stack.isEmpty() || brackets.get(stack.pollLast()) != currChar) {
                    return false;
                }
                i++;
            }

            return stack.isEmpty();
        }
    }
}
