package ca.jotto;

/**
 * Defines a general exception by Jotto.
 */
public class JottoException extends Exception {
    /**
     * Initializes a new instance of the JottoException class with its message string set to message.
     *
     * @param A String that describes the error. The content of message is intended to be understood by humans.
     */
    public JottoException(String message) {
        super(message);
    }
}
