package crossword.logic;

import crossword.crosswordmaker.UserInterface;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vanamo Piirainen
 */
public class BoardOfWordsTest {
    
    public BoardOfWordsTest() {
    }

    /**
     * Test of drawWord method, of class BoardOfWords.
     */
    @Test
    public void testDrawWordHorizontal() {
        System.out.println("drawWordHorizontal");
        String word = "ai";
        int x = 3;
        int y = 3;
        int alignment = 0;
        WordPosition position = new WordPosition(x, y, alignment, word.length());
        BoardOfWords instance = new BoardOfWords();
        instance.drawWord(word, position);
        char[][] result = instance.getBoard();

        char[][] expResult = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                expResult[i][j] = 'X';
            }
        }
        expResult[3][3] = 'a';
        expResult[3][4] = 'i';
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testDrawWordVertical() {
        System.out.println("drawWordVertical");
        String word = "ai";
        int x = 3;
        int y = 3;
        int alignment = 1;
        WordPosition position = new WordPosition(x, y, alignment, word.length());
        BoardOfWords instance = new BoardOfWords();
        instance.drawWord(word, position);
        char[][] result = instance.getBoard();

        char[][] expResult = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                expResult[i][j] = 'X';
            }
        }
        expResult[3][3] = 'a';
        expResult[4][3] = 'i';
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getLetter method, of class BoardOfWords.
     */
    @Test
    public void testGetLetter() {
        System.out.println("getLetter");
        String word = "a";
        int x = 0;
        int y = 0;
        int alignment = 1;
        WordPosition position = new WordPosition(x, y, alignment, word.length());
        BoardOfWords instance = new BoardOfWords();
        instance.drawWord(word, position);
        char result = instance.getLetter(x, y);

        char expResult = 'a';
        assertEquals(expResult, result);

    } 
}
