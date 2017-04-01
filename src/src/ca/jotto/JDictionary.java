package ca.jotto;

import java.io.*;
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

    private final int _size;
    private final int _minDifficulty;
    private final int _maxDifficulty;

    private final JCharset _charset;

    private final ArrayList<JWord> _words;
    private final Map<Integer, ArrayList<JWord>> _difficultyMap;
    private final HashMap<String, JWord> _wordMap;

    /**
     * Initializes the jotto dictionary based on a specific word size.
     *
     * @param wordSize The size of the dictionary words.
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
            minDifficulty = Math.min(minDifficulty, word.getDifficulty());
            maxDifficulty = Math.max(maxDifficulty, word.getDifficulty());

            _wordMap.put(word.getWord(), word);
            ArrayList<JWord> lists = _difficultyMap.getOrDefault(word.getDifficulty(), null);
            if (lists == null) {
                lists = new ArrayList<JWord>();
                _difficultyMap.put(word.getDifficulty(), lists);
            }
            lists.add(word);
        }

        _minDifficulty = minDifficulty;
        _maxDifficulty = maxDifficulty;
    }

    /**
     * Exports the a series of words from the jotto dictionary into a file. If
     * the file exists it is overwritten.
     *
     * @param filepath The file to export the dictionary to.
     * @return True if dictionary exporting successful; false otherwise.
     * @throws IOException IO exception related to the reading of a file.
     */
    static public void exportTo(JDictionary dictionary, String filepath) throws IOException {
        assert filepath != null;

        File dFile = new File(filepath);
        if (!dFile.exists()) {
            dFile.delete();
        }

        FileWriter fWriter = new FileWriter(dFile);
        BufferedWriter bWriter = new BufferedWriter(fWriter);

        try {
            JWord[] words = dictionary.getWords();
            for (int i = 0; i < words.length; i++) {
                JWord word = words[i];

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

    /**
     * Creates a JDictionary resource from a file.
     *
     * @param filepath The name of the file containing the jotto dictionary.
     * @return The JDictionary resource that has been created from the specified file.
     * @throws IOException IO exception related to the reading of a file.
     */
    static public JDictionary fromFile(JCharset charset, String filepath) throws IOException {
        assert filepath != null;

        File dFile = new File(filepath);
        if (!dFile.exists()) {
            throw new FileNotFoundException("The specified file could not be found: " + dFile.getAbsolutePath());
        }

        FileReader fReader = new FileReader(dFile);
        BufferedReader bReader = new BufferedReader(fReader);

        ArrayList<JWord> words = new ArrayList<JWord>();
        int size = 0;
        while (bReader.ready()) {
            String txt = bReader.readLine();
            String[] data = txt.split(" ");

            String word = data[0];
            int difficulty = Integer.parseInt(data[1]);
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

        bReader.close();
        fReader.close();

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
     * Determines if the specified word is present in the dictionary.
     *
     * @param word The word to detect if present within the dictionary.
     * @return True if the word exists in the dictionary; false otherwise.
     */
    public Boolean contains(JWord word) {
        assert word != null : "The provided String 'word' cannot be null";

        return _wordMap.containsKey(word.getWord());
    }

    /**
     * Gets a random word from the dictionary with a specified difficulty.
     *
     * @param difficulty The difficulty of the words to query against.
     * @return The word within the dictionary.
     */
    public JWord getRandomWord(int difficulty) {
        if (difficulty < _minDifficulty) {
            return null;
        } else if (difficulty > _maxDifficulty) {
            return null;
        }

        Random ran = new Random();
        ArrayList<JWord> list = _difficultyMap.get(difficulty);
        int index = ran.nextInt(list.size() - 1);
        JWord result = list.get(index);

        return result;
    }

    /**
     * Gets a list of words that match the specified regular expression.
     *
     * @param format The regular expression to which strings are to be matched.
     * @return This method returns an array of words if, and only if, this
     * string matches the given regular expression.
     */
    public JWord[] getMatchingWords(String format) {
        assert format != null : "The provided String 'format' cannot be null";

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
     * @param word The word to retrieve from the dictionary.
     * @return The jotto word object.
     */
    public JWord getWord(String word) {
        assert word != null : "The provided String 'word' cannot be null";

        if (word.isEmpty()) {
            throw new IllegalArgumentException("The argument 'word' cannot be the empty string.");
        }

        if (!contains(word)) {
            throw new IllegalArgumentException("The argument 'word' is not present in the dictionary.");
        }

        return _wordMap.get(word);
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index The index of the element to return.
     * @return This method returns the element at the specified position in this
     * list.
     */
    public JWord getWord(int index) {
        assert index >= 0 : "The provided Integer 'index' cannot be less than zero";
        assert index < _words.size() : "The provided Integer 'index' exceed the word count";

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
