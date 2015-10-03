package ca.jotto.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * A dictionary containing a collection of words available for use in a jotto
 * game.
 */
public final class JDictionary {

	private JCharset _charset;
	private final Map<Integer, ArrayList<JWord>> _difficultyMap;
	private final ArrayList<JWord> _words;
	private final HashMap<String, JWord> _wordMap;
	private final int _wordsize;

	private int _minDifficulty;
	private int _maxDifficulty;

	/**
	 * Initializes the jotto dictionary based on a specific word size.
	 * 
	 * @param wordSize
	 *            The size of the dictionary words.
	 */
	public JDictionary(int wordSize) {
		_charset = JCharset.DEFAULT;
		_difficultyMap = new HashMap<Integer, ArrayList<JWord>>();
		_words = new ArrayList<JWord>();
		_wordMap = new HashMap<String, JWord>();

		_minDifficulty = Integer.MAX_VALUE;
		_maxDifficulty = Integer.MIN_VALUE;
		_wordsize = wordSize;
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

		if (word.isEmpty()) {
			throw new IllegalArgumentException(
					"The argument 'word' cannot be the empty string.");
		}

		if (_charset.isValid(word)) {
			throw new IllegalArgumentException(
					"The argument 'word' does not match the character set of the dictionary.");
		}

		JWord jword = new JWord(word, difficulty);

		ArrayList<JWord> lists = _difficultyMap.getOrDefault(difficulty, null);
		if (lists == null) {
			lists = new ArrayList<JWord>();
			_difficultyMap.put(difficulty, lists);
		}

		lists.add(jword);
		_words.add(jword);
		_wordMap.put(jword.getWord(), jword);

		_minDifficulty = Math.min(_minDifficulty, difficulty);
		_maxDifficulty = Math.min(_maxDifficulty, difficulty);
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

		return _wordMap.containsKey(word);
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

		Pattern pattern = Pattern.compile(format);

		ArrayList<JWord> words = new ArrayList<JWord>();
		for (JWord wrd : _words) {
			if (pattern.matcher(wrd.getWord()).matches()) {
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

		if (word.isEmpty()) {
			throw new IllegalArgumentException(
					"The argument 'word' cannot be the empty string.");
		}

		if (!isWordPresent(word)) {
			throw new IllegalArgumentException(
					"The argument 'word' is not present in the dictionary.");
		}

		return _wordMap.get(word);
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

	/**
	 * Gets the character set of the words.
	 * 
	 * @return The character set of the dictionary.
	 */
	public JCharset getCharset() {
		return _charset;
	}

	/**
	 * Returns the number of words that are within the dictionary.
	 *
	 * @return The number of words within the dictionary.
	 */
	public int count() {
		return _words.size();
	}

	/**
	 * Returns the dictionary word size.
	 *
	 * @return The length of words within the dictionary.
	 */
	public int size() {
		return _wordsize;
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
			throw new IllegalArgumentException(
					"The argument 'difficulty' is less than the minimum difficulty of the dictionary.");
		} else if (difficulty > _maxDifficulty) {
			throw new IllegalArgumentException(
					"The argument 'difficulty' is less than the maximum difficulty of the dictionary.");
		}

		Random ran = new Random();
		ArrayList<JWord> list = _difficultyMap.get(difficulty);
		if (list.isEmpty()) {
			throw new IllegalArgumentException(
					"There exists no words associated with the specified difficulty.");
		}

		int index = ran.nextInt(list.size() - 1);
		return list.get(index);
	}

	/**
	 * Clears the jotto dictionary.
	 */
	public void clear() {
		_difficultyMap.clear();
		_words.clear();
		_wordMap.clear();
	}

	/**
	 * Imports the a series of words into the jotto dictionary from a file.
	 *
	 * @param filepath
	 *            The file to import into the dictionary.
	 * @return True if dictionary loading successful; false otherwise.
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void importFrom(String filepath) throws FileNotFoundException,
			IOException {
		assert filepath != null;

		File dFile = new File(filepath);
		if (!dFile.exists()) {
			throw new FileNotFoundException(
					"The specified file could not be found: "
							+ dFile.getAbsolutePath());
		}

		FileReader fReader = new FileReader(dFile);
		BufferedReader bReader = new BufferedReader(fReader);
		String txt;
		while (bReader.ready()) {
			txt = bReader.readLine();

			String[] data = txt.split(" ");
			String word = data[0];
			int diff = Integer.parseInt(data[1]);

			add(word, diff);
		}

		bReader.close();
		fReader.close();
	}

	/**
	 * Exports the a series of words from the jotto dictionary into a file. If
	 * the file exists it is overwritten.
	 *
	 * @param filepath
	 *            The file to export the dictionary to.
	 * @return True if dictionary exporting successful; false otherwise.
	 * @throws IOException
	 */
	public void exportTo(String filepath) throws IOException {
		assert filepath != null;

		File dFile = new File(filepath);
		if (!dFile.exists()) {
			dFile.delete();
		}

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
	}
}
