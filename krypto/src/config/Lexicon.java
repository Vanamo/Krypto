package config;

import java.io.*;
import java.util.List;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

public class Lexicon {

    public Lexicon() {
    }

    public List getLexicon() throws JDOMException {
        //Import lexicon using DOM

        List<Element> elementList = null;
        
        try {
            File inputFile = new File("./kotus-sanalista_v1/kotus-sanalista_v1.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            Element classElement = document.getRootElement();

            elementList = classElement.getChildren("st");


        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return elementList;
    }

}
