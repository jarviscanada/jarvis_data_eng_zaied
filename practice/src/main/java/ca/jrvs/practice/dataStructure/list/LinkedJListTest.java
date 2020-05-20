package ca.jrvs.practice.dataStructure.list;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class LinkedJListTest {

  @Test
  public void add() {
    JList<String> testLinked = new LinkedJList<String>();
    testLinked.add("first");
    Assert.assertEquals(testLinked.get(0),"first");
  }

  @Test
  public void toArray() {
    JList<String> testLinked = new LinkedJList<String>();
    testLinked.add("first");
    String[] actualArr= {"first"};
    Assert.assertArrayEquals(testLinked.toArray(),actualArr);
  }

  @Test
  public void size() {
    JList<String> testLinked = new LinkedJList<String>();
    testLinked.add("first");
    Assert.assertEquals(testLinked.size(),1);
  }

  @Test
  public void isEmpty() {
    JList<String> testLinked = new LinkedJList<String>();
    Assert.assertEquals(testLinked.size(),0);
  }

  @Test
  public void indexOf() {
    JList<String> testLinked = new LinkedJList<String>();
    testLinked.add("first");
    Assert.assertEquals(testLinked.indexOf("first"),0);
  }

  @Test
  public void contains() {
    JList<String> testLinked = new LinkedJList<String>();
    testLinked.add("first");
    Assert.assertEquals(testLinked.contains("first"),true);
    Assert.assertEquals(testLinked.contains("second"),false);
  }

  @Test
  public void get() {
    JList<String> testLinked = new LinkedJList<String>();
    testLinked.add("first");
    Assert.assertEquals(testLinked.get(0), "first");
    Assert.assertNull(testLinked.get(1));
  }

  @Test
  public void remove() {
    JList<String> testLinked = new LinkedJList<String>();
    testLinked.add("first");
    Assert.assertNull(testLinked.remove(0));
  }

  @Test
  public void clear() {
    JList<String> testLinked = new LinkedJList<String>();
    testLinked.add("first");
    testLinked.clear();
    assertEquals(testLinked.size(),0);
  }
}