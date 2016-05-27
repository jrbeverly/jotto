package test.ca.jotto.application;

import ca.jotto.application.JDifficulty;
import org.junit.Assert;
import org.junit.Test;

public class JDifficultyTest {
    @Test
    public void order() throws Exception {
        Assert.assertTrue(JDifficulty.Easy.getLevel() < JDifficulty.Normal.getLevel());
        Assert.assertTrue(JDifficulty.Normal.getLevel() < JDifficulty.Hard.getLevel());
        Assert.assertTrue(JDifficulty.Easy.getLevel() < JDifficulty.Hard.getLevel());
    }

}