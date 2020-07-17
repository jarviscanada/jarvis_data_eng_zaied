package ca.jrvs.practice.codingChallenge;

/**
 * AUTHOR: zaied
 * DATE: 2020-07-16
 * TIME: 8:44 p.m.
 **/

public class RotateString {

    /***
     * time complexity: O(n^2); n = string size
     * space complexity O(n^2); for the char Array and Stringbuilder
     * @param A String
     * @param B String
     * @return boolean
     */
    public boolean rotateStringWithLoop(String A, String B) {

        if(A.equals(B)) return true;
        if(A.length() != B.length()) return false;
        int l = A.length();
        for(int i=1;i<l;i++)
        {
            char[] tempChar = new char[l];
            for(int j=0;j<l;j++)
            {
                int nidx = (j + i) % l;
                tempChar[nidx] = A.charAt(j);
            }
            StringBuilder strB = new StringBuilder();
            for(int j=0;j<l;j++)
            {
                strB.append(tempChar[j]);
            }
            String currStr = strB.toString();
            if(currStr.equals(B)) return true;
        }
        return false;
    }

    /**
     * time complexity = O((2 * string length) ^ 2); contains is O(n^2); n = string length
     * space complexity: O(2 * string length)
     * @param A String
     * @param B String
     * @return boolean
     */
    public boolean rotateStringWithAppend(String A, String B) {

        if(A.length() != B.length()) return false;
        String testStr = A+A;
        return testStr.contains(B);
    }


}
