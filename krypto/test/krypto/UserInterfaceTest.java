/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krypto;

import java.util.ArrayList;
import java.util.Arrays;
import junit.framework.Assert;
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
public class UserInterfaceTest {

    public UserInterfaceTest() {
    }

    /**
     * Test of building a user defined krypto board in the constructor
     */
    @Test
    public void testUserInterface() {
        System.out.println("UserInterface");
        String[] words = {"a", "au", "alkaa", "aika", "ammatti", "ammua", "kaikki"};
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
        UserInterface instance = new UserInterface(wordList, 2, 1, "aa");
        char[][] result = instance.getBoardOfWords();
        
        char[][] expResult = new char[1][2];
        expResult[0][0] = 'a';
        expResult[0][1] = 'a';
        
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of drawWord method, of class Krypto.
     */
    @Test
    public void testDrawWordHorizontal() {
        System.out.println("drawWordHorizontal");
        String word = "ai";
        int x = 3;
        int y = 3;
        int alignment = 0;
        String[] words = {"a", "au", "alkaa", "aika", "ammatti", "ammua", "kaikki"};
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
        UserInterface instance = new UserInterface(wordList, 5, 5, "a");
        instance.drawWord(word, x, y, alignment);
        char[][] result = instance.getBoardOfWords();

        char[][] expResult = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                expResult[i][j] = 'X';
            }
        }
        expResult[0][0] = 'a';
        expResult[3][3] = 'a';
        expResult[3][4] = 'i';
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testDrawWordVertical() {
        System.out.println("drawWordVertical");
        String word = "ai";
        int x = 3;
        int y = 3;
        int alignment = 1;
        String[] words = {"a", "au", "alkaa", "aika", "ammatti", "ammua", "kaikki"};
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
        UserInterface instance = new UserInterface(wordList, 5, 5, "a");
        instance.drawWord(word, x, y, alignment);
        char[][] result = instance.getBoardOfWords();

        char[][] expResult = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                expResult[i][j] = 'X';
            }
        }
        expResult[0][0] = 'a';
        expResult[3][3] = 'a';
        expResult[4][3] = 'i';
        assertArrayEquals(expResult, result);
    }
}
