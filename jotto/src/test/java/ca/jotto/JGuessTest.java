package ca.jotto;

import org.junit.Test;

import static org.junit.Assert.*;

public class JGuessTest {

    private JWordMatch[] getEmptyMatches() {
        return new JWordMatch[]{
                JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE
        };
    }

    @Test
    public void constructor_zeroes() throws Exception {
        String word = "OTHER";
        int exact = 0, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);
    }

    @Test(expected = AssertionError.class)
    public void constructor_partial_neg() throws Exception {
        String word = "OTHER";
        int exact = 0, partial = -1;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);
    }

    @Test(expected = AssertionError.class)
    public void constructor_exact_neg() throws Exception {
        String word = "OTHER";
        int exact = -1, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);
    }

    @Test(expected = AssertionError.class)
    public void constructor_bad_length() throws Exception {
        String word = "OTHER";
        int exact = 0, partial = 0;
        JWordMatch[] matches = new JWordMatch[]{JWordMatch.NONE};
        JGuess guess = new JGuess(word, matches, exact, partial);
    }

    @Test(expected = AssertionError.class)
    public void constructor_empty() throws Exception {
        String word = "OTHER";
        int exact = 0, partial = 0;
        JWordMatch[] matches = null;
        JGuess guess = new JGuess(word, matches, exact, partial);
    }

    @Test(expected = AssertionError.class)
    public void constructor_null() throws Exception {
        String word = null;
        int exact = 0, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);
    }

    @Test(expected = AssertionError.class)
    public void constructor_blank() throws Exception {
        String word = "";
        int exact = 0, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);
    }

    @Test
    public void guess() throws Exception {
        String word = "HELLO";
        int exact = 0, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertFalse(guess.isCorrect());
        assertEquals(word, guess.getGuess());
        assertEquals(partial, guess.getPartial());
        assertEquals(exact, guess.getExact());
        assertEquals(word.length(), guess.size());

        for (int i = 0; i < matches.length; i++) {
            assertEquals(matches[i], guess.getMatch(i));
            assertEquals(word.charAt(i), guess.getChar(i));
        }
    }

    @Test
    public void size() {
        String word = "HELLO";
        int exact = 5, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(word.length(), guess.size());
    }

    @Test
    public void getExact() {
        String word = "HELLO";
        int exact = 5, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(exact, guess.getExact());
    }

    @Test
    public void isCorrect() {
        String word = "HELLO";
        int exact = word.length(), partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertTrue(guess.isCorrect());
        assertEquals(exact, guess.getExact());
    }

    @Test
    public void getPartial() {
        String word = "HELLO";
        int exact = 0, partial = 3;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(partial, guess.getPartial());
    }

    @Test
    public void getGuess() {
        String word = "HELLO";
        int exact = 0, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(word, guess.getGuess());
        assertEquals(word.length(), guess.size());
    }

    @Test
    public void getMatch() {
        String word = "HELLO";
        int exact = 0, partial = 0;
        JWordMatch[] matches = new JWordMatch[]{
                JWordMatch.NONE,
                JWordMatch.EXACT,
                JWordMatch.ELIMINATED,
                JWordMatch.PARTIAL,
                JWordMatch.NONE
        };
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(JWordMatch.NONE, guess.getMatch(0));
        assertEquals(JWordMatch.ELIMINATED, guess.getMatch(2));
    }

    @Test
    public void getMatches() {
        String word = "HELLO";
        int exact = 0, partial = 0;
        JWordMatch[] matches = new JWordMatch[]{
                JWordMatch.NONE,
                JWordMatch.EXACT,
                JWordMatch.ELIMINATED,
                JWordMatch.PARTIAL,
                JWordMatch.NONE
        };
        JGuess guess = new JGuess(word, matches, exact, partial);

        JWordMatch[] results = guess.getMatches();
        for (int i = 0; i < matches.length; i++) {
            assertEquals(matches[i], guess.getMatch(i));
            assertEquals(matches[i], results[i]);
        }
    }

    @Test
    public void getChar() throws Exception {
        String word = "HELLO";
        int exact = 0, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(word.length(), guess.size());
        for (int i = 0; i < matches.length; i++) {
            assertEquals(word.charAt(i), guess.getChar(i));
        }
    }
}