package ca.jotto;

/**
 * Enumeration for determining whether a guess is valid.
 */
public enum JValidation {
    /**
     * The guess is valid.
     */
    VALID,
    /**
     * The guess is not in the dictionary.
     */
    NOT_IN_DICTIONARY,
    /**
     * The guess is not the correct word size.
     */
    INVALID_SIZE,
    /**
     * The guess contains a character that is not valid.
     */
    INVALID_CHARACTER,
    /**
     * The player has guessed this before.
     */
    PREVIOUSLY_GUESSED
}
