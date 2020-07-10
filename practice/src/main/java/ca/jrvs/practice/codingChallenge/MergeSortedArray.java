package ca.jrvs.practice.codingChallenge;

/***
 * time complexity: O(m+n)
 * space complexity: O(1)
 */
public class MergeSortedArray {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int fp = m-1;
            int sp = n-1;
            int k = n+m-1;
            while(fp >= 0 && sp >= 0)
            {
                if(nums1[fp] > nums2[sp])
                {
                    nums1[k] = nums1[fp];
                    k--;
                    fp--;
                }
                else
                {
                    nums1[k] = nums2[sp];
                    k--;
                    sp--;
                }
            }
            while(sp >= 0)
            {
                nums1[k] = nums2[sp];
                k--;
                sp--;
            }
        }
}
