package crossword.logic;

import java.util.ArrayList;

/**
 * Defines the position of a new word on the board: x, y coordinates of the
 * starting position alignment (0 = for horizontal, 1 = for vertical alignment)
 * and length of the word.
 *
 * @author Vanamo Piirainen
 */
public class WordPosition implements Comparable<WordPosition> {

    private int x;
    private int y;
    private int alignment;
    private int wordLength;
    private ArrayList<WordPosition> crossingWords = null;

    public WordPosition(int x, int y, int alignment, int wordLength) {
        this.x = x;
        this.y = y;
        this.alignment = alignment;
        this.wordLength = wordLength;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAlignment() {
        return alignment;
    }

    public int getWordLength() {
        return wordLength;
    }

    public LetterPosition[] getLetterPositions() {
        LetterPosition[] letterPositions = new LetterPosition[this.wordLength - 1];
        int index = 0;
        if (this.alignment == 0) {
            for (int i = this.x; i < this.wordLength; i++) {
                letterPositions[index] = new LetterPosition(i, this.y);
                index++;
            }
        } else {
            for (int i = this.y; i < this.wordLength; i++) {
                letterPositions[index] = new LetterPosition(this.x, i);
                index++;
            }            
        }
        return letterPositions;
    }

    public void setCrossingWords(ArrayList<WordPosition> crossingWords) {
        this.crossingWords = crossingWords;
    }

    public ArrayList<WordPosition> getCrossingWords() {
        return this.crossingWords;
    }

    @Override
    public String toString() {
        return "WordPosition{" + "x=" + x + ", y=" + y + ", alignment=" + alignment + ", wordLength=" + wordLength + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WordPosition other = (WordPosition) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.alignment != other.alignment) {
            return false;
        }
        if (this.wordLength != other.wordLength) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(WordPosition o) {
        if (this.x + this.y < o.x + o.y) {
            return -1;
        } else if (this.x + this.y > o.x + o.y) {
            return 1;
        }
        return 0;
    }

}
