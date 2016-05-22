package ca.jotto.listeners;

import java.util.ArrayList;

import ca.jotto.JGameState;
import ca.jotto.JGuess;
import ca.jotto.Jotto;
import ca.jotto.JMatch;

/**
 * An event map designed to handle game events for the jotto game.
 */
public class JottoEventMap {

    private final ArrayList<JottoListener> _listeners;

    /**
     * Creates the event map designed to handle events for the specified jotto
     * game.
     */
    public JottoEventMap() {
        _listeners = new ArrayList<JottoListener>();
    }

    /**
     * Adds a game event listeners to the jotto events map.
     *
     * @param listener The listener to add to the event map.
     */
    public void addListener(JottoListener listener) {
        assert listener != null;
        _listeners.add(listener);
    }

    /**
     * Removes a game event listeners to the jotto events map.
     *
     * @param listener The listener to remove from the event map.
     */
    public void removeListener(JottoListener listener) {
        assert listener != null;
        _listeners.remove(listener);
    }

    /**
     * Invoked when a player guess was incorrect.
     *
     * @param guess The guess that the player submitted.
     */
    public void onTurnIncorrect(Jotto jotto, JGuess guess) {
        assert jotto != null;
        assert guess != null;

        for (int i = 0; i < _listeners.size(); i++) {
            TurnListener lst = as(TurnListener.class, _listeners.get(i));
            if (lst != null)
                lst.onTurnIncorrect(jotto, guess);
        }
    }

    /**
     * Invoked when a player guess was correct.
     *
     * @param guess The guess that the player submitted.
     */
    public void onTurnCorrect(Jotto jotto, JGuess guess) {
        assert jotto != null;
        assert guess != null;

        for (int i = 0; i < _listeners.size(); i++) {
            TurnListener lst = as(TurnListener.class, _listeners.get(i));
            if (lst != null)
                lst.onTurnCorrect(jotto, guess);
        }
    }

    /**
     * Invoked when a player guesses.
     *
     * @param guess The guess that the player submitted.
     */
    public void onTurnGuess(Jotto jotto, JGuess guess) {
        assert jotto != null;
        assert guess != null;

        for (int i = 0; i < _listeners.size(); i++) {
            TurnListener lst = as(TurnListener.class, _listeners.get(i));
            if (lst != null)
                lst.onTurnGuess(jotto, guess);
        }
    }

    /**
     * Invoked when a jotto game state has been modified.
     *
     * @param oldState the previous game state.
     * @param newState the new game state.
     */
    public void onGameStateChanged(Jotto jotto, JGameState oldState, JGameState newState) {
        assert jotto != null;
        assert oldState != null;
        assert newState != null;

        for (int i = 0; i < _listeners.size(); i++) {
            StateListener lst = as(StateListener.class, _listeners.get(i));
            if (lst != null)
                lst.onGameStateChanged(jotto, oldState, newState);
        }
    }

    /**
     * Invoked when a character has been eliminated in a jotto game.
     *
     * @param character the character eliminated.
     */
    public void onCharacterEliminated(Jotto jotto, char character) {
        assert jotto != null;
        assert Character.isDefined(character);

        for (int i = 0; i < _listeners.size(); i++) {
            StateListener lst = as(StateListener.class, _listeners.get(i));
            if (lst != null)
                lst.onCharacterEliminated(jotto, character);
        }
    }

    /**
     * Invoked when a character has been matched in a jotto game.
     *
     * @param character the character determined.
     */
    public void onCharacterExact(Jotto jotto, char character) {
        assert jotto != null;
        assert Character.isDefined(character);

        for (int i = 0; i < _listeners.size(); i++) {
            StateListener lst = as(StateListener.class, _listeners.get(i));
            if (lst != null)
                lst.onCharacterExact(jotto, character);
        }
    }

    /**
     * Invoked when a jotto match is started.
     */
    public void onMatchStart(Jotto jotto, JMatch match) {
        assert jotto != null;

        for (int i = 0; i < _listeners.size(); i++) {
            GameListener lst = as(GameListener.class, _listeners.get(i));
            if (lst != null)
                lst.onMatchStart(jotto, match);
        }
    }

    /**
     * Invoked when a jotto match is over.
     */
    public void onMatchOver(Jotto jotto, JMatch match) {
        assert jotto != null;

        for (int i = 0; i < _listeners.size(); i++) {
            GameListener lst = as(GameListener.class, _listeners.get(i));
            if (lst != null)
                lst.onMatchOver(jotto, match);
        }
    }

    /**
     * Invoked when a player in a jotto match has yielded.
     */
    public void onPlayerYield(Jotto jotto, JMatch match) {
        assert jotto != null;

        for (int i = 0; i < _listeners.size(); i++) {
            GameListener lst = as(GameListener.class, _listeners.get(i));
            if (lst != null)
                lst.onPlayerYield(jotto, match);
        }
    }

    /**
     * Invoked when the player in a jotto match wins.
     */
    public void onPlayerWin(Jotto jotto, JMatch match) {
        assert jotto != null;

        for (int i = 0; i < _listeners.size(); i++) {
            GameListener lst = as(GameListener.class, _listeners.get(i));
            if (lst != null)
                lst.onPlayerWin(jotto, match);
        }
    }

    /**
     * Invoked when the player in a jotto match loses.
     */
    public void onPlayerLoss(Jotto jotto, JMatch match) {
        assert jotto != null;

        for (int i = 0; i < _listeners.size(); i++) {
            GameListener lst = as(GameListener.class, _listeners.get(i));
            if (lst != null)
                lst.onPlayerLoss(jotto, match);
        }
    }

    /**
     * A function to perform certain types of conversions between compatible
     * reference types or nullable types.
     *
     * @param type The class to convert to.
     * @param obj  The object to convert.
     * @return Returns the converted object if the type matches; false
     * otherwise.
     */
    private <T> T as(Class<T> type, Object obj) {
        return type.isInstance(obj) ? type.cast(obj) : null;
    }

}
