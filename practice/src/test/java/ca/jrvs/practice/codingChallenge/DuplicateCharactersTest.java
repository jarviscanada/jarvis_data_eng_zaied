package ca.jrvs.practice.codingChallenge;

import org.hamcrest.Matcher;
import org.junit.Test;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class DuplicateCharactersTest {


    private Matcher<List<Character>> isAorC()
    {
        return new MatchesList();
    }

    @Test
    public void duplicateCharacters() {
        assertThat(DuplicateCharacters.duplicateCharacters("A black cat"), isAorC());
    }
}