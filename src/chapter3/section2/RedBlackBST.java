package chapter3.section2;

import java.io.ByteArrayInputStream;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    protected Node root;
    boolean color;
    static final boolean RED =true;
    static final Boolean BLACK = false;
    protected class Node { // own a variable color
        Key key;
        Value val;
        Node left, right;
        int N;
        boolean color;

        public int size() {
            return size(root);
        }

        private int size(Node root) {
            if (root == null) return 0;
            else return root.N;
        }
        public Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }

        protected boolean isRed(Node h) {
            if (h == null) return false;
            return h.color == RED;
        }

        Node rotateLeft(Node rt) {
            Node temp = rt;
            rt = temp.right;
            temp.right = rt.left;
            rt.left = temp;
            rt.color = temp.color;
            temp.color = RED;
            rt.N = temp.N;
            temp.N = 1 + size(temp.right) + size(temp.left);
            return rt;
        }

        Node rotateRight(Node h) {
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.N = h.N;
            x.color = h.color;
            h.color = RED;
            h.N = 1 + size(h.left) + size(h.right);
            return x;
        }

        //转换两个子节点的颜色，主要是在对3-节点put操作时，
        // 要将中节点作为RED传递至上一层树，而左右两个子节点转化成黑色
        void flipColors(Node h) {
            h.color = RED;
            h.left.color = BLACK;
            h.right.color = BLACK;
        }

        void put(Key key, Value val) {
            root = put(root, key, val);
            root.color = BLACK;
        }
        Node put(Node rt, Key k, Value v) {
            if (rt == null) return new Node(k, v, 1, RED);

            int cmp= k.compareTo(rt.key);

            if (cmp < 0) rt.left = put(rt.left, k, v);
            else if (cmp > 0) rt.right = put(rt.right, k, v);
            else rt.val = v;

            rt.N = 1 + size(rt.left) + size(rt.right);

            if(isRed(rt.right) && !isRed(rt.left)) rt = rotateLeft(rt);

            if (isRed(rt.left) && !isRed(rt.left.left)) rt = rotateRight(rt);

            if (isRed(rt.left) && isRed(rt.right)) flipColors(rt);

            return rt;

        }

    }
}
