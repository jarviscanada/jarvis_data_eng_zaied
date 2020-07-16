package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrintLetterWithNumberTest {

    @Test
    public void printLetterWithNumber() {
        assertEquals(PrintLetterWithNumber.printLetterWithNumber("aabA"),"a1a1b2A27");
    }
}