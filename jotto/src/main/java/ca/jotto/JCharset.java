package ca.jotto;

/**
 * A set of characters that can be used within the jotto game.
 */
public class JCharset {

    /**
     * The set of uppercase alphabetic Unicode characters.
     */
    public static final JCharset UPPERCASE = new JCharset(new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'});

    /**
     * The set of lowercase alphabetic Unicode characters.
     */
    public static final JCharset LOWERCASE = new JCharset(new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'});

    /**
     * The default set of alphabetic Unicode characters.
     */
    public static final JCharset DEFAULT = UPPERCASE;

    private final char[] _charset;

    /**
     * Creates a character set from a range between two characters.
     *
     * @param characters The characters present in the character set.
     */
    public JCharset(char[] characters) {
        assert characters != null : "The provided array 'characters' cannot be null";
        assert characters.length != 0 : "The provided array 'characters' cannot be empty";

        _charset = characters;
    }

    /**
     * Returns the number of characters in the character set.
     *
     * @return Returns number of characters in set.
     */
    public int length() {
        return _charset.length;
    }

    /**
     * Returns the normalized index of the specified character.
     *
     * @param character The character.
     * @return Returns the index of the character.
     */
    public int get(char character) {
        for (int i = 0; i < _charset.length; i++) {
            if (character == _charset[i]) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Returns the normalized index of the specified character.
     *
     * @param index The index.
     * @return Returns the index of the character.
     */
    public char at(int index) {
        assert index >= 0 : "The provided Integer 'index' cannot be less than zero";
        assert index < _charset.length : "The provided Integer 'index' exceeds the charset size";

        return _charset[index];
    }

    /**
     * Returns true if the character is within the set.
     *
     * @param character The character to check.
     * @return True if within the set; false otherwise.
     */
    public Boolean contains(char character) {
        return get(character) != -1;
    }

    /**
     * Returns true if the string is within the set.
     *
     * @param word The word to check.
     * @return True if within the set; false otherwise.
     */
    public Boolean contains(String word) {
        assert word != null : "The provided String 'word' cannot be null";
        assert !word.isEmpty() : "The provided String 'word' does not contain characters";

        for (char wch : word.toCharArray()) {
            if (!contains(wch)) {
                return false;
            }
        }
        return true;
    }
}
