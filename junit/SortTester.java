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

  // question e
  @Test public void test3(){
    int arr[] = new int[4];
    int arr2[] = new int[4];
    //1
    Random rand = new Random();
    for(int i = 0; i < arr.length; i++){
      arr[i] = rand.nextInt();
      arr2[i] = arr[i];
    }
    System.out.println("array 4 elem:" + Arrays.toString(arr));
    //2
    Sort.sort(arr);
    System.out.println("arr  4 elem:" + Arrays.toString(arr));
    System.out.println("arr2 4 elem:" + Arrays.toString(arr2));
    //3
    assertTrue( Sort.checkSort(arr2,arr) );
  }
  @Test public void test4(){
    int arr[] = new int[4];
    int arr2[] = new int[4];
    //1
    Random rand = new Random();
    for(int i = 0; i < arr.length; i++){
      arr[i] = rand.nextInt();
      arr2[i] = arr[i];
    }
    //2
    Sort.sort(arr);
    System.out.println("arr  4 elem:" + Arrays.toString(arr));
    System.out.println("arr2 4 elem:" + Arrays.toString(arr2));
    //3
    assertTrue( Sort.isPermutation(arr,arr2) );
  }

  @Test public void test5(){
    int arr[] = new int[4];
    int arr2[] = new int[4];
    //1
    Random rand = new Random();
    for(int i = 0; i < arr.length; i++){
      arr[i] = rand.nextInt();
      arr2[i] = arr[i];
    }
    //2
    Sort.sort(arr);
    System.out.println("arr  4 elem:" + Arrays.toString(arr));
    System.out.println("arr2 4 elem:" + Arrays.toString(arr2));
    //3
    assertTrue( Sort.isOrdered(arr) );
  }

}
