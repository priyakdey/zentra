package com.priyakdey.planner.exception;

import java.io.Serial;

/**
 * @author Priyak Dey
 */
public class BadRequestException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 5801007786950347066L;

    public BadRequestException(String message) {
        super(message);
    }
}
