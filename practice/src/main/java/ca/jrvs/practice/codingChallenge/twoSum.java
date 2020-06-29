package ca.jrvs.practice.codingChallenge;

import java.util.*;

public class twoSum {

    /***
     * time complexity: 0(n^2); n is the number of array elements
     * space complexity: 0(1)
     * @param arr
     * @param tar
     * @return
     */
    public static final int[] twoSumBrute(int[] arr, int tar) {
        int[] res = new int[2];
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == tar) {
                    res[0] = i;
                    res[1] = j;
                    break;
                }
            }
        }
        return res;
    }

    /***
     * time complexity: 0(nlogn); 0(nlogn) for both sort and the loop and binary search
     * space complexity: 0(n) for hashmap
     * @param arr
     * @param tar
     * @return
     */
    public static final int[] twoSumSortedbin(int[] arr, int tar) {
        int[] res = new int[2];
        int n = arr.length;
        Map<Integer, List<Integer>> um = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (um.containsKey(arr[i])) {
                List<Integer> idxs = um.get(arr[i]);
                um.remove(arr[i]);
                idxs.add(i);
                um.put(arr[i], idxs);
            } else {
                List<Integer> idxs = new ArrayList<>();
                idxs.add(i);
                um.put(arr[i], idxs);
            }
        }
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            int req = tar - arr[i];
            int low = i + 1;
            int high = n - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (arr[mid] == req) {
                    if (arr[i] == arr[mid]) {
                        res[0] = um.get(arr[i]).get(0);
                        res[1] = um.get(arr[i]).get(1);
                    } else {
                        res[0] = Math.min(um.get(arr[i]).get(0), um.get(arr[mid]).get(0));
                        res[1] = Math.max(um.get(arr[i]).get(0), um.get(arr[mid]).get(0));
                    }
                    break;
                } else if (arr[mid] < req) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return res;
    }

    /***
     * time complexity: 0(nlogn); 0(nlogn) for sort and for the loop 0(n)
     * space complexity: 0(n) for hashmap
     * @param arr
     * @param tar
     * @return
     */
    public static final int[] twoSumSortedlin(int[] arr, int tar) {
        int[] res = new int[2];
        int n = arr.length;
        Map<Integer, List<Integer>> um = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (um.containsKey(arr[i])) {
                List<Integer> idxs = um.get(arr[i]);
                um.remove(arr[i]);
                idxs.add(i);
                um.put(arr[i], idxs);
            } else {
                List<Integer> idxs = new ArrayList<>();
                idxs.add(i);
                um.put(arr[i], idxs);
            }
        }
        Arrays.sort(arr);
        int f = 0;
        int s = n - 1;
        while (f < s) {
            if (arr[f] + arr[s] < tar) {
                f++;
            } else if (arr[f] + arr[s] == tar) {
                if (arr[f] == arr[s]) {
                    res[0] = um.get(arr[f]).get(0);
                    res[1] = um.get(arr[f]).get(1);
                } else {
                    res[0] = Math.min(um.get(arr[f]).get(0), um.get(arr[s]).get(0));
                    res[1] = Math.max(um.get(arr[f]).get(0), um.get(arr[s]).get(0));
                }
                break;
            } else {
                s--;
            }

        }
        return res;
    }

    /***
     * time complexity: 0(n) for the loop
     * space complexity: 0(n) for hashmap
     * @param arr
     * @param tar
     * @return
     */
    public static final int[] twoSumlin(int[] arr, int tar) {
        int[] res = new int[2];
        int n = arr.length;
        Map<Integer, Integer> um = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (um.containsKey(tar - arr[i])) {
                res[0] = um.get(tar - arr[i]);
                res[1] = i;
                break;
            } else {
                um.put(arr[i], i);
            }
        }
        return res;
    }

}
