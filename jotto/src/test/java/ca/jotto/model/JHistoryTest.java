package ca.jotto.model;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JHistoryTest {

    private JWordMatch[] getEmptyMatches() {
        return new JWordMatch[]{
                JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE
        };
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_zero() {
        new JHistory(JCharset.DEFAULT, 0);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_negative() {
        new JHistory(JCharset.DEFAULT, -1);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_null() {
        new JHistory(null, 1);
    }

    @Category(ValidationTests.class)
    @Test
    public void length() {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, TestHelper.WORD_SIZE);

        assertEquals(0, history.length());
        history.add(new JGuess("HELLO", getEmptyMatches(), 0, 0));
        assertEquals(1, history.length());
    }

    @Category(ValidationTests.class)
    @Test
    public void length_empty() {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, TestHelper.WORD_SIZE);

        assertEquals(0, history.length());
    }

    @Category(FunctionalTests.class)
    @Test
    public void add() {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, TestHelper.WORD_SIZE);
        assertEquals(0, history.length());

        String word = "HELLO";
        history.add(new JGuess(word, getEmptyMatches(), 1, 0));
        history.add(new JGuess(word, getEmptyMatches(), 2, 0));
        history.add(new JGuess(word, getEmptyMatches(), 3, 0));

        assertEquals(3, history.length());
        for (int i = 0; i < history.length(); i++) {
            JGuess guess = history.get(i);
            assertEquals(word, guess.guess());
            assertEquals(i + 1, guess.exact());
        }
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void add_null() {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, TestHelper.WORD_SIZE);

        history.add(null);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void add_not() {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, TestHelper.WORD_SIZE);

        history.add(new JGuess("L33TZ", getEmptyMatches(), 1, 0));
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void get() {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, TestHelper.WORD_SIZE);

        history.add(null);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void get_below() {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, TestHelper.WORD_SIZE);
        history.add(new JGuess("HELLO", getEmptyMatches(), 1, 0));

        history.get(-1);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void get_above() {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, TestHelper.WORD_SIZE);
        history.add(new JGuess("HELLO", getEmptyMatches(), 1, 0));

        history.get(3);
    }

    @Category(ValidationTests.class)
    @Test
    public void contains() {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, TestHelper.WORD_SIZE);
        assertEquals(0, history.length());

        history.add(new JGuess("HELLO", getEmptyMatches(), 1, 0));
        history.add(new JGuess("PEACH", getEmptyMatches(), 2, 0));
        history.add(new JGuess("OTHER", getEmptyMatches(), 3, 0));

        assertEquals(3, history.length());
        assertTrue(history.contains("PEACH"));
        assertTrue(history.contains("OTHER"));
        assertTrue(history.contains("HELLO"));
        assertFalse(history.contains("WORLD"));
        assertFalse(history.contains("SOOTH"));
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void contains_null() {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, TestHelper.WORD_SIZE);
        assertEquals(0, history.length());

        history.add(new JGuess("HELLO", getEmptyMatches(), 1, 0));
        assertEquals(1, history.length());
        history.contains(null);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void contains_not() {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, TestHelper.WORD_SIZE);
        assertEquals(0, history.length());

        history.add(new JGuess("HELLO", getEmptyMatches(), 1, 0));
        assertEquals(1, history.length());
        history.contains("L33TZ");
    }

    @Category(FunctionalTests.class)
    @Test
    public void guesses() {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, TestHelper.WORD_SIZE);
        assertEquals(0, history.length());

        ArrayList<JGuess> guesses = new ArrayList<JGuess>();
        guesses.add(new JGuess("HELLO", getEmptyMatches(), 1, 0));
        guesses.add(new JGuess("PEACH", getEmptyMatches(), 2, 0));
        guesses.add(new JGuess("OTHER", getEmptyMatches(), 3, 0));

        for (int i = 0; i < guesses.size(); i++) {
            history.add(guesses.get(i));
        }

        ArrayList<JGuess> entries = history.guesses();
        assertEquals(guesses.size(), entries.size());
        for (int i = 0; i < entries.size(); i++) {
            assertTrue(history.contains(entries.get(i).guess()));
            assertEquals(0, entries.get(i).partial());
            assertEquals(i + 1, entries.get(i).exact());
        }
    }
}