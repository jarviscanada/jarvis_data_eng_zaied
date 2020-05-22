package ca.jrvs.practice.search;

import static org.junit.Assert.*;

import java.util.Optional;
import java.util.OptionalInt;
import org.junit.Test;

public class BinarySearchTest {

  @Test
  public void binarySearchRecursion() {
    BinarySearch bs=new BinarySearch();
    Integer[] arr={1,2,3,4,5,6};
    assertEquals(bs.binarySearchIteration(arr,4),Optional.of(3));
    assertNull(bs.binarySearchIteration(arr,8));
  }

  @Test
  public void binarySearchIteration() {
    BinarySearch bs=new BinarySearch();
    Integer[] arr={1,2,3,4,5,6};
    assertEquals(bs.binarySearchIteration(arr,4),Optional.of(3));
    assertNull(bs.binarySearchIteration(arr,8));
  }
}