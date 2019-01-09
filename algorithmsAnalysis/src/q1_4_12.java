import java.util.Arrays;
import org.junit.Test;
import org.junit.Assert;
public class q1_4_12 {

    // do not print repetitive T pair
    public <T extends Comparable<T>> void findSame(T[] arr1, T[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int index1 = 0;
        int index2 = 0;

        while (index1 < arr1.length && index2 < arr2.length) {
            if (arr1[index1].equals(arr2[index2])) {
                System.out.printf(String.valueOf(arr1[index1]));
                index1++;
                index2++;
            } else if (arr1[index1].compareTo(arr2[index2]) > 0) {
                index2++;
            } else if (arr1[index1].compareTo(arr2[index2]) < 0) {
                index1++;
            }
        }
    }

    @Test
    public void testForfindSame() {
        Integer[] a = {0, 2, 1, 8, 11, 9, 8, 10, 8};
        Integer[] b = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        findSame(a, b);

    }
}
