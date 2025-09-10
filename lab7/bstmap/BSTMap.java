package bstmap;

import java.util.*;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private BSTNode root;
    private int size;

    public class BSTNode {
        K key;
        V val;
        BSTNode left;
        BSTNode right;

        public BSTNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private BSTNode find(BSTNode x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) return find(x.right, key);
        if (cmp < 0) return find(x.left, key);
        else return x;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return find(root, key).key != null;
    }

    @Override
    public V get(K key) {
        BSTNode x = find(root, key);
        if (x == null) return null;
        else return x.val;
    }

    @Override
    public int size() {
        return size;
    }

    private BSTNode put(BSTNode x, K key, V val) {
        if (x == null) return new BSTNode(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp > 0) return x.right = put(x.right, key, val);
        if (cmp < 0) return x.left = put(x.left, key, val);
        return x;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
        size += 1;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    private BSTNode findMin(BSTNode x) {
        if (x.left == null) return x;
        return findMin(x.left);
    }

    private BSTNode removeMin(BSTNode x) {
        if (x.left == null) return x.right;
        x.left = removeMin(x.left);
        return x;
    }
    
    private BSTNode remove(BSTNode x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) remove(x.right, key);
        if (cmp < 0) remove(x.left, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            BSTNode t = x;
            x = findMin(x.right);
            x.right = removeMin(x.right);
            x.left = t.left;
        }
        return x;
    }

    @Override
    public V remove(K key) {
        BSTNode x = find(root, key);
        root = remove(root, key);
        return x.val;
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    private class BSTMapIterator implements Iterator<K> {
        private Stack<BSTNode> stack = new Stack<>();

        /**
         * 以 root 出发，一路向左入栈元素
         * */
        void pushLeftNode(BSTNode root) {
            BSTNode node = root;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * 中序遍历的一个步骤：一路向左将所有元素入栈
         * */
        BSTMapIterator() {
            pushLeftNode(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public K next() {
            BSTNode node = stack.pop();
            if (node.right != null) {
                pushLeftNode(node.right);
            }
            return node.key;
        }
    }

    /**
     * 需要返回的是一个 key 的迭代器，方便遍历 key
     * */
    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    public void printInOrder(BSTNode x) {
        if (x == null) return;
        printInOrder(x.left);
        System.out.print(x.key);
        System.out.print(' ');
        System.out.println(x.val);
        printInOrder(x.right);
    }
}
