package ca.jotto.model;

/**
 * Represents the secret word for the match.
 */
public final class JSecret {

    private final JWord _secret;

    /**
     * Initializes a secret word for the match.
     *
     * @param secret The word chosen to be the secret word.
     */
    public JSecret(JWord secret) {
        assert secret != null : "The provided JWord 'secret' cannot be null";

        _secret = secret;
    }

    /**
     * Guesses a word against the secret word.
     *
     * @param word The word that will be guessed.
     * @return A guess object representing the guess outcome.
     */
    public JGuess guess(String word) {
        assert word != null : "The provided String 'word' cannot be null";
        assert word.length() == _secret.length() : "The provided String 'word' must equal the dictionary word length";

        StringBuilder secret = new StringBuilder(_secret.word());

        JWordMatch[] matches = new JWordMatch[secret.length()];
        for (int i = 0; i < secret.length(); i++) {
            matches[i] = JWordMatch.NONE;
        }

        int exact = 0;
        int partial = 0;

        for (int i = 0; i < word.length(); i++) {
            if (secret.charAt(i) == word.charAt(i)) {
                secret.setCharAt(i, (char) Character.UNASSIGNED);
                matches[i] = JWordMatch.EXACT;
                exact++;
            }
        }

        for (int i = 0; i < word.length(); i++) {
            if (matches[i] != JWordMatch.NONE) {
                continue;
            }

            for (int r = 0; r < secret.length(); r++) {
                if (word.charAt(i) == secret.charAt(r)) {
                    secret.setCharAt(r, (char) Character.UNASSIGNED);
                    matches[i] = JWordMatch.PARTIAL;
                    partial++;
                }
            }
        }

        return new JGuess(word, matches, exact, partial);
    }
}
