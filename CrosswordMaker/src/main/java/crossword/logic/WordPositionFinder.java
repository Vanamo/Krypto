package crossword.logic;

import crossword.datastructures.CustomArrayList;

/**
 * For a board filled with 'X's (empty squares) and 'O's (to be letters),
 * finds positions where words will be laid.
 * 
 * @author Vanamo Piirainen
 */
public class WordPositionFinder {

    private char[][] boardOfWords;
    private int hight;
    private int width;
    private int wordLength;
    private int startX;
    private int startY;
    private CustomArrayList<WordPosition> positions;

    public WordPositionFinder(char[][] boardOfWords) {
        this.boardOfWords = boardOfWords;
        this.hight = boardOfWords.length;
        this.width = boardOfWords[0].length;
    }

    /**
     *
     * @return  array containing all the positions on the board where a word should be found
     */
    public CustomArrayList findPositions() {
        positions = new CustomArrayList<>();

        char prev = 'X';
        this.wordLength = 0;
        Alignment alignment;

        //Find horizontal positions 
        alignment = Alignment.HORIZONTAL;
        for (int y = 0; y < this.hight; y++) {
            for (int x = 0; x < this.width; x++) {
                this.addPosition(prev, x, y, alignment, this.boardOfWords[y][x]);
                prev = this.boardOfWords[y][x];
            }
            //end of the row
            this.addPosition(prev, this.width - 1, y, alignment, 'X');
            prev = 'X';
            this.wordLength = 0;
        }

        //Find vertical positions 
        alignment = Alignment.VERTICAL;
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.hight; y++) {
                this.addPosition(prev, x, y, alignment, this.boardOfWords[y][x]);
                prev = this.boardOfWords[y][x];
            }
            //end of the column
            this.addPosition(prev, x, this.hight - 1, alignment, 'X');
            prev = 'X';
            this.wordLength = 0;
        }

        if (this.positions.size() > 1) {
            QuickSort quickSort = new QuickSort();
            quickSort.sort(this.positions);
        }

        return this.positions;
    }

    /**
     * Helper method for findPositions.
     *
     * @param prev      previous character
     * @param x         x coordinate of the board
     * @param y         y coordinate of the board
     * @param alignment horizontal/vertical
     * @param current   character at the current coordinates
     */
    private void addPosition(char prev, int x, int y, Alignment alignment, char current) {
        if (prev == 'X' && current == 'O') {
            this.wordLength = 1;
            this.startX = x;
            this.startY = y;
        } else if (current == 'O') {
            this.wordLength++;
        } else if (current == 'X') {
            if (this.wordLength > 1) {
                WordPosition p = new WordPosition(this.startX, this.startY,
                        alignment, this.wordLength);
                this.positions.add(p);
            }
            this.wordLength = 0;
        }
    }
}
