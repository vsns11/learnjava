package ca.siva.ds.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class MeetingRooms {
    //Leetcode: 252, Time: O(NlogN), Space: O(N)
    class Solution {
        public boolean canAttendMeetings(int[][] intervals) {
            TreeMap<Integer, Integer> calendar = new TreeMap<>();
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

            for (int[] itvl : intervals) {
                Integer prevStart = calendar.floorKey(itvl[0]);
                Integer nextStart = calendar.ceilingKey(itvl[0]);

                if ((prevStart == null || itvl[0] >= calendar.get(prevStart)) && (nextStart == null || itvl[1] <= nextStart)) {
                    calendar.put(itvl[0], itvl[1]);
                } else {
                    return false;
                }

            }
            return true;
        }
    }

    //Optimal in space: Time: O(NlogN), Space: O(1)
    class OptimalSolution {
        public boolean canAttendMeetings(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            for (int i = 0; i < intervals.length - 1; i++) {
                if (intervals[i][1] > intervals[i + 1][0]) {
                    return false;
                }
            }
            return true;
        }
    }
}
