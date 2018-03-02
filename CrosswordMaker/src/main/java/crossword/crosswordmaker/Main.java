package crossword.crosswordmaker;

public class Main {

    public static void main(String[] args) {

        UserInterface krypto = new UserInterface();
        //krypto.makeCrossword();

        //Suorituskykytestaus
        int[] size = {5, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        for (int i = 0; i < size.length; i++) {
            for (int a = 1; a < 4; a++) {
                krypto.makeCrossword(size[i], size[i], "likka");
                krypto.makeCrossword(size[i], size[i], "testi");
                krypto.makeCrossword(size[i], size[i], "marsu");
                krypto.makeCrossword(size[i], size[i], "ärinä");
            }
        }
    }
}
