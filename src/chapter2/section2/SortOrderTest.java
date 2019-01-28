package chapter2.section2;
import java.util.Collections;
import org.junit.Test;
import java.util.ArrayList;
public class SortOrderTest {
    public static int H =  80;
    public static int L = 60;
    class Examinee implements Comparable<Examinee> {
        int ID, Dscore, Cscore, DCclass;

        public Examinee(int i1, int i2, int i3) {
            this.ID = i1;
            this.Dscore = i2;
            this.Cscore = i3;
            if (this.Dscore >= H && this.Cscore >= H) this.DCclass = 1;
            else if (this.Dscore >= H && this.Cscore < H && this.Cscore >= L) this.DCclass = 2;
            else if (this.DCclass < H && this.DCclass >= L && this.Cscore < H
                    && this.Cscore >= L && this.DCclass > this.Cscore) this.DCclass = 3;
            else if (this.Dscore >= L && this.Cscore >= L) this.DCclass = 4;
            else this.DCclass = 5;

        }

        @Override
        public int compareTo(Examinee exam) {
            if (this.DCclass != exam.DCclass) return this.DCclass - exam.DCclass;
            else if (this.DCclass == 1) return exam.Dscore - this.Dscore;
            else if (this.DCclass == 2) return exam.Dscore + exam.Cscore - this.Dscore - this.Cscore;
            else if (this.DCclass == 3) return exam.Dscore + exam.Cscore - this.Dscore - this.Cscore;
            else if (this.DCclass == 4) return exam.Dscore + exam.Cscore - this.Dscore - this.Cscore;
            else return exam.Dscore + exam.Cscore - this.Dscore - this.Cscore;

        }

        public String toString() {
            return this.ID + " " + this.Dscore + " " + this.Cscore + " " + this.DCclass;
        }
    }

    @Test
    public void testForExaminee() {
        ArrayList<Examinee> arr = new ArrayList<>();
        arr.add(new Examinee(10000001, 64, 90));
        arr.add(new Examinee(10000002, 90, 60));
        arr.add(new Examinee(10000011, 85, 80));
        arr.add(new Examinee(10000003, 85, 80));
        arr.add(new Examinee(10000004, 80, 85));
        arr.add(new Examinee(10000005, 82, 77));
        arr.add(new Examinee(10000006, 83, 76));
        arr.add(new Examinee(10000007, 90, 78));
        arr.add(new Examinee(10000008, 75, 79));
        arr.add(new Examinee(10000009, 59, 90));
        arr.add(new Examinee(10000010, 88, 45));
        arr.add(new Examinee(10000012, 80, 100));
        arr.add(new Examinee(10000013, 90, 99));
        arr.add(new Examinee(10000014, 66, 60));
        arr.add(new Examinee(10000014, 30, 200));

        Collections.sort(arr);
        for (Examinee i : arr) {
            System.out.println(i.toString());
        }
    }
}
