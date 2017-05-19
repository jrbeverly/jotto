package ca.jotto.model;

/**
 * Represents a letter match between the guess and secret.
 */
public enum JWordMatch {
    /**
     * There is no match for the letter.
     */
    NONE,
    /**
     * There is a partial match for the letter.
     */
    PARTIAL,
    /**
     * There is an exact match for the letter.
     */
    EXACT,
    /**
     * The letter is known not to be present in the secret word.
     */
    ELIMINATED
}
