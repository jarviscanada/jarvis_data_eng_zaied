package ca.jrvs.practice.codingChallenge;

import java.util.Deque;
import java.util.LinkedList;

public class queueTwoStack1<T> {

    Deque<T> s1 = new LinkedList<>();
    Deque<T> s2 = new LinkedList<>();

    public queueTwoStack1(){

        this.s1.clear();
        this.s2.clear();
    }

    /***
     * time complexity: 0(s1 size + s2 size)
     * space complexity: 0(s1 size + s2 size)
     * @param val
     */
    public void add (T val)
    {
        while(!s2.isEmpty())
        {
            s1.push(s2.pop());
        }
        s1.push(val);
        while(!s1.isEmpty())
        {
            s2.push(s1.pop());
        }
    }

    /***
     * time complexity: 0(1)
     * space complexity: 0(1)
     * @return
     */
    public T remove()
    {
        return s2.pop();
    }

    /***
     * time complexity: 0(1)
     * space complexity: 0(1)
     * @return
     */
    public T peek()
    {
        return s2.peek();
    }

    /***
     * time complexity: 0(1)
     * space complexity: 0(1)
     * @return
     */
    public boolean empty()
    {
        return s2.isEmpty();
    }
}
