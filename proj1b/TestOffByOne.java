import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    static CharacterComparator offByN = new OffByN(5);

    // Your tests go here.
    @Test
    public void testOffByOne(){
        assertTrue(offByOne.equalChars('a','b'));
        assertTrue(offByOne.equalChars('b','a'));
        assertTrue(offByOne.equalChars('%','&'));
        assertFalse(offByOne.equalChars('a','a'));
        assertFalse(offByOne.equalChars('a','z'));
    }

    @Test
    public void testOffByN(){
        assertFalse(offByN.equalChars('a','b'));
        assertTrue(offByN.equalChars('B','G'));
        assertTrue(offByN.equalChars('a','f'));

    }
}
