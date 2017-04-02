package ca.jotto;

import org.junit.Test;

import static org.junit.Assert.*;

public class JCharsetTest {

    @Test(expected = AssertionError.class)
    public void constructor_null() throws Exception {
        new JCharset(null);
    }

    @Test
    public void length() throws Exception {
        JCharset charset = new JCharset(new char[]{'a'});
        assertEquals(1, charset.length());
    }

    @Test
    public void get() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b'});
        assertEquals(0, charset.get('a'));
        assertEquals(1, charset.get('b'));
    }

    @Test
    public void get_bad() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b'});
        assertEquals(-1, charset.get('c'));
    }

    @Test
    public void at() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b'});
        assertEquals('a', charset.at(0));
        assertEquals('b', charset.at(1));
    }

    @Test(expected = AssertionError.class)
    public void at_bounds_lower() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b'});
        assertEquals('a', charset.at(-1));
    }

    @Test(expected = AssertionError.class)
    public void at_bounds_upper() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b'});
        assertEquals('a', charset.at(5));
    }

    @Test
    public void invalid() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b'});
        assertFalse(charset.invalid('a'));
        assertTrue(charset.invalid('c'));
    }

    @Test
    public void invalid1() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b', 'c', 'd'});
        assertFalse(charset.invalid("abc"));
        assertTrue(charset.invalid("abcdef"));
    }

    @Test
    public void valid() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b'});
        assertTrue(charset.valid('a'));
        assertFalse(charset.valid('c'));
    }

    @Test
    public void valid1() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b', 'c', 'd'});
        assertTrue(charset.valid("abc"));
        assertFalse(charset.valid("abcdef"));
    }

}