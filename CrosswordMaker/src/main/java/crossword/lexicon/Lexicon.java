package crossword.lexicon;

import crossword.datastructures.CustomArrayList;
import java.io.*;
import java.util.List;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

public class Lexicon {

    /**
     * Imports the Finnish lexicon from The Institute for the Languages of
     * Finland (http://kaino.kotus.fi/sanat/nykysuomi/) containing 94 110 words.
     * Also adds plurals for some of the words.
     */
    public Lexicon() {
    }

    /**
     * Import lexicon from xml file using DOM.
     *
     * @return CustomArrayList containing the words of the lexicon
     * @throws JDOMException   if the file is not found
     */
    public CustomArrayList<String> getLexicon() throws JDOMException {

        List<Element> elementList = null;
        CustomArrayList<String> wordList = new CustomArrayList<>();

        try {
            File input = new File("./kotus-sanalista_v1/kotus-sanalista_v1.xml");
            //InputStream input = getClass().getClassLoader().getResourceAsStream("kotus-sanalista_v1.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(input);
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
                if (word.indexOf('-') < 0) {
                    wordList.add(word.toLowerCase());
                }
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
     * @param wordList   array containing all the words of the lexicon 
     * @param wordLength searches for words of this length
     * @return           array containing all words of defined length
     */
    public CustomArrayList<String> xLetterWords(CustomArrayList<String> wordList,
            int wordLength) {

        CustomArrayList<String> xLetterWords = new CustomArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            String word = wordList.get(i);
            if (word.length() == wordLength) {
                xLetterWords.add(word);
            }
        }
        return xLetterWords;
    }

    /**
     * Constructs plurals for some nouns according to the conjucation rules of
     * Kotus and adds them to the wordlist.
     *
     * @param wordList CustomArrayList containing the words of the lexicon
     * @param word the word for which to construct the plural
     * @param c th number of the conjucation rule
     */
    public void addPlural(CustomArrayList<String> wordList, String word, int c) {

        Integer[] initSimple = {1, 2, 3, 5, 6, 8, 9, 10, 11, 12, 13, 15, 17, 18,
            19, 20, 21};
        Integer[] initEt = {23, 24, 25, 26, 29, 30};
        Character[] initC = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'};

        CustomArrayList<Integer> simple = new CustomArrayList<>();
        simple.addArray(initSimple);
        CustomArrayList<Integer> et = new CustomArrayList<>();
        et.addArray(initEt);
        CustomArrayList<Character> consonants = new CustomArrayList<>();
        consonants.addArray(initC);

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
