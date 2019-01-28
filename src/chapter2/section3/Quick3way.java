package chapter2.section3;

public class Quick3way {
    public static void exch(Comparable[] arr, int index1, int index2) {
        Comparable temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }


    public static void sortIn3way(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) return;

        int lt = lo;
        int gt = hi;
        int i = lo+1;
        Comparable v = arr[lo];

        while (i < gt) {
            int cmp = v.compareTo(arr[i]);
            if (cmp > 0) exch(arr, i++, lt++);
            else if (cmp > 0) exch(arr, i, gt--);
            else i++;
        }

        sortIn3way(arr, lo, lt-1);
        sortIn3way(arr, gt+1, hi);
    }
}
