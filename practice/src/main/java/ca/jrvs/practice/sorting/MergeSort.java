package ca.jrvs.practice.sorting;

public class MergeSort {

  private static void merge(Integer[] arr, int low, int high, int mid) {
    Integer[] tempArr = new Integer[high - low + 1];
    int fp = low;
    int sp = mid + 1;
    int k = 0;
    while (fp <= mid && sp <= high) {
      if (arr[fp] < arr[sp]) {
        tempArr[k] = arr[fp];
        fp++;
      } else {
        tempArr[k] = arr[sp];
        sp++;
      }
      k++;
    }
    while (fp <= mid) {
      tempArr[k] = arr[fp];
      k++;
      fp++;
    }
    while (sp <= high) {
      tempArr[k] = arr[sp];
      k++;
      sp++;
    }
    int ocnt = low;
    for (int i = 0; i < tempArr.length; i++) {
      arr[ocnt] = tempArr[i];
      ocnt++;
    }
  }

  private static void msortHelper(Integer[] arr, int low, int high) {
    if (low >= high) {
      return;
    }
    int mid = low + (high - low) / 2;
    msortHelper(arr, low, mid);
    msortHelper(arr, mid + 1, high);
    merge(arr, low, high, mid);
  }

  public void msort(Integer[] arr) {
    int low = 0;
    int high = arr.length - 1;
    msortHelper(arr, low, high);
  }
}
