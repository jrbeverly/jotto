package jotto.core.listeners;

import jotto.core.JGameState;
import jotto.core.Jotto;

/**
 *
 * @author Jonathan
 */
public interface GameListener extends JottoListener {
     
    /***/
    void onMatchStart(Jotto jotto);

    /***/
    void onMatchOver(Jotto jotto);

    /***/
    void onPlayerYield(Jotto jotto);

    /***/
    void onPlayerWin(Jotto jotto);

    /***/
    void onPlayerLoss(Jotto jotto);
    
}
