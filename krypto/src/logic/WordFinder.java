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

    /**
     * Find all words that fit to the given position. 
     * 
     * @param newPosition
     * @return 
     */
    public ArrayList<String> findWords(WordPosition newPosition) {
        String mask = makeMask(newPosition);
        Node root = this.wordTree.getRoot();
        ArrayList<String> words = new ArrayList<>();
        return searchWordTree(mask, root, 0, words);
    }

    /**
     * A helper merhod for the findWords method using recursion.
     * 
     * @param mask
     * @param node
     * @param level
     * @param words
     * @return 
     */
    private ArrayList<String> searchWordTree(String mask, Node node, int level,
            ArrayList<String> words) {
        if (mask.charAt(level) == ' ' || mask.charAt(level) == node.getKey()) {
            if (level + 1 == mask.length()) {
                if (node.getWord() != null) {
                    words.add(node.getWord());
                }
                return words;
            }
            Node childNode = node.getChild();
            while (childNode != null) {
                words = searchWordTree(mask, childNode, level + 1, words);
                childNode = childNode.getNext();
            }
        }
        return words;
    }

    /**
     * Creates a String (= the mask) for the word to search with the letters
     * already positioned on the board and empty squares marked with space.
     *
     * @param newPosition
     * @return
     */
    private String makeMask(WordPosition newPosition) {
        String mask = " "; //To match the root of the wordTree 
        for (int i = 0; i < newPosition.getWordLength(); i++) {
            if (newPosition.getAlignment() == 0) {
                mask = createString(mask, newPosition.getY(), i + newPosition.getX());
            } else {
                mask = createString(mask, i + newPosition.getY(), newPosition.getX());
            }
        }
        return mask;
    }

    /**
     * A helper method for the makeMask method.
     *
     * @param mask
     * @param y
     * @param x
     * @return
     */
    private String createString(String mask, int y, int x) {
        char newChar = this.boardOfWords[y][x];
        if (newChar == 'X') {
            mask = mask.concat(" ");
        } else {
            mask = mask.concat(String.valueOf(newChar));
        }
        return mask;
    }

}
