package jotto.core.listeners;

import jotto.core.Jotto;

/**
 * The listener interface for receiving match and player game events. The class that is interested in processing 
 * an game event implements this interface, and the object created with that class is registered
 * with a JottoEventMap, using the component's addListener method. When the action event occurs, 
 * that object's event method is invoked.
 */
public interface GameListener extends JottoListener {
     
    /**
     * Invoked when a jotto match is started.
     * @param jotto The jotto game whose match has started.
     * */
    void onMatchStart(Jotto jotto);

    /**
     * Invoked when a jotto match is over.
     * @param jotto The jotto game whose match has ended.
     * */
    void onMatchOver(Jotto jotto);

    /**
     * Invoked when a player in a jotto match has yielded.
     * @param jotto The jotto game where a player has yielded.
     * */
    void onPlayerYield(Jotto jotto);

    /**
     * Invoked when the player in a jotto match wins.
     * @param jotto The jotto game in which the player has won.
     * */
    void onPlayerWin(Jotto jotto);

    /**
     * Invoked when the player in a jotto match loses.
     * @param jotto The jotto game in which the player has lost.
     * */
    void onPlayerLoss(Jotto jotto); 
}
