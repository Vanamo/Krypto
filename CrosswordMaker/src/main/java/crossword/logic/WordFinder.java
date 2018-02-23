package crossword.logic;

import crossword.datastructures.CustomArrayList;
import crossword.datastructures.SearchTree;
import crossword.datastructures.SearchTreeNode;

/**
 *
 * @author Vanamo Piirainen
 */
public class WordFinder {

    private CustomArrayList<SearchTree> wordTreesByWordLength;
    private BoardOfWords boardOfWords;
    private CustomArrayList<WordPosition> positions;

    public WordFinder(BoardOfWords boardOfWords,
            CustomArrayList<CustomArrayList<String>> wordsByLength, int maxWordLength) {
        this.boardOfWords = boardOfWords;
        this.wordTreesByWordLength = new CustomArrayList<>(maxWordLength);
        for (int i = 1; i < maxWordLength; i++) {
            SearchTree searchTree = new SearchTree();
            searchTree.addListOfWords(wordsByLength.get(i));
            this.wordTreesByWordLength.add(i, searchTree);
        }
    }

    /**
     * Finds words for all positions given as an ArrayList.
     *
     * @param positions
     * @return
     */
    public BoardOfWords findWordsForAllPositions(CustomArrayList<WordPosition> positions) {
        CustomArrayList<String> usedWords = new CustomArrayList<>();
        this.positions = positions;
        return this.layWords(0, this.boardOfWords, usedWords);
    }

    /**
     * Uses recursion to find a possible word combination to fit the given
     * positions on the board.
     *
     * @param positions
     * @param positionIndex
     */
    private BoardOfWords layWords(int positionIndex, BoardOfWords board,
            CustomArrayList<String> usedWords) {
        if (positionIndex == positions.size()) {
            return board;
        }
        WordPosition position = this.positions.get(positionIndex);
        this.boardOfWords = board.makeCopy();

        CustomArrayList<String> fittingWords = this.findWords(position);

        for (int i = 0; i < usedWords.size(); i++) {
            fittingWords.remove(usedWords.get(i));
        }

        if (fittingWords.isEmpty()) {
            return null;
        }

        for (int i = 0; i < fittingWords.size(); i++) {
            BoardOfWords copyOfBoard = board.makeCopy();
            WordPosition p = this.positions.get(positionIndex);
            copyOfBoard.drawWord(fittingWords.get(i), p);
            CustomArrayList<String> copyOfUsedWords = new CustomArrayList<>(usedWords);
            copyOfUsedWords.add(fittingWords.get(i));
            BoardOfWords solution = this.layWords(positionIndex + 1, copyOfBoard,
                    copyOfUsedWords);
            if (solution != null) {
                return solution;
            }
        }
        return null;
    }

    /**
     * Find all words that fit to the given position.
     *
     * @param newPosition
     * @return
     */
    public CustomArrayList<String> findWords(WordPosition newPosition) {
        String mask = makeMask(newPosition);
        SearchTree wordsOfMaskLength = this.wordTreesByWordLength.get(mask.length() - 1);
        SearchTreeNode root = wordsOfMaskLength.getRoot();
        CustomArrayList<String> fittingWords = new CustomArrayList<>();
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
    private CustomArrayList<String> searchWordTree(String mask, SearchTreeNode node, 
            int level, CustomArrayList<String> fittingWords) {
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
        if (newChar == 'O') {
            mask = mask.concat(" ");
        } else {
            mask = mask.concat(String.valueOf(newChar));
        }
        return mask;
    }
}
