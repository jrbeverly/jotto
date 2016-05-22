package ca.jotto.exception;

import ca.jotto.JottoException;

/**
 * Created by Jonathan on 2016-05-20.
 */
public class JottoValidationException extends JottoException {
    public JottoValidationException(String message) {
        super(message);
    }
}
