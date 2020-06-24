package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.codingChallenge.compareMap;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.*;

public class compareMapTest {

    @org.junit.Test
    public void compareMapRunner() {

        Map<Integer, Integer> m1 = new HashMap<>();
        m1.put(1,1);
        Map<Integer, Integer> m2 = new HashMap<>();
        m2.put(1,1);
        Map<Integer, Integer> m3 = new HashMap<>();
        m3.put(2,1);
        assertTrue(compareMap.compareMapRunner(m1,m2));
        assertFalse(compareMap.compareMapRunner(m1,m3));
    }

    @Test
    public void compareMapRunnerWithHashJMap() {
        Map<Integer, Integer> m1 = new HashJMap<>();
        m1.put(1,1);
        Map<Integer, Integer> m2 = new HashJMap<>();
        m2.put(1,1);
        Map<Integer, Integer> m3 = new HashJMap<>();
        m3.put(2,1);
        assertTrue(compareMap.compareMapRunner(m1,m2));
        assertFalse(compareMap.compareMapRunner(m1,m3));
    }
}