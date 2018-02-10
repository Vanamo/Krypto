package crossword.logic;

/**
 * Defines the position of a new word on the board:
 * x, y cordinates of the starting position
 * alignment (0 = for horizontal, 1 = for vertical alignment)
 * and length of the word.
 * 
 * @author Vanamo Piirainen
 */
public class WordPosition {
    
    private int x;
    private int y;
    private int alignment;
    private int wordLength;

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

    @Override
    public String toString() {
        return "WordPosition{" + "x=" + x + ", y=" + y + ", alignment=" + alignment + ", wordLength=" + wordLength + '}';
    }
    
    
}
