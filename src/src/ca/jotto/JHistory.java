package ca.jotto;

import java.util.ArrayList;

/**
 * Represents the history of user guesses in the jotto game.
 */
public class JHistory {

    private final ArrayList<JGuess> _guesses;
    private final JCharset _charset;
    private final int _wordsize;

    /**
     * Initializes the history of a jotto game.
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
     * Returns the number of words in the history.
     *
     * @return The number of words within the history.
     */
    public int length() {
        return _guesses.size();
    }

    /***
     * Returns the list of guesses recorded in the history.
     *
     * @return The guess array.
     */
    public ArrayList<JGuess> guesses() {
        return (ArrayList<JGuess>) _guesses.clone();
    }

    /**
     * Adds a guess to the history of the jotto game.
     *
     * @param guess Adds a jotto guess into the history.
     */
    public void add(JGuess guess) {
        assert guess != null : "The provided JGuess 'guess' cannot be null";

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
     * Returns true if the word has been guessed, false otherwise.
     *
     * @return True if word has been guessed; false otherwise.
     */
    public boolean contains(String word) {
        assert word != null : "The provided String 'word' cannot be null";

        for (JGuess guess : _guesses) {
            if (guess.getGuess().equals(word)) {
                return true;
            }
        }
        return false;
    }
}
