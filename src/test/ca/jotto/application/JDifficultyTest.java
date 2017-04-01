package ca.jotto.application;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JDifficultyTest {

    @Test
    public void order() {
        assertTrue(JDifficulty.Easy.getLevel() < JDifficulty.Normal.getLevel());
        assertTrue(JDifficulty.Normal.getLevel() < JDifficulty.Hard.getLevel());
        assertTrue(JDifficulty.Easy.getLevel() < JDifficulty.Hard.getLevel());
    }
}