package crossword.lexicon;

import crossword.datastructures.CustomArrayList;
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
        CustomArrayList<String> wordList = new CustomArrayList<>();
        wordList.addArray(words);
        int wordLength = 0;
        Lexicon instance = new Lexicon();
        
        CustomArrayList<String> expResult = new CustomArrayList<>();
        expResult.add("");
        
        CustomArrayList<String> result = instance.xLetterWords(wordList, wordLength);
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    @Test
    public void test5LetterWords() {
        System.out.println("5LetterWords");
        String[] words = {"", "a", "au", "alkaa", "aika", "ammatti", "ammua", "kaikki"};
        CustomArrayList<String> wordList = new CustomArrayList<>();
        wordList.addArray(words);
        int wordLength = 5;
        Lexicon instance = new Lexicon();
        
        CustomArrayList<String> expResult = new CustomArrayList<>();
        expResult.add("alkaa");
        expResult.add("ammua");
        
        CustomArrayList<String> result = instance.xLetterWords(wordList, wordLength);
        assertArrayEquals(expResult.toArray(), result.toArray());
    }


    /**
     * Test of addPlural method, of class Lexicon.
     */
    @Test
    public void testAddPluralSimple() {
        System.out.println("addPluralSimple");
        CustomArrayList<String> result = new CustomArrayList<>();
        String word = "kissa";
        int c = 21;
        Lexicon instance = new Lexicon();
        instance.addPlural(result, word, c);

        CustomArrayList<String> expResult = new CustomArrayList<>();
        expResult.add("kissat");
        
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    /**
     * Test of addPlural method, of class Lexicon.
     */
    @Test
    public void testAddPluralSimpleC() {
        System.out.println("addPluralSimpleC");
        CustomArrayList<String> result = new CustomArrayList<>();
        String word = "taik";
        int c = 1;
        Lexicon instance = new Lexicon();
        instance.addPlural(result, word, c);

        CustomArrayList<String> expResult = new CustomArrayList<>();
        expResult.add("taikit");
        
        assertArrayEquals(expResult.toArray(), result.toArray());
    }
    
    /**
     * Test of addPlural method, of class Lexicon.
     */
    @Test
    public void testAddPluralEt() {
        System.out.println("addPluralEt");
        CustomArrayList<String> result = new CustomArrayList<>();
        String word = "koski";
        int c = 23;
        Lexicon instance = new Lexicon();
        instance.addPlural(result, word, c);

        CustomArrayList<String> expResult = new CustomArrayList<>();
        expResult.add("kosket");
        
        assertArrayEquals(expResult.toArray(), result.toArray());
    }
    
        /**
     * Test of addPlural method, of class Lexicon.
     */
    @Test
    public void testDoNotAddPlural() {
        System.out.println("doNotAddPlural");
        CustomArrayList<String> result = new CustomArrayList<>();
        String word = "koski";
        int c = 4;
        Lexicon instance = new Lexicon();
        instance.addPlural(result, word, c);

        CustomArrayList<String> expResult = new CustomArrayList<>();
        
        assertArrayEquals(expResult.toArray(), result.toArray());
    }
}
