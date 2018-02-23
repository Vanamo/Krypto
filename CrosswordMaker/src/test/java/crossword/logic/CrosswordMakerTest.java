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
}
