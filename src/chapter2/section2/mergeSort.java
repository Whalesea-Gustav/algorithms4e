package chapter2.section2;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;

public class mergeSort {
    public static Comparable[] aux;
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void merge(Comparable[] arr, Comparable[] aux, int lo, int mi, int hi) {
        //原地归并的抽象方法: 将两个不同的有序数组归并到第三个数组中

        for (int k = 0; k <= hi; k++) {
            aux[k] = arr[k];
        }

        int index1 = lo;
        int index2 = mi + 1;

        for (int k = lo; k <= hi; k++) {
           if (index1 > mi) arr[k] = aux[index2++];
           else if (index2 > hi) arr[k] = aux[index1++];
           else if (less(aux[index1], aux[index2])) arr[k] = aux[index1++];
           else arr[k] = aux[index2++];
        }
    }
    public static void merge_Optimized(Comparable[] arr, Comparable[] aux, int lo, int mi, int hi) {
        //原地归并的抽象方法: 将两个不同的有序数组归并到第三个数组中
        if (arr[mi].compareTo(arr[mi+1]) < 0) {
            return;
        }

        for (int k = 0; k <= hi; k++) {
            aux[k] = arr[k];
        }

        int index1 = lo;
        int index2 = mi + 1;

        for (int k = lo; k <= hi; k++) {
            if (index1 > mi) arr[k] = aux[index2++];
            else if (index2 > hi) arr[k] = aux[index1++];
            else if (less(aux[index1], aux[index2])) arr[k] = aux[index1++];
            else arr[k] = aux[index2++];
        }
    }
    @Test
    public void testForMerge() {
        Integer[] arr = {7,8,9,1,2,3};
        Integer[] aux = new Integer[6];
        merge(arr, aux, 0, 2, 5);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort_TopDown(Comparable[] arr, int lo, int hi) {
        //自顶向下的归并排序
        if (hi <= lo) return;
        int mi = (lo + hi) / 2;
        sort_TopDown(arr, lo, mi);
        sort_TopDown(arr, mi + 1, hi);
        merge(arr, arr.clone(), lo, mi, hi);
    }

    @Test
    public void testForSort_TopDown() {
        Integer[] arr = {9, 1, 8, 3, 0, 8, 7};
        sort_TopDown(arr, 0, 6);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort_BottomUp(Comparable[] arr) {
        int len = arr.length;
        Comparable[] aux = new Comparable[len];

        for (int sz= 1; sz < len; sz = 2 * sz) {
            for (int lo = 0; lo < len-sz; lo += 2 * sz) {
                merge(arr, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, len-1));
            }
        }
    }

    @Test
    public void testForSort_BottomUP() {
        Integer[] arr = {9, 1, 8, 3, 0, 8, 7};
        sort_BottomUp(arr);
        System.out.println(Arrays.toString(arr));
    }


}
