package jotto.core;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the history of user guesses in the jotto game.
 */
public class JHistory {

	private final ArrayList<JGuess> _guesses;
	private final Jotto _jotto;
	private final JCharset _characters;
	private final JMatch[] _letters;
	private final char[] _known;

	/**
	 * Initializes the history of a jotto game.
	 * 
	 * @param jotto
	 *            The jotto game which this history is associated with.
	 */
	public JHistory(Jotto jotto) {
		assert jotto != null;

		_jotto = jotto;
		_characters = _jotto.getCharset();

		_guesses = new ArrayList<JGuess>();
		_known = new char[jotto.getWordSize()];
		_letters = new JMatch[_characters.getCount()];

		clear();
	}

	/**
	 * Adds a guess to the history of the jotto game.
	 * 
	 * @param guess
	 *            Adds a jotto guess into the history.
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

	/**
	 * Sets the state of the character match.
	 * 
	 * @param match
	 *            The matching of the character.
	 * @param character
	 *            The character match state to assign.
	 */
	private void setState(JMatch match, char character) {
		assert match != null;

		int index = _characters.getIndex(character);
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
	 * Gets the match state of the various characters in the set.
	 * 
	 * @return The match states of characters.
	 */
	public JMatch[] getConfirms() {
		return _letters;
	}

	/**
	 * Get the match state for the specific character.
	 * 
	 * @param character
	 *            The character to retrieve match state for.
	 * @return The match state of a specified character.
	 */
	public JMatch getCharacterMatch(char character) {
		int index = _characters.getIndex(character);
		if (index != -1 || index < 0 || index >= _letters.length) {
			return null;
		}

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
	 * Gets the letters that are confirmed.
	 * 
	 * @return A string containing known characters.
	 */
	public String getKnown() {
		return new String(_known.clone());
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

	/**
	 * Clears the attributes of the history.
	 * */
	public void clear() {
		for (int i = 0; i < _known.length; i++) {
			_known[i] = '_';
		}

		for (int i = 0; i < _letters.length; i++) {
			_letters[i] = JMatch.NONE;
		}

		_guesses.clear();
	}
}
