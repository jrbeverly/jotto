package ca.jotto;

/**
 * Represents a guess in the jotto game.
 */
public final class JGuess {

    private final String _guess;
    private final JWordMatch[] _matches;
    private final int _partial;
    private final int _exact;

    /**
     * Initializes a guess based on the text and match qualifications.
     *
     * @param guess   The string text that you are guessing.
     * @param matches The matches of the string.
     * @param exact   The number of letters that exactly match the secret.
     * @param partial The number of letters that partially match the secret.
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
     * Determines if the guess is correct
     *
     * @return True if correct; false otherwise.
     */
    public Boolean isCorrect() {
        return _exact == _matches.length;
    }

    /**
     * The number of partial matches present for this guess.
     *
     * @return Number of partial character matches.
     */
    public int getPartial() {
        return _partial;
    }

    /**
     * The number of exact matches present for this guess
     *
     * @return Number of exact character matches.
     */
    public int getExact() {
        return _exact;
    }

    /**
     * Word length of a guess.
     *
     * @return Length of the guess word.
     */
    public int size() {
        return _guess.length();
    }

    /**
     * Returns a match for a specified character based on given index.
     *
     * @param index The character index to check for match state.
     * @return The match state of the specified character.
     */
    public JWordMatch getMatch(int index) {
        assert index >= 0 : "The provided Integer 'index' cannot be less than zero";
        assert index < _matches.length : "The provided Integer 'index' exceed the word size";

        return _matches[index];
    }

    /**
     * Gets the character at the specified index.
     *
     * @param index The character index.
     * @return The character at the specified index.
     */
    public char getChar(int index) {
        assert index >= 0 : "The provided Integer 'index' cannot be less than zero";
        assert index < _guess.length() : "The provided Integer 'index' exceed the word size";

        return _guess.charAt(index);
    }

    /**
     * Gets the word that was guessed.
     *
     * @return The guess string.
     */
    public String getGuess() {
        return _guess;
    }

    /**
     * Gets the guess as a character array.
     *
     * @return The guess string as a character array.
     */
    public char[] toCharArray() {
        return _guess.toCharArray();
    }

    /**
     * Gets the matches specified for each character.
     *
     * @return The matches of each character within the guess.
     */
    public JWordMatch[] getMatches() {
        return _matches.clone();
    }
}
