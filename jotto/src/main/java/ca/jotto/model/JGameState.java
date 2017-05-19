package ca.jotto.model;

/**
 * An enumeration that reflects the current state of the match.
 */
public enum JGameState {
    /**
     * The match is idle, no match is in progress.
     */
    IDLE,
    /**
     * The match is currently in progress.
     */
    PLAYING,
    /**
     * The match is over, the player has yielded.
     */
    YIELDED,
    /**
     * The match is over, the player has lost.
     */
    LOST,
    /**
     * The match is over, the player has won.
     */
    WON
}
