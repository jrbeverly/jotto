package ca.jotto;

import java.util.ArrayList;
import java.util.Arrays;

public class TestHelper {

    static final int SINGLE_DIFFICULTY = 3;
    static final int WORDS = 4;

    static public ArrayList<JWord> getWordList() {
        JWord[] words = new JWord[]{
                new JWord("OTHER", 1),
                new JWord("EJECT", 1),
                new JWord("HELLO", 1),
                new JWord("WORLD", SINGLE_DIFFICULTY)
        };
        return new ArrayList<>(Arrays.asList(words));
    }

    static public JWordMatch[] getEmptyMatches() {
        return new JWordMatch[]{
                JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE
        };
    }

    static public JGuess getGuess() {
        return new JGuess("OTHER", getEmptyMatches(), 0, 0);
    }

}
