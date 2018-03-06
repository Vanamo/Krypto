package crossword.logic;

import crossword.datastructures.CustomArrayList;
import java.util.Random;

/**
 * The crossword board where the words are drawn
 *
 * @author Vanamo Piirainen
 */
public class BoardOfWords {

    private char[][] boardOfWords;
    private int width;
    private int hight;
    private WordPosition firstPosition;

    /**
     * @param width width of the board
     * @param hight hight of the board
     */
    public BoardOfWords(int width, int hight) {
        this.width = width;
        this.hight = hight;
    }

    /**
     * Constructs the board with the first word
     *
     * @param firstWord the first word on the board
     */
    public void createBoard(String firstWord) {
        //alignment of the first word is horizontal
        this.firstPosition = new WordPosition(0, 0, Alignment.HORIZONTAL,
                firstWord.length());
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

    /**
     * There is only one word per one row / column.
     */
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
     * There are several words per one row / column.
     */
    public void letterPositionsForLargeBoard() {
        CustomArrayList<char[]> charsForBoard = new CustomArrayList<>();
        charsForBoard.add("OOOOOXOXOOOOOOX".toCharArray());
        charsForBoard.add("OXOXOOOOOOXOXOO".toCharArray());
        charsForBoard.add("OOOOOXOXOOOOOOX".toCharArray());
        charsForBoard.add("OXOXOOOOOOXOXOO".toCharArray());
        charsForBoard.add("OOOOOXOXOOOOOOX".toCharArray());
        charsForBoard.add("XOXOXOOOOXOXOOO".toCharArray());
        charsForBoard.add("OOOOOOXOXXOOOXO".toCharArray());
        charsForBoard.add("XOXOXOOOOXOXOOO".toCharArray());
        charsForBoard.add("OOOOOOXOOOOOOXO".toCharArray());
        charsForBoard.add("XOXOXOOOOXOXOOO".toCharArray());
        charsForBoard.add("OOOOOXOXOOOOOOX".toCharArray());
        charsForBoard.add("OXOXOOOOOOXOXOO".toCharArray());
        charsForBoard.add("OOOOOXOXXOOOOOX".toCharArray());
        charsForBoard.add("OXOXOOOOOOXOXOO".toCharArray());

        for (int y = 0; y < this.hight; y++) {
            for (int x = 0; x < this.width; x++) {
                //First row with the first word
                int firstWordLength = this.firstPosition.getWordLength();
                if (y == 0 && x <= firstWordLength) {
                    if (x < firstWordLength) {
                        this.boardOfWords[y][x] = 'O';
                    } else if (x == firstWordLength) {
                        this.boardOfWords[y][x] = 'X';
                    }
                } else {
                    int x2 = this.checkLength(x, charsForBoard.get(0).length);
                    int y2 = this.checkLength(y, charsForBoard.size());
                    this.boardOfWords[y][x] = charsForBoard.get(y2)[x2];
                    //Check that there will be no lonely letter on the upper right corner
                    if (firstWordLength == this.width - 2) {
                        if (y == 0 && x == this.width - 1) {
                            this.boardOfWords[y][x] = 'X';
                        }
                    }
                }
            }
        }
        //With many words with only two letters finding the solution (in Finnish language)
        //is very difficult, so most of the two-letter words are removed.
        removeTwoLetterWordsFromEdges();
    }

    /**
     * Helper method for the method letterPositionsForLargeBoard. If there are
     * no more characters on charsForBoard, start from the beginning.
     *
     * @param i index of the character position (horizontal or vertical)
     * @param length index of the last character position
     * @return index of charsForBoard from which to take the next character
     */
    private int checkLength(int i, int length) {
        if (i >= length) {
            i -= length;
        }
        return i;
    }

    /**
     * Words with only two letters are so rare in Finnish language that finding
     * solution is very difficult if there are many two-letter words on the
     * board. This is a clumsy solution for removing them from the edges.
     */
    private void removeTwoLetterWordsFromEdges() {
        //Remove from the first row
        int y = 0;
        for (int x = 0; x < this.width - 1; x++) {
            char char0 = this.boardOfWords[y][x];
            char char1 = this.boardOfWords[y + 1][x];
            char char2 = this.boardOfWords[y + 2][x];
            if (char0 == 'O' && char1 == 'O' && char2 == 'X') {
                this.boardOfWords[y + 1][x] = 'X';
            }
        }
        //Remove from the last row
        y = this.hight - 1;
        for (int x = 0; x < this.width - 1; x++) {
            char char0 = this.boardOfWords[y][x];
            char char1 = this.boardOfWords[y - 1][x];
            char char2 = this.boardOfWords[y - 2][x];
            if (char0 == 'O' && char1 == 'O' && char2 == 'X') {
                this.boardOfWords[y][x] = 'X';
            }
        }
        //Remove from the last column
        int x = this.width - 1;
        for (y = 0; y < this.hight - 1; y++) {
            char char0 = this.boardOfWords[y][x];
            char char1 = this.boardOfWords[y][x - 1];
            char char2 = this.boardOfWords[y][x - 2];
            if (char0 == 'O' && char1 == 'O' && char2 == 'X') {
                this.boardOfWords[y][x] = 'X';
            }
        }
    }

    /**
     * Draws the given word on the board. Alignment: 0 = horizontal 1 =
     * vertical.
     *
     * @param word word to be drawn
     * @param p position to which to draw the word
     */
    public void drawWord(String word, WordPosition p) {
        for (int i = 0; i < word.length(); i++) {
            if (p.getAlignment() == Alignment.HORIZONTAL) {
                this.boardOfWords[p.getY()][i + p.getX()] = word.charAt(i);
            } else {
                this.boardOfWords[i + p.getY()][p.getX()] = word.charAt(i);
            }
        }
    }

    /**
     * Draws the starting word on the board. It is always drawn on the upper
     * left corner of the board.
     *
     * @param word first word on the board
     */
    public void drawFirstWord(String word) {
        WordPosition p = this.firstPosition;
        for (int i = 0; i < word.length(); i++) {
            if (p.getAlignment() == Alignment.HORIZONTAL) {
                this.boardOfWords[p.getY()][i + p.getX()] = word.charAt(i);
            } else {
                this.boardOfWords[i + p.getY()][p.getX()] = word.charAt(i);
            }
        }
    }

    /**
     * @param x horizontal index on the board
     * @param y vertical position on the board
     * @return character on the board at the given position
     */
    public char getLetter(int x, int y) {
        return this.boardOfWords[y][x];
    }

    /**
     *
     * @param x horizontal index on the board
     * @param y vertical position on the board
     * @param c character to be set on the board
     */
    public void setLetter(int x, int y, char c) {
        this.boardOfWords[y][x] = c;
    }

    /**
     *
     * @return 2D array containing the characters on the board
     */
    public char[][] getBoard() {
        return boardOfWords;
    }

    /**
     *
     * @return width of the board
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return hight of the board
     */
    public int getHight() {
        return hight;
    }

    /**
     *
     * @param boardOfWords 2D array containing the characters of the board
     */
    private void setBoardOfWords(char[][] boardOfWords) {
        this.boardOfWords = boardOfWords;
    }

    /**
     * Creates a shallow copy of this BoardOfWords
     *
     * @return copy of this BoardOfWords
     */
    public BoardOfWords makeCopy() {
        BoardOfWords copy = new BoardOfWords(this.width, this.hight);
        char[][] copyOfBoard = new char[this.hight][this.width];
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

    public String toHTML() {
        String board = "<h1>Krypton ratkaisu</h1>\n"
                + "<table style=\"border: 1px solid black; border-collapse: collapse; font-size:200%\">\n";
        for (int y = 0; y < this.hight; y++) {
            board.concat("<tr style=\"border: 1px solid black\">\n");
            for (int x = 0; x < this.width; x++) {
                board = board.concat("<td style=\"border: 1px solid black; "
                        + "width: 40px; padding: 10px; text-align: center\">");
                Character c = this.boardOfWords[y][x];
                if (c == 'X') {
                    board = board.concat("<i class=\"fa fa-heart\"></i>");
                } else {
                    board = board.concat(c.toString().toUpperCase());
                }
                board = board.concat("</td>\n");
            }
            board = board.concat("</tr>");
        }
        board = board.concat("</table>");
        return board;
    }
}
