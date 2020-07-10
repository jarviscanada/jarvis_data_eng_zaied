package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckIfStringOnlyDigsTest {

    @Test
    public void checkIfStringOnlyDigs() {
        assertEquals(CheckIfStringOnlyDigs.checkIfStringOnlyDigs("123"),true);
        assertEquals(CheckIfStringOnlyDigs.checkIfStringOnlyDigs("123,000"),false);
    }
}