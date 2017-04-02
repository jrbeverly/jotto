package ca.jotto;

/**
 * Defines a character at specified index.
 */
public class JCharIndex {

    private final Integer _index;
    private final Character _character;

    /**
     * Creates a character set from a range between two characters.
     *
     * @param index The index in the character set.
     * @param character The characters from the character set.
     */
    public JCharIndex(Integer index, Character character) {
        assert index >= 0 : "The provided Integer 'index' cannot be less than zero";

        _index = index;
        _character = character;
    }

    /**
     *
     *
     * @return Returns the index.
     */
    public int index() {
        return _index;
    }

    /**
     * @return The character value.
     */
    public Character value() {
        return _character;
    }
}
