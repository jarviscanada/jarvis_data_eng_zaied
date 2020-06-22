package ca.jrvs.practice.dataStructure.stackQueue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LinkedJListDequeTest {


  @Test
  public void add() {
    JQueue<String> testJQ= new LinkedJListDeque<String>();
    assertEquals(testJQ.add("first"),true);
  }

  @Test
  public void remove() {
    JQueue<String> testJQ= new LinkedJListDeque<String>();
    testJQ.add("first");
    testJQ.add("second");
    assertEquals(testJQ.remove(),"first");
    assertEquals(testJQ.remove(),"second");
  }

  @Test
  public void pop() {
    JStack<String> testJS= new LinkedJListDeque<String>();
    testJS.push("first");
    testJS.push("second");
    assertEquals(testJS.pop(),"second");
    assertEquals(testJS.pop(),"first");
  }

  @Test
  public void peek() {
    JStack<String> testJS= new LinkedJListDeque<String>();
    testJS.push("first");
    testJS.push("second");
    assertEquals(testJS.peek(),"second");
  }
}