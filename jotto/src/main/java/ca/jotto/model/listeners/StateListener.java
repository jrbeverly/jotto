package ca.jotto.model.listeners;

import ca.jotto.model.JGameState;
import ca.jotto.model.Jotto;

/**
 * The listener interface for receiving state game events.
 */
public interface StateListener extends JottoListener {

    /**
     * Invoked when a game state has been modified.
     *
     * @param jotto    the jotto game referenced.
     * @param oldState the previous game state.
     * @param newState the new game state.
     */
    void onGameStateChanged(Jotto jotto, JGameState oldState, JGameState newState);

    /**
     * Invoked when a character has been eliminated in a game.
     *
     * @param jotto     the jotto game referenced.
     * @param character the character eliminated.
     */
    void onCharacterEliminated(Jotto jotto, char character);

    /**
     * Invoked when a character has been matched in a game.
     *
     * @param jotto     the jotto game referenced.
     * @param character the character determined.
     */
    void onCharacterExact(Jotto jotto, char character);

}
