public class q1_4_10 {

    public static int binarySearch(int n, int[] array) {
        int max = array.length - 1;
        int min = 0;
        int middle;
        while (min <= max) {
            middle = (min + max) / 2;

            if (array[middle] == n) {
                // when find n, iterate the left side of array until the value is not n
                while (middle > 0 && array[middle-1] == n) {
                    middle--;
                }
                return middle;

            } else if (array[middle] > n) {
                min = middle;
            } else {
                max = middle;
            }
            middle = (min + max) / 2;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] a = {0, 1, 10, 10, 10, 10, 9, 3, 8, 7, 1, 11, 7};

        System.out.printf(String.valueOf(binarySearch(10, a)));
    }
}
