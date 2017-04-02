package ca.jotto;

import ca.jotto.exception.JottoStateException;
import ca.jotto.exception.JottoValidationException;
import ca.jotto.listeners.GameListener;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class JMatchTest  {

    @Test
    public void isGameOver() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        assertFalse(match.isGameOver());
        match.start();
        assertFalse(match.isGameOver());
        match.yield();
        assertTrue(match.isGameOver());
    }

    @Test
    public void isPlaying() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        assertFalse(match.isPlaying());
        match.start();
        assertTrue(match.isPlaying());
        match.yield();
        assertFalse(match.isPlaying());
    }

    @Test
    public void hasYielded() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        assertFalse(match.hasYielded());
        match.start();
        assertFalse(match.hasYielded());
        match.yield();
        assertTrue(match.hasYielded());
    }

    @Test
    public void hasWon() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JWord secret = jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY);
        JMatch match = jotto.start(secret);

        assertNotNull(match);
        match.start();
        match.guess(secret.word());
        assertTrue(match.hasWon());
    }

    @Test
    public void hasLost() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JWord secret = jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY);
        JMatch match = new JMatch(jotto, new JSecret(secret), 1);

        match.start();
        match.guess("HELLO");

        assertTrue(match.hasLost());
    }

    @Test
    public void getState() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        assertEquals(JGameState.IDLE, match.getState());
        match.start();
        assertEquals(JGameState.PLAYING, match.getState());
        match.yield();
        assertEquals(JGameState.YIELDED, match.getState());
    }

    @Test
    public void getAttempts() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        match.start();

        assertEquals(0, match.getAttempts());
        match.guess("OTHER");
        assertEquals(1, match.getAttempts());
    }

    @Test
    public void getMaximumAttempts() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        assertEquals(Jotto.MAXIMUM_GUESS, match.getMaximumAttempts());
    }

    @Test
    public void getHistory() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match.getHistory());
    }

    @Test
    public void getAnalytics() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match.getAnalytics());
    }

    @Test
    public void getSecret() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match.getSecret());
    }

    @Test(expected = AssertionError.class)
    public void guess() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        match.start();

        match.guess(null);
    }

    @Test(expected = JottoStateException.class)
    public void guess_playing() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        match.guess("WORLD");
    }

    @Test(expected = JottoValidationException.class)
    public void guess_validation() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        match.start();

        match.guess("OPEN!");
    }

    @Test(expected = AssertionError.class)
    public void guess_null() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        match.start();

        match.guess(null);
    }

    @Test
    public void validate() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match);

        assertEquals(JValidation.VALID, match.validate("WORLD"));
    }

    @Test(expected = AssertionError.class)
    public void validate_null() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match);

        match.validate(null);
    }

    @Test
    public void validate_size() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match);

        assertEquals(JValidation.INVALID_SIZE, match.validate("CURRENTLY"));
    }

    @Test
    public void validate_contains() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match);

        assertEquals(JValidation.NOT_IN_DICTIONARY, match.validate("WRONG"));
    }

    @Test
    public void validate_invalid() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match);

        assertEquals(JValidation.INVALID_CHARACTER, match.validate("OPEN!"));
    }

    @Test
    public void validate_history() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match);

        match.start();
        match.guess("WORLD");

        assertEquals(JValidation.PREVIOUSLY_GUESSED, match.validate("WORLD"));
    }

    @Test
    public void yield() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(3));
        assertNotNull(match);
        match.start();

        MutableBoolean flag = new MutableBoolean(false);
        jotto.getEventMap().addListener(new OnStart(flag));

        match.yield();
        assertTrue(flag.get());
    }

    @Test(expected = JottoStateException.class)
    public void yield_state() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(3));
        assertNotNull(match);
        match.yield();
    }

    @Test
    public void start() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        MutableBoolean flag = new MutableBoolean(false);
        jotto.getEventMap().addListener(new OnStart(flag));

        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(3));
        assertNotNull(match);

        match.start();
        assertTrue(flag.get());
    }

    @Test(expected = JottoStateException.class)
    public void start_state() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.start(jotto.getDictionary().getRandomWord(3));
        assertNotNull(match);

        match.start();
        match.start();
    }

    class MutableBoolean {
        private boolean _value;

        public MutableBoolean(boolean value) {
            _value = value;
        }

        public boolean get() {
            return _value;
        }

        public void set(boolean value) {
            _value = value;
        }
    }

    class OnStart implements GameListener {
        private MutableBoolean _flag;

        public OnStart(MutableBoolean flag) {
            _flag = flag;
        }

        @Override
        public void onMatchStart(Jotto jotto, JMatch match) {
            assertNotNull(jotto);
            assertNotNull(match);

            _flag.set(true);
        }

        @Override
        public void onMatchOver(Jotto jotto, JMatch match) {
        }

        @Override
        public void onPlayerYield(Jotto jotto, JMatch match) {
            assertNotNull(jotto);
            assertNotNull(match);

            _flag.set(true);
        }

        @Override
        public void onPlayerWin(Jotto jotto, JMatch match) {
        }

        @Override
        public void onPlayerLoss(Jotto jotto, JMatch match) {
        }
    }
}