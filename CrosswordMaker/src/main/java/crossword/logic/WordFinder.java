package crossword.logic;

import java.util.ArrayList;
import crossword.crosswordmaker.UserInterface;

/**
 *
 * @author Vanamo Piirainen
 */
public class WordFinder {

    private SearchTree wordTree;
    private BoardOfWords boardOfWords;

    public WordFinder(UserInterface ui) {
        this.boardOfWords = ui.getBoardOfWords();
        this.wordTree = new SearchTree();
        this.wordTree.addListOfWords(ui.getWordList());
    }

    /**
     * Finds words for all positions given as an ArrayList.
     */
    public void findWordsForAllPositions(ArrayList<WordPosition> positions) {
        for (WordPosition position : positions) {
            
        }
    }
    
    private void layWord(int lastPositionIndex, int positionIndex, 
            WordPosition position, ArrayList<String> words) {
        if (words.isEmpty()) return;
        if (positionIndex == lastPositionIndex) {
            this.boardOfWords.printBoard();
            return;
        }
        
        for (String word : words) {
            BoardOfWords boardOfWords2 = this.boardOfWords.makeCopy();
            boardOfWords2.drawWord(word, position);
        }
    } 
    
    /**
     * Find all words that fit to the given position. 
     * 
     * @param newPosition
     * @return 
     */
    public ArrayList<String> findWords(WordPosition newPosition) {
        String mask = makeMask(newPosition);
        SearchTreeNode root = this.wordTree.getRoot();
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
    private ArrayList<String> searchWordTree(String mask, SearchTreeNode node, int level,
            ArrayList<String> words) {
        if (mask.charAt(level) == ' ' || mask.charAt(level) == node.getKey()) {
            if (level + 1 == mask.length()) {
                if (node.getWord() != null) {
                    words.add(node.getWord());
                }
                return words;
            }
            SearchTreeNode childNode = node.getChild();
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
        char newChar = this.boardOfWords.getLetter(x, y);
        if (newChar == 'X') {
            mask = mask.concat(" ");
        } else {
            mask = mask.concat(String.valueOf(newChar));
        }
        return mask;
    }

}
