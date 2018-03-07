package crossword.logic;

import crossword.datastructures.CustomArrayList;
import crossword.lexicon.Lexicon;
import org.jdom2.JDOMException;

/**
 * Calls for all the necessary methods to construct the crossword puzzle. Also
 * takes care of converting the letters to numbers in the resulting crossword
 * puzzle and finding a random starting word from the lexicon if it is not
 * provided by the user.
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
     * Constructor using the lexicon imported from an xml file.
     *
     * @param width width of the crossword board
     * @param height height of the crossword board
     * @param firstWord user defined starting word, "r" if not provided
     */
    public CrosswordMaker(int width, int height, String firstWord) {
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
        this.boardOfWords = new BoardOfWords(width, height);
        this.boardOfWords.createBoard(firstWord);

    }

    /**
     * Constructor for testing, lexicon is given as a list of words.
     *
     * @param width width of the crossword board
     * @param height height of the crossword board
     * @param firstWord user defined starting word
     * @param wordList CustomArrayList with the words as Strings
     */
    public CrosswordMaker(int width, int height, String firstWord,
            CustomArrayList<String> wordList) {
        this.wordList = wordList;
        this.firstWord = firstWord;
        this.makeWordListsAccordingToLength();
        this.boardOfWords = new BoardOfWords(width, height);
        this.boardOfWords.createBoard(firstWord);
    }

    /**
     * @return BoardOfWords with the resulting crossword puzzle filled with
     * letters
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
        this.boardOfWords = wordFinder.findWordsForAllPositions(positions);
        long endTime = System.currentTimeMillis();
        System.out.println("\nSanojen etsimiseen kului aikaa "
                + (endTime - startTime) + "ms \n");
        return this.boardOfWords;
    }

    /**
     * Converts the letters on the board to numbers.
     *
     * @return String presentation of the crossword puzzle filled with numbers
     */
    public Integer[][] lettersToNumbers() {
        CustomArrayList<Character> letters = new CustomArrayList<>();
        this.getUniqueLettersFromBoard(letters);
        int[] lettersToNumbers = new int[letters.size()];
        this.getNumbersForLetters(lettersToNumbers);

        int height = this.boardOfWords.getHeight();
        int width = this.boardOfWords.getWidth();
        Integer[][] boardWithNumbers = new Integer[height][width];
        this.fillBoardWithNumbers(letters, lettersToNumbers, boardWithNumbers);

        return boardWithNumbers;
    }

    /**
     * Helper method for lettersToNumbers. Finds all the unique letters from the
     * board of words and stores them in a CustomArrayList.
     *
     * @param letters empty CustomArrayList for storing the letters
     */
    private void getUniqueLettersFromBoard(CustomArrayList<Character> letters) {
        for (int y = 0; y < this.boardOfWords.getHeight(); y++) {
            for (int x = 0; x < this.boardOfWords.getWidth(); x++) {
                Character letter = this.boardOfWords.getLetter(x, y);
                if (!letters.contains(letter) && letter != 'X') {
                    letters.add(letter);
                }
            }
        }
    }

    /**
     * Helper method for lettersToNumbers. Converts letters to numbers.
     *
     * @param lettersToNumbers empty array for storing a number for each letter
     */
    private void getNumbersForLetters(int[] lettersToNumbers) {
        int nextInt = 1;
        for (int i = 0; i < lettersToNumbers.length; i++) {
            lettersToNumbers[i] = nextInt;
            nextInt++;
        }

    }

    /**
     * Helper method for lettersToNumbers. Creates a 2D array with all the
     * letters of the resulting crossword puzzle presented as numbers.
     *
     * @param letters CustomArrayList storing the unique letters
     * @param lettersToNumbers array storing the numbers which the letters are
     * converted to
     * @param boardWithNumbers letters of the crossword puzzle presented as
     * numbers
     */
    private void fillBoardWithNumbers(CustomArrayList<Character> letters,
            int[] lettersToNumbers, Integer[][] boardWithNumbers) {
        for (int y = 0; y < this.boardOfWords.getHeight(); y++) {
            for (int x = 0; x < this.boardOfWords.getWidth(); x++) {
                Character letter = this.boardOfWords.getLetter(x, y);
                if (letter != 'X') {
                    Integer letterIndex = letters.indexOf(letter);
                    boardWithNumbers[y][x] = lettersToNumbers[letterIndex];
                }
            }
        }
    }

    /**
     * Helper method for lettersToNumbers. Creates a string presentation of the
     * crossword puzzle with numbers.
     *
     * @param boardWithNumbers array with the crossword puzzle as numbers
     * @return String presentation of the crossword puzzle with numbers
     */
    public String boardWithNumbersToString(Integer[][] boardWithNumbers) {
        String board = "";
        for (int y = 0; y < boardWithNumbers.length; y++) {
            for (int x = 0; x < boardWithNumbers[0].length; x++) {
                if (boardWithNumbers[y][x] == null) {
                    board = board.concat("X\t");
                } else {
                    board = board.concat(boardWithNumbers[y][x].toString() + "\t");
                }
            }
            board = board.concat("\n\n");
        }
        board = board.concat("\n");
        return board;
    }

    /**
     * Helper method for lettersToNumbers. Creates a html presentation of the
     * crossword puzzle with numbers.
     *
     * @param boardWithNumbers array with the crossword puzzle as numbers
     * @return html presentation of the crossword puzzle with numbers
     */
    public String boardWithNumbersToHtmlString(Integer[][] boardWithNumbers) {
        String board = "<h1>Krypto</h1>\n"
                + "<table style=\"border: 1px solid black; border-collapse: collapse; font-size:200%\">\n";
        for (int y = 0; y < boardWithNumbers.length; y++) {
            board.concat("<tr style=\"border: 1px solid black\">\n");
            for (int x = 0; x < boardWithNumbers[0].length; x++) {
                if (boardWithNumbers[y][x] == null) {
                    board = board.concat("<td style=\"border: 1px solid black; "
                            + "width: 40px; height: 40px; padding: 10px; text-align: center\">");
                    board = board.concat("<i class=\"fa fa-heart\"></i>");
                } else {
                    board = board.concat("<td style=\"border: 1px solid black; "
                            + "width: 40px; height: 40px; padding: 10px; text-align: left;"
                            + "vertical-align: top; font-size: 50%\">");
                    board = board.concat(boardWithNumbers[y][x].toString());
                }
                board = board.concat("</td>\n");
            }
            board = board.concat("</tr>");
        }
        board = board.concat("</table>");
        return board;
    }

    /**
     * Finds a random word from the lexicon of predefined length. If words of
     * such length are not found, tries to find a shorter word. If no words are
     * found, returns "a".
     *
     * @param length length of the word in demand
     * @return random word from the lexicon as String
     */
    public String getRandomWord(int length) {
        while (this.wordsByLength.get(length).isEmpty()) {
            length--;
            if (length == 0) {
                return "a";
            }
        }
        CustomArrayList<String> words = this.wordsByLength.get(length);
        long time = System.nanoTime();
        int rand = (int) (time % words.size());
        String word = words.get(rand);

        return word;
    }

    /**
     * Creates a CustomArrayList with word lists containing all the words of
     * specific length. The index of the CustomArrayList defines the length of
     * the words in the word list at that index. Assumes there are no words
     * longer than maxWordLength.
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

    /**
     *
     * @return BoardOfWords containing the crossword puzzle at its current state
     */
    public BoardOfWords getBoardOfWords() {
        return this.boardOfWords;
    }

    /**
     *
     * @return the lexicon used for creating the crossword puzzle
     */
    public CustomArrayList<String> getWordList() {
        return this.wordList;
    }

    /**
     *
     * @return CustomArrayList containing all the words in the lexicon sorted by
     * length
     */
    public CustomArrayList<CustomArrayList<String>> getWordsByLength() {
        return wordsByLength;
    }

}
