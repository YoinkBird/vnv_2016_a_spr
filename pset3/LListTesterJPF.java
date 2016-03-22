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

  }
}
