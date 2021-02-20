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
        return align(new Alignment(S1,S2));
    }

    public static Alignment align(String S1, String S2, Scores scores) {
        return align(new Alignment(S1,S2,scores));
    }

    private static Alignment align(String S1, String S2, Scores scores, char GAP_CHAR) {
        return align(new Alignment(S1,S2,scores,GAP_CHAR));
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
        int[][] initializedMatrix = alignment.getScoreMatrix();

        for(int x = 0; x <= initializedMatrix.length -1; x++)
            initializedMatrix[x][0] = x * alignment.getScoreSettings().GAP;
        for(int y = 0; y <= initializedMatrix[0].length -1; y++)
            initializedMatrix[0][y] = y * alignment.getScoreSettings().GAP;

        alignment.setScoreMatrix(initializedMatrix);
        return alignment;
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
        int[][] calculatedMatrix = alignment.getScoreMatrix();

        for (int x = 1; x <= alignment.getX_Sequence().length(); x++) {
            for (int y = 1; y <= alignment.getY_Sequence().length(); y++) {
                int matchScore = alignment.getScoreSettings().MISMATCH;
                if (alignment.getX_Sequence().charAt(x-1) == alignment.getY_Sequence().charAt(y-1))
                    matchScore = alignment.getScoreSettings().MATCH;

                int match = calculatedMatrix[x-1][y-1] + matchScore;
                int deletion = calculatedMatrix[x-1][y] + alignment.getScoreSettings().GAP;
                int insertion = calculatedMatrix[x][y-1] + alignment.getScoreSettings().GAP;

                calculatedMatrix[x][y] = Math.max(match,Math.max(deletion,insertion));
            }
        }

        alignment.setScoreMatrix(calculatedMatrix);
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
        int[][] scoreMatrix = alignment.getScoreMatrix();

        String X_Sequence = alignment.getX_Sequence();
        String Y_Sequence = alignment.getY_Sequence();

        StringBuffer X_AlignmentBuffer = new StringBuffer();
        StringBuffer OperationsBuffer = new StringBuffer();
        StringBuffer Y_AlignmentBuffer = new StringBuffer();

        char gap_char = alignment.getGapCharacter();

        int x = scoreMatrix.length -1;
        int y = scoreMatrix[0].length -1;

        while (x > 0 && y > 0){

            int score = scoreMatrix[x][y];
            int matchScore = scoreMatrix[x - 1][y - 1];
            int deletionScore = scoreMatrix[x - 1][y];
            int insertionScore = scoreMatrix[x][y - 1];

            boolean matchBool = false;

            if (score == matchScore + alignment.getScoreSettings().MISMATCH || score == matchScore + alignment.getScoreSettings().MATCH) {
                boolean match = X_Sequence.charAt(x - 1) == Y_Sequence.charAt(y - 1);
                if (match)
                    matchBool = true;
            } else
                matchScore = Integer.MIN_VALUE;

            if (score != deletionScore + alignment.getScoreSettings().GAP) {
                deletionScore = Integer.MIN_VALUE;
            }
            if (score != insertionScore + alignment.getScoreSettings().GAP) {
                insertionScore = Integer.MIN_VALUE;
            }

            if (matchBool) {
                X_AlignmentBuffer.append(X_Sequence.charAt(x-1));
                OperationsBuffer.append('*');
                Y_AlignmentBuffer.append(Y_Sequence.charAt(y-1));
                x--;
                y--;
            }
            else {
                int prevScore = Math.max(matchScore, Math.max(deletionScore, insertionScore));
                if (prevScore == matchScore) {
                    X_AlignmentBuffer.append(X_Sequence.charAt(x - 1));
                    OperationsBuffer.append('|');
                    Y_AlignmentBuffer.append(Y_Sequence.charAt(y - 1));
                    x--;
                    y--;
                } else if (prevScore == deletionScore) {
                    X_AlignmentBuffer.append(X_Sequence.charAt(x - 1));
                    OperationsBuffer.append('_');
                    Y_AlignmentBuffer.append(gap_char);
                    x--;
                } else if (prevScore == insertionScore) {
                    X_AlignmentBuffer.append(gap_char);
                    OperationsBuffer.append('_');
                    Y_AlignmentBuffer.append(Y_Sequence.charAt(y - 1));
                    y--;
                }
            }
        }
        while ( x > 0 || y > 0){
            if ( x == 0 ){
                X_AlignmentBuffer.append(gap_char);
                OperationsBuffer.append('_');
                Y_AlignmentBuffer.append(Y_Sequence.charAt(y - 1));
                y--;
            }
            else {
                X_AlignmentBuffer.append(X_Sequence.charAt(x - 1));
                OperationsBuffer.append('_');
                Y_AlignmentBuffer.append(gap_char);
                x--;
            }
        }
        
        alignment.setX_Alignment(X_AlignmentBuffer.reverse().toString());
        alignment.setOperationsString(OperationsBuffer.reverse().toString());
        alignment.setY_Alignment(Y_AlignmentBuffer.reverse().toString());
        return alignment;
    }
}
