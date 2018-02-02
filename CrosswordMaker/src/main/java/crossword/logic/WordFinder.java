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

    public WordFinder(CrosswordMaker cm) {
        this.boardOfWords = cm.getBoardOfWords();
        this.wordTree = new SearchTree();
        this.wordTree.addListOfWords(cm.getWordList());
    }

    /**
     * Finds words for all positions given as an ArrayList.
     * @param positions
     * @return 
     */
    public BoardOfWords findWordsForAllPositions(ArrayList<WordPosition> positions) {
        return this.layWords(positions, 0);
    }

    /**
     * Uses recursion to find a possible word combination to fit the given 
     * positions on the board.
     * 
     * @param positions
     * @param positionIndex 
     */
    private BoardOfWords layWords(ArrayList<WordPosition> positions, int positionIndex) {
        if (positionIndex == positions.size()) {
            return this.boardOfWords;
        }
        WordPosition position = positions.get(positionIndex);
        ArrayList<String> fittingWords = this.findWords(position);

        if (fittingWords.isEmpty()) {
            return null;
        }

        for (String fittingWord : fittingWords) {
            BoardOfWords copy = this.boardOfWords.makeCopy();
            copy.drawWord(fittingWord, positions.get(positionIndex));
            BoardOfWords solution = this.layWords(positions, positionIndex + 1);
            if (solution != null) return solution;
        }
        return null;
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
        ArrayList<String> fittingWords = new ArrayList<>();
        return this.searchWordTree(mask, root, 0, fittingWords);
    }

    /**
     * A helper merhod for the findWords method using recursion.
     *
     * @param mask
     * @param node
     * @param level
     * @param fittingWords
     * @return
     */
    private ArrayList<String> searchWordTree(String mask, SearchTreeNode node, int level,
            ArrayList<String> fittingWords) {
        if (mask.charAt(level) == ' ' || mask.charAt(level) == node.getKey()) {
            if (level + 1 == mask.length()) {
                if (node.getWord() != null) {
                    fittingWords.add(node.getWord());
                }
                return fittingWords;
            }
            SearchTreeNode childNode = node.getChild();
            while (childNode != null) {
                fittingWords = this.searchWordTree(mask, childNode, level + 1, fittingWords);
                childNode = childNode.getNext();
            }
        }
        return fittingWords;
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
                mask = this.createString(mask, newPosition.getY(), i + newPosition.getX());
            } else {
                mask = this.createString(mask, i + newPosition.getY(), newPosition.getX());
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
