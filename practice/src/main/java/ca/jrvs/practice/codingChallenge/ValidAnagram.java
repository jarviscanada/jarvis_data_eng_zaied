package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

public class ValidAnagram {

    /***
     * time complexity: 0(s length)
     * space complexity: 0(1)
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramFreq(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] sFreq = new int[26];
        Arrays.fill(sFreq, 0);
        int[] tFreq = new int[26];
        Arrays.fill(tFreq, 0);
        for (int i = 0; i < s.length(); i++) {
            sFreq[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            tFreq[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (sFreq[i] != tFreq[i]) return false;
        }
        return true;
    }

    /***
     * time complexity: 0(nlogn) for sorting
     * space complexity: 0(n) to convert string to char array
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramSort(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] sArr = s.toCharArray();
        Arrays.sort(sArr);
        char[] tArr = t.toCharArray();
        Arrays.sort(tArr);
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] != tArr[i]) return false;
        }
        return true;
    }

}
