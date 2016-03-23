package examples;
/*
 * run:
javac -g examples/Example.java; java -jar ~/workspace/jpf/jpf-core/build/RunJPF.jar examples/Example.jpf 
 */

public class Example {

  public static void main (String[] args) {
    Example ex = new Example();
    ex.foo(2, 1);
    ex.addLast(1);
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
    while(true){
      if (x > y){
        System.out.println("First");
        break;
      }else{
        System.out.println("Second");
        break;
      }
    }
  }

}
