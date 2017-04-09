package ca.jotto.exception;

/**
 * Represents the exception that is thrown when the match state is invalid.
 */
public class JottoStateException extends JottoException {

    /**
     * Initializes a new instance of the JottoStateException class with its message string set to message.
     *
     * @param message The message that describes the error. The content of message is intended to be understood by humans.
     */
    public JottoStateException(String message) {
        super(message);
    }
}
