/*
  a loop-list is a singly-linked list, which is
  either empty
  or its last node has a pointer back to that node itself. 
 */
public class LList { // loop-list
  Node header;
  int size;

  static class Node {
    int elem;
    Node next;
  }

  // pset3_1a
  // returns true if and only if
  // 1. this is a loop-list
  // 2. size is the number of nodes in this
  public boolean repOk() {

    // TODO: remove hard-coding
    return false;
  }

  // pset3_1b
  public void addFirst(int x) {
    // adds a new node with element x at the *head* of the list
    // all other list nodes remain unchanged
  }

  // pset3_1c
  public void addLast(int x) {
    // adds a new node with element x at the *tail* of the list
    // all other list nodes remain unchanged
  }

  // pset3_1c
  public String toString(){
    // returns a string representation of the list of elements in this,
    // where consecutive elements are separated by a space
    // TODO: remove hard-coding
    return null;
  }
}
