package chapter3.section3;

public class P39 {
    public class RBBST<Key extends Comparable<Key>, Value> extends RedBlackBST<Key, Value> {
        public void deleteMin() {
            if (!isRed(root.left) && !isRed(root.right))
                root.color = RED;

            root = deleteMin(root);
            if (isRed(root)) root.color = BLACK;
        }
        @Override
        void flipColor(Node rt) {
            if (rt == null) return;
            rt.color = !rt.color;
            if (rt.left != null) rt.left.color = !rt.left.color;
            if (rt.right != null) rt.right.color = !rt.right.color;
        }

        protected Node deleteMin(Node rt) {
            if (rt.left == null) return null; // deleteion operation
            if (!isRed(rt.left) && !isRed(rt.left)) // the Left Node is 2- Node
                //move a Node to Left, and form 3- or 4- Node
                rt = moveRedLeft(rt);
            rt.left = deleteMin(rt.left); //recurse into next Node;
            return balance(rt);
        }

        protected Node moveRedLeft(Node rt) {
            flipColor(rt);
            if (isRed(rt.right.left)) {
                rt.right = rotateRight(rt.right);
                rt = rotateLeft(rt);
            }
            return rt;
        }

        protected Node balance(Node rt) {
            if (!isRed(rt.left) && (isRed(rt.right))) rt = rotateRight(rt);
            if (isRed(rt.left) && (isRed(rt.left.left))) rt = rotateLeft(rt);
            if (isRed(rt.left) && (isRed(rt.right))) flipColor(rt);
            return rt;
        }
    }
}
