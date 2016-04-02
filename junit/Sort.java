import java.util.*;
public class Sort{
  public static void sort(int[] arr){
    if(arr == null) throw new IllegalArgumentException();
    Arrays.sort(arr);
  }

  static boolean checkSort(int[] in, int[] out){
    if(in  == null) throw new IllegalArgumentException();
    if(out == null) throw new IllegalArgumentException();

    return isOrdered(out) && isPermutation(in,out);
  }

  static int count(int[] arr, int x){
    if(arr == null) throw new IllegalArgumentException();
    int counter = -1;
    for(int i:arr){
      if(i==x)counter++;
    }
    return counter;

  }
  static boolean isPermutation(int[] a1, int[] a2){
    if(a1 == null) throw new IllegalArgumentException();
    if(a2 == null) throw new IllegalArgumentException();

    HashSet<Integer> things = new HashSet<Integer>();
    for(int i:a1){
      things.add(i);
    }

    for(int i:things){
      if(count(a1,i) != count(a2,i)){
        return false;
      }
    }

    return true;
  }
  static boolean isOrdered(int[] a1){
    if(a1==null) throw new IllegalArgumentException();
    for(int i = 1; i < a1.length; i++){
      if(a1[i] < a1[i-1]){
        return false;
      }
    }

    return true;
  }
}
