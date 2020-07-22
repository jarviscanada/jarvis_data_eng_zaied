package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

public class fibonacchi {

    private static Integer[] memo = new Integer[33];

    public static final void init() {
        Integer[] temp = Arrays.stream(memo).map(val -> -1).toArray(Integer[]::new);
        memo = temp;
    }

    /***
     * finds the nth fibonacci (0-indexed) using recursion
     * time complexity: 0(2^n) exponential
     * space complexity: constant time except the implicit stack used by recursion
     * @param n
     * @return
     */
    public static final int fibonacchiWithRecursion(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        return fibonacchiWithRecursion(n - 1) + fibonacchiWithRecursion(n - 2);
    }

    /***
     * finds the nth fibonacci (0-indexed) using dynamic programming approach
     * time complexity: 0(n) with recursive function call overhead
     * space complexity: 0(n) with implicit stack used by recursion
     * @param n
     * @return
     */
    private static boolean initialized = false;

    public static final int fibonacchiWithBackwarddp(int n) {
        if (!initialized) {
            init();
            initialized = true;
        }
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (memo[n] != -1) return memo[n];
        memo[n] = fibonacchiWithBackwarddp(n - 1) + fibonacchiWithBackwarddp(n - 2);
        return memo[n];
    }

    /***
     * finds the nth fibonacci (0-indexed) using dynamic programming approach
     * time complexity: 0(n)
     * space complexity: 0(n)
     * @param n
     * @return
     */
    public static final int fibonacchiWithForwarddp(int n) {
        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

    /***
     * finds the nth fibonacci (0-indexed)
     * time complexity: 0(n)
     * space complexity: 0(1) or constant time
     * @param n
     * @return
     */
    public static final int fibonacchiWithAnotherApproach(int n) {
        int prev1 = 0;
        int prev2 = 1;
        if (n < 1) return prev1;
        if (n == 1) return prev2;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = prev2 + prev1;
            prev1 = prev2;
            prev2 = res;
        }
        return res;
    }
}
