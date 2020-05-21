package ca.jrvs.practice.dataStructure.tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class JBSTreeTest {

  @Test
  public void insert() {
    JBSTree<Integer> jb= new JBSTree<Integer>(new myComparator<Integer>());
    assertEquals(jb.insert(3),(Integer)3);
  }

  @Test
  public void search() {
    JBSTree<Integer> jb= new JBSTree<Integer>(new myComparator<Integer>());
    jb.insert(3);
    assertEquals(jb.search(3),(Integer)3);
  }

  @Test
  public void remove() {
    JBSTree<Integer> jb= new JBSTree<Integer>(new myComparator<Integer>());
    jb.insert(3);
    jb.insert(4);
    assertEquals(jb.remove(4),(Integer)4);
  }

  @Test
  public void preOrder() {
    JBSTree<Integer> jb= new JBSTree<Integer>(new myComparator<Integer>());
    jb.insert(3);
    jb.insert(2);
    jb.insert(5);
    jb.insert(6);
    jb.insert(4);
    Object[] actualPre={3,2,5,4,6};
    assertArrayEquals(jb.preOrder(),actualPre);
  }

  @Test
  public void inOrder() {
    JBSTree<Integer> jb= new JBSTree<Integer>(new myComparator<Integer>());
    jb.insert(3);
    jb.insert(2);
    jb.insert(5);
    jb.insert(6);
    jb.insert(4);
    Object[] actualIn={2,3,4,5,6};
    assertArrayEquals(jb.inOrder(),actualIn);
  }

  @Test
  public void postOrder() {
    JBSTree<Integer> jb= new JBSTree<Integer>(new myComparator<Integer>());
    jb.insert(3);
    jb.insert(2);
    jb.insert(5);
    jb.insert(6);
    jb.insert(4);
    Object[] actualPost={2,4,6,5,3};
    assertArrayEquals(jb.postOrder(),actualPost);
  }

  @Test
  public void findHeight() {
    JBSTree<Integer> jb= new JBSTree<Integer>(new myComparator<Integer>());
    jb.insert(3);
    jb.insert(2);
    jb.insert(5);
    jb.insert(6);
    jb.insert(4);
    assertEquals(jb.findHeight(),3);
  }
}