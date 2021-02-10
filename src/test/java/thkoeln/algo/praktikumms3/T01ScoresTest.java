package thkoeln.algo.praktikumms3;

import org.junit.Assert;
import org.junit.Test;
import thkoeln.algo.praktikumms3.Scores;

public class T01ScoresTest {

    /*************   Constructors  *************/
    @Test
    public void Constructor_DefaultScores_Test() {
        // Given

        // When
        Scores scores = new Scores();

        // Then
        Assert.assertEquals("MATCH-Score",1, scores.MATCH);
        Assert.assertEquals("MISMATCH-Score",-1, scores.MISMATCH);
        Assert.assertEquals("Gap-Penalty",-1, scores.GAP);
    }

    @Test
    public void Constructor_CustomScores_Test() {
        // Given
        int MATCH_SCORE = 3;
        int MISMATCH_SCORE = -3;
        int GAP_PENALTY = -5;

        // When
        Scores scores = new Scores(MATCH_SCORE, MISMATCH_SCORE, GAP_PENALTY);

        // Then
        Assert.assertEquals("Custom MATCH-Score",MATCH_SCORE, scores.MATCH);
        Assert.assertEquals("Custom MISMATCH-Score",MISMATCH_SCORE, scores.MISMATCH);
        Assert.assertEquals("Custom Gap-Penalty",GAP_PENALTY, scores.GAP);
    }

    /*************   WEIGHT  *************/
    @Test
    public void weight_DefaultScores_Test() {
        // Given
        int MATCH_SCORE = 1;
        int MISMATCH_SCORE = -1;
        int GAP_PENALTY = -1;

        char GAP_CHAR = '_';
        char A = 'A';
        char B = 'B';

        Scores scores = new Scores();

        // When
        int scoreAA = scores.weight(A, A, GAP_CHAR);
        int scoreAB = scores.weight(A, B, GAP_CHAR);
        int scoreA_ = scores.weight(A, GAP_CHAR, GAP_CHAR);
        int score_A = scores.weight(GAP_CHAR, A, GAP_CHAR);

        // Then
        Assert.assertEquals("Weight for A,A --> MATCH should be MATCH_SCORE",MATCH_SCORE, scoreAA);
        Assert.assertEquals("Weight for A,B --> MISMATCH should be MISMATCH-SCORE",MISMATCH_SCORE, scoreAB);
        Assert.assertEquals("Weight for A,_ --> GAP hould be GAP_PENALTY",GAP_PENALTY, scoreA_);
        Assert.assertEquals("Weight for _,A --> GAP hould be GAP_PENALTY",GAP_PENALTY, score_A);
    }

    @Test
    public void weight_CustomScores_Test() {
        // Given
        int MATCH_SCORE = 3;
        int MISMATCH_SCORE = -5;
        int GAP_PENALTY = -8;

        char GAP_CHAR = '_';
        char A = 'A';
        char B = 'B';

        Scores scores = new Scores(MATCH_SCORE, MISMATCH_SCORE, GAP_PENALTY);

        // When
        int scoreAA = scores.weight(A, A, GAP_CHAR);
        int scoreAB = scores.weight(A, B, GAP_CHAR);
        int scoreA_ = scores.weight(A, GAP_CHAR, GAP_CHAR);
        int score_A = scores.weight(GAP_CHAR, A, GAP_CHAR);

        // Then
        Assert.assertEquals("Weight for A,A --> MATCH should be MATCH_SCORE",MATCH_SCORE, scoreAA);
        Assert.assertEquals("Weight for A,B --> MISMATCH should be MISMATCH-SCORE",MISMATCH_SCORE, scoreAB);
        Assert.assertEquals("Weight for A,_ --> GAP hould be GAP_PENALTY",GAP_PENALTY, scoreA_);
        Assert.assertEquals("Weight for _,A --> GAP hould be GAP_PENALTY",GAP_PENALTY, score_A);
    }


    /*************   EQUAL  *************/
    @Test
    public void equal_DefaultScores_Test() {
        // Given
        int MATCH_SCORE = 1;
        int MISMATCH_SCORE = -1;
        int GAP_PENALTY = -1;

        char A = 'A';
        char B = 'B';

        Scores scores = new Scores();

        // When
        int scoreAA = scores.equal(A, A);
        int scoreAB = scores.equal(A, B);

        // Then
        Assert.assertEquals("Equal for A,A --> MATCH should be MATCH-SCORE", MATCH_SCORE, scoreAA);
        Assert.assertEquals("Equal for A,B --> MISMATCH should be MISMATCH-SCORE", MISMATCH_SCORE, scoreAB);
    }

    @Test
    public void equal_CustomScores_Test() {
        // Given
        int MATCH_SCORE = 3;
        int MISMATCH_SCORE = -5;
        int GAP_PENALTY = -8;

        char A = 'A';
        char B = 'B';

        Scores scores = new Scores(MATCH_SCORE, MISMATCH_SCORE, GAP_PENALTY);

        // When
        int scoreAA = scores.equal(A, A);
        int scoreAB = scores.equal(A, B);

        // Then
        Assert.assertEquals("Equal for A,A --> MATCH should be MATCH-SCORE", MATCH_SCORE, scoreAA);
        Assert.assertEquals("Equal for A,B --> MISMATCH should be MISMATCH-SCORE", MISMATCH_SCORE, scoreAB);
    }

}
