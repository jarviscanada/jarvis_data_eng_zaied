package ca.jrvs.practice.codingChallenge;

public class RotateString {

    public boolean rotateStringRoll(String A, String B)
    {
        long mod = 1000000009;
        long p = 53;

        //B hash compute

        long hashB = 0;
        long power = 1;
        for(int i=0;i<B.length();i++)
        {
            hashB = (hashB + (B.charAt(i) - 'A') * power) % mod;
            power = (power * p) % mod;
        }

        long hashA = 0;
        long last_power = 0;
        power = 1;
        for(int i=0;i<A.length();i++)
        {
            last_power = power;
            hashA = (hashA + (A.charAt(i) - 'A') * power) % mod;
            power = (power * p) % mod;
        }
        //System.out.println(hashA + " " + hashB);
        int n = A.length();
        long currA = hashA;
        power = last_power;
        for(int i=n-1;i>=0;i--)
        {
            currA = (((currA % mod - (((A.charAt(i) - 'A') % mod * power % mod)) % mod) * p % mod) % mod + (A.charAt(i) - 'A') % mod) % mod;
            if(currA == hashB) return true;
        }
        return false;
    }
}
