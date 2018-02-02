package crossword.lexicon;

import crossword.lexicon.Lexicon;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LexiconTest {

    public LexiconTest() {
    }

    /**
     * Test of getLexicon method, of class Lexicon.
     */
    @Test
    public void testGetLexicon() throws Exception {
        System.out.println("getLexicon");
        Lexicon instance = new Lexicon();
        String expResult = "aakkonen";
        String result = instance.getLexicon().get(0);
        assertEquals(expResult, result);
    }

    /**
     * Test of xLetterWords method, of class Lexicon.
     */
    @Test
    public void test0LetterWords() {
        System.out.println("0LetterWords");
        String[] words = {"", "a", "au", "alkaa", "aika", "ammatti", "ammua", "kaikki"};
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
        int wordLength = 0;
        Lexicon instance = new Lexicon();
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("");
        ArrayList<String> result = instance.xLetterWords(wordList, wordLength);
        assertEquals(expResult, result);
    }

    @Test
    public void test5LetterWords() {
        System.out.println("5LetterWords");
        String[] words = {"", "a", "au", "alkaa", "aika", "ammatti", "ammua", "kaikki"};
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
        int wordLength = 5;
        Lexicon instance = new Lexicon();
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("alkaa");
        expResult.add("ammua");
        ArrayList<String> result = instance.xLetterWords(wordList, wordLength);
        assertEquals(expResult, result);
    }
}
