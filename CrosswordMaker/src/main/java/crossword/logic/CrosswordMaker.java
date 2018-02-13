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
    private String firstWord;
    private HashMap<Integer, ArrayList<String>> wordsByLength;

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

        if (firstWord.equals("r")) {
            int length = Math.min(width, 5);
            firstWord = this.getRandomWord(length);
            System.out.println("Aloitussanaksi arvottiin " + firstWord);
        }
        this.firstWord = firstWord;
        this.boardOfWords = new BoardOfWords(width, hight);
        this.boardOfWords.createBoard(firstWord);

    }

    /**
     * Constructor for testing.
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
        this.boardOfWords.createBoard(firstWord);
    }

    /**
     *
     * @return
     */
    public BoardOfWords fillBoard() {
        WordFinder wordFinder = new WordFinder(this.boardOfWords, this.wordList);
        WordPositionFinder positionFinder = new WordPositionFinder(this.boardOfWords.getBoard());
        ArrayList<WordPosition> positions = positionFinder.findPositions();

        //Draw first word after the positions are found, otherwise the letters 
        //of the first word will interfere finding of word positions.
        this.boardOfWords.drawFirstWord(firstWord);
        return wordFinder.findWordsForAllPositions(positions);
    }

    public String getRandomWord(int length) {
        this.makeWordListsAccordingToLength();
        while (!this.wordsByLength.containsKey(length)) {
            length--;
            if (length == 0) {
                return "a";
            }
        }
        ArrayList<String> words = this.wordsByLength.get(length);
        Random rand = new Random();
        int i = rand.nextInt(words.size());
        String word = words.get(i);

        return word;
    }

    public void makeWordListsAccordingToLength() {
        this.wordsByLength = new HashMap<>();
        for (String word : this.wordList) {
            if (!wordsByLength.containsKey(word.length())) {
                ArrayList<String> words = new ArrayList<>();
                words.add(word);
                wordsByLength.put(word.length(), words);
            } else {
                wordsByLength.get(word.length()).add(word);
            }
        }
    }

    public BoardOfWords getBoardOfWords() {
        return this.boardOfWords;
    }

    public ArrayList<String> getWordList() {
        return this.wordList;
    }

    public HashMap<Integer, ArrayList<String>> getWordsByLength() {
        return wordsByLength;
    }

}
