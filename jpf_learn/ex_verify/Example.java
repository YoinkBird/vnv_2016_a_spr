package ex_verify;
/*
 * run:
javac -g examples/Example.java && java -jar ~/workspace/jpf/jpf-core/build/RunJPF.jar examples/Example.jpf
 */
import gov.nasa.jpf.vm.Verify;

public class Example {

  public static void main (String[] args) {
    Example ex = new Example();
    //Verify.incrementCounter(1);
    final int SEQUENCE_LENGTH  = 3; // Integer.parseInt(a[0]);
    final int ELEM_UPPER_BOUND = 1; // Integer.parseInt(a[1]);
    // verify api
    /*
    boolean cond = Verify.getBoolean();
    System.out.println("BOOL: " + cond);
    */
    // counter - id seems to just be to track number of counters
    int ctr1 = Verify.getCounter(0);
    int ctr2 = Verify.getCounter(1);

    // which value
    int value = Verify.getInt(0, ELEM_UPPER_BOUND);
    Verify.incrementCounter(0);
    // decide which method to test
    if(Verify.getBoolean()){
      System.out.printf("@Test %s\n", Verify.getCounter(0)); // test number
      System.out.printf("\taddLast %d", value);
    }
    else{
      System.out.printf("@Test %s\n", Verify.getCounter(0)); // test number
      System.out.printf("\taddFirst %d", value);
    }
    Verify.incrementCounter(0);
    System.out.println();




    /**********************/
    //ex.loopMess(SEQUENCE_LENGTH, ELEM_UPPER_BOUND);

  }
  
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
