package ca.jrvs.practice.codingChallenge;

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
}
