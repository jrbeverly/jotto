package ca.jotto.model;


import ca.jotto.model.listeners.GameListener;
import ca.jotto.model.listeners.StateListener;
import ca.jotto.model.listeners.TurnListener;

import static org.junit.Assert.assertNotNull;

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
    }

    @Override
    public void onPlayerWin(Jotto jotto, JMatch match) {
    }

    @Override
    public void onPlayerLoss(Jotto jotto, JMatch match) {
    }
}

class OnOver implements GameListener {
    private MutableBoolean _flag;

    public OnOver(MutableBoolean flag) {
        _flag = flag;
    }

    @Override
    public void onMatchStart(Jotto jotto, JMatch match) {
    }

    @Override
    public void onMatchOver(Jotto jotto, JMatch match) {
        assertNotNull(jotto);
        assertNotNull(match);

        _flag.set(true);
    }

    @Override
    public void onPlayerYield(Jotto jotto, JMatch match) {
    }

    @Override
    public void onPlayerWin(Jotto jotto, JMatch match) {
    }

    @Override
    public void onPlayerLoss(Jotto jotto, JMatch match) {
    }
}

class OnYield implements GameListener {
    private MutableBoolean _flag;

    public OnYield(MutableBoolean flag) {
        _flag = flag;
    }

    @Override
    public void onMatchStart(Jotto jotto, JMatch match) {
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

class OnWin implements GameListener {
    private MutableBoolean _flag;

    public OnWin(MutableBoolean flag) {
        _flag = flag;
    }

    @Override
    public void onMatchStart(Jotto jotto, JMatch match) {
    }

    @Override
    public void onMatchOver(Jotto jotto, JMatch match) {
    }

    @Override
    public void onPlayerYield(Jotto jotto, JMatch match) {
    }

    @Override
    public void onPlayerWin(Jotto jotto, JMatch match) {
        assertNotNull(jotto);
        assertNotNull(match);

        _flag.set(true);
    }

    @Override
    public void onPlayerLoss(Jotto jotto, JMatch match) {
    }
}

class OnLoss implements GameListener {
    private MutableBoolean _flag;

    public OnLoss(MutableBoolean flag) {
        _flag = flag;
    }

    @Override
    public void onMatchStart(Jotto jotto, JMatch match) {
    }

    @Override
    public void onMatchOver(Jotto jotto, JMatch match) {
    }

    @Override
    public void onPlayerYield(Jotto jotto, JMatch match) {
    }

    @Override
    public void onPlayerWin(Jotto jotto, JMatch match) {
    }

    @Override
    public void onPlayerLoss(Jotto jotto, JMatch match) {
        assertNotNull(jotto);
        assertNotNull(match);

        _flag.set(true);
    }
}

class OnChanged implements StateListener {
    private MutableBoolean _flag;

    public OnChanged(MutableBoolean flag) {
        _flag = flag;
    }

    @Override
    public void onGameStateChanged(Jotto jotto, JGameState oldState, JGameState newState) {
        assertNotNull(jotto);
        assertNotNull(oldState);
        assertNotNull(newState);

        _flag.set(true);
    }

    @Override
    public void onCharacterEliminated(Jotto jotto, char character) {

    }

    @Override
    public void onCharacterExact(Jotto jotto, char character) {

    }
}

class OnEliminated implements StateListener {
    private MutableBoolean _flag;

    public OnEliminated(MutableBoolean flag) {
        _flag = flag;
    }

    @Override
    public void onGameStateChanged(Jotto jotto, JGameState oldState, JGameState newState) {
    }

    @Override
    public void onCharacterEliminated(Jotto jotto, char character) {
        assertNotNull(jotto);

        _flag.set(true);
    }

    @Override
    public void onCharacterExact(Jotto jotto, char character) {
    }
}

class OnExact implements StateListener {
    private MutableBoolean _flag;

    public OnExact(MutableBoolean flag) {
        _flag = flag;
    }

    @Override
    public void onGameStateChanged(Jotto jotto, JGameState oldState, JGameState newState) {
    }

    @Override
    public void onCharacterEliminated(Jotto jotto, char character) {
    }

    @Override
    public void onCharacterExact(Jotto jotto, char character) {
        assertNotNull(jotto);

        _flag.set(true);
    }
}

class OnIncorrect implements TurnListener {
    private MutableBoolean _flag;

    public OnIncorrect(MutableBoolean flag) {
        _flag = flag;
    }

    @Override
    public void onTurnIncorrect(Jotto jotto, JGuess guess) {
        assertNotNull(jotto);
        assertNotNull(guess);

        _flag.set(true);
    }

    @Override
    public void onTurnCorrect(Jotto jotto, JGuess guess) {
    }

    @Override
    public void onTurnGuess(Jotto jotto, JGuess guess) {
    }
}

class OnCorrect implements TurnListener {
    private MutableBoolean _flag;

    public OnCorrect(MutableBoolean flag) {
        _flag = flag;
    }

    @Override
    public void onTurnIncorrect(Jotto jotto, JGuess guess) {
    }

    @Override
    public void onTurnCorrect(Jotto jotto, JGuess guess) {
        assertNotNull(jotto);
        assertNotNull(guess);

        _flag.set(true);
    }

    @Override
    public void onTurnGuess(Jotto jotto, JGuess guess) {
    }
}

class OnGuess implements TurnListener {
    private MutableBoolean _flag;

    public OnGuess(MutableBoolean flag) {
        _flag = flag;
    }

    @Override
    public void onTurnIncorrect(Jotto jotto, JGuess guess) {
    }

    @Override
    public void onTurnCorrect(Jotto jotto, JGuess guess) {
    }

    @Override
    public void onTurnGuess(Jotto jotto, JGuess guess) {
        assertNotNull(jotto);
        assertNotNull(guess);

        _flag.set(true);
    }
}