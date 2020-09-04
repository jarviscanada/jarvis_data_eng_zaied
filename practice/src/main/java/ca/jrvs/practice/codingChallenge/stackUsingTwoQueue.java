package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

public class stackUsingTwoQueue<T> {

    private Queue<T> q1 = new LinkedList<>(); // utility
    private Queue<T> q2 = new LinkedList<>(); // will hold

    public stackUsingTwoQueue() {
        this.q1.clear();
        this.q2.clear();
    }

    /***
     * time complexity: 0(q1 size + 1)
     * space complexity: 0(q1 size + 1)
     * @param val
     */

    public void push(T val) {
        q2.add(val);
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }
        Queue<T> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    /***
     * time complexity: 0(1)
     * space complexity: 0(1)
     * @return T
     */
    public T pop() {
        return q1.remove();
    }

    /***
     * time complexity: 0(1)
     * space complexity: 0(1)
     * @return T
     */
    public T top() {
        return q1.peek();
    }

    /***
     * time complexity: 0(1)
     * space complexity: 0(1)
     * @return boolean
     */
    public boolean empty() {
        return q1.isEmpty();
    }

}
