package ca.jotto;

import ca.jotto.listeners.JottoEventMap;

/**
 * Represents an instance of a game of jotto.
 */
public final class Jotto {

    public static final int MAXIMUM_GUESS = 10;

    private final JDictionary _dictionary;
    private final JottoEventMap _eventMap;
    private final JCharset _charset;

    /**
     * Initializes the jotto game based on a dictionary.
     *
     * @param dictionary The dictionary of words to use for the jotto game.
     */
    public Jotto(JDictionary dictionary) {
        assert dictionary != null : "The provided JDictionary 'dictionary' cannot be null";

        _dictionary = dictionary;
        _charset = dictionary.getCharset();
        _eventMap = new JottoEventMap();
    }

    /**
     * Returns the EventMap associated with the jotto game.
     *
     * @return The event map of the jotto game.
     */
    public JottoEventMap getEventMap() {
        return _eventMap;
    }

    /**
     * Returns the character set associated with the jotto game.
     *
     * @return The character set of the jotto game.
     */
    public JCharset getCharset() {
        return _charset;
    }

    /**
     * Gets the size of the words used in this jotto match.
     *
     * @return The word length within the dictionary.
     */
    public int getWordSize() {
        return _dictionary.size();
    }

    /**
     * Gets the dictionary that the jotto game is using.
     *
     * @return The dictionary of the jotto game.
     */
    public JDictionary getDictionary() {
        return _dictionary;
    }

    /**
     * Signals that the match is started.
     *
     * @param word The secret word to start the game with.
     */
    public JMatch start(JWord word) {
        assert word != null : "The provided JWord 'word' cannot be null";
        assert _dictionary.contains(word.word()) : "The provided JWord 'word' is not present in the dictionary";

        JSecret secret = new JSecret(word);
        JMatch match = new JMatch(this, secret, MAXIMUM_GUESS);

        return match;
    }
}
