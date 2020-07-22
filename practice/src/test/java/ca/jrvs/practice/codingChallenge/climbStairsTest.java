package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class climbStairsTest {

    @Test
    public void climbStairsWithRecursion() {
        assertEquals(climbStairs.climbStairsWithRecursion(4),5);
    }

    @Test
    public void climbStairsWithBackwarddp() {
        assertEquals(climbStairs.climbStairsWithBackwarddp(4),5);
    }

    @Test
    public void climbStairsWithForwarddp() {
        assertEquals(climbStairs.climbStairsWithForwarddp(4),5);
    }

    @Test
    public void climbStairsWithAnotherApproach() {
        assertEquals(climbStairs.climbStairsWithAnotherApproach(4),5);
    }
}