package com.priyakdey.zentra.exception;

import java.io.Serial;

/**
 * @author Priyak Dey
 */
public class InvalidCredentialsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1349261362937112916L;

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
