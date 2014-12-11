package jotto.core;

/**
 * An enumeration that reflects the current state of the jotto game
 */
public enum JGameState {

	/**
	 * The game is idle, no match is in progress
	 */
	IDLE,
	/**
	 * A game is currently in progress
	 */
	PLAYING,
	/**
	 * The game is over, the player has yielded
	 */
	YIELDED,
	/**
	 * The game is over, the player has lost
	 */
	LOST,
	/**
	 * The game is over, the player has won
	 */
	WON
}
