package crossword.logic;

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
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
        CrosswordMaker instance = new CrosswordMaker(5, 5, "testi", wordList);
        instance.makeWordListsAccordingToLength();
        ArrayList<String> result = instance.getWordsByLength().get(5);

        String[] fiveLetterWords = {"alkaa", "ammua"};
        ArrayList<String> expResult = new ArrayList<>(Arrays.asList(fiveLetterWords));

        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    /**
     * Test of getRandomWord method, of class CrosswordMaker.
     */
    @Test
    public void testGetRandomWordWithNoResults() {
        System.out.println("getRandomWordWithNoResults");
        String[] words = {"alkaa", "ammatti", "ammua", "kaikki"};
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
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
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
        int length = 5;
        CrosswordMaker instance = new CrosswordMaker(5, 5, "sana", wordList);
        String expResult = "kuka";
        String result = instance.getRandomWord(length);
        assertEquals(expResult, result);
    }
}
