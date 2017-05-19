package ca.jotto.model;

/**
 * Represents the status of a single letter in the match.
 */
public enum JLetterStatus {
    /**
     * No known information about the letter with respect to the secret in the {@link JMatch}.
     */
    NONE,
    /**
     * The letter has been verified to not exist within the secret in the {@link JMatch}.
     */
    ELIMINATED,
    /**
     * The letter has been verified to exist within the secret in the {@link JMatch}.
     */
    DISCOVERED
}