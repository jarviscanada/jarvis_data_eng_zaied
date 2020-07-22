package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

/**
 * AUTHOR: zaied
 * DATE: 2020-07-16
 * TIME: 12:50 a.m.
 **/

public class CountPrimes {

    /***
     * time complexity: O(n log log n)
     * space complexity: O(n)
     * @param n int
     * @return int
     */
    public int countPrimes(int n) {
        if(n <= 2) return 0;
        boolean[] prime = new boolean[n];
        Arrays.fill(prime, true);
        int res = 1;
        for(int i=3;i<n;i+=2)
        {
            if(prime[i])
            {
                res++;
                long val = (long)(i)*i;
                if((val) > Integer.MAX_VALUE) continue;
                for(int j=(i*i);j<n;j+=i)
                {
                    prime[j] = false;
                }
            }
        }
        return res;
    }
}
