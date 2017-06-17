package ca.jotto.model;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class JSecretTest {

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_null() throws Exception {
        JSecret jsecret = new JSecret(null);
    }

    @Category(ValidationTests.class)
    @Test
    public void match() throws Exception {
        JWord secret = new JWord("AAAAA", 0);
        String word = "AAAAA";

        JSecret jsecret = new JSecret(secret);
        JGuess guess = jsecret.guess(word);

        assertTrue(guess.correct());
        assertEquals(word.length(), guess.size());
        assertEquals(word, guess.guess());

        assertEquals(0, guess.partial());
        assertEquals(word.length(), guess.exact());
    }

    @Category(ValidationTests.class)
    @Test
    public void no_match() throws Exception {
        JWord secret = new JWord("AAAAA", 0);
        String word = "BBBBB";

        JSecret jsecret = new JSecret(secret);
        JGuess guess = jsecret.guess(word);

        assertFalse(guess.correct());
        assertEquals(word.length(), guess.size());
        assertEquals(word, guess.guess());

        assertEquals(0, guess.partial());
        assertEquals(0, guess.exact());
    }

    @Category(FunctionalTests.class)
    @Test
    public void single_match() throws Exception {
        JSecret secret = new JSecret(new JWord("AAAAA", 0));

        char[] guess = "BBBBB".toCharArray();
        for (int i = 0; i < guess.length; i++) {
            char value = guess[i];
            guess[i] = 'A';

            String word = new String(guess);
            JGuess jguess = secret.guess(word);

            assertFalse(jguess.correct());
            assertEquals(word.length(), jguess.size());
            assertEquals(word, jguess.guess());
            assertEquals(JWordMatch.EXACT, jguess.matchAt(i));

            assertEquals(0, jguess.partial());
            assertEquals(1, jguess.exact());

            guess[i] = value;
        }
    }

    @Category(FunctionalTests.class)
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
            assertEquals(word, jguess.guess());
            assertEquals(JWordMatch.EXACT, jguess.matchAt(i));

            assertEquals(0, jguess.partial());
            assertEquals(i + 1, jguess.exact());
        }
    }

    @Category(FunctionalTests.class)
    @Test
    public void one_exact() throws Exception {
        JSecret secret = new JSecret(new JWord("AAAAA", 0));
        JGuess jguess = secret.guess("ABBBB");

        assertEquals(JWordMatch.EXACT, jguess.matchAt(0));
        assertEquals(1, jguess.exact());
    }

    @Category(FunctionalTests.class)
    @Test
    public void one_partial() throws Exception {
        JSecret secret = new JSecret(new JWord("AAAAC", 0));
        JGuess jguess = secret.guess("CBBBB");

        assertEquals(JWordMatch.PARTIAL, jguess.matchAt(0));
        assertEquals(1, jguess.partial());
        assertEquals(0, jguess.exact());
    }

    @Category(FunctionalTests.class)
    @Test
    public void simple() throws Exception {
        JSecret secret = new JSecret(new JWord("OTHER", 0));
        JGuess jguess = secret.guess("PEACH");

        assertEquals(JWordMatch.PARTIAL, jguess.matchAt(1));
        assertEquals(JWordMatch.PARTIAL, jguess.matchAt(4));
        assertEquals(2, jguess.partial());
        assertEquals(0, jguess.exact());
    }

    @Category(FunctionalTests.class)
    @Test
    public void similar() throws Exception {
        JSecret secret = new JSecret(new JWord("MOUTH", 0));
        JGuess jguess = secret.guess("COUTH");

        assertEquals(JWordMatch.NONE, jguess.matchAt(0));
        assertEquals(0, jguess.partial());
        assertEquals(4, jguess.exact());
    }

    @Category(FunctionalTests.class)
    @Test
    public void matching() throws Exception {
        JSecret secret = new JSecret(new JWord("SOOTH", 0));
        JGuess jguess = secret.guess("SHOOK");

        assertEquals(JWordMatch.PARTIAL, jguess.matchAt(1));
        assertEquals(2, jguess.partial());
        assertEquals(2, jguess.exact());
    }
}