package ca.jotto.exception;

/**
 * Represents an Exception that occurs when a guess does not pass validation.
 */
public final class JottoValidationException extends JottoException {

    /**
     * Initializes a new instance of the JottoValidationException class with a specified error message.
     *
     * @param message The message that describes the error. The content of message is intended to be understood by humans.
     */
    public JottoValidationException(String message) {
        super(message);
    }
}
