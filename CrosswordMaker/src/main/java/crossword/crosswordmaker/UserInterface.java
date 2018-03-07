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
        int height = scanner.nextInt();
        System.out.println("Anna aloitussana tai paina r ja enter:");
        String firstWord = scanner.next();
        while (firstWord.length() > width) {
            System.out.println("Aloitussanan tulee mahtua kryptoon leveyssuunnassa.");
            System.out.println("Anna aloitussana tai paina r ja enter:");
            firstWord = scanner.next();
        }

        System.out.println("Generoidaan kryptoa... \n");

        CrosswordMaker crosswordMaker = new CrosswordMaker(width, height, firstWord);
        BoardOfWords solutionWithLetters = crosswordMaker.fillBoard();
        if (solutionWithLetters == null) {
            System.out.println("Ratkaisua ei löytynyt. Yritä uudestaan eri aloitussanalla.");
        } else {
            Integer[][] boardWithNumbers = crosswordMaker.lettersToNumbers();
            String solutionWithNumbers = crosswordMaker.boardWithNumbersToHtmlString(boardWithNumbers);
            printSolutionsToFile(solutionWithLetters, solutionWithNumbers);
        }
    }

    private void printSolutionsToFile(BoardOfWords solutionWithLetters, String solutionWithNumbers) {
        String filename = "krypto_" + System.currentTimeMillis() + ".html";
        try {
            //FileWriter fw = new FileWriter("c:/temp/" + filename);
            FileWriter fw = new FileWriter(filename);
            PrintWriter writer = new PrintWriter(fw);
            writer.println("<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">"
                + "</head>\n"
                + "<body>\n");
            writer.println(solutionWithNumbers);
            writer.println("<br><br><br><br><br><br>\n");
            writer.println(solutionWithLetters.toHTML());
            writer.println("</body>\n</html>");
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
