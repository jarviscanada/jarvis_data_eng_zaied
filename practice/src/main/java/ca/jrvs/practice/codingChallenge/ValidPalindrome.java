package ca.jrvs.practice.codingChallenge;

public class ValidPalindrome {

    /***
     * time complexity: 0(string size)
     * space complexity: 0(sting size) creation of lower string
     * @param s
     * @return
     */
    public boolean isPalindrome(String s)
    {
        int n = s.length();
        int fp = 0;
        int sp = n-1;
        String lower_s = s.toLowerCase();
        while(fp <= sp)
        {

            if(((lower_s.charAt(fp) >= 'a' && lower_s.charAt(fp) <= 'z') ||(lower_s.charAt(fp) >= '0' && lower_s.charAt(fp) <= '9'))
                    && ((lower_s.charAt(sp) >= 'a' && lower_s.charAt(sp) <= 'z') ||(lower_s.charAt(sp) >= '0' && lower_s.charAt(sp) <= '9')))
            {
                if(lower_s.charAt(fp) ==lower_s.charAt(sp)) {
                    fp++;
                    sp--;
                }
                else
                {
                    return false;
                }
            }
            else if(!((lower_s.charAt(fp) >= 'a' && lower_s.charAt(fp) <= 'z') ||(lower_s.charAt(fp) >= '0' && lower_s.charAt(fp) <= '9')))
            {
                fp++;
            }
            else if(!((lower_s.charAt(sp) >= 'a' && lower_s.charAt(sp) <= 'z') ||(lower_s.charAt(sp) >= '0' && lower_s.charAt(sp) <= '9')))
            {
                sp--;
            }
        }
        return true;
    }

    /***
     * time complexity: 0(string size) with recursive function call overhead
     * space complexity: 0(sting size) creation of lower string
     * @param s
     * @return
     */

    String lower_s;
    private boolean recur(int fp, int sp)
    {
        if(fp > sp) return true;
        if(((lower_s.charAt(fp) >= 'a' && lower_s.charAt(fp) <= 'z') ||(lower_s.charAt(fp) >= '0' && lower_s.charAt(fp) <= '9'))
                && ((lower_s.charAt(sp) >= 'a' && lower_s.charAt(sp) <= 'z') ||(lower_s.charAt(sp) >= '0' && lower_s.charAt(sp) <= '9')))
        {
            if(lower_s.charAt(fp) ==lower_s.charAt(sp)) {
                return recur(fp+1,sp-1);
            }
            else
            {
                return false;
            }
        }
        else if(!((lower_s.charAt(fp) >= 'a' && lower_s.charAt(fp) <= 'z') ||(lower_s.charAt(fp) >= '0' && lower_s.charAt(fp) <= '9')))
        {
            return recur(fp+1,sp);
        }
        return recur(fp,sp+1);
    }

    public boolean isPalindromeRecur(String s)
    {
        int n = s.length();
        lower_s = s.toLowerCase();
        return recur(0,n-1);
    }

}

