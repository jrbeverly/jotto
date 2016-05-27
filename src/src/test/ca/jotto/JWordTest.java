package test.ca.jotto;

import ca.jotto.JWord;
import junit.framework.Assert;
import org.junit.Test;

public class JWordTest {

    @Test
    public void get() throws Exception {
        String name = "Sample";
        int difficult = 1;

        JWord word = new JWord(name, difficult);

        Assert.assertEquals(name, word.getWord());
        Assert.assertEquals(difficult, word.getDifficulty());
    }

    @Test
    public void getWordIterative() throws Exception {
        int difficult = 1;
        String[] names = new String[]{
                "Hello",
                "World",
                "System"
        };

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            JWord word = new JWord(name, difficult);

            Assert.assertEquals(name, word.getWord());
        }
    }

    @Test
    public void getDifficultyIterative() throws Exception {
        String name = "Sample";

        for (int difficult = 0; difficult < 10; difficult++) {
            JWord word = new JWord(name, difficult);
            Assert.assertEquals(difficult, word.getDifficulty());
        }
    }
}