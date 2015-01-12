package jotto.core;

/**
 * A set of characters that can be used within the jotto game.
 * */
public class JCharset {
	private final int _alphabet;
	private final char _start;
	private final char _end;

	/**
	 * Creates a character set from a range between two characters.
	 * 
	 * @param start
	 *            The start character of the set range.
	 * @param end
	 *            The end character of the set range.
	 * */
	public JCharset(char start, char end) {
		_start = start;
		_end = end;
		_alphabet = end - start;
	}

	/**
	 * Returns the number of characters in the character set.
	 * 
	 * @return Returns number of characters in set.
	 * */
	public int getCount() {
		return _alphabet;
	}

	/**
	 * Returns the normalized index of the specified character.
	 * 
	 * @return Returns the index of the character.
	 * */
	public int getIndex(char character) {
		if (character < _start || character > _end) {
			return -1;
		}

		return character - _start;
	}

	/**
	 * Returns true if the character is within the set.
	 * 
	 * @return True if within the set; false otherwise.
	 * */
	public Boolean isValid(char character) {
		return character >= _start && character <= _end;
	}

	/**
	 * Returns true if the string is within the set.
	 * 
	 * @return True if within the set; false otherwise.
	 * */
	public Boolean isValid(String word) {
		String lcase = word.toLowerCase();
		for (char wch : lcase.toCharArray()) {
			if (!isValid(wch)) {
				return false;
			}
		}
		return true;
	}
}
