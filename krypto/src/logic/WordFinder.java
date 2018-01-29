package logic;

import java.util.ArrayList;
import krypto.UserInterface;

/**
 *
 * @author Vanamo Piirainen
 */
public class WordFinder {

    private char[][] boardOfWords;
    private SearchTree wordTree;
    
    public WordFinder(UserInterface krypto) {
        this.boardOfWords = krypto.getBoardOfWords();
        this.wordTree = new SearchTree();
        this.wordTree.addListOfWords(krypto.getWordList());
    }
    
    public void findWord (Word newWord) {
        
    }
    
}
