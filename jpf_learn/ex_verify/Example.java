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

    // track current test number
    final int ctrTest = 0;
    // track all verify.get* calls
    final int ctrGet = 1;

    int value = -1;
    int value2 = -1;
    // backtrack 1
    int seqLen = Verify.getInt(0, SEQUENCE_LENGTH - 1);
    Verify.incrementCounter(ctrGet);
    System.out.print("-D-: init: \n");
    System.out.print("branch: seqLen: " + seqLen);
    System.out.println();
    //String valArr[] = new String[seqLen];
    ArrayList<String> valArr = new ArrayList<String>();
    for(int i = 0; i <= seqLen; i++){
      Verify.setCounter(2,i); // record index before backtracking
      System.out.println();
      System.out.print("  pre-branch[getInt]:");
      System.out.println();
      // backtrack 2
      value = Verify.getInt(0, ELEM_UPPER_BOUND);
      Verify.incrementCounter(ctrGet);
      System.out.print("    branch[getInt]:");
      System.out.printf(" pre-i, i, getInt: %d|%d,%d", Verify.getCounter(2), i, value);

      System.out.print("\n      pre-branch[getBool]:");
      // backtrack 3
      String methodName = (Verify.getBoolean()) ? "addLast" : "addFirst";
      Verify.incrementCounter(ctrGet);
      System.out.print("\n        branch[getBool]:");

      //valArr[i] = String.format("%s %d", methodName, value);
      String methodCall =  String.format("%s %d", methodName, value);
      valArr.add(methodCall);
    } // end for-loop

    System.out.println();
    System.out.printf("@Test %02d {\n", Verify.getCounter(0)); // test number
    for(String val : valArr){
      System.out.print(val + " ");
    }
    System.out.printf("}"); // test number
    System.out.printf("//@Test %02d\n", Verify.getCounter(0)); // test number
    System.out.println();
    Verify.incrementCounter(ctrTest);
    if(seqLen > 3){
      System.out.println("END_FOR");
      System.out.printf("-I-: path %02d\n", Verify.getCounter(ctrGet)); // path number
    }
  }// </main>
  
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
   <seqlen=0>
@Test public void test0() { LList l = new LList(); }
   </seqlen=0>
   <seqlen=1>
@Test public void test1() { LList l = new LList(); l.addLast(0); }
@Test public void test2() { LList l = new LList(); l.addLast(1); }
@Test public void test3() { LList l = new LList(); l.addFirst(0); }
@Test public void test4() { LList l = new LList(); l.addFirst(1); }
   </seqlen=1>
   <seqlen=2>
@Test public void test5() { LList l = new LList(); l.addLast(0); l.addLast(0); }
@Test public void test6() { LList l = new LList(); l.addLast(0); l.addLast(1); }
@Test public void test7() { LList l = new LList(); l.addLast(0); l.addFirst(0); }
   <.../>
   </seqlen=2>
   <.../>
 </bound=1>
*/
