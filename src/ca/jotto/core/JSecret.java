package ca.jotto.core;

/**
 * Represents the secret word from the jotto dictionary.
 */
public final class JSecret {

	private final JWord _secret;
	private final int _maxAttempts;
	private int _numAttempts;

	/**
	 * Initializes a secret word for the jotto game.
	 *
	 * @param secret
	 *            The word from the jotto dictionary that will be the secret
	 *            word.
	 */
	public JSecret(JWord secret, int maxNumAttempts) {
		assert secret != null && maxNumAttempts > 0;

		_secret = secret;
		_numAttempts = 0;
		_maxAttempts = maxNumAttempts;
	}

	/**
	 * Returns the number of attempts at the secret word in the jotto game.
	 *
	 * @return The number of attempts at the secret word.
	 */
	public int getAttempts() {
		return _numAttempts;
	}

	/**
	 * Guesses a word against the secret word.
	 *
	 * @param word
	 *            The word that will be guessed.
	 * @return A jotto guess object representing the guess outcome.
	 * @throws Exception
	 */
	public JGuess guess(String word) throws Exception {
		assert word != null;

		if (_numAttempts >= _maxAttempts) {
			throw new Exception(
					"The maximum number of guesses for this secret have been set.");
		}

		// gets the text of the secret
		String secret = _secret.getWord();
		_numAttempts++;

		// initializes the matches
		JMatch[] matches = new JMatch[secret.length()];
		for (int i = 0; i < secret.length(); i++) {
			matches[i] = JMatch.NONE;
		}

		// determines the exact matches with the words
		for (int i = 0; i < secret.length(); i++) {
			if (secret.charAt(i) == word.charAt(i)) {
				matches[i] = JMatch.EXACT;
			}
		}

		// determines the matches based on partial/none
		for (int i = 0; i < secret.length(); i++) {
			if (matches[i] != JMatch.NONE) {
				continue;
			}

			for (int r = 0; r < word.length(); r++) {
				if (word.charAt(r) == secret.charAt(i)) {
					matches[r] = JMatch.PARTIAL;
					break;
				}
			}
		}

		// returns a JGuess instance
		return new JGuess(word, matches);
	}
}
