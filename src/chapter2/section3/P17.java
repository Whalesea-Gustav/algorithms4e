package chapter2.section3;

import org.junit.Test;
import java.util.Arrays;

public class P17 {
    //quickSort with sentinal

    public static void shuffle(Comparable[] arr) {
        //make sentinal at the end of arr
        Comparable max = arr[0];
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (max.compareTo(arr[i]) <0) {
                maxIndex = i;
                max = arr[i];
            }
        }

        arr[maxIndex] = arr[arr.length-1];
        arr[arr.length-1] = max;


        for (int i = 0; i < arr.length - 1; i++) {
            int k = i + (int) (Math.random() * (arr.length - i - 1));
            // Math.random() = [0, 1)
            //Math.random * (n-i-1) + i
            // = [0, 1) * (n - i - 1) + i
            // =[i, n-1);
            Comparable temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
        }
    }
    @Test
    public void testForShuffle() {
        Integer[] arr = {10, 8, 7, 6, 5, 4, 3, 2 ,1 ,6, 12, 5, 4 ,3 ,2 ,1};
        shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(Comparable[] arr) {
        shuffle(arr);
        System.out.println(Arrays.toString(arr));
        quickSort_partitionWithSentinal(arr, 0, arr.length-1);
    }

    public static void quickSort_partitionWithSentinal(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) return;  // if less thant k [5, 15], can apply insertion sort
        int j = partitionWithSentinal(arr, lo, hi);
        quickSort_partitionWithSentinal(arr, lo, j-1);
        quickSort_partitionWithSentinal(arr, j+1, hi);
    }
    public static int partitionWithSentinal(Comparable[] arr, int lo, int hi) {
        int i = lo; int j = hi + 1;
        Comparable v = arr[lo];
        while (true) {
            while (v.compareTo(arr[++i]) > 0) ;
            while (v.compareTo(arr[--j]) < 0) ;

            if (i >= j) break;

            Comparable temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        arr[lo] = arr[j];
        arr[j] = v;
        return j;
    }

    @Test
    public void testForSentinal() {
        Integer[] arr = {10, 8, 7, 6, 5, 4, 3, 2 ,1 ,6, 12, 5, 4 ,3 ,2 ,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
