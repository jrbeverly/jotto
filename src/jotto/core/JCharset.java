package jotto.core;

public class JCharset {
	private final int _alphabet;
	private final char _start;
	private final char _end;

	public JCharset(char start, char end) {
		_start = start;
		_end = end;
		_alphabet = end - start;
	}

	public int getCount()
	{
		return _alphabet;
	}
	
	public int getIndex(char character) {
		if (character < _start || character > _end) {
			return -1;
		}

		return character - _start;
	}

	public Boolean isValid(char character) {
		return !(character < _start || character > _end);
	}

	public Boolean isValid(String word) {
		for(char wch : word.toCharArray())
		{
			if(!isValid(wch))
			{
				return false;
			}
		}
		return true;
	}
}
