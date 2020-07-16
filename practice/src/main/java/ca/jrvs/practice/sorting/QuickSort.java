package ca.jrvs.practice.sorting;

public class QuickSort {

  private static int partition(Integer[] arr, int low, int high) {
    int pivot = arr[high];
    int smaller = low - 1;
    for (int j = low; j < high; j++) {
      if (arr[j] < pivot) {
        smaller++;
        int temp = arr[smaller];
        arr[smaller] = arr[j];
        arr[j] = temp;
      }
    }
    arr[high] = arr[smaller + 1];
    arr[smaller + 1] = pivot;
    return smaller + 1;
  }

  private static void qsortHelper(Integer[] arr, int low, int high) {
    if (low > high) {
      return;
    }
    int pi = partition(arr, low, high);
    qsortHelper(arr, low, pi - 1);
    qsortHelper(arr, pi + 1, high);
  }

  public void qsort(Integer[] arr) {
    int low = 0;
    int high = arr.length - 1;
    qsortHelper(arr, low, high);
  }

}
