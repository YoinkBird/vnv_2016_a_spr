import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Arrays;

import org.junit.Test;

public class SortTester{
  //question a
  @Test (expected=IllegalArgumentException.class)
  public void t0() {
    Sort sortInst = new Sort();
    sortInst.sort(null);
  }

  // question b
  @Test public void t1() {
    int arr[] = new int[]{};
    System.out.println("array 0 elem:" + Arrays.toString(arr));
    Sort.sort(arr);
    assertTrue(arr.length == 0);
  }

  // question c
  @Test public void t2() {
    int arr[] = new int[]{1};
    System.out.println("array 1 elem:" + Arrays.toString(arr));
    Sort.sort(arr);
    assertTrue(arr.length == 1);
  }

}
