package ca.jotto.model.exception;

/**
 * Represents errors that occur during application execution.
 */
public class JottoException extends Exception {

    /**
     * Initializes a new instance of the JottoException class with a specified error message.
     *
     * @param message The message that describes the error. The content of message is intended to be understood by humans.
     */
    public JottoException(String message) {
        super(message);
    }
}
