import org.junit.*;
//import org.junit.Test;
import static org.junit.Assert.*;
// for printing out testNames
import org.junit.rules.*;
import org.junit.runner.*;

public class LListTester{
  @Rule
  public TestRule watcher = new TestWatcher() {
    protected void starting(Description description) {
      System.out.println("Starting test: " + description.getMethodName());
    }
  };
  @Test public void testRepOk0Nodes() {
    LList l1 = new LList();
    int values[] = new int[]{};
    LList l = l1.generateTestList(values);
    assertTrue(l.repOk());
    assertTrue(values.length == l.size);
  }

  @Test public void testRepOk1Nodes() {
    LList l1 = new LList();
    int values[] = new int[]{0};
    LList l = l1.generateTestList(values);
    assertTrue(l.repOk());
    assertTrue(values.length == l.size);
  }

  @Test public void testRepOk4Nodes() {
    LList l1 = new LList();
    int values[] = new int[]{0,1,2,4};
    LList l = l1.generateTestList(values);
    assertTrue(l.repOk());
    assertTrue(values.length == l.size);
  }

  @Test (expected=NullPointerException.class)
    public void testRepOk4NodesFailTail() {
    LList l1 = new LList();
    int values[] = new int[]{0,1,2,4};
    // corrupt 'tail'
    LList l = l1.generateTestList(values,1);
//    System.out.println("LList.repr:" + l.repr());
//    expected: java.lang.NullPointerException
    assertTrue(l.repOk());
    // create mismatch
    l.size--;
    assertFalse(values.length == l.size);
  }

  @Test public void testRepOk4NodesFail() {
    LList l1 = new LList();
    int values[] = new int[]{0,1,2,4};
    // bad string
    LList l = l1.generateTestList(values,2);
    // create mismatch
    l.size--;
    assertFalse(values.length == l.size);
  }

  @Test public void testStringMthds() {
    LList l1 = new LList();
    int values[] = new int[]{0,1,2,3};
    String expected = "3 2 1 0";
    LList l = l1.generateTestList(values);
    assertTrue(l.repOk());

    System.out.println("LList.repr:" + l.repr());
    /*
    System.out.println("LList.toString:[" + l.toString() + "]");
    */
    assertEquals(l.toString(),expected);
  }

  @Test public void addFirst_test_toString() {
    LList l = new LList();
    int values[] = new int[]{0,1,2,3};
    for(int n : values){
      l.addFirst(n);
    }
    assertEquals(l.toString(),"3 2 1 0");
    assertTrue(l.repOk());
  }

  // pset2a JUnit Tests [6 points]
  /*
    Write some JUnit tests such that each test makes exactly one invocation of addLast
    and running all the tests provides full statement coverage for the method addLast
  */
  @Test public void test0(){
  }
  @Test public void test1(){
  }

  @Test public void addLast_test_toString() {
    LList l = new LList();
    int values[] = new int[]{0,1,2,3};
    String expected = "0 1 2 3";
    for(int n : values){
      System.out.println("adding " + n);
      l.addLast(n);
    }
    System.out.println("LList.toString:[" + l.toString() + "]");
    System.out.println("LList.repr:" + l.repr());

    assertEquals(expected, l.toString());
    assertTrue(values.length == l.size);
    assertTrue(l.repOk());
  }

  // JPF test generator [14 points]
  /*
    Implement the following main method such that running it using the JPF JVM
    generates all method sequences of length up to SEQUENCE_LENGTH,
    where the ﬁrst method in each sequence is a constructor call,
    which is followed by up to SEQUENCE_LENGTH - 1 invocations of addFirst or addLast,
    and each invocation of addFirst and addLast uses only
        integers {0, ..., ELEM_UPPER_BOUND} as parameter values

    To illustrate, executing your main method using JPF JVM for main arguments ["3", "1"]
      should produce 21 JUnit tests
    e.g. test[0...21] { LList 1 = new LList():
      1,2: l.addLast(0); | 2: l.addFirst(0);
      3,4: l.addLast(1); | 4: l.addFirst(1);
      5: l.addLast(0); l.addLast(0);
      6: l.addLast(0); l.addLast(1);
      7: l.addLast(1); l.addLast(0);
      8: l.addLast(1); l.addLast(1);
      9-12: repeate 5-8 for 'addFirst'
      13-21: permutations 'addLast' and 'addFirst'



    Hint: The JPF class Verify provides methods,
      such as resetCounter, getCounter, and incrementCounter,
      to implement a counter whose value is not reset during backtracking.
   */
  public static void main(String[] a){
    if (a.length != 2) throw new IllegalArgumentException();
    final int SEQUENCE_LENGTH  = Integer.parseInt(a[0]);
    final int ELEM_UPPER_BOUND = Integer.parseInt(a[1]);
  }
}
