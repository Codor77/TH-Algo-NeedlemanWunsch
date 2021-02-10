package thkoeln.algo.praktikumms3;

import org.junit.Assert;
import org.junit.Test;
import thkoeln.algo.praktikumms3.Alignment;
import thkoeln.algo.praktikumms3.NeedlemanWunsch;
import thkoeln.algo.praktikumms3.Scores;
import thkoeln.algo.praktikumms3.util.MatrixUtil;

@SuppressWarnings("ConstantConditions")
public class T03NeedlemanWunschInitialisation {

    /***
     *     _______         __ __   __         __ __
     *    |_     _|.-----.|__|  |_|__|.---.-.|  |__|.-----.-----.
     *     _|   |_ |     ||  |   _|  ||  _  ||  |  ||-- __|  -__|
     *    |_______||__|__||__|____|__||___._||__|__||_____|_____|
     */
    @Test
    public void initializeAlignmentScoreMatrix_DefaultScores_Test() {
        // Given
        String S1 = "AAA";
        String S2 = "BB";

        // When
        Alignment alignment = new Alignment(S1, S2);
        alignment = NeedlemanWunsch.initialize(alignment);

        int[][] initialized_ScoreMatrix = alignment.getScoreMatrix();

        // Then
        int[][] expected_initialized_Scorematrix = {
                {0, -1, -2},
                {-1, 0, 0},
                {-2, 0, 0},
                {-3, 0, 0}
        };


        System.out.println("Exprected:");
        System.out.println(MatrixUtil.printScoreMatrix(expected_initialized_Scorematrix));
        System.out.println("\nActual:");
        System.out.println(MatrixUtil.printScoreMatrix(initialized_ScoreMatrix));

        Assert.assertEquals("initialized with correct dimensions",
                expected_initialized_Scorematrix.length, expected_initialized_Scorematrix.length);
        Assert.assertEquals("initialized with correct dimensions",
                expected_initialized_Scorematrix[0].length, expected_initialized_Scorematrix[0].length);

        boolean matricesAreEqual = MatrixUtil.MatrixEquals(expected_initialized_Scorematrix, initialized_ScoreMatrix);
        Assert.assertTrue("Matrices arent equal!",matricesAreEqual);
    }

    @Test
    public void initializeAlignmentScoreMatrix_EmptyStrings_Test() {
        // Given
        String S1 = "";
        String S2 = "";

        Scores scores = new Scores();

        // When
        Alignment alignment = new Alignment(S1, S2);
        alignment = NeedlemanWunsch.initialize(alignment);
        int[][] initialized_ScoreMatrix = alignment.getScoreMatrix();

        // Then
        int[][] expected_initialized_Scorematrix = {
                {    0 }
        };

        System.out.println("Exprected:");
        System.out.println(MatrixUtil.printScoreMatrix(expected_initialized_Scorematrix));
        System.out.println("\nActual:");
        System.out.println(MatrixUtil.printScoreMatrix(initialized_ScoreMatrix));

        Assert.assertEquals("initialized with correct dimensions",
                0, initialized_ScoreMatrix[0][0]);
        Assert.assertEquals("initialized with correct dimensions",
                expected_initialized_Scorematrix.length, expected_initialized_Scorematrix.length);
        Assert.assertEquals("initialized with correct dimensions",
                expected_initialized_Scorematrix[0].length, expected_initialized_Scorematrix[0].length);

        boolean matricesAreEqual = MatrixUtil.MatrixEquals(expected_initialized_Scorematrix, initialized_ScoreMatrix);
        Assert.assertTrue("Matrices arent equal!",matricesAreEqual);

    }

    @Test
    public void initializeAlignmentScoreMatrix_CustomScores_Test() {
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
        int[][] initialized_ScoreMatrix = alignment.getScoreMatrix();

        // Then
        int[][] expected_initialized_Scorematrix = {
                {    0,   -4,   -8 },
                {   -4,    0,    0 },
                {   -8,    0,    0 },
                {  -12,    0,    0 }
        };


        System.out.println("Expected:");
        System.out.println(MatrixUtil.printScoreMatrix(expected_initialized_Scorematrix));
        System.out.println("\nActual:");
        System.out.println(MatrixUtil.printScoreMatrix(initialized_ScoreMatrix));

        Assert.assertEquals("initialized with correct dimensions",
                0, initialized_ScoreMatrix[0][0]);
        Assert.assertEquals("initialized with correct dimensions",
                expected_initialized_Scorematrix.length, expected_initialized_Scorematrix.length);
        Assert.assertEquals("initialized with correct dimensions",
                expected_initialized_Scorematrix[0].length, expected_initialized_Scorematrix[0].length);

        boolean matricesAreEqual = MatrixUtil.MatrixEquals(expected_initialized_Scorematrix, initialized_ScoreMatrix);
        Assert.assertTrue("Matrices arent equal!",matricesAreEqual);
    }
}
