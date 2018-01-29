/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

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
        String[] words = {"", "a", "alku", "aika"};
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
        SearchTree instance = new SearchTree();
        instance.addListOfWords(wordList);

        Node expRoot = new Node(' ');
        Node a = new Node('a');
        Node l = new Node('l');
        Node k = new Node('k');
        Node u = new Node('u');
        Node i = new Node('i');
        Node k2 = new Node('k');
        Node a2 = new Node('a');
        expRoot.setChildForTesting(a);
        a.setWord("a");
        a.setChildForTesting(l);
        l.setChildForTesting(k);
        k.setChildForTesting(u);
        u.setWord("alku");
        l.setIsLastForTesting(false);
        l.setNextForTesting(i);
        i.setChildForTesting(k2);
        k2.setChildForTesting(a2);
        a2.setWord("aika");  
        
        compareNodesRecursively(instance.getRootForTesting(), expRoot);
    }

    /**
     * Test of addWord method, of class SearchTree.
     */
    @Test
    public void testAddWord() {
        System.out.println("addWord");
        String word = "tes";
        SearchTree instance = new SearchTree();
        instance.addWord(word);

        Node expRoot = new Node(' ');
        Node t = new Node('t');
        Node e = new Node('e');
        Node s = new Node('s');
        expRoot.setChildForTesting(t);
        t.setChildForTesting(e);
        e.setChildForTesting(s);
        s.setWord(word);

        compareNodesRecursively(instance.getRootForTesting(), expRoot);
    }

    private void compareNodesRecursively(Node result, Node expResult) {
        assertEquals(expResult.getKey(), result.getKey());
        assertEquals(expResult.isLast(), result.isLast());
        assertEquals(expResult.getWord(), result.getWord());
        Node childNode = result.getChild();
        Node expChildNode = expResult.getChild();
        while (childNode != null || expChildNode != null) {
            compareNodesRecursively(childNode, expChildNode);
            childNode = childNode.getNext();
            expChildNode = expChildNode.getNext();
        }
    }
}
