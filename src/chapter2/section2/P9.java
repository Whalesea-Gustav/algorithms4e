package chapter2.section2;
import org.junit.Test;

import java.util.Arrays;

public class P9 {
    //避免在每一次的merge过程中，new一个新的数组
    public static void merge(Comparable[] arr, int lo, int mi, int hi, Comparable[] aux) {
        //if arr[mi] < arr[mi+1], there is no need for merging
        if (arr[mi].compareTo(arr[mi+1]) < 0) {
            return;
        }

        //可以使用System.arraycopy()
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }

        int i = lo;

        int j = mi+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mi) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) > 0) arr[k] = aux[i++];
            else arr[k] = aux[j++];

        }
    }
    public static void sort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) return;

        //只创建一次aux数组，将数组作为参数传入子函数
        Comparable[] aux = new Comparable[arr.length];
        int mi = (lo + hi) / 2;
        sort(arr, lo, mi, aux);
        sort(arr, mi+1, hi, aux);
        merge(arr, lo, mi, hi, aux);
    }

    public static void sort(Comparable[] arr, int lo, int hi, Comparable[] aux) {
        if (hi <= lo) return;
        int mi = (lo + hi) / 2;
        sort(arr, lo, mi, aux);
        sort(arr, mi+1, hi, aux);
        merge(arr, lo, mi, hi, aux);
    }

    @Test
    public void testForP9Sort() {
        Integer[] arr = {9, 1, 8, 3, 0, 8, 7};
        sort(arr, 0, 6);
        System.out.println(Arrays.toString(arr));
    }
}
