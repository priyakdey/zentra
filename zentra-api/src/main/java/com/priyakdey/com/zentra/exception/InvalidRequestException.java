package com.priyakdey.com.zentra.exception;

import java.io.Serial;

/**
 * @author Priyak Dey
 */
public class InvalidRequestException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -66122538107031189L;

    public InvalidRequestException(String message) {
        super(message);
    }
}
