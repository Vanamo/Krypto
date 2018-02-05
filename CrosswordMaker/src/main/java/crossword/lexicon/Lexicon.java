package crossword.lexicon;

import java.io.*;
import java.util.ArrayList;
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
     * Creates plurals for some nouns according to the conjucation rules of Kotus 
     * and adds them to the wordlist.
     * @param wordList
     * @param word
     * @param c 
     */
    
    private void addPlural(ArrayList<String> wordList, String word, int c) {

        Set<Integer> simple = new HashSet<>();
        simple.add(1);
        simple.add(2);
        simple.add(3);
        simple.add(5);
        simple.add(6);
        simple.add(8);
        simple.add(9);
        simple.add(10);
        simple.add(11);
        simple.add(12);
        simple.add(13);
        simple.add(15);
        simple.add(17);
        simple.add(18);
        simple.add(19);
        simple.add(20);
        simple.add(21);

        Set<Integer> et = new HashSet<>();
        et.add(23);
        et.add(24);
        et.add(25);
        et.add(26);
        et.add(29);
        et.add(30);

        Set<Character> consonants = new HashSet<>();
        consonants.add('b');
        consonants.add('c');
        consonants.add('d');
        consonants.add('f');
        consonants.add('g');
        consonants.add('h');
        consonants.add('j');
        consonants.add('k');
        consonants.add('l');
        consonants.add('m');
        consonants.add('n');
        consonants.add('p');
        consonants.add('q');
        consonants.add('r');
        consonants.add('s');
        consonants.add('t');
        consonants.add('v');
        consonants.add('w');
        consonants.add('x');
        consonants.add('z');
        

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
