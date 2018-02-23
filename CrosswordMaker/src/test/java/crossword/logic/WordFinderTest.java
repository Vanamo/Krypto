package crossword.logic;

import crossword.datastructures.CustomArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zz
 */
public class WordFinderTest {

    public WordFinderTest() {
    }

    /**
     * Test of findWords method so that first letter of the word is known.
     */
    @Test
    public void testFindWordsWithFirstLetterKnown() {
        System.out.println("findWordsWithFirstLetterKnown");
        String[] words = {"a", "au", "alkaa", "aika", "ammatti", "ammua", "kaikki"};
        CustomArrayList<String> wordList = new CustomArrayList<>();
        wordList.addArray(words);
        WordPosition newPosition = new WordPosition(0, 0, 1, 5);

        String firstWord = "asti";
        BoardOfWords board = new BoardOfWords(4, 5);
        board.createBoard(firstWord);
        board.drawFirstWord(firstWord);

        WordFinder instance = new WordFinder(board, wordList);
        CustomArrayList<String> result = instance.findWords(newPosition);

        String[] expWords = {"alkaa", "ammua"};
        CustomArrayList<String> expResult = new CustomArrayList<>();
        expResult.addArray(expWords);

        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    /**
     * Test of findWords method so that 1st and 5th letter are known.
     */
    @Test
    public void testFindWordsWithTwoLettersKnown() {
        System.out.println("findWordsWithTwoLettersKnown");
        String[] words = {"a", "au", "alkaa", "aika", "alati", "ammua", "arkki", "kaikki"};
        CustomArrayList<String> wordList = new CustomArrayList<>();
        wordList.addArray(words);

        String firstWord = "astia";
        BoardOfWords board = new BoardOfWords(5, 5);
        board.createBoard(firstWord);
        board.drawFirstWord(firstWord);

        WordPosition secondWordp = new WordPosition(0, 4, 0, 5);
        board.drawWord("istua", secondWordp);
        
        WordFinder instance = new WordFinder(board, wordList);
        WordPosition position = new WordPosition(0, 0, 1, 5);
        CustomArrayList<String> result = instance.findWords(position);

        String[] expWords = {"alati", "arkki"};
        CustomArrayList<String> expResult = new CustomArrayList<>();
        expResult.addArray(expWords);

        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    /**
     * Test of findWordsForAllPositions method, of class WordFinder.
     */
    @Test
    public void testFindWordsForAllPositions() {
        System.out.println("findWordsForAllPositions");

        WordPosition p1 = new WordPosition(0, 0, 1, 5);
        WordPosition p2 = new WordPosition(2, 0, 1, 5);
        WordPosition p3 = new WordPosition(4, 0, 1, 5);
        WordPosition p4 = new WordPosition(0, 2, 0, 5);
        WordPosition p5 = new WordPosition(0, 4, 0, 5);
        CustomArrayList<WordPosition> positions = new CustomArrayList<>();
        positions.add(p1);
        positions.add(p2);
        positions.add(p3);
        positions.add(p4);
        positions.add(p5);

        String[] words = {"aaloe", "apila", "saada", "lelli", "ahava", "alati", 
            "laaja", "ohari"};
        CustomArrayList<String> wordList = new CustomArrayList<>();
        wordList.addArray(words);
        String firstWord = "sello";
        BoardOfWords board = new BoardOfWords(5, 5);
        board.createBoard(firstWord);
        board.drawFirstWord(firstWord);

        WordFinder wordFinder = new WordFinder(board, wordList);
        BoardOfWords instance = wordFinder.findWordsForAllPositions(positions);
        char[][] result = instance.getBoard();

        board.drawWord("saada", p1);
        board.drawWord("laaja", p2);
        board.drawWord("ohari", p3);
        board.drawWord("ahava", p4);
        board.drawWord("alati", p5);

        char[][] expResult = board.getBoard();

        assertArrayEquals(expResult, result);
    }
    
    /**
     * Test of findWordsForAllPositions method, of class WordFinder.
     */
    @Test
    public void testFindWordsForAllPositionsWithoutUsingTheSameWordTwice() {
        System.out.println("findWordsForAllPositionsWithoutUsingTheSameWordTwice");

        WordPosition p1 = new WordPosition(0, 0, 1, 5);
        WordPosition p2 = new WordPosition(2, 0, 1, 5);
        WordPosition p3 = new WordPosition(4, 0, 1, 5);
        WordPosition p4 = new WordPosition(0, 2, 0, 5);
        WordPosition p5 = new WordPosition(0, 4, 0, 5);
        CustomArrayList<WordPosition> positions = new CustomArrayList<>();
        positions.add(p1);
        positions.add(p2);
        positions.add(p3);
        positions.add(p4);
        positions.add(p5);

        String[] words = {"ahava", "alati", "saada", "laaja", "orava", "akana"};
        CustomArrayList<String> wordList = new CustomArrayList<>();
        wordList.addArray(words);
        String firstWord = "sello";
        BoardOfWords board = new BoardOfWords(5, 5);
        board.createBoard(firstWord);
        board.drawFirstWord(firstWord);

        WordFinder wordFinder = new WordFinder(board, wordList);
        BoardOfWords instance = wordFinder.findWordsForAllPositions(positions);
        char[][] result = instance.getBoard();

        board.drawWord("saada", p1);
        board.drawWord("laaja", p2);
        board.drawWord("orava", p3);
        board.drawWord("ahava", p4);
        board.drawWord("akana", p5);

        char[][] expResult = board.getBoard();

        assertArrayEquals(expResult, result);
    }

}
