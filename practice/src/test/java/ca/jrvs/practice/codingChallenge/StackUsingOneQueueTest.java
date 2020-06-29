package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackUsingOneQueueTest {

    private StackUsingOneQueue stackUsingOneQueue;
    @Before
    public void setUp() throws Exception {
        stackUsingOneQueue = new StackUsingOneQueue<>();
        stackUsingOneQueue.push(1);
        stackUsingOneQueue.push(2);
    }

    @Test
    public void pop() {
        assertEquals(stackUsingOneQueue.pop(),2);
        stackUsingOneQueue.push(3);
        assertEquals(stackUsingOneQueue.pop(),3);
        assertEquals(stackUsingOneQueue.pop(),1);
    }

    @Test
    public void top() {
        assertEquals(stackUsingOneQueue.top(),2);
        stackUsingOneQueue.push(3);
        assertEquals(stackUsingOneQueue.top(),3);
        assertEquals(stackUsingOneQueue.top(),3);
    }

    @Test
    public void empty() {
        assertEquals(stackUsingOneQueue.empty(),false);
    }
}