package jotto.core;

/**
 * Enumeration for determining whether a jotto guess is valid
 *
 */
public enum JValidation {

	/**
	 * The jotto guess is valid
	 */
	VALID,
	/**
	 * The jotto guess is not in the dictionary
	 */
	NOT_IN_DICTIONARY,
	/**
	 * The jotto guess is not the correct word size
	 */
	INVALID_SIZE,
	/**
	 * The jotto guess contains a character that is not valid
	 */
	INVALID_CHARACTER,
	/**
	 * The player has guessed this before
	 */
	PREVIOUSLY_GUESSED
}
