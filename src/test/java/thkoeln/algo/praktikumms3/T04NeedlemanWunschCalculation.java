package thkoeln.algo.praktikumms3;

import org.junit.Assert;
import org.junit.Test;
import thkoeln.algo.praktikumms3.Alignment;
import thkoeln.algo.praktikumms3.NeedlemanWunsch;
import thkoeln.algo.praktikumms3.Scores;
import thkoeln.algo.praktikumms3.util.MatrixUtil;

public class T04NeedlemanWunschCalculation {

    /***
     *     ______         __              __         __
     *    |      |.---.-.|  |.----.--.--.|  |.---.-.|  |_.-----.
     *    |   ---||  _  ||  ||  __|  |  ||  ||  _  ||   _|  -__|
     *    |______||___._||__||____|_____||__||___._||____|_____|
     */
    @Test
    public void calculateAlignmentScoreMatrix_DefaultScores_Test() {
        // Given
        String S1 = "ACCGGTCGAGTGCGCGGAAGCCGGCCGA";
        String S2 = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";

        // When
        Alignment alignment = new Alignment(S1, S2);
        alignment = NeedlemanWunsch.initialize(alignment);
        alignment = NeedlemanWunsch.calculate(alignment);
        int[][] calculatedScoreMatrix = alignment.getScoreMatrix();

        // Then
        int[][] expected_calculated_Scorematrix = {
                {    0,   -1,   -2,   -3,   -4,   -5,   -6,   -7,   -8,   -9,  -10,  -11,  -12,  -13,  -14,  -15,  -16,  -17,  -18,  -19,  -20,  -21,  -22,  -23,  -24,  -25,  -26,  -27,  -28 },
                {   -1,   -1,   -2,   -3,   -4,   -5,   -6,   -7,   -8,   -9,   -8,   -9,  -10,  -11,  -12,  -13,  -14,  -15,  -16,  -17,  -18,  -19,  -20,  -21,  -22,  -23,  -24,  -25,  -26 },
                {   -2,   -2,   -2,   -1,   -2,   -3,   -4,   -5,   -6,   -7,   -8,   -9,  -10,  -11,  -10,  -11,  -12,  -13,  -14,  -15,  -16,  -17,  -18,  -19,  -20,  -21,  -22,  -23,  -24 },
                {   -3,   -3,   -3,   -1,   -2,   -3,   -4,   -3,   -4,   -5,   -6,   -7,   -8,   -9,  -10,   -9,  -10,  -11,  -12,  -13,  -14,  -15,  -16,  -17,  -18,  -19,  -20,  -21,  -22 },
                {   -4,   -2,   -3,   -2,    0,   -1,   -2,   -3,   -2,   -3,   -4,   -5,   -6,   -7,   -8,   -9,   -8,   -9,  -10,  -11,  -12,  -13,  -14,  -15,  -16,  -17,  -18,  -19,  -20 },
                {   -5,   -3,   -3,   -3,   -1,   -1,   -2,   -3,   -2,   -1,   -2,   -3,   -4,   -5,   -6,   -7,   -8,   -9,  -10,   -9,  -10,  -11,  -12,  -13,  -14,  -15,  -16,  -17,  -18 },
                {   -6,   -4,   -2,   -3,   -2,    0,    0,   -1,   -2,   -2,   -2,   -3,   -2,   -3,   -4,   -5,   -6,   -7,   -8,   -9,  -10,   -9,  -10,  -11,  -12,  -13,  -14,  -15,  -16 },
                {   -7,   -5,   -3,   -1,   -2,   -1,   -1,    1,    0,   -1,   -2,   -3,   -3,   -3,   -2,   -3,   -4,   -5,   -6,   -7,   -8,   -9,   -8,   -9,  -10,  -11,  -12,  -13,  -14 },
                {   -8,   -6,   -4,   -2,    0,   -1,   -2,    0,    2,    1,    0,   -1,   -2,   -2,   -3,   -3,   -2,   -3,   -4,   -5,   -6,   -7,   -8,   -9,   -8,   -9,  -10,  -11,  -12 },
                {   -9,   -7,   -5,   -3,   -1,   -1,   -2,   -1,    1,    1,    2,    1,    0,   -1,   -2,   -3,   -3,   -3,   -4,   -5,   -6,   -7,   -8,   -9,   -9,   -9,   -8,   -9,  -10 },
                {  -10,   -8,   -6,   -4,   -2,   -2,   -2,   -2,    0,    2,    1,    1,    0,    1,    0,   -1,   -2,   -3,   -4,   -3,   -4,   -5,   -6,   -7,   -8,   -9,   -9,   -9,  -10 },
                {  -11,   -9,   -7,   -5,   -3,   -1,   -1,   -2,   -1,    1,    1,    0,    2,    1,    0,   -1,   -2,   -1,   -2,   -3,   -4,   -3,   -4,   -5,   -6,   -7,   -8,   -9,  -10 },
                {  -12,  -10,   -8,   -6,   -4,   -2,   -2,   -2,   -1,    0,    0,    0,    1,    3,    2,    1,    0,   -1,   -2,   -1,   -2,   -3,   -4,   -5,   -4,   -5,   -6,   -7,   -8 },
                {  -13,  -11,   -9,   -7,   -5,   -3,   -3,   -1,   -2,   -1,   -1,   -1,    0,    2,    4,    3,    2,    1,    0,   -1,    0,   -1,   -2,   -3,   -4,   -5,   -6,   -7,   -8 },
                {  -14,  -12,  -10,   -8,   -6,   -4,   -4,   -2,    0,   -1,   -2,   -2,   -1,    1,    3,    3,    4,    3,    2,    1,    0,   -1,   -2,   -3,   -2,   -3,   -4,   -5,   -6 },
                {  -15,  -13,  -11,   -9,   -7,   -5,   -5,   -3,   -1,   -1,   -2,   -3,   -2,    0,    2,    4,    3,    3,    2,    1,    2,    1,    0,   -1,   -2,   -3,   -4,   -5,   -6 },
                {  -16,  -14,  -12,  -10,   -8,   -6,   -6,   -4,   -2,    0,   -1,   -2,   -3,   -1,    1,    3,    5,    4,    3,    3,    2,    1,    0,   -1,    0,   -1,   -2,   -3,   -4 },
                {  -17,  -15,  -13,  -11,   -9,   -7,   -7,   -5,   -3,   -1,   -1,   -2,   -3,   -2,    0,    2,    4,    4,    3,    4,    3,    2,    1,    0,    0,   -1,   -2,   -3,   -4 },
                {  -18,  -16,  -14,  -12,  -10,   -8,   -8,   -6,   -4,   -2,    0,    0,   -1,   -2,   -1,    1,    3,    3,    3,    3,    3,    2,    1,    0,   -1,   -1,    0,   -1,   -2 },
                {  -19,  -17,  -15,  -13,  -11,   -9,   -9,   -7,   -5,   -3,   -1,    1,    0,   -1,   -2,    0,    2,    2,    2,    2,    2,    2,    1,    0,   -1,   -2,    0,    1,    0 },
                {  -20,  -18,  -16,  -14,  -12,  -10,  -10,   -8,   -6,   -4,   -2,    0,    0,    1,    0,   -1,    1,    1,    1,    3,    2,    1,    1,    0,    1,    0,   -1,    0,    0 },
                {  -21,  -19,  -17,  -15,  -13,  -11,  -11,   -9,   -7,   -5,   -3,   -1,   -1,    0,    2,    1,    0,    0,    0,    2,    4,    3,    2,    1,    0,    0,   -1,   -1,   -1 },
                {  -22,  -20,  -18,  -16,  -14,  -12,  -12,  -10,   -8,   -6,   -4,   -2,   -2,   -1,    1,    3,    2,    1,    0,    1,    3,    3,    4,    3,    2,    1,    0,   -1,   -2 },
                {  -23,  -21,  -19,  -17,  -15,  -13,  -13,  -11,   -9,   -7,   -5,   -3,   -3,   -1,    0,    2,    4,    3,    2,    1,    2,    2,    3,    3,    4,    3,    2,    1,    0 },
                {  -24,  -22,  -20,  -18,  -16,  -14,  -14,  -12,  -10,   -8,   -6,   -4,   -4,   -2,   -1,    1,    3,    3,    2,    3,    2,    1,    2,    2,    4,    3,    2,    1,    0 },
                {  -25,  -23,  -21,  -19,  -17,  -15,  -15,  -13,  -11,   -9,   -7,   -5,   -5,   -3,   -1,    0,    2,    2,    2,    2,    4,    3,    2,    1,    3,    3,    2,    1,    0 },
                {  -26,  -24,  -22,  -20,  -18,  -16,  -16,  -14,  -12,  -10,   -8,   -6,   -6,   -4,   -2,    0,    1,    1,    1,    1,    3,    3,    4,    3,    2,    2,    2,    1,    0 },
                {  -27,  -25,  -23,  -21,  -19,  -17,  -17,  -15,  -13,  -11,   -9,   -7,   -7,   -5,   -3,   -1,    1,    0,    0,    2,    2,    2,    3,    3,    4,    3,    2,    1,    0 },
                {  -28,  -26,  -24,  -22,  -20,  -18,  -18,  -16,  -14,  -12,  -10,   -8,   -8,   -6,   -4,   -2,    0,    0,   -1,    1,    1,    1,    2,    2,    3,    3,    4,    3,    2 }
        };

        System.out.println("Expected:");
        System.out.println(MatrixUtil.printScoreMatrix(expected_calculated_Scorematrix));
        System.out.println("\nActual:");
        System.out.println(MatrixUtil.printScoreMatrix(calculatedScoreMatrix));

        Assert.assertEquals("initialized with correct dimensions",
                expected_calculated_Scorematrix.length, expected_calculated_Scorematrix.length);
        Assert.assertEquals("initialized with correct dimensions",
                expected_calculated_Scorematrix[0].length, expected_calculated_Scorematrix[0].length);

        boolean matricesAreEqual = MatrixUtil.MatrixEquals(expected_calculated_Scorematrix, calculatedScoreMatrix);
        Assert.assertTrue("Matrices arent equal!",matricesAreEqual);
    }

    @Test
    public void calculateAlignmentScoreMatrix_EmptyStrings_Test() {
        // Given
        String S1 = "";
        String S2 = "";

        // When
        Alignment alignment = new Alignment(S1, S2);
        alignment = NeedlemanWunsch.initialize(alignment);
        alignment = NeedlemanWunsch.calculate(alignment);
        int[][] calculatedScoreMatrix = alignment.getScoreMatrix();

        // Then
        int[][] expected_calculated_SoreMatrix = {
                {    0 }
        };

        System.out.println("Expected:");
        System.out.println(MatrixUtil.printScoreMatrix(expected_calculated_SoreMatrix));
        System.out.println("\nActual:");
        System.out.println(MatrixUtil.printScoreMatrix(calculatedScoreMatrix));

        Assert.assertEquals("initialized with correct dimensions",
                0, calculatedScoreMatrix[0][0]);
        Assert.assertEquals("initialized with correct dimensions",
                expected_calculated_SoreMatrix.length, expected_calculated_SoreMatrix.length);
        Assert.assertEquals("initialized with correct dimensions",
                expected_calculated_SoreMatrix[0].length, expected_calculated_SoreMatrix[0].length);

        boolean matricesAreEqual = MatrixUtil.MatrixEquals(expected_calculated_SoreMatrix, calculatedScoreMatrix);
        Assert.assertTrue("Matrices arent equal!",matricesAreEqual);

    }

    @Test
    public void calculateAlignmentScoreMatrix_CustomScores_Test() {
        // Given
        String S1 = "AAA";
        String S2 = "BB";

        int MATCH_SCORE = 3;
        int MISMATCH_SCORE = -3;
        int GAP_PENALTY = -4;

        Scores scores = new Scores(MATCH_SCORE, MISMATCH_SCORE, GAP_PENALTY);

        // When
        Alignment alignment = new Alignment(S1, S2, scores);
        alignment = NeedlemanWunsch.initialize(alignment);
        alignment = NeedlemanWunsch.calculate(alignment);
        int[][] calculatedScoreMatrix = alignment.getScoreMatrix();

        // Then
        int[][] expected_calculated_SoreMatrix = {
                {    0,   -4,   -8 },
                {   -4,   -3,   -7 },
                {   -8,   -7,   -6 },
                {  -12,  -11,  -10 }
        };

        System.out.println("Expected:");
        System.out.println(MatrixUtil.printScoreMatrix(expected_calculated_SoreMatrix));
        System.out.println("\nActual:");
        System.out.println(MatrixUtil.printScoreMatrix(calculatedScoreMatrix));

        Assert.assertEquals("initialized with correct dimensions",
                0, calculatedScoreMatrix[0][0]);
        Assert.assertEquals("initialized with correct dimensions",
                expected_calculated_SoreMatrix.length, expected_calculated_SoreMatrix.length);
        Assert.assertEquals("initialized with correct dimensions",
                expected_calculated_SoreMatrix[0].length, expected_calculated_SoreMatrix[0].length);

        boolean matricesAreEqual = MatrixUtil.MatrixEquals(expected_calculated_SoreMatrix, calculatedScoreMatrix);
        Assert.assertTrue("Matrices arent equal!",matricesAreEqual);
    }
}
