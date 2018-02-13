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
        BoardOfWords solution = crosswordMaker.fillBoard();
        if (solution == null) {
            System.out.println("Ratkaisua ei löytynyt. Yritä uudestaan eri aloitussanalla.");
        } else {
            System.out.println(solution);;
        }
    }
}
