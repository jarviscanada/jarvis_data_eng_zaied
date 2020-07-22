package ca.jrvs.practice.codingChallenge;

public class CheckIfStringOnlyDigs {

    /***
     * time complexity: O(n); n = string length
     * space complexity: O(1)
     * @param str
     * @return
     */
    public static final boolean checkIfStringOnlyDigs(String str)
    {
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i) < '0' || str.charAt(i) > '9') return false;
        }
        return true;
    }
}
