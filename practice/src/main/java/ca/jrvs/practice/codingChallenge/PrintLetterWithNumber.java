package ca.jrvs.practice.codingChallenge;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * AUTHOR: zaied
 * DATE: 2020-07-15
 * TIME: 9:55 p.m.
 **/

public class PrintLetterWithNumber {

    public static String printLetterWithNumber(String str)
    {
        int n = str.length();
        char[] ch = new char[3*n];
        Arrays.fill(ch,' ');
        int k = 0;
        for(int i=0;i<n;i++)
        {
            char curr = str.charAt(i);
            ch[k++] = curr;
            if(curr >= 'a' && curr <= 'z') {
                int num = curr - 'a' + 1;
                k = getK(ch, k, num);

            }
            else {
                int num = curr - 'A' + 27;
                k = getK(ch, k, num);
            }
        }
        StringBuilder res = new StringBuilder();
        for (char c : ch) {
            if(c != ' ')
                res.append(c);
        }
        return res.toString();
    }

    private static int getK(char[] ch, int k, int num) {
        int d1 = num % 10;
        num = num /10;
        if(num > 0)
        {
            int d2 = num % 10;
            ch[k++] = (char)('0' + d2);
        }
        ch[k++] = (char)('0' + d1);
        return k;
    }

}
