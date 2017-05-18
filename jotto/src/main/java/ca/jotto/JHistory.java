package ca.jotto;

import java.util.ArrayList;

/**
 * Represents the history of user guesses in the game.
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
     * Returns the number of words in the history.
     *
     * @return The number of words within the history.
     */
    public int length() {
        return _guesses.size();
    }

    /**
     * Adds a guess to the history of the game.
     *
     * @param guess Adds a guess into the history.
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
     * Returns true if the word has been guessed, false otherwise.
     *
     * @param word The word to locate in the dictionary.
     * @return True if word has been guessed; false otherwise.
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
     * @return The guess array.
     */
    public ArrayList<JGuess> guesses() {
        return (ArrayList<JGuess>) _guesses.clone();
    }
}
