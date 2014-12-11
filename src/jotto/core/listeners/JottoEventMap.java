package jotto.core.listeners;

import jotto.core.Listeners.JottoListener;
import java.util.ArrayList;

public class JottoEventMap  {

    private final ArrayList<JottoListener> _listeners;
    private final Jotto _game;

    protected JottoEventMap(Jotto game) {
        assert game != null;
        
        _game = game;
        _listeners = new ArrayList<JottoListener>();
    }

    /**
     * Adds a game event listeners to the jotto events map
     * @param listener The listener to add to the event map
     */
    public void addListener(JottoListener listener) {
        assert listener != null;
        
        _listeners.add(listener);
    }
    
    /**
     * Removes a game event listeners to the jotto events map
     * @param listener The listener to add to the event map
     */
    public void removeListener(JottoListener listener) {
         assert listener != null;
         
        _listeners.remove(listener);
    }

    public Jotto getGame() {
        return _game;
    }
    
    public void onGameStateChanged(JGameState oldState, JGameState newState) {
        for (JottoListener lst : _listeners) {
            lst.onGameStateChanged(oldState, newState);
        }
    }

    public void onMatchStart() {
        for (JottoListener lst : _listeners) {
            lst.onMatchStart();
        }
    }

    public void onMatchOver() {
        for (JottoListener lst : _listeners) {
            lst.onMatchOver();
        }
    }

    public void onPlayerYield() {
        for (JottoListener lst : _listeners) {
            lst.onPlayerYield();
        }
    }

    public void onPlayerWin() {
        for (JottoListener lst : _listeners) {
            lst.onPlayerWin();
        }
    }

    public void onPlayerLoss() {
        for (JottoListener lst : _listeners) {
            lst.onPlayerLoss();
        }
    }

    public void onPlayerWrong(JGuess guess) {
        for (JottoListener lst : _listeners) {
            lst.onPlayerWrong(guess);
        }
    }

    public void onPlayerGuess(JGuess guess) {
        for (JottoListener lst : _listeners) {
            lst.onPlayerGuess(guess);
        }
    }

    public void onCharacterEliminated(char character) {
        for (JottoListener lst : _listeners) {
            lst.onCharacterEliminated(character);
        }
    }

    public void onCharacterExact(char character) {
        for (JottoListener lst : _listeners) {
            lst.onCharacterExact(character);
        }
    }

}
