package ca.jotto.model;

/**
 * Represents a guess with associated computed match data.
 */
public final class JGuess {

    private final String _guess;
    private final JWordMatch[] _matches;
    private final int _partial;
    private final int _exact;

    /**
     * Initializes a guess based on the text and match data.
     *
     * @param guess   The guessed string.
     * @param matches The similarity of characters between the guess and secret.
     * @param exact   Number of exact character matches.
     * @param partial Number of partial character matches.
     */
    public JGuess(String guess, JWordMatch[] matches, int exact, int partial) {
        assert guess != null : "The provided String 'guess' cannot be null";
        assert matches != null : "The provided JWordMatch[] 'matches' cannot be null";
        assert guess.length() == matches.length : "The provided length of String 'guess' must equal the length of array 'matches'";
        assert exact >= 0 : "The provided Integer 'exact' cannot be less than zero";
        assert partial >= 0 : "The provided Integer 'partial' cannot be less than zero";

        _guess = guess;
        _matches = matches;
        _partial = partial;
        _exact = exact;
    }

    /**
     * Returns the guessed phrase.
     *
     * @return The guess string.
     */
    public String guess() {
        return _guess;
    }

    /**
     * Determines if the guess is equal to the {@link JSecret}.
     *
     * @return true if guess is equal to the {@link JSecret}; false otherwise.
     */
    public Boolean correct() {
        return _exact == _matches.length;
    }

    /**
     * The number of characters in common with the {@link JSecret}.
     *
     * @return Number of partial character matches.
     */
    public int partial() {
        return _partial;
    }

    /**
     * The number of characters with the same character value as the {@link JSecret}.
     *
     * @return Number of exact character matches.
     */
    public int exact() {
        return _exact;
    }

    /**
     * Returns the length of this guess.
     *
     * @return The length of the sequence of characters represented by this object.
     */
    public int size() {
        return _guess.length();
    }

    /**
     * Returns an array of character matches that indicates their similarity.
     *
     * @return The matches of each character within the guess.
     */
    public JWordMatch[] matches() {
        return _matches.clone();
    }

    /**
     * Returns the {@Link JWordMatch} at the specified index.
     *
     * @param index The index of the word match.
     * @return The match at the specified index of this {@link JGuess}.
     */
    public JWordMatch matchAt(int index) {
        assert index >= 0 : "The provided Integer 'index' cannot be less than zero";
        assert index < _matches.length : "The provided Integer 'index' exceed the word size";

        return _matches[index];
    }

    /**
     * Returns the character at the specified index.
     *
     * @param index The index of the character.
     * @return The character at the specified index of this {@link JGuess}.
     */
    public char charAt(int index) {
        assert index >= 0 : "The provided Integer 'index' cannot be less than zero";
        assert index < _guess.length() : "The provided Integer 'index' exceed the word size";

        return _guess.charAt(index);
    }
}
