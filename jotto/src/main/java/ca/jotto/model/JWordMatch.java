package ca.jotto.model;

/**
 * Represents the results from a single character {@link JGuess} match.
 */
public enum JWordMatch {
    /**
     * The character occurs within the {@link String}.
     */
    PARTIAL,
    /**
     * The character occurs within the {@link String} at the same index.
     */
    EXACT,
    /**
     * The character does not occur within the {@link String}.
     */
    NONE;

    /**
     * Compares two specified String objects, and returns an array of character matches that indicates their similarity.
     *
     * @param guess  The first string to compare.
     * @param secret The second string to compare.
     * @return A {@link JWordMatch} array whose elements indicates the similarity relationship between the two comparands.
     */
    static public JWordMatch[] compareTo(String guess, String secret) {
        assert guess != null : "The provided String 'word' cannot be null";
        assert secret != null : "The provided String 'word' cannot be null";
        assert guess.length() == secret.length() : "The provided String 'word' must equal the dictionary word length";

        StringBuilder value = new StringBuilder(secret);

        JWordMatch[] matches = new JWordMatch[guess.length()];
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == value.charAt(i)) {
                value.setCharAt(i, (char) Character.UNASSIGNED);
                matches[i] = JWordMatch.EXACT;
            } else {
                matches[i] = JWordMatch.NONE;
            }
        }

        for (int i = 0; i < guess.length(); i++) {
            if (matches[i] != JWordMatch.NONE) {
                continue;
            }

            for (int r = 0; r < secret.length(); r++) {
                if (guess.charAt(i) == secret.charAt(r) && value.charAt(r) != Character.UNASSIGNED) {
                    value.setCharAt(r, (char) Character.UNASSIGNED);
                    matches[i] = JWordMatch.PARTIAL;
                }
            }
        }

        return matches;
    }
}