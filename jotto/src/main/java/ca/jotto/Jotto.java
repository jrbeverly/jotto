package ca.jotto;

import ca.jotto.listeners.JottoEventMap;

/**
 * Represents the rules and structure of the jotto game.
 */
public final class Jotto {

    public static final int MAXIMUM_GUESS = 10;

    private final JDictionary _dictionary;
    private final JottoEventMap _eventMap;
    private final JCharset _charset;

    /**
     * Initializes the game based on a dictionary.
     *
     * @param dictionary The dictionary of words to use for the game.
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
     * Gets the size of the words used in this match.
     *
     * @return The word length within the dictionary.
     */
    public int getWordSize() {
        return _dictionary.size();
    }

    /**
     * Gets the dictionary of the game.
     *
     * @return The word dictionary of the game.
     */
    public JDictionary getDictionary() {
        return _dictionary;
    }

    /**
     * Constructs a match from the game definition.
     *
     * @param word The secret word of the match.
     */
    public JMatch construct(JWord word) {
        assert word != null : "The provided JWord 'word' cannot be null";
        assert _dictionary.contains(word.word()) : "The provided JWord 'word' is not present in the dictionary";

        JSecret secret = new JSecret(word);
        JMatch match = new JMatch(this, secret, MAXIMUM_GUESS);

        return match;
    }
}
