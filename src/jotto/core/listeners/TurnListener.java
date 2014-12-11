package jotto.core.listeners;

import jotto.core.JGuess;
import jotto.core.Jotto;

/**
 *
 * @author Jonathan
 */
public interface TurnListener extends JottoListener {

    void onTurnIncorrect(Jotto jotto, JGuess guess);
    
    void onTurnCorrect(Jotto jotto, JGuess guess);

    void onTurnGuess(Jotto jotto, JGuess guess);
}
