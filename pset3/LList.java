import java.util.*;
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

    public String reprSimple(){
      String txt = this.toString();
      txt += "[";
      txt += "int:" + this.elem;
      txt += "]";
      return txt;
    }
    public String repr(){
      String txt = this.toString();
      txt += "[";
      txt += "int:" + this.elem + "|";
      if(this.next == this){
        txt += "->" + "self";
      }else{
        txt += "->" + this.next.toString();
      }
      txt += "]";
      return txt;
    }

  }

  // generate add-first list
  public LList generateTestList(int[] values){
    LList l = new LList();
    l.size = values.length;
    for(int i = 0; i < values.length; i++){
      Node n = new Node();
      n.elem = values[i];
      if(i == 0){
        n.next = n;
      }else{
        n.next = l.header;
      }
      l.header = n;
    }
    return l;
  }

  // pset3_1a
  // returns true if and only if
  // 1. this is a loop-list
  // 2. size is the number of nodes in this
  public boolean repOk() {
    // either empty
    if (header == null) return size == 0;
    // track visited nodes
    Set<Node> visited = new HashSet<Node>();
    // iterator
    Node current = header;

    // or its last node has a pointer back to that node itself.
    int elemCtr = 0; //safeguard against infinite loops
    while(elemCtr <= size){
      elemCtr++;
      // add current node to visited nodes
      //   Set.add() Returns: true if this set did not already contain the specified element
      if (!visited.add(current)) return false;

      // done when last node points to itself
      if(current.next == current){
        break;
      }
      // advance through loop
      current = current.next;
    }
    return size == visited.size();
  }

  // pset3_1b
  public void addFirst(int x) {
    // adds a new node with element x at the *head* of the list
    // all other list nodes remain unchanged
    Node n = new Node();
    n.elem = x;
    n.next = header;
    // the first node is also the terminal node:
    // if this is first addition (header == null)
    // the current node needs a self-loop
    // as it will be the terminal node as well
    if(n.next == null){
      n.next = n;
    }

    header = n;
    size++;
  }

  // pset3_1c
  public void addLast(int x) {
    // adds a new node with element x at the *tail* of the list
    // all other list nodes remain unchanged
  }

  public String repr(){
    // returns a string representation of the list of elements in this,
    // in which each node's elem is printed and the last node is indicated as pointing to self
    String repr = "";
    // iterator
    Node current = header;
    repr += header.reprSimple();
    while(current.next != current){
      current = current.next;
      repr += "->" + current.reprSimple();
      // indicate last node
      if(current.next == current){
        repr += "->" + "self";
      }
    }
    return repr;
  }

  // pset3_1c
  public String toString(){
    // returns a string representation of the list of elements in this,
    // where consecutive elements are separated by a space
    String repr = Integer.toString(header.elem);
    // iterator
    Node current = header;
    while(current.next != current){
      current = current.next;
      repr += " " + current.elem;
    }
    return repr;
  }
}
