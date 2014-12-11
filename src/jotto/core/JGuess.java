package jotto.core;

/**
 * Represents a guess in the jotto game
 */
public final class JGuess {

    private final String _guess;
    private final JMatch[] _matches;
    private final int _partial;
    private final int _exact;

    /**
     * Initializes a guess based on the text and match qualifications
	 *
     */
    public JGuess(String guess, JMatch[] matches) {
        _guess = guess;
        _matches = matches;

        int partial = 0;
        int exact = 0;

        // computes number of exact/partials
        for (int i = 0; i < matches.length; i++) {
            switch (matches[i]) {
                case EXACT:
                    exact++;
                    break;
                case PARTIAL:
                    partial++;
                    break;
                default:
                    break;
            }
        }

        _partial = partial;
        _exact = exact;
    }

    /**
     * The number of partial matches present for this guess
	 *
     */
    public int getPartial() {
        return _partial;
    }

    /**
     * The number of exact matches present for this guess
	 *
     */
    public int getExact() {
        return _exact;
    }

    /**
     * Length of a guess
	 *
     */
    public int getWordSize() {
        return _guess.length();
    }

    /**
     * Determines if the guess is correct
	 *
     */
    public Boolean isCorrect() {
        return _exact == _matches.length;
    }

    /**
     * Returns a match for a specified character index
	 *
     */
    public JMatch getMatch(int index) {
        return _matches[index];
    }

    /**
     * Gets the character at the specified index
	 *
     */
    public char getChar(int index) {
        return _guess.charAt(index);
    }

    /**
     * Gets the word that was guessed
	 *
     */
    public String getGuess() {
        return _guess;
    }

    /**
     * Gets the guess as a character array
	 *
     */
    public char[] getCharacters() {
        return _guess.toCharArray();
    }

    /**
     * Gets the matches specified for each character
	 *
     */
    public JMatch[] getMatches() {
        return _matches;
    }
}
