package ca.jotto;

import org.junit.Test;

import static org.junit.Assert.*;

public class JSecretTest {

    @Test(expected = AssertionError.class)
    public void constructor_null() throws Exception {
        JSecret jsecret = new JSecret(null);
    }

    @Test
    public void match() throws Exception {
        JWord secret = new JWord("AAAAA", 0);
        String word = "AAAAA";

        JSecret jsecret = new JSecret(secret);
        JGuess guess = jsecret.guess(word);

        assertTrue(guess.isCorrect());
        assertEquals(word.length(), guess.size());
        assertEquals(word, guess.getGuess());

        assertEquals(0, guess.getPartial());
        assertEquals(word.length(), guess.getExact());
    }

    @Test
    public void no_match() throws Exception {
        JWord secret = new JWord("AAAAA", 0);
        String word = "BBBBB";

        JSecret jsecret = new JSecret(secret);
        JGuess guess = jsecret.guess(word);

        assertFalse(guess.isCorrect());
        assertEquals(word.length(), guess.size());
        assertEquals(word, guess.getGuess());

        assertEquals(0, guess.getPartial());
        assertEquals(0, guess.getExact());
    }

    @Test
    public void single_match() throws Exception {
        JSecret secret = new JSecret(new JWord("AAAAA", 0));

        char[] guess = "BBBBB".toCharArray();
        for (int i = 0; i < guess.length; i++) {
            char value = guess[i];
            guess[i] = 'A';

            String word = new String(guess);
            JGuess jguess = secret.guess(word);

            assertFalse(jguess.isCorrect());
            assertEquals(word.length(), jguess.size());
            assertEquals(word, jguess.getGuess());
            assertEquals(JWordMatch.EXACT, jguess.getMatch(i));

            assertEquals(0, jguess.getPartial());
            assertEquals(1, jguess.getExact());

            guess[i] = value;
        }
    }

    @Test
    public void exact_matches() throws Exception {
        JSecret secret = new JSecret(new JWord("AAAAA", 0));

        char[] guess = "BBBBB".toCharArray();
        for (int i = 0; i < guess.length; i++) {
            char value = guess[i];
            guess[i] = 'A';

            String word = new String(guess);
            JGuess jguess = secret.guess(word);

            assertEquals(word.length(), jguess.size());
            assertEquals(word, jguess.getGuess());
            assertEquals(JWordMatch.EXACT, jguess.getMatch(i));

            assertEquals(0, jguess.getPartial());
            assertEquals(i + 1, jguess.getExact());
        }
    }

    @Test
    public void one_exact() throws Exception {
        JSecret secret = new JSecret(new JWord("AAAAA", 0));
        JGuess jguess = secret.guess("ABBBB");

        assertEquals(JWordMatch.EXACT, jguess.getMatch(0));
        assertEquals(1, jguess.getExact());
    }

    @Test
    public void one_partial() throws Exception {
        JSecret secret = new JSecret(new JWord("AAAAC", 0));
        JGuess jguess = secret.guess("CBBBB");

        assertEquals(JWordMatch.PARTIAL, jguess.getMatch(0));
        assertEquals(1, jguess.getPartial());
        assertEquals(0, jguess.getExact());
    }

    @Test
    public void simple() throws Exception {
        JSecret secret = new JSecret(new JWord("OTHER", 0));
        JGuess jguess = secret.guess("PEACH");

        assertEquals(JWordMatch.PARTIAL, jguess.getMatch(1));
        assertEquals(JWordMatch.PARTIAL, jguess.getMatch(4));
        assertEquals(2, jguess.getPartial());
        assertEquals(0, jguess.getExact());
    }

    @Test
    public void similar() throws Exception {
        JSecret secret = new JSecret(new JWord("MOUTH", 0));
        JGuess jguess = secret.guess("COUTH");

        assertEquals(JWordMatch.NONE, jguess.getMatch(0));
        assertEquals(0, jguess.getPartial());
        assertEquals(4, jguess.getExact());
    }

    @Test
    public void matching() throws Exception {
        JSecret secret = new JSecret(new JWord("SOOTH", 0));
        JGuess jguess = secret.guess("SHOOK");

        assertEquals(JWordMatch.PARTIAL, jguess.getMatch(1));
        assertEquals(2, jguess.getPartial());
        assertEquals(2, jguess.getExact());
    }
}