/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krypto;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vseppane
 */
public class SearchTreeTest {
    
    public SearchTreeTest() {
    }

    /**
     * Test of addListOfWords method, of class SearchTree.
     */
    @Test
    public void testAddListOfWords() {
        System.out.println("addListOfWords");
        ArrayList<String> wordList = null;
        SearchTree instance = new SearchTree();
        instance.addListOfWords(wordList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addWord method, of class SearchTree.
     */
    @Test
    public void testAddWord() {
        System.out.println("addWord");
        String word = "";
        SearchTree instance = new SearchTree();
        instance.addWord(word);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
}
