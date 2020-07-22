package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class validParenthesesTest {

    private validParentheses validParenthesesobj;
    @Before
    public void setUp() throws Exception {
        validParenthesesobj = new validParentheses();
    }

    @Test
    public void isValidStack() {
        assertEquals(validParenthesesobj.isValidStack("((}))"),false);
    }
}