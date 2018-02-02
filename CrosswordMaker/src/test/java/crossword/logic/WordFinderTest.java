/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crossword.logic;

import java.util.ArrayList;
import java.util.Arrays;
import crossword.crosswordmaker.UserInterface;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
    public void testFindWordsWithFirstLetter() {
        System.out.println("findWordsWithFirstLetter");
        String[] words = {"a", "au", "alkaa", "aika", "ammatti", "ammua", "kaikki"};
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
        UserInterface ui = new UserInterface(wordList, 4, 5, "asti");
        WordPosition newPosition = new WordPosition(0, 0, 1, 5);
        WordFinder instance = new WordFinder(ui);
        ArrayList<String> result = instance.findWords(newPosition);

        String[] expWords = {"alkaa", "ammua"};
        ArrayList<String> expResult = new ArrayList<>(Arrays.asList(expWords));

        assertEquals(expResult, result);
    }

    /**
     * Test of findWords method so that 1st and 5th letter are known.
     */
    @Test
    public void testFindWordsWithTwoLetters() {
        System.out.println("findWordsWithTwoLetters");
        String[] words = {"a", "au", "alkaa", "aika", "alati", "ammua", "arkki", "kaikki"};
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
        UserInterface ui = new UserInterface(wordList, 5, 5, "astia");
        ui.drawWord("istua", 0, 4, 0);
        WordPosition newPosition = new WordPosition(0, 0, 1, 5);
        WordFinder instance = new WordFinder(ui);
        ArrayList<String> result = instance.findWords(newPosition);

        String[] expWords = {"alati", "arkki"};
        ArrayList<String> expResult = new ArrayList<>(Arrays.asList(expWords));

        assertEquals(expResult, result);
    }
}
