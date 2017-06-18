package ca.jotto.model;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JAnalyticsTest {

    @Category(ValidationTests.class)
    @Test
    public void constructor() throws Exception {
        JAnalytics analytics = new JAnalytics(JCharset.DEFAULT, TestHelper.WORD_SIZE);
        assertNotNull(analytics);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_null() throws Exception {
        new JAnalytics(null, TestHelper.WORD_SIZE);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_zero() throws Exception {
        new JAnalytics(JCharset.DEFAULT, 0);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_negative() throws Exception {
        new JAnalytics(JCharset.DEFAULT, -1);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void compute_jotto_null() throws Exception {
        JAnalytics analytics = new JAnalytics(JCharset.DEFAULT, TestHelper.WORD_SIZE);
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));

        analytics.compute(null, jotto.getEventMap(), match.getHistory());
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void compute_event_null() throws Exception {
        JAnalytics analytics = new JAnalytics(JCharset.DEFAULT, TestHelper.WORD_SIZE);
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));

        analytics.compute(jotto, null, match.getHistory());
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void compute_history_null() throws Exception {
        JAnalytics analytics = new JAnalytics(JCharset.DEFAULT, TestHelper.WORD_SIZE);
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));

        analytics.compute(jotto, jotto.getEventMap(), null);
    }

    @Category(FunctionalTests.class)
    @Test
    public void compute_exact() throws Exception {
        JAnalytics analytics = new JAnalytics(JCharset.DEFAULT, TestHelper.WORD_SIZE);
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));
        match.start();

        MutableBoolean flag = new MutableBoolean(false);
        jotto.getEventMap().addListener(new OnExact(flag));

        match.guess("HELLO");
        analytics.compute(jotto, jotto.getEventMap(), match.getHistory());
        assertTrue(flag.get());
    }

    @Category(FunctionalTests.class)
    @Test
    public void compute_eliminated() throws Exception {
        JAnalytics analytics = new JAnalytics(JCharset.DEFAULT, TestHelper.WORD_SIZE);
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));
        match.start();

        MutableBoolean flag = new MutableBoolean(false);
        jotto.getEventMap().addListener(new OnEliminated(flag));

        match.guess("EJECT");
        analytics.compute(jotto, jotto.getEventMap(), match.getHistory());
        assertTrue(flag.get());
    }
}