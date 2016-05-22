package ca.jotto;

/**
 * Defines a character at specified index.
 */
public class JCharIndex {
    public final Integer _index;
    public final Character _character;

    public JCharIndex(Integer index, Character character) {
        assert index >= 0;

        _index = index;
        _character = character;
    }
}
