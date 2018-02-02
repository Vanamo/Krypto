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
        CrosswordMaker cm = new CrosswordMaker(4, 5, "asti", wordList);
        WordPosition newPosition = new WordPosition(0, 0, 1, 5);
        WordFinder instance = new WordFinder(cm);
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
        CrosswordMaker cm = new CrosswordMaker(5, 5, "astia", wordList);
        WordPosition secondWordp = new WordPosition(0, 4, 0, 5);
        cm.getBoardOfWords().drawWord("istua", secondWordp);
        WordFinder instance = new WordFinder(cm);
        WordPosition position = new WordPosition(0, 0, 1, 5);
        ArrayList<String> result = instance.findWords(position);

        String[] expWords = {"alati", "arkki"};
        ArrayList<String> expResult = new ArrayList<>(Arrays.asList(expWords));

        assertEquals(expResult, result);
    }

    /**
     * Test of findWordsForAllPositions method, of class WordFinder.
     */
    @Test
    public void testFindWordsForAllPositions() {
        System.out.println("findWordsForAllPositions");

        WordPosition p0 = new WordPosition(0, 0, 0, 5);
        
        WordPosition p1 = new WordPosition(0, 0, 1, 5);
        WordPosition p2 = new WordPosition(2, 0, 1, 5);
        WordPosition p3 = new WordPosition(4, 0, 1, 5);
        WordPosition p4 = new WordPosition(0, 2, 0, 5);
        WordPosition p5 = new WordPosition(0, 4, 0, 5);
        ArrayList<WordPosition> positions = new ArrayList<>();
        positions.add(p1);
        positions.add(p2);
        positions.add(p3);
        positions.add(p4);
        positions.add(p5);

        String[] words = {"ahava", "alati", "saada", "laaja", "ohari"};
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
        CrosswordMaker cm = new CrosswordMaker(5, 5, "sello", wordList);
        WordFinder wordFinder = new WordFinder(cm);
        BoardOfWords instance = wordFinder.findWordsForAllPositions(positions);        
        char[][] result = instance.getBoard();
        
        BoardOfWords board = new BoardOfWords(5, 5);
        board.drawWord("sello", p0);
        board.drawWord("saada", p1);
        board.drawWord("laaja", p2);
        board.drawWord("ohari", p3);
        board.drawWord("ahava", p4);
        board.drawWord("alati", p5);
        
        char[][] expResult = board.getBoard();

        assertArrayEquals(expResult, result);
    }

}
