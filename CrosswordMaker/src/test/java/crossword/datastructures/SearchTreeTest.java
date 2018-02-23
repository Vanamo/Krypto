package crossword.datastructures;

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
        CustomArrayList<String> wordList = new CustomArrayList<>();
        wordList.addArray(words);
        SearchTree instance = new SearchTree();
        instance.addListOfWords(wordList);

        SearchTreeNode expRoot = new SearchTreeNode(' ');
        SearchTreeNode a = new SearchTreeNode('a');
        SearchTreeNode l = new SearchTreeNode('l');
        SearchTreeNode k = new SearchTreeNode('k');
        SearchTreeNode u = new SearchTreeNode('u');
        SearchTreeNode i = new SearchTreeNode('i');
        SearchTreeNode k2 = new SearchTreeNode('k');
        SearchTreeNode a2 = new SearchTreeNode('a');
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
        
        compareNodesRecursively(instance.getRoot(), expRoot);
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

        SearchTreeNode expRoot = new SearchTreeNode(' ');
        SearchTreeNode t = new SearchTreeNode('t');
        SearchTreeNode e = new SearchTreeNode('e');
        SearchTreeNode s = new SearchTreeNode('s');
        expRoot.setChildForTesting(t);
        t.setChildForTesting(e);
        e.setChildForTesting(s);
        s.setWord(word);

        compareNodesRecursively(instance.getRoot(), expRoot);
    }

    private void compareNodesRecursively(SearchTreeNode result, SearchTreeNode expResult) {
        assertEquals(expResult.getKey(), result.getKey());
        assertEquals(expResult.isLast(), result.isLast());
        assertEquals(expResult.getWord(), result.getWord());
        SearchTreeNode childNode = result.getChild();
        SearchTreeNode expChildNode = expResult.getChild();
        while (childNode != null || expChildNode != null) {
            compareNodesRecursively(childNode, expChildNode);
            childNode = childNode.getNext();
            expChildNode = expChildNode.getNext();
        }
    }
}
