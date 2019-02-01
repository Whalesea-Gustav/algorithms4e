package chapter3.section2;

public class P13<Key extends Comparable<Key>, Value> extends BST<Key, Value>  {
    // implementation of get in iteration way
    @Override
    public Value get(Key key) {
        Node pointer = root;
        while(pointer != null) {
            int cmp = key.compareTo(pointer.key);
            if (cmp == 0) return pointer.val;
            else if (cmp < 0) pointer = pointer.left;
            else pointer = pointer.right;
        }
        return null;
    }
    @Override
    public void put(Key key, Value val) {
        if (root == null) {
            root = new Node(key, val, 1);
            return;
        }
        boolean hasKey = false;
        Node pointer = root;
        //change the key-val if key exists
        while (pointer != null) {
            int cmp = key.compareTo(pointer.key);
            if (cmp == 0) {
                hasKey = true;
                pointer.val = val;
                break;
            }
            else if (cmp < 0) pointer = pointer.left;
            else pointer = pointer.right;
        }

        if (hasKey) return;

        //add key-val to the bottom of BST if key don`t exist
        pointer = root;

        while (pointer != null) {
            int cmp = key.compareTo(pointer.key);
            pointer.N++;

            if (cmp > 0) {
                if (pointer.right == null) {
                    //add node at the left end of Node
                    pointer.right = new Node(key, val, 1);
                    break;
                } else pointer = pointer.right;
            } else {
                if (pointer.left == null) {
                    //add node at the right end of Node
                    pointer.left = new Node(key, val, 1);
                    break;
                } else pointer = pointer.left;
            }
        }
    }
}
