package ca.jotto.model;

/**
 * Represents the secret word that should be kept confidential.
 */
public final class JSecret {

    private final JWord _secret;

    /**
     * Initializes a new instance of the {@link JSecret} class with the specified secret.
     *
     * @param secret The {@link JWord} to set as the secret.
     */
    public JSecret(JWord secret) {
        assert secret != null : "The provided JWord 'secret' cannot be null";

        _secret = secret;
    }

    /**
     * Guesses the specified input string against the secret specified in the constructor.
     *
     * @param word The string to guess for a match.
     * @return An object that contains information about the guess.
     */
    public JGuess guess(String word) {
        assert word != null : "The provided String 'word' cannot be null";
        assert word.length() == _secret.length() : "The provided String 'word' must equal the secret word length";

        int exact = 0;
        int partial = 0;
        JWordMatch[] matches = JWordMatch.compareTo(word, _secret.word());
        for (int i = 0; i < _secret.length(); i++) {
            switch (matches[i]) {
                case EXACT:
                    exact++;
                    break;
                case PARTIAL:
                    partial++;
                    break;
            }
        }

        return new JGuess(word, matches, exact, partial);
    }
}
