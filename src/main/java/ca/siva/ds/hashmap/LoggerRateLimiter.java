package ca.siva.ds.hashmap;

import java.util.HashMap;
import java.util.Map;

//Leetcode: 359, Time: O(1), Space: O(N)
public class LoggerRateLimiter {
    class Logger {
        private Map<String, Integer> ref;

        public Logger() {
            ref = new HashMap<>();
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            if (!ref.containsKey(message)) {
                ref.put(message, timestamp);
                return true;
            } else {
                Integer timeLimit = ref.get(message) + 10;
                if (timestamp < timeLimit) return false;
                else {
                    ref.put(message, timestamp);
                    return true;
                }
            }
        }
    }
}
