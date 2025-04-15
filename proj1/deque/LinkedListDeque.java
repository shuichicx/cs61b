package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    private  Node sentinel;

    private class Node {
        private Node prev;
        private Node next;
        private T elem;

        public Node(T item) {
            this.elem = item;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node p = new Node(item);
        p.prev = sentinel;
        p.next = sentinel.next;
        sentinel.next.prev = p;
        sentinel.next = p;
        size += 1;
    }

    public void addLast(T item) {
        Node p = new Node(item);
        p.next = sentinel;
        p.prev = sentinel.prev;
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.elem + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        Node p = sentinel.next;
        T ans = p.elem;
        sentinel.next = p.next;
        p.next.prev = sentinel;
        size -= 1;
        return ans;
    }

    public T removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        }
        Node p = sentinel.prev;
        T ans = p.elem;
        sentinel.prev = p.prev;
        p.prev.next = sentinel;
        size -= 1;
        return ans;
    }

    public T get(int index) {
        if (sentinel.next == sentinel || index > size - 1) {
            return null;
        }
        Node p = sentinel.next;
        while (index != 0) {
            p = p.next;
            index -= 1;
        }
        return p.elem;
    }

    public T getRecursive(int index) {
        return Recursive(sentinel.next, index);
    }

    private T Recursive(Node p, int index) {
        if (index == 0) {
            return p.elem;
        }
         return Recursive(p.next, index - 1);
    }
}
