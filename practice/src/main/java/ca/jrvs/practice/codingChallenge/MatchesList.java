package ca.jrvs.practice.codingChallenge;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

/**
 * AUTHOR: zaied
 * DATE: 2020-07-15
 * TIME: 7:03 p.m.
 **/

public class MatchesList extends TypeSafeMatcher<List<Character>> {
    @Override
    protected boolean matchesSafely(List<Character> characterList) {
        for (Character character : characterList) {
            if (character != 'a' && character != 'c') return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {

    }
}
