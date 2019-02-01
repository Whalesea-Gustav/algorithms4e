package chapter3.section2;

public class P14<Key extends Comparable<Key>, Value> extends BST<Key, Value> {
    @Override
    public Key min() {
        if (root == null) return null;
        Node pointer = root;
        while (pointer.left != null) {
            pointer = pointer.left;
        }
        return pointer.key;
    }

    @Override
    public Key max() {
        if (root == null) return null;
        Node pointer = root;
        while (pointer.right != null) {
            pointer = pointer.right;
        }
        return pointer.key;
    }

    //嵌套递归的迭代表示, 可是使用多个变量代替多重递归, 整个多重递归在一个while中解决
    @Override
    public Key floor(Key k) {
        Node pointer = root;
        Node pointer2floor = null;
        while (pointer != null) {
            int cmp = k.compareTo(pointer.key);
            if (cmp == 0) {
                pointer2floor = pointer;
                break;
            }
            else if (cmp < 0) pointer = pointer.left;
            else {
                pointer2floor = pointer;
                pointer = pointer.right;
            }

        }
        return (pointer2floor == null) ? null : pointer2floor.key;
    }

    @Override
    public Key ceiling(Key k) {
        Node pointer = root;
        Node pointer2ceiling = null;
        while (pointer != null) {
            int cmp = k.compareTo(pointer.key);
            if (cmp == 0) {
                pointer2ceiling = pointer;
                break;
            }
            else if (cmp > 0) pointer = pointer.right;
            else {
                pointer2ceiling = pointer;
                pointer = pointer.right;
            }
        }

        return (pointer2ceiling == null) ? null: pointer2ceiling.key;
    }


}
