package crossword.logic;

import crossword.lexicon.Lexicon;
import java.util.ArrayList;
import java.util.Random;
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
        this.createBoard(width, hight, firstWord);

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
        this.createBoard(width, hight, firstWord);
        this.wordList = wordList;
    }

    /**
     * 
     * @return
     */
    public BoardOfWords fillBoard() {
        WordPosition p1 = new WordPosition(0, 0, 1, 5);
        WordPosition p2 = new WordPosition(2, 0, 1, 5);
        WordPosition p3 = new WordPosition(4, 0, 1, 5);
        WordPosition p4 = new WordPosition(0, 2, 0, 5);
        WordPosition p5 = new WordPosition(0, 4, 0, 5);     
        ArrayList<WordPosition> positions = new ArrayList<>();
        positions.add(p1);
        positions.add(p2);
        positions.add(p3);
        positions.add(p4);
        positions.add(p5);
        
        WordFinder wordFinder = new WordFinder(this);
        return wordFinder.findWordsForAllPositions(positions);
    }

    private void createBoard(int width, int hight, String firstWord) {
        this.boardOfWords = new BoardOfWords(width, hight);
        if (firstWord.isEmpty()) {
            firstWord = getRandomWord(width);
        }
        WordPosition firstPosition = new WordPosition(0, 0, 0, firstWord.length());
        this.boardOfWords.drawWord(firstWord, firstPosition);
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

    public BoardOfWords getBoardOfWords() {
        return this.boardOfWords;
    }

    public ArrayList<String> getWordList() {
        return this.wordList;
    }
}
