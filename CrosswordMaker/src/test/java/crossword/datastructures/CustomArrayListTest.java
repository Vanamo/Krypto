package crossword.datastructures;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vanamo Piirainen
 */
public class CustomArrayListTest {

    private TestCustomArrayList<Integer> emptyArray;
    private TestCustomArrayList<Integer> arrayWithOneInteger;
    private TestCustomArrayList<Integer> arrayWithManyIntegers;

    private static class TestCustomArrayList<E> extends CustomArrayList<E> {

        public Object[] getCustomArrayForTesting() {
            return customArray;
        }
    }

    @Before
    public void setUp() {
        emptyArray = new TestCustomArrayList<>();
        arrayWithOneInteger = new TestCustomArrayList<>();
        arrayWithManyIntegers = new TestCustomArrayList<>();
        arrayWithOneInteger.add(1);

        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        arrayWithManyIntegers.addArray(integers);
    }

    /**
     * Test of add method, of class CustomArrayList.
     */
    @Test
    public void testAdd() {
        System.out.println("add");

        Object[] result = arrayWithOneInteger.getCustomArrayForTesting();
        Integer[] expResult = new Integer[10];
        expResult[0] = 1;

        assertArrayEquals(expResult, result);
    }

    /**
     * Test of addArray method, of class CustomArrayList.
     */
    @Test
    public void testAddArray() {
        System.out.println("addArray");

        Object[] result = arrayWithManyIntegers.getCustomArrayForTesting();
        Integer[] expResult = new Integer[20];
        int toAdd = 1;
        for (int i = 0; i < 11; i++) {
            expResult[i] = toAdd;
            toAdd++;
        }

        assertArrayEquals(expResult, result);
    }

    /**
     * Test of get method, of class CustomArrayList.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int index = 0;
        Integer expResult = 1;
        Integer result = arrayWithOneInteger.get(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of get method with non-existent index.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetWithWrongIndex() {
        System.out.println("getWithWrongIndex");
        int index = 0;
        emptyArray.get(index);
    }

    /**
     * Test of size method, of class CustomArrayList.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        int expResult = 11;
        int result = arrayWithManyIntegers.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class CustomArrayList.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        boolean expResult = true;
        boolean result = emptyArray.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class CustomArrayList.
     */
    @Test
    public void testContainsIsTrue() {
        System.out.println("containsIsTrue");
        boolean expResult = true;
        boolean result = arrayWithOneInteger.contains(1);
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class CustomArrayList.
     */
    @Test
    public void testContainsIsFalse() {
        System.out.println("containsIsFalse");
        boolean expResult = false;
        boolean result = arrayWithOneInteger.contains(0);
        assertEquals(expResult, result);
    }

    /**
     * Test of indexOf method, of class CustomArrayList.
     */
    @Test
    public void testIndexOf() {
        System.out.println("indexOf");
        int expResult = 3;
        int result = arrayWithManyIntegers.indexOf(4);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class CustomArrayList.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Integer[] expResult = new Integer[10];

        arrayWithOneInteger.remove(1);
        Object[] result = arrayWithOneInteger.getCustomArrayForTesting();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of remove method, of class CustomArrayList.
     */
    @Test
    public void testRemoveIsFalse() {
        System.out.println("removeIsFalse");
        boolean expResult = false;

        boolean result = arrayWithManyIntegers.remove(0);
        assertEquals(expResult, result);
    }

    /**
     * Test of replace method, of class CustomArrayList.
     */
    @Test
    public void testReplace() {
        System.out.println("replace");
        int index = 0;
        arrayWithOneInteger.replace(index, 2);
        Integer result = arrayWithOneInteger.get(index);
        Integer expResult = 2;
        assertEquals(expResult, result);
    }
}
