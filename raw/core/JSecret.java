package jotto.core;

/**
 * Represents the secret word from the jotto dictionary.
 */
public final class JSecret {

	private final Jotto _jotto;
	private final JWord _secret;

	/**
	 * Initializes a secret word for the jotto game.
	 *
	 * @param jotto
	 *            The jotto game which this secret is associated with.
	 * @param secret
	 *            The word from the jotto dictionary that will be the secret
	 *            word.
	 */
	public JSecret(Jotto jotto, JWord secret) {
		assert jotto != null && secret != null;

		_jotto = jotto;
		_secret = secret;
	}

	/**
	 * Returns the number of attempts at the secret word in the jotto game.
	 *
	 * @return The number of attempts at the secret word.
	 */
	public int getAttempts() {
		return _jotto.getAttempts();
	}

	/**
	 * Guesses a word against the secret word.
	 *
	 * @param data
	 *            The word that will be guessed.
	 * @return A jotto guess object representing the guess outcome.
	 */
	public JGuess guess(String data) {
		assert data != null;

		// gets the text of the secret
		String secret = _secret.getWord();

		// initializes the matches
		JMatch[] matches = new JMatch[secret.length()];
		for (int i = 0; i < secret.length(); i++) {
			matches[i] = JMatch.NONE;
		}

		// determines the exact matches with the words
		for (int i = 0; i < secret.length(); i++) {
			if (secret.charAt(i) == data.charAt(i)) {
				matches[i] = JMatch.EXACT;
			}
		}

		// determines the matches based on partial/none
		for (int i = 0; i < secret.length(); i++) {
			if (matches[i] != JMatch.NONE) {
				continue;
			}

			for (int r = 0; r < data.length(); r++) {
				if (data.charAt(r) == secret.charAt(i)) {
					matches[r] = JMatch.PARTIAL;
					break;
				}
			}
		}

		// returns a JGuess instance
		return new JGuess(data, matches);
	}
}
