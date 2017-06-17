package ca.jotto.model;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;

public class JWordTest {

    @Category(ValidationTests.class)
    @Test
    public void simple() throws Exception {
        String name = "OTHER";
        int difficult = 3;

        JWord word = new JWord(name, difficult);

        assertEquals(name, word.word());
        assertEquals(name.length(), word.length());
        assertEquals(difficult, word.difficulty());
    }

    @Category(ValidationTests.class)
    @Test
    public void constructor_zero() throws Exception {
        String name = "SAMPLE";
        int difficult = 0;

        JWord word = new JWord(name, difficult);

        assertEquals(name, word.word());
        assertEquals(name.length(), word.length());
        assertEquals(difficult, word.difficulty());
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_null() throws Exception {
        String name = null;
        int difficult = 1;

        new JWord(name, difficult);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_empty() throws Exception {
        String name = "";
        int difficult = 1;

        new JWord(name, difficult);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_negative() throws Exception {
        String name = "OTHER";
        int difficult = -1;

        new JWord(name, difficult);
    }

    @Category(ValidationTests.class)
    @Test
    public void word_iterative() throws Exception {
        int difficult = 1;
        String[] names = new String[]{
                "HELLO",
                "WORLD",
                "OTHER"
        };

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            JWord word = new JWord(name, difficult);

            assertEquals(name, word.word());
            assertEquals(name.length(), word.length());
            assertEquals(difficult, word.difficulty());
        }
    }

    @Category(ValidationTests.class)
    @Test
    public void difficulty_iterative() throws Exception {
        String name = "WORLD";

        for (int difficult = 0; difficult < 10; difficult++) {
            JWord word = new JWord(name, difficult);

            assertEquals(name, word.word());
            assertEquals(name.length(), word.length());
            assertEquals(difficult, word.difficulty());
        }
    }

    @Category(ValidationTests.class)
    @Test
    public void length_iterative() throws Exception {
        String name = "";

        for (int length = 0; length < 10; length++) {
            name += "A";

            JWord word = new JWord(name, 0);
            assertEquals(name, word.word());
            assertEquals(name.length(), word.length());
        }
    }
}