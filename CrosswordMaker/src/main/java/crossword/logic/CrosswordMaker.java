package crossword.logic;

import crossword.datastructures.CustomArrayList;
import crossword.lexicon.Lexicon;
import java.util.Random;
import org.jdom2.JDOMException;

/**
 *
 * @author Vanamo Piirainen
 */
public class CrosswordMaker {

    private BoardOfWords boardOfWords;
    private CustomArrayList<String> wordList;
    private String firstWord;
    private CustomArrayList<CustomArrayList<String>> wordsByLength;
    private final int maxWordLength = 50;

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

        this.makeWordListsAccordingToLength();

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
            CustomArrayList<String> wordList) {
        this.wordList = wordList;
        this.makeWordListsAccordingToLength();
        this.boardOfWords = new BoardOfWords(width, hight);
        this.boardOfWords.createBoard(firstWord);
    }

    /**
     *
     * @return
     */
    public BoardOfWords fillBoard() {

        WordFinder wordFinder
                = new WordFinder(this.boardOfWords, this.wordsByLength);
        WordPositionFinder positionFinder = new WordPositionFinder(this.boardOfWords.getBoard());
        CustomArrayList positions = positionFinder.findPositions();
        System.out.println("Kryptoon tulee " + positions.size() + " sanaa");

        //Remove firstword position
        positions.remove(new WordPosition(0, 0, Alignment.HORIZONTAL, this.firstWord.length()));
        
        //Draw first word after the positions are found, otherwise the letters 
        //of the first word will interfere finding of word positions.
        this.boardOfWords.drawFirstWord(firstWord);
        
        System.out.println("\nKryptolauta alussa: \n" + this.boardOfWords);
        
        long startTime = System.currentTimeMillis();
        BoardOfWords result = wordFinder.findWordsForAllPositions(positions);
        long endTime = System.currentTimeMillis();
        System.out.println("\nSanojen etsimiseen kului aikaa "
                + (endTime - startTime) + "ms \n");
        return result;
    }

    public String getRandomWord(int length) {
        while (this.wordsByLength.get(length).isEmpty()) {
            length--;
            if (length == 0) {
                return "a";
            }
        }
        CustomArrayList<String> words = this.wordsByLength.get(length);
        Random rand = new Random();
        int i = rand.nextInt(words.size());
        String word = words.get(i);

        return word;
    }

    /**
     * Assuming there are no words longer than maxWordLength.
     */
    public void makeWordListsAccordingToLength() {
        this.wordsByLength
                = new CustomArrayList<CustomArrayList<String>>(this.maxWordLength);
        for (int i = 1; i < this.maxWordLength; i++) {
            wordsByLength.add(i, new CustomArrayList<String>());
        }
        for (int i = 0; i < wordList.size(); i++) {
            String word = wordList.get(i);
            wordsByLength.get(word.length()).add(word);
        }
    }

    public BoardOfWords getBoardOfWords() {
        return this.boardOfWords;
    }

    public CustomArrayList<String> getWordList() {
        return this.wordList;
    }

    public CustomArrayList<CustomArrayList<String>> getWordsByLength() {
        return wordsByLength;
    }

}
