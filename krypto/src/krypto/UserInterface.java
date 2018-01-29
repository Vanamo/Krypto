package krypto;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Vanamo Piirainen
 */
public class UserInterface {

    private char[][] boardOfWords;
    private int width;
    private int hight;
    private String firstWord;
    private ArrayList<String> wordList;

    /**
     * Constructor with default parameters and random starting word.
     */
    public UserInterface(ArrayList<String> wordList) {
        this.wordList = wordList;
        this.width = 5;
        this.hight = 5;
        this.firstWord = getRandomWord();
        this.boardOfWords = new char[5][5];
        createBoard();
    }

    /**
     * Constructor with user set parameters and starting word.
     */
    public UserInterface(ArrayList<String> wordList,
            int width, int hight, String firstWord) {
        this.wordList = wordList;
        this.width = width;
        this.hight = hight;
        this.firstWord = firstWord;
        boardOfWords = new char[this.width][this.hight];
        createBoard();
    }

    private String getRandomWord() {
        Random rand = new Random();
        int i = rand.nextInt(this.wordList.size());
        return this.wordList.get(i);
    }

    /**
     * Creates an empty board (x stands for an empty square) and adds the
     * starting word in the upper left corner of the board.
     */
    private void createBoard() {
        for (int y = 0; y < this.hight; y++) {
            for (int x = 0; x < this.width; x++) {
                this.boardOfWords[y][x] = 'X';
            }
        }
        int x = 0;
        int y = 0;
        int direction = 0;
        drawWord(this.firstWord, x, y, direction);
    }

    public void printBoard() {
        for (int y = 0; y < this.hight; y++) {
            for (int x = 0; x < this.width; x++) {
                System.out.print(this.boardOfWords[y][x] + "\t");
            }
            System.out.println("\n");
        }
    }

    /**
     * Draws the given word on the board. Alignment: 0 = horizontal 1 =
     * vertical.
     *
     * @param word
     * @param x
     * @param y
     * @param alignment
     */
    public void drawWord(String word, int x, int y, int alignment) {
        for (int i = 0; i < word.length(); i++) {
            if (alignment == 0) {
                this.boardOfWords[y][i + x] = word.charAt(i);
            } else {
                this.boardOfWords[i + y][x] = word.charAt(i);                
            }
        }
    }

    public char[][] getBoardOfWords() {
        return boardOfWords;
    }
    
    public ArrayList<String> getWordList() {
        return this.wordList;
    }
    
}
