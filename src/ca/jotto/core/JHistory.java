package ca.jotto.core;

import java.util.ArrayList;
import java.util.Arrays;

import ca.jotto.listeners.JottoEventMap;

/**
 * Represents the history of user guesses in the jotto game.
 */
public class JHistory {

	private final ArrayList<JGuess> _guesses;
	private final JCharset _charset;
	private final JMatch[] _letters;
	private final int _wordsize;
	private final char[] _known;
	private final ArrayList<JCharIndex> _exactMatches;

	/**
	 * Initializes the history of a jotto game.
	 */
	public JHistory(JCharset characterSet, int wordSize) {
		assert characterSet != null;

		_wordsize = wordSize;
		_charset = characterSet;
		_guesses = new ArrayList<JGuess>();
		_known = new char[wordSize];
		_letters = new JMatch[_charset.getCount()];
		_exactMatches = new ArrayList<JCharIndex>();

		for (int i = 0; i < _known.length; i++) {
			_known[i] = Character.UNASSIGNED;
		}

		for (int i = 0; i < _letters.length; i++) {
			_letters[i] = JMatch.NONE;
		}
	}

	/**
	 * Adds a guess to the history of the jotto game.
	 * 
	 * @param guess
	 *            Adds a jotto guess into the history.
	 */
	public void add(JottoEventMap eventMap, JGuess guess) {
		assert guess != null;

		_guesses.add(guess);

		for (JGuess ges : _guesses) {
			if (ges.getPartial() == 0 && ges.getExact() == 0) {
				for (int l = 0; l < _wordsize; l++) {
					int index = _charset.getIndex(ges.getChar(l));
					_letters[index] = JMatch.ELIMINATED;

					// eventMap.onCharacterEliminated(character);
				}
			} else {
				for (int l = 0; l < _wordsize; l++) {
					if (ges.getMatch(l) == JMatch.EXACT) {
						char exactChar = ges.getChar(l);
						int index = _charset.getIndex(exactChar);

						_letters[index] = JMatch.EXACT;
						_known[l] = exactChar;

						// eventMap..onCharacterExact(character);
					}
				}
			}
		}
	}

	/**
	 * Gets the match state of the various characters in the set.
	 * 
	 * @return The match states of characters.
	 */
	public JMatch[] getConfirms() {
		return _letters.clone();
	}

	/**
	 * Get the match state for the specific character.
	 * 
	 * @param character
	 *            The character to retrieve match state for.
	 * @return The match state of a specified character.
	 */
	public JMatch getCharacterMatch(char character) {
		if (!_charset.isValid(character)) {
			throw new IllegalArgumentException(
					"The argument 'character' is not present in the character set.");
		}

		int index = _charset.getIndex(character);
		return _letters[index];
	}

	/**
	 * Gets a list of letters that are partial (suggested to guess).
	 * 
	 * @return The suggested string.
	 */
	public String getSuggestions() {
		StringBuilder buffer = new StringBuilder();

		for (JGuess guess : _guesses) {
			for (int i = 0; i < _wordsize; i++) {
				JMatch match = guess.getMatch(i);
				if (match == JMatch.EXACT || match == JMatch.PARTIAL) {
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

	public JCharIndex[] GetSuggested(int index) {
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

	/**
	 * Returns true if the word has been guessed, false otherwise.
	 * 
	 * @return True if word has been guessed; false otherwise.
	 * */
	public boolean hasGuessed(String word) {
		assert word != null;

		for (JGuess guess : _guesses) {
			if (guess.getGuess().equals(word)) {
				return true;
			}
		}
		return false;
	}
}
