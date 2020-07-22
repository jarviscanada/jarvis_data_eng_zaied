package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;

/**
 * AUTHOR: zaied
 * DATE: 2020-07-15
 * TIME: 8:53 p.m.
 **/

public class MissingNumber {

    /***
     * time complexity: O(n); n = nums.length
     * space complexity: O(1)
     * @param nums int[]
     * @return int
     */
    public int missingNumber(int[] nums) {
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i] != i && nums[i] != nums.length)
            {
                int hand = nums[i];
                while(true)
                {
                    if(nums[hand] ==hand) break;
                    int temp = nums[hand];
                    nums[hand] = hand;
                    hand = temp;
                    if(hand == nums.length)
                    {
                        break;
                    }
                }
            }
        }
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i] != i)
            {
                return i;
            }
        }
        return nums.length;
    }

    /***
     * time complexity: O(n); n = nums.length
     * space complexity: O(1)
     * @param nums int[]
     * @return int
     */

    public int missingNumberWithSum(int[] nums) {

        int sum = 0;
        boolean redunt = false;

        for(int i=0;i<nums.length;i++)
        {
            sum += i;
        }

        for (int num : nums) {
            if (num != nums.length) {

                sum -= num;
            } else {
                redunt = true;
            }
        }
        if(redunt) return sum;
        return nums.length;
    }

    /***
     * time complexity: O(n); n = nums.length
     * space complexity: O(n) hashset
     * @param nums int[]
     * @return int
     */
    public int missingNumberWithSet(int[] nums) {

        int n = nums.length;
        HashSet<Integer> hs = new HashSet<>();
        for (int num : nums) {
            hs.add(num);
        }
        for(int i=0;i<n;i++)
        {
            if(!hs.contains(i)) return i;
        }
        return n;
    }

    /***
     * time complexity: O(n)
     * space complexity: O(1)
     * @param nums int[]
     * @return int
     */
    public int missingNumberWithGauss(int[] nums) {

        int n = nums.length;
        int gauss_sum = ((n+1) * n) / 2;

        for (int num : nums) {
            gauss_sum -= num;
        }
        return gauss_sum;
    }

    /***
     * time complexity: O(n)
     * space complexity: O(1)
     * @param nums int[]
     * @return int
     */
    public int missingNumberWithXor(int[] nums) {

        int n = nums.length;
        int res = 0;
        for(int i=0;i<=n;i++)
        {
            res ^= i;
        }
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
