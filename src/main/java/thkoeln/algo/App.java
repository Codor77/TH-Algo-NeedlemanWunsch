package thkoeln.algo;

import thkoeln.algo.praktikumms3.Alignment;
import thkoeln.algo.praktikumms3.NeedlemanWunsch;
import thkoeln.algo.praktikumms3.Scores;

import java.util.Scanner;

public class App {
    private static String S1;
    private static String S2;

    private static int match;
    private static int mismatch;
    private static int gap;

    private static Scanner scanner = new Scanner(System.in);
    private static Alignment alignment;
    private static NeedlemanWunsch needlemanWunsch = new NeedlemanWunsch();

    public static void main(String[] args) {
        System.out.println("Globales Sequence-Alignment mit dem Needleman-Wunsch-Algorithmus" );
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Für das globale Alignment zweier Sequenzen sind die folgenden Eingaben erforderlich:");
        System.out.println(" - S1: Erste Sequenz/Zeichenfolge");
        System.out.println(" - S2: Zweite Sequenz/Zeichenfolge");
        System.out.println(" - Gewichtungen:");
        System.out.println("   - MatchScore:           Bewertung bei Übereinstimmung       (default:  1)");
        System.out.println("   - MismatchScore:        Bewertung bei Nicht-Übereinstimmung (default: -1)");
        System.out.println("   - GapScore/GapPenalty:  Bewertung bei Auslassung            (default: -1)\n");
        System.out.println();
        S1 = getStringFromUser("Enter S1: ");
        S2 = getStringFromUser("Enter S2: ");
        System.out.println();
        match = getIntFromUser("Enter MatchScore: ");
        mismatch = getIntFromUser("Enter MismatchScore: ");
        gap = getIntFromUser("Enter GapScore/GapPenalty: ");
        System.out.println();

        alignment = new Alignment(S1,S2,new Scores(match,mismatch,gap));
        needlemanWunsch.align(alignment);

        System.out.println("Alignment-Score: "+alignment.getScore());
        System.out.println("Aligned S1: "+alignment.getX_Alignment());
        System.out.println("            "+alignment.getOperationsString().replace('_',' ').replace('|',' ').replace('*','|'));
        System.out.println("Aligned S2: "+alignment.getY_Alignment());

    }

    private static int getIntFromUser(String question){
        String answer;
        System.out.print(question);

        do {
            answer = scanner.nextLine();

            try {
                return Integer.parseInt(answer);
            }
            catch (Exception e){
                System.out.print("Integer required, try again: ");
            }

        } while (true);
    }
    private static String getStringFromUser(String question){
        System.out.print(question);
        return scanner.nextLine();
    }
}