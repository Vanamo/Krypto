package crossword.logic;

import crossword.datastructures.CustomArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vanamo Piirainen
 */
public class WordPositionFinderTest {
    
    public WordPositionFinderTest() {
    }

    /**
     * Test of findPositions method, of class WordPositionFinder.
     */
    @Test
    public void testFindPositions() {
        System.out.println("findPositions");

        char[][] board = new char[3][3];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                board[y][x] = 'O';
            }
        }
        board[0][0] = 'X';
        board[1][1] = 'X';
        board[2][2] = 'X';

        WordPositionFinder instance = new WordPositionFinder(board);
        CustomArrayList<WordPosition> expResult = new CustomArrayList<>();
        CustomArrayList result = instance.findPositions();

        expResult.add(new WordPosition(1, 0, Alignment.HORIZONTAL, 2));
        expResult.add(new WordPosition(0, 1, Alignment.VERTICAL, 2));
        expResult.add(new WordPosition(0, 2, Alignment.HORIZONTAL, 2));
        expResult.add(new WordPosition(2, 0, Alignment.VERTICAL, 2));

        assertArrayEquals(expResult.toArray(), result.toArray());
    }
}
