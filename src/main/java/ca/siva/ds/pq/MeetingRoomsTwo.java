package ca.siva.ds.pq;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomsTwo {
    class Solution {
        //Leetcode: 253, Time: O(NlogN) Space: O(N)
        public int minMeetingRooms(int[][] intervals) {
            if (intervals.length == 0) return 0;
            Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(intervals.length, (a, b) -> a - b);
            minHeap.add(intervals[0][1]);

            for (int i = 1; i < intervals.length; ++i) {
                if (intervals[i][0] >= minHeap.peek()) {
                    minHeap.poll();
                }
                minHeap.add(intervals[i][1]);
            }

            return minHeap.size();

        }
    }
}
