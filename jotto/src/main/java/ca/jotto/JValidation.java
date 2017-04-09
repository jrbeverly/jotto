package ca.jotto;

/**
 * Specifies the validation property used for guess validation.
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
     * The guess has been previously guessed.
     */
    PREVIOUSLY_GUESSED
}
