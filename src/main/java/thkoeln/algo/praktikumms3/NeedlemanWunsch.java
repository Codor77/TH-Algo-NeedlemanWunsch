package thkoeln.algo.praktikumms3;

/***
 *     _______                 __ __
 *    |    |  |.-----.-----.--|  |  |.-----.--------.---.-.-----.
 *    |       ||  -__|  -__|  _  |  ||  -__|        |  _  |     |
 *    |__|____||_____|_____|_____|__||_____|__|__|__|___._|__|__|
 *
 *     ________                          __
 *    |  |  |  |.--.--.-----.-----.----.|  |--.
 *    |  |  |  ||  |  |     |__ --|  __||     |
 *    |________||_____|__|__|_____|____||__|__|
 *
 *     _______ __                    __ __   __
 *    |   _   |  |.-----.-----.----.|__|  |_|  |--.--------.--.--.-----.
 *    |       |  ||  _  |  _  |   _||  |   _|     |        |  |  |__ --|
 *    |___|___|__||___  |_____|__|  |__|____|__|__|__|__|__|_____|_____|
 *                |_____|
 *
 *  Diese Klasse hält Methoden bereit zum Alignment zweier Sequenzen mithilfe des
 *  NeedlemanWunsch-Algorithmus.
 *
 *  Bemerkung:
 *  Die einzelnen Schritte des Algorithmus wurden in separate Methoden verlagert,
 *  um besser getestet werden zu können.
 *
 */


@SuppressWarnings("ConstantConditions")
public class NeedlemanWunsch {

    /***
     *     _______ __ __
     *    |   _   |  |__|.-----.-----.
     *    |       |  |  ||  _  |     |
     *    |___|___|__|__||___  |__|__|
     *                   |_____|
     *
     * Liefert das Alignment zweier Sequenzen
     *
     */
    public static Alignment align(Alignment alignment) {
        alignment = initialize(alignment);
        alignment = calculate(alignment);
        alignment = reconstruct(alignment);
        return alignment;
    }

    public static Alignment align(String S1, String S2) {
        throw new UnsupportedOperationException();
        return align(alignment);
    }

    public static Alignment align(String S1, String S2, Scores scores) {
        throw new UnsupportedOperationException();
        return align(alignment);
    }

    private static Alignment align(String S1, String S2, Scores scores, char GAP_CHAR) {
        throw new UnsupportedOperationException();
        return align(alignment);
    }


    /***
     *     ____           _______         __ __   __         __ __
     *    |_   |         |_     _|.-----.|__|  |_|__|.---.-.|  |__|.-----.-----.
     *     _|  |_ __      _|   |_ |     ||  |   _|  ||  _  ||  |  ||-- __|  -__|
     *    |______|__|    |_______||__|__||__|____|__||___._||__|__||_____|_____|
     *
     * Initialisierung der Score-Matrix
     *
     *      Benötigt:
     *          - Sequenz X
     *          - Sequenz Y
     *          - Scores-Instanz
     *      Initialisierung der ersten Zeile/Spalte der ScoreMatrix
     *      Nachbedingungen:
     *          - Alignment enthält initialisierte ScoreMatrix
     *          - Alignment isInitialized() --> true
     */
    public static Alignment initialize(Alignment alignment) {
        throw new UnsupportedOperationException();
    }



    /***
     *     ______         ______         __              __         __
     *    |__    |       |      |.---.-.|  |.----.--.--.|  |.---.-.|  |_.-----.
     *    |    __|__     |   ---||  _  ||  ||  __|  |  ||  ||  _  ||   _|  -__|
     *    |______|__|    |______||___._||__||____|_____||__||___._||____|_____|
     *
     *      Benötigt:
     *          - Sequenz X
     *          - Sequenz Y
     *          - ScoreMatrix (initialisiert)
     *      Resilienz: Noch nicht initialisiert? --> initialisiere Scorematrix
     *      Nachbedingungen:
     *          - Alignment enthält berechnete ScoreMatrix
     *          - Alignment isCalculated() --> true
     */
    public static Alignment calculate(Alignment alignment) {
        throw new UnsupportedOperationException();
        return alignment;
    }


    /***
     *     ______         ______                                __                    __
     *    |__    |       |   __ \.-----.----.-----.-----.-----.|  |_.----.--.--.----.|  |_
     *    |__    |__     |      <|  -__|  __|  _  |     |__ --||   _|   _|  |  |  __||   _|
     *    |______|__|    |___|__||_____|____|_____|__|__|_____||____|__| |_____|____||____|
     *
     *  NeedlemanWunsch-Rekonstruktion der Alignment-Strings
     *      Benötigt:
     *          - Sequenz X,
     *          - Sequenz Y,
     *          - Scores-Instanz
     *          - ScoreMatrix (initialisiert und berechnet)
     *      Nachbedingungen:
     *          - Alignment enthält rekonstruierten X_Alignment-String
     *          - Alignment enthält rekonstruierten Y_Alignment-String
     *          - Alignment enthält rekonstruierten Operations-String
     *              - MATCH     -->  '*'
     *              - MISMATCH  -->  '|'
     *              - GAP       -->  '_'
     */
    private static Alignment reconstruct(Alignment alignment) {
        throw new UnsupportedOperationException();
        return alignment;
    }

}
