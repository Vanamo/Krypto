package crossword.lexicon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
                Element node = elementList.get(i);
                String word = node.getChildText("s");
                Element conjugations = node.getChild("t");
                if (conjugations != null) {
                    String conj = conjugations.getChildText("tn");
                    String gradation = conjugations.getChildText("av");
                    if (gradation == null) {
                        int c = Integer.parseInt(conj);
                        addPlural(wordList, word, c);
                    }
                }
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

        ArrayList<String> xLetterWords = new ArrayList<>();
        for (String word : wordList) {
            if (word.length() == wordLength) {
                xLetterWords.add(word);
            }
        }
        return xLetterWords;
    }

    /**
     * Creates plurals for some nouns according to the conjucation rules of
     * Kotus and adds them to the wordlist.
     *
     * @param wordList
     * @param word
     * @param c
     */
    private void addPlural(ArrayList<String> wordList, String word, int c) {

        Integer[] initSimple = {1, 2, 3, 5, 6, 8, 9, 10, 11, 12, 13, 15, 17, 18,
            19, 20, 21};
        Integer[] initEt = {23, 24, 25, 26, 29, 30};
        Character[] initC = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'};

        ArrayList<Integer> simple = new ArrayList<>(Arrays.asList(initSimple));
        ArrayList<Integer> et = new ArrayList<>(Arrays.asList(initEt));
        ArrayList<Character> consonants = new ArrayList<>(Arrays.asList(initC));

        if (simple.contains(c)) {
            if (consonants.contains(word.charAt(word.length() - 1))) {
                word = word.concat("i");
            }
            word = word.concat("t");
            wordList.add(word);
        } else if (et.contains(c)) {
            word = word.substring(0, word.length() - 1);
            word = word.concat("et");
            wordList.add(word);
        }
    }

}
