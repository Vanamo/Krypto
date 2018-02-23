package crossword.logic;

import crossword.datastructures.CustomArrayList;

/**
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
     * For a board filled with 'X's (empty squares) and 'O's (to be letters),
     * find positions where words will be layed.
     *
     * @return
     */
    public CustomArrayList findPositions() {
        positions = new CustomArrayList<>();

        char prev = 'X';
        this.wordLength = 0;
        int alignment;

        //Find horizontal positions 
        alignment = 0;
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
        alignment = 1;
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
            this.sort();
        }
        
        return this.positions;
    }

    /**
     * Uses quick sort to sort the contents of the custom arraylist. Code
     * from http://www.java2novice.com/java-sorting-algorithms/quick-sort/
     */
    public void sort() {
        this.quicksort(0, positions.size() - 1);
    }

    private void quicksort(int left, int right) {
        int[] partition = this.makePartition(left, right);
        int i = partition[0];
        int j = partition[1];
        if (left < j) {
            quicksort(left, j);
        }
        if (i < right) {
            quicksort(i, right);
        }

    }

    /**
     * Helper method for quicksort
     *
     * @param left
     * @param right
     * @return
     */
    private int[] makePartition(int left, int right) {
        WordPosition partitionElement = positions.get(left);
        int i = left;
        int j = right;
        while (i <= j) {
            while (positions.get(i).compareTo(partitionElement) == -1) {
                i++;
            }
            while (positions.get(j).compareTo(partitionElement) == 1) {
                j--;
            }
            if (i <= j) {
                this.switchPlaces(i, j);
                i++;
                j--;
            }
        }
        int[] ij = {i, j};
        return ij;
    }

    /**
     * Helper method for quicksort
     *
     * @param i
     * @param j
     */
    private void switchPlaces(int i, int j) {
        WordPosition temp = positions.get(i);
        positions.replace(i, positions.get(j));
        positions.replace(j, temp);
    }

    private void addPosition(char prev, int x, int y, int alignment, char current) {
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
