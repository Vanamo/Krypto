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
    
        UserInterface krypto = new UserInterface();
        krypto.makeCrossword();
    }
}
