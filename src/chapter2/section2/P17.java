package chapter2.section2;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
// apply merge sort to LinkedList
public class P17 {

    ///single LinkedList
    private static class Node {

        Comparable item;
        Node next;

        Node() {
        }

        Node(Comparable item) {
            this.item = item;
        }
    }

    //merge
    public static Node merge(Node a, Node b) {
        Node dummyHead = new Node(0); // dummy head to keep the result LL
        Node curr = dummyHead;             // keep track of the items from a and b

        while (a != null && b != null) {
            if (a.item.compareTo(b.item) < 0) {
                curr.next = a;             // add the item of less value into the result LL
                a = a.next;
            } else {
                curr.next = b;
                 b = b.next;
            }

            curr = curr.next;              // step into next loop
        }

        curr.next = (a == null) ? b: a;

        return dummyHead.next;             // return result LL

    }

    //split LL into two halves, return the first half\
    // use slow and fast node to find the half
    public static Node getHalf(Node head) {

        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static Node sort_TopDown(Node head) {
        if (head == null || head.next == null) return head;

        Node middle = getHalf(head);
        Node secondHalf = middle.next;
        // 拆分链表
        middle.next = null;              // 此时head也会受到影响，
        // head为头部的LL只有原先LL的一半

        return merge(sort_TopDown(head), sort_TopDown(secondHalf));
    }

    @Test
    public void testForSortTopDown() {
        Node head = new Node(0);
        Node l1 = new Node(2);
        Node l2 = new Node(5);
        Node l3 = new Node(3);
        Node l4 = new Node(8);
        Node l5 = new Node(4);
        Node l6 = new Node(2);
        Node l7 = new Node(1);

        head.next = l1;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = null;

        Node p = head;
        while (p.next != null) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.print(p.item);
        System.out.println();

        p = sort_TopDown(head);
        while (p.next != null) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.print(p.item);
        System.out.println();
    }

    public static Node sort_BottomUp(Node head) {
        if (head == null || head.next == null) return head;

        Node left, right;
        //Node dummyHead = new Node(0);
        //Node curr = dummyHead;
        Node next;
        Node tail;
        int sz = 1;

        // detemine the bottomUp sort finish
        // normally we can use `for (int sz = 1; sz < length(head); sz *= 2)`
        // but the length of head is unknown
        // so use mergeTimes instead
        int mergeTimes;         // if mergeTimes = 1
                                // it indicates that sz > length(head) / 2
                                // and merge finish

        do {
            left = head;
            int leftSZ;
            int rightSZ;
            tail = null;
            head = null;
            mergeTimes = 0;
            next = null;

            // sort Node every sz elements left(head) ~ sz ~ sz ~ sz ... ~ sz ~ null
            while (left != null) {
                mergeTimes++;
                right = left;
                leftSZ = 0;
                rightSZ = sz;
                // cut the LL
                // set right at least (number of (sz)) Nodes away from left
                // left(Node) ~ (sz - 1) Nodes ~ right(Node) ~ (unknown numbers of Nodes)
                while (right != null && leftSZ < sz) {
                    leftSZ++;
                    right = right.next;
                }

                // merge left and right into head || I try to use dummyHead
                while (leftSZ > 0 || (rightSZ > 0 && right != null)) {
                    if (leftSZ == 0) {
                        //curr.next = right;
                        next = right;        //point to the Node that is needed to add into tail

                        right = right.next;
                        rightSZ--;
                    } else if (rightSZ == 0 || right == null) {
                        //curr.next = left;
                        next = left;
                        left = left.next;
                        leftSZ--;
                    } else if (left.item.compareTo(right.item) < 0) {
                        //curr.next = left;
                        next = left;
                        left = left.next;
                        leftSZ--;
                    } else {
                        //curr.next = right;
                        next = right;
                        right = right.next;
                        rightSZ--;
                    }

                    //curr = curr.next;           // my try: connect Node to dummyHead

                    if (tail == null) {         //tail == null
                        //next is the first Node
                        head = next;            // head = next;
                    } else {  // tail != null;
                        tail.next = next;       //connect the next Node to the tail
                    }

                    tail = next;           //keep tail as the end of LL
                    // tail = tail.next;
                }


                left = right;               // goto the next loop
                // but before right, the Nodes are sorted
                // left = right regard right as a new start
            }

            //this sz round is finished, so terminate the tail and double sz

            if (tail != null) {
                tail.next = null;
            }

            sz *= 2;

        } while (mergeTimes > 1);

        return head;
        //return dummyHead.next;
    }

    @Test
    public void testForSortBottomUp() {
        Node head = new Node(0);
        Node l1 = new Node(2);
        Node l2 = new Node(5);
        Node l3 = new Node(3);
        Node l4 = new Node(8);
        Node l5 = new Node(4);
        Node l6 = new Node(2);
        Node l7 = new Node(1);

        head.next = l1;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = null;

        Node p = head;
        while (p.next != null) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.print(p.item);
        System.out.println();

        p = sort_BottomUp(head);
        while (p.next != null) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.print(p.item);
        System.out.println();
    }


    public static Node sort_BottomUp_V2(Node head) {
        if (head == null || head.next == null) return head;

        Node left, right;
        Node dummyHead = new Node(0);
        Node curr = dummyHead;
        int sz = 1;

        // detemine the bottomUp sort finish
        // normally we can use `for (int sz = 1; sz < length(head); sz *= 2)`
        // but the length of head is unknown
        // so use mergeTimes instead
        int mergeTimes;         /* if mergeTimes = 1
                                 * it indicates that sz > length(head) / 2
                                /* and merge finish */

        do {
            left = head;
            int leftSZ;
            int rightSZ;
            mergeTimes = 0;

            // sort Node every sz elements left(head) ~ sz ~ sz ~ sz ... ~ sz ~ null
            while (left != null) {
                mergeTimes++;
                right = left;
                leftSZ = 0;
                rightSZ = sz;
                // cut the LL
                // set right at least (number of (sz)) Nodes away from left
                // left(Node) ~ (sz - 1) Nodes ~ right(Node) ~ (unknown numbers of Nodes)
                while (right != null && leftSZ < sz) {
                    leftSZ++;
                    right = right.next;
                }

                // merge left and right into head || I try to use dummyHead
                while (leftSZ > 0 || (rightSZ > 0 && right != null)) {
                    if (leftSZ == 0) {
                        curr.next = right;
                        right = right.next;
                        rightSZ--;
                    } else if (rightSZ == 0 || right == null) {
                        curr.next = left;
                        left = left.next;
                        leftSZ--;
                    } else if (left.item.compareTo(right.item) < 0) {
                        curr.next = left;
                        left = left.next;
                        leftSZ--;
                    } else {
                        curr.next = right;
                        right = right.next;
                        rightSZ--;
                    }

                    curr = curr.next;           // my try: connect Node to dummyHead
                }


                left = right;               // goto the next loop
            }

            curr.next = null;

            sz *= 2;
            head = dummyHead.next;
            dummyHead.next = null;
            curr = dummyHead;

        } while (mergeTimes > 1);

        //return head;
        return head;
    }

    @Test
    public void testForSortBottomUpV2() {
        Node head = new Node(0);
        Node l1 = new Node(2);
        Node l2 = new Node(5);
        Node l3 = new Node(3);
        Node l4 = new Node(8);
        Node l5 = new Node(4);
        Node l6 = new Node(2);
        Node l7 = new Node(1);

        head.next = l1;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = null;

        Node p = head;
        while (p.next != null) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.print(p.item);
        System.out.println();

        p = sort_BottomUp_V2(head);
        while (p.next != null) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.print(p.item);
        System.out.println();
    }
}
