package ca.jotto.model;

import java.util.ArrayList;
import java.util.Arrays;

public class TestHelper {

    static final int SINGLE_DIFFICULTY = 3;
    static final int WORDS = 4;
    static final int WORD_SIZE = 5;

    static public ArrayList<JWord> getWordList() {
        JWord[] words = new JWord[]{
                new JWord("OTHER", 1),
                new JWord("EJECT", 1),
                new JWord("HELLO", 1),
                new JWord("WORLD", SINGLE_DIFFICULTY)
        };
        return new ArrayList<>(Arrays.asList(words));
    }

    static public ArrayList<JWord> getTestWords() {
        JWord[] words = new JWord[]{
                getWord(),
                new JWord("NYMPH", 1),
                new JWord("QUAKE", 1),
                new JWord("PYGMY", 1),
                new JWord("PSYCH", 1)
        };
        return new ArrayList<>(Arrays.asList(words));
    }

    static public ArrayList<JWord> getDuplicates() {
        JWord[] words = new JWord[]{
                new JWord("OTHER", 1),
                new JWord("OTHER", 2),
                new JWord("EJECT", 1),
                new JWord("HELLO", 1)
        };
        return new ArrayList<>(Arrays.asList(words));
    }

    static public ArrayList<JWord> getDifficultyList(int min, int max) {
        char alpha = 'A';
        int difficulty = min;
        int length = (max - min) + 1;
        JWord[] words = new JWord[length];
        for (int i = 0; i < length; i++) {
            words[i] = new JWord("WORD" + alpha, difficulty);
            alpha++;
            difficulty++;
        }
        return new ArrayList<>(Arrays.asList(words));
    }

    static public JWordMatch[] getNoMatches() {
        return new JWordMatch[]{
                JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE, JWordMatch.NONE
        };
    }

    static public JGuess getGuess() {
        return new JGuess("OTHER", getNoMatches(), 0, 0);
    }

    static public JWord getWord() {
        return new JWord("MYTHS", 1);
    }
}
