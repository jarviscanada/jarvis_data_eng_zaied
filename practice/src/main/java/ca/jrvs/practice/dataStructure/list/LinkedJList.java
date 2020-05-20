package ca.jrvs.practice.dataStructure.list;

import java.util.AbstractSequentialList;
import java.util.LinkedList;

public class LinkedJList<E> implements JList<E>  {

  transient int size = 0;

  /** Node declaration **/
  public static class Node<E> {
    public E item;
    public Node<E> next;
    public Node<E> prev;

    public Node(Node<E> prev, E element, Node<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }
  }



  /**
   * Pointer to first node.
   * Invariant: (first == null && last == null) ||
   *            (first.prev == null && first.item != null)
   */
  transient Node<E> first;

  /**
   * Pointer to last node.
   * Invariant: (first == null && last == null) ||
   *            (last.next == null && last.item != null)
   */
  transient Node<E> last;

  /**
   * Constructs an empty list.
   */

  void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<E>(l, e, null);
    last = newNode;
    if (l == null)
      first = newNode;
    else
      l.next = newNode;
    size++;
  }

  @Override
  public boolean add(E e) {
    linkLast(e);
    return true;
  }

  @Override
  public Object[] toArray() {
    Object[] result = new Object[size];
    int i = 0;
    for (Node<E> x = first; x != null; x = x.next)
      result[i++] = x.item;
    return result;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int indexOf(Object o) {
    int index = 0;
    if (o == null) {
      for (Node<E> x = first; x != null; x = x.next) {
        if (x.item == null)
          return index;
        index++;
      }
    } else {
      for (Node<E> x = first; x != null; x = x.next) {
        if (o.equals(x.item))
          return index;
        index++;
      }
    }
    return -1;
  }

  @Override
  public boolean contains(Object o) {
    return (indexOf(o) != -1);
  }

  private boolean isElementIndex(int index) {
    return index >= 0 && index < size;
  }


  private void checkElementIndex(int index) {
    if (!isElementIndex(index))
      throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
  }

  Node<E> node(int index) {
    // assert isElementIndex(index);

    if (index < (size >> 1)) {
      Node<E> x = first;
      for (int i = 0; i < index; i++)
        x = x.next;
      return x;
    } else {
      Node<E> x = last;
      for (int i = size - 1; i > index; i--)
        x = x.prev;
      return x;
    }
  }

  private String outOfBoundsMsg(int index) {
    return "Index: "+index+", Size: "+size;
  }

  @Override
  public E get (int index) {
      if(index >= size)
      {
        return null;
      }
      return node(index).item;
  }

  E unlink(Node<E> x) {
    // assert x != null;
    final E element = x.item;
    final Node<E> next = x.next;
    final Node<E> prev = x.prev;

    if (prev == null) {
      first = next;
    } else {
      prev.next = next;
      x.prev = null;
    }

    if (next == null) {
      last = prev;
    } else {
      next.prev = prev;
      x.next = null;
    }

    x.item = null;
    size--;
    return element;
  }
  @Override
  public E remove(int index) {
    if(index <= size)
    {
      int idx=0;
      for (Node<E> x = first; x != null; x = x.next) {
        if (idx == index) {
          unlink(x);
          break;
        }
        idx++;
      }
    }
    return null;
  }

  @Override
  public void clear() {
    for (Node<E> x = first; x != null; ) {
      Node<E> next = x.next;
      x.item = null;
      x.next = null;
      x.prev = null;
      x = next;
    }
    first = last = null;
    size = 0;
  }
}
