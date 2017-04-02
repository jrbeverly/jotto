package ca.jotto;

import org.junit.Test;

import static org.junit.Assert.*;

public class JAnalyticsTest {

    @Test
    public void constructor() throws Exception {
        JAnalytics analytics = new JAnalytics(JCharset.DEFAULT, 5);
        assertNotNull(analytics);
    }

    @Test(expected = AssertionError.class)
    public void constructor_null() throws Exception {
        JAnalytics analytics = new JAnalytics(null, 5);
    }

    @Test(expected = AssertionError.class)
    public void constructor_zero() throws Exception {
        JAnalytics analytics = new JAnalytics(JCharset.DEFAULT, 0);
    }

    @Test(expected = AssertionError.class)
    public void constructor_negative() throws Exception {
        JAnalytics analytics = new JAnalytics(JCharset.DEFAULT, -1);
    }

    @Test(expected = AssertionError.class)
    public void compute_jotto_null() throws Exception {
        JAnalytics analytics = new JAnalytics(JCharset.DEFAULT, 5);
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        analytics.compute(null, jotto.getEventMap(), match.getHistory());
    }

    @Test(expected = AssertionError.class)
    public void compute_event_null() throws Exception {
        JAnalytics analytics = new JAnalytics(JCharset.DEFAULT, 5);
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        analytics.compute(jotto, null, match.getHistory());
    }

    @Test(expected = AssertionError.class)
    public void compute_history_null() throws Exception {
        JAnalytics analytics = new JAnalytics(JCharset.DEFAULT, 5);
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        analytics.compute(jotto, jotto.getEventMap(), null);
    }

    @Test
    public void compute_exact() throws Exception {
        JAnalytics analytics = new JAnalytics(JCharset.DEFAULT, 5);
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        match.start();

        MutableBoolean flag = new MutableBoolean(false);
        jotto.getEventMap().addListener(new OnExact(flag));

        match.guess("HELLO");
        analytics.compute(jotto, jotto.getEventMap(), match.getHistory());
        assertTrue(flag.get());
    }

    @Test
    public void compute_eliminated() throws Exception {
        JAnalytics analytics = new JAnalytics(JCharset.DEFAULT, 5);
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        match.start();

        MutableBoolean flag = new MutableBoolean(false);
        jotto.getEventMap().addListener(new OnEliminated(flag));

        match.guess("EJECT");
        analytics.compute(jotto, jotto.getEventMap(), match.getHistory());
        assertTrue(flag.get());
    }
}