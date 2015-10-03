package jotto.core.listeners;

import jotto.core.*;

import java.util.ArrayList;

/**
 * An event map designed to handle game events for the jotto game.
 * */
public class JottoEventMap {

	private final ArrayList<JottoListener> _listeners;
	private final Jotto _game;

	/**
	 * Creates the event map designed to handle events for the specified jotto
	 * game.
	 * 
	 * @param game
	 *            The jotto game to handle events for.
	 * */
	public JottoEventMap(Jotto game) {
		assert game != null;

		_game = game;
		_listeners = new ArrayList<JottoListener>();
	}

	/**
	 * Adds a game event listeners to the jotto events map.
	 * 
	 * @param listener
	 *            The listener to add to the event map.
	 */
	public void addListener(JottoListener listener) {
		assert listener != null;

		_listeners.add(listener);
	}

	/**
	 * Removes a game event listeners to the jotto events map.
	 * 
	 * @param listener
	 *            The listener to remove from the event map.
	 */
	public void removeListener(JottoListener listener) {
		assert listener != null;

		_listeners.remove(listener);
	}

	/**
	 * Returns the jotto game associated with the event map.
	 */
	public Jotto getGame() {
		return _game;
	}

	/**
	 * Invoked when a player guess was incorrect.
	 * 
	 * @param guess
	 *            The guess that the player submitted.
	 */
	public void onTurnIncorrect(JGuess guess) {
		assert guess != null;

		for (int i = 0; i < _listeners.size(); i++) {
			TurnListener lst = as(TurnListener.class, _listeners.get(i));
			if (lst != null)
				lst.onTurnIncorrect(_game, guess);
		}
	}

	/**
	 * Invoked when a player guess was correct.
	 * 
	 * @param guess
	 *            The guess that the player submitted.
	 */
	public void onTurnCorrect(JGuess guess) {
		assert guess != null;

		for (int i = 0; i < _listeners.size(); i++) {
			TurnListener lst = as(TurnListener.class, _listeners.get(i));
			if (lst != null)
				lst.onTurnCorrect(_game, guess);
		}
	}

	/**
	 * Invoked when a player guesses.
	 * 
	 * @param guess
	 *            The guess that the player submitted.
	 */
	public void onTurnGuess(JGuess guess) {
		assert guess != null;

		for (int i = 0; i < _listeners.size(); i++) {
			TurnListener lst = as(TurnListener.class, _listeners.get(i));
			if (lst != null)
				lst.onTurnGuess(_game, guess);
		}
	}

	/**
	 * Invoked when a jotto game state has been modified.
	 * 
	 * @param oldState
	 *            the previous game state.
	 * @param newState
	 *            the new game state.
	 */
	public void onGameStateChanged(JGameState oldState, JGameState newState) {
		for (int i = 0; i < _listeners.size(); i++) {
			StateListener lst = as(StateListener.class, _listeners.get(i));
			if (lst != null)
				lst.onGameStateChanged(_game, oldState, newState);
		}
	}

	/**
	 * Invoked when a character has been eliminated in a jotto game.
	 * 
	 * @param character
	 *            the character eliminated.
	 */
	public void onCharacterEliminated(char character) {
		for (int i = 0; i < _listeners.size(); i++) {
			StateListener lst = as(StateListener.class, _listeners.get(i));
			if (lst != null)
				lst.onCharacterEliminated(_game, character);
		}
	}

	/**
	 * Invoked when a character has been matched in a jotto game.
	 * 
	 * @param character
	 *            the character determined.
	 */
	public void onCharacterExact(char character) {
		for (int i = 0; i < _listeners.size(); i++) {
			StateListener lst = as(StateListener.class, _listeners.get(i));
			if (lst != null)
				lst.onCharacterExact(_game, character);
		}
	}

	/**
	 * Invoked when a jotto match is started.
	 * */
	public void onMatchStart() {
		for (int i = 0; i < _listeners.size(); i++) {
			GameListener lst = as(GameListener.class, _listeners.get(i));
			if (lst != null)
				lst.onMatchStart(_game);
		}
	}

	/**
	 * Invoked when a jotto match is over.
	 * */
	public void onMatchOver() {
		for (int i = 0; i < _listeners.size(); i++) {
			GameListener lst = as(GameListener.class, _listeners.get(i));
			if (lst != null)
				lst.onMatchOver(_game);
		}
	}

	/**
	 * Invoked when a player in a jotto match has yielded.
	 * */
	public void onPlayerYield() {
		for (int i = 0; i < _listeners.size(); i++) {
			GameListener lst = as(GameListener.class, _listeners.get(i));
			if (lst != null)
				lst.onPlayerYield(_game);
		}
	}

	/**
	 * Invoked when the player in a jotto match wins.
	 * */
	public void onPlayerWin() {
		for (int i = 0; i < _listeners.size(); i++) {
			GameListener lst = as(GameListener.class, _listeners.get(i));
			if (lst != null)
				lst.onPlayerWin(_game);
		}
	}

	/**
	 * Invoked when the player in a jotto match loses.
	 * */
	public void onPlayerLoss() {
		for (int i = 0; i < _listeners.size(); i++) {
			GameListener lst = as(GameListener.class, _listeners.get(i));
			if (lst != null)
				lst.onPlayerLoss(_game);
		}
	}

	/**
	 * A function to perform certain types of conversions between compatible
	 * reference types or nullable types.
	 * 
	 * @param type
	 *            The class to convert to.
	 * @param obj
	 *            The object to convert.
	 * @return Returns the converted object if the type matches; false
	 *         otherwise.
	 */
	private <T> T as(Class<T> type, Object obj) {
		return type.isInstance(obj) ? type.cast(obj) : null;
	}

}
