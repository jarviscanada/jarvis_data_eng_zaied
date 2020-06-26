package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class queueTwoStack1Test {

    private queueTwoStack1 queueTwoStack1obj;
    @Before
    public void setUp() throws Exception {

        queueTwoStack1obj = new queueTwoStack1();
        queueTwoStack1obj.add(1);
        queueTwoStack1obj.add(2);
    }

    @Test
    public void remove() {
        assertEquals(queueTwoStack1obj.remove(),1);
        queueTwoStack1obj.add(3);
        assertEquals(queueTwoStack1obj.remove(),2);
    }

    @Test
    public void peek() {
        assertEquals(queueTwoStack1obj.peek(),1);
        queueTwoStack1obj.add(3);
        assertEquals(queueTwoStack1obj.peek(),1);
    }

    @Test
    public void empty() {
        assertEquals(queueTwoStack1obj.empty(),false);
    }
}