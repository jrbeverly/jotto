package jotto.core.listeners;

import jotto.core.JGameState;
import jotto.core.Jotto;

/**
 * A listener for requesting information onthe state listener
 * @author Jonathan
 */
public interface StateListener extends JottoListener {

    /** Notifies in the event of a game state changed
     * @param jotto the jotto game referenced
     * @param oldState the previous game state
     * @param newState the next game state */
    void onGameStateChanged(Jotto jotto, JGameState oldState, JGameState newState);

    /**Notifies that a letter has been eliminate
     * @param jotto the jotto game referenced
     * @param character the character eliminated */
    void onCharacterEliminated(Jotto jotto, char character);

    /**Notifies that a letter has been identified
     * @param jotto the jotto game referenced
     * @param character the character determined */
    void onCharacterExact(Jotto jotto, char character);

}
