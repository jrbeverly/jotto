package ca.jotto;

import ca.jotto.listeners.GameListener;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class JottoTest  {

    @Test
    public void getEventMap() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        assertNotNull(jotto.getEventMap());
    }

    @Test
    public void getCharset() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        assertNotNull(jotto.getCharset());
        assertEquals(JCharset.DEFAULT, jotto.getCharset());
    }

    @Test
    public void getWordSize() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        assertEquals(5, jotto.getWordSize());
    }

    @Test
    public void getDictionary() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        assertNotNull(jotto.getDictionary());
        assertEquals(3, jotto.getDictionary().length());
    }

    @Test
    public void start() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(3));
        assertNotNull(match);
    }

    @Test(expected = AssertionError.class)
    public void start_null() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        jotto.start(null);
    }

    @Test(expected = AssertionError.class)
    public void start_not() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        jotto.start(new JWord("SOOTH", 5));
    }
}