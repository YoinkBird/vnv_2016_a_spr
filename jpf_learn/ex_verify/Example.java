package ex_verify;
import java.util.*;
/*
 * run:
javac -g examples/Example.java && java -jar ~/workspace/jpf/jpf-core/build/RunJPF.jar examples/Example.jpf
 */
import gov.nasa.jpf.vm.Verify;

public class Example {

  public static void main (String[] args) {
    Example ex = new Example();
    final int SEQUENCE_LENGTH  = 3; // Integer.parseInt(a[0]);
    final int ELEM_UPPER_BOUND = 1; // Integer.parseInt(a[1]);
    final String debugSpacer1 = "  -D-:";

    ex.calcNumTestsAndPrint(SEQUENCE_LENGTH);

    // track current test number
    final int ctrTest = 0;
    // track all verify.get* calls
    final int ctrGet = 1;

    int value = -1;
    int value2 = -1;
    // backtrack 1
    int seqLen = Verify.getInt(0, SEQUENCE_LENGTH - 1);
    Verify.incrementCounter(ctrGet);
    System.out.print(debugSpacer1 + "init: \n");
    System.out.print(debugSpacer1 + "branch: seqLen: " + seqLen);
    System.out.println();
    // store values
    ArrayList<String> valArr = new ArrayList<String>();
    for(int i = 0; i <= seqLen; i++){
      Verify.setCounter(2,i); // record index before backtracking
      System.out.println();
      System.out.print(debugSpacer1 + "  pre-branch[getInt]:");
      System.out.println();
      // backtrack 2
      value = Verify.getInt(0, ELEM_UPPER_BOUND);
      Verify.incrementCounter(ctrGet);
      System.out.print(debugSpacer1 + "    branch[getInt]:");
      System.out.printf(" pre-i, i, getInt: %d|%d,%d", Verify.getCounter(2), i, value);

      System.out.println();
      System.out.print(debugSpacer1 + "      pre-branch[getBool]:\n");
      // backtrack 3
      String methodName = (Verify.getBoolean()) ? "addLast" : "addFirst";
      Verify.incrementCounter(ctrGet);
      System.out.print(debugSpacer1 + "        branch[getBool]:");

      // format: l.<addLast|addFirst>(<0|1>)
      String methodCall =  String.format("l.%s(%d)", methodName, value);
      valArr.add(methodCall);
    } // end for-loop

    System.out.println();
    System.out.printf("@Test public void test%02d() { ", Verify.getCounter(0)); // test number
    //System.out.println(); // comment for test body on one-line
    System.out.print("LList l = new LList(); ");
    //System.out.println(); // comment for test body on one-line
    for(String val : valArr){
      System.out.print(val + "; ");
    }
    System.out.printf("}"); // test number
    System.out.printf("//@Test %02d\n", Verify.getCounter(0)); // test number

    Verify.incrementCounter(ctrTest);
    if(seqLen > 3){
      System.out.println("END_FOR");
      System.out.printf("-I-: path %02d\n", Verify.getCounter(ctrGet)); // path number
    }
  }// </main>

  // numTests[0] has total number of tests
  public int[] calcNumTests(int SEQUENCE_LENGTH){
    // calculate total num of tests
    int numTests[] = new int[SEQUENCE_LENGTH+1];
    numTests[0] = 0;
    for(int i = 1; i < SEQUENCE_LENGTH+1; i++){
      // 4 possibilities of binary choice (addFirst||addLast and 0||1)
      numTests[i] = (int) Math.pow(4, i);
      // update total number tests
      numTests[0] += numTests[i];
    }
    return numTests;
  }
  // print out expected number of tests
  public int[] calcNumTestsAndPrint(int SEQUENCE_LENGTH){
    // calculate total num of tests
    int numTests[] = this.calcNumTests(SEQUENCE_LENGTH);
    for(int i = 1; i < numTests.length; i++){
      System.out.printf("-I-: %03d tests with %d method calls\n", numTests[i], i);
    }
    System.out.printf("-I-: %03d tests will be generated for seqLen %d\n", numTests[0], SEQUENCE_LENGTH-1);
    return numTests;
  }
  
  /**********************/
  //ex.loopMess(SEQUENCE_LENGTH, ELEM_UPPER_BOUND);
  public void loopMess(int SEQUENCE_LENGTH, int ELEM_UPPER_BOUND){
    int counter = 0;
    Verify.resetCounter(0);
    Verify.resetCounter(1);
    for(int i = 0; i < SEQUENCE_LENGTH; i++){
      counter = Verify.getCounter(0);
      for(int j = counter; j <= ELEM_UPPER_BOUND; j++){
        System.out.println("@Test");
        System.out.println("LList 1 = new LList();");
        if(counter >0 && counter <= ELEM_UPPER_BOUND){
          System.out.println("addLast(" + counter + ")");
        }
        Verify.incrementCounter(0);
      }
      Verify.resetCounter(0);
    }
  }

  public void printTest(int i){
    System.out.print("list.addLast(" + i);
    System.out.println(")");
  }

  // simple conditional
  public int foo(int x, int y){
    if (x > y){
      System.out.println("First");
      return x;
    }else{
      System.out.println("Second");
      return y;
    }
  } // end conditional

  // demonstrate while-loop
  public void addLast(int x) {
    int y = 2;
    // adds a new node with element x at the *tail* of the list
    // all other list nodes remain unchanged

    if(x>1){
      System.out.println("5");
    }
    // iterate
    while(y > 0){
      if(x < 1){
        x++;
      }
      y--;
    }
  }

}
// sample output
/*
 <bound=1>
   <seqlen=2>
@Test public void test0() { LList l = new LList(); }
   </seqlen=2>
   <seqlen=3>
@Test public void test1() { LList l = new LList(); l.addLast(0); }
@Test public void test2() { LList l = new LList(); l.addLast(1); }
@Test public void test3() { LList l = new LList(); l.addFirst(0); }
@Test public void test4() { LList l = new LList(); l.addFirst(1); }
   </seqlen=3>
   <seqlen=4>
@Test public void test5() { LList l = new LList(); l.addLast(0); l.addLast(0); }

@Test public void test6() { LList l = new LList(); l.addLast(0); l.addLast(1); }
@Test public void test7() { LList l = new LList(); l.addLast(0); l.addFirst(0); }
   <.../>
   </seqlen=4>
   <.../>
 </bound=1>
*/
