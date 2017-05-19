package ca.jotto.model;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameTest {

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

    @Category(FunctionalTests.class)
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
            assertEquals(guesses[i], guess.guess());
            assertEquals(exact[i], guess.exact());
            assertEquals(partial[i], guess.partial());
        }
    }
}
