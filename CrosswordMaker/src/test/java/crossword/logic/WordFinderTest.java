package crossword.logic;

import crossword.datastructures.CustomArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vanamo Piirainen
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
        WordPosition newPosition = new WordPosition(0, 0, Alignment.VERTICAL, 5);

        String firstWord = "asti";
        int width = 4;
        int hight = 5;

        CrosswordMaker crosswordMaker = new CrosswordMaker(width, hight,
                firstWord, wordList);
        BoardOfWords board = crosswordMaker.getBoardOfWords();
        board.drawFirstWord(firstWord);
        WordFinder wordFinder = new WordFinder(board, crosswordMaker.getWordsByLength());
        CustomArrayList<String> result = wordFinder.findWords(newPosition);

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
        int width = 5;
        int hight = 5;
        BoardOfWords board = new BoardOfWords(width, hight);
        board.createBoard(firstWord);
        board.drawFirstWord(firstWord);

        WordPosition secondWordp = new WordPosition(0, 4, Alignment.HORIZONTAL, 5);
        board.drawWord("istua", secondWordp);

        CrosswordMaker crosswordMaker = new CrosswordMaker(width, hight,
                firstWord, wordList);
        WordFinder wordFinder = new WordFinder(board, crosswordMaker.getWordsByLength());
        WordPosition position = new WordPosition(0, 0, Alignment.VERTICAL, 5);
        CustomArrayList<String> result = wordFinder.findWords(position);

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

        WordPosition p1 = new WordPosition(0, 0, Alignment.VERTICAL, 5);
        WordPosition p2 = new WordPosition(2, 0, Alignment.VERTICAL, 5);
        WordPosition p3 = new WordPosition(4, 0, Alignment.VERTICAL, 5);
        WordPosition p4 = new WordPosition(0, 2, Alignment.HORIZONTAL, 5);
        WordPosition p5 = new WordPosition(0, 4, Alignment.HORIZONTAL, 5);
        WordPosition[] posArray = {p1, p2, p3, p4, p5};
        CustomArrayList<WordPosition> positions = new CustomArrayList<>();
        positions.addArray(posArray);

        String[] words = {"aaloe", "apila", "saada", "lelli", "ahava", "alati", 
            "laaja", "ohari"};
        CustomArrayList<String> wordList = new CustomArrayList<>();
        wordList.addArray(words);
        String firstWord = "sello";
        int width = 5;
        int hight = 5;
        BoardOfWords board = new BoardOfWords(width, hight);
        board.createBoard(firstWord);
        board.drawFirstWord(firstWord);

        CrosswordMaker crosswordMaker = new CrosswordMaker(width, hight,
                firstWord, wordList);
        WordFinder wordFinder = new WordFinder(board, crosswordMaker.getWordsByLength());
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

        WordPosition p1 = new WordPosition(0, 0, Alignment.VERTICAL, 5);
        WordPosition p2 = new WordPosition(2, 0, Alignment.VERTICAL, 5);
        WordPosition p3 = new WordPosition(4, 0, Alignment.VERTICAL, 5);
        WordPosition p4 = new WordPosition(0, 2, Alignment.HORIZONTAL, 5);
        WordPosition p5 = new WordPosition(0, 4, Alignment.HORIZONTAL, 5);
        WordPosition[] posArray = {p1, p2, p3, p4, p5};
        CustomArrayList<WordPosition> positions = new CustomArrayList<>();
        positions.addArray(posArray);

        String[] words = {"ahava", "alati", "saada", "laaja", "orava", "akana"};
        CustomArrayList<String> wordList = new CustomArrayList<>();
        wordList.addArray(words);
        String firstWord = "sello";
        int width = 5;
        int hight = 5;
        BoardOfWords board = new BoardOfWords(width, hight);
        board.createBoard(firstWord);
        board.drawFirstWord(firstWord);

        CrosswordMaker crosswordMaker = new CrosswordMaker(width, hight,
                firstWord, wordList);
        WordFinder wordFinder = new WordFinder(board, crosswordMaker.getWordsByLength());
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

    /**
     * Test of findWords method, of class WordFinder.
     */
    @Test
    public void testCollectStatistics() {
        System.out.println("collectStatistics");
        WordPosition p1 = new WordPosition(0, 0, Alignment.VERTICAL, 5);
        WordPosition p2 = new WordPosition(2, 0, Alignment.VERTICAL, 5);
        WordPosition p3 = new WordPosition(4, 0, Alignment.VERTICAL, 5);
        WordPosition p4 = new WordPosition(0, 2, Alignment.HORIZONTAL, 5);
        WordPosition p5 = new WordPosition(0, 4, Alignment.HORIZONTAL, 5);
        WordPosition[] posArray = {p1, p2, p3, p4, p5};
        CustomArrayList<WordPosition> positions = new CustomArrayList<>();
        positions.addArray(posArray);

        String[] words = {"ahava", "alati", "saada", "laaja", "orava", "akana"};
        CustomArrayList<String> wordList = new CustomArrayList<>();
        wordList.addArray(words);
        String firstWord = "sello";
        int width = 5;
        int hight = 5;
        BoardOfWords board = new BoardOfWords(width, hight);
        board.createBoard(firstWord);
        board.drawFirstWord(firstWord);

        CrosswordMaker crosswordMaker = new CrosswordMaker(width, hight,
                firstWord, wordList);
        WordFinder wordFinder = new WordFinder(board, crosswordMaker.getWordsByLength());
        wordFinder.findWordsForAllPositions(positions);
        
        CustomArrayList<Integer> result = wordFinder.getTimesVisitedAtPosition();
        
        Object[] expResult = {1, 1, 1, 1, 1};
        
        assertArrayEquals(expResult, result.toArray());
    }

}
