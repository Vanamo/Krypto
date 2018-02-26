/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crossword.logic;

import crossword.datastructures.CustomArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zz
 */
public class QuickSortTest {

    public QuickSortTest() {
    }

    /**
     * Test of sort method, of class QuickSort.
     */
    @Test
    public void testSort() {
        System.out.println("sort");
        WordPosition p1 = new WordPosition(0, 0, Alignment.VERTICAL, 5);
        WordPosition p2 = new WordPosition(2, 0, Alignment.VERTICAL, 5);
        WordPosition p3 = new WordPosition(4, 0, Alignment.VERTICAL, 5);
        WordPosition p4 = new WordPosition(0, 2, Alignment.HORIZONTAL, 5);
        WordPosition p5 = new WordPosition(0, 4, Alignment.HORIZONTAL, 5);
        WordPosition[] posArray = {p1, p2, p3, p4, p5};
        CustomArrayList<WordPosition> positions = new CustomArrayList<>();
        positions.addArray(posArray);
        
        QuickSort instance = new QuickSort();
        instance.sort(positions);
        
        WordPosition[] resultArray = {p1, p4, p2, p5, p3};
        CustomArrayList<WordPosition> expResult = new CustomArrayList<>();
        expResult.addArray(resultArray);
        
        assertArrayEquals(expResult.toArray(), positions.toArray());
    }

}
