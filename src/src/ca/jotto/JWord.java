package ca.jotto;

/**
 * Represents a jotto word with associated attributes.
 */
public final class JWord {

    private final String _word;
    private final int _difficulty;

    /**
     * Initializes the jotto word with the text and difficulty.
     *
     * @param word       A string representing the word.
     * @param difficulty The difficulty of the word.
     */
    public JWord(String word, int difficulty) {
        assert word != null : "The provided String 'word' cannot be null";
        assert difficulty >= 0 : "The provided Integer 'difficulty' cannot less than zero";

        _word = word;
        _difficulty = difficulty;
    }

    /**
     * Gets the String representation of the word.
     *
     * @return The string representation of the word.
     */
    public String getWord() {
        return _word;
    }

    /**
     * Gets the difficulty of the word.
     *
     * @return The difficulty of the word.
     */
    public int getDifficulty() {
        return _difficulty;
    }

    /**
     * Gets the length of the word.
     *
     * @return The length of the word.
     */
    public int length() {
        return _word.length();
    }
}