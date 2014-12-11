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
 * game
 */
public final class JDictionary {

    private final Map<Integer, ArrayList<JWord>> _wordmap;
    private final ArrayList<JWord> _words;
    private int _minDifficulty;
    private int _maxDifficulty;

    /**
     * Initializes the jotto dictionary based on a specific word size
     */
    public JDictionary() {
        _wordmap = new HashMap<Integer, ArrayList<JWord>>();
        _words = new ArrayList<JWord>();

        _minDifficulty = Integer.MAX_VALUE;
        _maxDifficulty = Integer.MIN_VALUE;
    }

    /**
     * Adds a word to the dictionary
     *
     * @param word the word to add to the dictionary 
     * @param difficulty the difficulty of the specific word
     */
    public void add(String word, int difficulty) {
        assert word != null;
        assert difficulty > 0;

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
     * Determines if the specified word is present in the dictionary
	 *
     * @param word
     * @return True if the word exists, false otherwise
     */
    public Boolean isWordPresent(String word) {
        for (int i = 0; i < _words.size(); i++) {
            if (_words.get(i).getWord().equals(word)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets a list of words that match the specified regular expression
	 *
     */
    public JWord[] getMatchingWords(String format) {
        ArrayList<JWord> words = new ArrayList<JWord>();
        for (JWord wrd : _words) {
            if (wrd.getWord().matches(format)) {
                words.add(wrd);
            }
        }

        return words.toArray(new JWord[words.size()]);
    }

    /**
     * Gets a jotto word that matches the specified string
	 *
     */
    public JWord getWord(String word) {
        for (int i = 0; i < _words.size(); i++) {
            if (_words.get(i).getWord().equals(word)) {
                return _words.get(i);
            }
        }
        return null;
    }

    /**
     * Gets a word present at a specified index
	 *
     */
    public JWord getWord(int index) {
        return _words.get(index);
    }

    /**
     * Gets the words in the jotto dictionary
	 *
     */
    public JWord[] getWords() {
        return _words.toArray(new JWord[_words.size()]);
    }

    /**
     * Gets the number of words present in the dictionary
	 *
     */
    public int count() {
        return _words.size();
    }

    /**
     * Gets a random word from the dictionary with a specified difficulty
	 *
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
     * Clears the jotto dictionary
	 *
     */
    public void clear() {
        _wordmap.clear();
        _words.clear();
    }

    /**
     * Imports the current jotto dictionary from a file
	 *
     */
    public void importFrom(String filepath) {
        File dFile = new File(filepath);
        if (!dFile.exists()) {
            System.out.println("Cannot find file: " + dFile.getAbsolutePath());
            return;
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
        }
    }

    /**
     * Exports the current jotto dictionary into a file
	 *
     */
    public void exportTo(String filepath) {
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
        }
    }

}
