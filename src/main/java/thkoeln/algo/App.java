package thkoeln.algo;

import thkoeln.algo.praktikumms3.Alignment;
import thkoeln.algo.praktikumms3.NeedlemanWunsch;
import thkoeln.algo.praktikumms3.Scores;

import java.util.Scanner;

public class App {

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

        
    }
}
