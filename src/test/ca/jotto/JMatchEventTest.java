package ca.jotto;

import ca.jotto.listeners.GameListener;
import ca.jotto.listeners.JottoEventMap;
import ca.jotto.listeners.StateListener;
import ca.jotto.listeners.TurnListener;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JMatchEventTest {

    private static final int LISTENERS = 5;

    @Test
    public void addListener() throws Exception {
        JottoEventMap eventMap = new JottoEventMap();
        MutableBoolean flag = new MutableBoolean(false);
        eventMap.addListener(new OnStart(flag));
    }

    @Test
    public void removeListener() throws Exception {
        JottoEventMap eventMap = new JottoEventMap();
        MutableBoolean flag = new MutableBoolean(false);

        OnStart listener = new OnStart(flag);
        eventMap.addListener(listener);
        eventMap.removeListener(listener);
    }

    @Test(expected = AssertionError.class)
    public void addListener_null() throws Exception {
        JottoEventMap eventMap = new JottoEventMap();
        eventMap.addListener(null);
    }

    @Test(expected = AssertionError.class)
    public void removeListener_null() throws Exception {
        JottoEventMap eventMap = new JottoEventMap();
        eventMap.removeListener(null);
    }

    @Test
    public void onTurnIncorrect_multi() throws Exception {
        JottoEventMap eventMap = new JottoEventMap();
        MutableBoolean[] flags = new MutableBoolean[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            flags[i] = new MutableBoolean(false);
        }

        OnIncorrect[] listeners = new OnIncorrect[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            listeners[i] = new OnIncorrect(flags[i]);
            eventMap.addListener(listeners[i]);
        }

        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        eventMap.onTurnIncorrect(jotto, TestHelper.getGuess());

        for (int i = 0; i < LISTENERS; i++) {
            assertTrue(flags[i].get());
        }
    }

    @Test
    public void onTurnCorrect_multi() throws Exception {
        JottoEventMap eventMap = new JottoEventMap();
        MutableBoolean[] flags = new MutableBoolean[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            flags[i] = new MutableBoolean(false);
        }

        OnCorrect[] listeners = new OnCorrect[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            listeners[i] = new OnCorrect(flags[i]);
            eventMap.addListener(listeners[i]);
        }

        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        eventMap.onTurnCorrect(jotto, TestHelper.getGuess());

        for (int i = 0; i < LISTENERS; i++) {
            assertTrue(flags[i].get());
        }
    }

    @Test
    public void onTurnGuess_multi() throws Exception {
        JottoEventMap eventMap = new JottoEventMap();
        MutableBoolean[] flags = new MutableBoolean[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            flags[i] = new MutableBoolean(false);
        }

        OnGuess[] listeners = new OnGuess[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            listeners[i] = new OnGuess(flags[i]);
            eventMap.addListener(listeners[i]);
        }

        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        eventMap.onTurnGuess(jotto, TestHelper.getGuess());

        for (int i = 0; i < LISTENERS; i++) {
            assertTrue(flags[i].get());
        }
    }

    @Test
    public void onGameStateChanged_multi() throws Exception {
        JottoEventMap eventMap = new JottoEventMap();
        MutableBoolean[] flags = new MutableBoolean[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            flags[i] = new MutableBoolean(false);
        }

        OnChanged[] listeners = new OnChanged[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            listeners[i] = new OnChanged(flags[i]);
            eventMap.addListener(listeners[i]);
        }

        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        eventMap.onGameStateChanged(jotto, JGameState.PLAYING, JGameState.WON);

        for (int i = 0; i < LISTENERS; i++) {
            assertTrue(flags[i].get());
        }
    }

    @Test
    public void onCharacterEliminated_multi() throws Exception {
        JottoEventMap eventMap = new JottoEventMap();
        MutableBoolean[] flags = new MutableBoolean[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            flags[i] = new MutableBoolean(false);
        }

        OnEliminated[] listeners = new OnEliminated[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            listeners[i] = new OnEliminated(flags[i]);
            eventMap.addListener(listeners[i]);
        }

        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        eventMap.onCharacterEliminated(jotto, 'A');

        for (int i = 0; i < LISTENERS; i++) {
            assertTrue(flags[i].get());
        }
    }

    @Test
    public void onCharacterExact_multi() throws Exception {
        JottoEventMap eventMap = new JottoEventMap();
        MutableBoolean[] flags = new MutableBoolean[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            flags[i] = new MutableBoolean(false);
        }

        OnExact[] listeners = new OnExact[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            listeners[i] = new OnExact(flags[i]);
            eventMap.addListener(listeners[i]);
        }

        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        eventMap.onCharacterExact(jotto, 'A');

        for (int i = 0; i < LISTENERS; i++) {
            assertTrue(flags[i].get());
        }
    }

    @Test
    public void onMatchStart_multi() throws Exception {
        JottoEventMap eventMap = new JottoEventMap();
        MutableBoolean[] flags = new MutableBoolean[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            flags[i] = new MutableBoolean(false);
        }

        OnStart[] listeners = new OnStart[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            listeners[i] = new OnStart(flags[i]);
            eventMap.addListener(listeners[i]);
        }

        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        eventMap.onMatchStart(jotto, match);

        for (int i = 0; i < LISTENERS; i++) {
            assertTrue(flags[i].get());
        }
    }

    @Test
    public void onMatchOver_multi() throws Exception {
        JottoEventMap eventMap = new JottoEventMap();
        MutableBoolean[] flags = new MutableBoolean[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            flags[i] = new MutableBoolean(false);
        }

        OnOver[] listeners = new OnOver[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            listeners[i] = new OnOver(flags[i]);
            eventMap.addListener(listeners[i]);
        }

        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        eventMap.onMatchOver(jotto, match);

        for (int i = 0; i < LISTENERS; i++) {
            assertTrue(flags[i].get());
        }
    }

    @Test
    public void onPlayerYield_multi() throws Exception {
        JottoEventMap eventMap = new JottoEventMap();
        MutableBoolean[] flags = new MutableBoolean[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            flags[i] = new MutableBoolean(false);
        }

        OnYield[] listeners = new OnYield[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            listeners[i] = new OnYield(flags[i]);
            eventMap.addListener(listeners[i]);
        }

        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        eventMap.onPlayerYield(jotto, match);

        for (int i = 0; i < LISTENERS; i++) {
            assertTrue(flags[i].get());
        }
    }

    @Test
    public void onPlayerWin_multi() throws Exception {
        JottoEventMap eventMap = new JottoEventMap();
        MutableBoolean[] flags = new MutableBoolean[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            flags[i] = new MutableBoolean(false);
        }

        OnWin[] listeners = new OnWin[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            listeners[i] = new OnWin(flags[i]);
            eventMap.addListener(listeners[i]);
        }

        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        eventMap.onPlayerWin(jotto, match);

        for (int i = 0; i < LISTENERS; i++) {
            assertTrue(flags[i].get());
        }
    }

    @Test
    public void onPlayerLoss_multi() throws Exception {
        JottoEventMap eventMap = new JottoEventMap();
        MutableBoolean[] flags = new MutableBoolean[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            flags[i] = new MutableBoolean(false);
        }

        OnLoss[] listeners = new OnLoss[LISTENERS];
        for (int i = 0; i < LISTENERS; i++) {
            listeners[i] = new OnLoss(flags[i]);
            eventMap.addListener(listeners[i]);
        }

        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        JMatch match = jotto.construct(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        eventMap.onPlayerLoss(jotto, match);

        for (int i = 0; i < LISTENERS; i++) {
            assertTrue(flags[i].get());
        }
    }

    @Test
    public void onMatchStart() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        MutableBoolean flag = new MutableBoolean(false);
        jotto.getEventMap().addListener(new OnStart(flag));

        JMatch match = jotto.construct(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match);

        match.start();
        assertTrue(flag.get());
    }

    @Test
    public void onMatchOver() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        MutableBoolean flag = new MutableBoolean(false);
        jotto.getEventMap().addListener(new OnOver(flag));

        JMatch match = jotto.construct(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match);

        match.start();
        match.yield();
        assertTrue(flag.get());
    }

    @Test
    public void onPlayerWin() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        MutableBoolean flag = new MutableBoolean(false);
        jotto.getEventMap().addListener(new OnWin(flag));

        JWord word = jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY);
        JMatch match = jotto.construct(word);
        assertNotNull(match);

        match.start();
        match.guess(word.word());
        assertTrue(flag.get());
    }

    @Test
    public void onPlayerLoss() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        MutableBoolean flag = new MutableBoolean(false);
        jotto.getEventMap().addListener(new OnLoss(flag));

        JWord secret = jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY);
        JMatch match = new JMatch(jotto, new JSecret(secret), 1);

        match.start();
        match.guess("HELLO");
        assertTrue(flag.get());
    }

    @Test
    public void onPlayerYield() throws Exception {
        Jotto jotto = new Jotto(new JDictionary(JCharset.DEFAULT, 5, TestHelper.getWordList()));
        MutableBoolean flag = new MutableBoolean(false);
        jotto.getEventMap().addListener(new OnYield(flag));

        JMatch match = jotto.construct(jotto.getDictionary().getRandomWord(TestHelper.SINGLE_DIFFICULTY));
        assertNotNull(match);

        match.start();
        match.yield();
        assertTrue(flag.get());
    }
}
