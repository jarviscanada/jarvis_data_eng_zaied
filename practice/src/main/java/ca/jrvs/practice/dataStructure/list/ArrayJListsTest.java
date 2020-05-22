package ca.jrvs.practice.dataStructure.list;

import org.junit.Assert;

public class ArrayJListsTest {

  @org.junit.Test
  public void add() {
    JList<String> testList = new ArrayJLists<String>();
    testList.add("first");
    Assert.assertEquals(testList.get(0), "first");
  }

  @org.junit.Test
  public void toArray() {
    JList<String> testList = new ArrayJLists<String>();
    testList.add("first");
    String[] actualArr = {"first"};
    Assert.assertArrayEquals(testList.toArray(), actualArr);
  }

  @org.junit.Test
  public void size() {
    JList<String> testList = new ArrayJLists<String>();
    testList.add("first");
    Assert.assertEquals(testList.size(), 1);
  }

  @org.junit.Test
  public void isEmpty() {
    JList<String> testList = new ArrayJLists<String>();
    Assert.assertEquals(testList.isEmpty(), true);
  }

  @org.junit.Test
  public void indexOf() {
    JList<String> testList = new ArrayJLists<String>();
    testList.add("first");
    Assert.assertEquals(testList.indexOf("first"), 0);
  }

  @org.junit.Test
  public void contains() {
    JList<String> testList = new ArrayJLists<String>();
    testList.add("first");
    Assert.assertEquals(testList.contains("first"), true);
    Assert.assertEquals(testList.contains("second"), false);
  }

  @org.junit.Test
  public void get() {
    JList<String> testList = new ArrayJLists<String>();
    testList.add("first");
    Assert.assertEquals(testList.get(0), "first");
  }

  @org.junit.Test
  public void remove() {
    JList<String> testList = new ArrayJLists<String>();
    testList.add("first");
    testList.remove(0);
    Assert.assertEquals(testList.size(), 0);
  }

  @org.junit.Test
  public void clear() {
    JList<String> testList = new ArrayJLists<String>();
    testList.add("first");
    testList.add("second");
    testList.clear();
    Assert.assertEquals(testList.size(), 0);
  }
}