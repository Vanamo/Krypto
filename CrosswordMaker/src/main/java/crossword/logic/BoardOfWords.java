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

        if (this.hight < 10 && this.width < 10) {
            this.letterPositionsForSmallBoard();
        } else {
            this.letterPositionsForLargeBoard();
        }
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

    private void letterPositionsForLargeBoard() {
        ArrayList<char[]> charsForBoard = new ArrayList<>();
        charsForBoard.add("OOOOOXOXOOOOOOX".toCharArray());
        charsForBoard.add("OXOXOOOOOOXOXOO".toCharArray());
        charsForBoard.add("OOOOOXOXOOOOOOX".toCharArray());
        charsForBoard.add("OXOXOOOOOOXOXOO".toCharArray());
        charsForBoard.add("OOOOOXOXOOOOOOX".toCharArray());
        charsForBoard.add("XOXOXOOOOXOXOOO".toCharArray());
        charsForBoard.add("OOOOOOXOOOOOOXO".toCharArray());
        charsForBoard.add("XOXOXOOOXXOXOOO".toCharArray());
        charsForBoard.add("OOOOOXOOOOOOOXO".toCharArray());
        charsForBoard.add("XOXOXOOOOXOXOOO".toCharArray());
        charsForBoard.add("OOOOOXOXOOOOXOX".toCharArray());
        charsForBoard.add("OXOXOOOOOOXOXOO".toCharArray());
        charsForBoard.add("OOOOOXOXOOOOOOX".toCharArray());
        charsForBoard.add("OXOXOOOOXOXOXOO".toCharArray());

        for (int y = 0; y < this.hight; y++) {
            for (int x = 0; x < this.width; x++) {
                //First row with the first word
                if (y == 0) {
                    if (x < this.firstPosition.getWordLength()) {
                        this.boardOfWords[y][x] = 'O';
                    } else if (x == this.firstPosition.getWordLength()) {
                        this.boardOfWords[y][x] = 'X';
                    }
                }
                int x2 = this.checkLength(x, charsForBoard.get(0).length);
                int y2 = this.checkLength(y, charsForBoard.size());
                this.boardOfWords[y][x] = charsForBoard.get(y2)[x2];
            }
        }
    }

    /**
     * Helper method for the method letterPositionsForLargeBoard
     *
     * @param i
     * @param length
     * @return
     */
    private int checkLength(int i, int length) {
        if (i >= length) {
            i -= length;
        }
        return i;
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

    private void setBoardOfWords(char[][] boardOfWords) {
        this.boardOfWords = boardOfWords;
    }
    
    public BoardOfWords makeCopy() {
        BoardOfWords copy = new BoardOfWords(this.width, this.hight);
        char[][] copyOfBoard = new char[this.width][this.hight];
        for (int y = 0; y < this.hight; y++) {
            for (int x = 0; x < this.width; x++) {
                copyOfBoard[y][x] = this.boardOfWords[y][x];
            }
        }
        copy.setBoardOfWords(copyOfBoard);
        return copy;
    }

    @Override
    public String toString() {
        String board = "";
        for (int y = 0; y < this.hight; y++) {
            for (int x = 0; x < this.width; x++) {
                board = board.concat(this.boardOfWords[y][x] + "\t");
            }
            board = board.concat("\n\n");
        }
        board = board.concat("\n");
        return board;
    }
}
