package ca.jrvs.practice.search;

import java.util.Optional;
import ca.jrvs.practice.dataStructure.tree.myComparator;

public class BinarySearch<E> {

  private static <E> Optional<Integer> binarysearchrecursionHelper(E[] arr, int low, int high, E target) {
    if(low>high)
    {
      return null;
    }
    int mid = low + (high - low)/2;
    myComparator mcomp = new myComparator();
    int cmp = mcomp.compare(arr[mid], target);
    if(cmp==0)
    {
      return Optional.of(mid);
    }
    else if(cmp>0)
    {
      return binarysearchrecursionHelper(arr, mid+1, high, target);
    }
    return binarysearchrecursionHelper(arr, low, mid-1, target);
  }
  /**
   * find the the target index in a sorted array
   *
   * @param arr input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not ound
   */
  myComparator mcomp = new myComparator();
  public <E> Optional<Integer> binarySearchRecursion(E[] arr, E target) {
    int low=0;
    int high=arr.length-1;
    return binarysearchrecursionHelper(arr, low, high, target);
  }

  /**
   * find the the target index in a sorted array
   *
   * @param arr input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not ound
   */
  public <E> Optional<Integer> binarySearchIteration(E[] arr, E target) {
    int low = 0;
    int high = arr.length - 1;
    while(low<=high)
    {
      int mid=low+(high-low)/2;
      //System.out.println(arr[mid]);
      int cmp= mcomp.compare(arr[mid],target);
      //System.out.println(cmp);
      if(cmp==0)
      {
        return Optional.of(mid);
      }
      else if(cmp>0)
      {
        low=mid+1;
      }
      else
      {
        high=mid-1;
      }
    }
    return null;
  }
}
