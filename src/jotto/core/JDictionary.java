package jotto.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A dictionary containing a collection of words available for use in a jotto
 * game.
 */
public final class JDictionary {

	private final Map<Integer, ArrayList<JWord>> _wordmap;
	private final ArrayList<JWord> _words;
	private JCharset _characters;
	private int _minDifficulty;
	private int _maxDifficulty;
	private int _size;

	/**
	 * Initializes the jotto dictionary based on a specific word size.
	 * 
	 * @param dictSize
	 *            The size of the dictionary words.
	 */
	public JDictionary(int dictSize) {
		_wordmap = new HashMap<Integer, ArrayList<JWord>>();
		_words = new ArrayList<JWord>();
		_characters = new JCharset('A', 'Z');

		_minDifficulty = Integer.MAX_VALUE;
		_maxDifficulty = Integer.MIN_VALUE;
		_size = dictSize;
	}

	/**
	 * Adds a word to the dictionary.
	 *
	 * @param word
	 *            The word to add to the dictionary.
	 * @param difficulty
	 *            The difficulty of the specific word.
	 */
	public void add(String word, int difficulty) {
		assert word != null && difficulty > 0;
		_size = word.length(); // set word length

		JWord jword = new JWord(word, difficulty);
		if (!_wordmap.containsKey(difficulty)) {
			_wordmap.put(difficulty, new ArrayList<JWord>());
		}

		ArrayList<JWord> lists = _wordmap.get(difficulty);
		lists.add(jword);
		_words.add(jword);

		if (difficulty < _minDifficulty) {
			_minDifficulty = difficulty;
		} else if (difficulty > _maxDifficulty) {
			_maxDifficulty = difficulty;
		}
	}

	/**
	 * Determines if the specified word is present in the dictionary.
	 *
	 * @param word
	 *            The word to detect if present within the dictionary.
	 * @return True if the word exists in the dictionary; false otherwise.
	 */
	public Boolean isWordPresent(String word) {
		assert word != null;

		for (int i = 0; i < _words.size(); i++) {
			if (_words.get(i).getWord().equals(word)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Gets a list of words that match the specified regular expression.
	 *
	 * @param format
	 *            The regular expression to which strings are to be matched.
	 * @return This method returns an array of words if, and only if, this
	 *         string matches the given regular expression.
	 */
	public JWord[] getMatchingWords(String format) {
		assert format != null;

		ArrayList<JWord> words = new ArrayList<JWord>();
		for (JWord wrd : _words) {
			if (wrd.getWord().matches(format)) {
				words.add(wrd);
			}
		}

		return words.toArray(new JWord[words.size()]);
	}

	/**
	 * Returns the word object associated with the word.
	 *
	 * @param word
	 *            The word to retrieve from the dictionary.
	 * @return The jotto word object.
	 */
	public JWord getWord(String word) {
		assert word != null;

		for (int i = 0; i < _words.size(); i++) {
			if (_words.get(i).getWord().equals(word)) {
				return _words.get(i);
			}
		}
		return null;
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index
	 *            The index of the element to return.
	 * @return This method returns the element at the specified position in this
	 *         list.
	 */
	public JWord getWord(int index) {
		assert index >= 0 && index < _words.size();
		return _words.get(index);
	}

	/**
	 * Gets the list of words within the dictionary.
	 *
	 * @return The words of the dictionary.
	 */
	public JWord[] getWords() {
		return _words.toArray(new JWord[_words.size()]);
	}

	public JCharset getCharset() {
		return _characters;
	}

	/**
	 * Returns the number of words that are within the dictionary.
	 *
	 * @return The number of words within the dictionary.
	 */
	public int count() {
		return _words.size();
	}

	public int size() {
		return _size;
	}

	/**
	 * Gets a random word from the dictionary with a specified difficulty.
	 * 
	 * @param difficulty
	 *            The difficulty of the words to query against.
	 * @return The word within the dictionary.
	 */
	public JWord getRandomWord(int difficulty) {
		if (difficulty < _minDifficulty) {
			return null;
		} else if (difficulty > _maxDifficulty) {
			return null;
		}

		Random ran = new Random();
		ArrayList<JWord> list = _wordmap.get(difficulty);
		int index = ran.nextInt(list.size() - 1);
		JWord result = list.get(index);

		return result;
	}

	/**
	 * Clears the jotto dictionary.
	 */
	public void clear() {
		_wordmap.clear();
		_words.clear();
	}

	/**
	 * Imports the a series of words into the jotto dictionary from a file.
	 *
	 * @param filepath
	 *            The file to import into the dictionary.
	 * @return True if dictionary loading successful; false otherwise.
	 */
	public Boolean importFrom(String filepath) {
		assert filepath != null;

		File dFile = new File(filepath);
		if (!dFile.exists()) {
			System.out.println("Cannot find file: " + dFile.getAbsolutePath());
			return false;
		}

		try {
			FileReader fReader = new FileReader(dFile);
			BufferedReader bReader = new BufferedReader(fReader);

			try {
				String txt;
				while (bReader.ready()) {
					txt = bReader.readLine();

					String[] data = txt.split(" ");
					String word = data[0];
					int diff = Integer.parseInt(data[1]);

					add(word, diff);
				}
			} catch (IOException io_read) {
				io_read.printStackTrace();
			}

			bReader.close();
			fReader.close();
		} catch (IOException io_open) {
			io_open.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Exports the a series of words from the jotto dictionary into a file. If
	 * the file exists it is overwritten.
	 *
	 * @param filepath
	 *            The file to export the dictionary to.
	 * @return True if dictionary exporting successful; false otherwise.
	 */
	public Boolean exportTo(String filepath) {
		assert filepath != null;

		File dFile = new File(filepath);
		if (!dFile.exists()) {
			dFile.delete();
		}

		try {
			FileWriter fWriter = new FileWriter(dFile);
			BufferedWriter bWriter = new BufferedWriter(fWriter);

			try {
				for (int i = 0; i < _words.size(); i++) {
					JWord word = _words.get(i);

					bWriter.write(word.getWord());
					bWriter.write(" ");
					bWriter.write(word.getDifficulty());
					bWriter.newLine();
				}
			} catch (IOException io_read) {
				io_read.printStackTrace();
			}

			bWriter.close();
			fWriter.close();
		} catch (IOException io_open) {
			io_open.printStackTrace();
			return false;
		}

		return true;
	}
}
