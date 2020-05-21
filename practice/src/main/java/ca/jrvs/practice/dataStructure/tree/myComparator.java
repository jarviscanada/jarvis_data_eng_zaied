package ca.jrvs.practice.dataStructure.tree;

import java.util.Comparator;

public class myComparator<E> implements Comparator<E> {

  @Override
  public int compare(E o1, E o2) {
    if(((Integer)o1).intValue() < ((Integer)o2).intValue())
    {
      return 1;
    }
    if(((Integer)o1).intValue() == ((Integer)o2).intValue())
    {
      return 0;
    }
    return -1;
  }
}
