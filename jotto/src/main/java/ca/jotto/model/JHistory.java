package ca.jotto.model;

import java.util.ArrayList;

/**
 * Represents a collection of guesses.
 */
public class JHistory {

    private final ArrayList<JGuess> _guesses;
    private final JCharset _charset;
    private final int _wordsize;

    /**
     * Initializes the history of a game.
     *
     * @param charset The character set that the words in the dictionary are defined.
     * @param size    The length of each word in the dictionary.
     */
    public JHistory(JCharset charset, int size) {
        assert charset != null : "The provided JCharset 'charset' cannot be null";
        assert size > 0 : "The provided Integer 'size' must be greater than zero";

        _wordsize = size;
        _charset = charset;
        _guesses = new ArrayList<JGuess>();
    }

    /**
     * Returns the number of guesses contained in the {@link JHistory}.
     *
     * @return The number of guesses contained in the {@link JHistory}.
     */
    public int length() {
        return _guesses.size();
    }

    /**
     * Adds the specified guess to the dictionary.
     *
     * @param guess The value of the {@link JGuess} to add.
     */
    public void add(JGuess guess) {
        assert guess != null : "The provided JGuess 'guess' cannot be null";
        assert _charset.contains(guess.guess()) : "The provided JGuess 'guess'  is not within the character set.";

        _guesses.add(guess);
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index The index of the element to return.
     * @return This method returns the element at the specified position in this list.
     */
    public JGuess get(int index) {
        assert index >= 0 : "The provided Integer 'index' cannot be less than zero";
        assert index < _guesses.size() : "The provided Integer 'index' exceed the word count";

        return _guesses.get(index);
    }

    /**
     * Determines whether the {@link JHistory} contains the specified string.
     *
     * @param word The word to locate in the dictionary.
     * @return true if the {@link JHistory} contains the string; otherwise, false.
     */
    public boolean contains(String word) {
        assert word != null : "The provided String 'word' cannot be null";
        assert _charset.contains(word) : "The provided String 'word'  is not within the character set.";

        for (JGuess guess : _guesses) {
            if (guess.guess().equals(word)) {
                return true;
            }
        }
        return false;
    }

    /***
     * Returns the list of guesses recorded in the history.
     *
     * @return An ArrayList containing the values in the {@link JHistory}.
     */
    public ArrayList<JGuess> guesses() {
        return (ArrayList<JGuess>) _guesses.clone();
    }
}
