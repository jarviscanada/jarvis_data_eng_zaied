package ca.jrvs.practice.sorting;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class QuickSortTest {

  @Test
  public void qsort() {
    Integer[] arr = {4, 3, 1, 7, 6, 10};
    QuickSort qs = new QuickSort();
    Integer[] expectedArr = {1, 3, 4, 6, 7, 10};
    qs.qsort(arr);
    assertArrayEquals(arr, expectedArr);
  }
}