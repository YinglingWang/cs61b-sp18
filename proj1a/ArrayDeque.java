public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty array deque */
    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. Must take constant time.*/
    public int size() {
        return size;
    }

    /**
     * Gets the item at the given index. If no such item exists, returns null. Must not alter the deque!
     * Must take constant time.
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        for (int i = 0; i < size; i ++) {
            System.out.print(items[(nextFirst + 1 + i) % items.length] + " ");
        }
        System.out.println();
    }

    private void resize(int capacity) {
        T[] a = (T []) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = items[(nextFirst + 1 + i) % items.length];
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = a;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * Must take constant time, except during resizing operations.
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = minOne(nextFirst);
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * Must take constant time, except during resizing operations.
     */
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     * Must take constant time, except during resizing operations.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        nextFirst = plusOne(nextFirst);
        T removed = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;

        if (items.length >= 16 && size < items.length * 0.25) {
            resize(items.length / 2);
        }
        return removed;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     * Must take constant time, except during resizing operations.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        nextLast = minOne(nextLast);
        T removed = items[nextLast];
        items[nextLast] = null;
        size -= 1;

        if (items.length >= 16 && size < items.length * 0.25) {
            resize(items.length / 2);
        }
        return removed;
    }

    private int minOne(int index) {
        return (index -1 + items.length) % items.length;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }
}
