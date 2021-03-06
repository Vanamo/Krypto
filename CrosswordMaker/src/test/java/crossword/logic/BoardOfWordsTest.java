package crossword.logic;

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
        int boardHeight = 4;
        BoardOfWords instance = new BoardOfWords(boardWidth, boardHeight);
        instance.createBoard(firstWord);
        char[][] result = instance.getBoard();

        char[][] expResult = new char[boardHeight][boardWidth];
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
     * Test of createBoard method, of class BoardOfWords.
     */
    @Test
    public void testCreateLargeBoard() {
        System.out.println("createLargeBoard");
        String firstWord = "12345678";
        int boardWidth = 8;
        int boardHeight = 6;
        BoardOfWords instance = new BoardOfWords(boardWidth, boardHeight);
        instance.createBoard(firstWord);
        instance.letterPositionsForLargeBoard();
        char[][] result = instance.getBoard();

        char[][] expResult = new char[boardHeight][boardWidth];
        for (int i = 0; i < expResult.length; i++) {
            for (int j = 0; j < expResult[0].length; j++) {
                expResult[i][j] = 'O';
            }
        }

        expResult[1][1] = 'X';
        expResult[1][3] = 'X';
        expResult[1][5] = 'X';
        expResult[1][7] = 'X';
        expResult[2][5] = 'X';
        expResult[2][7] = 'X';
        expResult[3][1] = 'X';
        expResult[3][3] = 'X';
        expResult[4][5] = 'X';
        expResult[4][7] = 'X';
        expResult[5][0] = 'X';
        expResult[5][1] = 'X';
        expResult[5][2] = 'X';
        expResult[5][3] = 'X';
        expResult[5][4] = 'X';

        assertArrayEquals(expResult, result);
    }

    /**
     * Test of createBoard method, of class BoardOfWords.
     */
    @Test
    public void testCreateVeryLargeBoard() {
        System.out.println("createVeryLargeBoard");
        String firstWord = "testi";
        int boardWidth = 16;
        int boardHeight = 15;
        BoardOfWords instance = new BoardOfWords(boardWidth, boardHeight);
        instance.createBoard(firstWord);
        char[][] result = instance.getBoard();

        char[][] expResult = new char[boardHeight][boardWidth];
        for (int i = 0; i < expResult.length; i++) {
            for (int j = 0; j < expResult[0].length; j++) {
                expResult[i][j] = 'O';
            }
        }
        expResult[0][5] = 'X';
        expResult[0][7] = 'X';
        expResult[0][14] = 'X';
        expResult[1][1] = 'X';
        expResult[1][3] = 'X';
        expResult[1][10] = 'X';
        expResult[1][12] = 'X';
        expResult[2][5] = 'X';
        expResult[2][7] = 'X';
        expResult[2][14] = 'X';
        expResult[3][1] = 'X';
        expResult[3][3] = 'X';
        expResult[3][10] = 'X';
        expResult[3][12] = 'X';
        expResult[4][5] = 'X';
        expResult[4][7] = 'X';
        expResult[4][14] = 'X';
        expResult[5][0] = 'X';
        expResult[5][2] = 'X';
        expResult[5][4] = 'X';
        expResult[5][9] = 'X';
        expResult[5][11] = 'X';
        expResult[5][15] = 'X';
        expResult[6][6] = 'X';
        expResult[6][8] = 'X';
        expResult[6][9] = 'X';
        expResult[6][13] = 'X';
        expResult[6][15] = 'X';
        expResult[7][0] = 'X';
        expResult[7][2] = 'X';
        expResult[7][4] = 'X';
        expResult[7][9] = 'X';
        expResult[7][11] = 'X';
        expResult[7][15] = 'X';
        expResult[8][6] = 'X';
        expResult[8][13] = 'X';
        expResult[8][15] = 'X';
        expResult[9][0] = 'X';
        expResult[9][2] = 'X';
        expResult[9][4] = 'X';
        expResult[9][9] = 'X';
        expResult[9][11] = 'X';
        expResult[9][15] = 'X';
        expResult[10][5] = 'X';
        expResult[10][7] = 'X';
        expResult[10][14] = 'X';
        expResult[11][1] = 'X';
        expResult[11][3] = 'X';
        expResult[11][10] = 'X';
        expResult[11][12] = 'X';
        expResult[12][5] = 'X';
        expResult[12][7] = 'X';
        expResult[12][8] = 'X';
        expResult[12][14] = 'X';
        expResult[13][1] = 'X';
        expResult[13][3] = 'X';
        expResult[13][10] = 'X';
        expResult[13][12] = 'X';
        expResult[14][5] = 'X';
        expResult[14][7] = 'X';
        expResult[14][8] = 'X';
        expResult[14][14] = 'X';

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
        Alignment alignment = Alignment.HORIZONTAL;
        int boardWidth = 5;
        int boardHeight = 5;
        WordPosition position = new WordPosition(x, y, alignment, word.length());
        BoardOfWords instance = new BoardOfWords(boardWidth, boardHeight);
        instance.createBoard("testi");
        instance.drawWord(word, position);
        char[][] result = instance.getBoard();

        char[][] expResult = new char[boardHeight][boardWidth];
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
        Alignment alignment = Alignment.VERTICAL;
        int boardWidth = 5;
        int boardHeight = 5;
        WordPosition position = new WordPosition(x, y, alignment, word.length());
        BoardOfWords instance = new BoardOfWords(boardWidth, boardHeight);
        instance.createBoard("testi");
        instance.drawWord(word, position);
        char[][] result = instance.getBoard();

        char[][] expResult = new char[boardHeight][boardWidth];
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
     * Test of drawFirstWord method, of class BoardOfWords.
     */
    @Test
    public void testDrawFirstWord() {
        System.out.println("drawFirstWord");
        String word = "ai";
        int boardWidth = 5;
        int boardHeight = 5;
        BoardOfWords instance = new BoardOfWords(boardWidth, boardHeight);
        instance.createBoard(word);
        instance.drawFirstWord(word);
        char[][] result = instance.getBoard();

        char[][] expResult = new char[boardHeight][boardWidth];
        for (int i = 0; i < expResult.length; i++) {
            for (int j = 0; j < expResult[0].length; j++) {
                expResult[i][j] = 'O';
            }
        }
        expResult[0][2] = 'X';
        expResult[0][3] = 'X';
        expResult[0][4] = 'X';
        expResult[1][1] = 'X';
        expResult[1][3] = 'X';
        expResult[3][1] = 'X';
        expResult[3][3] = 'X';

        expResult[0][0] = 'a';
        expResult[0][1] = 'i';

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
        Alignment alignment = Alignment.VERTICAL;
        WordPosition position = new WordPosition(x, y, alignment, word.length());
        BoardOfWords instance = new BoardOfWords(5, 5);
        instance.createBoard("testi");
        instance.drawWord(word, position);
        char result = instance.getLetter(x, y);

        char expResult = 'a';
        assertEquals(expResult, result);

    }

    /**
     * Test of makeCopy method, of class BoardOfWords.
     */
    @Test
    public void testMakeCopy() {
        System.out.println("makeCopy");
        int width = 5;
        int height = 7;
        BoardOfWords instance = new BoardOfWords(width, height);
        instance.createBoard("testi");
        BoardOfWords result = instance.makeCopy();

        char[][] expResult = new char[height][width];
        for (int i = 0; i < expResult.length; i++) {
            for (int j = 0; j < expResult[0].length; j++) {
                expResult[i][j] = 'O';
            }
        }
        expResult[1][1] = 'X';
        expResult[1][3] = 'X';
        expResult[3][1] = 'X';
        expResult[3][3] = 'X';
        expResult[5][1] = 'X';
        expResult[5][3] = 'X';
        
        
        assertArrayEquals(expResult, result.getBoard());
    }
}
