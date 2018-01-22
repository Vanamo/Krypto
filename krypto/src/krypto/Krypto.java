package krypto;

import config.Lexicon;
import java.util.List;
import org.jdom2.Element;
import org.jdom2.JDOMException;

public class Krypto {

    public static void main(String[] args) throws JDOMException {

        Lexicon lexicon = new Lexicon();
        List<Element> wordList = lexicon.getLexicon();
        
        for (int i = 0; i < wordList.size(); i++) {
            Element wordListElement = wordList.get(i);
            System.out.println(wordListElement.getChildText("s"));
        }

    }

}
