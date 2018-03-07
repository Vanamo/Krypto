package crossword.logic;

/**
 * Defines the position of a new word on the board: x, y coordinates of the
 * starting position, alignment (horizontal or vertical alignment)
 * and length of the word.
 *
 * @author Vanamo Piirainen
 */
public class WordPosition implements Comparable<WordPosition> {

    private int x;
    private int y;
    private Alignment alignment;
    private int wordLength;

    /**
     *
     * @param x             x coordinate of the first letter of the word
     * @param y             y coordinate of the first letter of the word
     * @param alignment     alignment of the word (horizontal/vertical)
     * @param wordLength    length of the word
     */
    public WordPosition(int x, int y, Alignment alignment, int wordLength) {
        this.x = x;
        this.y = y;
        this.alignment = alignment;
        this.wordLength = wordLength;
    }

    /**
     *
     * @return  x coordinate of the WordPosition
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return  y coordinate of the WordPosition
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @returnx alignment of the WordPosition
     */
    public Alignment getAlignment() {
        return alignment;
    }

    /**
     *
     * @returnx length of the WordPosition
     */
    public int getWordLength() {
        return wordLength;
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
        //Compare positions
        if (this.x + this.y < o.x + o.y) {
            return -1;
        } else if (this.x + this.y > o.x + o.y) {
            return 1;
        }
        //Horizontal positions first
        if (this.alignment == Alignment.HORIZONTAL && o.alignment == Alignment.VERTICAL) {
            return -1;
        } else if (this.alignment == Alignment.VERTICAL && o.alignment == Alignment.HORIZONTAL) {
            return 1;
        }
        return 0;
    }

}
