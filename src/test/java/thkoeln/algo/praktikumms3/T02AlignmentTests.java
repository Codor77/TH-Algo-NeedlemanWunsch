package thkoeln.algo.praktikumms3;

import org.junit.Assert;
import org.junit.Test;
import thkoeln.algo.praktikumms3.Alignment;
import thkoeln.algo.praktikumms3.Scores;
import thkoeln.algo.praktikumms3.util.MatrixUtil;

public class T02AlignmentTests {


    @Test
    public void Alignment_DefaultConstructor_Getter_Tests() {
        // Given

        // When
        Alignment alignment = new Alignment();

        // Then
        Assert.assertNotNull(alignment.getX_Sequence());
        Assert.assertNotNull(alignment.getY_Sequence());
        Assert.assertNotNull(alignment.getScoreSettings());
        Assert.assertNotNull(alignment.getX_Alignment());
        Assert.assertNotNull(alignment.getY_Alignment());
        Assert.assertNotNull(alignment.getOperationsString());
        Assert.assertNotNull(alignment.getScoreMatrix());
        Assert.assertEquals(0, alignment.getScoreMatrix().length);
    }

    @Test
    public void Alignment_SequenceConstructor_Getter_Tests() {
        // Given
        String X_Sequence = "ABCD";
        String Y_Sequence = "BCDE";

        // When
        Alignment alignment = new Alignment(X_Sequence, Y_Sequence);

        // Then
        Assert.assertEquals(X_Sequence, alignment.getX_Sequence());
        Assert.assertEquals(Y_Sequence, alignment.getY_Sequence());
        Assert.assertNotNull(alignment.getScoreSettings());
        Assert.assertNotNull(alignment.getX_Alignment());
        Assert.assertNotNull(alignment.getY_Alignment());
        Assert.assertNotNull(alignment.getOperationsString());
        Assert.assertNotNull(alignment.getScoreMatrix());
        Assert.assertEquals(5, alignment.getScoreMatrix().length);
        Assert.assertEquals(5, alignment.getScoreMatrix()[0].length);
        Assert.assertEquals(0, alignment.getScore());
    }

    @Test
    public void Alignment_GetterSetter_Tests() {
        // Given
        String X_Sequence = "ABCD";
        String Y_Sequence = "BCDE";

        Scores scores = new Scores();

        char GAP_CHAR = '_';
        String X_Alignment = "ABCD_";
        String Y_Alignment = "_BCDE";

        String operationsString = "_***_";

        int expectedScore = 1;

        int[][] scoreMatrix = {
                {    0,   -1,   -2,   -3,   -4 },
                {   -1,   -1,   -2,   -3,   -4 },
                {   -2,    0,   -1,   -2,   -3 },
                {   -3,   -1,    1,    0,   -1 },
                {   -4,   -2,    0,    2,    1 }
        };

        // When
        Alignment alignment = new Alignment();
        alignment.setX_Sequence(X_Sequence);
        alignment.setY_Sequence(Y_Sequence);
        alignment.setScoreSettings(scores);
        alignment.setX_Alignment(X_Alignment);
        alignment.setY_Alignment(Y_Alignment);
        alignment.setOperationsString(operationsString);
        alignment.setScoreMatrix(scoreMatrix);
        alignment.setGapCharacter(GAP_CHAR);

        // Then
        Assert.assertEquals(X_Sequence, alignment.getX_Sequence());
        Assert.assertEquals(Y_Sequence, alignment.getY_Sequence());
        Assert.assertEquals(scores, alignment.getScoreSettings());
        Assert.assertEquals(X_Alignment, alignment.getX_Alignment());
        Assert.assertEquals(Y_Alignment, alignment.getY_Alignment());
        Assert.assertEquals(operationsString, alignment.getOperationsString());
        Assert.assertTrue(MatrixUtil.MatrixEquals(scoreMatrix, alignment.getScoreMatrix()));
        Assert.assertEquals(5, alignment.getScoreMatrix().length);
        Assert.assertEquals(5, alignment.getScoreMatrix()[0].length);
        Assert.assertEquals(expectedScore, alignment.getScore());
        Assert.assertEquals(GAP_CHAR, alignment.getGapCharacter());
    }
}
