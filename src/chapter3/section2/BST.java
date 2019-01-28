package chapter3.section2;
import org.junit.Test;
import org.junit.Assert;
public class BST <Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N; // sz, or numbers of Node in tree

        public Node(Key k, Value v, int n) {
            this.key = k;
            this.val = v;
            this.N = n;
        }
    }
    @Test
    public void testForInitiation() {
        BST<Character, Integer> tree = new BST<>();
        tree.put('A', 10);
        tree.put('B', 20);
        tree.put('D', 30);
        tree.put('C', 40);
        Assert.assertEquals(tree.size(), 4);
        Assert.assertEquals(tree.get('A'), (Integer) 10);
        Assert.assertEquals(tree.min(), (Character) 'A');
        Assert.assertEquals(tree.max(), (Character) 'D');
        Assert.assertEquals(tree.floor('E'), (Character) 'D');
        Assert.assertEquals(tree.floor('B'), (Character) 'B');
        Assert.assertEquals(tree.rank('B'), 1);
        Assert.assertEquals(tree.rank('C'), 2);
    }

    public int size() { return size(root); }

    public int size(Node root) {
        if (root == null) return 0;
        else return root.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }
    private Value get(Node rt, Key k) {
            if (rt == null) return null;

            int cmp = rt.key.compareTo(k);
            if (cmp > 0) return get(rt.left, k); // rt.key > k
            else if (cmp < 0)  return get(rt.right, k); // rt.key < k
            else return rt.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }
    public Node put(Node rt, Key k, Value v) {
        //如果k存在于root中，则更新它的值
        //否则new一个Node，并插入到树的节点中
        if (rt == null) return new Node(k, v, 1);
        int cmp = rt.key.compareTo(k);
        if (cmp > 0) rt.left = put(rt.left, k, v);
        else if (cmp < 0) rt.right = put(rt.right, k, v);
        else rt.val = v;
        rt.N = size(rt.left) + size(rt.right) + 1;
        return rt;
    }

    // Ordered Symbol Table API
    public Key min() {
        return min(root).key;
    }
    private Node min(Node rt) {
        if (rt.left == null) return rt;
        else return min(rt.left);
    }

    public Key max() {
        return max(root).key;
    }
    private Node max(Node rt) {
        if (rt.right == null) return rt;
        else return max(rt.right);
    }

    public Key floor(Key k) {
        Node x = floor(root, k); // 返回<=Key的最大键所在的子节点
        if (x == null) return null;
        else return x.key;
    }
    private Node floor(Node rt, Key k) {
        if (rt == null) return null;
        int cmp = rt.key.compareTo(k);
        if (cmp == 0) return rt;
        else if (cmp > 0) return floor(rt.left, k);
        else { // cmp < 0时 有两种可能，rt 或者 floor(rt.right, k)
            Node temp = floor(rt.right, k);
            if (temp == null) return rt;
            else return temp;
        }
    }

    public Key ceiling(Key k) {
        return ceiling(root, k).key;
    }
    // find the Node with maximal key of >= k 找到 >= 给定Key的最小Node
    private Node ceiling(Node rt, Key k) {
        if (rt == null) return null;
        int cmp = rt.key.compareTo(k);
        if (cmp < 0) return ceiling(rt.right, k);
        else {
            Node temp = ceiling(rt.left , k);
            if (temp == null) return rt;
            else return temp;
        }
    }

    public Key select(int k) {
        return select(root, k).key;
    }
    private Node select(Node rt, int k) {
        if (rt == null) return null;
        int temp = size(rt.left);
        if (temp > k) return select(rt.left, k);
        else if (temp < k) return select(rt.right, k-temp-1);
        else return rt;
    }

    public int rank(Key k) {
        return rank(root, k);
    }
    private int rank(Node rt, Key k) {
        if (rt == null) return 0;
        int cmp = rt.key.compareTo(k);
        if (cmp < 0) return size(rt.left) + 1 + rank(rt.right, k);
        else if (cmp > 0) return rank(rt.left, k);
        else return size(rt.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }
    private Node deleteMin(Node rt) {
        if (rt.left == null) return rt.right;
        rt.left = deleteMin(rt.left);
        rt.N = size(rt.left) + size(rt.right) + 1;
        return rt;
    }

    public void deleteMax() { root = deleteMax(root); }
    private Node deleteMax(Node rt) {
        if (rt.right == null) return rt.left;
        rt.right = deleteMax(rt.right);
        rt.N = size(rt.left) + size(rt.right) + 1;
        return rt;
    }

    public void delete(Key key) {
        root = deletePredecessor(root, key);
    }
    //使用先驱节点
    private Node deletePredecessor(Node rt, Key k) {
        if (rt == null) return null;
        int cmp = k.compareTo(rt.key);
        if (cmp > 0) rt.right = deletePredecessor(rt.right, k);
        else if (cmp < 0) rt.left =  deletePredecessor(rt.left, k);
        else { // rt.key.equals(k)
            if (rt.right == null) return rt.left;
            if (rt.left == null) return rt.right;
            Node temp = rt;
            rt = max(temp.left);
            rt.left = deleteMax(temp.left);
            rt.right = temp.right;
        }
        rt.N = size(rt.left) + size(rt.right) + 1;
        return rt;
    }
    //使用后继节点
    private Node deleteSuccessor(Node rt, Key k) {
        if (rt == null) return null;
        int cmp =  k.compareTo(rt.key);
        if (cmp > 0) rt.right = deleteSuccessor(rt.right, k);
        else if (cmp < 0) rt.left = deleteSuccessor(rt.left, k);
        else {
            if (rt.right == null) return rt.left;
            if (rt.left == null) return rt.right;
            Node temp = rt;
            rt = min(rt.right);
            rt.right = deleteMin(temp.right);
            rt.left = temp.left;
        }
        rt.N = size(rt.left) + size(rt.right) + 1;
        return rt;

    }

    

}
