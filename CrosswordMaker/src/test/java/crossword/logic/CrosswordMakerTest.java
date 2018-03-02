package crossword.logic;

import crossword.datastructures.CustomArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vanamo Piirainen
 */
public class CrosswordMakerTest {

    public CrosswordMakerTest() {
    }

    /**
     * Test of makeWordListsAccordingToLength method, of class CrosswordMaker.
     */
    @Test
    public void testMakeWordListsAccordingToLength() {
        System.out.println("makeWordListsAccordingToLength");
        String[] words = {"a", "au", "alkaa", "aika", "ammatti", "ammua", "kaikki"};
        CustomArrayList<String> wordList = new CustomArrayList<>();
        wordList.addArray(words);
        
        CrosswordMaker instance = new CrosswordMaker(5, 5, "testi", wordList);
        instance.makeWordListsAccordingToLength();
        CustomArrayList<String> result = instance.getWordsByLength().get(5);

        String[] fiveLetterWords = {"alkaa", "ammua"};
        CustomArrayList<String> expResult = new CustomArrayList<>();
        expResult.addArray(fiveLetterWords);

        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    /**
     * Test of getRandomWord method, of class CrosswordMaker.
     */
    @Test
    public void testGetRandomWordWithNoResults() {
        System.out.println("getRandomWordWithNoResults");
        String[] words = {"alkaa", "ammatti", "ammua", "kaikki"};
        CustomArrayList<String> wordList = new CustomArrayList<>();
        wordList.addArray(words);
        int length = 4;
        
        CrosswordMaker instance = new CrosswordMaker(5, 5, "sana", wordList);
        String expResult = "a";
        String result = instance.getRandomWord(length);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getRandomWord method, of class CrosswordMaker.
     */
    @Test
    public void testGetRandomWordWithOnePossibleResult() {
        System.out.println("getRandomWordWithOnePossibleResult");
        String[] words = {"almanakka", "ammatti", "aivastaa", "kuka"};
        CustomArrayList<String> wordList = new CustomArrayList<>();
        wordList.addArray(words);
        int length = 5;
        
        CrosswordMaker instance = new CrosswordMaker(5, 5, "sana", wordList);
        String expResult = "kuka";
        String result = instance.getRandomWord(length);
        assertEquals(expResult, result);
    }

    /**
     * Test of lettersToNumbers method, of class CrosswordMaker.
     */
    @Test
    public void testLettersToNumbers() {
        System.out.println("lettersToNumbers");
        String[] words = {"aaloe", "aatos", "raaja", "iglut", "apeus"};
        CustomArrayList<String> wordList = new CustomArrayList<>();
        wordList.addArray(words);
        CrosswordMaker instance = new CrosswordMaker(5, 5, "roima", wordList);
        instance.fillBoard();
        String expResult = "1\t2\t3\t4\t5\t\n\n5\tX\t6\tX\t7\t\n\n5\t5\t8\t2\t9\t\n\n10\tX\t11\tX\t11\t\n\n5\t5\t12\t2\t13\t\n\n\n";
        String result = instance.lettersToNumbers();
        assertEquals(expResult, result);
    }
}
