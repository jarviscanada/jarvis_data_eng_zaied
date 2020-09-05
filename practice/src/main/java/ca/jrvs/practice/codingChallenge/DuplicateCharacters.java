package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * AUTHOR: zaied
 * DATE: 2020-07-15
 * TIME: 5:40 p.m.
 **/

public class DuplicateCharacters {

    public static List<Character> duplicateCharacters(String str)
    {
        List<Character> res = new ArrayList<>();
        int[] mark = new int[58];
        Arrays.fill(mark,0);
        for(int i=0;i<str.length();i++)
        {
            char ch = str.charAt(i);
            if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
            {
                if(mark[ch - 'A'] == 1)
                {
                    res.add(ch);
                }
                mark[ch - 'A']++;

            }
        }
        return res;
    }
}
