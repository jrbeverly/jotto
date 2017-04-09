package ca.jotto.listeners;

import ca.jotto.JGameState;
import ca.jotto.JGuess;
import ca.jotto.JMatch;
import ca.jotto.Jotto;

import java.util.ArrayList;

/**
 * An event map is a notification component for synchronous event handling.
 */
public class JottoEventMap {

    private final ArrayList<JottoListener> _listeners;

    /**
     * Initializes a new instance of the JottoEventMap class.
     */
    public JottoEventMap() {
        _listeners = new ArrayList<JottoListener>();
    }

    /**
     * Adds the specified listener to receive game events from this component.
     *
     * @param listener the game listener.
     */
    public void addListener(JottoListener listener) {
        assert listener != null;
        _listeners.add(listener);
    }

    /**
     * Removes the specified listener so that it no longer receives game events from this component.
     *
     * @param listener the game listener.
     */
    public void removeListener(JottoListener listener) {
        assert listener != null;
        _listeners.remove(listener);
    }

    /**
     * Invoked when a player guess was incorrect.
     *
     * @param jotto The Jotto game.
     * @param guess The player guess.
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
     * @param jotto The Jotto game.
     * @param guess The player guess.
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
     * @param jotto The Jotto game.
     * @param guess The player guess.
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
     * Invoked when a game state has been modified.
     *
     * @param jotto    the jotto game referenced.
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
     * Invoked when a character has been eliminated in a game.
     *
     * @param jotto     the jotto game referenced.
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
     * Invoked when a character has been matched in a game.
     *
     * @param jotto     the jotto game referenced.
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
     * Invoked when a match is started.
     *
     * @param jotto the jotto game referenced.
     * @param match The current game match.
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
     * Invoked when a match is over.
     *
     * @param jotto the jotto game referenced.
     * @param match The current game match.
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
     * Invoked when a player has yielded.
     *
     * @param jotto the jotto game referenced.
     * @param match The current game match.
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
     * Invoked when the player wins.
     *
     * @param jotto the jotto game referenced.
     * @param match The current game match.
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
     * Invoked when the player loses.
     *
     * @param jotto the jotto game referenced.
     * @param match The current game match.
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
