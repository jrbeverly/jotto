package jotto.core;

import jotto.core.listeners.JottoEventMap;

/**
 * Represents an instance of a game of jotto.
 */
public final class Jotto {

	private final JDictionary _dictionary;
	private final JHistory _history;
	private final int _maximumAttempts = 10;

	private JSecret _secret;
	private JGameState _state;
	private JottoEventMap _eventMap;
	private JCharset _characters;

	private Boolean _secretGuessed = false;
	private int _attempts = 0;

	/**
	 * Initializes the jotto game based on a dictionary.
	 *
	 * @param dictionary
	 *            The dictionary of words to use for the jotto game.
	 */
	public Jotto(JDictionary dictionary) {
		assert dictionary != null;

		_dictionary = dictionary;
		_characters = dictionary.getCharset();
		_eventMap = new JottoEventMap(this);
		_history = new JHistory(this);
		_state = JGameState.IDLE;
	}

	/**
	 * Resets the jotto game back to an initial state.
	 * */
	public void reset() {
		_state = JGameState.IDLE;
		_history.clear();
		_attempts = 0;
		_secretGuessed = false;
		_secret = null;
	}

	/**
	 * Sets the state of the jotto game.
	 *
	 * @param state
	 *            The state to assign to the jotto game.
	 */
	private void setState(JGameState state) {
		if (state == _state) {
			return;
		}

		_eventMap.onGameStateChanged(_state, state);
		_state = state;

		switch (state) {
		case IDLE:
			break;
		case PLAYING:
			_eventMap.onMatchStart();
			break;
		case LOST:
			_eventMap.onMatchOver();
			_eventMap.onPlayerLoss();
			break;
		case YIELDED:
			_eventMap.onMatchOver();
			_eventMap.onPlayerYield();
			break;
		case WON:
			_eventMap.onMatchOver();
			_eventMap.onPlayerWin();
			break;
		default:
			break;
		}
	}

	/**
	 * Signals that the match is started.
	 *
	 * @param word
	 *            The secret word to start the game with.
	 */
	public void start(JWord word) {
		assert word != null;

		if (_state == JGameState.IDLE) {
			_secret = new JSecret(this, word);
			setState(JGameState.PLAYING);
			_eventMap.onMatchStart();
		}
	}

	/**
	 * Yields the match.
	 */
	public void yield() {
		if (_state == JGameState.PLAYING) {
			setState(JGameState.YIELDED);
		}
	}

	/**
	 * Determines if the game is over.
	 *
	 * @return True if game is over; false otherwise.
	 */
	public Boolean isGameOver() {
		return (_maximumAttempts <= _attempts) || _secretGuessed;
	}

	/**
	 * Gets the maximum number of guesses allowed.
	 *
	 * @return The maximum number of guess attempts allowed.
	 */
	public int getMaximumAttempts() {
		return _maximumAttempts;
	}

	/**
	 * Returns the EventMap associated with the jotto game.
	 * 
	 * @return The event map of the jotto game.
	 * */
	public JottoEventMap getEventMap() {
		return _eventMap;
	}

	/**
	 * Returns the character set associated with the jotto game.
	 * 
	 * @return The character set of the jotto game.
	 * */
	public JCharset getCharset() {
		return _characters;
	}

	/**
	 * Gets the size of the words used in this jotto match.
	 *
	 * @return The word length within the dictionary.
	 */
	public int getWordSize() {
		return _dictionary.size();
	}

	/**
	 * Gets the state of the jotto game.
	 *
	 * @return The state of the jotto game.
	 */
	public JGameState getState() {
		return _state;
	}

	/**
	 * Gets the current number of attempts.
	 *
	 * @return The current number of attempts.
	 */
	public int getAttempts() {
		return _attempts;
	}

	/**
	 * Gets the secret word in the jotto game.
	 *
	 * @return The jotto secret word.
	 */
	public JSecret getSecret() {
		return _secret;
	}

	/**
	 * Gets the dictionary that the jotto game is using.
	 *
	 * @return The dictionary of the jotto game.
	 */
	public JDictionary getWordDictionary() {
		return _dictionary;
	}

	/**
	 * Gets the history of guesses of this jotto game.
	 *
	 * @return The history of guesses for this jotto game.
	 */
	public JHistory getHistory() {
		return _history;
	}

	/**
	 * Validates a string against conditions present in the game.
	 *
	 * @param word
	 *            The word is validate.
	 * @return The validation property of the word.
	 */
	public JValidation validate(String word) {
		assert word != null;

		if (word.length() != getWordSize()) {
			return JValidation.INVALID_SIZE;
		} else if (!_dictionary.isWordPresent(word)) {
			return JValidation.NOT_IN_DICTIONARY;
		} else if (_characters.isValid(word)) {
			return JValidation.INVALID_CHARACTER;
		} else if (_history.hasGuessed(word)) {
			return JValidation.PREVIOUSLY_GUESSED;
		}

		return JValidation.VALID;
	}

	/**
	 * Performs a guess of the secret word in the jotto game.
	 *
	 * @param word
	 *            The word to guess.
	 * @return null if the guess is invalid, otherwise a guess object.
	 */
	public JGuess guess(String word) {
		assert word != null;

		if (isGameOver()) {
			System.err
					.println("User cannot guess as the game state is not valid");
			return null;
		}

		if (validate(word) != JValidation.VALID) {
			System.err.println("Guess phrase is not valid");
			return null;
		}

		JGuess guess = _secret.guess(word);
		_history.add(guess);
		_attempts++;

		_eventMap.onTurnGuess(guess);

		_secretGuessed = guess.isCorrect();

		if (_secretGuessed) {
			setState(JGameState.WON);
		} else if (isGameOver()) {
			setState(JGameState.LOST);
		}

		return guess;
	}
}
