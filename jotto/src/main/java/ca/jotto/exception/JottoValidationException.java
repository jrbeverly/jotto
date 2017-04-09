package ca.jotto.exception;

/**
 * Represents the exception that occurs during validation of a guess.
 */
public class JottoValidationException extends JottoException {

    /**
     * Initializes a new instance of the JottoValidationException class using a specified error message.
     *
     * @param message A String that describes the error. The content of message is intended to be understood by humans.
     */
    public JottoValidationException(String message) {
        super(message);
    }
}
