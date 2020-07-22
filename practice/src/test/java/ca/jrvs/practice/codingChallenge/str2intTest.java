package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import ca.jrvs.practice.codingChallenge.str2int;
import static org.junit.Assert.*;

public class str2intTest {

    @Test
    public void str2intRunner() {
        assertEquals(str2int.str2intRunner("+989080808999"),2147483647);
    }

    @Test
    public void str2intWithAPI() {
        assertEquals(str2int.str2intWithAPI("   +0 123"),0);
    }
}