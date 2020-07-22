package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class RotateStringTest {

    @Test
    public void rotateString() {
        assertTrue(RotateString.rotateStringRolling("clrwmpkwru", "wmpkwruclr"));
    }
}