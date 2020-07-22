package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;

public class ContainsDuplicate {
        /**
        * time complexity: O(n); n = arr length
         * space complexity: O(n) because of hashmap;
        * @param nums
        * @return
        */
        public boolean containsDuplicate(int[] nums) {
            HashSet<Integer> hs = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                if (hs.contains(nums[i])) {
                    return true;
                } else {
                    hs.add(nums[i]);
                }
            }
            return false;
        }
}
