package ca.jotto;

import org.junit.Test;

import static org.junit.Assert.*;

public class JGuessTest {

    private JWordMatch[] getEmptyMatches() {
        return new JWordMatch[] {
                JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE
        };
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

        for (int i = 0; i < matches.length; i++){
            assertEquals(matches[i], guess.getMatch(i));
            assertEquals(word.charAt(i), guess.getChar(i));
        }
    }

    @Test
    public void exact() {
        String word = "HELLO";
        int exact = 5, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(exact, guess.getExact());
    }

    @Test
    public void correct() {
        String word = "HELLO";
        int exact = word.length(), partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertTrue(guess.isCorrect());
        assertEquals(exact, guess.getExact());
    }

    @Test
    public void partial() {
        String word = "HELLO";
        int exact = 0, partial = 3;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(partial, guess.getPartial());
    }

    @Test
    public void word() {
        String word = "HELLO";
        int exact = 0, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(word, guess.getGuess());
        assertEquals(word.length(), guess.size());
    }

    @Test
    public void matches() {
        String word = "HELLO";
        int exact = 0, partial = 0;
        JWordMatch[] matches = new JWordMatch[] {
                JWordMatch.NONE,
                JWordMatch.EXACT,
                JWordMatch.ELIMINATED,
                JWordMatch.PARTIAL,
                JWordMatch.NONE
        };
        JGuess guess = new JGuess(word, matches, exact, partial);

        for (int i = 0; i < matches.length; i++){
            assertEquals(matches[i], guess.getMatch(i));
        }
    }

    @Test
    public void getChar() throws Exception {
        String word = "HELLO";
        int exact = 0, partial = 0;
        JWordMatch[] matches = getEmptyMatches();
        JGuess guess = new JGuess(word, matches, exact, partial);

        assertEquals(word.length(), guess.size());
        for (int i = 0; i < matches.length; i++){
            assertEquals(word.charAt(i), guess.getChar(i));
        }
    }
}