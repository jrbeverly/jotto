package ca.jotto.model;

import ca.jotto.model.exception.JottoException;
import ca.jotto.model.exception.JottoStateException;
import ca.jotto.model.exception.JottoValidationException;

/**
 * Represents a match of the jotto game.
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
     * Initializes a new instance of the {@link JMatch} class from the {@link Jotto} game.
     *
     * @param game           An object that contains information about the guess.
     * @param secret         The {@link JWord} to set as the secret.
     * @param maximumAttempt The maximum number of guesses allowed.
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
     * @return true if match is over; false otherwise.
     */
    public Boolean isGameOver() {
        return hasWon() || hasLost() || hasYielded();
    }

    /**
     * Determines if the match is currently in progress.
     *
     * @return true if match is in progress; false otherwise.
     */
    public Boolean isPlaying() {
        return _state == JGameState.PLAYING;
    }

    /**
     * Determines if the match has ended by yielding.
     *
     * @return true if match is over by yield; false otherwise.
     */
    public Boolean hasYielded() {
        return _state == JGameState.YIELDED;
    }

    /**
     * Determines if the victory conditions have been met.
     *
     * @return true if match is won; false otherwise.
     */
    public Boolean hasWon() {
        return _state == JGameState.WON;
    }

    /**
     * Determines if the loss conditions have been met.
     *
     * @return true if match is lost; false otherwise.
     */
    public Boolean hasLost() {
        return _state == JGameState.LOST;
    }

    /**
     * Returns the state of the match.
     *
     * @return The match state.
     */
    public JGameState getState() {
        return _state;
    }

    /**
     * Returns the current number of guesses.
     *
     * @return The current number of guesses.
     */
    public int getAttempts() {
        return _attempts;
    }

    /**
     * Returns the maximum number of guesses allowed.
     *
     * @return The maximum number of guess attempts allowed.
     */
    public int getMaximumAttempts() {
        return _maximumAttempts;
    }

    /**
     * Returns the {@link JMatch} guess history.
     *
     * @return The historical data.
     */
    public JHistory getHistory() {
        return _history;
    }

    /***
     * Returns computed historical data for the {@link JMatch}.
     *
     * @return The computed historical data.
     */
    public JAnalytics getAnalytics() {
        return _analytics;
    }

    /**
     * Returns the secret word of the game.
     *
     * @return The secret word.
     */
    public JSecret secret() {
        return _secret;
    }

    /**
     * Guesses the specified input string against the secret specified in the constructor.
     *
     * @param word The string to guess for a match.
     * @return An object that contains information about the guess.
     * @throws JottoStateException      An error occurred as the current state of the {@link JMatch} does not permit guessing.
     * @throws JottoValidationException An error occurred as the guess is not of a valid form.
     */
    public JGuess guess(String word) throws JottoStateException, JottoValidationException {
        assert word != null : "The provided String 'word' cannot be null";

        if (!isPlaying()) {
            throw new JottoStateException("The method 'guess' can only be called when the game is being played");
        }

        JValidation result = validate(word);
        if (result != JValidation.VALID) {
            throw new JottoValidationException(result, String.format("The guessed String 'word' is not valid form %s", result));
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

    //
    /**
     * Validates the {@link String} against the {@link JMatch} structure.
     *
     * @param word The string to validate for a guess.
     * @return An enumeration value that indicates the validation value.
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
     * Starts the match.
     *
     * @throws JottoStateException An error occurred as the current state of the {@link JMatch} cannot transition to playing.
     */
    public void start() throws JottoStateException {
        setState(JGameState.PLAYING);
    }

    /**
     * Yields the match.
     *
     * @throws JottoStateException An error occurred as the current state of the {@link JMatch} cannot transition to yielded.
     */
    public void yield() throws JottoException {
        if (_state != JGameState.PLAYING) {
            throw new JottoStateException("The game is not currently in progress.");
        }

        setState(JGameState.YIELDED);
    }

    /**
     * Provides a transition to a specified {@link JGameState}.
     *
     * @param targetState The {@link JGameState} to transition to.
     */
    private void setState(JGameState targetState) throws JottoStateException {
        assert targetState != null : "The provided JGameState 'targetState' cannot be null";

        if (targetState == _state) {
            throw new JottoStateException("The game state cannot be set to the current state");
        }

        _game.getEventMap().onGameStateChanged(_game, _state, targetState);
        _state = targetState;

        switch (targetState) {
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
}
