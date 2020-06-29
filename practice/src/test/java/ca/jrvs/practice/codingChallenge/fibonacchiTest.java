package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class fibonacchiTest {

    @Test
    public void fibonacchiWithRecursion() {
        assertEquals(fibonacchi.fibonacchiWithRecursion(32),2178309);
    }

    @Test
    public void fibonacchiWitBackwardhdp() {
        assertEquals(fibonacchi.fibonacchiWithBackwarddp(32),2178309);
    }

    @Test
    public void fibonacchiWithAnotherApproach() {
        assertEquals(fibonacchi.fibonacchiWithAnotherApproach(32),2178309);
    }

    @Test
    public void fibonacchiWithForwarddp() {
        assertEquals(fibonacchi.fibonacchiWithForwarddp(32),2178309);
    }
}