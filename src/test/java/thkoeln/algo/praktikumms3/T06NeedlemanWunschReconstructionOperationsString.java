package thkoeln.algo.praktikumms3;

import org.junit.Assert;
import org.junit.Test;
import thkoeln.algo.praktikumms3.Alignment;
import thkoeln.algo.praktikumms3.NeedlemanWunsch;
import thkoeln.algo.praktikumms3.Scores;
import thkoeln.algo.praktikumms3.util.MatrixUtil;

public class T06NeedlemanWunschReconstructionOperationsString {

    /***
     *     ______                                __                    __
     *    |   __ \.-----.----.-----.-----.-----.|  |_.----.--.--.----.|  |_
     *    |      <|  -__|  __|  _  |     |__ --||   _|   _|  |  |  __||   _|
     *    |___|__||_____|____|_____|__|__|_____||____|__| |_____|____||____|
     */

    private void SequencesTest(String x, String y, String expected_X_Alignment, String expected_Y_Alignment, String expectedOperationsString, int expected_Score) {
        // When
        Alignment alignment = NeedlemanWunsch.align(x, y);
        System.out.println(alignment.toString());

        // Then
        Assert.assertEquals("Alignment X", expected_X_Alignment, alignment.getX_Alignment());
        Assert.assertEquals("Alignment Y", expected_Y_Alignment, alignment.getY_Alignment());
        Assert.assertEquals("OperationsString", expectedOperationsString, alignment.getOperationsString());
        Assert.assertEquals("Score", expected_Score, alignment.getScore());

    }

    private void SequencesTest(String x, String y, Scores scores, String expected_X_Alignment, String expected_Y_Alignment, String expectedOperationsString, int expected_Score) {
        // When
        Alignment alignment = NeedlemanWunsch.align(x, y, scores);
        System.out.println(alignment.toString());

        // Then
        Assert.assertEquals("Alignment X", expected_X_Alignment, alignment.getX_Alignment());
        Assert.assertEquals("Alignment Y", expected_Y_Alignment, alignment.getY_Alignment());
        Assert.assertEquals("OperationsString", expectedOperationsString, alignment.getOperationsString());

        Assert.assertEquals("Score", expected_Score, alignment.getScore());
    }

    @Test
    public void TestSequences3MisMatches_OperationString() {
        // Given
        String x = "AAA";
        String y = "BBB";

        String expectedX = "AAA";
        String expectedY = "BBB";
        String expected_OperationsString = "|||";
        int expectedScore = -3;

        // When
        SequencesTest(x, y, expectedX, expectedY, expected_OperationsString, expectedScore);
    }

    @Test
    public void TestSequencesWolframExample_OperationString() {
        // Given
        String x = "BANDMASTERS";
        String y = "TWINSET";

        String expected_X_Alignment = "_BANDMASTERS";
        String expected_Y_Alignment = "TWIN___S_E_T";
        String expected_OperationsString = "_||*___*_*_|";
        int expected_Score = -6;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_OperationsString, expected_Score);
    }

    @Test
    public void TestSequencesCenterMatch_OperationString() {
        // Given
        String x = "ABC";
        String y = "CBA";

        String expected_X_Alignment = "ABC";
        String expected_Y_Alignment = "CBA";
        String expected_OperationsString = "|*|";
        int expected_Score = -1;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_OperationsString, expected_Score);
    }

    @Test
    public void TestSequencesFirstSequenceLonger_OperationString() {
        // Given
        String x = "ABCDE";
        String y = "ABC";

        String expected_X_Alignment = "ABCDE";
        String expected_Y_Alignment = "ABC__";
        String expected_OperationsString = "***__";
        int expected_Score = 1;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_OperationsString, expected_Score);
    }


    @Test
    public void TestSequencesSecondSequenceLonger_OperationString() {
        // Given
        String x = "ABC";
        String y = "ABCDE";

        String expected_X_Alignment = "ABC__";
        String expected_Y_Alignment = "ABCDE";
        String expected_OperationsString = "***__";
        int expected_Score = 1;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_OperationsString, expected_Score);
    }

    @Test
    public void TestSequencesMiddleMatch_OperationString() {
        // Given
        String x = "ABCD";
        String y = "BCDE";

        String expected_X_Alignment = "ABCD_";
        String expected_Y_Alignment = "_BCDE";
        String expected_OperationsString = "_***_";
        int expected_Score = 1;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_OperationsString, expected_Score);
    }

    @Test
    public void TestSequences_OperationString() {
        // Given
        String x = "ATA";
        String y = "AGTA";

        String expected_X_Alignment = "A_TA";
        String expected_Y_Alignment = "AGTA";
        String expected_OperationsString = "*_**";
        int expected_Score = 2;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_OperationsString, expected_Score);
    }

    @Test
    public void TestSequencesFlipped_OperationString() {
        // Given
        String x = "AGTA";
        String y = "ATA";

        String expected_X_Alignment = "AGTA";
        String expected_Y_Alignment = "A_TA";
        String expected_OperationsString = "*_**";
        int expected_Score = 2;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_OperationsString, expected_Score);
    }

    @Test
    public void TestSequences2_OperationString() {
        // Given
        String x = "ABACAABCA";
        String y = "ABABAABCB";

        String expected_X_Alignment = "ABACAABCA";
        String expected_Y_Alignment = "ABABAABCB";
        String expected_OperationsString = "***|****|";
        int expected_Score = 5;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_OperationsString, expected_Score);
    }


    @Test
    public void TestSequencesAufgabenstellung_OperationString() {
        // Given
        String x = "ACCGGTCGAGTGCGCGGAAGCCGGCCGA";
        String y = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";

        String expected_X_Alignment = "ACCGGTC_GAGTGCGCGGAAGC_CGGCCGA";
        String expected_Y_Alignment = "GTCGTTCGGAATGC_C_GTTGCTCTGTAAA";
        String expected_OperationsString = "||**|**_**|***_*_*||**_*|*|||*";
        int expected_Score = 2;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_OperationsString, expected_Score);
    }

    @Test
    public void TestSequencesBeispiel2Folie20_OperationString() {
        // Given
        String x = "MAEHTE";
        String y = "AEHREN";

        String expected_X_Alignment = "MAEHTE_";
        String expected_Y_Alignment = "_AEHREN";
        String expected_OperationsString = "_***|*_";
        int expected_Score = 10;

        Scores scores = new Scores(3, 0, -1);
        SequencesTest(x, y, scores, expected_X_Alignment, expected_Y_Alignment, expected_OperationsString, expected_Score);
    }
}
