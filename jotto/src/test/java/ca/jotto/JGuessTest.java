package ca.jotto;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class JGuessTest {

    private JWordMatch[] getEmptyMatches() {
        return new JWordMatch[]{
                JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE
        };
    }

    @Category(ValidationTests.class)
    @Test
    public void constructor_zeroes() throws Exception {
        String word = "OTHER";
        int exact = 0, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        new JGuess(word, matches, exact, partial);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_partial_neg() throws Exception {
        String word = "OTHER";
        int exact = 0, partial = -1;
        JWordMatch[] matches = getEmptyMatches();
        new JGuess(word, matches, exact, partial);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_exact_neg() throws Exception {
        String word = "OTHER";
        int exact = -1, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        new JGuess(word, matches, exact, partial);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_bad_length() throws Exception {
        String word = "OTHER";
        int exact = 0, partial = 0;
        JWordMatch[] matches = new JWordMatch[]{JWordMatch.NONE};
        new JGuess(word, matches, exact, partial);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_match_empty() throws Exception {
        String word = "OTHER";
        int exact = 0, partial = 0;
        JWordMatch[] matches = null;
        new JGuess(word, matches, exact, partial);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_guess_null() throws Exception {
        String word = null;
        int exact = 0, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        new JGuess(word, matches, exact, partial);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_guess_empty() throws Exception {
        String word = "";
        int exact = 0, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        new JGuess(word, matches, exact, partial);
    }

    @Category(ValidationTests.class)
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

    @Category(ValidationTests.class)
    @Test
    public void size() {
        String word = "HELLO";
        int exact = 5, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(word.length(), guess.size());
    }

    @Category(ValidationTests.class)
    @Test
    public void exact() {
        String word = "HELLO";
        int exact = 5, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(exact, guess.getExact());
    }

    @Category(ValidationTests.class)
    @Test
    public void correct() {
        String word = "HELLO";
        int exact = word.length(), partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertTrue(guess.isCorrect());
        assertEquals(exact, guess.getExact());
    }

    @Category(ValidationTests.class)
    @Test
    public void partial() {
        String word = "HELLO";
        int exact = 0, partial = 3;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(partial, guess.getPartial());
    }

    @Category(ValidationTests.class)
    @Test
    public void getGuess() {
        String word = "HELLO";
        int exact = 0, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(word, guess.getGuess());
        assertEquals(word.length(), guess.size());
    }

    @Category(ValidationTests.class)
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

        for (int i = 0; i < matches.length; i++) {
            assertEquals(matches[i], guess.getMatch(i));
        }
    }

    @Category(ValidationTests.class)
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
            assertEquals(matches[i], results[i]);
        }
    }

    @Category(ValidationTests.class)
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