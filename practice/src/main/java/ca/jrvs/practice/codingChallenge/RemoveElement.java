package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

public class RemoveElement {

    /***
     * time complexity: O(array length)
     * space complexity: O(1)
     * @param nums int[]
     * @param val int
     * @return int
     */
    public int removeElement(int[] nums, int val) {
        int idx = -1;
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i] != val)
            {
                idx++;
                nums[idx] = nums[i];
            }
        }
        return idx+1;
    }
}
