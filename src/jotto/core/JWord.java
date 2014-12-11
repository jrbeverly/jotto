package jotto.core;

/**
 * Represents a jotto Word with associated attributes
 */
public final class JWord {

    private final String _word;
    private final int _difficulty;

    /**
     * Initializes the jotto word with the text and difficulty
     * @param word A string representing the word
     * @param difficulty The difficulty of the word
     */
    public JWord(String word, int difficulty) {
        assert word != null && difficulty > 0;
        
        _word = word;
        _difficulty = difficulty;
    }

    /**
     * Gets the String representation of the word
     * @return The string representation of the word
     */
    public String getWord() {
        return _word;
    }

    /**
     * Gets the difficulty of the word
     * @return The difficulty of the word
     */
    public int getDifficulty() {
        return _difficulty;
    }

    /**
     * Gets the length of the word
     * @return The length of the word
     */
    public int getWordLength() {
        return _word.length();
    }
}
/*
      public static final char START = 'A';
    public static final char END = 'Z';
    public static final int ALPHABET = 26;
    public static final char UNKNOWN_CHAR = '_';

    public static int getIndex(char character) {
        if (character < START || character > END) {
            return -1;
        }

        return character - START;
    }

    public static Boolean isCharacterValid(char character) {
        if (character < START || character > END) {
            return false;
        }

        return true;
    }

    public static Boolean isValid(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (JWord.isCharacterValid(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }
*/
