package ca.siva.ds.tree;

import java.util.TreeMap;

//Leetcode: 729, Time: O(NlogN), Space:O(N)
public class MyCalendar1 {
    class MyCalendar {

        TreeMap<Integer, Integer> calendar;

        public MyCalendar() {
            calendar = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            // find the maximum point near to the start
            Integer prevStart = calendar.floorKey(start);
            Integer nxtStart = calendar.ceilingKey(start);
            if ((prevStart == null || calendar.get(prevStart) <= start) && (nxtStart == null || end<= nxtStart)) {
                calendar.put(start, end);
                return true;
            }
            return false;

            // find the least maximum greater than end
            // then both should evaluate to true without any conflict
        }
    }

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
}
