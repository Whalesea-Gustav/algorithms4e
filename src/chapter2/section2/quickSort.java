package chapter2.section2;
import org.junit.Test;

import java.util.Arrays;

public class quickSort {

    private static int partition(Comparable[] arr, int lo, int hi) {
        int i = lo, j = hi+1;
        Comparable cut = arr[lo];
        while (true) {
            while (arr[++i].compareTo(cut) < 0) if (i == hi) break;
            while (arr[--j].compareTo(cut) > 0) if (j == lo) break;
            if (i >= j) break;
            //swap
            Comparable temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        //swap lo and j
        arr[lo] = arr[j];
        arr[j] = cut;
        return j;
    }

    @Test
    public void testForPartition() {
        Comparable[] a = {5, 0, 3, 5, 2, 4, 5, 6, 7};
        Comparable[] b= {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        partition(a, 0, a.length-1);
        partition(b, 0, b.length-1);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
}
