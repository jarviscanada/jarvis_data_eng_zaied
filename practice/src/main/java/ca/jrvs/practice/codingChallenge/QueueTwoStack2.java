package ca.jrvs.practice.codingChallenge;

import java.util.Deque;
import java.util.LinkedList;

public class QueueTwoStack2<T> {

    Deque<T> s1 = new LinkedList<>();
    Deque<T> s2 = new LinkedList<>();

    public QueueTwoStack2(){

        this.s1.clear();
        this.s2.clear();
    }

    /***
     * time complexity: 0(1)
     * space complexity: 0(1)
     * @param val
     */
    public void add (T val)
    {
        s1.push(val);
    }

    private void doIf()
    {
        if(s2.isEmpty())
        {
            while(!s1.isEmpty()) s2.push(s1.pop());
        }
    }

    /***
     * time complexity: amortized 0(1)
     * space complexity: 0(s1 size)
     * @return
     */
    public T remove()
    {
        doIf();
        return s2.pop();
    }

    /***
     * time complexity: amortized 0(1)
     * space complexity: 0(s1 size)
     * @return
     */
    public T peek()
    {
        doIf();
        return s2.peek();
    }

    /***
     * time complexity:amortized 0(1)
     * space complexity: 0(s1 size)
     * @return
     */
    public boolean empty()
    {
        doIf();
        return s2.isEmpty();
    }
}
