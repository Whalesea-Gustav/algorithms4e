package chapter2.section2;


import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;
public class P11 {

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    @Test
    public void testForLess() {
        Assert.assertTrue(less((Integer) 5, (Integer) 10));
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void merge(Comparable[] arr, Comparable[] aux, int lo, int hi) {
        int mi = (lo + hi) / 2;

        if (less(arr[mi], arr[mi+1])) {
            for (int k = lo; k <= hi; k++) {
                aux[k] = arr[k];
            }
            return;
        }
        // merge arr into aux
        int index1 = lo;
        int index2 = mi + 1;
        for (int k = lo; k <= hi; k++) {

            if (index1 > mi) aux[k] = arr[index2++];
            else if (index2 > hi) aux[k] = arr[index1++];
            else if (less(arr[index1], arr[index2])) aux[k] = arr[index1++];
            else aux[k] = arr[index2++];

        }
    }

    public static void sort(Comparable[] arr) {
        Comparable[] aux = arr.clone();
        sort(arr, aux, 0, arr.length-1);
    }

    public static void sort(Comparable[] arr, Comparable[] aux, int lo, int hi) {
        if (hi - lo < 3) {
            insertionSort(arr, lo, hi);
            return;
        }

        int mi = (lo + hi) / 2;

        // use arr as auxiliary array to sort aux
        sort(aux, arr, lo, mi);
        sort(aux, arr, mi+1, hi);

        // merge aux into arr
        merge(aux, arr, lo, hi);

    }

    public static void insertionSort(Comparable[] arr, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j >= lo+1 && less(arr[j], arr[j-1]); j--) {
                //swap
                exch(arr, j, j-1);
            }
        }
    }
    @Test
    public void testForInsertion() {
        Integer[] arr = {10, 9, 8, 7, 6};
        insertionSort(arr, 0, 4);
        System.out.println(Arrays.toString(arr));
    }
    public static void main(String[] args) {
        Integer[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 4, 2, 1, 2, 9, 5, 4,
                            2, 1, 2, 3, 2, 34, 45 ,43, 2, 2,-10};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
