package ca.jotto.listeners;

import ca.jotto.JGuess;
import ca.jotto.Jotto;

/**
 * The listener interface for receiving turn game events.
 */
public interface TurnListener extends JottoListener {

    /**
     * Invoked when a player guess was incorrect.
     *
     * @param jotto the jotto game referenced.
     * @param guess The guess that the player submitted.
     */
    void onTurnIncorrect(Jotto jotto, JGuess guess);

    /**
     * Invoked when a player guess was correct.
     *
     * @param jotto the jotto game referenced.
     * @param guess The guess that the player submitted.
     */
    void onTurnCorrect(Jotto jotto, JGuess guess);

    /**
     * Invoked when a player guesses.
     *
     * @param jotto the jotto game referenced.
     * @param guess The guess that the player submitted.
     */
    void onTurnGuess(Jotto jotto, JGuess guess);
}
