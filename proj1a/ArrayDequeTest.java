import org.junit.Test;
import static org.junit.Assert.*;


public class ArrayDequeTest {

    @Test
    public void testIsEmpty(){
        ArrayDeque<String> a = new ArrayDeque<>();
        assertEquals(true,a.isEmpty());
    }

    @Test
    public void testAddRemove(){
        ArrayDeque<String> a = new ArrayDeque<>();
        a.addFirst("Front");
        a.addFirst("Frontt");
        a.addFirst("Fronttt");
        assertEquals("Fronttt",a.getItem(0));
        assertEquals(3,a.size());
        a.addLast("Back");
        a.addLast("Backk");
        assertEquals(5,a.size());
        a.removeFirst();
        assertEquals("Frontt",a.getItem(0));
        a.removeLast();
        assertEquals(3,a.size());
    }

    @Test
    public void testAddRemove2(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addLast(7);
        a.addLast(6);
        a.addLast(5);
        a.addLast(4);
        assertEquals(4,a.size());
        a.removeFirst();
        a.removeLast();
        a.removeLast();
    }
}

