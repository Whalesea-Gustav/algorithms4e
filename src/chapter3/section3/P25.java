package chapter3.section3;

public class P25 {
    static class TopDown234<Key extends Comparable<Key>, Value> {
        static final boolean RED = true;
        static final boolean BLACK = false;

        class Node {
            Key key;
            Value value;
            Node left, right;
            boolean color;
            int size;
            Node(Key key, Value value, int size, boolean color) {
                this.key = key;
                this.value = value;

                this.size = size;
                this.color = color;
            }
        }

        Node root;

        public int size() {
            return size(root);
        }

        protected int size(Node node) {
            if (node == null) {
                return 0;
            }

            return node.size;
        }

        public boolean isEmpty() {
            return size(root) == 0;
        }

        boolean isRed(Node node) {
            if (node == null) {
                return false;
            }

            return node.color == RED;
        }

        Node rotateLeft(Node node) {
            if (node == null || node.right == null) {
                return node;
            }

            Node newRoot = node.right;

            node.right = newRoot.left;
            newRoot.left = node;

            newRoot.color = node.color;
            node.color = RED;

            newRoot.size = node.size;
            node.size = size(node.left) + 1 + size(node.right);

            return newRoot;
        }

        Node rotateRight(Node node) {
            if (node == null || node.left == null) {
                return node;
            }

            Node newRoot = node.left;

            node.left = newRoot.right;
            newRoot.right = node;

            newRoot.color = node.color;
            node.color = RED;

            newRoot.size = node.size;
            node.size = size(node.left) + 1 + size(node.right);

            return newRoot;
        }

        void flipColors(Node rt) {
            if (rt == null || rt.left == null || rt.right == null) return;

            if ((isRed(rt) && !isRed(rt.left) && !isRed(rt.right)) ||
            (!isRed(rt) && isRed(rt.left) && isRed(rt.right))){
                rt.color = !rt.color;
                rt.left.color = !rt.left.color;
                rt.right.color = !rt.right.color;
            }
        }

        void put(Key key, Value val) {
            root = put(root, key, val);
        }
        Node put(Node rt, Key k, Value v) {
            if (rt == null ) return new Node(k, v, 1, BLACK);

            // decompose 5- Node
            flipColors(rt);

            int cmp = k.compareTo(rt.key);
            if (cmp == 0) rt.value = v;
            else if (cmp > 0) rt.right = put(rt.right, k, v);
            else rt.left = put(rt.left, k, v);

            //compose 4-Node
            if (isRed(rt.left) && !isRed(rt.right)) rt = rotateLeft(rt);
            if (isRed(rt.left) && isRed(rt.left.left)) rt = rotateRight(rt);

            rt.size = size(rt.left) + size(rt.right) + 1;

            return rt;


        }

    }
}
