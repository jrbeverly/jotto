package ca.jotto;

import java.util.ArrayList;

/**
 * Defines analytics for the jotto game.
 */
public class JAnalytics {
    private final JWordMatch[] _letters;
    private final char[] _known;
    private final ArrayList<JCharIndex> _exactMatches;
    private final int _wordsize;
    private final JCharset _charset;

    public JAnalytics(JCharset charset, int wordsize) {
        int characters = charset.length();

        _wordsize = wordsize;
        _known = new char[wordsize];
        _letters = new JWordMatch[characters];
        _exactMatches = new ArrayList<JCharIndex>();
        _charset = charset;

        for (int i = 0; i < _known.length; i++) {
            _known[i] = Character.UNASSIGNED;
        }

        for (int i = 0; i < _letters.length; i++) {
            _letters[i] = JWordMatch.NONE;
        }
    }

    /**
     * Gets the match state of the various characters in the set.
     *
     * @return The match states of characters.
     */
    public JWordMatch[] getConfirms() {
        return _letters.clone();
    }


    public JCharIndex[] getSuggested(int index) {
        assert index > 0;
        return null;
    }

    /**
     * Gets the letters that are confirmed.
     *
     * @return A string containing known characters.
     */
    public JCharIndex[] getKnown() {
        return _exactMatches.toArray(new JCharIndex[_exactMatches.size()]);
    }

    public void compute(ArrayList<JGuess> guesses) {
        for (JGuess ges : guesses) {
            if (ges.getPartial() == 0 && ges.getExact() == 0) {
                for (int l = 0; l < _wordsize; l++) {
                    int index = _charset.get(ges.getChar(l));
                    _letters[index] = JWordMatch.ELIMINATED;
                }
            } else {
                for (int l = 0; l < _wordsize; l++) {
                    if (ges.getMatch(l) == JWordMatch.EXACT) {
                        char exactChar = ges.getChar(l);
                        int index = _charset.get(exactChar);

                        _letters[index] = JWordMatch.EXACT;
                        _known[l] = exactChar;
                    }
                }
            }
        }
    }
}
