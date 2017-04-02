package ca.jotto;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JHistoryTest {

    private JWordMatch[] getEmptyMatches() {
        return new JWordMatch[]{
                JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE
        };
    }

    @Test(expected = AssertionError.class)
    public void constructor_zero() throws Exception {
        JHistory history = new JHistory(JCharset.DEFAULT, 0);
    }

    @Test(expected = AssertionError.class)
    public void constructor_null() throws Exception {
        JHistory history = new JHistory(null, 5);
    }

    @Test
    public void empty() throws Exception {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, 5);

        assertEquals(0, history.length());
    }

    @Test
    public void length() throws Exception {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, 5);

        assertEquals(0, history.length());
        history.add(new JGuess("HELLO", getEmptyMatches(), 0, 0));
        assertEquals(1, history.length());
    }

    @Test
    public void add() throws Exception {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, 5);
        assertEquals(0, history.length());

        String word = "HELLO";
        history.add(new JGuess(word, getEmptyMatches(), 1, 0));
        history.add(new JGuess(word, getEmptyMatches(), 2, 0));
        history.add(new JGuess(word, getEmptyMatches(), 3, 0));

        assertEquals(3, history.length());
        for (int i = 0; i < history.length(); i++) {
            JGuess guess = history.get(i);
            assertEquals(word, guess.getGuess());
            assertEquals(i + 1, guess.getExact());
        }
    }

    @Test
    public void contains() throws Exception {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, 5);
        assertEquals(0, history.length());

        history.add(new JGuess("HELLO", getEmptyMatches(), 1, 0));
        history.add(new JGuess("PEACH", getEmptyMatches(), 2, 0));
        history.add(new JGuess("OTHER", getEmptyMatches(), 3, 0));

        assertEquals(3, history.length());
        assertTrue(history.contains("PEACH"));
        assertTrue(history.contains("OTHER"));
        assertTrue(history.contains("HELLO"));
    }

    @Test
    public void contains_not() throws Exception {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, 5);
        assertEquals(0, history.length());

        history.add(new JGuess("HELLO", getEmptyMatches(), 1, 0));
        history.add(new JGuess("PEACH", getEmptyMatches(), 2, 0));
        history.add(new JGuess("OTHER", getEmptyMatches(), 3, 0));

        assertEquals(3, history.length());
        assertTrue(history.contains("HELLO"));
        assertFalse(history.contains("WORLD"));
        assertFalse(history.contains("SOOTH"));
    }

    @Test
    public void guesses() throws Exception {
        JCharset charset = JCharset.DEFAULT;
        JHistory history = new JHistory(charset, 5);
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
            assertTrue(history.contains(entries.get(i).getGuess()));
            assertEquals(0, entries.get(i).getPartial());
            assertEquals(i + 1, entries.get(i).getExact());
        }
    }
}