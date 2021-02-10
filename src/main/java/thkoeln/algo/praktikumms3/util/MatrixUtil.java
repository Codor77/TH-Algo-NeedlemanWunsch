package thkoeln.algo.praktikumms3.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;


/***
 *     _______         __         __         _______ __   __ __
 *    |   |   |.---.-.|  |_.----.|__|.--.--.|   |   |  |_|__|  |
 *    |       ||  _  ||   _|   _||  ||_   _||   |   |   _|  |  |
 *    |__|_|__||___._||____|__|  |__||__.__||_______|____|__|__|
 *
 * Helferklasse mit Methoden zur Ausgabe und zum Vergleich von Matrizen
 *
 *
 *
 *
 */
public class MatrixUtil {

    public static boolean MatrixEquals(int[][] m1, int [][] m2){
        // Check dimensions
        if (m1.length != m2.length)
            return  false;
        if (m1[0].length != m2[0].length)
            return  false;

        // compare cells
        for (int x = 0; x < m1.length -1; x++){
            for (int y = 0; y < m1[1].length; y++){
                if (m1[x][y] != m2[x][y])
                    return false;
            }
        }
        return true;
    }

    private static String cell(String s){
        return String.format("%5s ", s);
    }

    private static String numberCell(int s, NumberFormat nf){
        return cell(nf.format(s));
    }

    /***
     *
     * @return Formatierte Ausgabe der Score-Matrix
     */
    public static String printScoreMatrix(int[][] scoreMatrix) {
        StringBuffer sb = new StringBuffer();
        NumberFormat nf = new DecimalFormat("#;-#");

        for (int x = 0; x < scoreMatrix.length; x++) {
            for (int y = 0; y < scoreMatrix[0].length; y++) {
                sb.append(numberCell(scoreMatrix[x][y], nf));
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    public static String printScoreMatrixForTests(int[][] scoreMatrix) {
        StringBuffer sb = new StringBuffer();
        NumberFormat nf = new DecimalFormat("#;-#");

        sb.append("int[][] expectedSoreMatrix = {\n");
        for (int x = 0; x < scoreMatrix.length; x++) {
            sb.append("\t{");
            for (int y = 0; y < scoreMatrix[0].length; y++) {
                sb.append(numberCell(scoreMatrix[x][y], nf));
                sb.append(",");
            }
            sb.delete(sb.length() - 1, sb.length() );
            sb.append(" },\n");
        }
        sb.delete(sb.length() - 2, sb.length() - 1);
        sb.append("};");
        return sb.toString();
    }

    public static String printAlignmentTable(String X_Sequence, String Y_Sequence, int[][] scoreMatrix) {
        StringBuffer sb = new StringBuffer();
        NumberFormat nf = new DecimalFormat("#;-#");

        // Table Header-Lines - indices
        sb.append(cell("")).append(cell("y="));
        for (int y = 0; y < Y_Sequence.length() + 1; y++) {
            sb.append(numberCell(y, nf));
        }
        sb.append('\n');

        // Table Header-Lines - Characters
        sb.append(cell("x=")).append(cell("")).append(cell("_"));
        for (int y = 0; y < Y_Sequence.length(); y++) {
            sb.append(cell(String.valueOf(Y_Sequence.charAt(y))));
        }
        sb.append('\n');

        for (int x = 0; x < X_Sequence.length() + 1; x++) {
            // Table Start-Column-cells
            sb.append(numberCell(x, nf));
            if (x == 0)
                sb.append(cell("_"));
            else
                sb.append(cell(String.valueOf(X_Sequence.charAt(x-1))));

            for (int y = 0; y < Y_Sequence.length() + 1; y++) {
                String formatedCell = numberCell(scoreMatrix[x][y], nf);

                sb.append(formatedCell);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static String printAlignmentTableColorsExperimental(String X_Sequence, String Y_Sequence, int[][] scoreMatrix) {
        StringBuffer sb = new StringBuffer();
        NumberFormat nf = new DecimalFormat("#;-#");

        String blackOnWhite = "\033[30;47m";
        String sequenceColor = "\033[30;41m";
        String gapColor = "\033[32;40m";
        String match = "\033[32m";
        String mismatch = "\033[31m";
        String clearColor = "\033[0;0m";

        // Table Header-Lines - indices
        sb.append(cell("")).append(blackOnWhite + cell(  "y="));
        for (int y = 0; y < Y_Sequence.length() + 1; y++) {
            sb.append(numberCell(y, nf));
        }
        sb.append(clearColor).append('\n');

        // Table Header-Lines - Characters
        sb.append(blackOnWhite + cell("x=") + clearColor);
        sb.append(cell(""));
        sb.append(sequenceColor + cell("_"));
        for (int y = 0; y < Y_Sequence.length(); y++) {
            sb.append(sequenceColor + cell(String.valueOf(Y_Sequence.charAt(y))) + clearColor);
        }
        sb.append('\n');

        for (int x = 0; x < X_Sequence.length() + 1; x++) {
            // Table Start-Column-cells
            sb.append(blackOnWhite + numberCell(x, nf) + clearColor);
            if (x == 0)
                sb.append(sequenceColor + cell("_") + clearColor);
            else
                sb.append(sequenceColor + cell(String.valueOf(X_Sequence.charAt(x-1))) + clearColor);

            for (int y = 0; y < Y_Sequence.length() + 1; y++) {
                String formatedCell = numberCell(scoreMatrix[x][y], nf);
                String coloredCell;
                if (x==0 || y==0)
                    coloredCell = gapColor + formatedCell + clearColor;
                else {
                    if (X_Sequence.charAt(x-1) == Y_Sequence.charAt(y - 1)){
                        coloredCell = match + formatedCell + clearColor;
                    }else {
                        coloredCell = mismatch + formatedCell + clearColor;
                    }
                }
                sb.append(coloredCell);
            }
            sb.append('\n');
        }
        return sb.toString();
    }


    private static String yellowCell(String formatedCell){
        StringBuffer sb = new StringBuffer();
        sb.append("\033[33m").append(formatedCell).append("\033[0m");
        return  sb.toString();
    }

    private static String redCell(String formatedCell){
        StringBuffer sb = new StringBuffer();
        sb.append("\033[31m").append(formatedCell).append("\031[0m");
        return  sb.toString();
    }

    public static String printScoreMatrixFlipped(int[][] scoreMatrix) {
        StringBuffer sb = new StringBuffer();
        NumberFormat plusMinusNF = new DecimalFormat("#;-#");

        for (int y = 0; y < scoreMatrix[0].length; y++) {
            for (int x = 0; x < scoreMatrix.length; x++) {
                //String.format("%02d", scoreMatrix[x][y])
                String tmp = String.format("%5s", plusMinusNF.format(scoreMatrix[x][y]));
                sb.append(tmp);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
