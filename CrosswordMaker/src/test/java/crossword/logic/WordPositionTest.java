package crossword.logic;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vanamo Piirainen
 */
public class WordPositionTest {

    public WordPositionTest() {
    }

    /**
     * Test of equals method, of class WordPosition.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        WordPosition obj = new WordPosition(0, 0, Alignment.HORIZONTAL, 0);
        WordPosition instance = new WordPosition(0, 0, Alignment.HORIZONTAL, 0);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class WordPosition.
     */
    @Test
    public void testEqualsIsFalse() {
        System.out.println("equalsIsFalse");
        WordPosition obj = new WordPosition(0, 0, Alignment.VERTICAL, 0);
        WordPosition instance = new WordPosition(0, 0, Alignment.HORIZONTAL, 0);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class WordPosition.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        WordPosition o = new WordPosition(0, 0, Alignment.VERTICAL, 0);
        WordPosition instance = new WordPosition(0, 0, Alignment.HORIZONTAL, 0);
        int expResult = -1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class WordPosition.
     */
    @Test
    public void testCompareToEqual() {
        System.out.println("compareToEqual");
        WordPosition o = new WordPosition(0, 0, Alignment.HORIZONTAL, 1);
        WordPosition instance = new WordPosition(0, 0, Alignment.HORIZONTAL, 0);
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }
}
