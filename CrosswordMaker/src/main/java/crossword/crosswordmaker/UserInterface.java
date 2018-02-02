package crossword.crosswordmaker;

import crossword.logic.BoardOfWords;
import crossword.logic.WordFinder;
import crossword.logic.WordPosition;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Vanamo Piirainen
 */
public class UserInterface {

    private BoardOfWords boardOfWords;
    private ArrayList<String> wordList;

    /**
     * Constructor with default parameters and random starting word.
     * @param wordList
     */
    public UserInterface(ArrayList<String> wordList) {
        this.wordList = wordList;
        this.boardOfWords = new BoardOfWords(5, 5);
        String firstWord = getRandomWord();
        WordPosition firstPosition = new WordPosition(0, 0, 0, firstWord.length());
        this.boardOfWords.drawWord(firstWord, firstPosition);
    }

    /**
     * Constructor with user set parameters and starting word.
     * @param wordList
     * @param width
     * @param hight
     * @param firstWord
     */
    public UserInterface(ArrayList<String> wordList,
            int width, int hight, String firstWord) {
        this.wordList = wordList;
        this.boardOfWords = new BoardOfWords(width, hight);
        WordPosition firstPosition = new WordPosition(0, 0, 0, firstWord.length());
        this.boardOfWords.drawWord(firstWord, firstPosition);
    }

    private String getRandomWord() {
        Random rand = new Random();
        int i = rand.nextInt(this.wordList.size());
        return this.wordList.get(i);
    }

    public void findWords() {
        WordFinder wordFinder = new WordFinder(this);
        //Jatka
    }

    public BoardOfWords getBoardOfWords() {
        return this.boardOfWords;
    }

    public ArrayList<String> getWordList() {
        return this.wordList;
    }

}
