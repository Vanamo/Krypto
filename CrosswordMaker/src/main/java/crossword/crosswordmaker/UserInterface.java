package crossword.crosswordmaker;

import crossword.logic.BoardOfWords;
import crossword.logic.CrosswordMaker;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Vanamo Piirainen
 */
public class UserInterface {

    public UserInterface() {
    }

    public void makeCrossword() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Anna krypton leveys:");
        int width = scanner.nextInt();
        System.out.println("Anna krypton korkeus:");
        int hight = scanner.nextInt();
        System.out.println("Anna aloitussana tai paina r ja enter:");
        String firstWord = scanner.next();
        while (firstWord.length() > width) {
            System.out.println("Aloitussanan tulee mahtua kryptoon leveyssuunnassa.");
            System.out.println("Anna aloitussana tai paina r ja enter:");
            firstWord = scanner.next();
        }

        System.out.println("Generoidaan kryptoa... \n");

        CrosswordMaker crosswordMaker = new CrosswordMaker(width, hight, firstWord);
        BoardOfWords solutionWithLetters = crosswordMaker.fillBoard();
        if (solutionWithLetters == null) {
            System.out.println("Ratkaisua ei löytynyt. Yritä uudestaan eri aloitussanalla.");
        } else {
            String solutionWithNumbers = crosswordMaker.lettersToNumbers();
            printSolutionsToFile(solutionWithLetters, solutionWithNumbers);
        }
    }

    public void makeCrossword(int width, int hight, String firstWord) {
        System.out.println("Generoidaan kryptoa... \n");

        CrosswordMaker crosswordMaker = new CrosswordMaker(width, hight, firstWord);
        BoardOfWords solutionWithLetters = crosswordMaker.fillBoard();
        if (solutionWithLetters == null) {
            System.out.println("Ratkaisua ei löytynyt. Yritä uudestaan eri aloitussanalla.");
        } else {
            String solutionWithNumbers = crosswordMaker.lettersToNumbers();
            System.out.println(solutionWithLetters);
            System.out.println(solutionWithNumbers);
        }
    }

    private void printSolutionsToFile(BoardOfWords solutionWithLetters, String solutionWithNumbers) {
        String filename = "krypto_" + System.currentTimeMillis() + ".html";
        try {
            FileWriter fw = new FileWriter("c:/temp/" + filename);
            PrintWriter writer = new PrintWriter(fw);
            writer.println(solutionWithLetters.toHTML());
            //writer.println(solutionWithNumbers);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
