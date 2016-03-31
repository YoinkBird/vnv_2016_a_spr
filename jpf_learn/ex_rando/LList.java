package ex_rando;
import gov.nasa.jpf.vm.Verify;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class LList
{ // loop-list
    Node header;
    int size;

    static class Node
    {
        int elem;
        Node next;
    }

    public LList()
    {
        header = new Node();
        header.next = header;
    }

    // Part 1A: Class Invariant
    public boolean repOk()
    {
        // returns true if and only if (1) this is a loop-list and
        // (2) size is the number of nodes in this

        // Condition 1 This is a loop list:
        // List must contain
        int nodeCount = 0;
        Node positionInList;
        positionInList = header;

        // header can't be null
        if (header == null)
        {
            return false;
        }

        while (positionInList != null)
        {
            // check if the node points to itself
            if (positionInList.next == positionInList)
            {
                // you've reached the end of the list so break and check sizes/Nodes and elements
                break;

            }
            // increment the count of the nodes
            nodeCount++;
            // increment pointer so its pointing to the next node in the list
            positionInList = positionInList.next;

        }

        // if it points to itself it must be the last node in the list
        // The iteration count should be equal to size && the node and its next must point to the
        // same element
        if (nodeCount == size && positionInList.elem == positionInList.next.elem)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // Part B: Add First
    // adds a new node with element x at the *head* of the list; all other list nodes
    // remain unchanged
    public void addFirst(int x)
    {
        Node node = new Node();
        node.elem = x;
        // if the header points to itself then its an empty list.
        // In this case the node should be added to the list and the node should point to itself
        if (header.next == header)
        {
            header.next = node;
            node.next = node;
            size += 1; // increment size
        }
        else
        {
            // The list is non empty, the new node should point to the rest of the list
            node.next = header.next;
            header.next = node;
            size += 1;
        }
    }

    // Part C: Add Last
    // adds a new node with element x at the *tail* of the list; all other list nodes
    // remain unchanged
    public void addLast(int x)
    {
        Node node = new Node();
        node.elem = x;

        // 1st find the end of the list
        int nodeCount = 0;
        Node positionInList;
        positionInList = header;

        while (positionInList.next != positionInList)
        // while (positionInList.next != null)
        {
            // check if the node points to itself
            // if(positionInList.next == positionInList)
            // {
            // //you've reached the end of the list so break and check sizes/Nodes and elements
            // break;
            //
            // }

            // increment the count of the nodes
            nodeCount++;
            // increment pointer so its pointing to the next node in the list
            positionInList = positionInList.next;

        }
        positionInList.next = node;
        // node = positionInList.next;
        node.next = node;
        size += 1;
    }

    // Part 4D:
    // returns a string representation of the list of elements in this, where
    // consecutive elements are separated by a space
    public String toString()
    {

        String elements = "";

        Node node;
        node = header;
        // If the header points to itself then the list is empty
        if (header.next == header)
        {
            elements = "List is Empty";
            return elements;
        }
        // If the node has a next we need to continue to transverse the list
        while (node.next != null)
        {
            if (node.next == node)
            {
                // Reached the end of the list
                // elements += node.elem + " ";
                break;
            }
            node = node.next;
            elements += node.elem + " ";
            // node = node.next;
        }

        elements = elements.trim();

        return elements;

    }

    public static void main(String a[])
    {
        if (a.length != 2)
            throw new IllegalArgumentException();
        final int SEQUENCE_LENGTH = Integer.parseInt(a[0]);
        final int ELEM_UPPER_BOUND = Integer.parseInt(a[1]);

//        Integer[] elements = new Integer[ELEM_UPPER_BOUND + 1];
//        for (int i = 0; i < elements.length; i++)
//        {
//            elements[i] = i;
//        }

        int seqLength = 0;
        String className = LList.class.getSimpleName();
        int testCounter = 0;
        Verify.setCounter(testCounter, 0);

        // methodCounter = 1
        int methodCounter = 1;
        Verify.setCounter(methodCounter, 0);
        testCounter = 0;

        int count = 0;

        // Attempt 2 -------------

            int mc = Verify.getCounter(methodCounter);
            seqLength = Verify.random(SEQUENCE_LENGTH);
            System.out.print("\t\t\t\t-D-: START: ");
            System.out.print(" seqLength: " + seqLength);
            System.out.print(" methodCounter: " + Verify.getCounter(methodCounter));
            System.out.print(" testCounter: " + Verify.getCounter(testCounter));
            System.out.println();


            if (Verify.getCounter(methodCounter) < seqLength)
            {
                //System.out.println("\tMethodCounter = " + Verify.getCounter(methodCounter));
               //System.out.println("@Test public void test" + Verify.getCounter(testCounter) + "(){");
               //System.out.println("\t" + className + " l = new " + className + "();");
                if (Verify.randomBool())
                {
                    //System.out.println("\tMethodCounter = " + Verify.getCounter(methodCounter));
                    System.out.println("\t" + "l.addFirst(" + Verify.random(ELEM_UPPER_BOUND) + ");");
                    Verify.resetCounter(methodCounter);
                }
                else
                {
                    //System.out.println("\tMethodCounter = " + Verify.getCounter(methodCounter));
                    System.out.println("\t" + "l.addLast(" + Verify.random(ELEM_UPPER_BOUND) + ");");
                    Verify.resetCounter(methodCounter);
                }
                Verify.incrementCounter(methodCounter);
                System.out.print("\t\t\t\t\t-D-: mid: ");
                System.out.println(" methodCounter: " + Verify.getCounter(methodCounter));

                
            }
            else{
                System.out.println("@Test public void test" + Verify.getCounter(testCounter) + "(){");
                System.out.println("\t" + className + " l = new " + className + "();");
            }
            System.out.print("\t\t\t\t\t-D-: after: ");
            System.out.print(" methodCounter: " + Verify.getCounter(methodCounter));
            System.out.print(" testCounter: " + Verify.getCounter(testCounter));
            System.out.println();
            Verify.incrementCounter(testCounter);
            System.out.println("}");
            System.out.println("@Test public void test" + Verify.getCounter(testCounter) + "(){");
            System.out.println("\t" + className + " l = new " + className + "();");
            //Verify.incrementCounter(testCounter);


        
    }
}
