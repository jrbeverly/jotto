package jotto.core;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the history of user guesses in the jotto game
 *
 */
public class JHistory {

    private final ArrayList<JGuess> _guesses;
    private final Jotto _jotto;
    private final JMatch[] _letters;
    private final char[] _known;

    /**
     * Initializes the history of a jotto game
     */
    public JHistory(Jotto jotto) {
    	assert jotto != null;
    	
        _jotto = jotto;
        _guesses = new ArrayList<JGuess>();
        _letters = new JMatch[JWord.ALPHABET];
        _known = new char[jotto.getWordSize()];

        clear();
    }

    /**
     * Adds a guess to the history of the jotto game
     */
    public void add(JGuess guess) {
    	assert guess != null;
    	
        _guesses.add(guess);

        for (JGuess ges : _guesses) {
            if (ges.getPartial() == 0 && ges.getExact() == 0) {
                for (int l = 0; l < _jotto.getWordSize(); l++) {
                    setState(JMatch.ELIMINATED, ges.getChar(l));
                }
            } else {
                for (int l = 0; l < _jotto.getWordSize(); l++) {
                    if (ges.getMatch(l) == JMatch.EXACT) {
                        _known[l] = ges.getChar(l);

                        setState(JMatch.EXACT, ges.getChar(l));
                    }
                }
            }
        }
    }

    private void setState(JMatch match, char character) {
    	assert match != null;
    	
        int index = JWord.getIndex(character);
        switch (match) {
            case ELIMINATED:
                _jotto.getEventMap().onCharacterEliminated(character);
                break;
            case EXACT:
                _jotto.getEventMap().onCharacterExact(character);
                break;
            default:
                break;
        }
        _letters[index] = match;
    }

    /**
     * Gets the letters that are matched
     */
    public JMatch[] getConfirms() {
        return _letters;
    }

    /**
     * Get the match set for the specific character
     */
    public JMatch getCharacterMatch(char character) {
        int index = JWord.getIndex(character);
        if (index != -1 || index < 0 || index >= _letters.length) {
            return null;
        }

        return _letters[index];
    }

    /**
     * Gets a list of letters that are partial (suggested to guess)
     */
    public String getSuggestions() {
        StringBuilder buffer = new StringBuilder();

        for (JGuess guess : _guesses) {
            for (int i = 0; i < _jotto.getWordSize(); i++) {
                JMatch match = guess.getMatch(i);
                if ((match == JMatch.EXACT) || (match == JMatch.PARTIAL)) {
                    if (buffer.indexOf(guess.getChar(i) + "") == -1) {
                        buffer.append(guess.getChar(i));
                    }
                }
            }
        }

        char[] data = buffer.toString().toCharArray();
        Arrays.sort(data);
        return new String(data);
    }

    /**
     * Gets the letters that are confirmed
     */
    public String getKnown() {
        return new String(_known.clone());
    }

    public boolean hasGuessed(String word) {
    	assert word != null;
    	
        for (JGuess guess : _guesses) {
            if (guess.getGuess().equals(word)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < _known.length; i++) {
            _known[i] = JWord.UNKNOWN_CHAR;
        }

        for (int i = 0; i < _letters.length; i++) {
            _letters[i] = JMatch.NONE;
        }

        _guesses.clear();
    }
}
