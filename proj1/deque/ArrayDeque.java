package deque;

public class ArrayDeque<T> implements Deque<T> {
    public T[] items;
    public int size;
    public int front;

    public ArrayDeque() {
        items = (T[]) new Object[10];
        size = 0;
        front = 0;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i += 1) {
            a[i] = items[i];
        }
        items = a;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        front = (front + 1) % items.length;
        items[front] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        int avail = (size + front) % items.length;
        items[avail] = item;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int walk = front;
        for (int i = 0; i < size; i += 1) {
            System.out.print(items[walk] + " ");
            walk = (walk + 1) % items.length;
        }
        System.out.println();
    }

    public T removeFirst() {
        T ans = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        size -= 1;
        if (size <= items.length / 4) {
            resize(size / 2);
        }
        return ans;
    }

    public T removeLast() {
        int back = (size + front - 1) % items.length;
        T ans = items[back];
        items[back] = null;
        size -= 1;
        if (size <= items.length / 4) {
            resize(size / 2);
        }
        return ans;
    }

    public T get(int index) {
        int walk = front;
        for (int i = 0; i < index; i += 1) {
            walk = (walk + 1) % items.length;
        }
        return items[walk];
    }
    
}
