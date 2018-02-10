package crossword.crosswordmaker;

import crossword.logic.BoardOfWords;
import crossword.logic.CrosswordMaker;
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
        int width = 5;
        int hight = 5;
        String firstWord = "";

        System.out.println("Anna krypton leveys:");
        width = scanner.nextInt();
        System.out.println("Anna krypton korkeus:");
        hight = scanner.nextInt();
        System.out.println("Anna aloitussana tai paina r ja enter:");
        firstWord = scanner.next();
        while (firstWord.length() > width) {
            System.out.println("Aloitussanan tulee mahtua kryptoon leveyssuunnassa.");
            System.out.println("Anna aloitussana tai paina r ja enter:");
            firstWord = scanner.next();
        }
        if (firstWord.equals("r")) {
            firstWord = "";
        }
        
        System.out.println("Generoidaan kryptoa... \n");
        
        CrosswordMaker crosswordMaker = new CrosswordMaker(width, hight, firstWord);
        BoardOfWords solution = crosswordMaker.fillBoard();
        //crosswordMaker.makeWordLengthStatistics();
        if (solution == null) {
            System.out.println("Ratkaisua ei löytynyt. Yritä uudestaan eri aloitussanalla.");
        } else {
            solution.printBoard();
        }
    }
}
