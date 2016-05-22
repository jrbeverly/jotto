package ca.jotto;

/**
 * Represents the secret word from the jotto dictionary.
 */
public final class JSecret {

    private final JWord _secret;

    /**
     * Initializes a secret word for the jotto game.
     *
     * @param secret The word from the jotto dictionary that will be the secret word.
     */
    public JSecret(JWord secret) {
        assert secret != null : "The provided JWord 'secret' cannot be null";

        _secret = secret;
    }

    /**
     * Guesses a word against the secret word.
     *
     * @param word The word that will be guessed.
     * @return A jotto guess object representing the guess outcome.
     */
    public JGuess guess(String word) {
        assert word != null : "The provided String 'word' cannot be null";
        assert word.length() == _secret.length() : "The provided String 'word' must equal the dictionary word length";

        // gets the text of the secret
        String secret = _secret.getWord();

        // initializes the matches
        JWordMatch[] matches = new JWordMatch[secret.length()];
        int exact = 0;
        int partial = 0;
        for (int i = 0; i < secret.length(); i++) {
            matches[i] = JWordMatch.NONE;
        }

        // determines the exact matches with the words
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == word.charAt(i)) {
                matches[i] = JWordMatch.EXACT;
                exact++;
            }
        }

        // determines the matches based on partial/none
        for (int i = 0; i < secret.length(); i++) {
            if (matches[i] != JWordMatch.NONE) {
                continue;
            }

            for (int r = 0; r < word.length(); r++) {
                if (word.charAt(r) == secret.charAt(i)) {
                    matches[r] = JWordMatch.PARTIAL;
                    partial++;
                    break;
                }
            }
        }

        // returns a JGuess instance
        return new JGuess(word, matches, exact, partial);
    }
}
