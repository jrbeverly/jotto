package jotto.core.listeners;

import jotto.core.JGameState;
import jotto.core.Jotto;

/**
 * The listener interface for receiving state game events. The class that is interested in processing 
 * an game event implements this interface, and the object created with that class is registered
 * with a JottoEventMap, using the component's addListener method. When the action event occurs, 
 * that object's event method is invoked.
 */
public interface StateListener extends JottoListener {

    /** Invoked when a jotto game state has been modified.
     * @param jotto the jotto game referenced
     * @param oldState the previous game state
     * @param newState the new game state */
    void onGameStateChanged(Jotto jotto, JGameState oldState, JGameState newState);

    /** Invoked when a character has been eliminated in a jotto game.
     * @param jotto the jotto game referenced
     * @param character the character eliminated */
    void onCharacterEliminated(Jotto jotto, char character);

    /** Invoked when a character has been matched in a jotto game.
     * @param jotto the jotto game referenced
     * @param character the character determined */
    void onCharacterExact(Jotto jotto, char character);

}
