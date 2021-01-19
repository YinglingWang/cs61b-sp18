import org.junit.Test;
import static org.junit.Assert.*;

/** Tests ArrayDeque class */
public class ArrayDequeTest {

    /**
     * Tests if ArrayDeque works when it's empty
     */
    @Test
    public void testEmpty() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());
        assertNull(q.get(0));
        assertNull(q.removeFirst());
        assertNull(q.removeLast());
    }

    /**
     * Tests if ArrayDeque.addFirst & ArrayDeque.addLast methods without resizing.
     */
    @Test
    public void testAdd() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.addFirst(0);
        q.addLast(1);
        assertFalse(q.isEmpty());
        assertEquals(2, q.size());

        int actual0th = q.get(0);
        int actual1st = q.get(1);
        assertEquals(0, actual0th);
        assertEquals(1, actual1st);
        q.printDeque();
    }

    /**
     * Tests if ArrayDeque.removeFirst & ArrayDeque.removeLast method without resizing.
     */
    @Test
    public void testRemove() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.addFirst(0);
        q.addLast(1);
        assertFalse(q.isEmpty());
        assertEquals(2, q.size());

        int actualFirst = q.removeFirst();
        int actualLast = q.removeLast();
        assertEquals(0, actualFirst);
        assertEquals(1, actualLast);
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());

        q.addFirst(1);
        q.addFirst(0);
        int actualLast1 = q.removeLast();
        int actualLast0 = q.removeLast();
        assertEquals(1, actualLast1);
        assertEquals(0, actualLast0);
        q.printDeque();
    }

    /**
     * Tests if ArrayDeque.addFirst & ArrayDeque.addLast methods with resizing.
     */
    @Test
    public void testAddResize() {
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < 10; i++) {
            q.addFirst(i);
        }
        assertEquals(10, q.size());
        int actualFirst = q.get(0);
        int actualLast = q.get(9);
        assertEquals(9, actualFirst);
        assertEquals(0, actualLast);

        for (int i = 10; i < 100; i++) {
            q.addLast(i);
        }
        actualLast = q.get(99);
        assertEquals(100, q.size());
        assertEquals(99, actualLast);
    }

    /**
     * Tests if ArrayDeque.removeFirst & ArrayDeque.removeLast method with resizing.
     *
     * @NOTE: To check memory usage, ArrayDeque.capacity should be made public and the 4 lines below
     * should be commented in.
     */
    @Test
    public void testRemoveResize() {
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i <= 100; i++) {
            q.addFirst(i);
        }
        for (int i = 100; i >= 10; i--) {
            int removed = q.removeFirst();
            assertEquals(i, removed);
        }

        for (int i = 10; i <= 1000; i++) {
            q.addLast(i);
        }
        for (int i = 1000; i >= 10; i--) {
            int removed = q.removeLast();
            assertEquals(i, removed);
        }
    }
}
