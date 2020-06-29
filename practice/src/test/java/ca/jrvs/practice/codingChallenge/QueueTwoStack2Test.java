package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTwoStack2Test {

    private QueueTwoStack2 queueTwoStack2;

    @Before
    public void setUp() throws Exception {

        queueTwoStack2 = new QueueTwoStack2();
        queueTwoStack2.add(1);
        queueTwoStack2.add(2);
    }

    @Test
    public void peek() {
        assertEquals(queueTwoStack2.peek(),1);
        queueTwoStack2.add(3);
        assertEquals(queueTwoStack2.peek(),1);
    }

    @Test
    public void remove() {
        assertEquals(queueTwoStack2.remove(),1);
        queueTwoStack2.add(3);
        assertEquals(queueTwoStack2.remove(),2);
    }



    @Test
    public void empty() {
        assertEquals(queueTwoStack2.empty(),false);
    }
}