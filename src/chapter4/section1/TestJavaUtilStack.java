package chapter4.section1;

import java.util.Stack;
public class TestJavaUtilStack {

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(5);

        for (Integer i : test) {
            System.out.print(i + "-");
        }
         System.out.print('\n');
        while (!test.empty()) {
            System.out.print(test.pop() + " ");
        }
    }
}
