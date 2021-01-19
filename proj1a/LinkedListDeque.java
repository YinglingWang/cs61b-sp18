public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        public T item;
        public Node preNode;
        public Node nextNode;

        public Node(T item) {
            this.item = item;
        }

        public Node(T item, Node preNode, Node nextNode) {
            this.item = item;
            this.preNode = preNode;
            this.nextNode = nextNode;
        }
    }

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.preNode = sentinel;
        sentinel.nextNode = sentinel;
        size = 0;
    }

    /**
     * Gets the item at the given index. If no such item exists, returns null. Must not alter the deque!
     * Must use iteration, not recursion.
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node current = sentinel.nextNode;
        while (index > 0) {
            index -= 1;
            current = current.nextNode;
        }
        return current.item;
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.nextNode);
    }

    private T getRecursiveHelper(int index, Node current) {
        if (index == 0) {
            return current.item;
        }
        return getRecursiveHelper(index -1, current.nextNode);
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. Must take constant time.*/
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node current = sentinel.nextNode;
        for (int i = 0; i < size; i++) {
            System.out.print(current.item + " ");
            current = current.nextNode;
        }
        System.out.println();
    }

    /**
     * Adds an item of type T to the front of the deque.
     * Must not involve any looping or recursion. A single such operation must take “constant time”.
     */
    public void addFirst(T item) {
        Node oldFirst = sentinel.nextNode;
        Node newNode = new Node(item, sentinel, oldFirst);
        sentinel.nextNode = newNode;
        oldFirst.preNode = newNode;
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * Must not involve any looping or recursion. A single such operation must take “constant time”.
     */
    public void addLast(T item) {
        Node oldLast = sentinel.preNode;
        Node newNode = new Node(item, oldLast, sentinel);
        sentinel.preNode = newNode;
        oldLast.nextNode = newNode;
        size += 1;
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     * Must not involve any looping or recursion. A single such operation must take “constant time”.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        Node removed = sentinel.nextNode;
        sentinel.nextNode = sentinel.nextNode.nextNode;
        sentinel.nextNode.preNode = sentinel;
        size -= 1;
        return removed.item;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     * Must not involve any looping or recursion. A single such operation must take “constant time”.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        Node removed = sentinel.preNode;
        sentinel.preNode = sentinel.preNode.preNode;
        sentinel.preNode.nextNode = sentinel;
        size -= 1;
        return removed.item;
    }
}
