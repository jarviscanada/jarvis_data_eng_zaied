package ca.jrvs.practice.dataStructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;

/**
 * A simplified BST implementation
 *
 * @param <E> type of object to be stored
 */
public class JBSTree<E> implements JTree<E> {

  /**
   * The comparator used to maintain order in this tree map
   * Comparator cannot be null
   */
  private Comparator<E> comparator;
  private int size=0;
  /**
   * Create a new BST
   *
   * @param comparator the comparator that will be used to order this map.
   * @throws IllegalArgumentException if comparator is null
   */
  public JBSTree(Comparator<E> comparator) {
    this.comparator = comparator;
  }

  /**
   * Insert an object into the BST.
   * Please review the BST property.
   *
   * @param object item to be inserted
   * @return inserted item
   * @throws IllegalArgumentException if the object already exists
   */

  private Node root=null;

  //method to get root
  public Node<E> getRoot()
  {
    return root;
  }

  //recursive helper to insert
  private Node<E> insertHelper(Node root, Node parent, E object)
  {
    if(root==null)
    {
      return new Node<E>(object,parent);
    }
    int cmp = comparator.compare((E) root.getValue(),object);
    if(cmp == 0)
    {
      return null;
    }
    if(cmp < 0)
    {
      root.setLeft(insertHelper(root.getLeft(),root,object));
    }
    else if (cmp > 0)
    {
      root.setRight(insertHelper(root.getRight(),root, object));
    }
    return root;
  }
  @Override
  public E insert(E object) {
    if(size == 0)
    {
      Node<E> temp = new Node<E>(object,null);
      root = temp;
    }
    else
    {
      //handle root
      Node<E> temp = insertHelper(root,null,object);
      if(temp==null)
      {
        throw new IllegalArgumentException();
      }
      root=temp;
    }
    size++;
    return object;
  }

  /**
   * Search and return an object, return null if not found
   *
   * @param object to be found
   * @return the object if exists or null if not
   */

  //recursive helper function to search
  private E searchHelper(Node<E> root, E object)
  {
    if(root==null)
    {
      return null;
    }
    if(comparator.compare((E) root.getValue(),object) == 0)
    {
      return object;
    }
    E res;
    res = searchHelper(root.getLeft(),object);
    res= searchHelper(root.getRight(),object);
    return res;
  }
  @Override
  public E search(E object) {
    return (E) searchHelper(root, object);
  }

  //recursive funtion to help delete helper
  private void removeHelperHelper(Node<E> orig, Node<E> toSet)
  {
    if(orig.getLeft()==null)
    {
      orig.setLeft(toSet);
      return;
    }
    removeHelperHelper(orig.getLeft(),toSet);
  }
  //recursive helper function to remove
  private Node<E> removeHelper(Node<E> root, E object)
  {
    if(root==null)
    {
      return null;
    }
    if(comparator.compare(root.getValue(),object)==0)
    {

      if(root.getLeft()==null && root.getRight()==null)
      {
        return null;
      }
      if(root.getLeft()!=null && root.getRight()==null)
      {
        return root.getLeft();
      }
      if(root.getLeft()!=null)
      {
        removeHelperHelper(root.getRight(),root.getLeft());
      }
      return root.getRight();
    }
    else if(comparator.compare(root.getValue(),object) > 0)
    {
      root.setRight(removeHelper(root.getRight(),object));
    }
    else
    {
      root.setLeft(removeHelper(root.getLeft(),object));
    }
    return root;
  }
  /**
   * Remove an object from the tree.
   *
   * @param object to be removed
   * @return removed object
   * @throws IllegalArgumentException if the object not exists
   */
  @Override
  public E remove(E object) {
    if(removeHelper(root,object) == null)
    {
      return null;
    }
    return object;
  }

  private void preorderHelper(Node<E> root, E[] preArr, int k)
  {
    if(root==null)
    {
      return;
    }
    preArr[k]=root.getValue();
    preorderHelper(root.getLeft(),preArr,k+1);
    preorderHelper(root.getRight(),preArr,k+2);
  }

  /**
   * traverse the tree recursively
   *
   * @return all objects in pre-order
   */

  @Override
  public E[] preOrder() {
    E[] preArr= (E[]) new Object[size];
    preorderHelper(root,preArr,0);
    return preArr;
  }

  private int inCount=0;
  private void inorderHelper(Node<E> root, E[] inArr)
  {
    if(root==null)
    {
      return;
    }
    //System.out.print(root.getValue());
    inorderHelper(root.getLeft(),inArr);
    inArr[inCount]=root.getValue();
    inCount=inCount+1;
    inorderHelper(root.getRight(),inArr);
  }

  /**
   * traverse the tree recursively
   *
   * @return all objects in-order
   */
  @Override
  public E[] inOrder() {
    E[] inArr= (E[]) new Object[size];
    inorderHelper(root,inArr);
    return inArr;
  }

  private int postCount=0;
  private void postorderHelper(Node<E> root, E[] postArr)
  {
    if(root==null)
    {
      return;
    }
    postorderHelper(root.getLeft(),postArr);
    postorderHelper(root.getRight(),postArr);
    postArr[postCount]=root.getValue();
    postCount=postCount+1;
  }
  /**
   * traverse the tree recursively
   *
   * @return all objects pre-order
   */
  @Override
  public E[] postOrder() {
    E[] postArr= (E[]) new Object[size];
    postorderHelper(root,postArr);
    return postArr;
  }

  //recursive function to help findHeight
  private int findheightHelper(Node<E> root)
  {
    if(root==null)
    {
      return 0;
    }
    return 1+Math.max(findheightHelper(root.getLeft()),findheightHelper(root.getRight()));
  }
  /**
   * traverse through the tree and find out the tree height
   * @return height
   * @throws NullPointerException if the BST is empty
   */
  @Override
  public int findHeight() {
    return findheightHelper(root);
  }

  static final class Node<E> {

    E value;
    Node<E> left;
    Node<E> right;
    Node<E> parent;

    public Node(E value, Node<E> parent) {
      this.value = value;
      this.parent = parent;
    }

    public E getValue() {
      return value;
    }

    public void setValue(E value) {
      this.value = value;
    }

    public Node<E> getLeft() {
      return left;
    }

    public void setLeft(Node<E> left) {
      this.left = left;
    }

    public Node<E> getRight() {
      return right;
    }

    public void setRight(Node<E> right) {
      this.right = right;
    }

    public Node<E> getParent() {
      return parent;
    }

    public void setParent(Node<E> parent) {
      this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Node)) {
        return false;
      }
      Node<?> node = (Node<?>) o;
      return getValue().equals(node.getValue()) &&
          Objects.equals(getLeft(), node.getLeft()) &&
          Objects.equals(getRight(), node.getRight()) &&
          getParent().equals(node.getParent());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getValue(), getLeft(), getRight(), getParent());
    }
  }

}
