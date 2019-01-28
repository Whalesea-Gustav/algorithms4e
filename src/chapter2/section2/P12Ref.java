package chapter2.section2;
import java.util.Arrays;

public class P12Ref {
    public static void main(String[] args) {

        int arraySize = 9;
        int blockSize = 3;
        Integer[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};

        if (arraySize % blockSize != 0) {
            throw new RuntimeException("Array size needs to be a multiple of block size");
        }


        selectionSortBlocks(arr, blockSize);

        //O (N^2)
        for(int i = 0; i < arraySize / blockSize; i++) {
            mergeBlocks(arr, blockSize);
        }
        System.out.println(Arrays.toString(arr));
    }


    private static void selectionSortBlocks(Comparable[] array, int blockSize) {
        for(int i = 0; i < array.length; i += blockSize) {
            selectionSort(array, i, i + blockSize - 1);
        }
    }

    private static void selectionSort(Comparable[] array, int low, int high) {
        for(int i = low; i <= high; i++) {
            int minIndex = i;

            for(int j = i + 1; j <= high; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            Comparable temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    private static void mergeBlocks(Comparable[] array, int blockSize) {
        Comparable[] aux = new Comparable[blockSize];

        int low = 0;
        int middle = blockSize - 1;
        int high = middle + blockSize;

        int blocksMerged = 1;

        while(high < array.length) {

            merge(array, aux, low, middle, high);

            blocksMerged++;

            low = (blocksMerged - 1) * blockSize;
            middle = blocksMerged * blockSize - 1;
            high = middle + blockSize;
        }
    }

    private static void merge(Comparable[] array, Comparable[] aux, int low, int middle, int high) {

        int auxIndex = 0;

        for(int i = low; i <= middle; i++) {
            aux[auxIndex] = array[i];
            auxIndex++;
        }

        int indexLeft = 0;
        int indexRight = middle + 1;
        int arrayIndex = low;

        while (indexLeft < aux.length && indexRight <= high) {
            if (aux[indexLeft].compareTo(array[indexRight]) <= 0) {
                array[arrayIndex] = aux[indexLeft];
                indexLeft++;
            } else {
                array[arrayIndex] = array[indexRight];
                indexRight++;
            }

            arrayIndex++;
        }

        while (indexLeft < aux.length) {
            array[arrayIndex] = aux[indexLeft];

            indexLeft++;
            arrayIndex++;
        }
    }
}
