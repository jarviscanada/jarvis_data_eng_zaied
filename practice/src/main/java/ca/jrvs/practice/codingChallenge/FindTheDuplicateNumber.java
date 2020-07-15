package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;

public class FindTheDuplicateNumber {


    /***
     * time complexity: O(nums length)
     * space complexity: O(1)
     * but array has been changed
     * @param nums int[]
     * @return int
     */
    public int findDuplicate(int[] nums) {
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i] != i+1)
            {
                int inhand = nums[i];
                nums[i] = -1;
                while(true)
                {
                    if(nums[inhand - 1] == -1)
                    {
                        nums[inhand - 1] = inhand;
                        break;
                    }
                    else if(nums[inhand-1] == inhand)
                    {
                        return nums[inhand-1];
                    }
                    int temp = nums[inhand-1];
                    nums[inhand-1] = inhand;
                    inhand = temp;

                }
            }
        }
        return -1;
    }

    /***
     * time complexity: O(nlogn) n = nums length
     * space complexity: O(1)
     * @param nums int[]
     * @return int
     */
    public int findDuplicateWithSorting(int[] nums) {
        Arrays.sort(nums);
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i] == nums[i-1])
            {
                return nums[i];
            }
        }
        return -1;
    }

    /***
     * time complexity: 0(n); n = nums.length
     * space complexity: O(n) for hashset
     * @param nums int[]
     * @return int
     */
    public int findDuplicateWithHashSet(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();
        for (int num : nums) {
            if (hs.contains(num))
                return num;
            hs.add(num);
        }
        return -1;
    }

    /**
     * time complexity: O(n); n = nums.length
     * space complexity: O(1)
     * @param nums int[]
     * @return int
     */
    public int findDuplicateWithFastSlow(int[] nums) {
        int hair = nums[0];
        int tortoise = nums[0];

        //meet
        do
        {
            tortoise = nums[tortoise];
            hair = nums[nums[hair]];

        }while(tortoise != hair);

        //entry
        tortoise = nums[0];
        while(tortoise != hair)
        {
            hair = nums[hair];
            tortoise = nums[tortoise];
        }

        return hair;
    }
}
