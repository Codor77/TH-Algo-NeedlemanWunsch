package thkoeln.algo.praktikumms3;

import org.junit.Assert;
import org.junit.Test;
import thkoeln.algo.praktikumms3.Alignment;
import thkoeln.algo.praktikumms3.NeedlemanWunsch;
import thkoeln.algo.praktikumms3.Scores;
import thkoeln.algo.praktikumms3.util.MatrixUtil;

public class T07NeedlemanWunschAlignGivenAlignment {

    private void SequencesTest(String x, String y, String expected_X_Alignment, String expected_Y_Alignment, int expected_Score) {
        // When
        Alignment alignment = new Alignment();
        alignment.setX_Sequence(x);
        alignment.setY_Sequence(y);

        alignment = NeedlemanWunsch.align(alignment);
        System.out.println(alignment.toString());

        // Then
        Assert.assertEquals("Alignment X", expected_X_Alignment, alignment.getX_Alignment());
        Assert.assertEquals("Alignment Y", expected_Y_Alignment, alignment.getY_Alignment());
        Assert.assertEquals("Score", expected_Score, alignment.getScore());

        System.out.println(MatrixUtil.printScoreMatrixForTests(alignment.getScoreMatrix()));
    }

    private void SequencesTest(String x, String y, Scores scores, String expected_X_Alignment, String expected_Y_Alignment, int expected_Score) {
        // When
        Alignment alignment = new Alignment();
        alignment.setX_Sequence(x);
        alignment.setY_Sequence(y);
        alignment.setScoreSettings(scores);

        alignment = NeedlemanWunsch.align(alignment);
        System.out.println(alignment.toString());

        // Then
        Assert.assertEquals("Alignment X", expected_X_Alignment, alignment.getX_Alignment());
        Assert.assertEquals("Alignment Y", expected_Y_Alignment, alignment.getY_Alignment());
        Assert.assertEquals("Score", expected_Score, alignment.getScore());
    }

    @Test
    public void TestSequences3MisMatches() {
        // Given
        String x = "AAA";
        String y = "BBB";

        String expectedX = "AAA";
        String expectedY = "BBB";
        int expectedScore = -3;

        // When
        SequencesTest(x, y, expectedX, expectedY, expectedScore);
    }

    @Test
    public void TestSequencesWolframExample() {
        // Given
        String x = "BANDMASTERS";
        String y = "TWINSET";

        String expected_X_Alignment = "_BANDMASTERS";
        String expected_Y_Alignment = "TWIN___S_E_T";
        int expected_Score = -6;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_Score);
    }

    @Test
    public void TestSequencesCenterMatch() {
        // Given
        String x = "ABC";
        String y = "CBA";

        String expected_X_Alignment = "ABC";
        String expected_Y_Alignment = "CBA";
        int expected_Score = -1;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_Score);
    }

    @Test
    public void TestSequencesFirstSequenceLonger() {
        // Given
        String x = "ABCDE";
        String y = "ABC";

        String expected_X_Alignment = "ABCDE";
        String expected_Y_Alignment = "ABC__";
        int expected_Score = 1;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_Score);
    }


    @Test
    public void TestSequencesSecondSequenceLonger() {
        // Given
        String x = "ABC";
        String y = "ABCDE";

        String expected_X_Alignment = "ABC__";
        String expected_Y_Alignment = "ABCDE";
        int expected_Score = 1;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_Score);
    }

    @Test
    public void TestSequencesMiddleMatch() {
        // Given
        String x = "ABCD";
        String y = "BCDE";

        String expected_X_Alignment = "ABCD_";
        String expected_Y_Alignment = "_BCDE";
        int expected_Score = 1;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_Score);
    }

    @Test
    public void TestSequences() {
        // Given
        String x = "ATA";
        String y = "AGTA";

        String expected_X_Alignment = "A_TA";
        String expected_Y_Alignment = "AGTA";
        int expected_Score = 2;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_Score);
    }

    @Test
    public void TestSequencesFlipped() {
        // Given
        String x = "AGTA";
        String y = "ATA";

        String expected_X_Alignment = "AGTA";
        String expected_Y_Alignment = "A_TA";
        int expected_Score = 2;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_Score);
    }

    @Test
    public void TestSequences2() {
        // Given
        String x = "ABACAABCA";
        String y = "ABABAABCB";

        String expected_X_Alignment = "ABACAABCA";
        String expected_Y_Alignment = "ABABAABCB";
        int expected_Score = 5;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_Score);
    }


    @Test
    public void TestSequencesAufgabenstellung() {
        // Given
        String x = "ACCGGTCGAGTGCGCGGAAGCCGGCCGA";
        String y = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";

        String expected_X_Alignment = "ACCGGTC_GAGTGCGCGGAAGC_CGGCCGA";
        String expected_Y_Alignment = "GTCGTTCGGAATGC_C_GTTGCTCTGTAAA";
        String expected_OperationsString = "||**|**_**|***_*_*||**_*|*|||*";
        int expected_Score = 2;

        SequencesTest(x, y, expected_X_Alignment, expected_Y_Alignment, expected_Score);
    }

    @Test
    public void TestSequencesBeispiel2Folie20() {
        // Given
        String x = "MAEHTE";
        String y = "AEHREN";

        String expected_X_Alignment = "MAEHTE_";
        String expected_Y_Alignment = "_AEHREN";
        int expected_Score = 10;

        Scores scores = new Scores(3,0,-1);
        SequencesTest(x, y, scores, expected_X_Alignment, expected_Y_Alignment, expected_Score);
    }
}
