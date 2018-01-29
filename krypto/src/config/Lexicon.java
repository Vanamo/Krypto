package config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

public class Lexicon {

    /**
     *
     */
    public Lexicon() {
    }

    /**
     * Import lexicon from xml file using DOM.
     *
     * @return
     * @throws JDOMException
     */
    public ArrayList<String> getLexicon() throws JDOMException {


        List<Element> elementList = null;
        ArrayList<String> wordList = new ArrayList<>();
        
        try {
            File inputFile = new File("./kotus-sanalista_v1/kotus-sanalista_v1.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            Element classElement = document.getRootElement();

            elementList = classElement.getChildren("st");

            for (int i = 0; i < elementList.size(); i++) {
                Element elementListElement = elementList.get(i);
                String word = elementListElement.getChildText("s");
                wordList.add(word);
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return wordList;
    }

    /**
     * Generate a list of words of defined length.
     *
     * @param wordList
     * @param wordLength
     * @return
     */
    public ArrayList<String> xLetterWords(ArrayList<String> wordList, int wordLength) {

        ArrayList<String> xLetterWords= new ArrayList<>();
        for (String word : wordList) {
            if (word.length() == wordLength) {
                xLetterWords.add(word);
            }
        }  
        return xLetterWords;
    }

}
