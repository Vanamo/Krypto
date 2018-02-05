package crossword.logic;

import crossword.lexicon.Lexicon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeSet;
import org.jdom2.JDOMException;

/**
 *
 * @author Vanamo Piirainen
 */
public class CrosswordMaker {

    private BoardOfWords boardOfWords;
    private ArrayList<String> wordList;

    /**
     *
     * @param width
     * @param hight
     * @param firstWord
     */
    public CrosswordMaker(int width, int hight, String firstWord) {
        Lexicon lexicon = new Lexicon();
        try {
            this.wordList = lexicon.getLexicon();
        } catch (JDOMException ex) {
            System.out.println("Sanalistan haku ei onnistunut");
            System.out.println(ex);
        }
//        for (String word : wordList) {
//            System.out.println(word);
//        }
        if (firstWord.isEmpty()) {
            firstWord = this.getRandomWord(width);
        }
        this.boardOfWords = new BoardOfWords(width, hight);
        this.boardOfWords.addFirstWord(firstWord);

    }

    /**
     * Contructor for testing.
     *
     * @param width
     * @param hight
     * @param firstWord
     * @param wordList
     */
    public CrosswordMaker(int width, int hight, String firstWord,
            ArrayList<String> wordList) {
        this.wordList = wordList;
        this.boardOfWords = new BoardOfWords(width, hight);
        this.boardOfWords.addFirstWord(firstWord);
    }

    /**
     *
     * @return
     */
    public BoardOfWords fillBoard() {
        WordFinder wordFinder = new WordFinder(this);
        return wordFinder.findWordsForAllPositions(this.boardOfWords.findPositions());
    }
    
    private String getRandomWord(int length) {
        Random rand = new Random();
        String word = "";
        while (word.length() != length) {
            int i = rand.nextInt(this.wordList.size());
            word = this.wordList.get(i);
        }
        return word;
    }
    
    public void makeWordLengthStatistics() {
        HashMap<Integer, Integer> statistics = new HashMap<>();
        for (String word : this.wordList) {
            if (!statistics.containsKey(word.length())) {
                statistics.put(word.length(), 1);
            } else {
                statistics.replace(word.length(), statistics.get(word.length()) + 1);
            }
        }
        System.out.println("statistics: \n" + statistics.toString());
    }

    public BoardOfWords getBoardOfWords() {
        return this.boardOfWords;
    }

    public ArrayList<String> getWordList() {
        return this.wordList;
    }
}
