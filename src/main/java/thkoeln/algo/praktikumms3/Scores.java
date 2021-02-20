package thkoeln.algo.praktikumms3;

public class Scores {

    private final int NEEDLEMANWUNSCH_DEFAULT_MATCH_SCORE = 1;
    private final int NEEDLEMANWUNSCH_DEFAULT_MISMATCH_SCORE = -1;
    private final int NEEDLEMANWUNSCH_DEFAULT_GAP_PENALTY = -1;

    public int MATCH;
    public int MISMATCH;
    public int GAP;

    /***
     *     _______
     *    |     __|.----.-----.----.-----.-----.
     *    |__     ||  __|  _  |   _|  -__|__ --|
     *    |_______||____|_____|__| |_____|_____|
     *
     * Diese Klasse hält die Werte der Scores für das Alignment und stellt Scoring-Methoden (equal, weight)
     * zur Verfügung.
     *
     */
    public Scores() {
        MATCH = NEEDLEMANWUNSCH_DEFAULT_MATCH_SCORE;
        MISMATCH = NEEDLEMANWUNSCH_DEFAULT_MISMATCH_SCORE;
        GAP = NEEDLEMANWUNSCH_DEFAULT_GAP_PENALTY;
    }

    public Scores(int MATCH, int MISMATCH, int GAP) {
        this.MATCH = MATCH;
        this.MISMATCH = MISMATCH;
        this.GAP = GAP;
    }

    /***
     * Überprüft, ob ein MATCH oder MISMATCH vorliegt.
     *
     * @param a Character/Zeichen
     * @param b Character/Zeichen
     * @return MATCH oder MISMATCH Score
     */
    public int equal(char a, char b) {
        if (a == b)
            return MATCH;
        else
            return MISMATCH;
    }

    /***
     * Die Scoring-Funktion für den NeedlemanWunsch-Algorithmus
     *
     * @param a Character/Zeichen
     * @param b Character/Zeichen
     * @param GAP_CHAR Gap-Zeichen
     * @return MATCH, MISMATCH oder GAP Score
     */
    public int weight(char a, char b, char GAP_CHAR) {
        if (a == b)
            return MATCH;
        else if (a == GAP_CHAR || b == GAP_CHAR)
            return GAP;
        else
            return MISMATCH;
    }
}
