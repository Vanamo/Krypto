package crossword.crosswordmaker;

import crossword.lexicon.Lexicon;
import crossword.logic.BoardOfWords;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import crossword.logic.WordFinder;
import crossword.logic.WordPosition;
import org.jdom2.Element;
import org.jdom2.JDOMException;

public class Main {

    public static void main(String[] args) throws JDOMException {

        Lexicon lexicon = new Lexicon();
        ArrayList<String> wordList = lexicon.getLexicon();
        ArrayList<String> fiveLetterWords = new ArrayList<>();

        //List all five-letter words
        fiveLetterWords = lexicon.xLetterWords(wordList, 5);
    
        UserInterface krypto = new UserInterface(fiveLetterWords);
        krypto.getBoardOfWords().printBoard();

//        WordFinder finder = new WordFinder(krypto);
//        WordPosition position = new WordPosition(0, 0, 1, 5);
//        ArrayList<String> words = finder.findWords(position);
//        for (String w : words) {
//            System.out.println(w);
//        }

        WordPosition p1 = new WordPosition(0, 0, 1, 5);
        WordPosition p2 = new WordPosition(2, 0, 1, 5);
        WordPosition p3 = new WordPosition(4, 0, 1, 5);
        WordPosition p4 = new WordPosition(0, 2, 0, 5);
        WordPosition p5 = new WordPosition(0, 4, 0, 5);
        ArrayList<WordPosition> positions = new ArrayList<>();
        positions.add(p1);
        positions.add(p2);
        positions.add(p3);
        positions.add(p4);
        positions.add(p5);
        BoardOfWords solution = krypto.fillBoard(positions);
        if (solution == null) {
            System.out.println("no solution");
        } else {
            solution.printBoard();
        }
    }

}
