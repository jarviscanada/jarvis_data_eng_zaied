package ca.jrvs.practice.codingChallenge;

public class RemoveDuplicatesFromSortedArray {

        public static final void advance(int[] nums, int idx)
        {
            int temp = nums[idx];
            for(int i = idx+1;i<nums.length;i++)
            {
                nums[i-1] = nums[i];
            }
            nums[nums.length-1] = temp;
        }

    /**
     * time complexity: O(n^2); n = array length
     * space complexity: O(1)
     * @param nums
     * @return
     */
    public static final int removeDuplicates(int[] nums) {
            int n = nums.length;
            int i =1;
            while(i < n)
            {
                if(nums[i] == nums[i-1])
                {
                    n--;
                    advance(nums,i);
                }
                else
                {
                    i++;
                }

            }
            return n;
        }

    /***
     * time complexity: O(n)
     * sapce complexity: O(1)
     * @param nums
     * @return
     */
    public static int removeDuplicatesTwoPointer(int[] nums) {
            int i=0;
            for(int j=1;j<nums.length;j++)
            {
                if(nums[j] != nums[i])
                {
                    i++;
                }
                nums[i] = nums[j];
            }
            return i+1;
        }
}
