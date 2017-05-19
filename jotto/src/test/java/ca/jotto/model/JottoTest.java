package ca.jotto.model;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JottoTest {

    @Category(ValidationTests.class)
    @Test
    public void getEventMap() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        assertNotNull(jotto.getEventMap());
    }

    @Category(ValidationTests.class)
    @Test
    public void getCharset() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        assertNotNull(jotto.getCharset());
        assertEquals(JCharset.DEFAULT, jotto.getCharset());
    }

    @Category(ValidationTests.class)
    @Test
    public void getWordSize() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        assertEquals(TestHelper.WORD_SIZE, jotto.getWordSize());
    }

    @Category(ValidationTests.class)
    @Test
    public void getDictionary() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        assertNotNull(jotto.getDictionary());
        assertEquals(TestHelper.WORDS, jotto.getDictionary().length());
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_null() throws Exception {
        new Jotto(null);
    }

    @Category(ValidationTests.class)
    @Test
    public void construct() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(3));
        assertNotNull(match);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void construct_null() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        jotto.construct(null);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void construct_not() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        jotto.construct(new JWord("SOOTH", 5));
    }
}