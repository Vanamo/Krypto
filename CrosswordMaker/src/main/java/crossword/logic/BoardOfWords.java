package crossword.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @Vanamo Piirainen
 */
public class BoardOfWords {

    private char[][] boardOfWords;
    private int width;
    private int hight;

    /**
     * Constructor with default parameters and random starting word.
     */
    public BoardOfWords() {
        this.width = 5;
        this.hight = 5;
        this.boardOfWords = new char[5][5];
        createBoard();
    }

    /**
     * Constructor with user set parameters and starting word.
     */
    public BoardOfWords(int width, int hight) {
        this.width = width;
        this.hight = hight;
        boardOfWords = new char[this.hight][this.width];
        createBoard();
    }

    /**
     * Creates an empty board (x stands for an empty square).
     */
    private void createBoard() {
        for (int y = 0; y < this.hight; y++) {
            for (int x = 0; x < this.width; x++) {
                this.boardOfWords[y][x] = 'X';
            }
        }
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
    public void drawWord(String word, WordPosition p) {
        for (int i = 0; i < word.length(); i++) {
            if (p.getAlignment() == 0) {
                this.boardOfWords[p.getY()][i + p.getX()] = word.charAt(i);
            } else {
                this.boardOfWords[i + p.getY()][p.getX()] = word.charAt(i);
            }
        }
    }

    public char getLetter(int x, int y) {
        return this.boardOfWords[y][x];
    }
    
    public char[][] getBoardOfWords() {
        return boardOfWords;
    }
    
    private void setBoard(char[][] boardOfWords) {
        this.boardOfWords = boardOfWords;
    }
    
    public BoardOfWords makeCopy() {
        BoardOfWords newBoardOfWords = new BoardOfWords(this.width, this.hight);
        newBoardOfWords.setBoard(boardOfWords.clone());
        return newBoardOfWords;
    }

}
