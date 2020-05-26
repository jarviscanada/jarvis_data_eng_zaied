package ca.jrvs.practice.dataStructure.stackQueue;

import ca.jrvs.practice.dataStructure.list.LinkedJList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class LinkedJListDeque<E> extends LinkedJList<E> implements JDeque<E> {

  transient int size = 0;
  /**
   * This is equivalent enqueue operation in Queue ADT
   * <p>
   * Inserts the specified element into the queue represented by this deque (in other words, at the
   * tail of this deque) if it is possible to do so immediately without violating capacity
   * restrictions, returning {@code true} upon success and throwing an {@code IllegalStateException}
   * if no space is currently available.
   *
   * @param e the element to add
   * @return {@code true} (as specified by {@link Collection#add})
   * @throws NullPointerException if the specified element is null and this deque does not permit
   * null elements
   */
  transient Node<E> first;
  transient Node<E> last;

  void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<E>(l, e, null);
    last = newNode;
    if (l == null) {
      first = newNode;
    } else {
      l.next = newNode;
    }
    size++;
  }

  @Override
  public boolean add(E e) {
    if (e == null) {
      throw new NullPointerException();
    }
    linkLast(e);
    return true;
  }

  /**
   * This is equivalent dequeue operation in Queue ADT
   * <p>
   * Retrieves and removes the head of the queue represented by this deque (in other words, the
   * first element of this deque).
   *
   * @return the head of the queue represented by this deque
   * @throws NoSuchElementException if this deque is empty
   */

  private E unlinkFirst(Node<E> f) {
    // assert f == first && f != null;
    final E element = f.item;
    final Node<E> next = f.next;
    f.item = null;
    f.next = null; // help GC
    first = next;
    if (next == null) {
      last = null;
    } else {
      next.prev = null;
    }
    size--;
    return element;
  }

  public E removeFirst() {
    final Node<E> f = first;
    if (f == null) {
      throw new NoSuchElementException();
    }
    return unlinkFirst(f);
  }

  @Override
  public E remove() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    return removeFirst();
  }

  /**
   * Pops an element from the stack represented by this deque. In other words, removes and returns
   * the first element of this deque.
   *
   * @return the element at the front of this deque (which is the top of the stack represented by
   * this deque)
   * @throws NoSuchElementException if this deque is empty
   */
  @Override
  public E pop() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    return removeFirst();
  }

  /**
   * Pushes an element onto the stack represented by this deque (in other words, at the head of this
   * deque) if it is possible to do so immediately without violating capacity restrictions
   *
   * @param e the element to push
   * @throws NullPointerException if the specified element is null and this deque does not permit
   *                              null elements
   */

  private void linkFirst(E e) {
    final Node<E> f = first;
    final Node<E> newNode = new Node<>(null, e, f);
    first = newNode;
    if (f == null) {
      last = newNode;
    } else {
      f.prev = newNode;
    }
    size++;
  }

  @Override
  public void push(E e) {
    if (e == null) {
      throw new NullPointerException();
    }
    linkFirst(e);
  }

  /**
   * Retrieves, but does not remove, the head of the queue represented by this deque (in other
   * words, the first element of this deque), or returns {@code null} if this deque is empty.
   *
   * @return the head of the queue represented by this deque, or {@code null} if this deque is empty
   */
  public E getFirst() {
    final Node<E> f = first;
    if (f == null) {
      throw new NoSuchElementException();
    }
    return f.item;
  }

  @Override
  public E peek() {
    if (size == 0) {
      return null;
    }
    return getFirst();
  }
}
