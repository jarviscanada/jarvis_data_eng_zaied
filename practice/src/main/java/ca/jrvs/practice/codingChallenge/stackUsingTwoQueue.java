package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.list.LinkedJList;

import java.util.*;

public class stackUsingTwoQueue<T> {

    private Queue<T> q1 = new LinkedList<>(); // utility
    private Queue<T> q2 = new LinkedList<>(); // will hold

    public stackUsingTwoQueue()
    {
        this.q1.clear();
        this.q2.clear();
    }

    public void push(T val)
    {
        q2.add(val);
        while(!q1.isEmpty())
        {
            q2.add(q1.remove());
        }
        Queue<T> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public T pop()
    {
        return q1.remove();
    }

    public T top()
    {
        return q1.peek();
    }

    public boolean empty()
    {
        return q1.isEmpty();
    }

}
