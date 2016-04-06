import org.junit.*;
//import org.junit.Test;
import static org.junit.Assert.*;
// for printing out testNames
import org.junit.rules.*;
import org.junit.runner.*;

/* build:
   * set classpath  to '.' and junit.jar
   * run javac
   * if passes,
   * run java
env CLASSPATH=.:/home/yoinkbird/workspace/res/junit/4.10/junit.jar sh -cx  'buildfile=LListTester; javac -g ${buildfile}.java && java org.junit.runner.JUnitCore $buildfile'
 */

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
    assertEquals(expected, l.toString());
  }

  @Test public void addFirst_test_toString() {
    LList l = new LList();
    int values[] = new int[]{0,1,2,3};
    String expected = "3 2 1 0";
    for(int n : values){
      l.addFirst(n);
    }
    assertEquals(expected, l.toString());
    assertTrue(l.repOk());
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

  // pset2a JUnit Tests [6 points]
  /*
    Write some JUnit tests such that each test makes exactly one invocation of addLast
    and running all the tests provides full statement coverage for the method addLast
  */
  @Test public void test0(){
    LList l = new LList();
    int values[] = new int[]{};
    String expected = "";
    for(int n : values){
      l.addLast(n);
    }

    assertEquals(expected, l.toString());
    assertTrue(values.length == l.size);
    assertTrue(l.repOk());
  }
  @Test public void test1(){
    LList l = new LList();
    int values[] = new int[]{0};
    String expected = "0";
    for(int n : values){
      l.addLast(n);
    }

    assertEquals(expected, l.toString());
    assertTrue(values.length == l.size);
    assertTrue(l.repOk());
  }
}
