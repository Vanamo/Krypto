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

    public void addFirstWord(String firstWord) {
        //alignment of the first word is horizontal
        this.firstPosition = new WordPosition(0, 0, 0, firstWord.length());
        this.drawWord(firstWord, firstPosition);
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

    public ArrayList<WordPosition> findPositions() {
        ArrayList<WordPosition> positions = new ArrayList<>();

//        //Horizontal blocks
//        int blockWidth = Math.min(this.firstPosition.getWordLength(), 8);
//        ArrayList<Integer> blocksH = new ArrayList<>();
//        blocksH = calculateBlocks(blocksH, this.width, blockWidth);
//
//        //Vertical blocks
//        int blockHight = Math.min(this.hight, 6);
//        ArrayList<Integer> blocksV = new ArrayList<>();
//        blocksV = this.calculateBlocks(blocksV, this.hight, blockHight);
//
//        //Horizontal positions
//        for (int i = 0; i < blocksH.size(); i += 2) {
//            int x = 0;
//            for (int j = 0; j <= i; j++) {
//                x += blocksH.get(j);
//            }
//            int wordLength = blocksH.get(i);
//            for (int y = 0; y <= blocksV.get(i); y += 2) {
//                WordPosition p = new WordPosition(x, y, 0, wordLength);
//                positions.add(p);
//            }
//        }
//        //Vertical positions
//        for (int i = 0; i < blocksV.size(); i += 2) {
//            int y = 0;
//            for (int j = 0; j <= i; j++) {
//                y += blocksV.get(j);
//            }
//            int wordLength = blocksV.get(i);
//            for (int x = 0; x <= blocksH.get(i); x += 2) {
//                WordPosition p = new WordPosition(x, 1, 1, wordLength);
//                positions.add(p);
//            }
//        }
        WordPosition p1 = new WordPosition(0, 0, 1, 5);
        WordPosition p2 = new WordPosition(2, 0, 1, 5);
        WordPosition p3 = new WordPosition(4, 0, 1, 5);
        WordPosition p4 = new WordPosition(0, 2, 0, 5);
        WordPosition p5 = new WordPosition(0, 4, 0, 5);
        positions.add(p1);
        positions.add(p2);
        positions.add(p3);
        positions.add(p4);
        positions.add(p5);
        
        return positions;
    }

    private ArrayList<Integer> calculateBlocks(ArrayList<Integer> blocks,
            int spaceLeft, int length) {

        Random rand = new Random();
        while (spaceLeft >= 11) {
            blocks.add(length);
            spaceLeft -= (length - 1);
            length = rand.nextInt(3) + 4;
            if (length % 2 == 0) {
                length++;
            }
        }
        blocks.add(spaceLeft);
        return blocks;
    }

    public char getLetter(int x, int y) {
        return this.boardOfWords[y][x];
    }

    public char[][] getBoard() {
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
