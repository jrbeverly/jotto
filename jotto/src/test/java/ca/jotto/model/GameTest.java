package ca.jotto.model;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameTest {

    @Category(FunctionalTests.class)
    @Test
    public void simple() throws Exception {
        ArrayList<JWord> words = TestHelper.getTestWords();

        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, words));
        JMatch match = jotto.construct(words.get(0));

        String[] guesses = new String[]{
                "NYMPH",//1,2
                "QUAKE",//0,0
                "PYGMY",//1,1
                "PSYCH",//0,3
                "MYTHS"//5,0
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
