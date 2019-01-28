package chapter2.section2;

import org.junit.Test;

import java.util.Arrays;

public class P16 {
    //natural merge sort in bottom up order
    public static void merge(Comparable[] arr, Comparable[] aux, int lo, int mi, int hi) {
        if (hi <= lo) return;

        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }

        int index1 = lo;
        int index2 = mi+1;

        for (int k = lo; k <= hi; k++) {
            if (index1 > mi) arr[k] = aux[index2++];
            else if (index2 > hi) arr[k] = aux[index1++];
            else if (aux[index1].compareTo(aux[index2]) < 0) arr[k] = aux[index1++];
            else arr[k] = aux[index2++];
        }
    }
    /* 不断merge数组中相邻的有序数组直到最后一个元素
     * 前一个merge完的数组作为下一次merge的第一部分
     */
    public static void naturalMergeSort(Comparable[] arr) {
        if (arr == null && arr.length == 1) return;

        Comparable[] aux = new Comparable[arr.length];

        int lo = 0;
        int mi = 0;
        int hi = 0;
        /* 判断是否是第一个有序数组
         *
         */
        boolean isFirstMergePart = false;
        for (int k = 1; k < arr.length; k++) {
            if (arr[k].compareTo(arr[k-1]) < 0) { // find a sorted arr
                if (!isFirstMergePart) { // if it`s the meet of arr for the first time
                    mi = k-1;            // set the mi(ddle)
                    isFirstMergePart = true;
                } else {                 // if it`s the second time to meet the ordered arr
                    hi = k - 1;          // assign k-1 to hi and do the merge between the lo~mi and mi+1~hi
                    merge(arr, aux, lo, mi, hi);
                    mi = hi;             // regard the merged arr(lo~hi) as the first part of merge (lo~mi)
                                         // do the next merge
                }
            }
        }
        // arr[arr.length-1] is not sorted in the above process
        merge(arr, aux, lo, mi, arr.length-1);
    }

    @Test
    public void testForSort_BottomUP() {
        Integer[] arr = {9, 1, 8, 3, 0, 8, 7};
        naturalMergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
