package crossword.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
    private ArrayList<WordPosition> positions;
    private HashMap<LetterPosition, ArrayList<WordPosition>> wordsAt;

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
    public ArrayList<WordPosition> findPositions() {
        positions = new ArrayList<>();

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

        Collections.sort(this.positions);
        return this.positions;
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

    public void findCrossingPositions() {
        //Find all word positions intersecting a certain position on the board
        this.wordsAt = new HashMap<>();
        for (WordPosition position : this.positions) {
            LetterPosition[] letterPositions = position.getLetterPositions();
            for (LetterPosition lp : letterPositions) {
                addToWordsAt(lp, position);
            }
        }

        //For a certain word position find word positions that intersect
        for (WordPosition position : this.positions) {
            LetterPosition[] letterPositions = position.getLetterPositions();
            ArrayList<WordPosition> crossingPositions = new ArrayList<>();
            for (LetterPosition lp : letterPositions) {                
                crossingPositions.addAll(this.wordsAt.get(lp));
            }  
            position.setCrossingPositions(crossingPositions);
        }
    }

    /**
     * Helper method for findCrossingPositions
     * @param lp
     * @param position 
     */
    private void addToWordsAt(LetterPosition lp, WordPosition position) {
        if (this.wordsAt.containsKey(lp)) {
            ArrayList<WordPosition> p = this.wordsAt.get(lp);
            p.add(position);
            this.wordsAt.replace(lp, p);
        } else {
            ArrayList<WordPosition> p = new ArrayList<>();
            p.add(position);
            this.wordsAt.put(lp, p);
        }
    }
}
