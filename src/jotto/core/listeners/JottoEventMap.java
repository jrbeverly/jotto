package jotto.core.listeners;

import jotto.core.*;

import java.util.ArrayList;

/**
 * */
public class JottoEventMap {

	private final ArrayList<JottoListener> _listeners;
	private final Jotto _game;
	
	/**
	 * 
	 * 
	 * @param game The jotto game
	 * */
	public JottoEventMap(Jotto game) {
		assert game != null;

		_game = game;
		_listeners = new ArrayList<JottoListener>();
	}

	/**
	 * Adds a game event listeners to the jotto events map
	 * 
	 * @param listener The listener to add to the event map
	 */
	public void addListener(JottoListener listener) {
		assert listener != null;

		_listeners.add(listener);
	}

	/**
	 * Removes a game event listeners to the jotto events map
	 * 
	 * @param listener
	 *            The listener to add to the event map
	 */
	public void removeListener(JottoListener listener) {
		assert listener != null;

		_listeners.remove(listener);
	}

	/**
	 * 
	 */
	public Jotto getGame() {
		return _game;
	}

	/**
	 * 
	 */
	private <T> T as(Class<T> t, Object o) {
		return t.isInstance(o) ? t.cast(o) : null;
	}

	/**
	 * 
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
	 * 
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
	 * 
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
	 * 
	 */
	public void onGameStateChanged(JGameState oldState, JGameState newState) {
		for (int i = 0; i < _listeners.size(); i++) {
			StateListener lst = as(StateListener.class, _listeners.get(i));
			if (lst != null)
				lst.onGameStateChanged(_game, oldState, newState);
		}
	}

	/**
	 * 
	 */
	public void onCharacterEliminated(char character) {
		for (int i = 0; i < _listeners.size(); i++) {
			StateListener lst = as(StateListener.class, _listeners.get(i));
			if (lst != null)
				lst.onCharacterEliminated(_game, character);
		}
	}

	/**
	 * 
	 */
	public void onCharacterExact(char character) {
		for (int i = 0; i < _listeners.size(); i++) {
			StateListener lst = as(StateListener.class, _listeners.get(i));
			if (lst != null)
				lst.onCharacterExact(_game, character);
		}
	}

	/**
	 * 
	 */
	public void onMatchStart() {
		for (int i = 0; i < _listeners.size(); i++) {
			GameListener lst = as(GameListener.class, _listeners.get(i));
			if (lst != null)
				lst.onMatchStart(_game);
		}
	}

	/**
	 * 
	 */
	public void onMatchOver() {
		for (int i = 0; i < _listeners.size(); i++) {
			GameListener lst = as(GameListener.class, _listeners.get(i));
			if (lst != null)
				lst.onMatchOver(_game);
		}
	}

	/**
	 * 
	 */
	public void onPlayerYield() {
		for (int i = 0; i < _listeners.size(); i++) {
			GameListener lst = as(GameListener.class, _listeners.get(i));
			if (lst != null)
				lst.onPlayerYield(_game);
		}
	}

	/**
	 * 
	 */
	public void onPlayerWin() {
		for (int i = 0; i < _listeners.size(); i++) {
			GameListener lst = as(GameListener.class, _listeners.get(i));
			if (lst != null)
				lst.onPlayerWin(_game);
		}
	}

	/**
	 * 
	 */
	public void onPlayerLoss() {
		for (int i = 0; i < _listeners.size(); i++) {
			GameListener lst = as(GameListener.class, _listeners.get(i));
			if (lst != null)
				lst.onPlayerLoss(_game);
		}
	}
}
