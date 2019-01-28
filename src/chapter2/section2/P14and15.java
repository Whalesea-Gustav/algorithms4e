package chapter2.section2;
import java.util.Queue;
import java.util.LinkedList;
import org.junit.Test;
public class P14and15 {

    public static Queue<Comparable> merge(Queue<Comparable> q1, Queue<Comparable> q2) {
        Queue<Comparable> result = new LinkedList<>();
        Comparable e1 = q1.peek();
        Comparable e2 = q2.peek();
        while (e1 != null || e2 != null) {

            if (e1 == null) result.add(q2.poll());
            else if (e2 == null) result.add(q1.poll());
            else if (e1.compareTo(e2) < 0) result.add(q1.poll());
            else result.add(q2.poll());

            e1 = q1.peek();
            e2 = q2.peek();

        }

        return result;
    }

    @Test
    public void testForMerge() {
        Queue<Comparable> Q1 = new LinkedList<>();
        Queue<Comparable> Q2 = new LinkedList<>();
        Q1.add(7);
        Q1.add(8);
        Q1.add(9);
        Q2.add(1);
        Q2.add(2);
        Q2.add(3);
        Queue<Comparable> merge = merge(Q1, Q2);
        for (Comparable i : merge) {
            System.out.println(i);
        }
    }

    public static void sort_BottomUp(Queue<Queue<Comparable>> nestQueue) {

        while (nestQueue.size() != 1) {
            nestQueue.add(merge(nestQueue.poll(), nestQueue.poll()));
        }

        return;
    }
    public static void main(String[] args) {
        Integer[] arr = {9, 8, 2, 3, 4, 6, 5, 2, 1, 10};
        Queue<Queue<Comparable>> nestQueue = new LinkedList<>();
        for (Integer i : arr) {
            Queue<Comparable> q = new LinkedList<>();
            q.add(i);
            nestQueue.add(q);
        }

        sort_BottomUp(nestQueue);
        for (Comparable i : nestQueue.peek()) {
            System.out.println(i);
        }
    }


}
