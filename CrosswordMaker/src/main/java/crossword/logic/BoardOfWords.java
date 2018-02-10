package crossword.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Vanamo Piirainen
 */
public class BoardOfWords {

    private char[][] boardOfWords;
    private int width;
    private int hight;
    private WordPosition firstPosition;

    /**
     * @param width
     * @param hight
     */
    public BoardOfWords(int width, int hight) {
        this.width = width;
        this.hight = hight;
    }

    public void createBoard(String firstWord) {
        //alignment of the first word is horizontal
        this.firstPosition = new WordPosition(0, 0, 0, firstWord.length());
        //Create board first
        this.boardOfWords = new char[this.hight][this.width];
        this.createBoard();
    }

    /**
     * Creates a board for the crossword. 'O's stand for letter positions and
     * 'X's for empty squares.
     */
    private void createBoard() {
        for (int y = 0; y < this.hight; y++) {
            for (int x = 0; x < this.width; x++) {
                this.boardOfWords[y][x] = 'X';
            }
        }

        if (this.hight < 7 && this.width < 7) {
            letterPositionsForSmallBoard();
        }
    }

    public void printBoard() {
        for (int y = 0; y < this.hight; y++) {
            for (int x = 0; x < this.width; x++) {
                System.out.print(this.boardOfWords[y][x] + "\t");
            }
            System.out.println("\n");
        }
        System.out.println("\n");
    }

    private void letterPositionsForSmallBoard() {
        //First row with the first word
        for (int x = 0; x < this.firstPosition.getWordLength(); x++) {
            this.boardOfWords[0][x] = 'O';
        }

        for (int y = 1; y < this.hight; y++) {
            if (y % 2 == 1) {
                for (int x = 0; x < this.width; x += 2) {
                    this.boardOfWords[y][x] = 'O';
                }
            } else {
                for (int x = 0; x < this.width; x++) {
                    this.boardOfWords[y][x] = 'O';
                }
            }
        }
    }

    /**
     * Draws the given word on the board. Alignment: 0 = horizontal 1 =
     * vertical.
     *
     * @param word
     * @param p
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
    
    public void drawFirstWord(String word) {
        WordPosition p = this.firstPosition;
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

    public char[][] getBoard() {
        return boardOfWords;
    }

    public int getWidth() {
        return width;
    }

    public int getHight() {
        return hight;
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
