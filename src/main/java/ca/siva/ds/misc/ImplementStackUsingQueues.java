package ca.siva.ds.misc;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Leetcode: 225
 */
class MyStack {

    Queue<Integer> q1, q2;
    int top = 0;

    public MyStack() {
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
    }

    public void push(int x) {
        q1.offer(x);
        top = x;
    }

    public int pop() {
        while (q1.size() > 1) {
            int tmp = q1.poll();
            q2.offer(tmp);
            top = tmp;
        }
        int ret = q1.poll();
        Queue<Integer> tmpQ = q1;
        q1 = q2;
        q2 = tmpQ;
        return ret;
    }

    public int top() {
        return top;
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}
