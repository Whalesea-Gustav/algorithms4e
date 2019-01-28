package chapter2.section3;
import org.junit.Test;
import java.util.Arrays;
public class P22 {
    public static void exch(Comparable[] arr, int index1, int index2) {
        Comparable temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void quick3way(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) return;

        int i = lo;
        int j = hi+1;
        int p = lo+1;
        int q = hi;
        Comparable v = arr[lo];
        while (true) {
            while (v.compareTo(arr[++i]) >= 0) { // v >= arr[j]
                if (v.compareTo(arr[i]) == 0) exch(arr, p++, i);
            }
            while (v.compareTo(arr[--j]) <= 0) { // v <= arr[j]
                if (v.compareTo(arr[j]) == 0) {
                    exch(arr, q--, j);
                }
            }

            if (i >= j) break;

            exch(arr, i, j);
        }
        System.out.println(i + " " + j);
    }


    @Test
    public void testForQuick3way() {
        Integer[] a = {5, 3, 1, 5, 6, 4, 3, 7, 7, 2, 9, 1, 5, 4, 8, 0, 1};
        quick3way(a, 0, a.length-1);
        System.out.println(Arrays.toString(a));
    }
}
