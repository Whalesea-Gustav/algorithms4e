package chapter2.section2;

import org.junit.Test;
import java.util.Arrays;

public class P10 {

    public static void merge(Comparable[] arr, Comparable[] aux, int lo, int hi) {
        int mi = (lo + hi) / 2;
        if (arr[mi].compareTo(arr[mi+1]) < 0) {
            return;
        }
        for (int k = lo; k <= mi; k++) {
            aux[k] = arr[k];
        }
        //copies the second half of a[] to aux[] in decreasing order
        for (int k = mi+1; k <= hi; k++) {
            aux[k] = arr[mi+hi+1-k];
        }

        int i = lo;
        int j = hi;

        //This change allows you to remove the code
        // to test that each of the halves has been exhausted
        // from the inner loop.

        for (int k = lo; k <= hi; k++) {
            if (aux[i].compareTo(aux[j]) < 0) {
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j--];
            }
        }
    }

    @Test
    public void testForMerge() {
        Integer[] arr = {7,8,9,1,2,3};
        Integer[] aux = new Integer[6];
        merge(arr, aux, 0, 5);
        System.out.println(Arrays.toString(arr));
    }
}
