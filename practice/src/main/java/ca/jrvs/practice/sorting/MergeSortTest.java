package ca.jrvs.practice.sorting;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class MergeSortTest {

  @Test
  public void msort() {
    Integer[] arr = {4, 3, 1, 7, 6, 10};
    MergeSort ms = new MergeSort();
    ms.msort(arr);
    Integer[] expectedArr = {1, 3, 4, 6, 7, 10};
    assertArrayEquals(arr, expectedArr);
  }
}