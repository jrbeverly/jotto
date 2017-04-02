package ca.jotto;

import org.junit.Test;

import static org.junit.Assert.*;

public class JCharIndexTest {

    @Test(expected = AssertionError.class)
    public void constructor() throws Exception {
        JCharIndex jchar = new JCharIndex(-1, 'a');
    }

    @Test
    public void index() throws Exception {
        JCharIndex jchar = new JCharIndex(0, 'a');
        assertEquals(0, jchar.index());
    }

    @Test
    public void value() throws Exception {
        JCharIndex jchar = new JCharIndex(0, 'a');
        assertEquals((Character)'a', jchar.value());
    }

}