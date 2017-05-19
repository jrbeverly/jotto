package ca.jotto.model;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class JGuessTest {

    @Category(ValidationTests.class)
    @Test
    public void constructor_zeroes() throws Exception {
        String word = "OTHER";
        int exact = 0, partial = 0;
        JWordMatch[] matches = TestHelper.getNoMatches();
        new JGuess(word, matches, exact, partial);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_partial_neg() throws Exception {
        String word = "OTHER";
        int exact = 0, partial = -1;
        JWordMatch[] matches = TestHelper.getNoMatches();
        new JGuess(word, matches, exact, partial);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_exact_neg() throws Exception {
        String word = "OTHER";
        int exact = -1, partial = 0;
        JWordMatch[] matches = TestHelper.getNoMatches();
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
        JWordMatch[] matches = TestHelper.getNoMatches();
        new JGuess(word, matches, exact, partial);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_guess_empty() throws Exception {
        String word = "";
        int exact = 0, partial = 0;
        JWordMatch[] matches = TestHelper.getNoMatches();
        new JGuess(word, matches, exact, partial);
    }

    @Category(ValidationTests.class)
    @Test
    public void simple() throws Exception {
        String word = "HELLO";
        int exact = 0, partial = 0;
        JWordMatch[] matches = TestHelper.getNoMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertFalse(guess.correct());
        assertEquals(word, guess.guess());
        assertEquals(partial, guess.partial());
        assertEquals(exact, guess.exact());
        assertEquals(word.length(), guess.size());

        for (int i = 0; i < matches.length; i++) {
            assertEquals(matches[i], guess.matchAt(i));
            assertEquals(word.charAt(i), guess.charAt(i));
        }
    }

    @Category(ValidationTests.class)
    @Test
    public void size() {
        String word = "HELLO";
        int exact = 5, partial = 0;
        JWordMatch[] matches = TestHelper.getNoMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(word.length(), guess.size());
    }

    @Category(ValidationTests.class)
    @Test
    public void exact() {
        String word = "HELLO";
        int exact = 5, partial = 0;
        JWordMatch[] matches = TestHelper.getNoMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(exact, guess.exact());
    }

    @Category(ValidationTests.class)
    @Test
    public void correct() {
        String word = "HELLO";
        int exact = word.length(), partial = 0;
        JWordMatch[] matches = TestHelper.getNoMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertTrue(guess.correct());
        assertEquals(exact, guess.exact());
    }

    @Category(ValidationTests.class)
    @Test
    public void partial() {
        String word = "HELLO";
        int exact = 0, partial = 3;
        JWordMatch[] matches = TestHelper.getNoMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(partial, guess.partial());
    }

    @Category(ValidationTests.class)
    @Test
    public void guess() {
        String word = "HELLO";
        int exact = 0, partial = 0;
        JWordMatch[] matches = TestHelper.getNoMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(word, guess.guess());
        assertEquals(word.length(), guess.size());
    }

    @Category(ValidationTests.class)
    @Test
    public void matchAt() {
        String word = "HELLO";
        int exact = 0, partial = 0;
        JWordMatch[] matches = new JWordMatch[]{
                JWordMatch.NONE,
                JWordMatch.EXACT,
                JWordMatch.NONE,
                JWordMatch.PARTIAL,
                JWordMatch.NONE
        };
        JGuess guess = new JGuess(word, matches, exact, partial);

        for (int i = 0; i < matches.length; i++) {
            assertEquals(matches[i], guess.matchAt(i));
        }
    }

    @Category(ValidationTests.class)
    @Test
    public void matches() {
        String word = "HELLO";
        int exact = 0, partial = 0;
        JWordMatch[] matches = new JWordMatch[]{
                JWordMatch.NONE,
                JWordMatch.EXACT,
                JWordMatch.NONE,
                JWordMatch.PARTIAL,
                JWordMatch.NONE
        };
        JGuess guess = new JGuess(word, matches, exact, partial);

        JWordMatch[] results = guess.matches();
        for (int i = 0; i < matches.length; i++) {
            assertEquals(matches[i], results[i]);
        }
    }

    @Category(ValidationTests.class)
    @Test
    public void charAt() throws Exception {
        String word = "HELLO";
        int exact = 0, partial = 0;
        JWordMatch[] matches = TestHelper.getNoMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(word.length(), guess.size());
        for (int i = 0; i < matches.length; i++) {
            assertEquals(word.charAt(i), guess.charAt(i));
        }
    }
}