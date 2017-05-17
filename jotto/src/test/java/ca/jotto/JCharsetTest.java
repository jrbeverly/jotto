package ca.jotto;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class JCharsetTest {

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_null() throws Exception {
        new JCharset(null);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_empty() throws Exception {
        new JCharset(new char[0]);
    }

    @Category(ValidationTests.class)
    @Test
    public void length() throws Exception {
        JCharset charset = new JCharset(new char[]{'a'});
        assertEquals(1, charset.length());
    }

    @Category(ValidationTests.class)
    @Test
    public void length_iterative() throws Exception {
        for (int length = 1; length < 10; length++) {
            JCharset charset = new JCharset(new char[length]);
            assertEquals(length, charset.length());
        }
    }

    @Category(ValidationTests.class)
    @Test
    public void get() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b'});
        assertEquals(0, charset.get('a'));
        assertEquals(1, charset.get('b'));
    }

    @Category(ValidationTests.class)
    @Test
    public void get_iterative() throws Exception {
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f'};
        JCharset charset = new JCharset(chars);
        for (int i = 0; i < chars.length; i++) {
            assertEquals(i, charset.get(chars[i]));
        }
    }

    @Category(ValidationTests.class)
    @Test
    public void get_bad() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b'});
        assertEquals(-1, charset.get('c'));
    }

    @Category(ValidationTests.class)
    @Test
    public void at() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b'});
        assertEquals('a', charset.at(0));
        assertEquals('b', charset.at(1));
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void at_bounds_lower() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b'});
        charset.at(-1);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void at_bounds_upper() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b'});
        charset.at(5);
    }

    @Category(ValidationTests.class)
    @Test
    public void contains() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b'});
        assertTrue(charset.contains('a'));
        assertFalse(charset.contains('c'));
    }

    @Category(ValidationTests.class)
    @Test
    public void contains_string() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b', 'c', 'd'});
        assertTrue(charset.contains("abc"));
        assertFalse(charset.contains("abcde"));
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void contains_empty() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b', 'c', 'd'});
        charset.contains("");
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void contains_null() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b', 'c', 'd'});
        charset.contains(null);
    }

    @Category(ValidationTests.class)
    @Test
    public void contains_single() throws Exception {
        JCharset charset = new JCharset(new char[]{'a', 'b', 'c', 'd'});
        charset.contains("a");
    }
}