import org.junit.Test;
import java.util.Arrays;


public class q1_4_15 {
    public static int TwoSumFaster(int[] arr, int k, int i) {
        int cnt = 0;
        int start = i;
        int end = arr.length - 1;

        while (start < end) {
            if (arr[start] + arr[end] == k) {
                cnt++;
                start++;
                end--;
            } else if (arr[start] + arr[end] > k) {
                end--;
            } else if (arr[end] + arr[start] < k) {
                start++;
            }
        }

        return cnt;
    }

    public static int ThreeSumFaster(int[] arr) {
        int cnt= 0 ;
        for (int i = 0; i < (arr.length - 1); i++) {

            // 需要避免重复
            cnt+= TwoSumFaster(arr, -arr[i], i+1);
        }

        return cnt;
    }

    @Test
    public void testTwoSumFaster() {
        int[] a = {0, 0, 1, -1, 8, 10, -10, 8, -8, 9, -9, 11, 120};
        Arrays.sort(a);
        System.out.printf(String.valueOf(TwoSumFaster(a, 0, 0)));

        int[] b = {-1, 0, 1, 7, -7, 9, -9, 10, -10};
        Arrays.sort(b);

        System.out.printf(String.valueOf(ThreeSumFaster(b)));
    }

}
