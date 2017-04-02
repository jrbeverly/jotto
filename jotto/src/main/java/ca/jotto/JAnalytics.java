package ca.jotto;

import ca.jotto.listeners.JottoEventMap;

/**
 * Defines analytics for the match.
 */
public final class JAnalytics {

    private final JWordMatch[] _letters;
    private final char[] _known;
    private final int _size;
    private final JCharset _charset;

    /**
     * Initializes the analytics module of the match.
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
        _letters = new JWordMatch[characters];
        _charset = charset;

        for (int i = 0; i < _known.length; i++) {
            _known[i] = Character.UNASSIGNED;
        }

        for (int i = 0; i < _letters.length; i++) {
            _letters[i] = JWordMatch.NONE;
        }
    }

    /**
     * Computes the analytics based on the current state of the match.
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
            if (ges.getPartial() == 0 && ges.getExact() == 0) {
                for (int l = 0; l < _size; l++) {
                    Character ch = ges.getChar(l);
                    int index = _charset.get(ch);
                    _letters[index] = JWordMatch.ELIMINATED;

                    eventMap.onCharacterEliminated(jotto, ch);
                }
            } else {
                for (int l = 0; l < _size; l++) {
                    if (ges.getMatch(l) == JWordMatch.EXACT) {
                        char exactChar = ges.getChar(l);
                        int index = _charset.get(exactChar);

                        _letters[index] = JWordMatch.EXACT;
                        _known[l] = exactChar;

                        eventMap.onCharacterExact(jotto, exactChar);
                    }
                }
            }
        }
    }
}