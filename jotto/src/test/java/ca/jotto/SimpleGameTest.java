package ca.jotto;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class SimpleGameTest {

    static public ArrayList<JWord> getWordList() {
        JWord[] words = new JWord[]{
                new JWord("MYTHS", 1),
                new JWord("NYMPH", 1),
                new JWord("QUAKE", 1),
                new JWord("PYGMY", 1),
                new JWord("PSYCH", 1)
        };
        return new ArrayList<>(Arrays.asList(words));
    }

    @Test
    public void simple() throws Exception {
        ArrayList<JWord> words = getWordList();

        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, words));
        JMatch match = jotto.construct(words.get(0));

        String[] guesses = new String[]{
                "NYMPH",
                "QUAKE",
                "PYGMY",
                "PSYCH",
                "MYTHS"
        };

        int[] exact = new int[]{
                1, 0, 1, 0, 5
        };

        int[] partial = new int[]{
                2, 0, 1, 3, 0
        };

        match.start();
        for (int i = 0; i < guesses.length; i++) {
            JGuess guess = match.guess(guesses[i]);

            assertNotNull(guess);
            assertEquals(guesses[i], guess.getGuess());
            assertEquals(exact[i], guess.getExact());
            assertEquals(partial[i], guess.getPartial());
        }
    }
}
