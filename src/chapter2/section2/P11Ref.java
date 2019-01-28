package chapter2.section2;
import java.util.Arrays;
public class P11Ref {
    private static final int CUTOFF = 3;

    public static void main(String[] args) {
        Integer[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 4, 2, 1, 2, 9, 5, 4,
                2, 1, 2, 3, 2, 34, 45, 43, 2, 2, -10};
        topDownMergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    private static void topDownMergeSort(Comparable[] array) {
        //Improvement #3 - Eliminate the copy to the auxiliary array on merge
        Comparable[] aux = array.clone();

        topDownMergeSort(aux, array, 0, array.length - 1);
    }

    private static void topDownMergeSort(Comparable[] array, Comparable[] aux, int low, int high) {

        //Improvement #1 - Cutoff for small arrays
        if (high - low <= CUTOFF) {
            insertionSort(aux, low, high);
            return;
        }

        int middle = low + (high - low) / 2;

        topDownMergeSort(aux, array, low, middle);
        topDownMergeSort(aux, array, middle + 1, high);

        //Improvement #2 - Skip merge if the array is already in order
        if (array[middle].compareTo(array[middle + 1]) < 0) {
            System.arraycopy(array, low, aux, low, high - low + 1);
            return;
        }

        merge(array, aux, low, middle, high);
    }

    private static void merge(Comparable[] array, Comparable[] aux, int low, int middle, int high) {

        int indexLeft = low;
        int indexRight = middle + 1;

        for (int i = low; i <= high; i++) {
            if (indexLeft > middle) {
                aux[i] = array[indexRight++];
            } else if (indexRight > high) {
                aux[i] = array[indexLeft++];
            } else if (array[indexLeft].compareTo(array[indexRight]) < 0) {
                aux[i] = array[indexLeft++];   // to ensure stability
            } else {
                aux[i] = array[indexRight++];
            }
        }
    }

    private static void insertionSort(Comparable[] array, int low, int high) {

        for (int i = low; i <= high; i++) {
            for (int j = i; j > low && array[j - 1].compareTo(array[j]) > 0; j--) {

                Comparable temp = array[j - 1];
                array[j - 1] = array[j];
                array[j] = temp;
            }
        }

    }
}
