package ca.jotto;

import org.junit.Test;
import sun.misc.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class JDictionaryTest {

    @Test(expected = AssertionError.class)
    public void constructor_words() throws Exception {
        new JDictionary(JCharset.DEFAULT, 5, null);
    }

    @Test(expected = AssertionError.class)
    public void constructor_null() throws Exception {
        new JDictionary(null, 5, TestHelper.getWordList());
    }

    @Test(expected = AssertionError.class)
    public void constructor_zero() throws Exception {
        new JDictionary(JCharset.DEFAULT, 0, TestHelper.getWordList());
    }

    @Test
    public void contains() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList());

        assertTrue(dictionary.contains("OTHER"));
        assertTrue(dictionary.contains("WORLD"));
        assertFalse(dictionary.contains("SOOTH"));
    }

    @Test(expected = AssertionError.class)
    public void contains_null() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList());

        dictionary.contains(null);
    }

    @Test
    public void getRandomWord() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList());

        JWord word = dictionary.getRandomWord(3);
        assertEquals("WORLD", word.word());
        assertEquals(3, word.difficulty());
    }

    @Test
    public void getRandomWord_empty() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList());

        JWord word = dictionary.getRandomWord(2);
        assertNull(word);
    }

    @Test
    public void getRandomWord_above() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList());

        JWord word = dictionary.getRandomWord(300);
        assertNull(word);
    }

    @Test
    public void getRandomWord_below() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList());

        JWord word = dictionary.getRandomWord(0);
        assertNull(word);
    }

    @Test(expected = AssertionError.class)
    public void getRandomWord_negative() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList());
        JWord word = dictionary.getRandomWord(-1);
    }

    @Test
    public void getWord() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList());
        JWord word = dictionary.getWord("OTHER");

        assertEquals("OTHER", word.word());
        assertEquals(1, word.difficulty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWord_not() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList());
        JWord word = dictionary.getWord("SOOTH");
    }

    @Test(expected = AssertionError.class)
    public void getWord_null() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList());
        JWord word = dictionary.getWord(null);
    }

    @Test(expected = AssertionError.class)
    public void getWord_empty() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList());
        JWord word = dictionary.getWord("");
    }

    @Test
    public void getWords() throws Exception {
        ArrayList<JWord> words = TestHelper.getWordList();

        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList());
        JWord[] elements = dictionary.getWords();

        assertEquals(words.size(), dictionary.length());
        assertEquals(words.size(), elements.length);
        for (int i = 0; i < words.size(); i++) {
            JWord word = words.get(i);
            JWord elem = elements[i];

            assertEquals(word.difficulty(), elem.difficulty());
            assertEquals(word.length(), elem.length());
            assertEquals(word.word(), elem.word());
        }
    }

    @Test
    public void getWords_empty() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, new ArrayList<JWord>());
        assertEquals(JCharset.DEFAULT, dictionary.getCharset());

        JWord[] elements = dictionary.getWords();
        assertEquals(0, elements.length);
    }

    @Test
    public void getCharset() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, new ArrayList<JWord>());
        assertEquals(JCharset.DEFAULT, dictionary.getCharset());
    }

    @Test
    public void size() throws Exception {
        int size = 5;
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, size, new ArrayList<JWord>());
        assertEquals(size, dictionary.size());
    }

    @Test
    public void length() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList());
        assertEquals(3, dictionary.length());
    }

    @Test
    public void length_empty() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, 5, new ArrayList<JWord>());
        assertEquals(0, dictionary.length());
    }

    @Test
    public void fromStream() throws Exception {
        InputStream is = new ByteArrayInputStream("SOOTH 1\nCARVE 1\nRECUT 2\nBOOKY 2\n".getBytes());
        JDictionary dictionary = JDictionary.fromStream(JCharset.DEFAULT, is);

        assertEquals(4, dictionary.length());
        assertTrue(dictionary.contains("SOOTH"));
        assertTrue(dictionary.contains("RECUT"));
        assertFalse(dictionary.contains("LOOKY"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromStream_invalid() throws Exception {
        InputStream is = new ByteArrayInputStream("SOOTH\n".getBytes());
        JDictionary.fromStream(JCharset.DEFAULT, is);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromStream_empty() throws Exception {
        InputStream is = new ByteArrayInputStream(" 1\n".getBytes());
        JDictionary.fromStream(JCharset.DEFAULT, is);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromStream_format_int() throws Exception {
        InputStream is = new ByteArrayInputStream("SOOTH TEMP\n".getBytes());
        JDictionary.fromStream(JCharset.DEFAULT, is);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromStream_format_extra() throws Exception {
        InputStream is = new ByteArrayInputStream("SOOTH 1 1\n".getBytes());
        JDictionary.fromStream(JCharset.DEFAULT, is);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromStream_format_chars() throws Exception {
        InputStream is = new ByteArrayInputStream("HELLO! 1\n".getBytes());
        JDictionary.fromStream(JCharset.DEFAULT, is);
    }
}