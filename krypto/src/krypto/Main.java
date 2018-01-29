package krypto;

import config.Lexicon;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        krypto.printBoard();

    }

}
