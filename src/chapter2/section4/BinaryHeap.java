package chapter2.section4;

public class BinaryHeap <Key extends Comparable<Key>> {
    private Key priorityQueue[];
    private int sz = 0; // size;

    public void MaxPQ(int maxN) { priorityQueue = (Key[]) new Object[maxN + 1]; }

    public boolean isEmpty() { return sz == 0; }

    public int size() { return sz; }

    //swap [i] and [j]
    private void exch(int i, int j) {
        Key temp = priorityQueue[i];
        priorityQueue[i] = priorityQueue[j];
        priorityQueue[j] = temp;
    }

    private boolean less(int i, int j) { return priorityQueue[i].compareTo(priorityQueue[j]) < 0; }

    //swim up  上浮
    private void swim(int k) { // swim [k]
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    //sink down
    private void sink(int k) {// sink [k]
        while (2*k < sz) {
            int j = 2 * k;
            j = (less(j, j+1)) ? j+1 : j;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public void insert(Key k) {
        priorityQueue[++sz] = k;
        swim(sz); // restore heap property
    }

    public Key peek() {
        return priorityQueue[1];
    }

    public Key poll() {
        Key toReturn = peek();
        exch(1, sz);
        priorityQueue[sz--] = null;
        sink(1); // restore heap property
        return toReturn;
    }






}
