package ca.jotto;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class JDictionaryTest {

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_null_words() throws Exception {
        new JDictionary(JCharset.DEFAULT, 5, null);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_null_chars() throws Exception {
        new JDictionary(null, 5, TestHelper.getWordList());
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_zero() throws Exception {
        new JDictionary(JCharset.DEFAULT, 0, TestHelper.getWordList());
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_negative() throws Exception {
        new JDictionary(JCharset.DEFAULT, -1, TestHelper.getWordList());
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_size_mismatch() throws Exception {
        new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE + 10, TestHelper.getWordList());
    }

    @Category(ValidationTests.class)
    @Test
    public void difficulty_iterative() throws Exception {
        int min = 1;
        int max = 5;
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getDifficultyList(min, max));
        assertEquals(min, dictionary.minimum());
        assertEquals(max, dictionary.maximum());

        for (int i = min; i <= max; i++) {
            JWord word = dictionary.random(i);
            assertEquals(i, word.difficulty());
        }
    }

    @Category(FunctionalTests.class)
    @Test
    public void minimum() throws Exception {
        int min = 1;
        int max = 5;
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getDifficultyList(min, max));
        assertEquals(min, dictionary.minimum());
    }

    @Category(FunctionalTests.class)
    @Test
    public void maximum() throws Exception {
        int min = 1;
        int max = 5;
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getDifficultyList(min, max));
        assertEquals(max, dictionary.maximum());
    }

    @Category(ValidationTests.class)
    @Test
    public void difficulty_max() throws Exception {
        int min = 1;
        int max = 5;
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getDifficultyList(min, max));
        assertEquals(min, dictionary.minimum());
        assertEquals(max, dictionary.maximum());

        JWord word = dictionary.random(max);
        assertNotNull(word);
    }

    @Category(ValidationTests.class)
    @Test
    public void difficulty_min() throws Exception {
        int min = 1;
        int max = 5;
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getDifficultyList(min, max));
        assertEquals(min, dictionary.minimum());
        assertEquals(max, dictionary.maximum());

        JWord word = dictionary.random(min);
        assertNotNull(word);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void difficulty_above() throws Exception {
        int min = 2;
        int max = 6;
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getDifficultyList(min, max));
        assertEquals(min, dictionary.minimum());
        assertEquals(max, dictionary.maximum());

        dictionary.random(max + 1);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void difficulty_below() throws Exception {
        int min = 2;
        int max = 6;
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getDifficultyList(min, max));
        assertEquals(min, dictionary.minimum());
        assertEquals(max, dictionary.maximum());

        dictionary.random(min - 1);
    }

    @Category(FunctionalTests.class)
    @Test
    public void random() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList());

        JWord word = dictionary.random(3);
        assertEquals("WORLD", word.word());
        assertEquals(3, word.difficulty());
    }

    @Category(ValidationTests.class)
    @Test
    public void random_zero() throws Exception {
        int min = 0;
        int max = 2;
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getDifficultyList(min, max));
        assertEquals(min, dictionary.minimum());
        assertEquals(max, dictionary.maximum());

        dictionary.random(0);
    }

    @Category(ValidationTests.class)
    @Test
    public void random_empty() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList());

        JWord word = dictionary.random(2);
        assertNull(word);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void random_negative() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList());
        dictionary.random(-1);
    }

    @Category(ValidationTests.class)
    @Test
    public void contains() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList());

        assertTrue(dictionary.contains("OTHER"));
        assertTrue(dictionary.contains("WORLD"));
        assertFalse(dictionary.contains("SOOTH"));
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void contains_null() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList());

        dictionary.contains(null);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void contains_mismatch() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList());

        dictionary.contains("LONGER_THAN_WORD_SIZE");
    }

    @Category(ValidationTests.class)
    @Test
    public void size() throws Exception {
        int size = 4;
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, size, new ArrayList<JWord>());
        assertEquals(size, dictionary.size());
    }

    @Category(ValidationTests.class)
    @Test
    public void length() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList());
        assertEquals(TestHelper.WORDS, dictionary.length());
    }

    @Category(ValidationTests.class)
    @Test
    public void length_empty() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, new ArrayList<JWord>());
        assertEquals(0, dictionary.length());
    }

    @Category(FunctionalTests.class)
    @Test(expected = AssertionError.class)
    public void length_duplicates() throws Exception {
        ArrayList<JWord> words = TestHelper.getDuplicates();
        new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, words);
    }

    @Category(ValidationTests.class)
    @Test
    public void getCharset() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, new ArrayList<JWord>());
        assertEquals(JCharset.DEFAULT, dictionary.getCharset());
    }

    @Category(ValidationTests.class)
    @Test
    public void word() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList());
        JWord word = dictionary.get("OTHER");

        assertEquals("OTHER", word.word());
        assertEquals(1, word.difficulty());
    }

    @Category(ValidationTests.class)
    @Test(expected = IllegalArgumentException.class)
    public void word_not() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList());
        dictionary.get("SOOTH");
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void word_null() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList());
        dictionary.get(null);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void word_empty() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList());
        dictionary.get("");
    }

    @Category(ValidationTests.class)
    @Test
    public void toArray() throws Exception {
        ArrayList<JWord> words = TestHelper.getWordList();

        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList());
        JWord[] items = words.toArray(new JWord[words.size()]);
        JWord[] elements = dictionary.toArray();

        Arrays.sort(items);
        Arrays.sort(elements);

        assertEquals(items.length, dictionary.length());
        assertEquals(items.length, elements.length);
        for (int i = 0; i < items.length; i++) {
            JWord word = items[i];
            JWord elem = elements[i];

            assertEquals(word.difficulty(), elem.difficulty());
            assertEquals(word.length(), elem.length());
            assertEquals(word.word(), elem.word());
        }
    }

    @Category(ValidationTests.class)
    @Test
    public void toArray_empty() throws Exception {
        JDictionary dictionary = new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, new ArrayList<JWord>());
        assertEquals(JCharset.DEFAULT, dictionary.getCharset());

        JWord[] elements = dictionary.toArray();
        assertEquals(0, elements.length);
    }

    @Category(ValidationTests.class)
    @Test
    public void fromStream() throws Exception {
        InputStream is = new ByteArrayInputStream("SOOTH 1\nCARVE 1\nRECUT 2\nBOOKY 2\n".getBytes());
        JDictionary dictionary = JDictionary.fromStream(JCharset.DEFAULT, is);

        assertEquals(4, dictionary.length());
        assertTrue(dictionary.contains("SOOTH"));
        assertTrue(dictionary.contains("RECUT"));
        assertFalse(dictionary.contains("LOOKY"));
    }

    @Category(ValidationTests.class)
    @Test(expected = IllegalArgumentException.class)
    public void fromStream_invalid() throws Exception {
        InputStream is = new ByteArrayInputStream("SOOTH\n".getBytes());
        JDictionary.fromStream(JCharset.DEFAULT, is);
    }

    @Category(ValidationTests.class)
    @Test(expected = IllegalArgumentException.class)
    public void fromStream_empty() throws Exception {
        InputStream is = new ByteArrayInputStream(" 1\n".getBytes());
        JDictionary.fromStream(JCharset.DEFAULT, is);
    }

    @Category(ValidationTests.class)
    @Test(expected = IllegalArgumentException.class)
    public void fromStream_format_int() throws Exception {
        InputStream is = new ByteArrayInputStream("SOOTH TEMP\n".getBytes());
        JDictionary.fromStream(JCharset.DEFAULT, is);
    }

    @Category(ValidationTests.class)
    @Test(expected = IllegalArgumentException.class)
    public void fromStream_format_extra() throws Exception {
        InputStream is = new ByteArrayInputStream("SOOTH 1 1\n".getBytes());
        JDictionary.fromStream(JCharset.DEFAULT, is);
    }

    @Category(ValidationTests.class)
    @Test(expected = IllegalArgumentException.class)
    public void fromStream_format_chars() throws Exception {
        InputStream is = new ByteArrayInputStream("HELLO! 1\n".getBytes());
        JDictionary.fromStream(JCharset.DEFAULT, is);
    }
}