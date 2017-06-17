package ca.jotto.model;

/**
 * An enumeration of values that represent guess validation errors.
 */
public enum JValidation {
    /**
     * A value that indicates that a guess is valid.
     */
    VALID,
    /**
     * A value that indicates that the word could not be found in the dictionary.
     */
    NOT_IN_DICTIONARY,
    /**
     * A value that represents that word is not of the word size.
     */
    INVALID_SIZE,
    /**
     * A value that represents that validation found an invalid character.
     */
    INVALID_CHARACTER,
    /**
     * A value that represents that validation found a word had been previously guessed.
     */
    PREVIOUSLY_GUESSED
}
