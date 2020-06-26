package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingOneQueue<T> {

    private Queue<T> q1 = new LinkedList<>();

    public StackUsingOneQueue() {
        this.q1.clear();
    }

    /***
     * time complexity: 0(q1 size + 1)
     * space complexity: 0(q1 size + 1)
     * @param val
     */
    public void push(T val) {
        int s = q1.size();
        q1.add(val);
        while (s > 0) {
            q1.add(q1.remove());
            s--;
        }
    }

    /***
     * time complexity: 0(1)
     * space complexity: 0(1)
     * @return
     */
    public T pop() {
        return q1.remove();
    }

    /***
     * time complexity: 0(1)
     * space complexity: 0(1)
     * @return
     */
    public T top() {
        return q1.peek();
    }

    /***
     * time complexity: 0(1)
     * space complexity: 0(1)
     * @return
     */
    public boolean empty() {
        return q1.isEmpty();
    }

}
