package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c) {
        size = 0;
        items = (T[]) new Object[10];
        front = 0;
        cmp = c;
    }

    public T max() {
        if (size == 0) {
            return null;
        }
        int walk = front;
        T max = items[walk];
        for (int i = 0; i < size - 1; i ++) {
            walk = (walk + 1) % items.length;
            if (cmp.compare(items[walk], max) > 0) {
                max = items[walk];
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        if (size == 0) {
            return null;
        }
        int walk = front;
        T max = items[walk];
        for (int i = 0; i < size - 1; i ++) {
            walk = (walk + 1) % items.length;
            if (c.compare(items[walk], max) > 0) {
                max = items[walk];
            }
        }
        return max;
    }
}
