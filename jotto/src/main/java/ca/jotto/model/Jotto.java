package ca.jotto.model;

import ca.jotto.model.listeners.JottoEventMap;

/**
 * Represents the structure of a jotto game.
 */
public final class Jotto {

    /**
     * The default maximum number of guesses for a {@link JMatch}.
     */
    public static final int MAXIMUM_GUESS = 10;

    private final JDictionary _dictionary;
    private final JottoEventMap _eventMap;
    private final JCharset _charset;

    /**
     * Initializes a new instance of the {@link Jotto} class with the specified word dictionary.
     *
     * @param dictionary The object that contains the words.
     */
    public Jotto(JDictionary dictionary) {
        assert dictionary != null : "The provided JDictionary 'dictionary' cannot be null";

        _dictionary = dictionary;
        _charset = dictionary.getCharset();
        _eventMap = new JottoEventMap();
    }

    /**
     * Returns the event map associated with the game.
     *
     * @return The event map of the game.
     */
    public JottoEventMap getEventMap() {
        return _eventMap;
    }

    /**
     * Returns the character set associated with the game.
     *
     * @return The character set of the game.
     */
    public JCharset getCharset() {
        return _charset;
    }

    /**
     * Returns the size of the words associated with the game.
     *
     * @return The word length of the dictionary.
     */
    public int getWordSize() {
        return _dictionary.size();
    }

    /**
     * Returns the dictionary associated with the game.
     *
     * @return The word dictionary of the game.
     */
    public JDictionary getDictionary() {
        return _dictionary;
    }

    /**
     * Constructs a {@link JMatch} with the specified secret.
     *
     * @param word The {@link JWord} to set as the secret for the {@link JMatch}.
     * @return A new {@link JMatch} that is constructed from the jotto game.
     */
    public JMatch construct(JWord word) {
        assert word != null : "The provided JWord 'word' cannot be null";
        assert _dictionary.contains(word.word()) : "The provided JWord 'word' is not present in the dictionary";

        JSecret secret = new JSecret(word);
        JMatch match = new JMatch(this, secret, MAXIMUM_GUESS);

        return match;
    }
}
