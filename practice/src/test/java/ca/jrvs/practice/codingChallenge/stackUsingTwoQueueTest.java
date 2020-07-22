package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class stackUsingTwoQueueTest {

    private stackUsingTwoQueue stackUsingTwoQueueObj;
    @Before
    public void setUp() throws Exception {
        stackUsingTwoQueueObj= new stackUsingTwoQueue<>();
        stackUsingTwoQueueObj.push(1);
        stackUsingTwoQueueObj.push(2);
    }

    @Test
    public void pop() {
        assertEquals(stackUsingTwoQueueObj.pop(),2);
        stackUsingTwoQueueObj.push(3);
        assertEquals(stackUsingTwoQueueObj.pop(),3);
        assertEquals(stackUsingTwoQueueObj.pop(),1);
    }

    @Test
    public void top() {
        assertEquals(stackUsingTwoQueueObj.top(),2);
        stackUsingTwoQueueObj.push(3);
        assertEquals(stackUsingTwoQueueObj.top(),3);
        assertEquals(stackUsingTwoQueueObj.top(),3);
    }

    @Test
    public void empty() {
        assertEquals(stackUsingTwoQueueObj.empty(),false);
    }
}