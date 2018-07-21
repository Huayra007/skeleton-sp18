package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(4);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue(3);
        arb.enqueue(2);
        arb.enqueue(5);
        arb.enqueue(6);
        assertTrue(arb.isFull());
        assertEquals(3,arb.dequeue().longValue());
        arb.enqueue(7);
        arb.dequeue();
        arb.dequeue();
        arb.enqueue(0);
    }

    @Test
    public void testIterator(){
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(4);
        arb.enqueue(3);
        arb.enqueue(6);
        arb.enqueue(9);
        arb.enqueue(1);
        for (int item : arb)
            System.out.print(item + " ");
        System.out.println();
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
