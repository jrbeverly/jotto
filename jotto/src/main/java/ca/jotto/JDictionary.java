package ca.jotto;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * A dictionary containing a collection of words available for use in a game.
 */
public final class JDictionary {

    private final int _size;
    private final int _minDifficulty;
    private final int _maxDifficulty;
    private final JCharset _charset;
    private final ArrayList<JWord> _words;
    private final Map<Integer, ArrayList<JWord>> _difficultyMap;
    private final HashMap<String, JWord> _wordMap;

    /**
     * Initializes the dictionary based on a specific word size.
     *
     * @param charset The character set of the dictionary.
     * @param wordSize The size of the dictionary words.
     * @param words The words of the area.
     */
    public JDictionary(JCharset charset, int wordSize, ArrayList<JWord> words) {
        assert charset != null : "The provided JCharset 'charset' cannot be null";
        assert words != null : "The provided ArrayList<JWord> 'words' cannot be null";
        assert wordSize > 0 : "The provided Integer 'wordSize' cannot be null";

        _charset = charset;
        _words = words;
        _size = wordSize;

        _difficultyMap = new HashMap<Integer, ArrayList<JWord>>();
        _wordMap = new HashMap<String, JWord>();

        int minDifficulty = Integer.MAX_VALUE;
        int maxDifficulty = Integer.MIN_VALUE;
        for (int i = 0; i < words.size(); i++) {
            JWord word = words.get(i);
            minDifficulty = Math.min(minDifficulty, word.difficulty());
            maxDifficulty = Math.max(maxDifficulty, word.difficulty());

            _wordMap.put(word.word(), word);
            ArrayList<JWord> lists = _difficultyMap.getOrDefault(word.difficulty(), null);
            if (lists == null) {
                lists = new ArrayList<JWord>();
                _difficultyMap.put(word.difficulty(), lists);
            }
            lists.add(word);
        }

        _minDifficulty = minDifficulty;
        _maxDifficulty = maxDifficulty;
    }

    /**
     * Creates a JDictionary resource from an InputStream.
     *
     * @param charset     The character set for the words.
     * @param inputStream The input stream containing the jotto dictionary.
     * @return The JDictionary resource that has been created from the stream.
     * @throws IOException IO exception related to the reading of the stream.
     */
    static public JDictionary fromStream(JCharset charset, InputStream inputStream) throws IOException {
        assert charset != null : "The provided JCharset 'charset' cannot be null";
        assert inputStream != null : "The provided InputStream 'inputStream' cannot be null";

        Scanner reader = new Scanner(inputStream);

        ArrayList<JWord> words = new ArrayList<JWord>();
        int size = 0;
        while (reader.hasNextLine()) {
            String txt = reader.nextLine();
            String[] data = txt.split(" ");

            if (data.length != 2) {
                throw new IllegalArgumentException("The input format is not of the form [WORD DIFFICULTY].");
            }

            String word = data[0];
            int difficulty = parseInt(data[1]);
            size = word.length();

            if (word.isEmpty()) {
                throw new IllegalArgumentException("The argument 'word' cannot be the empty string.");
            }

            if (charset.invalid(word)) {
                throw new IllegalArgumentException("The argument 'word' does not match the character set of the dictionary.");
            }

            JWord jword = new JWord(word, difficulty);
            words.add(jword);
        }

        reader.close();

        return new JDictionary(charset, size, words);
    }

    /**
     * Determines if the specified word is present in the dictionary.
     *
     * @param word The word to detect if present within the dictionary.
     * @return True if the word exists in the dictionary; false otherwise.
     */
    public Boolean contains(String word) {
        assert word != null : "The provided String 'word' cannot be null";

        return _wordMap.containsKey(word);
    }

    /**
     * Gets a random word from the dictionary with a specified difficulty.
     *
     * @param difficulty The difficulty of the words to query against.
     * @return The word within the dictionary.
     */
    public JWord getRandomWord(int difficulty) {
        assert difficulty >= 0 : "The provided Integer 'difficulty' cannot less than zero";

        if (difficulty < _minDifficulty) {
            return null;
        } else if (difficulty > _maxDifficulty) {
            return null;
        }

        Random ran = new Random();
        ArrayList<JWord> list = _difficultyMap.get(difficulty);
        if (list == null || list.isEmpty()) {
            return null;
        }

        int index = ran.nextInt(list.size());
        JWord result = list.get(index);
        return result;
    }

    /**
     * Returns the word object associated with the word.
     *
     * @param word The word to retrieve from the dictionary.
     * @return The jotto word object.
     */
    public JWord getWord(String word) {
        assert word != null : "The provided String 'word' cannot be null";
        assert !word.isEmpty() : "The argument 'word' cannot be the empty string";

        if (!contains(word)) {
            throw new IllegalArgumentException("The argument 'word' is not present in the dictionary.");
        }

        return _wordMap.get(word);
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
    public int length() {
        return _words.size();
    }

    /**
     * Returns the dictionary word size.
     *
     * @return The length of words within the dictionary.
     */
    public int size() {
        return _size;
    }
}
