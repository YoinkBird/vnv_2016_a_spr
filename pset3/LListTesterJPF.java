import java.util.*;
import gov.nasa.jpf.vm.Verify;
// run with
// java -jar ~/workspace/jpf/jpf-core/build/RunJPF.jar LListTesterJPF.jpf
// or
// jpf-core/bin/jpf +classpath=. LListTesterJPF 3 1
// or
// java -jar ${JPF_HOME}/jpf-core/build/RunJPF.jar  +classpath=. LListTesterJPF 3 1
// src: http://babelfish.arc.nasa.gov/trac/jpf/wiki/user/run#command-line
public class LListTesterJPF{
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

    LListTesterJPF test = new LListTesterJPF();
    final String debugSpacer1 = "  -D-:";

    test.calcNumTestsAndPrint(SEQUENCE_LENGTH);

    // track current test number
    final int ctrTest = 0;
    // track all verify.get* calls
    final int ctrGet = 1;

    int value = -1;
    // backtrack 1
    int seqLen = Verify.getInt(0, SEQUENCE_LENGTH - 1);
    Verify.incrementCounter(ctrGet);
    System.out.print(debugSpacer1 + "init: \n");
    System.out.print(debugSpacer1 + "branch: seqLen: " + seqLen);
    // store values
    ArrayList<String> valArr = new ArrayList<String>();
    for(int i = 0; i < seqLen; i++){
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
    // zero index to store total, all others for numTests per length
    int numTests[] = new int[SEQUENCE_LENGTH+1];
    numTests[0] = 0;
    for(int i = 1; i < numTests.length; i++){
      // sequences start at zero but loop starts at 1
      int seqNumber = i - 1;
      // 4 possibilities of binary choice (addFirst||addLast and 0||1)
      numTests[i] = (int) Math.pow(4, seqNumber);
      // update total number tests
      numTests[0] += numTests[i];
      //debug// System.out.printf("-II-: %03d tests with %d method calls\n", numTests[i], i);
    }
    return numTests;
  }
  // print out expected number of tests
  public int[] calcNumTestsAndPrint(int SEQUENCE_LENGTH){
    // calculate total num of tests
    int numTests[] = this.calcNumTests(SEQUENCE_LENGTH);
    for(int i = 1; i < numTests.length; i++){
      System.out.printf("-I-: %03d tests with %d method calls\n", numTests[i], i-1);
    }
    System.out.printf("-I-: %03d tests will be generated for seqLen %d\n", numTests[0], SEQUENCE_LENGTH);
    return numTests;
  }
}
