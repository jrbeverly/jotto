package ca.jotto.model.exception;

import ca.jotto.model.JValidation;

/**
 * Represents an Exception that occurs when a guess does not pass validation.
 */
public final class JottoValidationException extends JottoException {

    private final JValidation _validation;

    /**
     * Initializes a new instance of the JottoValidationException class with a specified error message.
     *
     * @param validation The validation enumeration value.
     * @param message The message that describes the error. The content of message is intended to be understood by humans.
     */
    public JottoValidationException(JValidation validation, String message) {
        super(message);
        _validation = validation;
    }
}
