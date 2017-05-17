package ca.jotto;

import ca.jotto.exception.JottoStateException;
import ca.jotto.exception.JottoValidationException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class JMatchTest {

    @Category(ValidationTests.class)
    @Test
    public void constructor() {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JSecret secret = new JSecret(new JWord("HELLO", 2));
        new JMatch(jotto, secret, 1);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_game_empty() {
        JSecret secret = new JSecret(new JWord("HELLO", 2));
        new JMatch(null, secret, 1);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_secret_empty() {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        new JMatch(jotto, null, 1);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_negative() {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JSecret secret = new JSecret(new JWord("HELLO", 2));
        new JMatch(jotto, secret, -1);
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void constructor_zero() {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JSecret secret = new JSecret(new JWord("HELLO", 2));
        new JMatch(jotto, secret, 0);
    }

    @Category(BehaviourTests.class)
    @Test
    public void isGameOver() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        assertFalse(match.isGameOver());
        match.start();
        assertFalse(match.isGameOver());
        match.yield();
        assertTrue(match.isGameOver());
    }

    @Category(BehaviourTests.class)
    @Test
    public void isPlaying() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        assertFalse(match.isPlaying());
        match.start();
        assertTrue(match.isPlaying());
        assertEquals(match.getState(), JGameState.PLAYING);
        match.yield();
        assertFalse(match.isPlaying());
        assertEquals(match.getState(), JGameState.YIELDED);
    }

    @Category(BehaviourTests.class)
    @Test
    public void hasYielded() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        assertFalse(match.hasYielded());
        match.start();
        assertFalse(match.hasYielded());
        match.yield();
        assertTrue(match.hasYielded());
        assertEquals(match.getState(), JGameState.YIELDED);
    }

    @Category(BehaviourTests.class)
    @Test
    public void hasWon() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JWord secret = jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY);
        JMatch match = jotto.construct(secret);

        assertNotNull(match);
        match.start();
        match.guess(secret.word());
        assertTrue(match.hasWon());
        assertEquals(match.getState(), JGameState.WON);
    }

    @Category(BehaviourTests.class)
    @Test
    public void hasLost() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JWord secret = jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY);
        JMatch match = new JMatch(jotto, new JSecret(secret), 1);

        match.start();
        match.guess("HELLO");

        assertTrue(match.hasLost());
        assertEquals(match.getState(), JGameState.LOST);
    }

    @Category(BehaviourTests.class)
    @Test
    public void getState() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        assertEquals(JGameState.IDLE, match.getState());
        match.start();
        assertEquals(JGameState.PLAYING, match.getState());
        match.yield();
        assertEquals(JGameState.YIELDED, match.getState());
    }

    @Category(ValidationTests.class)
    @Test
    public void getAttempts() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        match.start();

        assertEquals(0, match.getAttempts());
        match.guess("OTHER");
        assertEquals(1, match.getAttempts());
    }

    @Category(ValidationTests.class)
    @Test
    public void getMaximumAttempts() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        assertEquals(Jotto.MAXIMUM_GUESS, match.getMaximumAttempts());
    }

    @Category(ValidationTests.class)
    @Test
    public void getHistory() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match.getHistory());
    }

    @Category(ValidationTests.class)
    @Test
    public void getAnalytics() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match.getAnalytics());
    }

    @Category(ValidationTests.class)
    @Test
    public void getSecret() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match.getSecret());
    }

    @Category(BehaviourTests.class)
    @Test(expected = JottoStateException.class)
    public void guess_playing() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        match.guess("WORLD");
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void guess_null() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        match.start();

        match.guess(null);
    }

    @Category(BehaviourTests.class)
    @Test(expected = JottoValidationException.class)
    public void guess_not() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));

        assertNotNull(match);
        match.start();

        match.guess("L33TZ");
    }

    @Category(BehaviourTests.class)
    @Test
    public void guess_onturn() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        MutableBoolean flag = new MutableBoolean(false);
        jotto.getEventMap().addListener(new OnGuess(flag));

        JMatch match = jotto.construct(jotto.getDictionary().random(3));
        assertNotNull(match);

        match.start();
        match.guess("OTHER");
        assertTrue(flag.get());
    }

    @Category(ValidationTests.class)
    @Test
    public void validate() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match);

        assertEquals(JValidation.VALID, match.validate("WORLD"));
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void validate_null() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match);

        match.validate(null);
    }

    @Category(ValidationTests.class)
    @Test
    public void validate_size() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match);

        assertEquals(JValidation.INVALID_SIZE, match.validate("CURRENTLY"));
    }

    @Category(ValidationTests.class)
    @Test
    public void validate_contains() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match);

        assertEquals(JValidation.NOT_IN_DICTIONARY, match.validate("WRONG"));
    }

    @Category(ValidationTests.class)
    @Test
    public void validate_invalid() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match);

        assertEquals(JValidation.INVALID_CHARACTER, match.validate("OPEN!"));
    }

    @Category(ValidationTests.class)
    @Test
    public void validate_history() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match);

        match.start();
        match.guess("WORLD");

        assertEquals(JValidation.PREVIOUSLY_GUESSED, match.validate("WORLD"));
    }

    @Category(BehaviourTests.class)
    @Test
    public void yield() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(3));
        assertNotNull(match);
        match.start();

        MutableBoolean flag = new MutableBoolean(false);
        jotto.getEventMap().addListener(new OnYield(flag));

        match.yield();
        assertTrue(flag.get());
    }

    @Category(BehaviourTests.class)
    @Test(expected = JottoStateException.class)
    public void yield_state() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(3));
        assertNotNull(match);
        match.yield();
    }

    @Category(BehaviourTests.class)
    @Test
    public void construct() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        MutableBoolean flag = new MutableBoolean(false);
        jotto.getEventMap().addListener(new OnStart(flag));

        JMatch match = jotto.construct(jotto.getDictionary().random(3));
        assertNotNull(match);

        match.start();
        assertTrue(flag.get());
    }

    @Category(BehaviourTests.class)
    @Test(expected = JottoStateException.class)
    public void construct_state() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, TestHelper.WORD_SIZE, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().random(3));
        assertNotNull(match);

        match.start();
        match.start();
    }
}