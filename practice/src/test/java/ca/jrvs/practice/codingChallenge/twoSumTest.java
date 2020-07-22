package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class twoSumTest {

    @Test
    public void twoSumBrute() {
        int[] arr = new int[]{3,3};
        assertArrayEquals(twoSum.twoSumBrute(arr,6),new int[]{0,1});
    }

    @Test
    public void twoSumSortedbin() {
        int[] arr = new int[]{3,3};
        assertArrayEquals(twoSum.twoSumSortedbin(arr,6),new int[]{0,1});
    }

    @Test
    public void twoSumSortedlin() {
        int[] arr = new int[]{3,3};
        assertArrayEquals(twoSum.twoSumSortedlin(arr,6),new int[]{0,1});
    }

    @Test
    public void twoSumlin() {
        int[] arr = new int[]{3,3};
        assertArrayEquals(twoSum.twoSumlin(arr,6),new int[]{0,1});
    }
}