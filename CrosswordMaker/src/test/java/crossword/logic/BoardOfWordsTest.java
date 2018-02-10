package crossword.logic;

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
     * Test of createBoard method, of class BoardOfWords.
     */
    @Test
    public void testCreateSmallBoard() {
        System.out.println("createSmallBoard");
        String firstWord = "testi";
        int boardWidth = 6;
        int boardHight = 4;
        BoardOfWords instance = new BoardOfWords(boardWidth, boardHight);
        instance.createBoard(firstWord);
        char[][] result = instance.getBoard();
        
        char[][] expResult = new char[boardHight][boardWidth];
        for (int i = 0; i < expResult.length; i++) {
            for (int j = 0; j < expResult[0].length; j++) {
                expResult[i][j] = 'O';
            }
        }   
        expResult[0][5] = 'X';
        expResult[1][1] = 'X';
        expResult[1][3] = 'X';
        expResult[1][5] = 'X';
        expResult[3][1] = 'X';
        expResult[3][3] = 'X';
        expResult[3][5] = 'X';
        
        assertArrayEquals(expResult, result);
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
        int boardWidth = 5;
        int boardHight = 5;
        WordPosition position = new WordPosition(x, y, alignment, word.length());
        BoardOfWords instance = new BoardOfWords(boardWidth, boardHight);
        instance.createBoard("testi");
        instance.drawWord(word, position);
        char[][] result = instance.getBoard();

        
        char[][] expResult = new char[boardHight][boardWidth];
        for (int i = 0; i < expResult.length; i++) {
            for (int j = 0; j < expResult[0].length; j++) {
                expResult[i][j] = 'O';
            }
        }   
        expResult[1][1] = 'X';
        expResult[1][3] = 'X';
        expResult[3][1] = 'X';
        
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
        int boardWidth = 5;
        int boardHight = 5;
        WordPosition position = new WordPosition(x, y, alignment, word.length());
        BoardOfWords instance = new BoardOfWords(boardWidth, boardHight);
        instance.createBoard("testi");
        instance.drawWord(word, position);
        char[][] result = instance.getBoard();

        
        char[][] expResult = new char[boardHight][boardWidth];
        for (int i = 0; i < expResult.length; i++) {
            for (int j = 0; j < expResult[0].length; j++) {
                expResult[i][j] = 'O';
            }
        }   
        expResult[1][1] = 'X';
        expResult[1][3] = 'X';
        expResult[3][1] = 'X';
        
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
        BoardOfWords instance = new BoardOfWords(5, 5);
        instance.createBoard("testi");
        instance.drawWord(word, position);
        char result = instance.getLetter(x, y);

        char expResult = 'a';
        assertEquals(expResult, result);

    } 

}
