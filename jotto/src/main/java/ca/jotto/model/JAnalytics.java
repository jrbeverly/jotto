package ca.jotto.model;

import ca.jotto.model.listeners.JottoEventMap;

/**
 * Provides various capabilities related to historical analytics.
 */
public final class JAnalytics {

    private final JLetterStatus[] _letters;
    private final char[] _known;
    private final int _size;
    private final JCharset _charset;

    /**
     * Initializes the analytics object for the match.
     *
     * @param charset The character set of the match.
     * @param size    The size of words in the dictionary.
     */
    public JAnalytics(JCharset charset, int size) {
        assert charset != null : "The provided JCharset 'charset' cannot be null";
        assert size > 0 : "The provided Integer 'size' cannot be null";

        int characters = charset.length();

        _size = size;
        _known = new char[size];
        _letters = new JLetterStatus[characters];
        _charset = charset;

        for (int i = 0; i < _known.length; i++) {
            _known[i] = Character.UNASSIGNED;
        }

        for (int i = 0; i < _letters.length; i++) {
            _letters[i] = JLetterStatus.NONE;
        }
    }

    /**
     * Computes the historical analytics based on the latest changes to the history.
     *
     * @param jotto    The jotto game.
     * @param eventMap The event map for the match.
     * @param history  The history of guesses.
     */
    public void compute(Jotto jotto, JottoEventMap eventMap, JHistory history) {
        assert jotto != null : "The provided Jotto 'jotto' cannot be null";
        assert eventMap != null : "The provided JottoEventMap 'eventMap' cannot be null";
        assert history != null : "The provided JHistory 'history' cannot be null";

        for (JGuess ges : history.guesses()) {
            if (ges.partial() == 0 && ges.exact() == 0) {
                for (int l = 0; l < _size; l++) {
                    Character ch = ges.charAt(l);
                    int index = _charset.get(ch);
                    _letters[index] = JLetterStatus.NONE;

                    eventMap.onCharacterEliminated(jotto, ch);
                }
            } else {
                for (int l = 0; l < _size; l++) {
                    if (ges.matchAt(l) == JWordMatch.EXACT) {
                        char exactChar = ges.charAt(l);
                        int index = _charset.get(exactChar);

                        _letters[index] = JLetterStatus.DISCOVERED;
                        _known[l] = exactChar;

                        eventMap.onCharacterExact(jotto, exactChar);
                    }
                }
            }
        }
    }
}