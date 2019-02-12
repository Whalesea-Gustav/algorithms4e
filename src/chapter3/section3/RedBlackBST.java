package chapter3.section3;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    static final boolean RED = true;
    static final boolean BLACK = false;
    Node root;

    protected class Node {
        Key key;
        Value val;
        Node left, right;
        int sz;
        boolean color;

        public Node(Key key, Value val, int size, boolean color) {
            this.key = key;
            this.val = val;
            this.sz = size;
            this.color = color;
        }
    }

    boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    void swapColor(Node node1, Node node2) {
        boolean tempColor = node1.color;
        node1.color = node2.color;
        node2.color = tempColor;
    }

    int size() {
        return size(root);
    }

    int size(Node rt) {
        return rt.sz;
    }

    Node rotateLeft(Node rt) {
        Node result = rt.right;
        rt.right = result.left;
        result.left = rt;
        swapColor(rt, result);
        //change sz
        result.sz = rt.sz;
        rt.sz = size(rt.left) + size(rt.right) + 1;
        return result;
    }

    Node rotateRight(Node rt) {
        Node result = rt.left;
        rt.right = result.left;
        result.left = rt;
        swapColor(rt, result);
        //change sz
        result.sz = rt.sz;
        rt.sz = size(rt.left) + size(rt.right) + 1;
        return result;
    }

    void flipColor(Node rt) {
        rt.color = RED;
        rt.left.color = BLACK;
        rt.right.color = BLACK;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node rt, Key k, Value v) {
        if (rt == null) return new Node(k, v, 1, RED);

        //put new RED node at the bottom of the tree
        int cmp = k.compareTo(rt.key);
        if (cmp == 0) rt.val = v;
        else if (cmp > 0) rt.right= put(rt.right, k, v);
        else rt.left = put(rt.left, k, v);

        //adjuct Red Link
        if (isRed(rt) && !isRed(rt.right)) rt = rotateLeft(rt);
        if (isRed(rt.left) && isRed(rt.left.left)) rt = rotateRight(rt);
        if (isRed(rt) && !isRed(rt.left) && !isRed(rt.right)) flipColor(rt);

        //correct sz;
        rt.sz = size(rt.left) + size (rt.right) + 1;

        return rt;
    }



}
