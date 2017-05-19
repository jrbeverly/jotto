package ca.jotto;

import ca.jotto.exception.JottoException;
import ca.jotto.exception.JottoStateException;
import ca.jotto.exception.JottoValidationException;

/**
 * Defines a match of the game.
 */
public final class JMatch {

    private final Jotto _game;
    private final JHistory _history;
    private final JSecret _secret;
    private final JAnalytics _analytics;
    private final int _maximumAttempts;

    private JGameState _state;
    private int _attempts = 0;

    /**
     * Initializes a match from the game structure.
     *
     * @param game           The game structure used by the match.
     * @param secret         The secret word for the match.
     * @param maximumAttempt The maximum number of guess attempts possible.
     */
    public JMatch(Jotto game, JSecret secret, int maximumAttempt) {
        assert game != null : "The provided JWord 'word' cannot be null";
        assert secret != null : "The provided JWord 'word' cannot be null";
        assert maximumAttempt > 0 : "The provided Integer 'maximumAttempt' must be greater than zero";

        _game = game;
        _secret = secret;

        _state = JGameState.IDLE;
        _maximumAttempts = maximumAttempt;
        _history = new JHistory(game.getCharset(), game.getWordSize());
        _analytics = new JAnalytics(game.getCharset(), game.getWordSize());
    }

    /**
     * Determines if the match is over.
     *
     * @return True if match is over; false otherwise.
     */
    public Boolean isGameOver() {
        return hasWon() || hasLost() || hasYielded();
    }

    /**
     * Determines if the match is currently in progress.
     *
     * @return True if match is in progress; false otherwise.
     */
    public Boolean isPlaying() {
        return _state == JGameState.PLAYING;
    }

    /**
     * Determines if the match has ended by yielding.
     *
     * @return True if match is over by yield; false otherwise.
     */
    public Boolean hasYielded() {
        return _state == JGameState.YIELDED;
    }

    /**
     * Determines if the user has won the match.
     *
     * @return True if match is won by user; false otherwise.
     */
    public Boolean hasWon() {
        return _state == JGameState.WON;
    }

    /**
     * Determines if the user has lost the match.
     *
     * @return True if match is lost by user; false otherwise.
     */
    public Boolean hasLost() {
        return _state == JGameState.LOST;
    }

    /**
     * Gets the state of the match.
     *
     * @return The state of the match.
     */
    public JGameState getState() {
        return _state;
    }

    /**
     * Sets the state of the match.
     *
     * @param state The state to assign to the match.
     */
    private void setState(JGameState state) throws JottoStateException {
        assert state != null : "The provided JGameState 'state' cannot be null";

        if (state == _state) {
            throw new JottoStateException("The JGameState cannot be set to the current state");
        }

        _game.getEventMap().onGameStateChanged(_game, _state, state);
        _state = state;

        switch (state) {
            case PLAYING:
                _game.getEventMap().onMatchStart(_game, this);
                break;
            case LOST:
                _game.getEventMap().onMatchOver(_game, this);
                _game.getEventMap().onPlayerLoss(_game, this);
                break;
            case YIELDED:
                _game.getEventMap().onMatchOver(_game, this);
                _game.getEventMap().onPlayerYield(_game, this);
                break;
            case WON:
                _game.getEventMap().onMatchOver(_game, this);
                _game.getEventMap().onPlayerWin(_game, this);
                break;
            default:
                break;
        }
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
     * Gets the maximum number of guesses allowed.
     *
     * @return The maximum number of guess attempts allowed.
     */
    public int getMaximumAttempts() {
        return _maximumAttempts;
    }

    /**
     * Gets the history of guesses.
     *
     * @return The history of guesses.
     */
    public JHistory getHistory() {
        return _history;
    }

    /***
     * Returns an analytics instance responsible for the current match.
     *
     * @return An analytics instance.
     */
    public JAnalytics getAnalytics() {
        return _analytics;
    }

    /**
     * Gets the secret word of the game.
     *
     * @return The secret word.
     */
    public JSecret secret() {
        return _secret;
    }


    /**
     * Starts the match.
     *
     * @throws JottoStateException If the match is not in the idle state.
     */
    public void start() throws JottoStateException {
        setState(JGameState.PLAYING);
    }

    /**
     * Performs a guess of the secret word in the game.
     *
     * @param word The word to guess.
     * @return null if the guess is invalid, otherwise a guess object.
     * @throws JottoStateException      If the match is not in a state where the player can guess.
     * @throws JottoValidationException If the guess is not valid.
     */
    public JGuess guess(String word) throws JottoStateException, JottoValidationException {
        assert word != null : "The provided String 'word' cannot be null";

        if (!isPlaying()) {
            throw new JottoStateException("User cannot guess as the game state is not valid");
        }

        if (validate(word) != JValidation.VALID) {
            throw new JottoValidationException("Guess phrase is not valid");
        }

        JGuess guess = _secret.guess(word);
        _history.add(guess);
        _attempts++;

        _analytics.compute(_game, _game.getEventMap(), _history);

        _game.getEventMap().onTurnGuess(_game, guess);

        boolean correct = guess.correct();
        if (correct) {
            setState(JGameState.WON);
        } else if (getAttempts() >= getMaximumAttempts()) {
            setState(JGameState.LOST);
        }

        return guess;
    }

    /**
     * Validates a string against conditions present in the game.
     *
     * @param word The word is validate.
     * @return The validation property of the word.
     */
    public JValidation validate(String word) {
        assert word != null : "The provided String 'word' cannot be null";

        if (word.length() != _game.getWordSize()) {
            return JValidation.INVALID_SIZE;
        } else if (!_game.getCharset().contains(word)) {
            return JValidation.INVALID_CHARACTER;
        } else if (!_game.getDictionary().contains(word)) {
            return JValidation.NOT_IN_DICTIONARY;
        } else if (_history.contains(word)) {
            return JValidation.PREVIOUSLY_GUESSED;
        }

        return JValidation.VALID;
    }

    /**
     * Yields the match.
     *
     * @throws JottoStateException If the match is not in the playing state.
     */
    public void yield() throws JottoException {
        if (_state != JGameState.PLAYING) {
            throw new JottoStateException("The game is not currently in progress.");
        }

        setState(JGameState.YIELDED);
    }
}
